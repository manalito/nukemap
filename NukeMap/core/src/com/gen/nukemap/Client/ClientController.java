package com.gen.nukemap.Client;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.gen.nukemap.GameObject.Enemy;
import com.gen.nukemap.GameObject.Bomb;
import com.gen.nukemap.GameObject.Personage;
import com.gen.nukemap.GameObject.Player;
import java.util.ArrayList;
import java.util.HashMap;

public class ClientController extends ApplicationAdapter {

    private static final int MAX_PLAYERS = 4;
    private static int nbPlayers = 0;
    private Player mainPlayer;
    private World world;
    private Client client;

    private boolean connectedToGame = false;

    private TextureRegion bombermanFront = new TextureRegion();
    private TextureRegion bombermanBottom = new TextureRegion();
    private TextureRegion bombermanLeft = new TextureRegion();
    private TextureRegion bombermanRight = new TextureRegion();

    private TextureRegion bombFront;

    //private TextureAtlas bomberman2;
    private Texture bomberman1; // joueur courant
    private Texture bomberman2; // joueurs ennemis

    private Texture creeperTexture;
    private Texture bombTexture;

    private HashMap<String,Player> autresPersonnages;
    private ArrayList<Bomb> bombList;

    private TextureRegion creeperFront = new TextureRegion();
    private TextureRegion creeperBottom = new TextureRegion();
    private TextureRegion creeperLeft = new TextureRegion();
    private TextureRegion creeperRight = new TextureRegion();
    private HashMap<String, Enemy> enemies;

    public ClientController(World world){

        this.world = world;
        /*bomberman1 = new TextureAtlas("core/assets/bomberman.png");
        bomberman2 = new TextureAtlas("core/assets/bomberman.png");
        bomberman1Statique = new Texture(bomberman1).get*/
        bomberman1 = new Texture("bombarab.png");
        bomberman2 = new Texture("bomberman.png");

        creeperTexture = new Texture("creeper.png");

        bombTexture = new Texture("bomb.png");

        /* BomberMan
        bombermanFront = new TextureRegion(bomberman1,1,0,19,31);
        bombermanBottom = new TextureRegion(bomberman1,1,32,19,32);
        bombermanLeft = new TextureRegion(bomberman1,113,32,19,32);
        bombermanRight = new TextureRegion(bomberman1,113,0,19,32);
        */
        // Bombarab
        bombermanFront = new TextureRegion(bomberman1,0,0,31,31);
        bombermanBottom = new TextureRegion(bomberman1,1,96,31,31);
        bombermanLeft = new TextureRegion(bomberman1,0,32,31,31);
        bombermanRight = new TextureRegion(bomberman1,0,64,31,31);

        creeperFront = new TextureRegion(creeperTexture,4,36,32,32);
        creeperBottom = new TextureRegion(creeperTexture,4,4,32,32);
        creeperLeft = new TextureRegion(creeperTexture,4,4,32,32);
        creeperRight = new TextureRegion(creeperTexture,3,0,32,32);

        bombList = new ArrayList<Bomb>();
        autresPersonnages = new HashMap<String, Player>();
        enemies = new HashMap<String, Enemy>();
    }

    public void initiateConnection(Client client){

        if(nbPlayers == (MAX_PLAYERS - 1)){
            System.out.println("We're sorry, already four players are connected to the game ! ");
        }else {
            client.connectToServer();
            ++nbPlayers;
        }

    }

    public void setClient(Client c){
        client = c;
    }
    public void createMainPlayerOnConnection(){
        mainPlayer = new Player(world, new Vector2(356,400),bomberman1,0,0,48,48,3, 100);
        mainPlayer.setRegion(bombermanBottom);
    }

    public void createNewPlayer(String playerId){
        Player autrePerso = new Player(world, new Vector2(356,400),bomberman1,0,0,48,48,3, 100);
        autrePerso.setRegion(bombermanBottom);
        autresPersonnages.put(playerId,autrePerso);
    }

    public void createMonster(String enemyId, float x, float y, Personage.STATE state){
        Enemy enemy = new Enemy(world, new Vector2(x, y), creeperTexture, 0, 0, 54, 54,1,10);
        enemy.setRegion(creeperFront);
        enemies.put(enemyId, enemy);
    }

    public void updateClientServer(Client client){
        client.updateClientToServer(Gdx.graphics.getDeltaTime(), mainPlayer);
    }

    public void playerMoved(String playerId, float x, float y, Personage.STATE state) {
        if (autresPersonnages.get(playerId) != null) {

            autresPersonnages.get(playerId).setPosition(x, y);
            autresPersonnages.get(playerId).getBody().setTransform(x + autresPersonnages.get(playerId).getWidth() / 2, y + autresPersonnages.get(playerId).getHeight() / 2, 0);
            autresPersonnages.get(playerId).updatePlayer();
            autresPersonnages.get(playerId).setState(state); // recupere la position du state
            switch (autresPersonnages.get(playerId).getState()) {
                case LEFT:
                    autresPersonnages.get(playerId).setRegion(bombermanLeft);
                    break;
                case RIGHT:
                    autresPersonnages.get(playerId).setRegion(bombermanRight);
                    break;
                case FRONT:
                    autresPersonnages.get(playerId).setRegion(bombermanFront);
                    break;
                case BOTTOM:
                    autresPersonnages.get(playerId).setRegion(bombermanBottom);
                    break;
                default:
                    System.out.println("COUCOZC SWITCH");
                    break;
            }
        }
    }

    public void createOtherPlayer(String playerId){
        Player ennemi = new Player(world, new Vector2(356,400),bomberman1,0,0,48,48,3, 100);
        autresPersonnages.put(playerId,ennemi);
    }

    public void createBomb(String bombId, float x, float y){
        // TODO change player attribution

        //bombList.add(mainPlayer.dropBomb());
        Bomb bomb = new Bomb(world, new Vector2(x, y), bombTexture, mainPlayer, mainPlayer.getBombRadius());
        //bomb.setRegion(bomb.getTexture());
        bombList.add(bomb);
    }

    public void handleInput(float delta){
        if (mainPlayer != null) {

             if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                 // TODO drop the bomb
                 mainPlayer.getBody().setLinearVelocity(new Vector2(0,0));
                 // bombList.add(mainPlayer.dropBomb());
                 client.DropBombSignal(mainPlayer);

             }
            else if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && mainPlayer.getBody().getLinearVelocity().x >= -3.1f){
                //mainPlayer.getBody().setTransform( mainPlayer.getX(), mainPlayer.getY(),0 );
                //mainPlayer.getBody().setLinearVelocity(new Vector2(-100f, 0));
                mainPlayer.setPosition(mainPlayer.getX() - (delta * 1), mainPlayer.getY());
                mainPlayer.getBody().applyLinearImpulse(new Vector2(-3f, 0), mainPlayer.getBody().getWorldCenter(),true);
                mainPlayer.setRegion(bombermanLeft);
                mainPlayer.setState(Player.STATE.LEFT);
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && mainPlayer.getBody().getLinearVelocity().x <= 3.1f){
                //mainPlayer.getBody().setLinearVelocity(new Vector2(100f, 0));
                mainPlayer.setPosition(mainPlayer.getX() + (delta * 1), mainPlayer.getY());
                mainPlayer.getBody().applyLinearImpulse(new Vector2(3f, 0), mainPlayer.getBody().getWorldCenter(),true);
                mainPlayer.setRegion(bombermanRight);
                mainPlayer.setState(Player.STATE.RIGHT);

            }
            else if(Gdx.input.isKeyPressed(Input.Keys.UP) && mainPlayer.getBody().getLinearVelocity().y <= 3.1f){
                //mainPlayer.getBody().setLinearVelocity(new Vector2(0, 0.1f));
                mainPlayer.getBody().applyLinearImpulse(new Vector2(0, 3f), mainPlayer.getBody().getWorldCenter(),true);
                mainPlayer.setPosition(mainPlayer.getX() , mainPlayer.getY() + (delta * 1));
                mainPlayer.setRegion(bombermanBottom);
                mainPlayer.setState(Player.STATE.BOTTOM);


            }
            else if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && mainPlayer.getBody().getLinearVelocity().y >= -3.1f){
                //mainPlayer.getBody().setLinearVelocity(new Vector2(0, -1f));
                mainPlayer.getBody().applyLinearImpulse(new Vector2(0, -3f), mainPlayer.getBody().getWorldCenter(),true);

                mainPlayer.setPosition(mainPlayer.getX() , mainPlayer.getY() - (delta * 1));
                mainPlayer.setRegion(bombermanFront);
                mainPlayer.setState(Player.STATE.FRONT);
            } else{
                mainPlayer.getBody().setLinearVelocity(new Vector2(0,0));
            }

        }
    }

    public void drawBomberman(SpriteBatch batch){
        if (mainPlayer !=null){
            mainPlayer.updatePlayer();
            mainPlayer.draw(batch); // dessine le bomberman en fonction du bouton appuye par l'utilisateur
        }
    }

    public void drawBomb(SpriteBatch batch){
        if(!bombList.isEmpty()){
            for(int i = 0; i < bombList.size(); ++i) {
                bombList.get(i).draw(batch);
            }
        }
    }

    public void drawOthersBomberman(SpriteBatch batch){
        for(HashMap.Entry<String,Player> autrePersonnage : autresPersonnages.entrySet()){
            autrePersonnage.getValue().updatePlayer();
            //autrePersonnage.getValue().getBody().setTransform(autrePersonnage.getValue().getX(), autrePersonnage.getValue().getY(), 0);
            autrePersonnage.getValue().draw(batch);
            //autrePersonnage.getValue().draw(batch,200);
        }
    }

    public void drawEnnemies(SpriteBatch batch){
        if(!enemies.isEmpty()) {
            for (HashMap.Entry<String, Enemy> enemy : enemies.entrySet()) {
                enemy.getValue().updateEnemy();
                enemy.getValue().draw(batch);
            }
        }
    }

    public void removePlayer(String playerId){
        autresPersonnages.remove(playerId);
    }

    @Override
    public void dispose() {
        super.dispose();
        bomberman1.dispose();
        bomberman2.dispose();
    }
}

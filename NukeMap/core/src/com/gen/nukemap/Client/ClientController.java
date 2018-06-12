package com.gen.nukemap.Client;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.gen.nukemap.GameObject.Enemy;
import com.gen.nukemap.GameObject.Bomb;
import com.gen.nukemap.GameObject.Personage;
import com.gen.nukemap.GameObject.Player;
import com.gen.nukemap.NukeMap;
import com.gen.nukemap.Screens.PlayScreen;
import com.gen.nukemap.Screens.ScoreScreen;

import java.util.ArrayList;
import java.util.HashMap;

public class ClientController extends ApplicationAdapter {

    private static final int MAX_PLAYERS = 4;
    private static int nbPlayersConnected = 0;
    private Player mainPlayer;
    private World world;
    private NukeMap game;
    private PlayScreen playScreen;
    private Client client;

    private boolean isConnected = false;

    public boolean setToScoreScreen = false;

    private TextureRegion bombermanFront = new TextureRegion();
    private TextureRegion bombermanBottom = new TextureRegion();
    private TextureRegion bombermanLeft = new TextureRegion();
    private TextureRegion bombermanRight = new TextureRegion();

    //private TextureAtlas bomberman2;
    private Texture bomberman1; // joueur courant
    private Texture bomberman2; // joueurs ennemis

    private Texture creeperTexture;
    private Texture bombTexture;

    private HashMap<String,Player> otherPlayers;
    private ArrayList<Bomb> bombList;

    private TextureRegion creeperFront = new TextureRegion();
    private TextureRegion creeperBottom = new TextureRegion();
    private TextureRegion creeperLeft = new TextureRegion();
    private TextureRegion creeperRight = new TextureRegion();
    private HashMap<String, Enemy> enemies;



    public ClientController(NukeMap game, World world, PlayScreen playScreen){

        this.game = game;
        this.world = world;
        this.playScreen = playScreen;
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
        otherPlayers = new HashMap<String, Player>();
        enemies = new HashMap<String, Enemy>();
    }

    public void initiateConnection(Client client){


            client.connectToServer();
            client.getNumberOfConnectedPlayers();

            isConnected = true;
    }

    public void updateNbPlayersConnected(int nbPlayersConnected){
        ClientController.nbPlayersConnected = nbPlayersConnected;
    }

    public Player getMainPlayer(){
        return mainPlayer;
    }


    public void setClient(Client c){
        client = c;
    }


    public void createMainPlayerOnConnection(String idPlayer){
        mainPlayer = new Player(idPlayer, world, new Vector2(356,400),bomberman1,0,0,48,48,3, 100);
        mainPlayer.setRegion(bombermanBottom);
        playScreen.getHud().updateBombs(mainPlayer.getMaxBombOnField() - mainPlayer.getBombOnField());
        playScreen.getHud().updateLife(mainPlayer.getLife());
    }

    public void createNewPlayer(String playerId){
        Player autrePerso = new Player(playerId, world, new Vector2(356,400),bomberman1,0,0,48,48,3, 100);
        autrePerso.setRegion(bombermanBottom);
        otherPlayers.put(playerId,autrePerso);
    }

    public void createMonster(String enemyId, float x, float y, Personage.STATE state){
        Enemy enemy = new Enemy(enemyId, world, new Vector2(x, y), creeperTexture, 0, 0, 54, 54,1,10);
        enemy.setRegion(creeperFront);
        enemies.put(enemyId, enemy);
    }

    public void monsterMoves(String monsterId, int direction) {
        Enemy enemy = enemies.get(monsterId);
        Body body = enemy.getBody();

        switch(direction){
            case 0: // UP
                body.applyLinearImpulse(new Vector2(0, 3f), body.getWorldCenter(), true);
                enemy.setRegion(creeperBottom);
                enemy.setState(Enemy.STATE.BOTTOM);
                break;
            case 1: // LEFT
                body.applyLinearImpulse(new Vector2(-3f, 0), body.getWorldCenter(), true);
                enemy.setRegion(creeperLeft);
                enemy.setState(Enemy.STATE.LEFT);
                break;
            case 2: // DOWN
                body.applyLinearImpulse(new Vector2(0, -3f), body.getWorldCenter(), true);
                enemy.setRegion(creeperFront);
                enemy.setState(Enemy.STATE.FRONT);
                break;
            case 3: // RIGHT
                body.applyLinearImpulse(new Vector2(3f, 0), body.getWorldCenter(), true);
                enemy.setRegion(creeperRight);
                enemy.setState(Enemy.STATE.RIGHT);
                break;
            default:
                System.out.println("Error in monster direction");

        }
    }

    public void updateClientServer(Client client){
        client.updateClientToServer(Gdx.graphics.getDeltaTime(), mainPlayer);
    }

    public void playerMoved(final String playerId, final float x, final float y, Personage.STATE state) {
        if (otherPlayers.get(playerId) != null) {

            otherPlayers.get(playerId).setPosition(x, y);
            try {
                Gdx.app.postRunnable(new Runnable() {

                    @Override
                    public void run () {
                        otherPlayers.get(playerId).getBody().setTransform(x + otherPlayers.get(playerId).getWidth() / 2, y + otherPlayers.get(playerId).getHeight() / 2, 0);
                    }
                });


            } catch (Exception e){
                e.printStackTrace();
            }
            otherPlayers.get(playerId).updatePlayer();
            otherPlayers.get(playerId).setState(state); // recupere la position du state
            switch (otherPlayers.get(playerId).getState()) {
                case LEFT:
                    otherPlayers.get(playerId).setRegion(bombermanLeft);
                    break;
                case RIGHT:
                    otherPlayers.get(playerId).setRegion(bombermanRight);
                    break;
                case FRONT:
                    otherPlayers.get(playerId).setRegion(bombermanFront);
                    break;
                case BOTTOM:
                    otherPlayers.get(playerId).setRegion(bombermanBottom);
                    break;
                default:
                    System.out.println("COUCOZC SWITCH");
                    break;
            }
        }
    }

    public void createOtherPlayer(String playerId){
        Player ennemi = new Player(playerId, world, new Vector2(356,400),bomberman1,0,0,48,48,3, 100);
        otherPlayers.put(playerId,ennemi);
    }

    public void exploseBomb(int idBomb){
        for(Bomb bomb : bombList){
            if(bomb.getIdBomb() == idBomb){

                try {
                    for (HashMap.Entry<String, Enemy> enemy : enemies.entrySet()) {
                        if (enemy.getValue().getBody().getPosition().dst(bomb.getPosition()) <= bomb.getRadius()) {
                            // Send onKillScore to server when the mainPlayer killed an enemy with a bomb
                            if(mainPlayer.equals(bomb.getPlayer())) {
                                mainPlayer.getScore().addScore(enemy.getValue().getOnKillScore());
                                playScreen.getHud().updateScore(mainPlayer.getScore().getScoreValue());
                                client.updateScoreSignal(mainPlayer, enemy.getValue().getOnKillScore());
                            }
                            enemy.getValue().destroyBody();
                            enemies.remove(enemy.getKey());
                        }
                    }
                } catch (Exception e){
                    System.out.println("error remove enemy");
                }
                try {
                    for (HashMap.Entry<String, Player> player : otherPlayers.entrySet()) {
                        if (player.getValue().getBody().getPosition().dst(bomb.getPosition()) <= bomb.getRadius()) {
                            if(mainPlayer.equals(bomb.getPlayer())) {
                                mainPlayer.getScore().addScore(player.getValue().getOnKillScore());
                                playScreen.getHud().updateScore(mainPlayer.getScore().getScoreValue());
                                client.updateScoreSignal(mainPlayer, player.getValue().getOnKillScore());
                            }
                            boolean isAlive = player.getValue().decreaseLife();
                            if(!isAlive){
                                Gdx.app.log("DEAD", "player DIED");
                                player.getValue().destroyBody();
                                otherPlayers.remove(player.getValue());
                            }
                            //
                        }
                    }
                } catch (Exception e){
                    System.out.println("error remove player");
                }


                if(mainPlayer.getBody().getPosition().dst(bomb.getPosition()) <= bomb.getRadius()){
                    decreaseMainPlayerLife();
                    //setToScoreScreen = true;
                }

                if(bomb.getPlayer().equals(mainPlayer)){
                    mainPlayer.decreaseBombOnField();
                    playScreen.getHud().updateBombs(mainPlayer.getMaxBombOnField()-mainPlayer.getBombOnField());
                }

                bomb.destroyBricks();
                bomb.destroyBody();
                bombList.remove(bomb);
                break;
            }
        }
    }

    public void createBomb(int bombId, String playerId, float x, float y){
        // TODO change player attribution

        Player playerWhoDropTheBomb = getPlayerFromId(playerId);

        /*for(HashMap.Entry<String,Player> otherPlayer : otherPlayers.entrySet()){
            if(otherPlayer.getValue().getId().equals(playerId)){
                playerWhoDropTheBomb = otherPlayer.getValue();
            }
        }
        if(playerWhoDropTheBomb == null){
            playerWhoDropTheBomb = mainPlayer;
        }*/

        Bomb bomb = new Bomb(bombId, world, new Vector2(x, y), bombTexture, playerWhoDropTheBomb, playerWhoDropTheBomb.getBombRadius());
        //bomb.setRegion(bomb.getTexture());
        if(bomb.getPlayer().equals(mainPlayer)){
            mainPlayer.increaseBombOnField();
            playScreen.getHud().updateBombs(mainPlayer.getMaxBombOnField() - mainPlayer.getBombOnField());
        }
        bombList.add(bomb); //  bomb.destroyBricks();
    }

    public void handleCollision(Fixture fixture){
        if(mainPlayer.getFixture() == fixture){
            decreaseMainPlayerLife();
        }
    }

    public void decreaseMainPlayerLife(){
        boolean isAlive = mainPlayer.decreaseLife();
        if(!isAlive){
            client.PlayerDiedSignal(mainPlayer);
        }
        playScreen.getHud().updateLife(mainPlayer.getLife());
    }

    public void handleInput(float delta){
        if (mainPlayer != null) {

             if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                 mainPlayer.getBody().setLinearVelocity(new Vector2(0,0));
                 // bombList.add(mainPlayer.dropBomb());
                 if(mainPlayer.getBombOnField() < mainPlayer.getMaxBombOnField()){
                      client.DropBombSignal(mainPlayer);
                 }

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
        if(!bombList.isEmpty()) {
            for (int i = 0; i < bombList.size(); ++i) {
                bombList.get(i).draw(batch);
                bombList.get(i).updatePosition();
            }
        }
    }

    public void drawOthersBomberman(SpriteBatch batch){
        for(HashMap.Entry<String,Player> otherPlayer : otherPlayers.entrySet()){
            otherPlayer.getValue().updatePlayer();
            //autrePersonnage.getValue().getBody().setTransform(autrePersonnage.getValue().getX(), autrePersonnage.getValue().getY(), 0);
            otherPlayer.getValue().draw(batch);
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

    public Player getPlayerFromId(String playerId){
        for(HashMap.Entry<String,Player> otherPlayer : otherPlayers.entrySet()){
            if(otherPlayer.getValue().getId().equals(playerId)){
                return otherPlayer.getValue();
            }
        }
        return mainPlayer;
    }

    public void removePlayer(String playerId){
        otherPlayers.remove(playerId);
    }

    public void switchToScoreScreen(){
        ((Game)Gdx.app.getApplicationListener()).setScreen(new ScoreScreen(game.getMenuScreen()));
    }

    public void handleEndOfGame(){
        mainPlayer.destroyBody();
        client.disconnectFromServer();
        setToScoreScreen=true;
    }

    @Override
    public void dispose() {
        super.dispose();
        bomberman1.dispose();
        bomberman2.dispose();
    }
}

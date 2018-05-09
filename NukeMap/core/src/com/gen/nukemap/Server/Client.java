package com.gen.nukemap.Server;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.gen.nukemap.GameObject.Player;
import io.socket.client.*;
import io.socket.emitter.Emitter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Client extends ApplicationAdapter {

    private Socket socket;
    private static final int MAX_PLAYERS = 4;
    private static int nbPlayers = 0;

    private Player mainPlayer;
    private HashMap<String,Player> autresPersonnages;
    private TextureRegion bombermanFront = new TextureRegion();
    private TextureRegion bombermanBottom = new TextureRegion();
    private TextureRegion bombermanLeft = new TextureRegion();
    private TextureRegion bombermanRight = new TextureRegion();

    //private TextureAtlas bomberman2;
    private Texture bomberman1; // joueur courant
    private Texture bomberman2; // joueurs ennemis

    private boolean connectedToGame = false;
    private static final float TIME_TO_UPDATE_CLIENT = 1/60f;
    private float timer;

    public Client(){

    }

    public void connectToServer(){
        try{
            socket= IO.socket("http://localhost:8080");
            if(nbPlayers == (MAX_PLAYERS - 1)){
                System.out.println("We're sorry, already four players are connected to the game ! ");
            }else {
                ++nbPlayers;
                socket.connect();
                connectedToGame = true;
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void handleInput(float delta){
        if (mainPlayer != null) {

            if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                mainPlayer.setPosition(mainPlayer.getX() - (delta * 200), mainPlayer.getY());
                mainPlayer.setRegion(bombermanLeft);
                mainPlayer.setState(Player.STATE.LEFT);
             }
             else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                mainPlayer.setPosition(mainPlayer.getX() + (delta * 200), mainPlayer.getY());
                mainPlayer.setRegion(bombermanRight);
                mainPlayer.setState(Player.STATE.RIGHT);

            }
            else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
                mainPlayer.setPosition(mainPlayer.getX() , mainPlayer.getY() + (delta * 200));
                mainPlayer.setRegion(bombermanBottom);
                mainPlayer.setState(Player.STATE.BOTTOM);


            }
            else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
                mainPlayer.setPosition(mainPlayer.getX() , mainPlayer.getY() - (delta * 200));
                mainPlayer.setRegion(bombermanFront);
                mainPlayer.setState(Player.STATE.FRONT);
            }

        }
    }

    public void updateClientToServer(float delta){
        timer+=delta;
        if(timer >= TIME_TO_UPDATE_CLIENT && mainPlayer !=null && mainPlayer.hasMoved()){
            JSONObject dataToSend = new JSONObject();
            try{
                dataToSend.put("x", mainPlayer.getX());
                dataToSend.put("y", mainPlayer.getY());
                dataToSend.put("state", mainPlayer.getState().name());
                socket.emit("playerMoved",dataToSend);
            } catch(JSONException e ){
                Gdx.app.log("SOCKET.IO","Error sending JSON update data to server");
            }
        }
    }

    public void drawBomberman(SpriteBatch batch){
        if (mainPlayer !=null){
            mainPlayer.draw(batch); // dessine le bomberman en fonction du bouton appuye par l'utilisateur
        }
    }

    public void drawOthersBomberman(SpriteBatch batch){
        for(HashMap.Entry<String,Player> autrePersonnage : autresPersonnages.entrySet()){
            autrePersonnage.getValue().draw(batch);
            //autrePersonnage.getValue().draw(batch,200);
        }
    }

    @Override
    public void create(){
        /*bomberman1 = new TextureAtlas("core/assets/bomberman.png");
        bomberman2 = new TextureAtlas("core/assets/bomberman.png");
        bomberman1Statique = new Texture(bomberman1).get*/
        bomberman1 = new Texture("bombarab.png");
        bomberman2 = new Texture("bomberman.png");

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

        autresPersonnages = new HashMap <String, Player>();
        connectToServer();
        configSocketEvent();
    }

    @Override
    public void dispose() {
        super.dispose();
        bomberman1.dispose();
        bomberman2.dispose();
    }


    public void configSocketEvent(){
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Gdx.app.log("SocketIO","Connected");
                mainPlayer = new Player(new Vector2(0,0),bomberman1,0,0,48,48,3, 100);
            }
        }).on("socketID", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject obj =(JSONObject) args[0];
                try{
                    String id = obj.getString("id");
                    Gdx.app.log("SocketIO","My ID:" + id);
                } catch (JSONException e){
                    Gdx.app.log("SocketIO","Error on JSON object");
                }
            }
        }).on("newPlayer", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject obj =(JSONObject) args[0];
                try{
                    String id = obj.getString("id");
                    Gdx.app.log("SocketIO","New player connected:" + id);
                    Player autrePerso = new Player(new Vector2(0,0),bomberman1,0,0,48,48,3, 100);
                    autrePerso.setRegion(bombermanBottom);
                    autresPersonnages.put(id,autrePerso);
                }catch (JSONException e){
                    Gdx.app.log("SocketIO","Error getting new player ID");

                }
            }

        }).on("playerMoved", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject dataToSend =(JSONObject) args[0];
                try{
                        String playerID = dataToSend.getString("id");

                        Double x = dataToSend.getDouble("x");
                        Double y = dataToSend.getDouble("y");
                        String state = dataToSend.getString("state");

                        if(autresPersonnages.get(playerID) != null){
                            autresPersonnages.get(playerID).setPosition(x.floatValue(),y.floatValue());
                            autresPersonnages.get(playerID).setState(Player.STATE.valueOf(state)); // recupere la position du state
                            switch (autresPersonnages.get(playerID).getState()){
                                case LEFT:
                                    autresPersonnages.get(playerID).setRegion(bombermanLeft);
                                    break;
                                case RIGHT:
                                    autresPersonnages.get(playerID).setRegion(bombermanRight);
                                    break;
                                case FRONT:
                                    autresPersonnages.get(playerID).setRegion(bombermanFront);
                                    break;
                                case BOTTOM:
                                    autresPersonnages.get(playerID).setRegion(bombermanBottom);
                                    break;
                                default:
                                    System.out.println("COUCOZC SWITCH");
                                    break;
                            }

                        }
                }catch (JSONException e){
                    Gdx.app.log("SocketIO","Error getting player moving ID");

                }
            }

        })

                .on("addOtherPlayers", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONArray array =(JSONArray) args[0];
                try{
                    for(int i=0; i < array.length(); i++){
                        Player ennemi = new Player(new Vector2(0,0),bomberman1,0,0,48,48,3, 100);
                        autresPersonnages.put(array.getJSONObject(i).getString("id"),ennemi);
                        Gdx.app.log("New Player : ",array.getJSONObject(i).getString("id"));
                    }
                }catch (JSONException e){
                    Gdx.app.log("SocketIO","Error getting players ID");

                }
            }

        }).on("playerDisconnected", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject obj = (JSONObject) args[0];
                try {
                    String id = obj.getString("id");
                    autresPersonnages.remove(id);
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting disconnected player ID");

                }
            }
        });
    }
}

package com.gen.nukemap.Server;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import io.socket.client.*;
import io.socket.emitter.Emitter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class Client extends ApplicationAdapter {

    private Socket socket;
    private static final int MAX_PLAYERS = 4;
    private static int nbPlayers = 0;

    private Personnage personnage;
    private HashMap<String,Personnage> autresPersonnages;
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
        if (personnage != null) {

            if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                personnage.setPosition(personnage.getX() - (delta * 200),personnage.getY());
                personnage.setRegion(bombermanLeft);
                //personnage.setState(Personnage.STATE.LEFT);
             }
             else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                personnage.setPosition(personnage.getX() + (delta * 200),personnage.getY());
                personnage.setRegion(bombermanRight);
                //personnage.setState(Personnage.STATE.RIGHT);

            }
            else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
                personnage.setPosition(personnage.getX() ,personnage.getY() + (delta * 200));
                personnage.setRegion(bombermanBottom);
                //personnage.setState(Personnage.STATE.BOTTOM);


            }
            else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
                personnage.setPosition(personnage.getX() ,personnage.getY() - (delta * 200));
                personnage.setRegion(bombermanFront);
               // personnage.setState(Personnage.STATE.FRONT);


            }

        }
    }

    public void updateClientToServer(float delta){
        timer+=delta;
        if(timer >= TIME_TO_UPDATE_CLIENT && personnage!=null && personnage.hasMoved()){
            JSONObject dataToSend = new JSONObject();
            try{
                dataToSend.put("x",personnage.getX());
                dataToSend.put("y",personnage.getY());
                //dataToSend.put("state",personnage.getState().ordinal());
                socket.emit("playerMoved",dataToSend);
            } catch(JSONException e ){
                Gdx.app.log("SOCKET.IO","Error sending JSON update data to server");
            }
        }
    }

    public void drawBomberman(SpriteBatch batch){
        if (personnage !=null){
            personnage.draw(batch); // dessine le bomberman en fonction du bouton appuye par l'utilisateur
        }
    }

    public void drawOthersBomberman(SpriteBatch batch){
        for(HashMap.Entry<String,Personnage> autrePersonnage : autresPersonnages.entrySet()){
            autrePersonnage.getValue().draw(batch);
            //autrePersonnage.getValue().draw(batch,200);
        }
    }

    @Override
    public void create(){
        /*bomberman1 = new TextureAtlas("core/assets/bomberman.png");
        bomberman2 = new TextureAtlas("core/assets/bomberman.png");
        bomberman1Statique = new Texture(bomberman1).get*/
        bomberman1 = new Texture("core/assets/bomberman.png");
        bomberman2 = new Texture("core/assets/bomberman.png");

        bombermanFront = new TextureRegion(bomberman1,1,0,19,31);
        bombermanBottom = new TextureRegion(bomberman1,1,32,19,32);
        bombermanLeft = new TextureRegion(bomberman1,113,32,19,32);
        bombermanRight = new TextureRegion(bomberman1,113,0,19,32);
        autresPersonnages = new HashMap <String, Personnage>();
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
                personnage = new Personnage(bomberman1);
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
                    Personnage autrePerso = (new Personnage(bomberman2));
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
                        //Integer i = dataToSend.getInt("state");

                        if(autresPersonnages.get(playerID) != null){
                            autresPersonnages.get(playerID).setPosition(x.floatValue(),y.floatValue());
                            //autresPersonnages.get(playerID).setState(Personnage.getStates()[i]); // recupere la position du state
                        }
                }catch (JSONException e){
                    Gdx.app.log("SocketIO","Error getting player moving ID");

                }
            }

        })

                .on("getPlayers", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONArray array =(JSONArray) args[0];
                try{
                    for(int i=0; i < array.length(); i++){
                        Personnage ennemi = new Personnage(bomberman2);
                        Vector2 position = new Vector2();
                        position.x = ((Double) array.getJSONObject(i).getDouble("x")).floatValue();
                        position.y = ((Double) array.getJSONObject(i).getDouble("y")).floatValue();

                        //Personnage.STATE state = Personnage.getStates()[array.getJSONObject(i).getInt("state")];
                        ennemi.setPosition(position.x,position.y);


                        /*switch (state){
                            case LEFT:
                                ennemi.setRegion(bombermanLeft);
                                break;
                            case RIGHT:
                                ennemi.setRegion(bombermanRight);

                                break;
                            case FRONT:
                                ennemi.setRegion(bombermanBottom);

                                break;
                            case BOTTOM:
                                ennemi.setRegion(bombermanFront);

                                break;
                            default:
                                break;
                        }*/

                        //Json contient les boutons appuyes par les ennemis et les envoie te vsuite gere



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

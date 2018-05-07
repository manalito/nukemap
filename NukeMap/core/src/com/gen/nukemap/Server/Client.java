package com.gen.nukemap.Server;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import io.socket.client.*;
import io.socket.emitter.Emitter;
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
    //private TextureAtlas bomberman1;
    //private TextureAtlas bomberman2;
    private Texture bomberman1;
    private Texture bomberman2;

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
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void handleInput(float delta){
        if (personnage != null) {

            if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                personnage.setPosition(personnage.getX() - (delta * 200),personnage.getY());
             }
             else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                personnage.setPosition(personnage.getX() + (delta * 200),personnage.getY());
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
                personnage.setPosition(personnage.getX() ,personnage.getY() + (delta * 200));

            }
            else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
                personnage.setPosition(personnage.getX() ,personnage.getY() - (delta * 200));

            }

        }
    }

    public void drawBomberman(SpriteBatch batch){
        if (personnage !=null){
            personnage.draw(batch);
        }
    }

    public void drawOthersBomberman(SpriteBatch batch){
        for(HashMap.Entry<String,Personnage> autrePersonnage : autresPersonnages.entrySet()){
            autrePersonnage.getValue().draw(batch);
        }
    }

    @Override
    public void create(){
        /*bomberman1 = new TextureAtlas("core/assets/bomberman.png");
        bomberman2 = new TextureAtlas("core/assets/bomberman.png");
        bomberman1Statique = new Texture(bomberman1).get*/
        bomberman1 = new Texture("core/assets/bomberman.png");
        bomberman2 = new Texture("core/assets/bomberman.png");
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
                    autresPersonnages.put(id,new Personnage(bomberman2));
                }catch (JSONException e){
                    Gdx.app.log("SocketIO","Error getting new player ID");

                }
            }
        });
    }
}

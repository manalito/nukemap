package com.gen.nukemap.Client;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.gen.nukemap.GameObject.Enemy;
import com.gen.nukemap.GameObject.Personage;
import com.gen.nukemap.GameObject.Player;
import io.socket.client.*;
import io.socket.emitter.Emitter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Client extends ApplicationAdapter {

    private ClientController clientController;
    private Socket socket;

    private static final float TIME_TO_UPDATE_CLIENT = 1 / 60f;
    private float timer;

    public Client(ClientController clientController) {

        this.clientController = clientController;
    }


    public void updateClientToServer(float delta, Player mainPlayer) {
        timer += delta;
        if (timer >= TIME_TO_UPDATE_CLIENT && mainPlayer != null && mainPlayer.hasMoved()) {
            JSONObject dataToSend = new JSONObject();
            try {
                dataToSend.put("x", mainPlayer.getX());
                dataToSend.put("y", mainPlayer.getY());
                dataToSend.put("state", mainPlayer.getState().name());
                socket.emit("playerMoved", dataToSend);
            } catch (JSONException e) {
                Gdx.app.log("SOCKET.IO", "Error sending JSON update data to server");
            }
        }
    }


    public void connectToServer() {
        try {
            socket = IO.socket("http://localhost:8080");
            socket.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        configSocketEvent();
    }

    public void DropBombSignal(Player player){
        JSONObject dataToSend = new JSONObject();
        try {
            dataToSend.put("x", player.getX());
            dataToSend.put("y", player.getY());
            socket.emit("bombDropped", dataToSend);
        } catch (JSONException e) {
            Gdx.app.log("SOCKET.IO", "Error sending JSON update Bomb to server");
        }
    }


    public void configSocketEvent() {
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Gdx.app.log("SocketIO", "Connected");
                clientController.createMainPlayerOnConnection();

            }
        });
                socket.on("socketID", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject obj = (JSONObject) args[0];
                try {
                    String id = obj.getString("id");
                    Gdx.app.log("SocketIO", "My ID:" + id);
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error on JSON object");
                }
            }
        });
                socket.on("newPlayer", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject obj = (JSONObject) args[0];
                try {
                    String id = obj.getString("id");
                    Gdx.app.log("SocketIO", "New player connected:" + id);
                    clientController.createNewPlayer(id);
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting new player ID");

                }
            }

        });
                socket.on("playerMoved", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject dataToSend = (JSONObject) args[0];
                try {
                    String playerID = dataToSend.getString("id");
                    Double x = dataToSend.getDouble("x");
                    Double y = dataToSend.getDouble("y");
                    String state = dataToSend.getString("state");

                    System.out.println(playerID + x + " " + state);
                    clientController.playerMoved(playerID, x.floatValue(), y.floatValue(), Personage.STATE.valueOf(state));

                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting player moving ID");

                }
            }

        });

                socket.on("addOtherPlayers", new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        JSONArray array = (JSONArray) args[0];
                        try {
                            for (int i = 0; i < array.length(); i++) {
                                String playerId = array.getJSONObject(i).getString("id");
                                clientController.createOtherPlayer(playerId);

                                Gdx.app.log("New Player : ", playerId);
                            }
                        } catch (Exception e) {
                            Gdx.app.log("SocketIO", "Error getting players ID");

                        }
                    }

                });
                socket.on("playerDisconnected", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject obj = (JSONObject) args[0];
                try {
                    String id = obj.getString("id");
                    clientController.removePlayer(id);

                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting disconnected player ID");

                }
            }
        });
                socket.on("addMonsters", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject obj =(JSONObject) args[0];
                try{
                    String idMonster = obj.getString("id");
                    Double x = obj.getDouble("x");
                    Double y = obj.getDouble("y");
                    String state = obj.getString("state");
                    Gdx.app.log("SocketIO","Monsters add: " + idMonster);
                    Gdx.app.log("Monster",idMonster + x + y);

                    clientController.createMonster(idMonster, x.floatValue(), y.floatValue(), Personage.STATE.valueOf(state));
                }catch (JSONException e){
                    Gdx.app.log("SocketIO","Error getting monsters ID");

                }
            }

        });

        socket.on("bombok", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject obj =(JSONObject) args[0];
                try{
                    String idBomb = obj.getString("id");
                    Double x = obj.getDouble("x");
                    Double y = obj.getDouble("y");
                    Gdx.app.log("SocketIO","Bomb dropped: " + idBomb);
                    Gdx.app.log("Bomb",idBomb + x + y);

                    clientController.createBomb(idBomb, x.floatValue(), y.floatValue());
                }catch (JSONException e){
                    Gdx.app.log("SocketIO","Error getting monsters ID");

                }
            }

        });





    }
}

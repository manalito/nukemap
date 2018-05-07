package com.gen.nukemap.Protocol.Serialization;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.gen.nukemap.GameObject.*;

import java.util.ArrayList;
import java.util.List;

public class ServerMessage implements Json.Serializable {
  
  // map needed...
  
  private List<Bomb> bombs = new ArrayList<Bomb>();
  
  private List<Breackable> breackables = new ArrayList<Breackable>();
  
  private List<PowerUp> powerUps = new ArrayList<PowerUp>();
  
  private List<Ennemy> ennemies = new ArrayList<Ennemy>();
  
  private List<Player> players = new ArrayList<Player>();
  
  private Player mainPlayer = new Player();
  
  @Override
  public void write(Json json) {
    json.writeObjectStart();
    json.writeValue(bombs);
    json.writeValue(breackables);
    json.writeValue(powerUps);
    json.writeValue(ennemies);
    json.writeValue(players);
    json.writeValue(mainPlayer);
    json.writeObjectEnd();
  
  }
  
  @Override
  public void read(Json json, JsonValue jsonData) {
  
  }
}

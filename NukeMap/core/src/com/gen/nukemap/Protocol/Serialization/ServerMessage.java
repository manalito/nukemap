package com.gen.nukemap.Protocol.Serialization;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.gen.nukemap.GameObject.*;

import java.util.ArrayList;
import java.util.List;

public class ServerMessage {
  
  public ServerMessage() {
  }
  
  public ServerMessage(List<Bomb> bombs, List<Breakable> breakables, List<PowerUp> powerUps, List<Enemy> enemies, List<Player> players, Player mainPlayer) {
    setBombs(bombs);
    setBreakables(breakables);
    setEnemies(enemies);
    setPlayers(players);
    setPowerUps(powerUps);
    setMainPlayer(mainPlayer);
  }
  
  public List<Bomb> getBombs() {
    List<Bomb> list = new ArrayList<Bomb>();
    list.addAll(bombs);
    return list;
  }
  
  public void setBombs(List<Bomb> bombs) {
    this.bombs.clear();
    bombs.addAll(bombs);
  }
  
  public List<Breakable> getBreakables() {
    List<Breakable> list = new ArrayList<Breakable>();
    list.addAll(breakables);
    return list;
  }
  
  public void setBreakables(List<Breakable> breakables) {
    this.breakables.clear();
    this.breakables.addAll(breakables);
  }
  
  public List<PowerUp> getPowerUps() {
    List<PowerUp> list = new ArrayList<PowerUp>();
    list.addAll(powerUps);
    return list;
  }
  
  public void setPowerUps(List<PowerUp> powerUps) {
    this.powerUps.clear();
    this.powerUps.addAll(powerUps);
  }
  
  public List<Enemy> getEnemies() {
    List<Enemy> list = new ArrayList<Enemy>();
    list.addAll(enemies);
    return list;
  }
  
  public void setEnemies(List<Enemy> enemies) {
    this.enemies.clear();
    this.enemies.addAll(enemies);
  }
  
  public List<Player> getPlayers() {
    List<Player> list = new ArrayList<Player>();
    list.addAll(players);
    return list;
  }
  
  public void setPlayers(List<Player> players) {
    this.players.clear();
    this.players.addAll(players);
  }
  
  public Player getMainPlayer() {
    Player p = new Player(mainPlayer);
    return p;
  }
  
  public void setMainPlayer(Player mainPlayer) {
    this.mainPlayer.setPlayer(mainPlayer);
  }
  
  // map needed...
  private List<Bomb> bombs = new ArrayList<Bomb>();
  private List<Breakable> breakables = new ArrayList<Breakable>();
  private List<PowerUp> powerUps = new ArrayList<PowerUp>();
  private List<Enemy> enemies = new ArrayList<Enemy>();
  private List<Player> players = new ArrayList<Player>();
  private Player mainPlayer = new Player();
}

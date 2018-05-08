package com.gen.nukemap.GameObject;

public class Player extends Personage {
  
  public Player() {
    super();
  }
  
  public Player(int positionX, int positionY) {
    super(positionX, positionY);
    
  }
  
  public Player(Player player) {
    setPlayer(player);
  }
  
  public void setPlayer(Player player) {
    this.setPositionX(player.getPositionX());
    this.setPositionY(player.getPositionY());
    this.bomb = new Bomb(player.bomb);
    this.score = new Score(score);
  }
  
  public void dropBomb() {
  }
  
  private Bomb bomb;
  private Score score;
  
}

package com.gen.nukemap.GameObject;

public class Personage extends GameObject {
  public Personage(){
    super();
    life = 3;
    onKillScore = 100;
  }
  public Personage(int positionX, int positionY){
    super(positionX, positionY);
  }
  
  public int getLife() {
    return life;
  }
  
  public void setLife(int life) {
    this.life = life;
  }
  
  public int getOnKillScore() {
    return onKillScore;
  }
  
  public void setOnKillScore(int onKillScore) {
    this.onKillScore = onKillScore;
  }
  
  public void deplacement(){
  
  }
  public void die(){
  
  }
  
  private int life;
  private int onKillScore;
}

package com.gen.nukemap.GameObject;

public class GameObject {
  
  public GameObject() {
    positionX = 0;
    positionY = 0;
  }
  
  public GameObject(int positionX, int positionY) {
    this.positionX = positionX;
    this.positionY = positionY;
  }
  
  public int getPositionX() {
    return positionX;
  }
  
  public void setPositionX(int positionX) {
    this.positionX = positionX;
  }
  
  public int getPositionY() {
    return positionY;
  }
  
  public void setPositionY(int positionY) {
    this.positionY = positionY;
  }
  
  private int positionX;
  private int positionY;
}

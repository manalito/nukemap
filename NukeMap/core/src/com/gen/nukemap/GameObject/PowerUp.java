package com.gen.nukemap.GameObject;

public class PowerUp extends GameObject{
  public PowerUp(){
    super();
  }
  public PowerUp(int positionX, int positionY, int amountBonus, int radiusBonus){
    super(positionX, positionY);
    this.amountBonus = amountBonus;
    this.radiusBonus = radiusBonus;
    
  }
  private int radiusBonus;
  private int amountBonus;
  
  public int getRadiusBonus() {
    return radiusBonus;
  }
  
  public void setRadiusBonus(int radiusBonus) {
    this.radiusBonus = radiusBonus;
  }
  
  public int getAmountBonus() {
    return amountBonus;
  }
  
  public void setAmountBonus(int amountBonus) {
    this.amountBonus = amountBonus;
  }
  
}

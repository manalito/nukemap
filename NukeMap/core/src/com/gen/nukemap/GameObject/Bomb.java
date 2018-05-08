package com.gen.nukemap.GameObject;

public class Bomb extends GameObject {
  public Bomb(){
    super();
  }
  public Bomb(int positionX, int positionY, int radius, int amount){
    super(positionX, positionY);
    this.radius = radius;
    this.amount = amount;
  }
  public Bomb(Bomb bomb){
    super(bomb.getPositionX(), bomb.getPositionY());
    this.amount = bomb.amount;
    this.radius = bomb.radius;
    this.maxAmount = bomb.maxAmount;
    this.maxRadius = bomb.maxRadius;
  }
  public int getRadius() {
    return radius;
  }
  
  public void setRadius(int radius) {
    this.radius = radius;
  }
  
  public int getAmount() {
    return amount;
  }
  
  public void setAmount(int amount) {
    this.amount = amount;
  }
  
  public int getMaxAmount() {
    return maxAmount;
  }
  
  public void setMaxAmount(int maxAmount) {
    this.maxAmount = maxAmount;
  }
  
  public int getMaxRadius() {
    return maxRadius;
  }
  
  public void setMaxRadius(int maxRadius) {
    this.maxRadius = maxRadius;
  }
  
  public void addPowerUp(PowerUp powerUp){
    if(this.amount + powerUp.getAmountBonus() < maxAmount){
      this.amount += powerUp.getAmountBonus();
    }
    if(this.radius + powerUp.getRadiusBonus() < maxRadius) {
      this.radius += powerUp.getRadiusBonus();
    }
  }
  public void explode(){
  
  }
  private int radius;
  private int amount;
  private int maxAmount;
  private int maxRadius;
  
  
}

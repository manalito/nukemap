package com.gen.nukemap.GameObject;

public class Score {
  public Score(){
  
  }
  public Score(String username, int score){
    this.username = username;
    this.score = score;
  }
  public Score(int score){
    this.username = "";
    this.score = score;
  }
  private String username;
  private int score;
  
  public Score(Score score) {
    this.score = score.score;
    this.username = score.username;
  }
  
  public String getUsername() {
    return username;
  }
  
  public void setUsername(String username) {
    this.username = username;
  }
  
  public int getScoreValue() {
    return score;
  }
  
  public void setScore(int score) {
    this.score = score;
  }
  
  public void addScore(int score){
    this.score += score;
  }
}

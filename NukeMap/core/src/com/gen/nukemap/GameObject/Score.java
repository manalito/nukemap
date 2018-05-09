package com.gen.nukemap.GameObject;

public class Score {
  public Score(){
  
  }
  public Score(String username, long score){
    this.username = username;
    this.score = score;
  }
  private String username;
  private long score;
  
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
  
  public long getScore() {
    return score;
  }
  
  public void setScore(long score) {
    this.score = score;
  }
  
  public void addScore(long score){
    this.score += score;
  }
}

package com.gen.nukemap.Protocol.Serialization;

import com.gen.nukemap.GameObject.Score;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardMessage {

  public LeaderboardMessage(){
  }
  public LeaderboardMessage(List<Score> scoreList){
    setScoreList(scoreList);
  }
  
  private List<Score> scoreList = new ArrayList<Score>();
  
  public List<Score> getScoreList() {
    List<Score> scoreList = new ArrayList<Score>();
    scoreList.addAll(scoreList);
    return scoreList;
  }
  
  public void setScoreList(List<Score> scoreList) {
    this.scoreList.clear();
    this.scoreList.addAll(scoreList);
  }
}

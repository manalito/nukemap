package com.gen.nukemap.Protocol.Serialization;


public class ClientMessage {
  public ClientMessage() {
  }
  
  public ClientMessage(String action) {
    this.action = action;
  }
  
  public String getAction() {
    return action;
  }
  
  public void setAction(String action) {
    this.action = action;
  }
  
  private String action;
  
  
}

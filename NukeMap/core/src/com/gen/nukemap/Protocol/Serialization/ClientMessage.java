package com.gen.nukemap.Protocol.Serialization;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class ClientMessage implements Json.Serializable {
  private String action;
  
  @Override
  public void write(Json json) {
    json.writeValue("Action", action);
  }
  
  @Override
  public void read(Json json, JsonValue jsonData) {
    action = jsonData.child().asString();
  }
}

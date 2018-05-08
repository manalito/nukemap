package com.gen.nukemap.Server;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Personnage extends Sprite {

    Vector2 previousPosition;
    Vector2 actualPosition;

    public Personnage(Texture texture){
        super(texture);
        previousPosition= new Vector2(getX(),getY());
    }

    public boolean hasMoved(){
        if(previousPosition.x !=getX() || previousPosition.y !=getY() ){
            previousPosition.x = getX();
            previousPosition.y = getY();
            return true;
        }
        return false;
    }
}

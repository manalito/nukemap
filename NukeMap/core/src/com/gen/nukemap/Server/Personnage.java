package com.gen.nukemap.Server;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Personnage extends Sprite {

    private Vector2 previousPosition;
    private Vector2 actualPosition;

    public enum STATE {FRONT, BOTTOM, LEFT, RIGHT}

    private STATE state;

    public static STATE [] getStates(){
        return STATE.values();
    }

    public Personnage(Texture texture){

        super(texture);
        setBounds(0,0,30,48);
        previousPosition= new Vector2(getX(),getY());
        state = STATE.BOTTOM;
    }

    public boolean hasMoved(){
        if(previousPosition.x !=getX() || previousPosition.y !=getY() ){
            previousPosition.x = getX();
            previousPosition.y = getY();
            return true;
        }
        return false;
    }

    public STATE getState(){
        return state;
    }

    public void setState(STATE newState){
        state = newState;
    }


}

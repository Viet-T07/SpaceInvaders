/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvader;

/**
 *
 * @author cstuser
 */
public class Enemy2 extends GameObject {
    
    public Enemy2(Vector2D position) {
        super(position, new Vector2D(0.0,0.0), new Vector2D(0.0,0.0), 15);
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }
    
}

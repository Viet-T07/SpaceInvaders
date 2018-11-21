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
public class Enemy extends GameObject{
    public Enemy(Vector2D position) {
        super(position, new Vector2D(0.0,0.0), new Vector2D(0.0,0.0), 15);
    }

    public Projectile shoot(Vector2D position) {
      return new Projectile(position,new Vector2D(0.0,75));
    }    
}

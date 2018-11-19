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
public class Projectile extends GameObject {
    
    public Projectile(Vector2D position, Vector2D velocity, double radius) {
        super(position, velocity, new Vector2D(0.0,0.0), radius);
    }
    
}

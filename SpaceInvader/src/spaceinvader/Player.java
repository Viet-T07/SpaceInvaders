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
public class Player extends Shooter {

    public Player(double x, double y, double radius) {
      super(x, y, radius);
    }

    public Projectile shoot(Vector2D position) {
      super.shoot(position, new Vector2D(0.0, 75.0));
    }

}

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
public class Player extends GameObject {

    protected int lives;

    public Player(Vector2D position) {
        super(position, new Vector2D(0.0, 0.0), new Vector2D(0.0, 0.0), 25);
        lives = 3;
    }

    public Projectile shoot(Vector2D position) {
      return new Projectile(position, new Vector2D(0.0, -700));
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
    
    public void addDamage() {
      lives--;
    }

    public Integer getLives() {
      return lives;
    }


}

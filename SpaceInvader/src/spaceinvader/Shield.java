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
public class Shield extends GameObject {

    protected int damage;

    public Shield(Vector2D position) {
        super(position, new Vector2D(0.0,0.0), new Vector2D(0.0,0.0), 50);
        damage = 0;
    }



    public void addDamage() {
      damage++;
    }

    public Integer getDamage() {
      return damage;
    }


}

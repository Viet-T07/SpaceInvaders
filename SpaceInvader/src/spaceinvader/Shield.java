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
public class Shield extends Circle {

    protected int damage;

    public Shield(double x) {
        super(x, 20.0, 30);
        damage = 0;
    }

    public void addDamage() {
      damage++;
    }

    public Integer getDamage() {
      return damage;
    }


}

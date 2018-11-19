/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvader;

import javafx.scene.shape.Circle;

/**
 *
 * @author cstuser
 */
public class GameObject {

    protected Circle circle;
    protected Vector2D position;
    protected Vector2D velocity;
    protected Vector2D acceleration;

    public GameObject(Vector2D position, Vector2D velocity, Vector2D acceleration, double radius) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;

        circle = new Circle(0.0, 0.0, radius);
        circle.setCenterX(position.getX());
        circle.setCenterY(position.getY());
    }

    public Circle getCircle() {
        return circle;
    }

    public void update(double dt) {
        // Euler Integration
        // Update velocity
        Vector2D frameAcceleration = acceleration.mult(dt);
        velocity = velocity.add(frameAcceleration);

        // Update position
        position = position.add(velocity.mult(dt));
        circle.setCenterX(position.getX());
        circle.setCenterY(position.getY());

    }

    public Vector2D getPosition() {
        return position;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public Vector2D getAcceleration() {
        return acceleration;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvader;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author cstuser
 */
public class FXMLDocumentController implements Initializable {
    
    private ArrayList<Enemy> enemyList = new ArrayList<>();
    private ArrayList<GameObject> objectList = new ArrayList<>();
    private Player ship = new Player(new Vector2D(15,600), new Vector2D(0.0,0.0));
    private double lastFrameTime = 0.0;
    
    @FXML
    AnchorPane pane;
    
    @FXML
    private void onMouseClicked(MouseEvent e) {
        final float PROJECTILE_SPEED = 700;
        Vector2D position = ship.getPosition();
        Vector2D velocity = new Vector2D(0.0,-PROJECTILE_SPEED);
        Projectile projectile = new Projectile(position,velocity);
        addToPane(projectile.getCircle());
        objectList.add(projectile);
    }
    
    @FXML
    private void onMouseMoved(MouseEvent e){
        ship.setPosition(new Vector2D(e.getX(),585));
        objectList.add(ship);
    }
    
    public void addToPane(Node node){
        pane.getChildren().add(node);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lastFrameTime = 0.0f;
        long initialTime = System.nanoTime();
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                enemyList.add(new Enemy(new Vector2D(225 + j * 60, 50 + i*50)));
            }
        }
    

        enemyList.forEach((x) -> {
            addToPane(x.getCircle());
        });

        addToPane(ship.getCircle());
        
        
        new AnimationTimer(){
            @Override
            public void handle(long now) {
                double currentTime = (now - initialTime)/1000000000.0;
                double frameDeltaTime = currentTime - lastFrameTime;
                lastFrameTime = currentTime;
                
                for(GameObject obj: objectList){
                    obj.update(frameDeltaTime);
                }
                
                
            }
        }.start();
    }    
    
}

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
    private Player ship = new Player(new Vector2D(15, 600), new Vector2D(0.0, 0.0));
    private double lastFrameTime = 0.0;
    private double xOffset = 50;
    private double yOffset = 0;
    
    
    @FXML
    AnchorPane pane;

    @FXML
    private void onMouseClicked(MouseEvent e) {
        final float PROJECTILE_SPEED = 700;
        Vector2D position = ship.getPosition();
        Vector2D velocity = new Vector2D(0.0, -PROJECTILE_SPEED);
        Projectile projectile = new Projectile(position, velocity);
        projectile.getCircle().setFill(AssetManager.getProjectileImage());
        addToPane(projectile.getCircle());
        objectList.add(projectile);
    }

    @FXML
    private void onMouseMoved(MouseEvent e) {
        ship.setPosition(new Vector2D(e.getX(), 575));
        objectList.add(ship);
    }

    public void addToPane(Node node) {
        pane.getChildren().add(node);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lastFrameTime = 0.0f;
        long initialTime = System.nanoTime();

        AssetManager.preloadAllAssets();
        pane.setBackground(AssetManager.getBackgroundImage());

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                Enemy enemy = new Enemy(new Vector2D(215 + j * 60, 50 + i * 50));
                if (i == 0 || i == 1) {
                    enemy.getCircle().setFill(AssetManager.getAliens(0));
                }
                if (i == 2) {
                    enemy.getCircle().setFill(AssetManager.getAliens(1));
                }
                if (i == 3) {
                    enemy.getCircle().setFill(AssetManager.getAliens(2));
                }
                enemyList.add(enemy);
            }
        }

        enemyList.forEach((x) -> {
            addToPane(x.getCircle());
        });

        ship.getCircle().setFill(AssetManager.getShipImage());
        addToPane(ship.getCircle());
        System.out.println(pane.getPrefWidth()-25);
        
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                double currentTime = (now - initialTime) / 1000000000.0;
                double frameDeltaTime = currentTime - lastFrameTime;
                lastFrameTime = currentTime;
                
              
                
                if (enemyList.get(7).getPosition().getX() >= pane.getPrefWidth()- 25.0) {
                    if(yOffset == 0){
                        xOffset = 0;
                        yOffset = 20;
                    }
                    else{
                        xOffset = -50;
                        yOffset = 0;
                    }
                }
                if(enemyList.get(0).getPosition().getX() <= 25){
                    if(yOffset == 0){
                        xOffset = 0;
                        yOffset = 20;
                    }
                    else{
                        xOffset = 50;
                        yOffset = 0;
                    }
                }
                
            

                for (GameObject obj : objectList) {
                    obj.update(frameDeltaTime);
                }
                for (GameObject ene : enemyList) {
                    ene.setPosition(new Vector2D(ene.getPosition().getX() + frameDeltaTime * xOffset, ene.getPosition().getY() + yOffset));

                    ene.update(frameDeltaTime);
                }

        

            }
        }.start();
    }

}

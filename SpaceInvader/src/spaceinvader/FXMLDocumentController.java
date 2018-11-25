/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvader;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;

/**
 *
 * @author cstuser
 */
public class FXMLDocumentController implements Initializable {

    private ArrayList<Enemy> enemyList = new ArrayList<>();
    private ArrayList<GameObject> objectList = new ArrayList<>();
    private ArrayList<Projectile> alienProjectileList = new ArrayList();
    private ArrayList<Shield> shieldList = new ArrayList<>();
    private ArrayList<Player> playerLives = new ArrayList<>();
    private Player ship = new Player(new Vector2D(15, 600));
    private double lastFrameTime = 0.0;
    private double xOffset = 50;
    private double yOffset = 0;
    private int score = 0;
    ScheduledExecutorService projectileExecutor = null;

    @FXML
    AnchorPane pane;

    @FXML
    Label winLabel;

    @FXML
    Label loseLabel;

    @FXML
    Label scoreLabel;

    @FXML
    Label livesLabel;

    @FXML
    private void onMouseClicked(MouseEvent e) {
        Projectile projectile = ship.shoot(ship.getPosition());
        projectile.getCircle().setFill(AssetManager.getProjectileImage());
        addToPane(projectile.getCircle());
        objectList.add(projectile);

        //Start Sound
        AudioClip sound = AssetManager.getShootingSound();
        sound.play();
    }

    @FXML
    private void onMouseMoved(MouseEvent e) {
        ship.setPosition(new Vector2D(e.getX(), 575));
    }

    public void addToPane(Node node) {
        pane.getChildren().add(node);
    }

    public void removeFromPane(Node node) {
        pane.getChildren().remove(node);
    }

    public void shutdown(){
        if(projectileExecutor != null){
            projectileExecutor.shutdown();
        }
        Platform.exit();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scoreLabel.setText(Integer.toString(score));
        livesLabel.setText(Integer.toString(ship.getLives()));
        lastFrameTime = 0.0f;
        long initialTime = System.nanoTime();

        AssetManager.preloadAllAssets();
        pane.setBackground(AssetManager.getBackgroundImage());

        //Start Background music
        Media sound = AssetManager.getBackgroundMusic();
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

        //Display shields on the pane
        for (int i = 0; i < 3; i++) {
            Shield shield = new Shield(new Vector2D(180 + i * 270, 475));
            shield.getCircle().setFill(AssetManager.getShieldImage());
            shieldList.add(shield);
            addToPane(shield.getCircle());
        }

        //Display enemies on the pane
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                Enemy enemy = new Enemy(new Vector2D(215 + j * 60, 75 + i * 50));
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
        
        projectileExecutor = Executors.newSingleThreadScheduledExecutor();
        projectileExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        int randomEnemy = (int) (Math.random() * enemyList.size());
                        Projectile projectile = enemyList.get(randomEnemy).shoot(enemyList.get(randomEnemy).getPosition());
                        projectile.getCircle().setFill(AssetManager.getAlienProjectile());
                        alienProjectileList.add(projectile);
                        addToPane(projectile.getCircle());
                    }
                });
            }
        }, 2, 2, TimeUnit.SECONDS);

        alienProjectileList.forEach((Projectile x) -> {
            addToPane(x.getCircle());
        });

        //Display the number of lives
        for (int i = 0; i < ship.getLives() - 1; i++) {
            Player shipLives = new Player(new Vector2D(88 + i * 55, 669));
            shipLives.getCircle().setFill(AssetManager.getShipImage());
            playerLives.add(shipLives);
            addToPane(shipLives.getCircle());
        }

        //Add enemies to the pane
        enemyList.forEach((x) -> {
            addToPane(x.getCircle());
        });

        ship.getCircle().setFill(AssetManager.getShipImage());
        addToPane(ship.getCircle());

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                double currentTime = (now - initialTime) / 1000000000.0;
                double frameDeltaTime = currentTime - lastFrameTime;
                lastFrameTime = currentTime;

                //Verify the bounds 
                for (GameObject edgeCheck : enemyList) {
                    if (edgeCheck.getPosition().getX() >= pane.getPrefWidth() - 25.0) {
                        if (yOffset == 0) {
                            xOffset = 0;
                            yOffset = 20;
                        } else {
                            xOffset = -50;
                            yOffset = 0;
                        }
                        break;
                    }
                    if (edgeCheck.getPosition().getX() <= 25) {
                        if (yOffset == 0) {
                            xOffset = 0;
                            yOffset = 20;
                        } else {
                            xOffset = 50;
                            yOffset = 0;
                        }
                        break;
                    }
                }

                //Update objects
                ship.update(frameDeltaTime);

                for (GameObject projectile : alienProjectileList) {
                    projectile.update(frameDeltaTime);
                }

                for (GameObject obj : objectList) {
                    obj.update(frameDeltaTime);
                }
                for (GameObject ene : enemyList) {
                    Circle circle2 = ene.getCircle();
                    Circle shipCircle = ship.getCircle();

                    //Verify Collison between Ship and aliens
                    Vector2D ship = new Vector2D(shipCircle.getCenterX(), shipCircle.getCenterY());
                    Vector2D c2 = new Vector2D(circle2.getCenterX(), circle2.getCenterY());
                    Vector2D z = c2.sub(ship);
                    double shipDistance = z.magnitude();
                    if (shipDistance < shipCircle.getRadius() + circle2.getRadius()) {
                        loseLabel.setVisible(true);
                        mediaPlayer.stop();
                        AudioClip lost = AssetManager.getLoseSound();
                        lost.play();
                        this.stop();
                    }

                    ene.setPosition(new Vector2D(ene.getPosition().getX() + frameDeltaTime * xOffset, ene.getPosition().getY() + yOffset));
                    ene.update(frameDeltaTime);
                }

                //Verify Collision between aliens and the projectile
                for (int i = 0; i < objectList.size(); i++) {
                    for (int j = 0; j < enemyList.size(); j++) {
                        if (!objectList.isEmpty() && !enemyList.isEmpty()) {
                            Circle circle1 = objectList.get(i).getCircle();
                            Circle circle2 = enemyList.get(j).getCircle();
                            Vector2D c1 = new Vector2D(circle1.getCenterX(), circle1.getCenterY());
                            Vector2D c2 = new Vector2D(circle2.getCenterX(), circle2.getCenterY());
                            Vector2D n = c2.sub(c1);
                            double distance = n.magnitude();
                            if (distance < circle1.getRadius() + circle2.getRadius()) {
                                removeFromPane(circle1);
                                objectList.remove(i);
                                removeFromPane(circle2);
                                enemyList.remove(j);
                                score += 10;
                                scoreLabel.setText(Integer.toString(score));
                            }

                        }
                    }
                }

                //Set the win condition
                if (enemyList.isEmpty()) {
                    winLabel.setVisible(true);
                    mediaPlayer.stop();
                    AudioClip winSound = AssetManager.getWinSound();
                    winSound.play();
                    this.stop();
                }

            }
        }.start();
    }

}

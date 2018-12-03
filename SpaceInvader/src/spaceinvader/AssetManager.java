/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvader;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.paint.ImagePattern;

/**
 *
 * @author cstuser
 */
public class AssetManager {
    static private Background backgroundImage = null;
    static private ArrayList<ImagePattern> aliens = new ArrayList<>();
    static private ImagePattern shieldImage = null;
    static private ImagePattern projectileImage = null;
    static private ImagePattern shipImage = null;
    static private ImagePattern alienProjectile = null;
    static private ImagePattern flash = null;
    static private Image play = null;
    static private Image restart = null;
    
    static private Media backgroundMusic = null;
    static private AudioClip winSound = null;
    static private AudioClip loseSound = null;
    static private AudioClip alienSound = null;
    static private AudioClip shootingSound = null;
    
    static private String fileURL(String relativePath)
    {
        return new File(relativePath).toURI().toString();
    }
    
    static public void preloadAllAssets(){
        
        Image background = new Image(fileURL("./assets/images/background.jpg"));
        
        backgroundImage = new Background(
                            new BackgroundImage(background, 
                                                BackgroundRepeat.NO_REPEAT, 
                                                BackgroundRepeat.NO_REPEAT, 
                                                BackgroundPosition.DEFAULT,
                                                BackgroundSize.DEFAULT));
        aliens.add(new ImagePattern(new Image(fileURL("./assets/images/alien.png"))));
        aliens.add(new ImagePattern(new Image(fileURL("./assets/images/invader.png"))));
        aliens.add(new ImagePattern(new Image(fileURL("./assets/images/invader2.png"))));
        projectileImage = new ImagePattern(new Image(fileURL("./assets/images/projectile1.png")));
        alienProjectile = new ImagePattern(new Image(fileURL("./assets/images/projectile.png")));
        shipImage = new ImagePattern(new Image(fileURL("./assets/images/ship.png")));
        shieldImage = new ImagePattern(new Image(fileURL("./assets/images/shield.png")));
        flash = new ImagePattern(new Image(fileURL("./assets/images/flash.png")));
        play = (new Image(fileURL("./assets/images/play_button.png")));
        restart = (new Image(fileURL("./assets/images/restart.png")));
        
        backgroundMusic = new Media(fileURL("./assets/music/playing.mp3"));
        winSound = new AudioClip(fileURL("./assets/music/won.wav"));
        loseSound = new AudioClip(fileURL("./assets/music/lost.wav"));
        shootingSound = new AudioClip(fileURL("./assets/soundfx/hadouken.wav"));
        alienSound = new AudioClip(fileURL("./assets/soundfx/hit.wav")); 
         
    }

    public static Image getPlay() {
        return play;
    }

    public static Image getRestart() {
        return restart;
    }

    public static ImagePattern getFlash() {
        return flash;
    }
    
    public static ImagePattern getAlienProjectile() {
        return alienProjectile;
    }

    public static ImagePattern getAliens(int x) {
        return aliens.get(x);
    }

    public static ImagePattern getShieldImage() {
        return shieldImage;
    }

    public static AudioClip getWinSound() {
        return winSound;
    }

    public static AudioClip getLoseSound() {
        return loseSound;
    }

    public static AudioClip getAlienSound() {
        return alienSound;
    }
    
    public static Background getBackgroundImage() {
        return backgroundImage;
    }
    
    public static Media getBackgroundMusic() {
        return backgroundMusic;
    }

    public static AudioClip getShootingSound() {
        return shootingSound;
    }

    public static ImagePattern getProjectileImage() {
        return projectileImage;
    }

    public static ImagePattern getShipImage() {
        return shipImage;
    }
    
}

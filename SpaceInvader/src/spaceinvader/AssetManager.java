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
        
        Image background = new Image(fileURL("./assets/images"));
        
         backgroundImage = new Background(
                            new BackgroundImage(background, 
                                                BackgroundRepeat.NO_REPEAT, 
                                                BackgroundRepeat.NO_REPEAT, 
                                                BackgroundPosition.DEFAULT,
                                                BackgroundSize.DEFAULT));
         backgroundMusic = new Media(fileURL("./assets/music/playing.mp3"));
         winSound = new AudioClip(fileURL("./assets/music/won.wav"));
         loseSound = new AudioClip(fileURL("./assets/music/lose.wav"));
         shootingSound = new AudioClip(fileURL("./assets/soundfx/hadouken.wav"));
         
         
    }

    public static ArrayList<ImagePattern> getAliens() {
        return aliens;
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

    public static ArrayList<ImagePattern> getPlanets() {
        return aliens;
    }

    public static Media getBackgroundMusic() {
        return backgroundMusic;
    }

    public static AudioClip getNewPlanetSound() {
        return alienSound;
    }

    public static AudioClip getShootingSound() {
        return shootingSound;
    }
    
    
    
    
    
}

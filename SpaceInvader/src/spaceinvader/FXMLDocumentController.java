/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvader;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
    
    private ArrayList<Enemy1> enemyOne = new ArrayList<>();
    private ArrayList<Enemy2> enemyTwo = new ArrayList<>();
    private ArrayList<Enemy3> enemyThree = new ArrayList<>();
    private ArrayList<Enemy4> enemyFour = new ArrayList<>();
    private ArrayList<GameObject> objectList = new ArrayList<>();
    
    @FXML
    AnchorPane pane;
    
    @FXML
    private void onMouseClicked(MouseEvent e) {
    }
    
    @FXML
    private void onMouseMoved(MouseEvent e){
    }
    
    public void addToPane(Node node){
        pane.getChildren().add(node);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int i = 0; i < 8; i++) {
            enemyOne.add(new Enemy1(new Vector2D(210 + i * 60, 50)));
            enemyTwo.add(new Enemy2(new Vector2D(210 + i * 60, 100)));
            enemyThree.add(new Enemy3(new Vector2D(210 + i * 60, 150)));
            enemyFour.add(new Enemy4(new Vector2D(210 + i * 60, 200)));
        }
        
        for(Enemy1 x : enemyOne){
           addToPane(x.getCircle());
        }
        
        for(Enemy2 y : enemyTwo){
            addToPane(y.getCircle());
        }
        
        for(Enemy3 z : enemyThree){
            addToPane(z.getCircle());
        }
        
        for(Enemy4 w : enemyFour){
            addToPane(w.getCircle());
        }
    }    
    
}

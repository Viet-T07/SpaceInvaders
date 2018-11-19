/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvader;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author cstuser
 */
public class FXMLDocumentController implements Initializable {
    
    private Enemy1 one;
    
    
    
    @FXML
    private Label label;
    
    @FXML
    private void onMouseClicked(MouseEvent e) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    private void onMouseMoved(MouseEvent e){
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                
            }
        }
    }    
    
}

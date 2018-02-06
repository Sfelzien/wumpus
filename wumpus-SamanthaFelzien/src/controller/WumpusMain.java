package controller;

/**
 * This class is the controller that functions 
 * as the go between for the view and the model 
 * using event handlers and observers
 * 
 * Samantha Felzien
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.HuntTheWumpusGame;
import model.Map;

public class WumpusMain extends Application {

  public static void main(String[] args) {
    launch(args);
  }
  private HuntTheWumpusGame theGame;
 // private Map map; 
  //

  @Override
  public void start(Stage stage) throws Exception {
    BorderPane pane = new BorderPane();

    Scene scene = new Scene(pane, 690, 630);
    stage.setScene(scene);
    //stage.show();
   theGame = new HuntTheWumpusGame();
    //map = new Map();
    
  }
}
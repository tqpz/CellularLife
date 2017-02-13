import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Created by Mateusz on 11.02.2017.
 */
public class GameOfLife extends Application {
    private static final int PRIMARY_STAGE_HEIGHT = 600;
    private static final int PRIMARY_STAGE_WIDTH = 800;
    private static final int CELL_SIZE = 10;

    private MenuBar menuBar;
    private Menu fileMenu, gameMenu, optionMenu, loadMenu;
    private MenuItem fm_exit, gm_start, gm_stop, gm_reset, om_speed, om_rules, lm_loadRule, lm_loadStruct;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        GameScene gameScene = new GameScene();

        Thread test = new Thread(gameScene);


        primaryStage.setTitle("Game of Life");

        Scene scene = new Scene(root, PRIMARY_STAGE_WIDTH, PRIMARY_STAGE_HEIGHT);



        menuBar = new MenuBar();

        fileMenu = new Menu("File");
        fm_exit = new MenuItem("Exit");
        fileMenu.getItems().add(fm_exit);

        fm_exit.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                primaryStage.close();
            }
        });

        gameMenu = new Menu("Game");
        gm_start = new MenuItem("Start");
        gm_stop = new MenuItem("Stop");
        gm_reset = new MenuItem("Reset");
        gameMenu.getItems().addAll(gm_start,gm_stop, gm_reset);


        gm_start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public synchronized void handle(ActionEvent event) {
                if(!test.isAlive())
                    test.start();
                else
                    test.resume();
            }
        });

        gm_stop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public synchronized void handle(ActionEvent event) {
                test.suspend();
                }
        });

        gm_reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameScene.resetAll();
            }
        });


        optionMenu = new Menu("Options");
        om_speed = new MenuItem("Speed");
        om_rules = new MenuItem("Rules");
        optionMenu.getItems().addAll(om_speed,om_rules);

        loadMenu = new Menu("Load");
        lm_loadRule = new MenuItem("Load rules");
        lm_loadStruct = new MenuItem("Load structures");
        loadMenu.getItems().addAll(lm_loadRule,lm_loadStruct);

        menuBar.getMenus().addAll(fileMenu, gameMenu, loadMenu, optionMenu);

        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

        root.setTop(menuBar);
        root.setCenter(gameScene);

        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
    }


    public static int getCellSize() {
        return CELL_SIZE;
    }


    public static void main(String[] args){
        launch(args);
    }
}

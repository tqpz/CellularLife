import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameOfLife extends Application {
    //set primary window size
    private static final int PRIMARY_STAGE_HEIGHT = 600;
    private static final int PRIMARY_STAGE_WIDTH = 800;

    //create Menu bar objects - they are representing clickable items on menubar
    private MenuBar menuBar;
    private Menu fileMenu, gameMenu, rulesMenu, structuresMenu, speedMenu, sizeMenu;
    private MenuItem fm_exit, gm_start, gm_stop, gm_reset,
            rm_conwayRules, rm_labirynth, rm_seeds,
            rm_coral, rm_highLife, rm_replicator,
            rm_assimilation, rm_walledCities,
            rm_coagulations, rm_twoXtwo, rm_dayAndNight,
            rm_amoeba, rm_diamoeba, rm_the34, rm_longLife,
            rm_stains, rm_gnarl, rm_mystery, rm_flakes;

    private MenuItem s_glider, s_acorn, s_Rpentomino, s_lightweightSpaceship, s_gliderGun;

    //create slider objects
    private CustomMenuItem om_slider, sm_slider;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        GameScene gameScene = new GameScene();

        //create simulation thread
        Thread test = new Thread(gameScene);

        //set window title
        primaryStage.setTitle("Game of Life");

        //create scene, set minimal height and width
        Scene scene = new Scene(root, PRIMARY_STAGE_WIDTH, PRIMARY_STAGE_HEIGHT);
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(600);

        //initialize "file" drop-down menu items and add eventhandlers
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

        //initialize "Game" menu item, add drop-down menu items and event handlers
        gameMenu = new Menu("Game");
        gm_start = new MenuItem("Start");
        gm_stop = new MenuItem("Stop");
        gm_reset = new MenuItem("Reset");
        gameMenu.getItems().addAll(gm_start, gm_stop, gm_reset);

        //operations on simulation thread
        gm_start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public synchronized void handle(ActionEvent event) {
                if (!test.isAlive())
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

        //initialize all "Rules" menu items and add on click event handlers
        rulesMenu = new Menu("Rules");
        rm_conwayRules = new MenuItem("Game of Life");
        rm_labirynth = new MenuItem("Maze");
        rm_seeds = new MenuItem("Seeds");
        rm_coral = new MenuItem("Coral");
        rm_highLife = new MenuItem("High Life");
        rm_replicator = new MenuItem("Replicator");
        rm_assimilation = new MenuItem("Assimilation");
        rm_walledCities = new MenuItem("Walled Cities");
        rm_coagulations = new MenuItem("Coagulations");
        rm_twoXtwo = new MenuItem("2x2");
        rm_dayAndNight = new MenuItem("Day and Night");
        rm_amoeba = new MenuItem("Amoeba");
        rm_diamoeba = new MenuItem("Diamoeba");
        rm_the34 = new MenuItem("34");
        rm_longLife = new MenuItem("Long Life");
        rm_stains = new MenuItem("Stains");
        rm_gnarl = new MenuItem("Gnarl");
        rm_mystery = new MenuItem("Mystery");
        rm_flakes = new MenuItem("Flakes");

        //on click all rules ale set to false and afterwards choosen rule is set true
        rm_conwayRules.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameScene.resetRules();
                gameScene.setConwayRules(true);
            }
        });

        rm_labirynth.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameScene.resetRules();
                gameScene.setLabirynth(true);
            }
        });

        rm_seeds.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameScene.resetRules();
                gameScene.setSeeds(true);
            }
        });

        rm_coral.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameScene.resetRules();
                gameScene.setCoral(true);
            }
        });

        rm_highLife.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameScene.resetRules();
                gameScene.setHighLife(true);
            }
        });

        rm_replicator.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameScene.resetRules();
                gameScene.setReplicator(true);
            }
        });

        rm_assimilation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameScene.resetRules();
                gameScene.setAssimilation(true);
            }
        });

        rm_walledCities.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameScene.resetRules();
                gameScene.setWalledCities(true);
            }
        });

        rm_coagulations.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameScene.resetRules();
                gameScene.setCoagulations(true);
            }
        });

        rm_twoXtwo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameScene.resetRules();
                gameScene.setTwoXtwo(true);
            }
        });

        rm_dayAndNight.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameScene.resetRules();
                gameScene.setDayAndNight(true);
            }
        });

        rm_amoeba.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameScene.resetRules();
                gameScene.setAmoeba(true);
            }
        });

        rm_diamoeba.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameScene.resetRules();
                gameScene.setDiamoeba(true);
            }
        });

        rm_the34.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameScene.resetRules();
                gameScene.setThe34(true);
            }
        });

        rm_longLife.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameScene.resetRules();
                gameScene.setLongLife(true);
            }
        });

        rm_stains.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameScene.resetRules();
                gameScene.setStains(true);
            }
        });

        rm_gnarl.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameScene.resetRules();
                gameScene.setGnarl(true);
            }
        });

        rm_mystery.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameScene.resetRules();
                gameScene.setMystery(true);
            }
        });

        rm_flakes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameScene.resetRules();
                gameScene.setFlakes(true);
            }
        });

        //add initialized items to menubar
        rulesMenu.getItems().addAll(rm_conwayRules, rm_labirynth, rm_seeds,
                rm_coral, rm_highLife, rm_replicator,
                rm_assimilation, rm_walledCities,
                rm_coagulations, rm_twoXtwo, rm_dayAndNight,
                rm_amoeba, rm_diamoeba, rm_the34, rm_longLife,
                rm_stains, rm_gnarl, rm_mystery, rm_flakes);

        //initialize and add "Load" menu items
        structuresMenu = new Menu("Structures");
        s_glider = new MenuItem("Glider");
        s_acorn = new MenuItem("Acorn");
        s_Rpentomino = new MenuItem("R-pentomino");
        s_lightweightSpaceship = new MenuItem("Lightweight Spaceship");
        s_gliderGun = new MenuItem("Glider Gun");
        structuresMenu.getItems().addAll(s_glider, s_acorn, s_Rpentomino, s_lightweightSpaceship, s_gliderGun);

        s_Rpentomino.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameScene.s_Rpentomino(100, 100);
            }
        });

        s_glider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameScene.s_gliderAdd(10, 10);
            }
        });

        s_acorn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameScene.s_acorn(100, 100);
            }
        });

        s_lightweightSpaceship.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameScene.s_lightweightSpaceship(20, 20);
            }
        });

        s_gliderGun.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameScene.s_gliderGun(30,30);
            }
        });

        //initialize slider that indicates speed of simulation
        speedMenu = new Menu("Speed");
        Slider speedSlider = new Slider();
        speedSlider.setMin(0);
        speedSlider.setMax(40);
        speedSlider.setValue(20);
        //speedSlider.setShowTickLabels(true);
        speedSlider.setShowTickMarks(true);
        speedSlider.setMinorTickCount(20);
        om_slider = new CustomMenuItem(speedSlider);
        speedMenu.getItems().addAll(om_slider);

        om_slider.setHideOnClick(false);

        om_slider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameScene.setANIMATION_SPEED((int) speedSlider.getValue());
            }
        });

        //initialize slider that is responsible for grid size
        sizeMenu = new Menu("Size");
        Slider sizeSlider = new Slider();
        sizeSlider.setMin(2);
        sizeSlider.setMax(100);
        sizeSlider.setValue(10);
        sizeSlider.setShowTickMarks(true);
        sizeSlider.setMinorTickCount(20);
        sm_slider = new CustomMenuItem(sizeSlider);

        sm_slider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameScene.setCELL_SIZE((int) sizeSlider.getValue());
                gameScene.requestLayout();
            }
        });

        //add items to menu
        sizeMenu.getItems().addAll(sm_slider);

        menuBar.getMenus().addAll(fileMenu, gameMenu, structuresMenu, rulesMenu, speedMenu, sizeMenu);

        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

        //set posiotion of components in the window
        root.setTop(menuBar);
        root.setCenter(gameScene);

        //set primary scene and show it
        primaryStage.setScene(scene);
        primaryStage.show();

        //exit window on close request
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}

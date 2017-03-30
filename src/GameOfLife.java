import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameOfLife extends Application {
    //set primary window size

    private static final int PRIMARY_STAGE_HEIGHT = 600;
    private static final int PRIMARY_STAGE_WIDTH = 800;

    //create Menu bar objects - they are representing clickable items on menubar
    private MenuBar menuBar;
    private Menu fileMenu, gameMenu, rulesMenu, structuresMenu, speedMenu, sizeMenu, colorMenu;
    private ToggleGroup rulesGroup = new ToggleGroup();
    private boolean paused;

    private RadioMenuItem
            rm_conwayRules, rm_labirynth, rm_seeds,
            rm_coral, rm_highLife, rm_replicator,
            rm_assimilation, rm_walledCities,
            rm_coagulations, rm_twoXtwo, rm_dayAndNight,
            rm_amoeba, rm_diamoeba, rm_the34, rm_longLife,
            rm_stains, rm_gnarl, rm_mystery, rm_flakes,
            rm_spiralGrowth;

    private MenuItem fm_exit, gm_start, gm_stop, gm_reset;

    private MenuItem s_glider, s_acorn, s_Rpentomino, s_lightweightSpaceship,
            s_gliderGun, s_pulsar, s_dart, s_puffer1, s_loaf,
            s_pond, s_elevener, s_honeycomb, s_paperclip,
            s_moose, s_eater2, s_spiral, s_lake2, s_mickeyMouse,
            s_pentadecathlon, s_galaxy, s_pinwheel, s_sixBits,
            s_gabrielsp138, s_archimsp144, s_78P70, s_p60hassler,
            s_70P2H1V01, s_sparky, s_backrake1, s_blinkerPuffer,
            s_spacefiller, s_pufferTrain, s_bloom, s_lidka,
            s_naturalLWSS, s_rabbits17423, s_rabbits17465,
            s_rabbits, s_pulsars;

    //create slider objects
    private CustomMenuItem om_slider, sm_slider;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.getIcons().add(new Image("file:img/logo.png"));
        paused = true;

        BorderPane root = new BorderPane();

        GameScene gameScene = new GameScene();

        Structure struct = new Structure(gameScene, gameScene.getCell());

        //set window title
        primaryStage.setTitle("Game of Life");

        gameScene.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            // test.suspend();
            gameScene.getTimer().stop();
            //paused = true;
        });

        gameScene.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            if (!paused) {
                //test.resume();
                gameScene.getTimer().start();
            }
        });

        //create scene, set minimal height and width
        Scene scene = new Scene(root, PRIMARY_STAGE_WIDTH, PRIMARY_STAGE_HEIGHT);
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(600);

        //initialize "file" drop-down menu items and add eventhandlers
        menuBar = new MenuBar();

        gameScene.setOnMouseExited(event -> gameScene.getTimer().stop());
        gameScene.setOnMouseEntered(event -> {
            if(!paused)
                gameScene.getTimer().start();
        });


        fileMenu = new Menu("File");
        fm_exit = new MenuItem("Exit");
        fileMenu.getItems().add(fm_exit);

        fm_exit.setOnAction(event -> primaryStage.close());

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
                paused = false;
                gameScene.startAnimation(gameScene.getTimer());
            }
        });

        gm_stop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public synchronized void handle(ActionEvent event) {
                paused = true;
                gameScene.getTimer().stop();
            }
        });

        gm_reset.setOnAction(event -> gameScene.resetAll());

        //initialize all "Rules" menu items and add on click event handlers
        rulesMenu = new Menu("Rules");

        rm_conwayRules = new RadioMenuItem("Game of Life");
        rm_conwayRules.setToggleGroup(rulesGroup);
        rm_conwayRules.setSelected(true);

        rm_labirynth = new RadioMenuItem("Maze");
        rm_labirynth.setToggleGroup(rulesGroup);

        rm_seeds = new RadioMenuItem("Seeds");
        rm_seeds.setToggleGroup(rulesGroup);

        rm_coral = new RadioMenuItem("Coral");
        rm_coral.setToggleGroup(rulesGroup);

        rm_highLife = new RadioMenuItem("High Life");
        rm_highLife.setToggleGroup(rulesGroup);

        rm_replicator = new RadioMenuItem("Replicator");
        rm_replicator.setToggleGroup(rulesGroup);

        rm_assimilation = new RadioMenuItem("Assimilation");
        rm_assimilation.setToggleGroup(rulesGroup);

        rm_walledCities = new RadioMenuItem("Walled Cities");
        rm_walledCities.setToggleGroup(rulesGroup);

        rm_coagulations = new RadioMenuItem("Coagulations");
        rm_coagulations.setToggleGroup(rulesGroup);

        rm_twoXtwo = new RadioMenuItem("2x2");
        rm_twoXtwo.setToggleGroup(rulesGroup);

        rm_dayAndNight = new RadioMenuItem("Day and Night");
        rm_dayAndNight.setToggleGroup(rulesGroup);

        rm_amoeba = new RadioMenuItem("Amoeba");
        rm_amoeba.setToggleGroup(rulesGroup);

        rm_diamoeba = new RadioMenuItem("Diamoeba");
        rm_diamoeba.setToggleGroup(rulesGroup);

        rm_the34 = new RadioMenuItem("34");
        rm_the34.setToggleGroup(rulesGroup);

        rm_longLife = new RadioMenuItem("Long Life");
        rm_longLife.setToggleGroup(rulesGroup);

        rm_stains = new RadioMenuItem("Stains");
        rm_stains.setToggleGroup(rulesGroup);

        rm_gnarl = new RadioMenuItem("Gnarl");
        rm_gnarl.setToggleGroup(rulesGroup);

        rm_mystery = new RadioMenuItem("Mystery");
        rm_mystery.setToggleGroup(rulesGroup);

        rm_flakes = new RadioMenuItem("Flakes");
        rm_flakes.setToggleGroup(rulesGroup);

        rm_spiralGrowth = new RadioMenuItem("Spiral Growth");
        rm_spiralGrowth.setToggleGroup(rulesGroup);


        //on click all rules ale set to false and afterwards choosen rule is set true
        rm_conwayRules.setOnAction(event -> {
            gameScene.resetRules();
            gameScene.setConwayRules(true);
        });

        rm_labirynth.setOnAction(event -> {
            gameScene.resetRules();
            gameScene.setLabirynth(true);
        });

        rm_seeds.setOnAction(event -> {
            gameScene.resetRules();
            gameScene.setSeeds(true);
        });

        rm_coral.setOnAction(event -> {
            gameScene.resetRules();
            gameScene.setCoral(true);
        });

        rm_highLife.setOnAction(event -> {
            gameScene.resetRules();
            gameScene.setHighLife(true);
        });

        rm_replicator.setOnAction(event -> {
            gameScene.resetRules();
            gameScene.setReplicator(true);
        });

        rm_assimilation.setOnAction(event -> {
            gameScene.resetRules();
            gameScene.setAssimilation(true);
        });

        rm_walledCities.setOnAction(event -> {
            gameScene.resetRules();
            gameScene.setWalledCities(true);
        });

        rm_coagulations.setOnAction(event -> {
            gameScene.resetRules();
            gameScene.setCoagulations(true);
        });

        rm_twoXtwo.setOnAction(event -> {
            gameScene.resetRules();
            gameScene.setTwoXtwo(true);
        });

        rm_dayAndNight.setOnAction(event -> {
            gameScene.resetRules();
            gameScene.setDayAndNight(true);
        });

        rm_amoeba.setOnAction(event -> {
            gameScene.resetRules();
            gameScene.setAmoeba(true);
        });

        rm_diamoeba.setOnAction(event -> {
            gameScene.resetRules();
            gameScene.setDiamoeba(true);
        });

        rm_the34.setOnAction(event -> {
            gameScene.resetRules();
            gameScene.setThe34(true);
        });

        rm_longLife.setOnAction(event -> {
            gameScene.resetRules();
            gameScene.setLongLife(true);
        });

        rm_stains.setOnAction(event -> {
            gameScene.resetRules();
            gameScene.setStains(true);
        });

        rm_gnarl.setOnAction(event -> {
            gameScene.resetRules();
            gameScene.setGnarl(true);
        });

        rm_mystery.setOnAction(event -> {
            gameScene.resetRules();
            gameScene.setMystery(true);
        });

        rm_flakes.setOnAction(event -> {
            gameScene.resetRules();
            gameScene.setFlakes(true);
        });

        rm_spiralGrowth.setOnAction(event -> {
            gameScene.resetRules();
            gameScene.setSpiralGrowth(true);
            struct.spiralGrowth(gameScene.getxClick() - 1, gameScene.getyClick());
        });

        //add initialized items to menubar
        rulesMenu.getItems().addAll(rm_conwayRules, rm_labirynth, rm_seeds,
                rm_coral, rm_highLife, rm_replicator,
                rm_assimilation, rm_walledCities,
                rm_coagulations, rm_twoXtwo, rm_dayAndNight,
                rm_amoeba, rm_diamoeba, rm_the34, rm_longLife,
                rm_stains, rm_gnarl, rm_mystery, rm_flakes, rm_spiralGrowth);

        //initialize and add "Structures" menu items
        Menu s_oscilators = new Menu("Oscillators");
        Menu s_stillLifes = new Menu("Still lifes");
        Menu s_spaceships = new Menu("Spaceships");
        Menu s_puffers = new Menu("Puffers");
        Menu s_methuselah = new Menu("Methuselah");
        Menu s_guns = new Menu("Guns");

        structuresMenu = new Menu("Structures");
        s_glider = new MenuItem("Glider");
        s_acorn = new MenuItem("Acorn");
        s_Rpentomino = new MenuItem("R-pentomino");
        s_lightweightSpaceship = new MenuItem("Lightweight Spaceship");
        s_gliderGun = new MenuItem("Glider Gun");
        s_pulsar = new MenuItem("Pulsar");
        s_dart = new MenuItem("Dart");
        s_puffer1 = new MenuItem("Puffer1");
        s_loaf = new MenuItem("Loaf");
        s_pond = new MenuItem("Pond");
        s_elevener = new MenuItem("Elevener");
        s_honeycomb = new MenuItem("Honeycomb");
        s_paperclip = new MenuItem("Paperclip");
        s_moose = new MenuItem("Moose");
        s_eater2 = new MenuItem("Eater 2");
        s_spiral = new MenuItem("Spiral");
        s_lake2 = new MenuItem("Lake 2");
        s_mickeyMouse = new MenuItem("Mickey Mouse");

        s_pentadecathlon = new MenuItem("Pentadecathlon");
        s_galaxy = new MenuItem("Galaxy");
        s_pinwheel = new MenuItem("Pinwheel");
        s_sixBits = new MenuItem("6Bits");
        s_gabrielsp138 = new MenuItem("Gabriel's p138");
        s_archimsp144 = new MenuItem("Archim's p144");
        s_78P70 = new MenuItem("78P70");
        s_p60hassler = new MenuItem("P60 hassler");

        s_70P2H1V01 = new MenuItem("70P2H1V01");
        s_sparky = new MenuItem("Sparky");

        s_backrake1 = new MenuItem("Backrake1");
        s_blinkerPuffer = new MenuItem("Blinker puffer");
        s_spacefiller = new MenuItem("Spacefiller");
        s_pufferTrain = new MenuItem("Puffer train");

        s_bloom = new MenuItem("Bloom");
        s_lidka = new MenuItem("Lidka precedessor");
        s_naturalLWSS = new MenuItem("Natural LWSS");
        s_rabbits17423 = new MenuItem("Rabbits relation 17423");
        s_rabbits17465 = new MenuItem("Rabbits relation 17465");
        s_rabbits = new MenuItem("Rabbits");
        s_pulsars = new MenuItem("Pulsars");

        s_oscilators.getItems().addAll(s_pulsar, s_pentadecathlon, s_galaxy,
                s_pinwheel, s_sixBits, s_gabrielsp138, s_archimsp144, s_78P70,
                s_p60hassler);
        s_spaceships.getItems().addAll(s_glider, s_lightweightSpaceship, s_dart,
                s_70P2H1V01, s_sparky);
        s_stillLifes.getItems().addAll(s_loaf, s_pond, s_elevener, s_honeycomb,
                s_paperclip, s_moose, s_eater2, s_spiral, s_lake2, s_mickeyMouse);
        s_methuselah.getItems().addAll(s_Rpentomino, s_acorn, s_bloom, s_lidka,
                s_naturalLWSS, s_rabbits17423, s_rabbits17465, s_rabbits, s_pulsars);
        s_puffers.getItems().addAll(s_puffer1, s_backrake1, s_blinkerPuffer,
                s_spacefiller, s_pufferTrain);
        s_guns.getItems().addAll(s_gliderGun);

        structuresMenu.getItems().addAll(s_oscilators, s_spaceships, s_stillLifes, s_methuselah, s_puffers, s_guns);

        s_Rpentomino.setOnAction(event -> struct.Rpentomino(gameScene.getxClick(), gameScene.getyClick()));

        s_glider.setOnAction(event -> struct.glider(gameScene.getxClick(), gameScene.getyClick() + 1));

        s_acorn.setOnAction(event -> struct.acorn(gameScene.getxClick() - 1, gameScene.getyClick()));

        s_lightweightSpaceship.setOnAction(event -> struct.lightweightSpaceship(gameScene.getxClick() - 1, gameScene.getyClick()));

        s_gliderGun.setOnAction(event -> struct.gliderGun(gameScene.getxClick(), gameScene.getyClick()));

        s_pulsar.setOnAction(event -> struct.pulsar(gameScene.getxClick(), gameScene.getyClick() - 2));

        s_dart.setOnAction(event -> struct.dart(gameScene.getxClick() - 1, gameScene.getyClick()));

        s_puffer1.setOnAction(event -> struct.puffer1(gameScene.getxClick(), gameScene.getyClick() + 2));

        s_loaf.setOnAction(event -> struct.loaf(gameScene.getxClick() - 1, gameScene.getyClick()));

        s_pond.setOnAction(event -> struct.pond(gameScene.getxClick() - 1, gameScene.getyClick()));

        s_elevener.setOnAction(event -> struct.elevener(gameScene.getxClick(), gameScene.getyClick()));

        s_honeycomb.setOnAction(event -> struct.honeycomb(gameScene.getxClick() - 2, gameScene.getyClick()));

        s_paperclip.setOnAction(event -> struct.paperclip(gameScene.getxClick(), gameScene.getyClick()));

        s_moose.setOnAction(event -> struct.moose(gameScene.getxClick(), gameScene.getyClick()));

        s_eater2.setOnAction(event -> struct.eater2(gameScene.getxClick() - 1, gameScene.getyClick() - 1));

        s_spiral.setOnAction(event -> struct.spiral(gameScene.getxClick(), gameScene.getyClick()));

        s_lake2.setOnAction(event -> struct.lake2(gameScene.getxClick(), gameScene.getyClick()));

        s_mickeyMouse.setOnAction(event -> struct.mickeyMouse(gameScene.getxClick() - 1, gameScene.getyClick()));

        s_pentadecathlon.setOnAction(event -> struct.pentadecathlon(gameScene.getxClick(), gameScene.getyClick() - 1));

        s_galaxy.setOnAction(event -> struct.galaxy(gameScene.getxClick(), gameScene.getyClick()));

        s_pinwheel.setOnAction(event -> struct.pinwheel(gameScene.getxClick(), gameScene.getyClick()));

        s_sixBits.setOnAction(event -> struct.sixBits(gameScene.getxClick(), gameScene.getyClick()));

        s_gabrielsp138.setOnAction(event -> struct.gabrielsp138(gameScene.getxClick(), gameScene.getyClick()));

        s_archimsp144.setOnAction(event -> struct.archimsp144(gameScene.getxClick(), gameScene.getyClick()));

        s_78P70.setOnAction(event -> struct.seven_eight_p_seven_zero(gameScene.getxClick(), gameScene.getyClick()));

        s_p60hassler.setOnAction(event -> struct.p60_hassler(gameScene.getxClick(), gameScene.getyClick()));

        s_70P2H1V01.setOnAction(event -> struct.seven_0P2H1V01(gameScene.getxClick(), gameScene.getyClick()));

        s_sparky.setOnAction(event -> struct.sparky(gameScene.getxClick(), gameScene.getyClick()));

        s_backrake1.setOnAction(event -> struct.backrake1(gameScene.getxClick(), gameScene.getyClick()));

        s_blinkerPuffer.setOnAction(event -> struct.blinkerPuffer(gameScene.getxClick(), gameScene.getyClick()));

        s_spacefiller.setOnAction(event -> struct.spacefiller(gameScene.getxClick(), gameScene.getyClick()));

        s_pufferTrain.setOnAction(event -> struct.pufferTrain(gameScene.getxClick(), gameScene.getyClick()));

        s_bloom.setOnAction(event -> struct.bloom(gameScene.getxClick(), gameScene.getyClick()));

        s_lidka.setOnAction(event -> struct.lidka(gameScene.getxClick(), gameScene.getyClick()));

        s_naturalLWSS.setOnAction(event -> struct.naturalLWSS(gameScene.getxClick(), gameScene.getyClick()));

        s_rabbits17423.setOnAction(event -> struct.rabbits17423(gameScene.getxClick(), gameScene.getyClick()));

        s_rabbits17465.setOnAction(event -> struct.rabbits17465(gameScene.getxClick(), gameScene.getyClick()));

        s_rabbits.setOnAction(event -> struct.rabbits(gameScene.getxClick(), gameScene.getyClick()));

        s_pulsars.setOnAction(event -> struct.pulsars(gameScene.getxClick() - 1, gameScene.getyClick() - 1));


        //initialize slider that indicates speed of simulation
        speedMenu = new Menu("Speed");
        Slider speedSlider = new Slider();
        speedSlider.setMin(0);
        speedSlider.setMax(1000);
        speedSlider.setValue(480);
        speedSlider.setOrientation(Orientation.HORIZONTAL);
        //speedSlider.setShowTickLabels(true);
        // speedSlider.setShowTickMarks(true);
        //speedSlider.setMinorTickCount(20);
        om_slider = new CustomMenuItem(speedSlider);
        speedMenu.getItems().addAll(om_slider);

        om_slider.setHideOnClick(false);

        om_slider.setOnAction(event -> gameScene.setANIMATION_SPEED((int)
                (500 - speedSlider.getValue())));

        //initialize slider responsible for grid size
        sizeMenu = new Menu("Size");
        Slider sizeSlider = new Slider();
        sizeSlider.setMin(1);
        sizeSlider.setMax(20);
        sizeSlider.setValue(10);

       // sizeSlider.setShowTickMarks(true);
        //sizeSlider.setMinorTickCount(20);
        sm_slider = new CustomMenuItem(sizeSlider);

        sm_slider.setOnAction(event -> {
            gameScene.setCELL_SIZE((int) sizeSlider.getValue());
            gameScene.requestLayout();
        });

        //create ColorPicker and add it to menubar with css styling
        root.getStylesheets().addAll("style.css");
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(gameScene.getCellColor());
        colorPicker.setOnAction(event -> gameScene.setCellColor(colorPicker.getValue()));
        colorPicker.getStyleClass().add("button");
        colorPicker.getStyleClass().add("color-button");
        colorPicker.setPrefSize(30, 10);
        colorMenu = new Menu(null, colorPicker);

        //add items to menu
        sizeMenu.getItems().addAll(sm_slider);

        menuBar.getMenus().addAll(fileMenu, gameMenu, structuresMenu, rulesMenu, speedMenu, sizeMenu, colorMenu);

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
}

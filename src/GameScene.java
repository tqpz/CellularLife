import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class GameScene extends Pane {
    private ArrayList<Point> cell = new ArrayList<Point>(0); //this array is representing all alive cells on board

    private int gameSceneWidth;
    private int gameSceneHeight;

    private int CELL_SIZE = 10; //size of one cell
    private int ANIMATION_SPEED = 20; //speed of animation

    private AnimationTimer timer;

    //create canvas with width and height of window
    private Canvas canvas = new Canvas(gameSceneWidth, gameSceneHeight);
    private GraphicsContext gc = canvas.getGraphicsContext2D();

    //create boolean variables representing every rule
    private boolean conwayRules, labirynth, seeds,
            coral, highLife, replicator,
            assimilation, walledCities,
            coagulations, twoXtwo, dayAndNight,
            amoeba, diamoeba, the34, longLife,
            stains, gnarl, mystery, flakes, spiralGrowth;

    private boolean devmode;

//    private int s_0, s_1, s_2, s_3, s_4, s_5, s_6, s_7, s_8;
//    private int b_0, b_1, b_2, b_3, b_4, b_5, b_6, b_7, b_8;

    //create label that shows number of evolution
    private Label generationLabel;
    private int evolutionNum = 0;

    //create label that shows number of alive cells
    private Label aliveCellsOnBoard;

    private Color cellColor;
    private int xClick;
    private int yClick;


    public GameScene() {
        canvas.setStyle("-fx-background-color: #222222;");
        //set width and height perfectly fit on scene even with user resize operation
        gameSceneWidth = gameSceneWidth - (gameSceneWidth % CELL_SIZE);
        gameSceneHeight = gameSceneHeight - (gameSceneHeight % CELL_SIZE);

        cellColor = Color.web("#1a3399");
        conwayRules = true; //initially set conway rules
        devmode = false;

        xClick = 1;
        yClick = 1;

        //setup labels indicating generation and alive cells numbers
        generationLabel = new Label("Number of generation: 0");
        getChildren().add(generationLabel);

        aliveCellsOnBoard = new Label("Number of alive cells: " + cell.size());
        getChildren().add(aliveCellsOnBoard);
        getChildren().add(canvas); //add canvas to pane

        timer = new AnimationTimer() {
            private long lastUpdateTime = 0;

            @Override
            public void handle(long timestamp) {
                if (timestamp - lastUpdateTime >= ANIMATION_SPEED * 1_000_000) {
                    updateScene();
                    lastUpdateTime = timestamp;
                }
            }
        };

        //create listeners of resize operation
        widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
            gameSceneWidth = newSceneWidth.intValue();
            gameSceneWidth = gameSceneWidth - (gameSceneWidth % CELL_SIZE + 1);
            updateArraySize();
        });

        heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
            gameSceneHeight = newSceneHeight.intValue();
            gameSceneHeight = gameSceneHeight - (gameSceneHeight % CELL_SIZE + 1) - 10;
            updateArraySize();

        });

        //create click listener - on click add alive cell
        addEventHandler(MouseEvent.MOUSE_CLICKED, event -> addPoint(event));

        //create drag listerer - as above
        addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> addPoint(event));

    }

    public void updateScene() {
        //create boolean 2D array representing grid
        //this array is created to capture better performance
        boolean[][] cellsBoard = new boolean[gameSceneWidth / CELL_SIZE + 2][gameSceneHeight / CELL_SIZE + 2];
        //  this for iterates through cell list
        // if there are cells - sets their position in bool array
        try {
            for (int i = 0; i < cell.size(); i++) {
                cellsBoard[cell.get(i).x + 1][cell.get(i).y + 1] = true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Out of bounds at thread start");
        } catch (NullPointerException e) {
            System.out.println("Nullpointer at thread start");
        }

        //create array that contains points wchich are added in next generation
        ArrayList<Point> nextGeneration = new ArrayList<Point>(0);

        //nested for loop iterating through all board
        for (int i = 1; i < cellsBoard.length - 1; i++) {
            for (int j = 1; j < cellsBoard[0].length - 1; j++) {
                int neighbours = 0; //variable contains number of neighbours of the cell

                //checking every cell in the Moore neighbourhood
                if (cellsBoard[i - 1][j - 1]) neighbours++;
                if (cellsBoard[i][j - 1]) neighbours++;
                if (cellsBoard[i + 1][j - 1]) neighbours++;
                if (cellsBoard[i - 1][j]) neighbours++;
                if (cellsBoard[i + 1][j]) neighbours++;
                if (cellsBoard[i - 1][j + 1]) neighbours++;
                if (cellsBoard[i][j + 1]) neighbours++;
                if (cellsBoard[i + 1][j + 1]) neighbours++;

                //this conditional statements are representing chosen rule by the user
                //if some rule is chosen apply their transfer function
                if (conwayRules) {
                    if (cellsBoard[i][j]) {
                        if ((neighbours == 2) || (neighbours == 3)) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    } else {
                        if (neighbours == 3) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    }

                } else if (labirynth) {
                    if (cellsBoard[i][j]) {
                        if ((neighbours >= 1) && (neighbours <= 5)) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    } else {
                        if (neighbours == 3) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    }

                } else if (seeds) {
                    if (!cellsBoard[i][j]) {
                        if (neighbours == 2) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    }
                } else if (coral) {
                    if (cellsBoard[i][j]) {
                        if (neighbours >= 4 && neighbours <= 8) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    } else {
                        if (neighbours == 3) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    }

                } else if (highLife) {
                    if (cellsBoard[i][j]) {
                        if (neighbours == 2 || neighbours == 3) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    } else {
                        if (neighbours == 3 || neighbours == 6) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    }

                } else if (replicator) {
                    if (cellsBoard[i][j]) {
                        if (neighbours == 1 || neighbours == 3 || neighbours == 5 || neighbours == 7) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    } else {
                        if (neighbours == 1 || neighbours == 3 || neighbours == 5 || neighbours == 7) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    }

                } else if (assimilation) {
                    if (cellsBoard[i][j]) {
                        if (neighbours >= 4 && neighbours <= 7) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    } else {
                        if (neighbours >= 3 && neighbours <= 5) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    }
                } else if (walledCities) {
                    if (cellsBoard[i][j]) {
                        if (neighbours >= 2 && neighbours <= 5) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    } else {
                        if (neighbours >= 4 && neighbours <= 8) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    }
                } else if (coagulations) {
                    if (cellsBoard[i][j]) {
                        if (neighbours == 2 || neighbours == 3 || neighbours >= 5 && neighbours <= 8) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    } else {
                        if (neighbours == 3 || neighbours == 7 || neighbours == 8) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    }
                } else if (twoXtwo) {
                    if (cellsBoard[i][j]) {
                        if (neighbours == 1 || neighbours == 2 || neighbours == 5) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    } else {
                        if (neighbours == 3 || neighbours == 6) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    }
                } else if (dayAndNight) {
                    if (cellsBoard[i][j]) {
                        if (neighbours == 3 || neighbours == 4 || neighbours >= 6 && neighbours <= 8) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    } else {
                        if (neighbours == 3 || neighbours >= 6 && neighbours <= 8) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    }
                } else if (amoeba) {
                    if (cellsBoard[i][j]) {
                        if (neighbours == 1 || neighbours == 3 || neighbours == 5 || neighbours == 8) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    } else {
                        if (neighbours == 3 || neighbours == 5 || neighbours == 7) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    }
                } else if (diamoeba) {
                    if (cellsBoard[i][j]) {
                        if (neighbours >= 5 && neighbours <= 8) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    } else {
                        if (neighbours == 3 || neighbours >= 5 && neighbours <= 8) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    }
                } else if (the34) {
                    if (cellsBoard[i][j]) {
                        if (neighbours == 3 || neighbours == 4) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    } else {
                        if (neighbours == 3 || neighbours == 4) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    }
                } else if (longLife) {
                    if (cellsBoard[i][j]) {
                        if (neighbours == 5) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    } else {
                        if (neighbours >= 3 && neighbours <= 5) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    }
                } else if (stains) {
                    if (cellsBoard[i][j]) {
                        if (neighbours == 2 || neighbours == 3 || neighbours >= 5 && neighbours <= 8) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    } else {
                        if (neighbours == 3 || neighbours >= 6 && neighbours <= 8) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    }
                } else if (gnarl) {
                    if (cellsBoard[i][j]) {
                        if (neighbours == 1) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    } else {
                        if (neighbours == 1) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    }
                } else if (mystery) {
                    if (cellsBoard[i][j]) {
                        if (neighbours == 0 || neighbours >= 5 && neighbours <= 8) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    } else {
                        if (neighbours == 3 || neighbours == 4 || neighbours == 5 || neighbours == 8) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    }
                } else if (flakes) {
                    if (cellsBoard[i][j]) {
                        if (neighbours >= 0 && neighbours <= 8) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    } else {
                        if (neighbours == 3) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    }
                } else if (spiralGrowth) {
                    if (cellsBoard[i][j]) {
                        if (neighbours == 1 || neighbours >= 5 && neighbours <= 8) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    } else {
                        if (neighbours == 8 || neighbours >= 3 && neighbours <= 6) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    }
                }
            }
        }

        try {
            evolutionNum++; //increment generation variable
            resetBoard(); //clear existing cells
            cell.addAll(nextGeneration); //add next generation to main cell list
            requestLayout(); //show it on screen
        } catch (StackOverflowError e) {
            System.out.println("Overflow error");
        }
    }

    public void drawCanvasFrame() {
        gc.strokeLine(CELL_SIZE,
                (canvas.getHeight()),
                canvas.getWidth(),
                canvas.getHeight());

        gc.strokeLine(canvas.getWidth(),
                (CELL_SIZE),
                canvas.getWidth(),
                canvas.getHeight());
    }

    public void drawGrid() {
         /*this block is responsible for painting grid,
         grid is drawn regardless of cell*/
        try {
            //create lines horizontally
            for (int i = 0; i <= gameSceneWidth / CELL_SIZE; i++) {
                gc.strokeLine(((i * CELL_SIZE) + CELL_SIZE),
                        CELL_SIZE,
                        (i * CELL_SIZE) + CELL_SIZE,
                        CELL_SIZE + (CELL_SIZE * gameSceneHeight / CELL_SIZE));
            }

            //create lines vertically
            for (int i = 0; i <= gameSceneHeight / CELL_SIZE; i++) {
                gc.strokeLine(CELL_SIZE,
                        ((i * CELL_SIZE) + CELL_SIZE),
                        CELL_SIZE * (gameSceneWidth / CELL_SIZE),
                        ((i * CELL_SIZE) + CELL_SIZE));
            }
            drawCanvasFrame();
        } catch (NullPointerException e) {
        }
    }

    public void drawCells() {
        try {
            //iterate through cell array and set its color, size and shape
            for (Point newPoint : cell) {
                gc.setFill(cellColor);
                gc.fillRect(CELL_SIZE + (CELL_SIZE * newPoint.x),
                        CELL_SIZE + (CELL_SIZE * newPoint.y),
                        CELL_SIZE,
                        CELL_SIZE);
            }
        } catch (ConcurrentModificationException | NullPointerException e) {
        }
    }


    //this method is responsible for refreshing view, on every next generation this method is called
    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        canvas.setWidth(gameSceneWidth - CELL_SIZE + 1);
        canvas.setHeight(gameSceneHeight - CELL_SIZE + 1);

        aliveCellsOnBoard.setLayoutY(gameSceneHeight - 3); // position of alive cells label
        generationLabel.setLayoutY(gameSceneHeight - 3);
        aliveCellsOnBoard.setLayoutX(CELL_SIZE); // position of alive cells label
        generationLabel.setLayoutX(160);
        generationLabel.setText("Number of generation: " + String.valueOf(evolutionNum));
        aliveCellsOnBoard.setText("Number of alive cells: " + cell.size());

        gc.clearRect(0, 0, getWidth(), getHeight()); //clear canvas after each generation
        gc.setLineWidth(0.2);

        drawGrid();
        drawCells();
    }

    //on call add cell to a cell list
    public void addPoint(int x, int y) {
        if (!cell.contains(new Point(x, y))) {
            cell.add(new Point(x, y));
        }
        requestLayout();
    }

    //mouse click indicates values of x and y
    public void addPoint(MouseEvent me) {
        int x = (int) me.getX() / CELL_SIZE - 1;
        int y = (int) me.getY() / CELL_SIZE - 1;
        xClick = x;
        yClick = y;
        if ((x >= 0) && (x < gameSceneWidth) && (y >= 0) && (y < gameSceneHeight)) {
            addPoint(x, y);
        }

        if (devmode) {
            System.out.println("aliveCells.add(new Point(x + " + x + ", y + " + y + "));");
        }
    }

    //this method is called when user is resizing window, if there were alive
    // cells on position which is not existing after resize - delete that cells
    private void updateArraySize() {
        ArrayList<Point> removeList = new ArrayList<Point>(0);

        for (Point current : cell) {
            if ((current.x > gameSceneWidth / CELL_SIZE - 3) || (current.y > gameSceneHeight / CELL_SIZE - 3)) {
                removeList.add(current);
            }
        }
        cell.removeAll(removeList);
        requestLayout();
    }

    //clear alive cells - remove all points from list
    private void resetBoard() {
        cell.clear();
    }

    //this method is called when user clicks on reset menu item
    public void resetAll() {
        cell.clear(); //clears layout
        evolutionNum = 0; // set evolution number to 0
        if (devmode) {
            System.out.println("___________________");
        }
        requestLayout(); //force to refresh window
    }

    //create rules setters
    void setConwayRules(boolean conwayRules) {
        this.conwayRules = conwayRules;
    }

    void setLabirynth(boolean labirynth) {
        this.labirynth = labirynth;
    }

    void setSeeds(boolean seeds) {
        this.seeds = seeds;
    }

    void setCoral(boolean coral) {
        this.coral = coral;
    }

    void setHighLife(boolean highLife) {
        this.highLife = highLife;
    }

    void setReplicator(boolean replicator) {
        this.replicator = replicator;
    }

    void setAssimilation(boolean assimilation) {
        this.assimilation = assimilation;
    }

    void setWalledCities(boolean walledCities) {
        this.walledCities = walledCities;
    }

    void setCoagulations(boolean coagulations) {
        this.coagulations = coagulations;
    }

    void setTwoXtwo(boolean twoXtwo) {
        this.twoXtwo = twoXtwo;
    }

    void setDayAndNight(boolean dayAndNight) {
        this.dayAndNight = dayAndNight;
    }

    void setAmoeba(boolean amoeba) {
        this.amoeba = amoeba;
    }

    void setDiamoeba(boolean diamoeba) {
        this.diamoeba = diamoeba;
    }

    void setThe34(boolean the34) {
        this.the34 = the34;
    }

    void setLongLife(boolean longLife) {
        this.longLife = longLife;
    }

    void setStains(boolean stains) {
        this.stains = stains;
    }

    void setGnarl(boolean gnarl) {
        this.gnarl = gnarl;
    }

    void setMystery(boolean mystery) {
        this.mystery = mystery;
    }

    void setFlakes(boolean flakes) {
        this.flakes = flakes;
    }

    void setSpiralGrowth(boolean spiralGrowth) {
        this.spiralGrowth = spiralGrowth;
    }

    //this method is called when user is changing rule to another
    void resetRules() {
        setConwayRules(false);
        setLabirynth(false);
        setSeeds(false);
        setCoral(false);
        setHighLife(false);
        setReplicator(false);
        setAssimilation(false);
        setWalledCities(false);
        setCoagulations(false);
        setTwoXtwo(false);
        setDayAndNight(false);
        setAmoeba(false);
        setDiamoeba(false);
        setThe34(false);
        setLongLife(false);
        setStains(false);
        setGnarl(false);
        setMystery(false);
    }


    //get last clicked x coordinate
    int getxClick() {
        return xClick;
    }

    //get last clicked y coordinate
    int getyClick() {
        return yClick;
    }


    ArrayList<Point> getCell() {
        return cell;
    }

    //this method is connected to speed slider item
    void setANIMATION_SPEED(int ANIMATION_SPEED) {
        this.ANIMATION_SPEED = ANIMATION_SPEED;
    }

    public Color getCellColor() {
        return cellColor;
    }

    void setCellColor(Color cellColor) {
        this.cellColor = cellColor;
        requestLayout();
    }

    public int getCELL_SIZE() {
        return CELL_SIZE;
    }

    //this method is connected to size slider item
    void setCELL_SIZE(int CELL_SIZE) {
        this.CELL_SIZE = CELL_SIZE;
    }

    public AnimationTimer getTimer() {
        return timer;
    }

    public void startAnimation(AnimationTimer timer) {
        timer.start();
    }
}

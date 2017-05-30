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

class SimulationScene extends Pane {
    private ArrayList<Point> cells = new ArrayList<Point>(0); //this array is representing all alive cells on board

    private int gameSceneWidth;
    private int gameSceneHeight;

    private int cellSize = 10; //size of one cells
    private int animationSpeed = 20; //speed of animation

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
            stains, gnarl, mystery, flakes, spiralGrowth,
            serviettes;

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


    SimulationScene() {
        canvas.setStyle("-fx-background-color: #222222;");
        //set width and height perfectly fit on scene even with user resize operation
        gameSceneWidth = gameSceneWidth - (gameSceneWidth % cellSize);
        gameSceneHeight = gameSceneHeight - (gameSceneHeight % cellSize);

        cellColor = Color.web("#1a3399");
        conwayRules = true; //initially set conway rules
        devmode = false;

        xClick = 1;
        yClick = 1;

        //setup labels indicating generation and alive cells numbers
        generationLabel = new Label("Generation: 0");
        getChildren().add(generationLabel);

        aliveCellsOnBoard = new Label("Alive cells: " + cells.size());
        getChildren().add(aliveCellsOnBoard);
        getChildren().add(canvas); //add canvas to pane

        timer = new AnimationTimer() {
            private long lastUpdateTime = 0;

            @Override
            public void handle(long timestamp) {
                if (timestamp - lastUpdateTime >= animationSpeed * 1_000_000) {
                    updateScene();
                    lastUpdateTime = timestamp;
                }
            }
        };

        //create listeners of resize operation
        widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
            gameSceneWidth = newSceneWidth.intValue();
            gameSceneWidth = gameSceneWidth - (gameSceneWidth % cellSize + 1);
            updateArraySize();
        });

        heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
            gameSceneHeight = newSceneHeight.intValue();
            gameSceneHeight = gameSceneHeight - (gameSceneHeight % cellSize + 1) - 10;
            updateArraySize();

        });

        //create click listener - on click add alive cells
        addEventHandler(MouseEvent.MOUSE_CLICKED, this::addCell);

        //create drag listerer - as above
        addEventHandler(MouseEvent.MOUSE_DRAGGED, this::addCell);
    }

    private void updateScene() {
        //create boolean 2D array representing grid
        //this array is created to capture better performance
        boolean[][] cellsBoard = new boolean[gameSceneWidth / cellSize + 2][gameSceneHeight / cellSize + 2];
        //  this for iterates through cells list
        // if there are cells - sets their position in bool array
        try {
            for (int i = 0; i < cells.size(); i++) {
                cellsBoard[cells.get(i).x + 1][cells.get(i).y + 1] = true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Out of bounds");
        } catch (NullPointerException e) {
            System.out.println("Nullpointer");
        }

        //create array that contains points wchich are added in next generation
        ArrayList<Point> nextGeneration = new ArrayList<>(0);

        //nested for loop iterating through all board
        for (int i = 1; i < cellsBoard.length - 1; i++) {
            for (int j = 1; j < cellsBoard[0].length - 1; j++) {
                int neighbours = 0; //variable contains number of neighbours of the cells
                // System.out.println("(" + i + "," + j + ") " + " XXXX " + cellsBoard[i][j]);
                //checking every cells in the Moore neighbourhood
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
                        if (neighbours == 2 || neighbours == 3 || (neighbours >= 5 && neighbours <= 8)) {
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
                        if (neighbours == 3 || neighbours == 4 || (neighbours >= 6 && neighbours <= 8)) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    } else {
                        if (neighbours == 3 || (neighbours >= 6 && neighbours <= 8)) {
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
                        if (neighbours == 3 || (neighbours >= 5 && neighbours <= 8)) {
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
                        if (neighbours == 2 || neighbours == 3 || (neighbours >= 5 && neighbours <= 8)) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    } else {
                        if (neighbours == 3 || (neighbours >= 6 && neighbours <= 8)) {
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
                        if (neighbours == 0 || (neighbours >= 5 && neighbours <= 8)) {
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
                        if (neighbours == 1 || (neighbours >= 5 && neighbours <= 8)) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    } else {
                        if (neighbours == 8 || (neighbours >= 3 && neighbours <= 6)) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    }
                } else if (serviettes) {
                    if (!cellsBoard[i][j]) {
                        if (neighbours >= 2 && neighbours <= 4) {
                            nextGeneration.add(new Point(i - 1, j - 1));
                        }
                    }
                }
            }
        }

        try {
            evolutionNum++; //increment generation variable
            resetBoard(); //clear existing cells
            cells.addAll(nextGeneration); //add next generation to main cells list
            requestLayout(); //show it on screen
        } catch (StackOverflowError e) {
            System.out.println("Overflow error");
        }
    }

    private void drawCanvasFrame() {
        gc.strokeLine(cellSize,
                (canvas.getHeight()),
                canvas.getWidth(),
                canvas.getHeight());

        gc.strokeLine(canvas.getWidth(),
                (cellSize),
                canvas.getWidth(),
                canvas.getHeight());
    }

    private void drawGrid() {
         /*this block is responsible for painting grid,
         grid is drawn regardless of cells*/
        try {
            //create lines horizontally
            for (int i = 0; i <= gameSceneWidth / cellSize; i++) {
                gc.strokeLine(((i * cellSize) + cellSize),
                        cellSize,
                        (i * cellSize) + cellSize,
                        cellSize + (cellSize * gameSceneHeight / cellSize));
            }

            //create lines vertically
            for (int i = 0; i <= gameSceneHeight / cellSize; i++) {
                gc.strokeLine(cellSize,
                        ((i * cellSize) + cellSize),
                        cellSize * (gameSceneWidth / cellSize),
                        ((i * cellSize) + cellSize));
            }
            drawCanvasFrame();
        } catch (NullPointerException ignored) {
        }
    }

    private void drawCells() {
        try {
            //iterate through cells array and set its color, size and shape
            for (Point newPoint : cells) {
                gc.setFill(cellColor);
                gc.fillRect(cellSize + (cellSize * newPoint.x),
                        cellSize + (cellSize * newPoint.y),
                        cellSize,
                        cellSize);
            }
        } catch (ConcurrentModificationException | NullPointerException ignored) {
        }
    }


    //this method is responsible for refreshing view, on every next generation this method is called
    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        canvas.setWidth(gameSceneWidth - cellSize + 1);
        canvas.setHeight(gameSceneHeight - cellSize + 1);

        aliveCellsOnBoard.setLayoutY(gameSceneHeight - 3); // position of alive cells label
        generationLabel.setLayoutY(gameSceneHeight - 3);
        aliveCellsOnBoard.setLayoutX(cellSize); // position of alive cells label
        generationLabel.setLayoutX(160);
        generationLabel.setText("Generation: " + String.valueOf(evolutionNum));
        aliveCellsOnBoard.setText("Alive cells: " + cells.size());

        gc.clearRect(0, 0, getWidth(), getHeight()); //clear canvas after each generation
        gc.setLineWidth(0.2);

        drawGrid();
        drawCells();
    }

    //on call add cells to a cells list
    private void addCell(int x, int y) {
        if (!cells.contains(new Point(x, y))) {
            cells.add(new Point(x, y));
        }
        requestLayout();
    }

    //mouse click indicates values of x and y
    private void addCell(MouseEvent me) {
        int x = (int) me.getX() / cellSize - 1;
        int y = (int) me.getY() / cellSize - 1;
        xClick = x;
        yClick = y;
        if ((x >= 0) && (x < gameSceneWidth) && (y >= 0) && (y < gameSceneHeight)) {
            addCell(x, y);
        }

        if (devmode)
            System.out.println("aliveCells.add(new Point(x + " + x + ", y + " + y + "));");
    }

    //this method is called when user is resizing window, if there were alive
    // cells on position which is not existing after resize - delete that cells
    private void updateArraySize() {
        ArrayList<Point> removeList = new ArrayList<>(0);

        for (Point current : cells) {
            if ((current.x > gameSceneWidth / cellSize - 3) || (current.y > gameSceneHeight / cellSize - 3)) {
                removeList.add(current);
            }
        }
        cells.removeAll(removeList);
        requestLayout();
    }

    //clear alive cells - remove all points from list
    private void resetBoard() {
        cells.clear();
    }

    //this method is called when user clicks on reset menu item
    void resetAll() {
        cells.clear(); //clears layout
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

    void setServiettes(boolean serviettes) {
        this.serviettes = serviettes;
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
        setServiettes(false);
    }


    //get last clicked x coordinate
    int getxClick() {
        return xClick;
    }

    //get last clicked y coordinate
    int getyClick() {
        return yClick;
    }


    ArrayList<Point> getCells() {
        return cells;
    }

    //this method is connected to speed slider item
    void setAnimationSpeed(int animationSpeed) {
        this.animationSpeed = animationSpeed;
    }

    Color getCellColor() {
        return cellColor;
    }

    void setCellColor(Color cellColor) {
        this.cellColor = cellColor;
        requestLayout();
    }

    //this method is connected to size slider item
    void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

    AnimationTimer getTimer() {
        return timer;
    }

    void startAnimation(AnimationTimer timer) {
        timer.start();
    }
}

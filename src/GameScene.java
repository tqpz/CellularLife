import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;


/**
 * Created by Mateusz on 11.02.2017.
 */
public class GameScene extends Pane implements Runnable {
    private ArrayList<Point> cell = new ArrayList<Point>(0);
    private int gameSceneWidth;
    private int gameSceneHeight;

    private int CELL_SIZE = 10;

    private int ANIMATION_SPEED = 20;
    private Canvas canvas = new Canvas(gameSceneWidth, gameSceneHeight);

    private boolean conwayRules, labirynth, seeds,
            coral, highLife, replicator,
            assimilation, walledCities,
            coagulations, twoXtwo, dayAndNight,
            amoeba, diamoeba, the34, longLife,
            stains, gnarl, mystery, flakes;

    private boolean[] rules = {conwayRules, labirynth, seeds,
            coral, highLife, replicator,
            assimilation, walledCities,
            coagulations, twoXtwo, dayAndNight,
            amoeba, diamoeba, the34, longLife,
            stains, gnarl, mystery, flakes};

//    private int s_0, s_1, s_2, s_3, s_4, s_5, s_6, s_7, s_8;
//    private int b_0, b_1, b_2, b_3, b_4, b_5, b_6, b_7, b_8;

    private Label generationLabel;
    private int evolutionNum = 0;

    private Label aliveCellsOnBoard;

    public GameScene() {
        conwayRules = true;


        gameSceneWidth = gameSceneWidth - (gameSceneWidth % CELL_SIZE);
        gameSceneHeight = gameSceneHeight - (gameSceneHeight % CELL_SIZE);

        generationLabel = new Label("Number of generation: " + evolutionNum);
        getChildren().add(generationLabel);

        aliveCellsOnBoard = new Label("Number of alive cells: " + cell.size());
        aliveCellsOnBoard.setLayoutX(160);
        getChildren().add(aliveCellsOnBoard);
        getChildren().add(canvas);

        widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                gameSceneWidth = newSceneWidth.intValue();
                gameSceneWidth = gameSceneWidth - (gameSceneWidth % CELL_SIZE + 1);
                updateArraySize();
            }
        });

        heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                gameSceneHeight = newSceneHeight.intValue();
                gameSceneHeight = gameSceneHeight - (gameSceneHeight % CELL_SIZE + 1);
                updateArraySize();

            }
        });

        addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                addPoint(event);
            }
        });

        addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                addPoint(event);
            }
        });
    }


    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        canvas.setWidth(gameSceneWidth - CELL_SIZE + 1);
        canvas.setHeight(gameSceneHeight - CELL_SIZE + 1);

        generationLabel.setText("Number of generation: " + String.valueOf(evolutionNum));
        aliveCellsOnBoard.setText("Number of alive cells: " + cell.size());

        gc.clearRect(0, 0, getWidth(), getHeight());
        gc.setLineWidth(0.2);

        try {
            for (Point newPoint : cell) {
                gc.setFill(Color.FIREBRICK);
                gc.fillRect(CELL_SIZE + (CELL_SIZE * newPoint.x),
                        CELL_SIZE + (CELL_SIZE * newPoint.y),
                        CELL_SIZE,
                        CELL_SIZE);
            }
        } catch (ConcurrentModificationException e) {
        } catch (NullPointerException e) {
        }

        try {
            for (int i = 0; i <= gameSceneWidth / CELL_SIZE; i++) {
                gc.strokeLine(((i * CELL_SIZE) + CELL_SIZE),
                        CELL_SIZE,
                        (i * CELL_SIZE) + CELL_SIZE,
                        CELL_SIZE + (CELL_SIZE * gameSceneHeight / CELL_SIZE));
            }

            for (int i = 0; i <= gameSceneHeight / CELL_SIZE; i++) {
                gc.strokeLine(CELL_SIZE,
                        ((i * CELL_SIZE) + CELL_SIZE),
                        CELL_SIZE * (gameSceneWidth / CELL_SIZE),
                        ((i * CELL_SIZE) + CELL_SIZE));
            }
        } catch (NullPointerException e) {
        }
    }


    public void addPoint(int x, int y) {
        if (!cell.contains(new Point(x, y))) {
            cell.add(new Point(x, y));
        }
        requestLayout();
    }

    public void addPoint(MouseEvent me) {
        int x = (int) me.getX() / CELL_SIZE - 1;
        int y = (int) me.getY() / CELL_SIZE - 1;
        if ((x >= 0) && (x < gameSceneWidth) && (y >= 0) && (y < gameSceneHeight)) {
            addPoint(x, y);
        }
    }

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

    private void resetBoard() {
        cell.clear();
    }

    public void resetAll() {
        cell.clear();
        evolutionNum = 0;
        requestLayout();
    }


    public void setConwayRules(boolean conwayRules) {
        this.conwayRules = conwayRules;
    }

    public void setLabirynth(boolean labirynth) {
        this.labirynth = labirynth;
    }

    public void setSeeds(boolean seeds) {
        this.seeds = seeds;
    }

    public void setCoral(boolean coral) {
        this.coral = coral;
    }

    public void setHighLife(boolean highLife) {
        this.highLife = highLife;
    }

    public void setReplicator(boolean replicator) {
        this.replicator = replicator;
    }

    public void setAssimilation(boolean assimilation) {
        this.assimilation = assimilation;
    }

    public void setWalledCities(boolean walledCities) {
        this.walledCities = walledCities;
    }

    public void setCoagulations(boolean coagulations) {
        this.coagulations = coagulations;
    }

    public void setTwoXtwo(boolean twoXtwo) {
        this.twoXtwo = twoXtwo;
    }

    public void setDayAndNight(boolean dayAndNight) {
        this.dayAndNight = dayAndNight;
    }

    public void setAmoeba(boolean amoeba) {
        this.amoeba = amoeba;
    }

    public void setDiamoeba(boolean diamoeba) {
        this.diamoeba = diamoeba;
    }

    public void setThe34(boolean the34) {
        this.the34 = the34;
    }

    public void setLongLife(boolean longLife) {
        this.longLife = longLife;
    }

    public void setStains(boolean stains) {
        this.stains = stains;
    }

    public void setGnarl(boolean gnarl) {
        this.gnarl = gnarl;
    }

    public void setMystery(boolean mystery) {
        this.mystery = mystery;
    }

    public void setFlakes(boolean flakes) {
        this.flakes = flakes;
    }

    public void resetRules() {
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


    public void setANIMATION_SPEED(int ANIMATION_SPEED) {
        this.ANIMATION_SPEED = ANIMATION_SPEED;
    }


    public void setCELL_SIZE(int CELL_SIZE) {
        this.CELL_SIZE = CELL_SIZE;
    }

    @Override
    public void run() {
        while (true) {
            boolean[][] cellsBoard = new boolean[gameSceneWidth / CELL_SIZE + 2][gameSceneHeight / CELL_SIZE + 2];
            try {
                for (int i = 0; i < cell.size(); i++) {
                    cellsBoard[cell.get(i).x + 1][cell.get(i).y + 1] = true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Out of bounds at thread start");
            } catch (NullPointerException e) {
                System.out.println("Nullpointer at thread start");
            }

            ArrayList<Point> nextGeneration = new ArrayList<Point>(0);

            for (int i = 1; i < cellsBoard.length - 1; i++) {
                for (int j = 1; j < cellsBoard[0].length - 1; j++) {
                    int neighbours = 0;
                    if (cellsBoard[i - 1][j - 1]) neighbours++;
                    if (cellsBoard[i][j - 1]) neighbours++;
                    if (cellsBoard[i + 1][j - 1]) neighbours++;
                    if (cellsBoard[i - 1][j]) neighbours++;
                    if (cellsBoard[i + 1][j]) neighbours++;
                    if (cellsBoard[i - 1][j + 1]) neighbours++;
                    if (cellsBoard[i][j + 1]) neighbours++;
                    if (cellsBoard[i + 1][j + 1]) neighbours++;

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
                    }
                }
            }

            try {
                evolutionNum++;
                resetBoard();
                cell.addAll(nextGeneration);
                requestLayout();
                Thread.sleep(1000 / (ANIMATION_SPEED + 1));

            } catch (InterruptedException e) {

            } catch (StackOverflowError e) {

                System.out.println("Overflow error");
            }
        }
    }
}

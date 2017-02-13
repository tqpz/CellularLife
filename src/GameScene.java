import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;

import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.input.MouseEvent;
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
    private int CELL_SIZE = GameOfLife.getCellSize();
    private int ANIMATION_SPEED = 20;
    private Canvas canvas = new Canvas(gameSceneWidth, gameSceneHeight);

    private Label generationLabel;
    private int evolutionNum = 0;


    public GameScene() {


        gameSceneWidth = gameSceneWidth - (gameSceneWidth % CELL_SIZE);
        gameSceneHeight = gameSceneHeight - (gameSceneHeight % CELL_SIZE);

        generationLabel = new Label("Number of generation: " + evolutionNum);
        getChildren().add(generationLabel);

        getChildren().add(canvas);

        widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                gameSceneWidth = newSceneWidth.intValue();
                gameSceneWidth = gameSceneWidth - (gameSceneWidth % CELL_SIZE+1);
                updateArraySize();
            }
        });

        heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                gameSceneHeight = newSceneHeight.intValue();
                gameSceneHeight = gameSceneHeight - (gameSceneHeight % CELL_SIZE+1);
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
        canvas.setWidth(gameSceneWidth - CELL_SIZE+1);
        canvas.setHeight(gameSceneHeight - CELL_SIZE+1);

        generationLabel.setText("Number of generation: " + String.valueOf(evolutionNum));

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
        } catch (NullPointerException e ){
        }

        try {
            for (int i = 0; i <= gameSceneWidth/CELL_SIZE; i++) {
                gc.strokeLine(((i * CELL_SIZE) + CELL_SIZE),
                        CELL_SIZE,
                        (i * CELL_SIZE) + CELL_SIZE,
                        CELL_SIZE + (CELL_SIZE * gameSceneHeight/CELL_SIZE));
            }

            for (int i = 0; i <= gameSceneHeight/CELL_SIZE; i++) {
                gc.strokeLine(CELL_SIZE,
                        ((i * CELL_SIZE) + CELL_SIZE),
                        CELL_SIZE * (gameSceneWidth/CELL_SIZE),
                        ((i * CELL_SIZE) + CELL_SIZE));
            }
        } catch (NullPointerException e) {}
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
            if ((current.x > gameSceneWidth/CELL_SIZE-3) || (current.y > gameSceneHeight/CELL_SIZE-3)) {
                removeList.add(current);
            }
        }
        cell.removeAll(removeList);
        requestLayout();
    }

    private void resetBoard(){
        cell.clear();
    }

    public void resetAll(){
        cell.clear();
        evolutionNum = 0;
        requestLayout();
    }

    @Override
    public void run() {

        boolean[][] cellsBoard = new boolean[gameSceneWidth/CELL_SIZE+25][gameSceneHeight/CELL_SIZE+25];
        try {
            for (Point current : cell) {
                cellsBoard[current.x + 1][current.y + 1] = true;
            }
        }catch (ConcurrentModificationException e) {
            System.out.println("Concurrent at thread start");
        }catch (NullPointerException e){
            System.out.println("Nullpointer at thread start");
        };

        ArrayList<Point> nextGeneration = new ArrayList<Point>(0);

        for(int i = 1; i < cellsBoard.length-1; i++){
            for(int j = 1; j < cellsBoard[0].length-1; j++){
                int neighbours = 0;
                if(cellsBoard[i-1][j-1]) neighbours++;
                if(cellsBoard[i][j-1]) neighbours++;
                if(cellsBoard[i+1][j-1]) neighbours++;
                if(cellsBoard[i-1][j]) neighbours++;
                if(cellsBoard[i+1][j]) neighbours++;
                if(cellsBoard[i-1][j+1]) neighbours++;
                if(cellsBoard[i][j+1]) neighbours++;
                if(cellsBoard[i+1][j+1]) neighbours++;
                if(cellsBoard[i][j]) {
                    if ((neighbours == 2) || (neighbours == 3)) {
                        nextGeneration.add(new Point(i - 1, j - 1));
                    }
                }else{
                    if(neighbours == 3){
                        nextGeneration.add(new Point(i - 1, j- 1));
                    }
                }
            }
        }

        try {
            evolutionNum++;
            resetBoard();
            cell.addAll(nextGeneration);
            requestLayout();
            Thread.sleep(1000 / ANIMATION_SPEED);
            run();

        } catch (InterruptedException e) {

        } catch (StackOverflowError e){

            System.out.println("Overflow error");
        }
    }
}

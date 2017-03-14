import javafx.scene.layout.Pane;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Mateusz on 14.03.2017.
 */
public class Structure {
    private Pane scene;
    private ArrayList<Point> aliveCells = new ArrayList<Point>(0);

    public Structure(Pane scene, ArrayList<Point> aliveCells){
        this.scene = scene;
        this.aliveCells = aliveCells;
    }

    public void glider(int x, int y) {
        aliveCells.add(new Point(x, y - 1));
        aliveCells.add(new Point(x + 1, y));
        aliveCells.add(new Point(x - 1, y + 1));
        aliveCells.add(new Point(x, y + 1));
        aliveCells.add(new Point(x + 1, y + 1));
        scene.requestLayout();
    }

    public void acorn(int x, int y) {
        aliveCells.add(new Point(x - 1, y - 1));
        aliveCells.add(new Point(x + 1, y));
        aliveCells.add(new Point(x - 2, y + 1));
        aliveCells.add(new Point(x - 1, y + 1));
        aliveCells.add(new Point(x + 2, y + 1));
        aliveCells.add(new Point(x + 3, y + 1));
        aliveCells.add(new Point(x + 4, y + 1));
        scene.requestLayout();
    }

    public void Rpentomino(int x, int y) {
        aliveCells.add(new Point(x, y - 1));
        aliveCells.add(new Point(x + 1, y - 1));
        aliveCells.add(new Point(x - 1, y));
        aliveCells.add(new Point(x, y));
        aliveCells.add(new Point(x, y + 1));
        scene.requestLayout();
    }


    public void lightweightSpaceship(int x, int y) {
        aliveCells.add(new Point(x + 1, y - 1));
        aliveCells.add(new Point(x + 1, y));
        aliveCells.add(new Point(x + 1, y + 1));
        aliveCells.add(new Point(x, y + 1));
        aliveCells.add(new Point(x - 1, y + 1));
        aliveCells.add(new Point(x - 2, y + 1));
        aliveCells.add(new Point(x - 3, y));
        aliveCells.add(new Point(x - 3, y - 2));
        aliveCells.add(new Point(x, y - 2));
        scene.requestLayout();
    }

    public void gliderGun(int x, int y) {
        aliveCells.add(new Point(x, y));
        aliveCells.add(new Point(x + 1, y));
        aliveCells.add(new Point(x + 1, y + 1));
        aliveCells.add(new Point(x, y + 1));
        aliveCells.add(new Point(x + 10, y));
        aliveCells.add(new Point(x + 10, y + 1));
        aliveCells.add(new Point(x + 10, y + 2));
        aliveCells.add(new Point(x + 11, y - 1));
        aliveCells.add(new Point(x + 11, y + 3));
        aliveCells.add(new Point(x + 12, y - 2));
        aliveCells.add(new Point(x + 13, y - 2));
        aliveCells.add(new Point(x + 12, y + 4));
        aliveCells.add(new Point(x + 13, y + 4));
        aliveCells.add(new Point(x + 14, y + 1));
        aliveCells.add(new Point(x + 15, y - 1));
        aliveCells.add(new Point(x + 15, y + 3));
        aliveCells.add(new Point(x + 16, y));
        aliveCells.add(new Point(x + 16, y + 1));
        aliveCells.add(new Point(x + 16, y + 2));
        aliveCells.add(new Point(x + 17, y + 1));
        aliveCells.add(new Point(x + 20, y));
        aliveCells.add(new Point(x + 21, y));
        aliveCells.add(new Point(x + 21, y - 1));
        aliveCells.add(new Point(x + 20, y - 1));
        aliveCells.add(new Point(x + 20, y - 2));
        aliveCells.add(new Point(x + 21, y - 2));
        aliveCells.add(new Point(x + 22, y - 3));
        aliveCells.add(new Point(x + 22, y + 1));
        aliveCells.add(new Point(x + 24, y - 3));
        aliveCells.add(new Point(x + 24, y - 4));
        aliveCells.add(new Point(x + 24, y + 1));
        aliveCells.add(new Point(x + 24, y + 2));
        aliveCells.add(new Point(x + 34, y - 2));
        aliveCells.add(new Point(x + 34, y - 1));
        aliveCells.add(new Point(x + 35, y - 1));
        aliveCells.add(new Point(x + 35, y - 2));
        scene.requestLayout();
    }

    public void pulsar(int x, int y) {
        aliveCells.add(new Point(x, y + 2));
        aliveCells.add(new Point(x, y + 3));
        aliveCells.add(new Point(x, y + 4));
        aliveCells.add(new Point(x + 2, y + 5));
        aliveCells.add(new Point(x + 3, y + 5));
        aliveCells.add(new Point(x + 4, y + 5));
        aliveCells.add(new Point(x + 5, y + 4));
        aliveCells.add(new Point(x + 5, y + 3));
        aliveCells.add(new Point(x + 5, y + 2));
        aliveCells.add(new Point(x + 4, y));
        aliveCells.add(new Point(x + 3, y));
        aliveCells.add(new Point(x + 2, y));
        aliveCells.add(new Point(x + 2, y + 7));
        aliveCells.add(new Point(x + 3, y + 7));
        aliveCells.add(new Point(x + 4, y + 7));
        aliveCells.add(new Point(x + 5, y + 8));
        aliveCells.add(new Point(x + 5, y + 9));
        aliveCells.add(new Point(x + 5, y + 10));
        aliveCells.add(new Point(x + 7, y + 2));
        aliveCells.add(new Point(x + 7, y + 3));
        aliveCells.add(new Point(x + 7, y + 4));
        aliveCells.add(new Point(x + 8, y + 5));
        aliveCells.add(new Point(x + 9, y + 5));
        aliveCells.add(new Point(x + 10, y + 5));
        aliveCells.add(new Point(x + 8, y + 7));
        aliveCells.add(new Point(x + 9, y + 7));
        aliveCells.add(new Point(x + 10, y + 7));
        aliveCells.add(new Point(x + 8, y + 7));
        aliveCells.add(new Point(x + 7, y + 8));
        aliveCells.add(new Point(x + 7, y + 9));
        aliveCells.add(new Point(x + 7, y + 10));
        aliveCells.add(new Point(x + 8, y));
        aliveCells.add(new Point(x + 9, y));
        aliveCells.add(new Point(x + 10, y));
        aliveCells.add(new Point(x + 12, y + 4));
        aliveCells.add(new Point(x + 12, y + 3));
        aliveCells.add(new Point(x + 12, y + 2));
        aliveCells.add(new Point(x + 12, y + 8));
        aliveCells.add(new Point(x + 12, y + 9));
        aliveCells.add(new Point(x + 12, y + 10));
        aliveCells.add(new Point(x + 8, y + 12));
        aliveCells.add(new Point(x + 9, y + 12));
        aliveCells.add(new Point(x + 10, y + 12));
        aliveCells.add(new Point(x + 4, y + 12));
        aliveCells.add(new Point(x + 3, y + 12));
        aliveCells.add(new Point(x + 2, y + 12));
        aliveCells.add(new Point(x, y + 8));
        aliveCells.add(new Point(x, y + 9));
        aliveCells.add(new Point(x, y + 10));
        scene.requestLayout();
    }

    public void dart(int x, int y){
        aliveCells.add(new Point(x + 0, y + 1));
        aliveCells.add(new Point(x + 0, y + 3));
        aliveCells.add(new Point(x + 0, y + 4));
        aliveCells.add(new Point(x + 0, y + 6));
        aliveCells.add(new Point(x + 1, y + 6));
        aliveCells.add(new Point(x + 2, y + 6));
        aliveCells.add(new Point(x + 3, y + 6));
        aliveCells.add(new Point(x + 4, y + 5));
        aliveCells.add(new Point(x + 4, y + 4));
        aliveCells.add(new Point(x + 3, y + 2));
        aliveCells.add(new Point(x + 2, y + 2));
        aliveCells.add(new Point(x + 2, y + 1));
        aliveCells.add(new Point(x + 1, y + 0));
        aliveCells.add(new Point(x + 0, y + 8));
        aliveCells.add(new Point(x + 1, y + 8));
        aliveCells.add(new Point(x + 2, y + 8));
        aliveCells.add(new Point(x + 3, y + 8));
        aliveCells.add(new Point(x + 4, y + 9));
        aliveCells.add(new Point(x + 4, y + 10));
        aliveCells.add(new Point(x + 0, y + 10));
        aliveCells.add(new Point(x + 0, y + 11));
        aliveCells.add(new Point(x + 0, y + 13));
        aliveCells.add(new Point(x + 1, y + 14));
        aliveCells.add(new Point(x + 2, y + 13));
        aliveCells.add(new Point(x + 2, y + 12));
        aliveCells.add(new Point(x + 3, y + 12));
        aliveCells.add(new Point(x + 6, y + 6));
        aliveCells.add(new Point(x + 6, y + 7));
        aliveCells.add(new Point(x + 6, y + 8));
        aliveCells.add(new Point(x + 7, y + 5));
        aliveCells.add(new Point(x + 7, y + 9));
        aliveCells.add(new Point(x + 8, y + 6));
        aliveCells.add(new Point(x + 8, y + 8));
        aliveCells.add(new Point(x + 9, y + 7));
        scene.requestLayout();
    }

    public void puffer1(int x, int y){
        aliveCells.add(new Point(x + 0, y + 2));
        aliveCells.add(new Point(x + 0, y + 6));
        aliveCells.add(new Point(x + 0, y + 7));
        aliveCells.add(new Point(x + 1, y + 3));
        aliveCells.add(new Point(x + 2, y + 3));
        aliveCells.add(new Point(x + 3, y + 3));
        aliveCells.add(new Point(x + 4, y + 3));
        aliveCells.add(new Point(x + 5, y + 3));
        aliveCells.add(new Point(x + 6, y + 3));
        aliveCells.add(new Point(x + 6, y + 2));
        aliveCells.add(new Point(x + 6, y + 1));
        aliveCells.add(new Point(x + 5, y + 0));
        aliveCells.add(new Point(x + 1, y + 6));
        aliveCells.add(new Point(x + 1, y + 7));
        aliveCells.add(new Point(x + 2, y + 6));
        aliveCells.add(new Point(x + 4, y + 8));
        aliveCells.add(new Point(x + 4, y + 9));
        aliveCells.add(new Point(x + 5, y + 9));
        aliveCells.add(new Point(x + 5, y + 10));
        aliveCells.add(new Point(x + 5, y + 11));
        aliveCells.add(new Point(x + 4, y + 11));
        aliveCells.add(new Point(x + 6, y + 10));
        aliveCells.add(new Point(x + 4, y + 15));
        aliveCells.add(new Point(x + 5, y + 15));
        aliveCells.add(new Point(x + 5, y + 16));
        aliveCells.add(new Point(x + 6, y + 16));
        aliveCells.add(new Point(x + 5, y + 17));
        aliveCells.add(new Point(x + 4, y + 17));
        aliveCells.add(new Point(x + 4, y + 18));
        aliveCells.add(new Point(x + 1, y + 19));
        aliveCells.add(new Point(x + 0, y + 19));
        aliveCells.add(new Point(x + 0, y + 20));
        aliveCells.add(new Point(x + 1, y + 20));
        aliveCells.add(new Point(x + 2, y + 20));
        aliveCells.add(new Point(x + 0, y + 24));
        aliveCells.add(new Point(x + 1, y + 23));
        aliveCells.add(new Point(x + 2, y + 23));
        aliveCells.add(new Point(x + 3, y + 23));
        aliveCells.add(new Point(x + 4, y + 23));
        aliveCells.add(new Point(x + 5, y + 23));
        aliveCells.add(new Point(x + 6, y + 23));
        aliveCells.add(new Point(x + 6, y + 24));
        aliveCells.add(new Point(x + 6, y + 25));
        aliveCells.add(new Point(x + 5, y + 26));
        scene.requestLayout();
    }

    public void loaf(int x, int y){
        aliveCells.add(new Point(x + 0, y + 1));
        aliveCells.add(new Point(x + 1, y + 2));
        aliveCells.add(new Point(x + 2, y + 3));
        aliveCells.add(new Point(x + 1, y + 0));
        aliveCells.add(new Point(x + 2, y + 0));
        aliveCells.add(new Point(x + 3, y + 1));
        aliveCells.add(new Point(x + 3, y + 2));
        scene.requestLayout();
    }

    public void pond(int x, int y){
        aliveCells.add(new Point(x + 0, y + 1));
        aliveCells.add(new Point(x + 1, y + 0));
        aliveCells.add(new Point(x + 2, y + 0));
        aliveCells.add(new Point(x + 3, y + 1));
        aliveCells.add(new Point(x + 3, y + 2));
        aliveCells.add(new Point(x + 2, y + 3));
        aliveCells.add(new Point(x + 2, y + 3));
        aliveCells.add(new Point(x + 1, y + 3));
        aliveCells.add(new Point(x + 1, y + 3));
        aliveCells.add(new Point(x + 0, y + 2));
        scene.requestLayout();
    }

    public void elevener(int x, int y) {
        aliveCells.add(new Point(x + 4, y + 0));
        aliveCells.add(new Point(x + 5, y + 0));
        aliveCells.add(new Point(x + 5, y + 1));
        aliveCells.add(new Point(x + 3, y + 1));
        aliveCells.add(new Point(x + 3, y + 2));
        aliveCells.add(new Point(x + 3, y + 3));
        aliveCells.add(new Point(x + 2, y + 3));
        aliveCells.add(new Point(x + 2, y + 3));
        aliveCells.add(new Point(x + 1, y + 3));
        aliveCells.add(new Point(x + 1, y + 3));
        aliveCells.add(new Point(x + 0, y + 4));
        aliveCells.add(new Point(x + 0, y + 5));
        aliveCells.add(new Point(x + 1, y + 5));
        scene.requestLayout();
    }

    public void honeycomb(int x, int y) {
        aliveCells.add(new Point(x + 0, y + 2));
        aliveCells.add(new Point(x + 1, y + 1));
        aliveCells.add(new Point(x + 2, y + 0));
        aliveCells.add(new Point(x + 3, y + 0));
        aliveCells.add(new Point(x + 4, y + 1));
        aliveCells.add(new Point(x + 5, y + 2));
        aliveCells.add(new Point(x + 4, y + 3));
        aliveCells.add(new Point(x + 3, y + 4));
        aliveCells.add(new Point(x + 2, y + 4));
        aliveCells.add(new Point(x + 1, y + 3));
        aliveCells.add(new Point(x + 2, y + 2));
        aliveCells.add(new Point(x + 3, y + 2));
        aliveCells.add(new Point(x + 3, y + 2));
        scene.requestLayout();
    }

    public void paperclip(int x, int y) {
        aliveCells.add(new Point(x + 2, y + 0));
        aliveCells.add(new Point(x + 3, y + 0));
        aliveCells.add(new Point(x + 4, y + 1));
        aliveCells.add(new Point(x + 4, y + 2));
        aliveCells.add(new Point(x + 3, y + 2));
        aliveCells.add(new Point(x + 3, y + 3));
        aliveCells.add(new Point(x + 3, y + 4));
        aliveCells.add(new Point(x + 2, y + 5));
        aliveCells.add(new Point(x + 1, y + 5));
        aliveCells.add(new Point(x + 0, y + 4));
        aliveCells.add(new Point(x + 0, y + 3));
        aliveCells.add(new Point(x + 1, y + 3));
        aliveCells.add(new Point(x + 1, y + 2));
        aliveCells.add(new Point(x + 1, y + 1));
        scene.requestLayout();
    }

    public void moose(int x, int y) {
        aliveCells.add(new Point(x + 1, y + 0));
        aliveCells.add(new Point(x + 0, y + 0));
        aliveCells.add(new Point(x + 0, y + 1));
        aliveCells.add(new Point(x + 1, y + 2));
        aliveCells.add(new Point(x + 2, y + 2));
        aliveCells.add(new Point(x + 3, y + 2));
        aliveCells.add(new Point(x + 3, y + 2));
        aliveCells.add(new Point(x + 3, y + 3));
        aliveCells.add(new Point(x + 4, y + 4));
        aliveCells.add(new Point(x + 5, y + 3));
        aliveCells.add(new Point(x + 5, y + 2));
        aliveCells.add(new Point(x + 6, y + 2));
        aliveCells.add(new Point(x + 7, y + 2));
        aliveCells.add(new Point(x + 8, y + 1));
        aliveCells.add(new Point(x + 8, y + 0));
        aliveCells.add(new Point(x + 7, y + 0));
        scene.requestLayout();
    }

    public void eater2(int x, int y) {
        aliveCells.add(new Point(x + 3, y + 0));
        aliveCells.add(new Point(x + 3, y + 1));
        aliveCells.add(new Point(x + 2, y + 1));
        aliveCells.add(new Point(x + 1, y + 1));
        aliveCells.add(new Point(x + 0, y + 2));
        aliveCells.add(new Point(x + 1, y + 3));
        aliveCells.add(new Point(x + 2, y + 3));
        aliveCells.add(new Point(x + 3, y + 3));
        aliveCells.add(new Point(x + 3, y + 4));
        aliveCells.add(new Point(x + 3, y + 5));
        aliveCells.add(new Point(x + 4, y + 6));
        aliveCells.add(new Point(x + 5, y + 5));
        aliveCells.add(new Point(x + 5, y + 4));
        aliveCells.add(new Point(x + 5, y + 3));
        aliveCells.add(new Point(x + 6, y + 3));
        aliveCells.add(new Point(x + 5, y + 1));
        aliveCells.add(new Point(x + 6, y + 1));
        aliveCells.add(new Point(x + 6, y + 0));
        aliveCells.add(new Point(x + 5, y + 0));
        scene.requestLayout();
    }

    public void spiral(int x, int y) {
        aliveCells.add(new Point(x + 0, y + 0));
        aliveCells.add(new Point(x + 1, y + 0));
        aliveCells.add(new Point(x + 1, y + 1));
        aliveCells.add(new Point(x + 1, y + 2));
        aliveCells.add(new Point(x + 2, y + 3));
        aliveCells.add(new Point(x + 3, y + 2));
        aliveCells.add(new Point(x + 4, y + 3));
        aliveCells.add(new Point(x + 4, y + 3));
        aliveCells.add(new Point(x + 3, y + 4));
        aliveCells.add(new Point(x + 4, y + 1));
        aliveCells.add(new Point(x + 5, y + 1));
        aliveCells.add(new Point(x + 6, y + 1));
        aliveCells.add(new Point(x + 6, y + 0));
        aliveCells.add(new Point(x + 5, y + 4));
        aliveCells.add(new Point(x + 5, y + 5));
        aliveCells.add(new Point(x + 5, y + 6));
        aliveCells.add(new Point(x + 6, y + 6));
        aliveCells.add(new Point(x + 2, y + 5));
        aliveCells.add(new Point(x + 1, y + 5));
        aliveCells.add(new Point(x + 0, y + 5));
        aliveCells.add(new Point(x + 0, y + 6));
        scene.requestLayout();
    }
    public void lake2(int x, int y) {
        aliveCells.add(new Point(x + 4, y + 0));
        aliveCells.add(new Point(x + 5, y + 0));
        aliveCells.add(new Point(x + 5, y + 0));
        aliveCells.add(new Point(x + 3, y + 1));
        aliveCells.add(new Point(x + 3, y + 2));
        aliveCells.add(new Point(x + 2, y + 3));
        aliveCells.add(new Point(x + 1, y + 3));
        aliveCells.add(new Point(x + 1, y + 3));
        aliveCells.add(new Point(x + 0, y + 4));
        aliveCells.add(new Point(x + 0, y + 5));
        aliveCells.add(new Point(x + 1, y + 6));
        aliveCells.add(new Point(x + 2, y + 6));
        aliveCells.add(new Point(x + 3, y + 7));
        aliveCells.add(new Point(x + 3, y + 8));
        aliveCells.add(new Point(x + 4, y + 9));
        aliveCells.add(new Point(x + 5, y + 9));
        aliveCells.add(new Point(x + 6, y + 8));
        aliveCells.add(new Point(x + 6, y + 7));
        aliveCells.add(new Point(x + 7, y + 6));
        aliveCells.add(new Point(x + 8, y + 6));
        aliveCells.add(new Point(x + 9, y + 5));
        aliveCells.add(new Point(x + 9, y + 4));
        aliveCells.add(new Point(x + 8, y + 3));
        aliveCells.add(new Point(x + 7, y + 3));
        aliveCells.add(new Point(x + 6, y + 2));
        aliveCells.add(new Point(x + 6, y + 1));
        scene.requestLayout();
    }

    public void mickeyMouse(int x, int y) {
        aliveCells.add(new Point(x + 1, y + 0));
        aliveCells.add(new Point(x + 2, y + 0));
        aliveCells.add(new Point(x + 3, y + 1));
        aliveCells.add(new Point(x + 3, y + 2));
        aliveCells.add(new Point(x + 4, y + 2));
        aliveCells.add(new Point(x + 5, y + 2));
        aliveCells.add(new Point(x + 5, y + 2));
        aliveCells.add(new Point(x + 6, y + 2));
        aliveCells.add(new Point(x + 6, y + 1));
        aliveCells.add(new Point(x + 7, y + 0));
        aliveCells.add(new Point(x + 8, y + 0));
        aliveCells.add(new Point(x + 9, y + 1));
        aliveCells.add(new Point(x + 9, y + 2));
        aliveCells.add(new Point(x + 8, y + 3));
        aliveCells.add(new Point(x + 7, y + 3));
        aliveCells.add(new Point(x + 0, y + 1));
        aliveCells.add(new Point(x + 0, y + 2));
        aliveCells.add(new Point(x + 1, y + 3));
        aliveCells.add(new Point(x + 2, y + 3));
        aliveCells.add(new Point(x + 3, y + 4));
        aliveCells.add(new Point(x + 4, y + 4));
        aliveCells.add(new Point(x + 5, y + 4));
        aliveCells.add(new Point(x + 6, y + 4));
        aliveCells.add(new Point(x + 6, y + 5));
        aliveCells.add(new Point(x + 3, y + 5));
        aliveCells.add(new Point(x + 4, y + 6));
        aliveCells.add(new Point(x + 5, y + 6));
        scene.requestLayout();
    }

    public void pentadecathlon(int x, int y){
        aliveCells.add(new Point(x + 0, y + 1));
        aliveCells.add(new Point(x + 1, y + 1));
        aliveCells.add(new Point(x + 2, y + 0));
        aliveCells.add(new Point(x + 2, y + 2));
        aliveCells.add(new Point(x + 3, y + 1));
        aliveCells.add(new Point(x + 4, y + 1));
        aliveCells.add(new Point(x + 5, y + 1));
        aliveCells.add(new Point(x + 6, y + 1));
        aliveCells.add(new Point(x + 7, y + 0));
        aliveCells.add(new Point(x + 7, y + 0));
        aliveCells.add(new Point(x + 7, y + 0));
        aliveCells.add(new Point(x + 7, y + 2));
        aliveCells.add(new Point(x + 8, y + 1));
        aliveCells.add(new Point(x + 9, y + 1));
        scene.requestLayout();
    }
}

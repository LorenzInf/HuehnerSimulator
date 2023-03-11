package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;

public class Field extends GraphicalObject {

    private GameComponent[][] field;
    private int xMin, xMax, yMin, yMax;

    public Field(int x, int y){
        x = Math.min(x,20);
        y = Math.min(y,20);
        field = new GameComponent[x][y];
        xMin = (1000-(x*50))/2;
        xMax = (1000+(x*50))/2;
        yMin = (1000-(y*50))/2;
        yMax = (1000+(y*50))/2;
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(Color.BLACK);
        for(int i = 0; i*50+xMin < xMax; i++){
            for(int j = 0; j*50+yMin < yMax; j++){
                if(field[i][j] == null){
                    drawTool.drawRectangle(i*50+xMin,j*50+yMin,50,50);
                }else{
                    drawTool.drawImage(field[i][j].getImg(),i*50+xMin,j*50+yMin);
                }
            }
        }
    }

    public void addComponent(GameComponent g,int x,int y){
        if(field.length > x && field[x].length > y){
            field[x][y] = g;
        }
    }
}

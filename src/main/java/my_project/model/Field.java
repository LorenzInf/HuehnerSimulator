package my_project.model;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Field extends GraphicalObject {

    private GameComponent[][] field;
    private int xMin, xMax, yMin, yMax;
    private BufferedImage[] fieldImg;
    private BufferedImage background;
    private Chicken chicken;

    public Field(int x, int y, Chicken chicken){
        this.x = x;
        this.y = y;
        x = Math.min(x,20);
        y = Math.min(y,20);
        field = new GameComponent[x][y];
        xMin = (1000-(x*50))/2;
        xMax = (1000+(x*50))/2;
        yMin = (1000-(y*50))/2;
        yMax = (1000+(y*50))/2;
        fieldImg = new BufferedImage[]{
                createImage("src/main/resources/graphic/Feld.jpeg"),                //0
                createImage("src/main/resources/graphic/Feld-Oben-Links.jpeg"),     //1
                createImage("src/main/resources/graphic/Feld-Oben.jpeg"),           //2
                createImage("src/main/resources/graphic/Feld-Oben-Rechts.jpeg"),    //3
                createImage("src/main/resources/graphic/Feld-Rechts.jpeg"),         //4
                createImage("src/main/resources/graphic/Feld-Unten-Rechts.jpeg"),   //5
                createImage("src/main/resources/graphic/Feld-Unten.jpeg"),          //6
                createImage("src/main/resources/graphic/Feld-Unten-Links.jpeg"),    //7
                createImage("src/main/resources/graphic/Feld-Links.jpeg")           //8
        };
        background = createImage("src/main/resources/graphic/Hintergrund.png");
        this.chicken = chicken;
    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.drawImage(background,0,0);
        int pointerX = -1;
        int pointerY = -1;
        drawTool.setCurrentColor(Color.BLACK);
        for(int i = 0; i*50+xMin < xMax; i++){
            for(int j = 0; j*50+yMin < yMax; j++){
                if(i == 0 && j == 0){
                    drawTool.drawImage(fieldImg[1],i*50+xMin,j*50+yMin);
                }else if(i == x-1 && j == y-1){
                    drawTool.drawImage(fieldImg[5],i*50+xMin,j*50+yMin);
                }else if(i == 0 && j == y-1){
                    drawTool.drawImage(fieldImg[7],i*50+xMin,j*50+yMin);
                }else if(i == x-1 && j == 0){
                    drawTool.drawImage(fieldImg[3],i*50+xMin,j*50+yMin);
                }else if(i == 0){
                    drawTool.drawImage(fieldImg[8],i*50+xMin,j*50+yMin);
                }else if(i == x-1){
                    drawTool.drawImage(fieldImg[4],i*50+xMin,j*50+yMin);
                }else if(j == 0){
                    drawTool.drawImage(fieldImg[2],i*50+xMin,j*50+yMin);
                }else if(j == y-1){
                    drawTool.drawImage(fieldImg[6],i*50+xMin,j*50+yMin);
                }else{
                    drawTool.drawImage(fieldImg[0],i*50+xMin,j*50+yMin);
                }
                drawTool.drawRectangle(i*50+xMin,j*50+yMin,50,50);
                if(field[i][j] != null){
                    drawTool.drawImage(field[i][j].getImg(),i*50+xMin,j*50+yMin);
                    if(field[i][j] instanceof Chicken){
                        pointerX = (i*50+xMin +25)+chicken.getMovementToX()*50;
                        pointerY = (j*50+yMin +25)+chicken.getMovementToY()*50;

                    }
                }
            }
        }
        if(pointerX != -1){
            drawTool.setCurrentColor(Color.RED);
            drawTool.drawFilledCircle(pointerX,pointerY,5);
        }
    }

    public void addComponent(GameComponent g,int x,int y){
        if(field.length > x && field[x].length > y){
            field[x][y] = g;
        }
    }

    public void placeChickenTo(int x, int y){
        chicken.setX(x);
        chicken.setY(y);
        field[Math.min(x, field.length)][Math.min(y, field[x].length)] = chicken;
    }

    public void moveChicken(){
        int newX = Math.min( ((int) chicken.getX() + chicken.getMovementToX()), field.length - 1);
        int newY = Math.min( ((int) chicken.getY() + chicken.getMovementToY()), field[0].length - 1);
        if (!(field[newX][newY] instanceof Fence)) {
            field[chicken.getXPos()][chicken.getYPos()] = null;
            chicken.setX(newX);
            chicken.setY(newY);
            field[newX][newY] = chicken;
        }
    }


}

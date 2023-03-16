package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.model.*;
import java.awt.image.BufferedImage;

public class ProgramController extends GraphicalObject {

    private Field field;
    private BufferedImage background;
    private ViewController viewController;
    private Chicken chicken;

    public ProgramController(ViewController viewController){
        viewController.draw(this);
        background = createImage("src/main/resources/graphic/Hintergrund.png");
        this.viewController = viewController;
        chicken = new Chicken();
    }

    public void startProgram() {
        createField(15,15);
    }

    public void updateProgram(double dt){

    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.drawImage(background,0,0);
    }

    //Erzeugt ein Feld mit x zu y Kästchen. Maximal bis zu 20*20

    public void createField(int x, int y){
        field = new Field(x,y,chicken);
        viewController.draw(field);
        createFood(4,13);
        createFence(5,5);
        createFence(5,6);
        createFence(5,7);
        createFence(5,8);
        createFence(5,9);
        createChicken(3,5);
        field.moveChicken(4);
        turnRight();
        moveChicken(3);
    }

    public void createFence(int x, int y){
        field.addComponent(new Fence(),x,y);
    }

    public void createFood(int x, int y){
        field.addComponent(new Food(),x,y);
    }

    public void createChicken(int x, int y){
        field.placeChickenTo(x,y);
    }

    public void moveChicken(int amount){
        field.moveChicken(amount);
    }

    public void moveChicken(){
        field.moveChicken();
    }

    public void turnLeft(){
        chicken.turnLeft();
    }

    public void turnRight(){
        chicken.turnRight();
    }
}

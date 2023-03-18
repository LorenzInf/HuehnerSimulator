package my_project.control;

import KAGO_framework.control.ViewController;

import KAGO_framework.model.abitur.datenstrukturen.Queue;
import my_project.model.*;
import my_project.view.InputField;

import javax.swing.*;
import java.awt.*;

public class ProgramController{

    private Field field;
    private ViewController viewController;
    private Chicken chicken;
    private InputField inputField;
    private Thread thread;

    public ProgramController(ViewController viewController){
        this.viewController = viewController;
        chicken = new Chicken();
    }

    public void startProgram() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        int x = width / 2;
        int y = height / 2;
        x = x - my_project.Config.WINDOW_WIDTH / 2;
        y = y - my_project.Config.WINDOW_HEIGHT / 2;
        JFrame frame = new JFrame("Hühner Simulator Code");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,1000 + 29);
        inputField = new InputField(this);
        frame.setContentPane(inputField.getPanel());
        frame.setVisible(true);
        frame.setLocation(x + my_project.Config.WINDOW_WIDTH - 15,y); //kp wieso das 15px zu weit rechts ist

    }

    public void updateProgram(double dt){

    }

    public void startSimulation(Queue<int[]> commands){
        thread = new Thread(new SimulationThread(commands,this));
        thread.start();
    }

    //Erzeugt ein Feld mit x zu y Kästchen. Maximal bis zu 20*20

    public void createField(int x, int y){
        field = new Field(x,y,chicken);
        viewController.draw(field);
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

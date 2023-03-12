package my_project.view;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.model.*;
import java.awt.image.BufferedImage;

public class SimulationSceeneManager extends GraphicalObject {

    private Field field;
    private BufferedImage background;
    private ViewController viewController;

    public SimulationSceeneManager(ViewController viewController){
        viewController.draw(this);
        background = createImage("src/main/resources/graphic/Hintergrund.png"); //ToDO ist kein gutes Bild, muss erstzt werden.
        this.viewController = viewController;
    }


    @Override
    public void draw(DrawTool drawTool) {
        drawTool.drawImage(background,0,0);
    }

    //Erzeugt ein Feld mit x zu y Kästchen. Maximal bis zu 20*20

    public void createField(int x, int y){
        field = new Field(x,y);
        viewController.draw(field);
        createFood(4,13);
    }

    public void addWall(int x, int y){
        field.addComponent(new Wall(),x,y);
    }

    public void createFood(int x, int y){
        field.addComponent(new Food(),x,y);
    }

    public void createChicken(int x, int y){

    }
}

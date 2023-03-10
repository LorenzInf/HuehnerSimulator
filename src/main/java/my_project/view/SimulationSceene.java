package my_project.view;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.control.ProgramController;

import java.awt.*;

public class SimulationSceene extends GraphicalObject {

    private int fieldWidth = 0;
    private int fieldHeight = 0;


    public SimulationSceene(ViewController viewController){
        viewController.draw(this);
    }


    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setCurrentColor(Color.BLACK);
        for(int i = (20-fieldHeight)/2; i < (20+fieldHeight)/2 ;i++){
            for(int j = (20-fieldWidth)/2; j < (20+fieldWidth)/2 ;j++){
                drawTool.drawRectangle(j*50,i*50,50,50);
            }
        }
    }

    //Erzeugt ein Feld mit x zu y Kästchen. Maximal bis zu 20*20

    public void createField(int x, int y){
        fieldWidth = Math.min(x,20);
        fieldHeight = Math.min(y,20);
    }
}

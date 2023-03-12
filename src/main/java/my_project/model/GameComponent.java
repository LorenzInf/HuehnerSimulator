package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameComponent extends GraphicalObject {

    protected BufferedImage img;

    public GameComponent(){

    }

    public BufferedImage getImg() {
        return img;
    }
}

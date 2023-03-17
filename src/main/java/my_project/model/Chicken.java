package my_project.model;

public class Chicken extends GameComponent{

    private int[][] nextMove;
    private int current;


    public Chicken() {
        img = createImage("src/main/resources/graphic/Huhn.png");
        x = -25;
        y = -25;
        nextMove = new int[][] { {1,0},{0,1},{-1,0},{0,-1} };
    }

    public void turnRight(){
        if(current+1 > nextMove.length){
            current = 0;
        }else{
            current++;
        }
    }

    public void turnLeft(){
        if(current-1 < 0){
            current = 3;
        }else{
            current--;
        }
    }

    public int getMovementToX(){
        return nextMove[current][0];
    }

    public int getMovementToY(){
        return nextMove[current][1];
    }


    public int getXPos(){
        return (int)x;
    }

    public int getYPos(){
        return (int)y;
    }
}

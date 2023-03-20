package my_project.model;

import KAGO_framework.model.abitur.datenstrukturen.Queue;
import my_project.control.ProgramController;

import java.util.Arrays;

public class SimulationThread implements Runnable{

    private Queue<int[]> commands;
    private ProgramController programController;
    private boolean skipTime = false;

    public SimulationThread(Queue<int[]> commands, ProgramController programController){
        this.commands = commands;
        this.programController = programController;
    }

    @Override
    public void run() {
        while (!commands.isEmpty()){
            skipTime = true;
            System.out.println(Arrays.toString(commands.front())); //Debug
            switch (commands.front()[0]) {
                case 0 -> programController.createField(commands.front()[1], commands.front()[2]);
                case 1 -> { programController.createChicken(commands.front()[1], commands.front()[2]);skipTime = false; }
                case 2 -> programController.createFood(commands.front()[1], commands.front()[2]);
                case 3 -> programController.createFence(commands.front()[1], commands.front()[2]);
                case 4 -> { programController.moveChicken();skipTime = false; }
                case 5 -> programController.turnRight();
                case 6 -> programController.turnLeft();
            }
            commands.dequeue();
            if(!skipTime) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

package my_project.model;

import KAGO_framework.model.abitur.datenstrukturen.Queue;
import my_project.control.ProgramController;

public class SimulationThread implements Runnable{

    private long time;
    private Queue<int[]> commands;
    private ProgramController programController;

    public SimulationThread(Queue<int[]> commands, ProgramController programController){
        this.commands = commands;
        this.programController = programController;
    }

    @Override
    public void run() {
        if (!commands.isEmpty()){
            time = System.currentTimeMillis();
            if ((System.currentTimeMillis() - time) < 1000) {
                time = System.currentTimeMillis();
                switch (commands.front()[0]) {
                    case 0 -> programController.createField(commands.front()[1], commands.front()[2]);
                    case 1 -> programController.createChicken(commands.front()[1], commands.front()[2]);
                    case 2 -> programController.createFood(commands.front()[1], commands.front()[2]);
                    case 3 -> programController.createFence(commands.front()[1], commands.front()[2]);
                    case 4 -> programController.moveChicken();
                    case 5 -> programController.turnRight();
                    case 6 -> programController.turnLeft();
            }
            commands.dequeue();
        }
    }

    }
}

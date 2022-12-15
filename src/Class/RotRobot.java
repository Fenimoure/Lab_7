package Class;

import Interface.Rotatable;

public class RotRobot extends Robot implements Rotatable {
    private int start_ang, end_ang;
    private String mvmnt_drctn = "FORWARD";

    public RotRobot() {
        this.start_ang = 0;
        this.end_ang = 270;
    }

    public RotRobot(int start_ang, int end_ang) {
        this.start_ang = start_ang;
        this.end_ang = end_ang;
    }

    public int getStart_ang() {
        return start_ang;
    }

    public void setStart_ang(int start_ang) {
        this.start_ang = start_ang;
    }

    public int getEnd_ang() {
        return end_ang;
    }

    public void setEnd_ang(int end_ang) {
        this.end_ang = end_ang;
    }

    @Override
    public void doSomething() {
        System.out.printf("Rot robot did something. Course %d. \n", course);
    }

    @Override
    public void move() {
        switch (mvmnt_drctn) {
            case "FORWARD" -> {
                while (!(course + step_angle > end_ang)) {
                    doSomething();
                    rotateForward();
                }
            }
            case "BACKWARD" -> {
                while(!(course - step_angle < start_ang)) {
                    doSomething();
                    rotateBackward();
                }
            }
            default -> System.out.println("Robot crashed.");
        }

        if (course + step_angle > end_ang) {
            mvmnt_drctn = "BACKWARD";
        } else if (course - step_angle < start_ang) {
            mvmnt_drctn = "FORWARD";
        }
    }

    @Override
    public void rotateForward() {
        this.course += step_angle;
    }

    @Override
    public void rotateBackward() {
        this.course -= step_angle;
    }
}

package Class;

import Interface.Shiftable;

public class ShiftRobot extends Robot implements Shiftable {

    private int start_shift, end_shift;
    private String mvmnt_drctn = "FORWARD";

    @Override
    public void doSomething() {
        System.out.println("ShiftRobot did something. Course: " + course);
    }

    @Override
    public void move() {
        switch (mvmnt_drctn) {
            case "FORWARD" -> {
                while (!(course + step_shift > end_shift)) {
                    doSomething();
                    shiftForward();
                }
            }
            case "BACKWARD" -> {
                while (!(course - step_shift < start_shift)) {
                    doSomething();
                    shiftBackward();
                }
            }
            default -> System.out.println("Robot crashed.");
        }
        if (course + step_shift > end_shift) {
            mvmnt_drctn = "BACKWARD";
        } else if (course - step_shift < start_shift) {
            mvmnt_drctn = "FORWARD";
        }
    }

    @Override
    public void shiftForward() {
        course += step_shift;
    }

    @Override
    public void shiftBackward() {
        course -= step_shift;
    }
}

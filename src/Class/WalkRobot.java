package Class;

import Interface.Rotatable;
import Interface.Shiftable;

import static Class.Action.ActionType.*;

public class WalkRobot extends Robot implements Rotatable, Shiftable {
    //shift course is an array to store the current position of a WalkRobot. It always has to be of a size of 2. The first integer is the x coordinate and the second is the y coor.
    private int[] shift_course;
    private int angle_course;
    private int wr_step_angle;
    private Action[] actionList;

    public WalkRobot() {
        shift_course = new int[2];
        angle_course = 0;
        //step count вказує скільки разів виконати дію.
        actionList = new Action[5];
        actionList[0] = new Action();
        actionList[0].setAction(RotF);
        actionList[0].setStep_count(1);
        actionList[1] = new Action();
        actionList[1].setAction(RotB);
        actionList[1].setStep_count(2);
        actionList[2] = new Action();
        actionList[2].setAction(ShiftF);
        actionList[2].setStep_count(3);
        actionList[3] = new Action();
        actionList[3].setAction(ShiftB);
        actionList[3].setStep_count(4);
        actionList[4] = new Action();
        actionList[4].setAction(DoSomething);
        actionList[4].setStep_count(1);
        wr_step_angle = 90;
    }

    public WalkRobot(int[] shift_course, int angle_course, Action[] actionList) {
        if (shift_course.length != 2) {
            System.out.println("'shift_course' array has to be of size 2. Setting default value of [0, 0]");
            this.shift_course = new int[2];
        } else {
            this.shift_course = shift_course;
        }
        this.angle_course = angle_course;
        this.actionList = actionList;
        wr_step_angle = 90;
    }

    public int getAngle_course() {
        return angle_course;
    }

    public void setAngle_course(int angle_course) {
        this.angle_course = angle_course;
    }

    public Action[] getActionList() {
        return actionList;
    }

    public void setActionList(Action[] actionList) {
        this.actionList = actionList;
    }

    public void viewActionList() {
        Action[] out = getActionList();
        System.out.print("Action list: ");
        for (Action action : out) {
            System.out.print(action.getAction() + " ");
        }
        System.out.println();
    }

    @Override
    public void doSomething() {
        System.out.println("WalkRobot did something. Position: x: " + shift_course[0] + " y: " + shift_course[1] + ". Angle:" + angle_course + ".");
        StringBuilder board = new StringBuilder();
        StringBuilder spacer = new StringBuilder();
        StringBuilder ecell = new StringBuilder();
        int max_len_sc = 1;
        for (int num : shift_course){
            if (num / 10 > max_len_sc) max_len_sc = (num / 10);
            if (num < 0 && max_len_sc == 1) max_len_sc = 2;
        }
        spacer.append(" ".repeat(Math.max(0, max_len_sc)));
        ecell.append("_".repeat(Math.max(0, max_len_sc)));
        for (int i = shift_course[1] - 2; i < shift_course[1] + 2; i++) {
            for (int j = shift_course[0] - 3; j < shift_course[0] + 2; j++) {
                if (j == shift_course[0] - 3) {
                    board.append(String.format("%d", i) + spacer + " ");
                } else if (i == shift_course[1] && j == shift_course[0])
                    board.append(spacer + "•");
                else board.append(ecell);
            }
            board.append("\n");
        }
        System.out.print("10^" + (shift_course[0] / 10) + spacer + " ");
        for (int i = shift_course[0] - 2; i < shift_course[0] + 2; i++){
            System.out.print(String.format("%d", i) + spacer);
        }
        System.out.println();
        System.out.println(board);
    }

    @Override
    public void move() {
        for (Action action : actionList) {
            switch (action.getAction()) {
                case RotF -> {
                    for (int i = 0; i < action.getStep_count(); i++) {
                        rotateForward();
                        System.out.println("RotF");
                    }
                }
                case RotB -> {
                    for (int i = 0; i < action.getStep_count(); i++) {
                        rotateBackward();
                        System.out.println("RotB");
                    }
                }
                case ShiftF -> {
                    for (int i = 0; i < action.getStep_count(); i++) {
                        shiftForward();
                        System.out.println("ShiftF");
                    }
                }
                case ShiftB -> {
                    for (int i = 0; i < action.getStep_count(); i++) {
                        shiftBackward();
                        System.out.println("ShiftB");
                    }
                }
                case DoSomething -> {
                    for (int i = 0; i < action.getStep_count(); i++) {
                        System.out.println("DoSomething:");
                        doSomething();
                    }
                }
                default -> System.out.println("WalkRobot crashed *sad face*");
            }
        }

    }

    @Override
    public void rotateForward() {
        if (angle_course + wr_step_angle > 270) {
            angle_course = wr_step_angle;
        } else {
            angle_course += wr_step_angle;
        }
    }

    @Override
    public void rotateBackward() {
        if (angle_course - wr_step_angle < 0) {
            angle_course = 360 - wr_step_angle;
        } else {
            angle_course -= wr_step_angle;
        }
    }

    @Override
    public void shiftForward() {
        switch (angle_course) {
            case 0 -> shift_course[1] -= step_shift;
            case 90 -> shift_course[0] += step_shift;
            case 180 -> shift_course[1] += step_shift;
            case 270 -> shift_course[0] -= step_shift;
        }
    }

    @Override
    public void shiftBackward() {
        switch (angle_course) {
            case 0 -> shift_course[1] += step_shift;
            case 90 -> shift_course[0] -= step_shift;
            case 180 -> shift_course[1] -= step_shift;
            case 270 -> shift_course[0] += step_shift;
        }
    }
}

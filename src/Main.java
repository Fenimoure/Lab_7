import Class.RotRobot;
import Class.WalkRobot;

public class Main {
    public static void main(String[] args) {
        RotRobot rotRobot = new RotRobot();
        System.out.println("Forward:");
        rotRobot.move();
        System.out.println("Backward:");
        rotRobot.move();
        for (int i = 0; i < 4; i++) {
            rotRobot.move();
        }
        WalkRobot walkRobot = new WalkRobot();
        walkRobot.move();
        walkRobot.viewActionList();

    }
}
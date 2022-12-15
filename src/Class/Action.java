package Class;

public class Action {
    public int getStep_count() {
        return step_count;
    }

    public void setStep_count(int step_count) {
        this.step_count = step_count;
    }

    public enum ActionType {RotF, RotB, ShiftF, ShiftB, DoSomething }
    private ActionType action;

    public ActionType getAction() {
        return action;
    }

    public void setAction(ActionType action) {
        this.action = action;
    }

    private int step_count;

}

package advanced.balik.application.view;

public enum Action {
    INSERT(""),
    INSERT_RANDOM(""),
    MIN(""),
    CLEAR("");

    private final String action;

    Action(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}

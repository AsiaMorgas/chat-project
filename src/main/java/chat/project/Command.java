package chat.project;

public enum Command {

    PRINT_CHANNELS("\\pc"), CREATE_CHANNEL("\\cc"),  JOIN_CHANNEL("\\j"), PRINT_USERS("\\pu"), LEAVE_CHANNEL("\\j"),
    HELP("\\h");
    private String command;
    Command(String command) {
        this.command = command;
    }
}

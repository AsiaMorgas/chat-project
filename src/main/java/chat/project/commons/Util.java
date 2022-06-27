package chat.project.commons;

import lombok.extern.java.Log;

@Log
public class Util {

    public static void help() {
        log.info("Available commands: \n" +
                "\\pc - prints all available channels \n" +
                "\\cc <channel_name> - create new channel \n" +
                "\\j <channel_name> - join channel \n" +
                "\\pu <channel_name> - print channel users" +
                "\\l <channel_name> - leave channel" +
                "\\h - print all commands");
    }

}

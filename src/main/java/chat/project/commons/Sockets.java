package chat.project.commons;

import lombok.extern.java.Log;

import java.io.IOException;
import java.net.Socket;

@Log
public class Sockets {
    private static final int MIN_PORT__NUMBER = 5000;
    private static final int MAX_PORT_NUMBER = 9000;


    public static int parse(String port) {
        int portNumber = Integer.parseInt(port);
        if(isInRange(portNumber))
            return portNumber;
        else return MAX_PORT_NUMBER;
    }

    public static void close(Socket socket) {
        try {
            socket.close();
        } catch (IOException e) {
            log.severe("Socket close failed: " + e.getMessage());
        }
    }

    private static boolean isInRange(int portNumber) {
        return portNumber >= MIN_PORT__NUMBER && portNumber <= MAX_PORT_NUMBER;
    }
}

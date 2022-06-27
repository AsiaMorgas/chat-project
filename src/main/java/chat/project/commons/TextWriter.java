package chat.project.commons;

import lombok.extern.java.Log;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

@Log
public class TextWriter {

    private PrintWriter writer;


    public TextWriter(Socket socket) {
        try {
            this.writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            log.severe("Output stream creation failer=d: " + e.getMessage());
        }
    }

    public void write(String text) {
        writer.write(text);
    }
}

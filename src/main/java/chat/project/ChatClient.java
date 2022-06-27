package chat.project;

import chat.project.commons.Sockets;
import chat.project.commons.TextReader;
import chat.project.commons.TextWriter;
import lombok.Getter;
import lombok.extern.java.Log;

import java.io.IOException;
import java.net.Socket;
import java.util.UUID;

@Log
public class ChatClient {

    private final Runnable readFromSocket;
    private final Runnable readFromConsole;

    @Getter
    private String name;

    public ChatClient(String host, int port, String name) throws IOException {
        var socket = new Socket(host, port);
        this.name = name;
        readFromSocket = () -> new TextReader(socket, log::info, () -> Sockets.close(socket)).read();
        readFromConsole = () -> new TextReader(System.in, text -> new TextWriter(socket).write(name + ": " + text)).read();
    }

    private void start() {
        new Thread(readFromSocket).start();
        var consoleReader = new Thread(readFromConsole);
        consoleReader.setDaemon(true);
        consoleReader.start();
    }

    public static void main(String[] args) throws IOException {
        var port = Sockets.parse(args[1]);
        new ChatClient(args[0], port, UUID.randomUUID().toString()).start();
    }

}

package chat.project;

import chat.project.commons.TextWriter;
import java.net.Socket;

public class Worker implements Runnable {

    private final Socket socket;
    private final EventBus eventBus;
    private final TextWriter writer;

    public Worker(Socket socket, EventBus eventBus) {
        this.socket = socket;
        this.eventBus = eventBus;
        writer = new TextWriter(socket);
    }

    @Override
    public void run() {}

    private void onText(String text) {
        eventBus.publish(ServerEvent.builder()
                .type(ServerEventType.MESSAGE_RECEIVED)
                .payload(text)
                .source(this)
                .build());
    }

    private void onInputClose() {
        eventBus.publish(ServerEvent.builder()
                .type(ServerEventType.CONNECTION_CLOSED)
                .source(this)
                .build());
    }

    void send(String text) {
        writer.write(text);
    }

}

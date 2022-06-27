package chat.project;

import lombok.extern.java.Log;

import java.util.function.Consumer;

@Log
public class ServerEventsLogger implements Consumer<ServerEvent> {

    @Override
    public void accept(ServerEvent serverEvent) {
        switch (serverEvent.getType()) {
            case SERVER_STARTED -> log.info("Server started.");
            case CONNECTION_ACCEPTED -> log.info("New connection accepted.");
            case CONNECTION_CLOSED -> log.info("Connection from client closed.");
        }
    }
}

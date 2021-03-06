package chat.project;

import lombok.extern.java.Log;

import java.util.function.Consumer;

@Log
public class MessageHistoryLogger implements Consumer<ServerEvent> {

    @Override
    public void accept(ServerEvent event) {
        if (event.getType().equals(ServerEventType.MESSAGE_RECEIVED)) {
            log.info("New message: " + event.getPayload());
        }
    }

}

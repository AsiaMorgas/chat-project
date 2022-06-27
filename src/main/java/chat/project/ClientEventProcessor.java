package chat.project;

import chat.project.commons.Util;
import lombok.RequiredArgsConstructor;

import java.util.function.Consumer;

@RequiredArgsConstructor
public class ClientEventProcessor implements Consumer<MessageType> {

    private final Channels channels;

    @Override
    public void accept(MessageType clientEvent) {
        switch (clientEvent.getCommand()) {
            case PRINT_CHANNELS -> channels.printChannels();
            case CREATE_CHANNEL -> channels.addChannel(new Channel(clientEvent.getValue()));
            case LEAVE_CHANNEL -> channels.getChannelByName(clientEvent.getValue()).removeUser(); //?
            case JOIN_CHANNEL -> channels.getChannelByName(clientEvent.getValue()).addUser(); //?
            case PRINT_USERS -> channels.getChannelByName(clientEvent.getValue()).printUsers();
            case HELP -> Util.help();
        }
    }
}

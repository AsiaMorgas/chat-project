package chat.project;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MessageType {
    private Command command;
    private String value;
    private Worker source;
}

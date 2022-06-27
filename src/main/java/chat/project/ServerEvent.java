package chat.project;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ServerEvent {

    private ServerEventType type;
    private String payload;
    private Worker source;

}

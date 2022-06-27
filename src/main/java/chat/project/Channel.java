package chat.project;

import lombok.Getter;
import lombok.extern.java.Log;

import java.util.HashSet;
import java.util.Set;

@Log
public class Channel {

    @Getter
    private String channelName;
    Set<ChatClient>  users = new HashSet<>();


    public Channel(String name) {
        this.channelName = name;
        log.info(name + " channel created");
    }

    public void addUser(ChatClient user) {
        if(!users.contains(user)) {
            users.add(user);
            log.info(user.getName() + " joined channel");
        }
    }

    public void removeUser(ChatClient user) {
        if(users.contains(user)) {
            users.remove(user);
            log.info(user.getName() + " left channel");
        }
    }

    public void printUsers() {
        if(hasUsers()) {
            users.stream()
                    .forEach(user -> log.info("Users in channel: " + user.getName()));
        }
    }

    public boolean hasUsers() {
        return users.size() > 0;
    }

    public boolean containsUser(ChatClient user) {
        return users.contains(user);
    }
}

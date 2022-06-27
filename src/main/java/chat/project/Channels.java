package chat.project;

import lombok.extern.java.Log;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;


@Log
public class Channels {

    private Set<Channel> channels = new HashSet<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void printChannels() {
        if (hasChannels())
            channels.stream()
                    .forEach(channel -> log.info(channel.getChannelName()));
        log.info("There are no available channels.");

    }

    public void addChannel(Channel channel) {
        lock.writeLock().lock();
        channels.add(channel);
        lock.writeLock().unlock();
    }

    public void removeChannel(String name) {
        lock.writeLock().lock();
        var channelToRemove = getChannelByName(name);
        channels.remove(channelToRemove);
        lock.writeLock().unlock();
    }

    public Channel getChannelByName(String name) {
        return channels.stream()
                .filter(ch -> ch.getChannelName().equals(name))
                .findFirst()
                .get();
    }

    public Set<Channel> getChannelsByUser(ChatClient user) {
        return channels.stream()
                .filter(channel -> channel.containsUser(user))
                .collect(Collectors.toSet());
    }

    private boolean hasChannels() {
        return channels.size() > 0;
    }
}

package chat.project;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@RequiredArgsConstructor
public class SynchronizedServiceWorkers implements ServerWorkers {
    private final ServerWorkers serverWorkers = new HashSetServerWorkers();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void add(Worker worker) {
        lock.writeLock().lock();
        serverWorkers.add(worker);
        lock.writeLock().unlock();
    }

    @Override
    public void remove(Worker worker) {
        lock.writeLock().lock();
        serverWorkers.remove(worker);
        lock.writeLock().unlock();
    }

    @Override
    public void broadcast(String text) {
        lock.readLock().lock();
        serverWorkers.broadcast(text);
        lock.readLock().unlock();
    }
}

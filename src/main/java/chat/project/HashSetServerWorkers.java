package chat.project;

import java.util.HashSet;
import java.util.Set;

public class HashSetServerWorkers implements ServerWorkers {

    private Set<Worker> workers = new HashSet<>();

    @Override
    public void add(Worker worker) {
        workers.add(worker);
    }

    @Override
    public void remove(Worker worker) {
        workers.remove(worker);
    }

   @Override
    public void broadcast(String text, Channel channel) {
       System.out.println("Broadcasting to channel " + channel.getChannelName());

        workers.forEach(chworker -> worker.send(text));
   }
}

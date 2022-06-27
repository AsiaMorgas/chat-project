package chat.project;

import chat.project.commons.Sockets;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * Rolą serwera jest - obsługa klientów, serwowanie jednej funkcjonalności (nie powinno tu byc parsowania, lockowania)
 */

@RequiredArgsConstructor
public class ChatServer {
    private static final int THREADS_COUNT = 1024;

    private final ServerWorkers serverWorkers;
    private final EventBus eventBus;
    private final ExecutorService executorService;

    private void start(int port) throws IOException {
        eventBus.addConsumer(new ServerEventsProcessor(serverWorkers));
        try (var serverSocket = new ServerSocket(port)) {
            eventBus.publish(ServerEvent.builder().type(ServerEventType.SERVER_STARTED).build());
            while (true) {
                var socket = serverSocket.accept();
                eventBus.publish(ServerEvent.builder().type(ServerEventType.CONNECTION_ACCEPTED).build());
                createWorker(socket);
            }
        }
    }

    private void createWorker(Socket socket) {
        var worker = new Worker(socket, eventBus);
        serverWorkers.add(worker);
        executorService.execute(worker);
    }

    public static void main(String[] args) throws IOException {

        //tu nie ma zdefiniowanego parsowania -> wyrzucone do pomocniczej klasy Sockets,
        var port = Sockets.parse(args[0]);

        //zeby nie trzeba wszystkiego robic w klasie serwerowej,
        // to chat.project.EventBus bedzie odpowiedzialny za nasłuchiwanie i logowanie
        var eventBus = new EventBus();
        eventBus.addConsumer(new ServerEventsLogger());

        //logger moze zapisywac logi do bazy a main nic o tym nie wie
        eventBus.addConsumer(new MessageHistoryLogger());

        //tu warstwa synchonizujaca w postaci chat.project.SynchronizedServiceWorkers()
        var serviceWorkers = new SynchronizedServiceWorkers();
        var server = new ChatServer(serviceWorkers, eventBus, newFixedThreadPool(THREADS_COUNT));
        server.start(port);
    }
}

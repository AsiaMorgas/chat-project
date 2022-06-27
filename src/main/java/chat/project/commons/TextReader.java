package chat.project.commons;

import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.function.Consumer;

@Log
public class TextReader {
    private final Consumer<String> textConsumer;
    private BufferedReader reader;
    private Runnable onClose;

    public TextReader(InputStream inputStream, Consumer<String> textConsumer) {
        reader = new BufferedReader(new InputStreamReader(inputStream));
        this.textConsumer = textConsumer;
        reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public TextReader(Socket socket, Consumer<String> textConsumer, Runnable onclose) {
        this.textConsumer = textConsumer;
        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            log.severe("Creating input stream failed " + e.getMessage());
        }
        this.onClose = onclose;
    }

    public void read() {
        String text;
         try {
             if (!(reader.readLine() != null)){
                 text = reader.readLine();
                 textConsumer.accept(text);
             }
         } catch (IOException e) {
             log.severe("Read message failed: " + e.getMessage());
         } finally {
             if(onClose != null) {
                 onClose.run();
             }
         }
    }
}

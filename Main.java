import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream("out.txt"));
            System.setErr(new PrintStream("err.txt"));
            ApiContextInitializer.init();
            TelegramBotsApi botsApi = new TelegramBotsApi();
           botsApi.registerBot(new TabloBot());
           new Thread(new TabloBot()).start();
        }
        catch (IOException | TelegramApiException e) {
            e.printStackTrace();
            new TabloBot().sendStackTrace(e);
        }
    }
}

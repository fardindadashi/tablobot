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
//             botsApi.registerBot(new CryptoBot());
//             new Thread(new CryptoBot()).start();
//            botsApi.registerBot(new TwitterBot());
//            new Thread(new TwitterBot()).start();

//            new TabloBot().shareHoldersAllRecords2();
//            if(args.length == 4)
//                new TabloBot().shareHoldersAllRecords( Integer.valueOf(args[0]), Integer.valueOf(args[1]),
//                        Integer.valueOf(args[2]), Integer.valueOf(args[3])); // Integer.valueOf(args[0])
//            else
//                System.out.println("No Args");

//            new TabloBot().shareHoldersAllRecords( 20210321, 20220319, 1,0); // Integer.valueOf(args[0])

//            System.out.println("main finished");
//            if(args.length == 1) {
//                for (int i = 0; i < Integer.valueOf(args[0]); i++) {
//                    new TabloBot().shareHoldersAllRecords2();
//                    System.out.println("\n---------step" + (i + 1) + " done");
//                }
//            }
//            else
//                System.out.println("No Args");

//            new TabloBot().addShareholdersRecords("sh/l01/");
//            new TabloBot().sortShareholdersRecords();
//            new TabloBot().shareHoldersAllRecords3();
        }
        catch (IOException | TelegramApiException e) { // TelegramApiException | InterruptedException |
            e.printStackTrace();
            new TabloBot().sendStackTrace(e);
        }
    }
}
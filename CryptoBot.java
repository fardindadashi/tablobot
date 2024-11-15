import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.lang.Math;
import java.io.IOException;
import java.time.LocalTime;
import java.util.*;

public class CryptoBot extends TelegramLongPollingBot implements Runnable {

    private CloseableHttpClient httpClient;
    private CloseableHttpResponse httpResponse;
    private HttpGet httpGet;
    private String[] crncPrices;
    private float[] crncPriceChanges;
    private String[] coinNames;
    private float[] coinPrices;
    private float[] coinPriceChanges;
    private ArrayList< ArrayList<Float> > priceHistory = new ArrayList<>();
    private ArrayList< ArrayList<Float> > FiveMinuteMax = new ArrayList<>();
    private ArrayList< ArrayList<Float> > FiveMinuteMin = new ArrayList<>();
    private float[] oneMinutePrices;
    private float[] maxPrice;
    private float[] minPrice;
    private int flatLineLen = 18;
    private String URLpart1 = "https://coin360.com/api/coins?currency=USD&updates_from=";
    private String URLpart2 = "&period=24h&no_charts=true";
    private String URLCrypto = "https://coin360.com/site-api/coins?currency=USD&period=24h";//"https://coin360.com/preloaded_store?&path=/coin/&timestamp=";
    private String URLUSDGoldPart1 = "https://call4.tgju.org/ajax.json?";
    String redCircle = "\uD83D\uDD34";
    String greenCircle = "\uD83D\uDFE2";
    String dollarEmoji = "\uD83D\uDCB5";
    String goldEmoji = "\uD83E\uDD47";
    long UTCDiff = 0L;
    private static class ID {
        private static final String FARDIN = "";
    }

    private int offDays[] = {3,20, 3,21, 3,22, 3,23, 3,24, 4,2, 5,2, 5,3, 5,26, 6,4, 6,5, 7,9, 7,17, 8,7, 8,8};

    private List<String> crncList = Arrays.asList("price_dollar_rl", "irec_future", "crypto-bitcoin", "bourse", "price_eur",
            "price_gbp", "price_aed", "crypto-tether-irr", "sekee", "sekeb", "nim", "rob", "geram18", "geram24", "oil_brent");
    private List<String> crncListFarsi = Arrays.asList("دلار", "سکه",  "بیت کوین", "شاخص بورس", "یورو", "پوند", "درهم", "تتر",
            "سکه امامی", "سکه تمام بهار", "نیم سکه", "ربع سکه", "گرم طلای 18", "گرم طلای 24", "نفت برنت");
    private int crncNumbers = 15;
    private int coinNumbers = 192;

    private List<String> binanceFutureCoins = Arrays.asList("BTC", "1000SHIB", "1INCH", "AAVE", "ABBC", "ADA", "AION", "AKRO", "ALGO",
            "ANKR", "ANT", "ARDR", "ARK", "ARPA", "ATOM", "AVA", "AVAX", "AXS", "BAKE", "BAL", "BAND", "BAT", "BCD", "BCH", "BLZ", "BNB",
            "BNT", "BSV", "BTCB", "BTG", "BTM", "BTS", "BTT", "C20", "CAKE", "CEL", "CELO", "CELR", "CENNZ", "CHR", "CHSB", "CHZ", "COMP",
            "COTI", "CRO", "CRV", "CVC", "DAG", "DASH", "DATA", "DCR", "DENT", "DERO", "DGB", "DGD", "DNT", "DOGE", "DOT", "DUSK", "EGLD",
            "ELA", "ELF", "ENJ", "EOS", "ETC", "ETH", "ETHOS", "ETN", "EUM", "FIL", "FTM", "FTT", "FUN", "GAS", "GNO", "GNT", "GRS", "GRT",
            "HBAR", "HEDG", "HOT", "HT", "ICP", "ICX", "IOST", "IOTA", "IOTX", "JST", "KAVA", "KCS", "KIN", "KMD", "KNC", "KSM", "LEO",
            "LINK", "LOC", "LOOM", "LRC", "LSK", "LTC", "LUNA", "MAID", "MANA", "MATIC", "MCO", "META", "MFT", "MKR", "MLN", "MOF", "MONA",
            "MTL", "NANO", "NEAR", "NEO", "NEXO", "NKN", "NMR", "NOIA", "NRG", "OCEAN", "OKB", "OMG", "ONE", "ONT", "ORBS", "OXT", "PAXG",
            "POLY", "POWR", "PPT", "QNT", "QTUM", "RBTC", "REN", "REP", "REQ", "REV", "RIF", "RLC", "RSR", "RUNE", "RVN", "SC", "SNL",
            "SNM", "SNT", "SNX", "SOL", "STEEM", "STORJ", "STX", "SUSHI", "SXP", "SYS", "TEL", "TFUEL", "THETA", "THR", "TOMO", "TRAC",
            "TRX", "TTT", "UBT", "UMA", "UNI", "UPP", "UTK", "VET", "VRA", "WAN", "WAVES", "WAX", "WAXP", "WBTC", "WIN", "WTC", "XDCE",
            "XEM", "XHV", "XLM", "XMR", "XRP", "XTZ", "XVG", "XYO", "YFI", "ZEC", "ZEN", "ZIL", "ZRX");
    private List<Integer> priceFractionDigits = Arrays.asList(2, 6, 2, 2, 6, 2, 6, 6, 2, 6, 2, 5, 2, 6, 2, 2, 2, 2, 2, 2, 2, 6, 2, 2, 6,
            2, 2, 2, 2, 2, 6, 5, 6, 2, 2, 2, 2, 6, 6, 6, 6, 5, 2, 6, 6, 2, 6, 6, 2, 6, 2, 6, 2, 6, 2, 6, 6, 2, 6, 2, 2, 6, 2, 2, 2, 2, 2,
            6, 2, 2, 2, 2, 6, 2, 2, 6, 5, 6, 6, 2, 6, 2, 2, 2, 6, 2, 5, 6, 2, 2, 6, 2, 2, 2, 2, 2, 2, 6, 6, 2, 2, 2, 6, 6, 2, 2, 6, 6, 2,
            2, 2, 2, 2, 2, 2, 2, 2, 6, 2, 6, 2, 6, 2, 2, 6, 5, 6, 6, 2, 6, 6, 2, 2, 2, 2, 6, 2, 6, 2, 6, 2, 5, 2, 5, 6, 6, 6, 6, 2, 2, 6,
            2, 2, 2, 2, 6, 6, 6, 2, 2, 2, 6, 6, 2, 2, 2, 2, 6, 6, 5, 6, 6, 2, 6, 6, 2, 6, 6, 6, 6, 2, 5, 2, 2, 2, 6, 6, 2, 2, 2, 6, 2);

    public void run() {
        httpClient = HttpClients.createDefault();
        httpResponse = null;
        httpGet = new HttpGet();
        crncNumbers = crncList.size();
        crncPrices = new String[crncNumbers];
        crncPriceChanges = new float[crncNumbers];
        coinNumbers = binanceFutureCoins.size();
        coinNames = new String[coinNumbers];
        coinPrices = new float[coinNumbers];
        coinPriceChanges = new float[coinNumbers];
        oneMinutePrices = new float[coinNumbers];
        maxPrice = new float[coinNumbers];
        minPrice = new float[coinNumbers];
        String[] firstSpaces = new String[coinNumbers];
        String[] secondSpaces = new String[coinNumbers];
        for (int i = 0; i < coinNumbers; i++){
            priceHistory.add(new ArrayList<>());
            FiveMinuteMax.add(new ArrayList<>());
            FiveMinuteMin.add(new ArrayList<>());
            coinNames[i] = binanceFutureCoins.get(i);
            minPrice[i] = 999999999F;
            firstSpaces[i] = "";
            int L = (int)(2.2F * (float)(10 - coinNames[i].length()));
            for (int j = 0; j < L; j++)
                firstSpaces[i] += " ";
        }
        Calendar calendar = Calendar.getInstance();
        long timestamp;
        int bitbotMsgID = 0, dollarbotMsgID1 = 0, dollarbotMsgID2 = 0;
        String toSendPrev1 = "", toSendPrev2 = "", toSendPrev3 = "";
        LocalTime time;
        int cnt10 = 0, cnt50 = 0;
        boolean firstIgnored1 = false, firstIgnored2 = false, bitbotNewMsgSent = true, dollarbotNewMsgSent = true;
        try {
            Scanner sc = new Scanner(new FileInputStream("bitbotmsgid"));
            if(sc.hasNextInt())
                bitbotMsgID = sc.nextInt();
            if(sc.hasNextInt())
                dollarbotMsgID1 = sc.nextInt();
            if(sc.hasNextInt())
                dollarbotMsgID2 = sc.nextInt();
            sc.close();
//            execute(new SendMessage().setChatId(ID.FARDIN).setText(bitbotMsgID + " " + dollarbotMsgID1 + " " + dollarbotMsgID2));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        while (true){
            calendar.setTime(new Date());
            timestamp = calendar.getTime().getTime() / 1000 - 5;
            time = LocalTime.now();//.plus(Duration.of(UTCDiff, ChronoUnit.SECONDS));
            int todayGreg = calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);
            int todayPersian = Integer.valueOf(new TabloBot().changeGregorianToPersian(String.valueOf(todayGreg)));
            String todayPersianStr = new TabloBot().formattedDate(String.valueOf(todayPersian));
            String toSend1 = "", toSend2 = "", toSend3 = "";

            /*
            getData1(todayGreg, time);
            for (int i = 0; i < crncNumbers; i++){
                toSend2 += crncListFarsi.get(i) + ": " + crncPrices[i] + " ";
                if(i == 2 || i == 14)
                    toSend2 += "دلار ";
                else if(i != 3)
                    toSend2 += "ریال ";
                if (crncPriceChanges[i] < 0)
                    toSend2 += " %" +  -1 * crncPriceChanges[i] + "- " + redCircle;
                else if (crncPriceChanges[i] > 0)
                    toSend2 += " %" + crncPriceChanges[i] + "+ " + greenCircle;
                toSend2 += "\n";
                if(i == 3 || i == 7 || i == 11 || i == 14)
                    toSend2 += "\n";
            }
            for (int i = 0; i < 2; i++){
                if(i == 0)
                    toSend1 += dollarEmoji + " #";
                if(i == 1)
                    toSend1 += goldEmoji + " #";
                toSend1 += crncListFarsi.get(i) + ": " + crncPrices[i] + " ریال ";
                if (crncPriceChanges[i] < 0)
                    toSend1 += " %" +  -1 * crncPriceChanges[i] + "- " + redCircle;
                else if (crncPriceChanges[i] > 0)
                    toSend1 += " %" + crncPriceChanges[i] + "+ " + greenCircle;
                toSend1 += "\n\n";
            }
            toSend1 += "این پست هر ۱ دقیقه آپدیت می شود." + "\n" + todayPersianStr;
            toSend2 += "این پست هر ۵ ثانیه آپدیت می شود." + "\n" + "منبع: tgju.org" + "\n" + todayPersianStr;
            if(time.toString().length() >= 8){
                toSend1 += " " + time.toString().substring(0, 8);
                toSend2 += " " + time.toString().substring(0, 8);
            }
            try {
                if(!dollarbotNewMsgSent && !isOffDay(calendar) && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY
                        && time.getHour() >= 10 && time.getHour() <= 22 && time.getMinute() < 5
                        || dollarbotMsgID1 == 0 || dollarbotMsgID2 == 0){
                    SendMessage message = new SendMessage();
                    message.setChatId(ID.DOLLARBOTID);
                    message.setText(toSend1);
                    Message retmsg = execute(message);
                    dollarbotMsgID1 = retmsg.getMessageId();
                    message.setText(toSend2);
                    retmsg = execute(message);
                    dollarbotMsgID2 = retmsg.getMessageId();
                    toSendPrev1 = toSend1;
                    toSendPrev2 = toSend2;
                    dollarbotNewMsgSent = true;
                    writeToMsgIDFile(bitbotMsgID, dollarbotMsgID1, dollarbotMsgID2);
                }
                if(time.getMinute() > 5)
                    dollarbotNewMsgSent = false;
                if(!toSend2.equals(toSendPrev2)){
                    editMessage(toSend2, ID.DOLLARBOTID, dollarbotMsgID2);
                    toSendPrev2 = toSend2;
                }
                if(time.getSecond() < 5 && !toSend1.equals(toSendPrev1)){
                    editMessage( toSend1, ID.DOLLARBOTID, dollarbotMsgID1);
                    toSendPrev1 = toSend1;
                }
            }
            catch (TelegramApiException e){
                e.printStackTrace();
            }
            */
            /*
            if(time.getSecond() == 0 || cnt10 == 9){
                if(!firstIgnored1){
                    for (int i = 0; i < coinNumbers; i++)
                        oneMinutePrices[i] = 0;
                    firstIgnored1 = true;
                }
                else {
                    for (int i = 0; i < coinNumbers; i++) {
                        if (oneMinutePrices[i] != 0) {
                            priceHistory.get(i).add(oneMinutePrices[i] / 10F);
                        }
                    }
                }
                if(time.getMinute() % 10 == 0){
                    for (int i = 0; i < coinNumbers; i++){
                        if(priceHistory.get(i).size() >= 10){
//                            try {
//                                FileWriter fw = new FileWriter("coin/" + coinNames[i], true);
//                                for (int j = 0; j < priceHistory.get(i).size(); j++)
//                                    fw.write(String.format("%." + priceFractionDigits.get(i) + "f", priceHistory.get(i).get(j)) + " ");
//                                fw.close();
//                            }
//                            catch (IOException e){
//                                e.printStackTrace();
//                            }
                            priceHistory.get(i).clear();
                        }
                    }
                }
                cnt10 = 0;
            }
            else
                cnt10++;
            if(time.getMinute() % 5 == 0 && time.getSecond() == 0 || cnt50 == 49){
                if(firstIgnored2) {
                    for (int i = 0; i < coinNumbers; i++) {
                        if (coinPrices[i] != 0) {
                            FiveMinuteMax.get(i).add(maxPrice[i]);
                            FiveMinuteMin.get(i).add(minPrice[i]);
                            if (FiveMinuteMax.get(i).size() == flatLineLen + 1)
                                FiveMinuteMax.get(i).remove(0);
                            if (FiveMinuteMin.get(i).size() == flatLineLen + 1)
                                FiveMinuteMin.get(i).remove(0);
                            maxPrice[i] = 0;
                            minPrice[i] = 999999999F;
                        }
                    }
//                    findFlatLines();
                }
                else{
                    for (int i = 0; i < coinNumbers; i++) {
                        maxPrice[i] = 0;
                        minPrice[i] = 999999999F;
                    }
                    firstIgnored2 = true;
                }
                cnt50 = 0;
            }
            else
                cnt50++;
            */
            getData2();
            for (int i = 0; i < coinNumbers; i++) {
                if (coinPrices[i] != 0) {
//                    if (cnt10 == 0)
//                        oneMinutePrices[i] = coinPrices[i];
//                    else
//                        oneMinutePrices[i] += coinPrices[i];
//                    if (coinPrices[i] > maxPrice[i])
//                        maxPrice[i] = coinPrices[i];
//                    if (coinPrices[i] < minPrice[i])
//                        minPrice[i] = coinPrices[i];
                    secondSpaces[i] = "";
                    int L = (int) (2.2F * (float) (13 - String.format("%." + priceFractionDigits.get(i) + "f", coinPrices[i]).length()));
                    for (int j = 0; j < L; j++)
                        secondSpaces[i] += " ";
                    toSend3 += coinNames[i] + "➖" + String.format("%." + priceFractionDigits.get(i) + "f", coinPrices[i]) + "➖ ";
                    if (coinPriceChanges[i] > 0)
                        toSend3 += "+";
                    toSend3 += coinPriceChanges[i] + "% ";
                    if (coinPriceChanges[i] > 0)
                        toSend3 += greenCircle;
                    else if (coinPriceChanges[i] < 0)
                        toSend3 += redCircle;
                    toSend3 += "\n";
                }
            }
            toSend3 += "\nThis post updates every 10 seconds.\n" +  "این پست هر ۱۰ ثانیه آپدیت می شود." + "\n\n";
            if(time.toString().length() >= 8)
                toSend3 += todayPersianStr + " " + time.toString().substring(0, 8) + "\n";
            try {
                if(!bitbotNewMsgSent && time.getHour() == 8 || bitbotMsgID == 0){
                    SendMessage message = new SendMessage();
                    message.setChatId(ID.BITBOTXID);
                    message.setText(toSend3);
                    Message retmsg = execute(message);
                    bitbotMsgID = retmsg.getMessageId();
                    toSendPrev3 = toSend3;
                    bitbotNewMsgSent = true;
                    writeToMsgIDFile(bitbotMsgID, dollarbotMsgID1, dollarbotMsgID2);

                }
                if(time.getHour() != 8)
                    bitbotNewMsgSent = false;
                if(time.getSecond() % 10 < 5 && !toSend3.equals(toSendPrev3)){
                    editMessage(toSend3, ID.BITBOTXID, bitbotMsgID);
                    toSendPrev3 = toSend3;
                }
                if(time.getSecond() % 5 == 0)
                    Thread.sleep(1000);
                while (true){
                    time = LocalTime.now();
                    if(time.getSecond() % 5 == 0)
                        break;
                    Thread.sleep(250);
                }
                FileWriter fw = new FileWriter("lastruntime2");
                fw.write(String.valueOf(time.getHour() * 3600 + time.getMinute() * 60 + time.getSecond()));
                fw.close();
            }
            catch (InterruptedException | TelegramApiException | IOException e){
                e.printStackTrace();
            }
        }
    }

    private String httpTask(String url) {
        final StringBuilder sb = new StringBuilder("");
        Runnable task1 = () -> {
            try {
                httpGet = new HttpGet(url);
                httpResponse = httpClient.execute(httpGet);
                HttpEntity entity = httpResponse.getEntity();
                if (entity != null && httpResponse.getStatusLine().getStatusCode() == 200) {
                    String result = EntityUtils.toString(entity);
                    sb.append(result);
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        };
        Thread thread1 = new Thread(task1);
        thread1.start();
        int threadTimer = 0;

        try {
            while (true) {
                Thread.sleep(250);
                threadTimer++;
                if (threadTimer % 40 == 39) {
                    System.out.print("Y1");
                    if(thread1.isAlive())
                        thread1.interrupt();
                    httpClient.close();
                    httpClient = HttpClients.createDefault();
                    thread1 = new Thread(task1);
                    sb.delete(0,sb.length());
                    thread1.start();
                    System.out.print("Y2");
                    LocalTime time2 = LocalTime.now();
                    FileWriter fw = new FileWriter("lastruntime2");
                    fw.write(String.valueOf(time2.getHour() * 3600 + time2.getMinute() * 60 + time2.getSecond()));
                    fw.close();
                }
                if (sb.length() > 200)
                    break;
            }
        }
        catch (InterruptedException | IOException e){
            e.printStackTrace();
//            sendStackTrace(e);
        }
        return sb.toString();
    }

    public void editMessage(String text, String chatID, int msgID){
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(chatID);
        editMessageText.setMessageId(msgID);
        editMessageText.setText(text);
        try {
            execute(editMessageText);
        }
        catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    public void writeToMsgIDFile(int bitbotMsgID, int dollarbotMsgID1, int dollarbotMsgID2){
        try {
            FileWriter fw = new FileWriter("bitbotmsgid");
            fw.write(bitbotMsgID + " " + dollarbotMsgID1 + " " + dollarbotMsgID2);
            fw.close();
//            execute(new SendMessage().setChatId(ID.FARDIN).setText(bitbotMsgID + " " + dollarbotMsgID1 + " " + dollarbotMsgID2));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean isOffDay(Calendar calendar){
        for (int i = 0; i < offDays.length; i++){
            int month = calendar.get(Calendar.MONTH) + 1;
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            if(offDays[i] == month && offDays[i+1] == dayOfMonth)
                return true;
            i++;
        }
        return false;
    }

    public long[] stringNextLong(String str, char seperating, int i){
        String temp = "";
        int strLen = str.length();
        for (; i < strLen; i++) {
            if (str.charAt(i) == seperating) {
                i++;
                break;
            }
            if(str.charAt(i) >= '0' && str.charAt(i) <= '9' || str.charAt(i) == '-' && temp.length() == 0)
                temp += str.charAt(i);
            else{
                temp = "";
                break;
            }
        }
        temp = (temp.equals("")) ? "0":temp;
        return new long[] {Long.valueOf(temp), i};
    }

    public double[] stringNextDouble(String str, char seperating, int i){
        String temp = "";
        int strLen = str.length();
        for (; i < strLen; i++) {
            if (str.charAt(i) == seperating) {
                i++;
                break;
            }
            if(str.charAt(i) >= '0' && str.charAt(i) <= '9'
                    || str.charAt(i) == '-' && temp.length() == 0
                    || str.charAt(i) == '.' && temp.length() != 0)
                temp += str.charAt(i);
            else{
                temp = "";
                break;
            }
        }
        temp = (temp.equals("")) ? "0":temp;
        return new double[] {Double.valueOf(temp), i};
    }

    public String[] stringNextString(String str, char seperating, int i){
        String temp = "";
        int strLen = str.length();
        for (; i < strLen; i++) {
            if (str.charAt(i) == seperating) {
                i++;
                break;
            }
            temp += str.charAt(i);
        }
        return new String[] {temp, String.valueOf(i)};
    }
    private int persianCalendarPlusOne(int baseDate){
        int year = baseDate / 10000;
        int month = (baseDate % 10000) / 100;
        int day = baseDate % 100;
        if(day == 31 && month <= 6 || day == 30 && month >= 7 && month <= 11){
            day = 1;
            month++;
        }
        else if(month == 12 && (day == 29 && year % 4 != 3 || day == 30)){
            day = 1;
            month = 1;
            year++;
        }
        else {
            day++;
        }
        return (year * 10000 + month * 100 + day);
    }
    private int gregorianCalendarPlusOne(int baseDate){
        int year = baseDate / 10000;
        int month = (baseDate % 10000) / 100;
        int day = baseDate % 100;
        if(day == 31 && (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) ||
                day == 30 && (month == 4 || month == 6 || month == 9 || month == 11) ||
                month == 2 && (day == 28 && year % 4 != 0 || day == 29)){
            day = 1;
            if(month == 12){
                month = 1;
                year++;
            }
            else
                month++;
        }
        else {
            day++;
        }
        return (year * 10000 + month * 100 + day);
    }
    public void getData1(int date, LocalTime time){
        int timeInt = time.getHour() * 10000 + time.getMinute() * 100 + time.getSecond();
        String randStr = "";
        for(int i = 0; i < 20; i++){
            int randNum = (int)(Math.random() * 62D);
            if(randNum <= 9)
                randStr += (char)((int)'0' + randNum);
            else if(randNum <= 35)
                randStr += (char)((int)'A' + randNum - 10);
            else
                randStr += (char)((int)'a' + randNum - 36);
        }
        String result = httpTask(URLUSDGoldPart1 + date + time.getHour() + "-" + date + timeInt + "-" + randStr);
        int resultLength = result.length();
        int i = result.indexOf("{\"current\":");
        if(i < 0)
            return;
        String[] tempStringArray;
        double[] tempDoubleArray;
        String name = "";
        int cnt = 0;
        while (i < resultLength){
            for(; i < resultLength; i++){
                if(i + 3 < resultLength && result.substring(i, i + 3).equals("},\"")){
                    i += 3;
                    break;
                }
            }
            tempStringArray = stringNextString(result, '\"', i);
            name = tempStringArray[0];
            i = Integer.valueOf(tempStringArray[1]);
            int crncIndex = crncList.indexOf(name);
            if(crncIndex >= 0){
                for(; i < resultLength; i++){
                    if(i + 5 < resultLength && result.substring(i, i + 5).equals("\"p\":\"")){
                        i += 5;
                        break;
                    }
                }
                tempStringArray = stringNextString(result, '\"', i);
                crncPrices[crncIndex] = tempStringArray[0];
                i = Integer.valueOf(tempStringArray[1]);
                for(; i < resultLength; i++){
                    if(i + 5 < resultLength && result.substring(i, i + 5).equals("\"dp\":")){
                        i += 5;
                        break;
                    }
                }
                tempDoubleArray = stringNextDouble(result, ',', i);
                crncPriceChanges[crncIndex] = (float) tempDoubleArray[0];
                i = (int)tempDoubleArray[1];
                for(; i < resultLength; i++){
                    if(i + 6 < resultLength && result.substring(i, i + 6).equals("\"dt\":\"")){
                        i += 6;
                        if(i + 3 < resultLength && result.substring(i, i + 3).equals("low"))
                            crncPriceChanges[crncIndex] *= -1;
                        break;
                    }
                }
                cnt++;
            }
            if(cnt == crncNumbers)
                break;
        }
        return;
    }
    public void getData2(){
        boolean[] done = new boolean[coinNumbers];
        String result = httpTask(URLCrypto);
        int resultLength = result.length();
        int i = result.indexOf("\"data\":[");
        if(i < 0)
            return;
        i += 8;
        String[] tempStringArray;
        double[] tempDoubleArray;
        long[] tempLongArray;
        float price = 0, change = 0;
        int mcr = 0;
        String name = "";
        int coinCnt = 0;
        while (i < resultLength){
            for(; i < resultLength; i++){
                if(i + 5 < resultLength && result.substring(i, i + 5).equals("\"ch\":")){
                    i += 5;
                    break;
                }
            }
            tempDoubleArray = stringNextDouble(result, ',', i);
            change = (float) tempDoubleArray[0];
            i = (int) tempDoubleArray[1];
            for(; i < resultLength; i++){
                if(i + 6 < resultLength && result.substring(i, i + 6).equals("\"mcr\":")){
                    i += 6;
                    break;
                }
            }
            tempLongArray = stringNextLong(result, ',', i);
            mcr = (int)tempLongArray[0];
            i = (int)tempLongArray[1];
            for(; i < resultLength; i++){
                if(i + 4 < resultLength && result.substring(i, i + 4).equals("\"p\":")){
                    i += 4;
                    break;
                }
            }
            tempDoubleArray = stringNextDouble(result, ',', i);
            price = (float) tempDoubleArray[0];
            i = (int) tempDoubleArray[1];
            for(; i < resultLength; i++){
                if(i + 5 < resultLength && result.substring(i, i + 5).equals("\"s\":\"")){
                    i += 5;
                    break;
                }
            }
            tempStringArray = stringNextString(result, '\"', i);
            name = tempStringArray[0];
            i = Integer.valueOf(tempStringArray[1]);
            boolean done2 = false;
            if(name.length() > 0 && name.charAt(name.length() - 1) == '*'){
                name = name.substring(0,name.length() - 1);
                done2 = true;
            }
            if(name.equals("SHIB")){
                name = "1000SHIB";
                price *= 1000F;
            }
            if(mcr <= 157 && price != 0) {
                for (int j = 0; j < coinNumbers; j++) {
                    if (coinNames[j].equals(name) && !done[j]) {
                        coinPrices[j] = price;
                        coinPriceChanges[j] = change;
                        done[j] = done2;
                        coinCnt++;
                        break;
                    }
                }
            }
            for(; i < resultLength; i++){
                if(result.charAt(i) == '}'){
                    i++;
                    break;
                }
            }
            if(i + 2 < resultLength && result.substring(i, i + 2).equals("],") || coinCnt == coinNumbers)
                break;
        }
    }
    private void findFlatLines(){
        String toSend = "";
        for (int i = 0; i < coinNumbers; i++){
            if(coinPrices[i] != 0 && FiveMinuteMax.get(i).size() == flatLineLen && FiveMinuteMin.get(i).size() == flatLineLen) {
                float min = FiveMinuteMin.get(i).get(flatLineLen - 1), max = FiveMinuteMax.get(i).get(flatLineLen - 1);
                for (int j = flatLineLen - 2; j >= 0; j--){
                    if(FiveMinuteMin.get(i).get(j) < min)
                        min = FiveMinuteMin.get(i).get(j);
                    if(FiveMinuteMax.get(i).get(j) > max)
                        max = FiveMinuteMax.get(i).get(j);
                }
                if(max / min <= 1.01)
                    toSend += coinNames[i] + " " + String.format("%.2f",(max / min - 1F) * 100F) + "% ";
            }
        }
        try {
            SendMessage message = new SendMessage().setChatId(ID.FARDIN);
            if(toSend.length() > 0) {
                message.setText(toSend);
                execute(message);
            }
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }


    @Override
    public void onUpdateReceived(Update update) {
        String userID, messageText;
        boolean hasText;
        if (update.hasMessage()) {
            hasText = update.getMessage().hasText();
            userID = String.valueOf(update.getMessage().getChatId());
            if(hasText) {
                messageText = update.getMessage().getText();
                if (messageText.equals("کیه کیه")) {
                    try {
                        SendMessage message = new SendMessage();
                        message.setChatId(userID);
                        message.setText("منم تهی");
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    @Override
    public String getBotUsername() {
        return "hyperbitbot";
    }

    @Override
    public String getBotToken() {
        return "";
    }
}

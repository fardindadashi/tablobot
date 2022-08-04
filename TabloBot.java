import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageMedia;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.awt.Font;
import java.awt.Color;
import java.awt.*;
import java.io.*;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import java.awt.image.BufferedImage;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TabloBot extends TelegramLongPollingBot implements Runnable {

    private ArrayList<CloseableHttpClient> httpClients = new ArrayList<>();
    private ArrayList<CloseableHttpResponse> httpResponses = new ArrayList<>();
    private ArrayList<HttpGet> httpGets = new ArrayList<>();
    private ArrayList<String> symbolID = new ArrayList<>();
    private ArrayList<String> symbolNickName = new ArrayList<>();
    private ArrayList<String> symbolName = new ArrayList<>();
    private ArrayList<String> symbolType = new ArrayList<>();
    private ArrayList<String> symbolIgnore = new ArrayList<>();
    private ArrayList<Integer> lastExchangeTime = new ArrayList<>();
    private ArrayList<Integer> maxAllowedVolume = new ArrayList<>();
    private ArrayList<Integer> firstPrice = new ArrayList<>();
    private ArrayList<Integer> closingPrice1 = new ArrayList<>();
    private ArrayList<Integer> closingPrice2 = new ArrayList<>();
    private ArrayList<Integer> lastPrice1 = new ArrayList<>();
    private ArrayList<Integer> lastPrice2 = new ArrayList<>();
    private ArrayList<Integer> exQuantity1 = new ArrayList<>();
    private ArrayList<Integer> exQuantity2 = new ArrayList<>();
    private ArrayList<Long> exVolume1 = new ArrayList<>();
    private ArrayList<Long> exVolume2 = new ArrayList<>();
    private ArrayList<Long> exValue1 = new ArrayList<>();
    private ArrayList<Long> exValue2 = new ArrayList<>();
    private ArrayList<Integer> minPrice1 = new ArrayList<>();
    private ArrayList<Integer> minPrice2 = new ArrayList<>();
    private ArrayList<Integer> maxPrice1 = new ArrayList<>();
    private ArrayList<Integer> maxPrice2 = new ArrayList<>();
    private ArrayList<Integer> yesterdayClosingPrice = new ArrayList<>();
    private ArrayList<Integer> yesterdayLastPrice = new ArrayList<>();
    private ArrayList<Integer> EPS = new ArrayList<>();
    private ArrayList<Integer> maxAllowed = new ArrayList<>();
    private ArrayList<Integer> minAllowed = new ArrayList<>();
    private ArrayList<Long> totalVolume = new ArrayList<>();

    private ArrayList<Integer> realBuyCount0 = new ArrayList<>();
    private ArrayList<Integer> realSellCount0 = new ArrayList<>();
    private ArrayList<Integer> legalBuyCount0 = new ArrayList<>();
    private ArrayList<Integer> legalSellCount0 = new ArrayList<>();
    private ArrayList<Integer> realBuyCount1 = new ArrayList<>();
    private ArrayList<Integer> realSellCount1 = new ArrayList<>();
    private ArrayList<Integer> legalBuyCount1 = new ArrayList<>();
    private ArrayList<Integer> legalSellCount1 = new ArrayList<>();
    private ArrayList<Long> realBuyVolume1 = new ArrayList<>();
    private ArrayList<Long> realSellVolume1 = new ArrayList<>();
    private ArrayList<Long> legalBuyVolume1 = new ArrayList<>();
    private ArrayList<Long> legalSellVolume1 = new ArrayList<>();
    private ArrayList<Integer> realBuyCount2 = new ArrayList<>();
    private ArrayList<Integer> realSellCount2 = new ArrayList<>();
    private ArrayList<Integer> legalBuyCount2 = new ArrayList<>();
    private ArrayList<Integer> legalSellCount2 = new ArrayList<>();
    private ArrayList<Long> realBuyVolume2 = new ArrayList<>();
    private ArrayList<Long> realSellVolume2 = new ArrayList<>();
    private ArrayList<Long> legalBuyVolume2 = new ArrayList<>();
    private ArrayList<Long> legalSellVolume2 = new ArrayList<>();
    private ArrayList<Integer> realBuyAccumulate = new ArrayList<>();
    private ArrayList<Integer> legalBuyAccumulate = new ArrayList<>();
    private ArrayList<Integer> realSellAccumulate = new ArrayList<>();
    private ArrayList<Integer> legalSellAccumulate = new ArrayList<>();
    private ArrayList<Integer> realBuyAccumulate2 = new ArrayList<>();
    private ArrayList<Integer> legalBuyAccumulate2 = new ArrayList<>();
    private ArrayList<Integer> realSellAccumulate2= new ArrayList<>();
    private ArrayList<Integer> legalSellAccumulate2 = new ArrayList<>();

    private ArrayList<Integer> buyQuantity1a = new ArrayList<>();
    private ArrayList<Integer> sellQuantity1a = new ArrayList<>();
    private ArrayList<Long> buyVolume1a = new ArrayList<>();
    private ArrayList<Long> sellVolume1a = new ArrayList<>();
    private ArrayList<Integer> buyPrice1a = new ArrayList<>();
    private ArrayList<Integer> sellPrice1a = new ArrayList<>();

    private ArrayList<Integer> buyQuantity1 = new ArrayList<>();
    private ArrayList<Integer> buyQuantity2 = new ArrayList<>();
    private ArrayList<Integer> buyQuantity3 = new ArrayList<>();
    private ArrayList<Integer> buyQuantity4 = new ArrayList<>();
    private ArrayList<Integer> buyQuantity5 = new ArrayList<>();
    private ArrayList<Integer> sellQuantity1 = new ArrayList<>();
    private ArrayList<Integer> sellQuantity2 = new ArrayList<>();
    private ArrayList<Integer> sellQuantity3 = new ArrayList<>();
    private ArrayList<Integer> sellQuantity4 = new ArrayList<>();
    private ArrayList<Integer> sellQuantity5 = new ArrayList<>();
    private ArrayList<Long> buyVolume1 = new ArrayList<>();
    private ArrayList<Long> buyVolume2 = new ArrayList<>();
    private ArrayList<Long> buyVolume3 = new ArrayList<>();
    private ArrayList<Long> buyVolume4 = new ArrayList<>();
    private ArrayList<Long> buyVolume5 = new ArrayList<>();
    private ArrayList<Long> sellVolume1 = new ArrayList<>();
    private ArrayList<Long> sellVolume2 = new ArrayList<>();
    private ArrayList<Long> sellVolume3 = new ArrayList<>();
    private ArrayList<Long> sellVolume4 = new ArrayList<>();
    private ArrayList<Long> sellVolume5 = new ArrayList<>();
    private ArrayList<Integer> buyPrice1 = new ArrayList<>();
    private ArrayList<Integer> buyPrice2 = new ArrayList<>();
    private ArrayList<Integer> buyPrice3 = new ArrayList<>();
    private ArrayList<Integer> buyPrice4 = new ArrayList<>();
    private ArrayList<Integer> buyPrice5 = new ArrayList<>();
    private ArrayList<Integer> sellPrice1 = new ArrayList<>();
    private ArrayList<Integer> sellPrice2 = new ArrayList<>();
    private ArrayList<Integer> sellPrice3 = new ArrayList<>();
    private ArrayList<Integer> sellPrice4 = new ArrayList<>();
    private ArrayList<Integer> sellPrice5 = new ArrayList<>();

    private ArrayList<Long> realBuyVolumeDiff = new ArrayList<>();
    private ArrayList<Long> realSellVolumeDiff = new ArrayList<>();
    private ArrayList<Long> legalBuyVolumeDiff = new ArrayList<>();
    private ArrayList<Long> legalSellVolumeDiff = new ArrayList<>();
    private ArrayList<Integer> lastCheckedRow = new ArrayList<>();
    private ArrayList<Integer> lastCheckedRow2 = new ArrayList<>();

    private ArrayList<Boolean> counter1 = new ArrayList<>();
    private ArrayList<Integer> counter2 = new ArrayList<>();
    private ArrayList<Boolean> queueAnnounced = new ArrayList<>();
    private ArrayList<Long> queueMaxVolume = new ArrayList<>();
    private ArrayList<Integer> counter2Timer = new ArrayList<>();
    private ArrayList<Integer> supportOrderTimer = new ArrayList<>();
    private ArrayList<Integer> supportOrderType = new ArrayList<>();
    private ArrayList<Integer> supportOrderCounter = new ArrayList<>();
    private ArrayList<Integer> inQueueDays = new ArrayList<>();
    private ArrayList<Long> valAvg1Month = new ArrayList<>();
    private ArrayList<Long> valAvg3Month = new ArrayList<>();
    private ArrayList<Long> valAvg12Month = new ArrayList<>();
    private ArrayList<Long> volAvg1Month = new ArrayList<>();
    private ArrayList<Long> volAvg3Month = new ArrayList<>();
    private ArrayList<Long> volAvg12Month = new ArrayList<>();
    private ArrayList<Integer> exQAvg1Month = new ArrayList<>();
    private ArrayList<Integer> exQAvg3Month = new ArrayList<>();
    private ArrayList<Integer> exQAvg12Month = new ArrayList<>();

    private ArrayList<Long> totalCodeToCodeVolume = new ArrayList<>();
    private ArrayList<Long> totalCodeToCodeCount = new ArrayList<>();
    private ArrayList<Long> totalCodeToCodeVolume2 = new ArrayList<>();
    private ArrayList<Long> totalCodeToCodeCount2 = new ArrayList<>();
    private ArrayList<Integer> volumeCounter = new ArrayList<>();
    private ArrayList<Integer> volumeCounterTimer = new ArrayList<>();
    private ArrayList<Boolean> volumeAnnounced = new ArrayList<>();
    private ArrayList<String> symbolNickNameY = new ArrayList<>();
    private ArrayList<Long> exVolumeY = new ArrayList<>();
    private ArrayList<Integer> lastSupportPrice = new ArrayList<>();
    private ArrayList<Long> lastSupportVolume = new ArrayList<>();
    private ArrayList<Long> firstSupportVolume = new ArrayList<>();
    private ArrayList<Integer> lastSupportTime = new ArrayList<>();
    private ArrayList<String> buyAvgOver40 = new ArrayList<>();
    private ArrayList<String> buyAvgOver60 = new ArrayList<>();
    private ArrayList<String> buyAvgOver80 = new ArrayList<>();
    private ArrayList<Long> buyAvgMax = new ArrayList<>();
    private ArrayList<String> sellerPowU3 = new ArrayList<>();
    private ArrayList<String> sellerPowU2 = new ArrayList<>();
    private ArrayList<String> sellerPowU1_5 = new ArrayList<>();
    private ArrayList<String> sellerPowU1_2 = new ArrayList<>();
    private ArrayList<Float> spu3BuyAvg = new ArrayList<>();
    private ArrayList<Float> spu3SellAvg = new ArrayList<>();
    private ArrayList<Float> spu2BuyAvg = new ArrayList<>();
    private ArrayList<Float> spu2SellAvg = new ArrayList<>();
    private ArrayList<Float> spu1_5BuyAvg = new ArrayList<>();
    private ArrayList<Float> spu1_5SellAvg = new ArrayList<>();
    private ArrayList<Float> buyerPowerMax = new ArrayList<>();
    private ArrayList<Integer> buyAvgIncLastTime = new ArrayList<>();

    private ArrayList<Float> totalMarketCapChange = new ArrayList<>();
    int players1MsgID = 0;
    int players2MsgID = 0;
    int hotMoneySupportOrderMsgID = 0;
    int totalMarketCapLastSentTime = 0;
    int totalMarketCapMsgID = 0;

    private ArrayList<String> watchlist = new ArrayList<>();

    private int[] publicSendTime = new int[20];
    private int[] public2SendTime = new int[20];
    private int[] shareholdersSendTime = new int[20];
    private int[] otherPrivateSendTime = new int[20];
    private int[] fardinSendTime = new int[20];
    private int psti = 0, p2sti = 0, shsti = 0, opsti = 0, fsti = 0;

    private ArrayList<Integer> rangeCounter = new ArrayList<>();
    private ArrayList<Integer> rangeCounterTimer = new ArrayList<>();
    private ArrayList<Boolean> rangeAnnounced = new ArrayList<>();
    
    private int lastExchangeTimeMax = 0;
    private int lastSendTimeDelay = 0;
    private long[][] volumeBuffer;
    private int volumeBufferSize = 6;
    private int volumeBufferIndex = 0;
    private long[][] priceBuffer;
    private long[][] priceAvgBuffer;
    private int priceBufferSize = 60;
    private int priceBufferIndex = 0;

    private ArrayList< ArrayList<Integer> > priceChartData = new ArrayList<>();
//    private ArrayList< ArrayList<Integer> > priceChartDataAvg = new ArrayList<>();
    private ArrayList<Integer> priceChartDataBlackIndex = new ArrayList<>();
    private ArrayList< ArrayList<Integer> > exValueChartData = new ArrayList<>();
    private ArrayList< ArrayList<Integer> > buyVolChartData = new ArrayList<>();
    private ArrayList< ArrayList<Integer> > buyCntChartData = new ArrayList<>();
//    private ArrayList< ArrayList<Integer> > buyAvgChartDataAvg = new ArrayList<>();
    private ArrayList< ArrayList<Integer> > sellVolChartData = new ArrayList<>();
    private ArrayList< ArrayList<Integer> > sellCntChartData = new ArrayList<>();
//    private ArrayList< ArrayList<Integer> > sellAvgChartDataAvg = new ArrayList<>();
    private ArrayList<Boolean> volPassed1B = new ArrayList<>();
//    private int chartDataCounter = 0;
    private int priceChartDataAvgLen = 5;
    private int barWidth = 6;
//    private int chartArraySize1 = 2650;
//    private int chartArraySize2 = 450;
    private ArrayList<String> halfHours;
    private ArrayList<Integer> halfHoursIndex;
    private final int defaultSymbolsCount = 1000;
    private String sellQWatchlist = "";
    private String rangeChatID = ID.PUBLIC_CHANNEL2;
    private String volumeChatID = ID.PUBLIC_CHANNEL2;
    private Thread threadChart = new Thread();

    private class Share{
        String shareName = "X";
        long sharesNumber = 0, change = 0;
        float percent = 0;
        String enterDate = "0", exitDate = "0";
        boolean positiveChange = false;
        boolean todayExist = false;
    }
    private class Shareholder{
        String id = "0", name = "X";
        private ArrayList<Share> currentShares = new ArrayList<>();
        private ArrayList<Share> prevShares = new ArrayList<>();
    }
    private ArrayList<Shareholder> shareholders = new ArrayList<>();

    private class Share2{
        String shareName = "X";
        String enterOrExit;
        String date = "0";
        float pnl1 = 0F;
        float pnl2 = 0F;
        float pnl3 = 0F;
    }
    private class Shareholder2{
        String id = "0", name = "X";
        private ArrayList<Share2> prevShares = new ArrayList<>();
    }
    private ArrayList<Shareholder2> shareholdersFull = new ArrayList<>();


    private class PlayerDay{
        String date = "";
        float marketCap = 0;
        float buyRatio = 0;
        float sellRatio = 0;
        int realBuy = 0;
        int legalBuy = 0;
        int realSell = 0;
        int legalSell = 0;
    }
    private class Player{
        String shareName = "";
        int realBuyDays = 0;
        int realSellDays = 0;
        int realBuySum = 0;
        int legalBuySum = 0;
        int realSellSum = 0;
        int legalSellSum = 0;
        private ArrayList<PlayerDay> playerDays = new ArrayList<>();
    }

    private int offDays[] = {7,18, 8,7, 8,8, 9,17, 9,25, 9,27, 10,5, 12,27, 2,4, 2,11, 2,18, 3,8, 3,20, 3,21, 3,22, 4,1, 4,2, 4,22, 4,23};

    long UTCDiff = 0L;
    double queueSensivity1 = 0.05, queueSensivity2 = 0.15;
    long baseVolume1 = 2000000;
    long baseVolume2 = 500000;
    long baseVolume3 = 20000000;
    long baseValue1 = 20000000000L;
    int programCounter = 0;
    String proxy = "";//https://tsetmc.radmansarmaye.com/proxy/?url=";
    String urlMWI = proxy + "http://www.tsetmc.com/tsev2/data/MarketWatchInit.aspx?h=0&r=0";
    String urlMWP = proxy + "http://www.tsetmc.com/tsev2/data/MarketWatchPlus.aspx?h=64055&r=8498091150";
    String urlClientType = proxy + "http://www.tsetmc.com/tsev2/data/ClientTypeAll.aspx";
    String urlClientHistory = proxy + "http://www.tsetmc.com/tsev2/data/clienttype.aspx?i=";
    String urlTradeHistory = proxy + "http://tsetmc.com/tsev2/data/InstTradeHistory.aspx?i=";
    String urlTradeHistory2 = proxy + "http://members.tsetmc.com/tsev2/data/InstTradeHistory.aspx?i=";
    String urlTradeHistoryPF = "&Top=999999&A=0";
    String urlTradeHistoryPF2 = "&Top=999999&A=1";
    String urlShareholdersChange = proxy + "http://cdn.tsetmc.com/api/Shareholder/";
    String urlTradeDetail = proxy + "http://www.tsetmc.com/tsev2/data/TradeDetail.aspx?i=";
    String urlTablo = proxy + "http://tsetmc.com/Loader.aspx?ParTree=151311&i=";
    String urlShareholders = proxy + "http://www.tsetmc.com/Loader.aspx?Partree=15131T&c=";
    String urlShareholderDetails = proxy + "http://www.tsetmc.com/tsev2/data/ShareHolder.aspx?i=";
    String urlShareholderDetailsPF = "%2C";
    String urlSearch = proxy + "http://www.tsetmc.com/tsev2/data/search.aspx?skey=";
    String urlShareholdersChangeAll = proxy + "http://www.tsetmc.com/Loader.aspx?ParTree=15131I";
    String urlChart = proxy + "http://dev.tsetmc.com/Loader.aspx?ParTree=151323&i=";
    String urlStatistics = proxy + "http://tsetmc.com/tsev2/data/instValue.aspx?i=";
    String urlStatisticsPF = "&t=i";
    String urlMamnooMojaz = proxy + "http://tsetmc.com/Loader.aspx?Partree=15131L&i=";
    String urlCapInc = proxy + "http://www.tsetmc.com/Loader.aspx?Partree=15131H&i=";

    String lastSOHMSent = "";
    String lastPSSent1 = "";
    String lastPSSent2 = "";
    String redCircle = "\uD83D\uDD34";
    String greenCircle = "\uD83D\uDFE2";
    String orangeCircle = "\uD83D\uDFE0️️";
    String purpleCircle = "\uD83D\uDFE3";
    private List<String> numberEmojis = Arrays.asList("0️⃣", "1️⃣" ,"2️⃣" ,"3️⃣" ,"4️⃣" ,"5️⃣" ,"6️⃣" ,"7️⃣" ,"8️⃣" ,"9️⃣" ,"\uD83D\uDD1F");

    private static class ID {
        private static final String FARDIN = "90960800", FARDIN2 = "672001949",
                PUBLIC_CHANNELOLD = "-1001451001298", PUBLIC_CHANNEL = "-1001238033098", TABLOBOT_CHANNEL = "@tablobot_channel",
                PRIVATE_QUEUE = "-1001473394510", PRIVATE_CHANNEL3 = "-1001204613764", PRIVATE_LARGE_BUY = "-1001172690079",
                PRIVATE_CODETOCODE = "-1001453587444", PRIVATE_SHAREHOLDERS = "-1001301617853", PUBLIC_CHANNEL2 = "-1001375795883",
                PLAYERS = "-1001348066614", ALI_MORADI_CHANNEL = "-1001500793717", MAFIA_CHANNEL = "-1001754351511", ALI_MORADI = "1705167567",
                IMAN = "265701449";
    }
    private static class CHART_TYPE {
        private static final int WITHOUT_MOVING_AVERAGE = 1, MOVING_AVERAGE_ALL = 2, MOVING_AVERAGE3 = 3, MOVING_AVERAGE5 = 4,
                MOVING_AVERAGE7 = 5, MOVING_AVERAGE10 = 6;
    }
    private static class SYMBOL_TYPE {
        private static final String BOURSE = "بورس", FARABOURSE = "فرابورس", BAZAR_PAYEH = "بازارپایه",
                SANDOGH = "صندوق", HAGH_TAGHADOM = "حق_تقدم";
    }

    public void run() {
        LocalTime time;
        boolean firstJobDone, secondJobDone, errClear;
        while (true) {
            try {
                firstJobDone = false;
                secondJobDone = false;
                errClear = false;
                while (true) {
                    time = LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS));
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(new Date());
                    if (!isOffDay(calendar) && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY &&
                            calendar.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY &&
                            (time.getHour() == 9 || time.getHour() == 10 || time.getHour() == 11
                            || time.getHour() == 12 && time.getMinute() <= 30))
                        break;
                    if(!errClear && time.getHour() == 5){
                        PrintWriter err = new PrintWriter("err.txt");
                        err.print("");
                        err.close();
                        errClear = true;
                    }
                    if(!firstJobDone && !isOffDay(calendar)
                            && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY
                            && time.getHour() == 8) {

                        publicSendTime = new int[20];
                        public2SendTime = new int[20];
                        shareholdersSendTime = new int[20];
                        otherPrivateSendTime = new int[20];
                        fardinSendTime = new int[20];
                        psti = 0; p2sti = 0; shsti = 0; opsti = 0; fsti = 0;
                        clearFiles();
                        httpClients.clear();
                        httpResponses.clear();
                        httpGets.clear();
                        for (int i = 0; i < 10; i++){
                            httpClients.add(HttpClients.createDefault());
                            httpResponses.add(null);
                            httpGets.add(null);
                        }
                        SendMessage message = new SendMessage();
                        message.setChatId(ID.FARDIN);
                        message.setText("Started1");
                        customSendMessage(1, message, null, null, ID.FARDIN, true);
                        System.out.println(LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS)) + " Started");
                        marketWatchInit(false, true);
                        initSomeArrayList(true);
//                        message.setText("market watch init done1");
//                        customSendMessage(1, message, null, null, ID.FARDIN, true);
                        System.out.println("\n" + LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS)) + " market watch init done.");
                        instTradeHistory();
//                        message.setText("inst trade history done1");
//                        customSendMessage(1, message, null, null, ID.FARDIN, true);
                        System.out.println("\n" + LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS)) + " inst trade history done.");
                        statistics();
//                        message.setText("statistics done1");
//                        customSendMessage(1, message, null, null, ID.FARDIN, true);
                        System.out.println("\n" + LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS)) + " statistics done.");
                        firstJobDone = true;
                    }
                    if(!secondJobDone && !isOffDay(calendar)
                            && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY
                            && time.getHour() == 8 && time.getMinute() >= 15){
                        SendMessage message = new SendMessage();
                        message.setChatId(ID.FARDIN);
//                        message.setText("checkShareholders and update PNL started");
//                        customSendMessage(1, message, null, null, ID.FARDIN, true);
                        System.out.println("\n" + LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS)) + " checkShareholders and update PNL started.");
                        checkShareholders(true);
                        updateProfitAndLoss();
//                        message.setText("checkShareholders and update PNL done");
                        message.setText("Done");
                        customSendMessage(1, message, null, null, ID.FARDIN, true);
                        System.out.println("\n" + LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS)) + " checkShareholders and update PNL done.");
                        secondJobDone = true;
                    }
                    if(time.getHour() == 8 && time.getMinute() >= 58)
                        Thread.sleep(5000);
                    else
                        Thread.sleep(60000);
                }

                buyAvgOver40 = new ArrayList<>();
                buyAvgOver60 = new ArrayList<>();
                buyAvgOver80 = new ArrayList<>();
                
                if(!firstJobDone) {
                    publicSendTime = new int[20];
                    public2SendTime = new int[20];
                    shareholdersSendTime = new int[20];
                    otherPrivateSendTime = new int[20];
                    fardinSendTime = new int[20];
                    psti = 0; p2sti = 0; shsti = 0; opsti = 0; fsti = 0;
                    httpClients.clear();
                    httpResponses.clear();
                    httpGets.clear();
                    for (int i = 0; i < 10; i++){
                        httpClients.add(HttpClients.createDefault());
                        httpResponses.add(null);
                        httpGets.add(null);
                    }
                    SendMessage message = new SendMessage();
                    message.setChatId(ID.FARDIN);
                    message.setText("Started2");
                    customSendMessage(1, message, null, null, ID.FARDIN, true);
                    System.out.println(LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS)) + " Start");
                    marketWatchInit(false, true);
                    initSomeArrayList(true);
//                    message.setText("market watch init done2");
//                    customSendMessage(1, message, null, null, ID.FARDIN, true);
                    System.out.println("\n" + LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS)) + " market watch init done.");
                    instTradeHistory();
//                    message.setText("inst trade history and updatePNL done2");
//                    customSendMessage(1, message, null, null, ID.FARDIN, true);
                    System.out.println("\n" + LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS)) + " inst trade history done.");
                    statistics();
//                    message.setText("statistics done2");
//                    customSendMessage(1, message, null, null, ID.FARDIN, true);
                    System.out.println("\n" + LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS)) + " statistics done.");

                    try {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(new Date());
                        int today = calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);
                        String filePath = "players/" + changeGregorianToPersian(String.valueOf(today));
                        File file = new File(filePath);
                        if(file.exists()){
                            Scanner sc = new Scanner(new FileInputStream(filePath));
                            String tempStr;
                            int rb = 0, lb = 0, rs = 0, ls = 0, index;
                            while (sc.hasNext()) {
                                tempStr = sc.next();
                                if (sc.hasNext())
                                    sc.nextDouble();
                                if (sc.hasNext())
                                    sc.nextDouble();
                                if (sc.hasNext())
                                    sc.nextDouble();
                                if (sc.hasNext())
                                    rb = sc.nextInt();
                                if (sc.hasNext())
                                    lb = sc.nextInt();
                                if (sc.hasNext())
                                    rs = sc.nextInt();
                                if (sc.hasNext())
                                    ls = sc.nextInt();
                                index = symbolNickName.indexOf(tempStr);
                                if (index >= 0) {
                                    realBuyAccumulate.set(index, rb);
                                    legalBuyAccumulate.set(index, lb);
                                    realSellAccumulate.set(index, rs);
                                    legalSellAccumulate.set(index, ls);
                                }
                            }
                            sc.close();
                        }
                        filePath = "players/" + changeGregorianToPersian(String.valueOf(today)) + "_2";
                        file = new File(filePath);
                        if(file.exists()){
                            Scanner sc = new Scanner(new FileInputStream(filePath));
                            String tempStr;
                            int rb = 0, lb = 0, rs = 0, ls = 0, index;
                            while (sc.hasNext()) {
                                tempStr = sc.next();
                                if (sc.hasNextDouble())
                                    sc.nextDouble();
                                if (sc.hasNextDouble())
                                    sc.nextDouble();
                                if (sc.hasNextDouble())
                                    sc.nextDouble();
                                if (sc.hasNextInt())
                                    rb = sc.nextInt();
                                if (sc.hasNextInt())
                                    lb = sc.nextInt();
                                if (sc.hasNextInt())
                                    rs = sc.nextInt();
                                if (sc.hasNextInt())
                                    ls = sc.nextInt();
                                index = symbolNickName.indexOf(tempStr);
                                if (index >= 0) {
                                    realBuyAccumulate2.set(index, rb);
                                    legalBuyAccumulate2.set(index, lb);
                                    realSellAccumulate2.set(index, rs);
                                    legalSellAccumulate2.set(index, ls);
                                }
                            }
                            sc.close();
                        }

                        
                        filePath = "buyAvgOver";
                        file = new File(filePath);
                        if(file.exists()){
                            Scanner sc = new Scanner(new FileInputStream(filePath));
                            String tempStr;
                            while (sc.hasNext()) {
                                tempStr = sc.next();
                                if(tempStr.equals("***"))
                                    break;
                                else
                                    buyAvgOver40.add(tempStr);
                            }
                            while (sc.hasNext()) {
                                tempStr = sc.next();
                                if(tempStr.equals("***"))
                                    break;
                                else
                                    buyAvgOver60.add(tempStr);
                            }
                            while (sc.hasNext()) {
                                tempStr = sc.next();
                                if(tempStr.equals("***"))
                                    break;
                                else
                                    buyAvgOver80.add(tempStr);
                            }
                            sc.close();
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        
                    }
                }

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                int today = calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);

                priceChartData = new ArrayList<>();
//                priceChartDataAvg = new ArrayList<>();
                priceChartDataBlackIndex = new ArrayList<>();
                exValueChartData = new ArrayList<>();
                buyVolChartData = new ArrayList<>();
                buyCntChartData = new ArrayList<>();
                sellVolChartData = new ArrayList<>();
                sellCntChartData = new ArrayList<>();
                volPassed1B = new ArrayList<>();
                int symbolNumber = symbolID.size();
                int x = priceChartData.size();
                for(; x < symbolNumber; x++){
                    priceChartData.add(new ArrayList<>());
//                    priceChartDataAvg.add(new ArrayList<>());
                    priceChartDataBlackIndex.add(0);
                    exValueChartData.add(new ArrayList<>());
                    buyVolChartData.add(new ArrayList<>());
                    buyCntChartData.add(new ArrayList<>());
                    sellVolChartData.add(new ArrayList<>());
                    sellCntChartData.add(new ArrayList<>());
                    volPassed1B.add(false);
                }
                halfHours = new ArrayList<>();
                halfHoursIndex = new ArrayList<>();

                sellerPowU3 = new ArrayList<>();
                sellerPowU2 = new ArrayList<>();
                sellerPowU1_5 = new ArrayList<>();
                sellerPowU1_2 = new ArrayList<>();
                spu3BuyAvg = new ArrayList<>();
                spu3SellAvg = new ArrayList<>();
                spu2BuyAvg = new ArrayList<>();
                spu2SellAvg = new ArrayList<>();
                spu1_5BuyAvg = new ArrayList<>();
                spu1_5SellAvg = new ArrayList<>();
                symbolNickNameY.clear();
                exVolumeY.clear();
                Scanner sc = new Scanner(new FileInputStream("exVolumes"));
                while (sc.hasNext()) {
                    symbolNickNameY.add(sc.next());
                    if(sc.hasNext())
                        exVolumeY.add(sc.nextLong());
                    else if(symbolNickNameY.size() > 0)
                        symbolNickNameY.remove(symbolNickNameY.size() - 1);
                }
                sc.close();

//                totalMarketCapChange = new ArrayList<>();
//                totalMarketCapLastSentTime = 0;
//                totalMarketCapMsgID = 0;
//                if(time.getHour() >= 9) {
//                    sc = new Scanner(new FileInputStream("totalMarketCapChange"));
//                    if (sc.hasNext())
//                        totalMarketCapMsgID = sc.nextInt();
//                    while (sc.hasNext())
//                        totalMarketCapChange.add(sc.nextFloat());
//                    sc.close();
//                }


//                final int totalThreads = 2;
//                ArrayList<Thread> tradeDetailsThreads = new ArrayList<>();
//                for (int i = 0; i < totalThreads; i++)
//                    tradeDetailsThreads.add(new Thread());

                marketWatchInit(false, true);
                initSomeArrayList(false);
                if(time.getHour() == 8 && time.getMinute() <= 59)
                    writeYesterdayLastPrice();
                else if(time.getHour() >= 9){
                    yesterdayLastPrice.clear();
                    symbolNumber = symbolID.size();
                    for(int i = 0; i < symbolNumber; i++)
                        yesterdayLastPrice.add(yesterdayClosingPrice.get(i));
                    sc = new Scanner(new FileInputStream("yesterdayLastPrice"));
                    while (sc.hasNext()){
                        int symbolIndex = symbolID.indexOf(sc.next());
                        if(symbolIndex > -1)
                            yesterdayLastPrice.set(symbolIndex, sc.nextInt());
                        else
                            sc.next();
                    }
                }
                threadChart = new Thread();

                boolean playersDone = false, cmdDone = false;
                while (true) {
                    time = LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS));
//                    for (int i = 0; i < totalThreads; i++){
//                        if(!tradeDetailsThreads.get(i).isAlive() &&
//                                (time.getHour() == 9 && time.getMinute() >= 5
//                                        || time.getHour() == 10 || time.getHour() == 11
//                                        || time.getHour() == 12 && time.getMinute() <= 40)){
//                            final int i2 = i;
//                            Runnable tradeDetailsTask = () -> {
//                                try {
//                                    tradeDetails(i2, totalThreads);
//                                }catch (IOException | InterruptedException e){
//                                    e.printStackTrace();
//                                    
//                                }
//                            };
//                            tradeDetailsThreads.set(i, new Thread(tradeDetailsTask));
//                            tradeDetailsThreads.get(i).start();
//                        }
//                    }

                    // Chart
                    if(time.getSecond() <= 5) {
                        try {
                            System.out.print("P1");
                            String nickName = "";
                            sc = new Scanner(new FileInputStream("oneChart"));
                            if (sc.hasNext())
                                nickName = sc.next();
                            sc.close();
                            PrintWriter pw = new PrintWriter("oneChart");
                            pw.print("");
                            pw.close();
                            System.out.print("P2");
                            if (!nickName.equals("") && symbolNickName.contains(nickName)) {
                                saveChartImage(true, nickName, CHART_TYPE.WITHOUT_MOVING_AVERAGE);
                                SendPhoto message = new SendPhoto().setPhoto(new File("chart.png"));
                                customSendMessage(2, null, message, null, ID.FARDIN, false);
                                SendDocument message2 = new SendDocument().setDocument(new File("chart.txt"));
                                customSendMessage(3, null, null, message2, ID.FARDIN, false);

                            }
                            if(nickName.equals("شاخص")){
                                saveStockIndexChartImage();
                                SendPhoto message = new SendPhoto().setPhoto(new File("chart.png"));
                                customSendMessage(2, null, message, null, ID.FARDIN, false);
                            }
                            System.out.println("P3");
                        } catch (IOException e) {
                            e.printStackTrace();
                            
                        }
                    }
//                    if(chartDataCounter % 12 == 0) {
//                        int symbolNumber = symbolID.size();
//                        FileWriter fw = new FileWriter("chartData", true);
//                        for (int i = 0; i < symbolNumber; i++){
//
//                        }
//                        fw.close();
//                    }
                    LocalTime time2 = LocalTime.now();
                    int milli0 = time2.getHour() * 3600000 + time2.getMinute() * 60000 + time2.getSecond() * 1000 + time2.getNano() / 1000000;
                    boolean validData = marketWatchPlus();
                    time2 = LocalTime.now();
                    int milli1 = time2.getHour() * 3600000 + time2.getMinute() * 60000 + time2.getSecond() * 1000 + time2.getNano() / 1000000;

                    if (validData) {
                        if(time.getHour() != 8) {
                            clientTypeAll();
                        }
                        time2 = LocalTime.now();
                        int milli2 = time2.getHour() * 3600000 + time2.getMinute() * 60000 + time2.getSecond() * 1000 + time2.getNano() / 1000000;
                        analyze();
                        time2 = LocalTime.now();
                        int milli3 = time2.getHour() * 3600000 + time2.getMinute() * 60000 + time2.getSecond() * 1000 + time2.getNano() / 1000000;
                        positiveRange();
                        time2 = LocalTime.now();
                        int milli4 = time2.getHour() * 3600000 + time2.getMinute() * 60000 + time2.getSecond() * 1000 + time2.getNano() / 1000000;
                        System.out.print("[" + (milli1 - milli0) + "," + (milli2 - milli1) + "," + (milli3 - milli2) + "," + (milli4 - milli3) + "]");
                        while (true){
                            time = LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS));
                            if(time.getSecond() % 5 == 0)
                                break;
                            Thread.sleep(250);
                        }
                        System.out.println("#" + time);
                        time2 = LocalTime.now();
                        FileWriter fw = new FileWriter("lastruntime");
                        fw.write(String.valueOf(time2.getHour() * 3600 + time2.getMinute() * 60 + time2.getSecond()));
                        fw.close();
                        programCounter++;
                    }
                    else {
                        System.out.println("market watch init: Invalid Data1");
                        marketWatchInit(false, true);
                        initSomeArrayList(false);
                        instTradeHistory();
                        statistics();
                        time2 = LocalTime.now();
                        if (time2.getSecond() < 8) {
                            SendMessage message = new SendMessage().setText("market watch init: Invalid Data1");
                            customSendMessage(1, message, null, null, ID.FARDIN, true);
                        }
                    }
                    if(time.getMinute() % 2 == 0 && time.getSecond() < 5){
                        System.out.print("P4");
                        marketWatchInit(false, false);
                        initSomeArrayList(false);
                        System.out.print("P5");
                        FileWriter fileWriter = new FileWriter("players/" + changeGregorianToPersian(String.valueOf(today)));
                        FileWriter fileWriter2 = new FileWriter("players/" + changeGregorianToPersian(String.valueOf(today)) + "_2");
                        for (int i = 0; i < symbolNickName.size(); i++){
                            int secondsNow = time.getHour() * 3600 + time.getMinute() * 60 + time.getSecond();
                            long marketCap = totalVolume.get(i) * closingPrice2.get(i) / 10000000L; // Million Toman
                            if(realBuyAccumulate.get(i) >= 200) {
                                int rba = (realBuyAccumulate.get(i) >= 100) ? realBuyAccumulate.get(i) : 0;
                                int lba = (legalBuyAccumulate.get(i) >= 100) ? legalBuyAccumulate.get(i) : 0;
                                int rsa = (realSellAccumulate.get(i) >= 100) ? realSellAccumulate.get(i) : 0;
                                int lsa = (legalSellAccumulate.get(i) >= 100) ? legalSellAccumulate.get(i) : 0;
                                float buyRatio = (float) (realBuyAccumulate.get(i) * 100) / (float) marketCap;
                                float sellRatio = (float) (realSellAccumulate.get(i) * 100) / (float) marketCap;
                                fileWriter.write(symbolNickName.get(i) + " " + String.format("%.1f", (float) marketCap / 1000000F) + " "
                                        + String.format("%.3f", buyRatio) + " " + String.format("%.3f", sellRatio) + " "
                                        + rba + " " + lba + " " + rsa + " " + lsa + "\n");
                            }
                            if(realBuyAccumulate2.get(i) >= 200) {
                                int rba = (realBuyAccumulate2.get(i) >= 100) ? realBuyAccumulate2.get(i) : 0;
                                int lba = (legalBuyAccumulate2.get(i) >= 100) ? legalBuyAccumulate2.get(i) : 0;
                                int rsa = (realSellAccumulate2.get(i) >= 100) ? realSellAccumulate2.get(i) : 0;
                                int lsa = (legalSellAccumulate2.get(i) >= 100) ? legalSellAccumulate2.get(i) : 0;
                                float buyRatio = (float) (realBuyAccumulate2.get(i) * 100) / (float) marketCap;
                                float sellRatio = (float) (realSellAccumulate2.get(i) * 100) / (float) marketCap;
                                fileWriter2.write(symbolNickName.get(i) + " " + String.format("%.1f", (float) marketCap / 1000000F) + " "
                                        + String.format("%.3f", buyRatio) + " " + String.format("%.3f", sellRatio) + " "
                                        + rba + " " + lba + " " + rsa + " " + lsa + "\n");
                            }
                        }
                        fileWriter.close();
                        fileWriter2.close();
//                        System.out.print("P6");
//                        fileWriter = new FileWriter("totalMarketCapChange");
//                        fileWriter.write(totalMarketCapMsgID + " ");
//                        for (int i = 0; i < totalMarketCapChange.size(); i++)
//                            fileWriter.write(String.format("%.3f ", totalMarketCapChange.get(i)));
//                        fileWriter.close();
                        System.out.print("P7");
                        sc = new Scanner(new FileInputStream("watchlist"));
                        String tempWatchlist = "";
                        if(sc.hasNextLine()){
                            tempWatchlist = sc.nextLine();
                            System.out.println("\n<" + tempWatchlist + ">");
                        }
                        sc.close();
                        PrintWriter pw = new PrintWriter("watchlist"); pw.print(""); pw.close();
                        if(!tempWatchlist.equals(""))
                            sellQWatchlist = tempWatchlist;
                        System.out.print("P8");
                    }
                    if(!cmdDone && time.getSecond() % 15 < 5){
                        sc = new Scanner(new FileInputStream("cmd"));
                        String cmd = "";
                        if(sc.hasNextLine()){
                            cmd = sc.nextLine();
                        }
                        sc.close();
                        PrintWriter pw = new PrintWriter("cmd"); pw.print(""); pw.close();
                        if(!cmd.equals("")){
                            if(cmd.equals("رنج کانال یک")){
                                rangeChatID = ID.PUBLIC_CHANNEL;
                            }
                        }
                        cmdDone = true;
                    }
                    if(time.getSecond() % 15 >= 5){
                        cmdDone = false;
                    }
                    if(time.getHour() >= 10 && time.getMinute() == 0 ||
                            time.getHour() == 12 && time.getMinute() % 5 == 0 && time.getMinute() >= 10)
                        System.out.print("Q1");
                    if(!playersDone && (time.getHour() >= 10 && time.getMinute() == 0 ||
                            time.getHour() == 12 && (time.getMinute() == 10 || time.getMinute() == 29))){
                        System.out.print("Q2");
                        String toSend1 = "", toSend2 = "", toSend3 = "", toSend4 = "";
                        ArrayList<String> chartList = new ArrayList<>();
                        FileWriter fileWriter = new FileWriter("players/all", true);
                        FileWriter fileWriter2 = new FileWriter("players/all2", true);
                        for (int i = 0; i < symbolNickName.size(); i++){
                            int rba = (realBuyAccumulate.get(i) >= 100) ? realBuyAccumulate.get(i):0;
                            int rsa = (realSellAccumulate.get(i) >= 100) ? realSellAccumulate.get(i):0;
                            int lba = (legalBuyAccumulate.get(i) >= 100) ? legalBuyAccumulate.get(i):0;
                            int lsa = (legalSellAccumulate.get(i) >= 100) ? legalSellAccumulate.get(i):0;
                            int rba2 = (realBuyAccumulate2.get(i) >= 100) ? realBuyAccumulate2.get(i):0;
                            int rsa2 = (realSellAccumulate2.get(i) >= 100) ? realSellAccumulate2.get(i):0;
                            int lba2 = (legalBuyAccumulate2.get(i) >= 100) ? legalBuyAccumulate2.get(i):0;
                            int lsa2 = (legalSellAccumulate2.get(i) >= 100) ? legalSellAccumulate2.get(i):0;
                            long marketCap = totalVolume.get(i) * closingPrice2.get(i) / 10000000L; // Million Toman
                            float buyRatio = (float)(realBuyAccumulate.get(i) * 100) / (float)marketCap;
                            float sellRatio = (float)(realSellAccumulate.get(i) * 100) / (float)marketCap;
                            float buyRatio2 = (float)(realBuyAccumulate2.get(i) * 100) / (float)marketCap;
                            float sellRatio2 = (float)(realSellAccumulate2.get(i) * 100) / (float)marketCap;
                            String str1 = symbolNickName.get(i) + " " + String.format("%.1f", (float)marketCap / 1000000F) + " "
                                    + String.format("%.2f",buyRatio) + " " + String.format("%.2f",sellRatio) + " "
                                    + rba + " " + lba + " " + rsa + " " + lsa;
                            String str2 = symbolNickName.get(i) + " " + String.format("%.1f", (float)marketCap / 1000000F) + " "
                                    + String.format("%.2f",buyRatio2) + " " + String.format("%.2f",sellRatio2) + " "
                                    + rba2 + " " + lba2 + " " + rsa2 + " " + lsa2;
                            String dateStr = changeGregorianToPersian(String.valueOf(today));
                            if(rba >= 500 && rba >= rsa * 2 && buyRatio >= 0.05 ||
                                    rba >= 700 && rba >= rsa * 3/2 && buyRatio >= 0.06 ||
                                    rba >= 1000 && rba >= rsa && buyRatio >= 0.07){
                                if(!chartList.contains(symbolNickName.get(i)))
                                    chartList.add(symbolNickName.get(i));
                                toSend1 += "#" + str1 + " "
                                        + formattedDate(dateStr) + "\n";
                                if(time.getHour() == 12 && time.getMinute() == 30)
                                    fileWriter.write(str1 + " " + dateStr + "\n");
                            }
                            if(rsa >= 500 && rsa >= rba * 2 && sellRatio >= 0.05 ||
                                    rsa >= 700 && rsa >= rba * 3/2 && sellRatio >= 0.06 ||
                                    rsa >= 1000 && rsa > rba && sellRatio >= 0.07){
                                toSend2 += "#" + str1 + " "
                                        + formattedDate(dateStr) + "\n";
                                if(time.getHour() == 12 && time.getMinute() == 30)
                                    fileWriter.write(str1 + " " + dateStr + "\n");
                            }
                            if(rba2 >= 1000 && rba2 >= rsa2 * 2 && buyRatio2 >= 0.15||
                                    rba2 >= 1500 && rba2 >= rsa2 * 3/2 && buyRatio2 >= 0.20 ||
                                    rba2 >= 2000 && rba2 >= rsa2 && buyRatio2 >= 0.25){
                                if(!chartList.contains(symbolNickName.get(i)))
                                    chartList.add(symbolNickName.get(i));
                                toSend3 += "#" + str2 + " "
                                        + formattedDate(dateStr) + "\n";
                                if(time.getHour() == 12 && time.getMinute() == 30)
                                    fileWriter2.write(str2 + " " + dateStr + "\n");
                            }
                            if(rsa2 >= 1000 && rsa2 >= rba2 * 2 && sellRatio2 >= 0.15||
                                    rsa2 >= 1500 && rsa2 >= rba2 * 3/2 && sellRatio2 >= 0.20 ||
                                    rsa2 >= 2000 && rsa2 > rba2 && sellRatio2 >= 0.25){
                                toSend4 += "#" + str2 + " "
                                        + formattedDate(dateStr) + "\n";
                                if(time.getHour() == 12 && time.getMinute() == 30)
                                    fileWriter2.write(str2 + " " + dateStr + "\n");
                            }
                        }
                        fileWriter.close();
                        fileWriter2.close();
                        playersStatus("players/all", true);
//                        playersStatus("players/all2", true);
//                        if(toSend1.length() > 0 || toSend2.length() > 0) {
//                            String toSend = "نوع یک\n";
//                            if(toSend1.length() > 0)
//                                toSend += "خرید\n" + toSend1;
//                            if(toSend2.length() > 0)
//                                toSend += "فروش\n" + toSend2;
//                            sendLongMessage(toSend, ID.PLAYERS, true);
//                        }
//                        if(toSend3.length() > 0 || toSend4.length() > 0) {
//                            String toSend = "نوع دو\n";
//                            if(toSend3.length() > 0)
//                                toSend += "خرید\n" + toSend3;
//                            if(toSend4.length() > 0)
//                                toSend += "فروش\n" + toSend4;
//                            sendLongMessage(toSend, ID.PLAYERS, true);
//                        }
                        System.out.print("Q3");
                        final LocalTime time3 = time;
                        final int L3 = priceChartData.get(0).size();
                        Runnable task1 = () -> {
                            try {
                                if (time3.getHour() == 12 && (time3.getMinute() == 10 || time3.getMinute() == 30)) {
//                                    if (L3 > 1500) {
//                                        for (int i = 0; i < chartList.size(); i++) {
//                                            saveChartImage(true, chartList.get(i), CHART_TYPE.WITHOUT_MOVING_AVERAGE);
//                                            SendPhoto message = new SendPhoto()
//                                                    .setCaption("#" + chartList.get(i))
//                                                    .setPhoto(new File("chart.png"));
//                                            customSendMessage(2, null, message, null, ID.PLAYERS, true);
//                                        }
//                                    }
                                    FileWriter fileWriter3 = new FileWriter("exVolumes");
                                    for (int i = 0; i < symbolNickName.size(); i++) {
                                        fileWriter3.write(symbolNickName.get(i) + " " + exVolume2.get(i) + "\n");
                                    }
                                    fileWriter3.close();
                                }
                                if (L3 > 1500 && time3.getHour() == 12 && time3.getMinute() == 10) {
                                    System.out.println("\nwriting chart images started at: "
                                            + LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS)));
                                    saveChartImage(false,"", CHART_TYPE.WITHOUT_MOVING_AVERAGE);
                                    System.out.println("\nwriting chart images finished at: "
                                            + LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS)));
                                }
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                        };
                        System.out.print("Q4");
                        if(!threadChart.isAlive()){
                            System.out.print("Q5");
                            threadChart = new Thread(task1);
                            threadChart.start();
                            System.out.print("Q6");
                        }
                        System.out.print("Q7");
                        playersDone = true;
                    }
                    if(time.getMinute() % 5 > 0){
                        playersDone = false;
                    }
                    if(time.getMinute() % 15 == 0 && time.getSecond() < 5){
                        checkHotMoneyAndOrderHistory(true);
//                        playersStatus("players/all", true);
                    }
                    if (time.getHour() == 12 && time.getMinute() == 31) {
                        if(priceChartData.get(0).size() > 1500) {
                            System.out.println("\nwriting chart images started at: "
                                    + LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS)));
                            saveChartImage(false, "", CHART_TYPE.WITHOUT_MOVING_AVERAGE);
                            System.out.println("\nwriting chart images finished at: "
                                    + LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS)));
                        }
//                        playersStatus("players/all", true);
//                        playersStatus("players/all2", true);
                        checkHotMoneyAndOrderHistory(true);
                        for (int i = 0; i < 10; i++)
                            httpClients.get(i).close();
                        writeYesterdayLastPrice();
                        players1MsgID = 0;
                        players2MsgID = 0;
                        hotMoneySupportOrderMsgID = 0;
                        System.out.println("stop");
                        SendMessage message = new SendMessage().setText("stop").setChatId(ID.FARDIN);
                        execute(message);
                        break;
                    }

                }
            } catch (IOException | TelegramApiException | InterruptedException e) {
//                StackTraceElement[] stackTrace = e.getStackTrace();
//                boolean containApache = false;
//                for (int i = 0; i < stackTrace.length; i++) {
//                    if (stackTrace[i].toString().contains("apache")) {
//                        containApache = true;
//                        break;
//                    }
//                }
//                if(!containApache) {
                    e.printStackTrace();
                    
//                }
            }
        }
    }

    private void clearFiles() throws IOException{
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int today = calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);
        PrintWriter pw = new PrintWriter("out.txt"); pw.print(""); pw.close();
        pw = new PrintWriter("sbx3.txt"); pw.print(""); pw.close();
        pw = new PrintWriter("sbx4.txt"); pw.print(""); pw.close();
        pw = new PrintWriter("ctc"); pw.print(""); pw.close();
        pw = new PrintWriter("ctc0"); pw.print(""); pw.close();
        pw = new PrintWriter("ctc1"); pw.print(""); pw.close();
        pw = new PrintWriter("ctcOut0"); pw.print(""); pw.close();
        pw = new PrintWriter("ctcOut"); pw.print(""); pw.close();
        pw = new PrintWriter("ctcOut1"); pw.print(""); pw.close();
        pw = new PrintWriter("chartData"); pw.print(""); pw.close();
        pw = new PrintWriter("totalMarketCapChange"); pw.print(""); pw.close();
        pw = new PrintWriter("buyAvgOver"); pw.print(""); pw.close();
        pw = new PrintWriter("players/" + changeGregorianToPersian(String.valueOf(today))); pw.print(""); pw.close();
    }
    private boolean isOffDay(Calendar calendar){
        for (int i = 0; i < offDays.length; i++){
            int month = calendar.get(Calendar.MONTH) + 1;
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            if(offDays[i] == month && offDays[i+1] == dayOfMonth)
                return true;
            i++;
        }
        return false;
    }
    public void sendStackTrace(Exception e){
        StackTraceElement[] stackTrace = e.getStackTrace();
        String str = e.toString() + "\n";
        for (int i = 0; i < stackTrace.length; i++)
            str += stackTrace[i].toString() + "\n";

        if(str.length() > 0){
            try {
                if(str.length() < 4000) {
                    SendMessage message = new SendMessage().setText(str).setChatId(ID.FARDIN);
                    customSendMessage(1, message, null, null, ID.FARDIN, true);
                }
                else{
                    SendMessage message = new SendMessage().setText(str.substring(0, 4000)).setChatId(ID.FARDIN);
                    customSendMessage(1, message, null, null, ID.FARDIN, true);
                }
            }
            catch (TelegramApiException | InterruptedException e2){
                e2.printStackTrace();
            }
        }
    }
    private String httpTask(String url) {
        final StringBuilder sb = new StringBuilder("");
        Runnable task1 = () -> {
            try {
                httpGets.set(0, new HttpGet(url));
//                System.out.print("X1");
                httpResponses.set(0, httpClients.get(0).execute(httpGets.get(0)));
                System.out.print("<" + httpResponses.get(0).getStatusLine().getStatusCode() + httpResponses.get(0).getStatusLine().getReasonPhrase() + ">");
                HttpEntity entity = httpResponses.get(0).getEntity();
//                System.out.print("X2");
                if (entity != null && httpResponses.get(0).getStatusLine().getStatusCode() == 200) {
                    String result = EntityUtils.toString(entity);
//                    System.out.print("X3");
                    sb.append(result);
                }
//                System.out.print("X4");
            }
            catch (IOException e){
//                e.printStackTrace();
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
                    LocalTime time2 = LocalTime.now();
                    int seconds = time2.getHour() * 3600 + time2.getMinute() * 60 + time2.getSecond();
                    if(seconds >= 15900 && seconds <= 28800){
                        FileWriter fw = new FileWriter("lastruntime");
                        fw.write(String.valueOf(seconds));
                        fw.close();
                    }
                    System.out.print("Y1");
                    if(thread1.isAlive())
                        thread1.interrupt();
                    httpClients.get(0).close();
                    httpClients.set(0, HttpClients.createDefault());
                    sb.delete(0,sb.length());
                    thread1 = new Thread(task1);
                    thread1.start();
                    System.out.print("Y2");
//                    if(threadTimer % 120 == 119){
//                        SendMessage message = new SendMessage();
//                        message.setChatId(ID.FARDIN2);
//                        message.setText("⚠️⚠️⚠️");
//                        execute(message);
//                    }
                }
                if (sb.length() > 200)
                    break;
            }
        }
        catch (InterruptedException | IOException e){
//            e.printStackTrace();
        }
        return sb.toString();
    }
    private String httpTask2(int httpIndex, String url, int responseLengthLimit, int waitTimeSec) {
        final StringBuilder sb = new StringBuilder("");
        Runnable task1 = () -> {
            try {
                httpGets.set(httpIndex, new HttpGet(url));
                httpResponses.set(httpIndex, httpClients.get(httpIndex).execute(httpGets.get(httpIndex)));
                HttpEntity entity = httpResponses.get(httpIndex).getEntity();
                if (entity != null && httpResponses.get(httpIndex).getStatusLine().getStatusCode() == 200) {
                    String result = EntityUtils.toString(entity);
                    sb.append(result);
                }
            }
            catch (IOException e){
//                e.printStackTrace();
            }
        };
        Thread thread1 = new Thread(task1);
        thread1.start();
        int threadTimer = 0;

        try {
            while (true) {
                Thread.sleep(50);
                threadTimer++;
                if (threadTimer % (20 * waitTimeSec) == (20 * waitTimeSec) - 1 && threadTimer < (60 * waitTimeSec)) {
                    LocalTime time2 = LocalTime.now();
                    int seconds = time2.getHour() * 3600 + time2.getMinute() * 60 + time2.getSecond();
                    if(seconds >= 15900 && seconds <= 28800){
                        FileWriter fw = new FileWriter("lastruntime");
                        fw.write(String.valueOf(seconds));
                        fw.close();
                    }
                    if(thread1.isAlive())
                        thread1.interrupt();
                    httpClients.get(httpIndex).close();
                    httpClients.set(httpIndex, HttpClients.createDefault());
                    sb.delete(0,sb.length());
                    thread1 = new Thread(task1);
                    thread1 .start();
                }
                if(threadTimer == (60 * waitTimeSec)){
//                    SendMessage message = new SendMessage();
//                    message.setChatId(ID.FARDIN2);
//                    message.setText("⚠️⚠️⚠️");
//                    execute(message);
                    if(thread1.isAlive())
                        thread1.interrupt();
                    break;
                }
                if (sb.length() > responseLengthLimit)
                    break;
            }
        }
        catch (InterruptedException | IOException e){
//            e.printStackTrace();
        }
        return sb.toString();
    }
    
    private long[] stringNextLong(String str, char seperating, int i){
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
    private double[] stringNextDouble(String str, char seperating, int i){
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
    private String[] stringNextString(String str, char seperating, int i){
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
    private int stringCountChar(String str, char seperating, int count, int i){
        int charCnt = 0;
        int strLen = str.length();
        for(; i < strLen; i++){
            if(str.charAt(i) == seperating) {
                charCnt++;
                if(charCnt == count)
                    break;
            }
        }
        return i+1;
    }
    private boolean isSaham(String name){
        if((name.charAt(name.length() - 1) < '0' || name.charAt(name.length() - 1) > '9'
                || name.equals("انرژی1") || name.equals("انرژی2") || name.equals("انرژی3"))
                && !symbolIgnore.contains(name))
            return true;
        else
            return false;
    }

    private void initSomeArrayList(boolean clear){
        if(clear) {
            lastExchangeTime.clear();
            realBuyVolumeDiff.clear();
            realSellVolumeDiff.clear();
            legalBuyVolumeDiff.clear();
            legalSellVolumeDiff.clear();
            realBuyAccumulate.clear();
            legalBuyAccumulate.clear();
            realSellAccumulate.clear();
            legalSellAccumulate.clear();
            realBuyAccumulate2.clear();
            legalBuyAccumulate2.clear();
            realSellAccumulate2.clear();
            legalSellAccumulate2.clear();
            lastCheckedRow.clear();
            lastCheckedRow2.clear();
            counter1.clear();
            counter2.clear();
            queueAnnounced.clear();
            queueMaxVolume.clear();
            counter2Timer.clear();
            supportOrderTimer.clear();
            supportOrderType.clear();
            supportOrderCounter.clear();
            totalCodeToCodeVolume.clear();
            totalCodeToCodeCount.clear();
            totalCodeToCodeVolume2.clear();
            totalCodeToCodeCount2.clear();
            volumeCounter.clear();
            volumeCounterTimer.clear();
            volumeAnnounced.clear();
            rangeCounter.clear();
            rangeCounterTimer.clear();
            rangeAnnounced.clear();
            lastSupportPrice.clear();
            lastSupportVolume.clear();
            firstSupportVolume.clear();
            lastSupportTime.clear();
            buyAvgIncLastTime.clear();
            volumeBuffer = new long[defaultSymbolsCount][volumeBufferSize];
            priceBuffer = new long[defaultSymbolsCount][priceBufferSize];
            priceAvgBuffer = new long[defaultSymbolsCount][priceBufferSize];

            priceChartData.clear();
//            priceChartDataAvg.clear();
            priceChartDataBlackIndex.clear();
            exValueChartData.clear();
            buyVolChartData.clear();
            buyCntChartData.clear();
            sellVolChartData.clear();
            sellCntChartData.clear();
            volPassed1B.clear();
            buyAvgMax.clear();
            buyerPowerMax.clear();
            inQueueDays.clear();
            valAvg1Month.clear();
            valAvg3Month.clear();
            valAvg12Month.clear();
            volAvg1Month.clear();
            volAvg3Month.clear();
            volAvg12Month.clear();
            exQAvg1Month.clear();
            exQAvg3Month.clear();
            exQAvg12Month.clear();
        }
        int i = lastExchangeTime.size();
        for (; i < symbolID.size(); i++){
            lastExchangeTime.add(0);
            realBuyVolumeDiff.add(0L);
            realSellVolumeDiff.add(0L);
            legalBuyVolumeDiff.add(0L);
            legalSellVolumeDiff.add(0L);
            realBuyAccumulate.add(0);
            legalBuyAccumulate.add(0);
            realSellAccumulate.add(0);
            legalSellAccumulate.add(0);
            realBuyAccumulate2.add(0);
            legalBuyAccumulate2.add(0);
            realSellAccumulate2.add(0);
            legalSellAccumulate2.add(0);
            lastCheckedRow.add(2);
            lastCheckedRow2.add(2);
            counter1.add(false);
            counter2.add(0);
            queueAnnounced.add(false);
            queueMaxVolume.add(0L);
            counter2Timer.add(0);
            supportOrderTimer.add(0);
            supportOrderType.add(0);
            supportOrderCounter.add(0);
            totalCodeToCodeVolume.add(0L);
            totalCodeToCodeCount.add(0L);
            totalCodeToCodeVolume2.add(0L);
            totalCodeToCodeCount2.add(0L);
            volumeCounter.add(0);
            volumeCounterTimer.add(0);
            volumeAnnounced.add(false);
            rangeCounter.add(0);
            rangeCounterTimer.add(0);
            rangeAnnounced.add(false);
            lastSupportPrice.add(0);
            lastSupportVolume.add(0L);
            firstSupportVolume.add(0L);
            lastSupportTime.add(0);
            buyAvgIncLastTime.add(0);
            inQueueDays.add(0);
            valAvg1Month.add(0L);
            valAvg3Month.add(0L);
            valAvg12Month.add(0L);
            volAvg1Month.add(0L);
            volAvg3Month.add(0L);
            volAvg12Month.add(0L);
            exQAvg1Month.add(0);
            exQAvg3Month.add(0);
            exQAvg12Month.add(0);

            priceChartData.add(new ArrayList<>());
//            priceChartDataAvg.add(new ArrayList<>());
            priceChartDataBlackIndex.add(0);
            exValueChartData.add(new ArrayList<>());
            buyVolChartData.add(new ArrayList<>());
            buyCntChartData.add(new ArrayList<>());
            sellVolChartData.add(new ArrayList<>());
            sellCntChartData.add(new ArrayList<>());
            volPassed1B.add(false);
            buyAvgMax.add(0L);
            buyerPowerMax.add(0F);
            int cp = closingPrice2.get(i);
            int size = priceChartData.get(0).size();
            for(int j = 0; j < size; j++){
                priceChartData.get(i).add(cp);
//                priceChartDataAvg.get(i).add(cp);
                if(j % barWidth == 0) {
                    exValueChartData.get(i).add(0);
                    buyVolChartData.get(i).add(0);
                    buyCntChartData.get(i).add(1);
                    sellVolChartData.get(i).add(0);
                    sellCntChartData.get(i).add(1);
                }
            }
        }
    }
    private void writeYesterdayLastPrice() throws IOException{
        FileWriter fileWriter = new FileWriter("yesterdayLastPrice");
        int symbolNumber = symbolID.size();
        yesterdayLastPrice.clear();
        for(int i = 0; i < symbolNumber; i++){
            yesterdayLastPrice.add(lastPrice2.get(i));
            fileWriter.write(symbolID.get(i) + " " + lastPrice2.get(i) + "\n");
        }
        fileWriter.close();
    }

    private void marketWatchInit(boolean allSymbols, boolean clear) throws InterruptedException, IOException, TelegramApiException {
        if(clear) {
            symbolID.clear();
            symbolNickName.clear();
            symbolName.clear();
            symbolType.clear();
            maxAllowedVolume.clear();
            firstPrice.clear();
            closingPrice1.clear();
            closingPrice2.clear();
            lastPrice1.clear();
            lastPrice2.clear();
            exQuantity1.clear();
            exQuantity2.clear();
            exVolume1.clear();
            exVolume2.clear();
            exValue1.clear();
            exValue2.clear();
            minPrice1.clear();
            minPrice2.clear();
            maxPrice1.clear();
            maxPrice2.clear();
            yesterdayClosingPrice.clear();
            yesterdayLastPrice.clear();
            EPS.clear();
            maxAllowed.clear();
            minAllowed.clear();
            totalVolume.clear();

            realBuyCount1.clear();
            realSellCount1.clear();
            legalBuyCount1.clear();
            legalSellCount1.clear();
            realBuyCount0.clear();
            realSellCount0.clear();
            legalBuyCount0.clear();
            legalSellCount0.clear();
            realBuyVolume1.clear();
            realSellVolume1.clear();
            legalBuyVolume1.clear();
            legalSellVolume1.clear();
            realBuyCount2.clear();
            realSellCount2.clear();
            legalBuyCount2.clear();
            legalSellCount2.clear();
            realBuyVolume2.clear();
            realSellVolume2.clear();
            legalBuyVolume2.clear();
            legalSellVolume2.clear();

            buyQuantity1a.clear();
            sellQuantity1a.clear();
            buyVolume1a.clear();
            sellVolume1a.clear();
            buyPrice1a.clear();
            sellPrice1a.clear();
            buyQuantity1.clear();
            buyQuantity2.clear();
            buyQuantity3.clear();
            buyQuantity4.clear();
            buyQuantity5.clear();
            sellQuantity1.clear();
            sellQuantity2.clear();
            sellQuantity3.clear();
            sellQuantity4.clear();
            sellQuantity5.clear();
            buyVolume1.clear();
            buyVolume2.clear();
            buyVolume3.clear();
            buyVolume4.clear();
            buyVolume5.clear();
            sellVolume1.clear();
            sellVolume2.clear();
            sellVolume3.clear();
            sellVolume4.clear();
            sellVolume5.clear();
            buyPrice1.clear();
            buyPrice2.clear();
            buyPrice3.clear();
            buyPrice4.clear();
            buyPrice5.clear();
            sellPrice1.clear();
            sellPrice2.clear();
            sellPrice3.clear();
            sellPrice4.clear();
            sellPrice5.clear();
            LocalTime time = LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS));
            lastExchangeTimeMax =  time.getHour() * 3600 + time.getMinute() * 60 + time.getSecond();
            lastSendTimeDelay = lastExchangeTimeMax;
        }
        symbolIgnore.clear();
        Scanner sc = new Scanner(new FileInputStream("symbolTypeIgnore"));
        while (sc.hasNext()) {
            symbolIgnore.add(sc.next());
        }
        sc.close();
        System.out.print("A1");
        String result = httpTask(urlMWI);
        System.out.print("A2");
//        PrintWriter writer = new PrintWriter("mwi.txt");
//        writer.print(result);
//        writer.close();
        System.out.print("A3");
        int i = 0;
        int resultLength = result.length();
        String temp;
        for (; i < resultLength; i++) {
            if (result.charAt(i) == '@') {
                i++;
                break;
            }
        }
        for (; i < resultLength; i++) {
            if (result.charAt(i) == '@') {
                i++;
                break;
            }
        }
        System.out.print("A4");
        boolean endOfPart1 = false;
        long[] tempLongArray = {0,0};
        String toSend1 = "";
        while (true) {
            boolean allowedSymbol = false;
            temp = "";
            for (; i < resultLength; i++) { // symbol ID
                if (result.charAt(i) == ',') {
                    i++;
                    break;
                }
                temp += result.charAt(i);
            }
            String symbolIDTemp = (temp.equals("")) ? "0":temp;
            for (; i < resultLength; i++) { // symbol code
                if (result.charAt(i) == ',') {
                    i++;
                    break;
                }
            }
            temp = "";
            for (; i < resultLength; i++) { // symbol nick name
                if (result.charAt(i) == ',') {
                    i++;
                    break;
                }
                else if(result.charAt(i) == ' ')
                    temp += '_';
                else if(result.charAt(i) == 'ك')
                    temp += 'ک';
                else if(result.charAt(i) == 'ي')
                    temp += 'ی';
                else
                    temp += result.charAt(i);
            }
            temp = (temp.equals("")) ? "0":temp;
            if(temp.equals("معیار_"))
                temp = "معیار";
            if(!symbolID.contains(symbolIDTemp) && (!allSymbols && isSaham(temp)
                    || allSymbols
                    && !temp.equals("گواهی_ظرفیت")
                    && !(temp.length() >= 4 && temp.substring(0,4).equals("سنفت"))
                    && !(temp.length() >= 3 && temp.substring(0,3).equals("پست"))
                    && !(temp.length() >= 3 && temp.substring(0,3).equals("زعف"))
                    && !(temp.length() >= 3 && temp.substring(0,3).equals("زیر"))
                    && !(temp.length() >= 3 && temp.substring(0,3).equals("سکه"))
                    && !(temp.length() >= 3 && temp.substring(0,3).equals("کشم"))
                    && !(temp.length() >= 3 && temp.substring(0,3).equals("رطب"))
                    && !(temp.length() >= 5 && temp.substring(0,5).equals("عیلام"))
                    && !(temp.length() >= 4 && temp.substring(0,4).equals("عپلی"))
                    && !(temp.length() >= 6 && temp.substring(0,6).equals("عفولاد"))
                    && !(temp.length() >= 5 && temp.substring(0,5).equals("عکاوه"))
                    && !(temp.length() >= 7 && temp.substring(0,7).equals("عسناسنگ")))){
                allowedSymbol = true;
                symbolID.add(symbolIDTemp);
                symbolNickName.add(temp);
            }
            temp = "";
            for (; i < resultLength; i++) { // symbol name
                if (result.charAt(i) == ',') {
                    i++;
                    break;
                }
                temp += result.charAt(i);
            }
            temp = (temp.equals("")) ? "0":temp;
            if(allowedSymbol)
                symbolName.add(temp);
            for (; i < resultLength; i++) { // last deal time
                if (result.charAt(i) == ',') {
                    i++;
                    break;
                }
            }
//            temp = "";
//            for (; i < resultLength; i++) { // first price
//                if (result.charAt(i) == ',') {
//                    i++;
//                    break;
//                }
//                if(result.charAt(i) >= '0' && result.charAt(i) <= '9')
//                    temp += result.charAt(i);
//                else{
//                    temp = "";
//                    break;
//                }
//            }
//            temp = (temp.equals("")) ? "0":temp;
            tempLongArray = stringNextLong(result, ',', i);
            i = (int)tempLongArray[1];
            if(allowedSymbol)
                firstPrice.add((int)tempLongArray[0]);
            tempLongArray = stringNextLong(result, ',', i);
            i = (int)tempLongArray[1];
            if(allowedSymbol) {
                closingPrice1.add((int)tempLongArray[0]);
                closingPrice2.add((int)tempLongArray[0]);
            }
            tempLongArray = stringNextLong(result, ',', i);
            i = (int)tempLongArray[1];
            if(allowedSymbol) {
                lastPrice1.add((int)tempLongArray[0]);
                lastPrice2.add((int)tempLongArray[0]);
            }
            tempLongArray = stringNextLong(result, ',', i);
            i = (int)tempLongArray[1];
            if(allowedSymbol) {
                exQuantity1.add((int)tempLongArray[0]);
                exQuantity2.add((int)tempLongArray[0]);
            }
            tempLongArray = stringNextLong(result, ',', i);
            i = (int)tempLongArray[1];
            if(allowedSymbol) {
                exVolume1.add(tempLongArray[0]);
                exVolume2.add(tempLongArray[0]);
            }
            tempLongArray = stringNextLong(result, ',', i);
            i = (int)tempLongArray[1];
            if(allowedSymbol) {
                exValue1.add(tempLongArray[0]);
                exValue2.add(tempLongArray[0]);
            }
            tempLongArray = stringNextLong(result, ',', i);
            i = (int)tempLongArray[1];
            if(allowedSymbol) {
                minPrice1.add((int)tempLongArray[0]);
                minPrice2.add((int)tempLongArray[0]);
            }
            tempLongArray = stringNextLong(result, ',', i);
            i = (int)tempLongArray[1];
            if(allowedSymbol) {
                maxPrice1.add((int)tempLongArray[0]);
                maxPrice2.add((int)tempLongArray[0]);
            }
            tempLongArray = stringNextLong(result, ',', i);
            i = (int)tempLongArray[1];
            if(allowedSymbol){
                yesterdayClosingPrice.add((int)tempLongArray[0]);
                yesterdayLastPrice.add((int)tempLongArray[0]);
            }
            tempLongArray = stringNextLong(result, ',', i);
            i = (int)tempLongArray[1];
            if(allowedSymbol)
                EPS.add((int)tempLongArray[0]);
            int tempcnt = 0;
            for (; i < resultLength; i++) {
                if (result.charAt(i) == ',') {
                    tempcnt++;
                }
                if (tempcnt == 4) {
                    i++;
                    break;
                }
            }
            tempLongArray = stringNextLong(result, '.', i);
            i = (int)tempLongArray[1];
            if(allowedSymbol)
                maxAllowed.add((int)tempLongArray[0]);
            for (; i < resultLength; i++) {
                if (result.charAt(i) == ',') {
                    i++;
                    break;
                }
            }
            tempLongArray = stringNextLong(result, '.', i);
            i = (int)tempLongArray[1];
            if(allowedSymbol)
                minAllowed.add((int)tempLongArray[0]);
            for (; i < resultLength; i++) {
                if (result.charAt(i) == ',') {
                    i++;
                    break;
                }
            }
            tempLongArray = stringNextLong(result, ',', i);
            i = (int)tempLongArray[1];
            if(allowedSymbol)
                totalVolume.add(tempLongArray[0]);
            for (; i < resultLength; i++) {
                if (result.charAt(i) == '@') {
                    i++;
                    endOfPart1 = true;
                    break;
                }
                if (result.charAt(i) == ';') {
                    i++;
                    break;
                }
            }
            if(endOfPart1)
                break;
            if(i == resultLength){
                System.out.print("market watch init: Invalid Data2");
                SendMessage message = new SendMessage().setText("market watch init: Invalid Data2");
                customSendMessage(1, message, null, null, ID.FARDIN, true);
                Thread.sleep(1000);
                marketWatchInit(false, true);
                initSomeArrayList(false);
                instTradeHistory();
                statistics();
                return;
            }
        }
        int symbolIDSize = symbolID.size();
        System.out.print("A5," + symbolIDSize);
        int j = symbolType.size();
        for (; j < symbolIDSize; j++) {
            symbolType.add("");
            maxAllowedVolume.add(0);
            realBuyCount1.add(0);
            realSellCount1.add(0);
            legalBuyCount1.add(0);
            legalSellCount1.add(0);
            realBuyCount0.add(0);
            realSellCount0.add(0);
            legalBuyCount0.add(0);
            legalSellCount0.add(0);
            realBuyVolume1.add(0L);
            realSellVolume1.add(0L);
            legalBuyVolume1.add(0L);
            legalSellVolume1.add(0L);
            realBuyCount2.add(1);
            realSellCount2.add(1);
            legalBuyCount2.add(1);
            legalSellCount2.add(1);
            realBuyVolume2.add(0L);
            realSellVolume2.add(0L);
            legalBuyVolume2.add(0L);
            legalSellVolume2.add(0L);

            buyQuantity1a.add(0);
            sellQuantity1a.add(0);
            buyVolume1a.add(0L);
            sellVolume1a.add(0L);
            buyPrice1a.add(0);
            sellPrice1a.add(0);
            buyQuantity1.add(0);
            buyQuantity2.add(0);
            buyQuantity3.add(0);
            buyQuantity4.add(0);
            buyQuantity5.add(0);
            sellQuantity1.add(0);
            sellQuantity2.add(0);
            sellQuantity3.add(0);
            sellQuantity4.add(0);
            sellQuantity5.add(0);
            buyVolume1.add(0L);
            buyVolume2.add(0L);
            buyVolume3.add(0L);
            buyVolume4.add(0L);
            buyVolume5.add(0L);
            sellVolume1.add(0L);
            sellVolume2.add(0L);
            sellVolume3.add(0L);
            sellVolume4.add(0L);
            sellVolume5.add(0L);
            buyPrice1.add(0);
            buyPrice2.add(0);
            buyPrice3.add(0);
            buyPrice4.add(0);
            buyPrice5.add(0);
            sellPrice1.add(0);
            sellPrice2.add(0);
            sellPrice3.add(0);
            sellPrice4.add(0);
            sellPrice5.add(0);
        }
        try {
            sc = new Scanner(new FileInputStream("symbolTypeBourse"));
            while (sc.hasNext()) {
                int index = symbolNickName.indexOf(sc.next());
                if(index >= 0){
                    symbolType.set(index, SYMBOL_TYPE.BOURSE);
//                    if(closingPrice2.get(index) < 5000)
//                        maxAllowedVolume.set(index, 400000);
//                    else if(closingPrice2.get(index) < 10000)
//                        maxAllowedVolume.set(index, 200000);
//                    else if(closingPrice2.get(index) < 100000)
//                        maxAllowedVolume.set(index, 100000);
//                    else
//                        maxAllowedVolume.set(index, 50000);
                }
            }
            sc.close();
            sc = new Scanner(new FileInputStream("symbolTypeFarabourse"));
            while (sc.hasNext()) {
                int index = symbolNickName.indexOf(sc.next());
                if(index >= 0){
                    symbolType.set(index, SYMBOL_TYPE.FARABOURSE);
//                    if(totalVolume.get(index) * (long)closingPrice2.get(index) > 2000000000000L)
//                        maxAllowedVolume.set(index, 200000);
//                    else
//                        maxAllowedVolume.set(index, 100000);
                }
            }
            sc.close();
            sc = new Scanner(new FileInputStream("symbolTypeBazarPayeh"));
            while (sc.hasNext()) {
                int index = symbolNickName.indexOf(sc.next());
                if(index >= 0){
                    symbolType.set(index, SYMBOL_TYPE.BAZAR_PAYEH);
//                    if(totalVolume.get(index) * (long)closingPrice2.get(index) > 2000000000000L)
//                        maxAllowedVolume.set(index, 200000);
//                    else
//                        maxAllowedVolume.set(index, 100000);
                }
            }
            sc.close();
            sc = new Scanner(new FileInputStream("symbolTypeSandogh"));
            while (sc.hasNext()) {
                int index = symbolNickName.indexOf(sc.next());
                if(index >= 0){
                    symbolType.set(index, SYMBOL_TYPE.SANDOGH);
//                    if(symbolNickName.get(index).equals("زر"))
//                        maxAllowedVolume.set(index, 10000);
//                    if(symbolNickName.get(index).equals("پارتین"))
//                        maxAllowedVolume.set(index, 25000);
//                    if(symbolNickName.get(index).equals("افق_ملت") || symbolNickName.get(index).equals("بذر") ||
//                            symbolNickName.get(index).equals("دارا_یکم") || symbolNickName.get(index).equals("کاردان") ||
//                            symbolNickName.get(index).equals("فیروزه") || symbolNickName.get(index).equals("صنوین"))
//                        maxAllowedVolume.set(index, 50000);
//                    else
//                        maxAllowedVolume.set(index, 100000);
                }
            }
            sc.close();

        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        int symbolTypeSize = symbolType.size();
        for (int k = 0; k < symbolTypeSize; k++){
            if(symbolNickName.get(k).charAt(symbolNickName.get(k).length() - 1) == 'ح'){
                symbolType.set(k, SYMBOL_TYPE.HAGH_TAGHADOM);
//                int index = symbolNickName.indexOf(symbolNickName.get(k).substring(0,symbolNickName.get(k).length() - 1));
//                if(index > -1)
//                    maxAllowedVolume.set(k, maxAllowedVolume.get(index));
            }

        }

        for (int k = 0; k < symbolID.size(); k++){
            if(symbolType.get(k).equals(""))
                toSend1 += symbolNickName.get(k) + " ";
        }
        if(!allSymbols){
            try {
                LocalTime time = LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS));
                if(toSend1.length() > 0 && time.getHour() == 12 && time.getMinute() > 25) {
                    SendMessage message = new SendMessage().setText("نوع بازار\n" + toSend1);
                    customSendMessage(1, message, null, null, ID.FARDIN, true);
                }
            }
            catch (TelegramApiException e){
                e.printStackTrace();
            }
        }
        System.out.print("A6," + (j - symbolIDSize));
    }


    private boolean marketWatchPlus() throws IOException {
        System.out.print("B1");
        String result = httpTask(urlMWP);
        System.out.print("B2");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int today = calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);
//        FileWriter fileWriter = new FileWriter("mwp/" + today + "-p" + LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS)) + ".txt");
//        fileWriter.write(result);
//        fileWriter.close();
        System.out.print("B3");
        int i = 0;
        int resultLength = result.length();
        String temp;
        int tempCnt = 0;
        for (; i < resultLength; i++) {
            if (result.charAt(i) == '@') {
                tempCnt++;
            }
            if(tempCnt == 2)
            {
                i++;
                break;
            }
        }
        if (i < resultLength && result.charAt(i) == '@')
            i++;
        if (i < resultLength && result.charAt(i) == '@')
            i++;
        if (i < resultLength && result.charAt(i) == '@')
            i++;
        boolean endOfPart1 = false, endOfPart2 = false;
        int index;
        System.out.print("B4");
        long[] tempLongArray = {0,0};
        while(true) {
            temp = "";
            for (; i < resultLength; i++) { // symbol ID
                if (result.charAt(i) == ',') {
                    i++;
                    break;
                }
                temp += result.charAt(i);
            }
            temp = (temp.equals("")) ? "0":temp;
            index = symbolID.indexOf(temp);
            tempLongArray = stringNextLong(result, ',', i);
            i = (int)tempLongArray[1];
            if(index >= 0){
                int hours = (int)tempLongArray[0] / 10000;
                int minutes = ((int)tempLongArray[0] % 10000) / 100;
                int seconds = (int)tempLongArray[0] % 100;
                seconds = hours * 3600 + minutes * 60 + seconds;
                if(seconds > lastExchangeTimeMax)
                    lastExchangeTimeMax = seconds;
                lastExchangeTime.set(index, seconds);
            }

            tempLongArray = stringNextLong(result, ',', i);
            i = (int)tempLongArray[1];
            if(index >= 0)
                firstPrice.set(index, (int)tempLongArray[0]);
            tempLongArray = stringNextLong(result, ',', i);
            i = (int)tempLongArray[1];
            if(index >= 0)
                closingPrice2.set(index, (int)tempLongArray[0]);
            tempLongArray = stringNextLong(result, ',', i);
            i = (int)tempLongArray[1];
            if(index >= 0){
                lastPrice2.set(index, (int)tempLongArray[0]);
                priceBuffer[index][priceBufferIndex] = (int)tempLongArray[0];
            }
            tempLongArray = stringNextLong(result, ',', i);
            i = (int)tempLongArray[1];
            if(index >= 0 && (int)tempLongArray[0] >= exQuantity2.get(index))
                exQuantity2.set(index, (int)tempLongArray[0]);
            tempLongArray = stringNextLong(result, ',', i);
            i = (int)tempLongArray[1];
            if(index >= 0){
                if(tempLongArray[0] < exVolume2.get(index))
                    tempLongArray[0] = exVolume2.get(index);
                exVolume2.set(index, tempLongArray[0]);
                if(exVolume1.get(index) == 0)
                    exVolume1.set(index, tempLongArray[0]);
                volumeBuffer[index][volumeBufferIndex] = tempLongArray[0] - exVolume1.get(index);
            }
            tempLongArray = stringNextLong(result, ',', i);
            i = (int)tempLongArray[1];
            if(index >= 0 && tempLongArray[0] >= exValue2.get(index))
                exValue2.set(index, tempLongArray[0]);
            tempLongArray = stringNextLong(result, ',', i);
            i = (int)tempLongArray[1];
            if(index >= 0)
                minPrice2.set(index, (int)tempLongArray[0]);
            temp = "";
            for (; i < resultLength; i++) { // max price
                if (result.charAt(i) == '@') {
                    i++;
                    endOfPart1 = true;
                    break;
                }
                if (result.charAt(i) == ';') {
                    i++;
                    break;
                }
                if(result.charAt(i) >= '0' && result.charAt(i) <= '9' || result.charAt(i) == '-' && temp.length() == 0)
                    temp += result.charAt(i);
                else{
                    temp = "";
                    break;
                }
            }
            temp = (temp.equals("")) ? "0":temp;
            if(index >= 0)
                maxPrice2.set(index, Integer.valueOf(temp));
            if(i == resultLength || endOfPart1)
                break;
        }
        if(i == resultLength)
        {
            System.out.println("$1");
            return false;
        }

        int row, sellQuantity, buyQuantity, buyPrice, sellPrice;
        long buyVolume, sellVolume;

        System.out.print("B5");
        while (true) {
            temp = "";
            for (; i < resultLength; i++) { // symbol ID
                if (result.charAt(i) == ',') {
                    i++;
                    break;
                }
                temp += result.charAt(i);
            }
            if (i >= resultLength) 
                break;
            temp = (temp.equals("")) ? "0":temp;
            index = symbolID.indexOf(temp);
            row = result.charAt(i) - '0';
            i += 2;
            tempLongArray = stringNextLong(result, ',', i);
            i = (int)tempLongArray[1];
            sellQuantity = (int)tempLongArray[0];
            tempLongArray = stringNextLong(result, ',', i);
            i = (int)tempLongArray[1];
            buyQuantity = (int)tempLongArray[0];
            tempLongArray = stringNextLong(result, ',', i);
            i = (int)tempLongArray[1];
            buyPrice = (int)tempLongArray[0];
            tempLongArray = stringNextLong(result, ',', i);
            i = (int)tempLongArray[1];
            sellPrice = (int)tempLongArray[0];
            tempLongArray = stringNextLong(result, ',', i);
            i = (int)tempLongArray[1];
            buyVolume = tempLongArray[0];
            temp = "";
            for (; i < resultLength; i++) { // sell Volume
                if (result.charAt(i) == '@') {
                    endOfPart2 = true;
                    break;
                }
                if (result.charAt(i) == ';') {
                    i++;
                    break;
                }
                if(result.charAt(i) >= '0' && result.charAt(i) <= '9' || result.charAt(i) == '-' && temp.length() == 0)
                    temp += result.charAt(i);
                else{
                    temp = "";
                    break;
                }
            }
            temp = (temp.equals("")) ? "0":temp;
            sellVolume = Long.valueOf(temp);
            if(index >= 0) {
                if (row == 1) {
                    buyQuantity1.set(index, buyQuantity);
                    sellQuantity1.set(index, sellQuantity);
                    buyVolume1.set(index, buyVolume);
                    sellVolume1.set(index, sellVolume);
                    buyPrice1.set(index, buyPrice);
                    sellPrice1.set(index, sellPrice);
                }
                else if (row == 2) {
                    buyQuantity2.set(index, buyQuantity);
                    sellQuantity2.set(index, sellQuantity);
                    buyVolume2.set(index, buyVolume);
                    sellVolume2.set(index, sellVolume);
                    buyPrice2.set(index, buyPrice);
                    sellPrice2.set(index, sellPrice);
                }
                else if (row == 3) {
                    buyQuantity3.set(index, buyQuantity);
                    sellQuantity3.set(index, sellQuantity);
                    buyVolume3.set(index, buyVolume);
                    sellVolume3.set(index, sellVolume);
                    buyPrice3.set(index, buyPrice);
                    sellPrice3.set(index, sellPrice);
                }
                else if (row == 4) {
                    buyQuantity4.set(index, buyQuantity);
                    sellQuantity4.set(index, sellQuantity);
                    buyVolume4.set(index, buyVolume);
                    sellVolume4.set(index, sellVolume);
                    buyPrice4.set(index, buyPrice);
                    sellPrice4.set(index, sellPrice);
                }
                else if (row == 5) {
                    buyQuantity5.set(index, buyQuantity);
                    sellQuantity5.set(index, sellQuantity);
                    buyVolume5.set(index, buyVolume);
                    sellVolume5.set(index, sellVolume);
                    buyPrice5.set(index, buyPrice);
                    sellPrice5.set(index, sellPrice);
                }
            }
            if(i == resultLength || endOfPart2)
                break;
        }
        System.out.print("B6");

        if(i == resultLength)
            System.out.println("$2");
        else{
            // --------- Chart Data Start ---------
            System.out.print("B7");
            int symbolsCount = (symbolID.size() < defaultSymbolsCount) ? symbolID.size() : defaultSymbolsCount;
            int tempPrice;
            for (int j = 0; j < symbolsCount; j++) {
                int bp1 = buyPrice1.get(j);
                int bp2 = buyPrice2.get(j);
                int sp1 = sellPrice1.get(j);
                int sp2 = sellPrice2.get(j);
                int ycp = yesterdayClosingPrice.get(j);
                int mxl = maxAllowed.get(j);
                int mnl = minAllowed.get(j);
                int L2 = priceChartData.get(j).size();
                if (exVolume2.get(j) == 0) {
                    tempPrice = minAllowed.get(j);
                    if (bp1 >= ycp && bp1 <= mxl)
                        tempPrice = bp1;
                    else if (sp1 < ycp && sp1 >= mnl)
                        tempPrice = sp1;
                    else if (bp2 >= ycp && bp2 <= mxl)
                        tempPrice = bp2;
                    else if (sp2 < ycp && sp2 >= mnl)
                        tempPrice = sp2;
                    priceChartData.get(j).add(tempPrice);
                    if (L2 % barWidth == 0)
                        exValueChartData.get(j).add(0);
//                    priceChartData[j][chartDataCounter] = tempPrice;
//                    priceChartDataColorGray[j][chartDataCounter] = true;
                } else {
                    priceChartData.get(j).add(lastPrice2.get(j));
                    if(priceChartDataBlackIndex.get(j) == 0)
                        priceChartDataBlackIndex.set(j, L2);
                    if (L2 % barWidth == 0)
                        exValueChartData.get(j).add((int) (exValue2.get(j) / 10000000L));
//                    priceChartData[j][chartDataCounter] = lastPrice2.get(j);
//                    exValueChartData[j][chartDataCounter] = (int)(exValue2.get(j) / 10000000L);
                }
                int L = buyVolChartData.get(j).size() - 1;
                if (L2 % barWidth == 0) {
                    if (L == -1) {
                        buyVolChartData.get(j).add(0);
                        buyCntChartData.get(j).add(1);
                        sellVolChartData.get(j).add(0);
                        sellCntChartData.get(j).add(1);
                    } else {
                        buyVolChartData.get(j).add(buyVolChartData.get(j).get(L));
                        buyCntChartData.get(j).add(buyCntChartData.get(j).get(L));
                        sellVolChartData.get(j).add(sellVolChartData.get(j).get(L));
                        sellCntChartData.get(j).add(sellCntChartData.get(j).get(L));
                    }
                }
                int sum = 0;
//                int tempLen = (chartDataCounter >= (priceChartDataAvgLen - 1)) ? priceChartDataAvgLen:chartDataCounter + 1;
                int tempLen = (L2 >= (priceChartDataAvgLen - 1)) ? priceChartDataAvgLen : L2 + 1;
                for (int k = 0; k < tempLen; k++)
                    sum += priceChartData.get(j).get(L2 - k);
                priceAvgBuffer[j][priceBufferIndex] = sum / tempLen;
//                for (int k = 0; k < tempLen; k++)
//                    sum += priceChartData[j][chartDataCounter - k];
//                priceChartDataAvg[j][chartDataCounter] = sum / tempLen;
            }
            int L = priceChartData.get(0).size() - 1;
            LocalTime timeNow = LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS));
            if (timeNow.getMinute() % 30 == 0 && timeNow.getSecond() < 20
                    && (halfHoursIndex.size() == 0 ||
                    halfHoursIndex.size() > 0 && halfHoursIndex.get(halfHoursIndex.size() - 1) < L - 20)) {
                halfHoursIndex.add(L);
                String timeTemp = "";
                if (timeNow.getHour() < 10)
                    timeTemp += "0" + timeNow.getHour() + ":";
                else
                    timeTemp += timeNow.getHour() + ":";

                if (timeNow.getMinute() < 10)
                    timeTemp += "0" + timeNow.getMinute();
                else
                    timeTemp += timeNow.getMinute();
                halfHours.add(timeTemp);
            }
//            chartDataCounter++;
            System.out.print("B8");
            // --------- Chart Data End ---------
            volumeBufferIndex = (volumeBufferIndex + 1) % volumeBufferSize;
            priceBufferIndex = (priceBufferIndex + 1) % priceBufferSize;
            return true;
        }
        return false;
    }

    private void clientTypeAll() throws IOException {
        System.out.print("D1");
        String result = httpTask(urlClientType);
        System.out.print("D2");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int today = calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);
//        FileWriter fileWriter = new FileWriter("cta/" + today + "-c" + LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS)) + ".txt");
//        fileWriter.write(result);
//        fileWriter.close();
        System.out.print("D3");
        int i = 0;
        int resultLength = result.length();
        String temp;
        int index;
        int realBuyCountTemp = 0, legalBuyCountTemp = 0, realSellCountTemp = 0, legalSellCountTemp = 0;
        long realBuyVolumeTemp = 0, legalBuyVolumeTemp = 0, realSellVolumeTemp = 0, legalSellVolumeTemp = 0;
        long[] tempLongArray = {0,0};
        while(true) {
            temp = "";
            for (; i < resultLength; i++) { // symbol ID
                if (result.charAt(i) == ',') {
                    i++;
                    break;
                }
                temp += result.charAt(i);
            }
            temp = (temp.equals("")) ? "0" : temp;
            index = symbolID.indexOf(temp);
            tempLongArray = stringNextLong(result, ',', i);
            i = (int)tempLongArray[1];
            if(index >= 0)
                realBuyCountTemp = (int)tempLongArray[0];
            tempLongArray = stringNextLong(result, ',', i);
            i = (int)tempLongArray[1];
            if(index >= 0)
                legalBuyCountTemp = (int)tempLongArray[0];
            tempLongArray = stringNextLong(result, ',', i);
            i = (int)tempLongArray[1];
            if(index >= 0)
                realBuyVolumeTemp = tempLongArray[0];
            tempLongArray = stringNextLong(result, ',', i);
            i = (int)tempLongArray[1];
            if(index >= 0)
                legalBuyVolumeTemp = tempLongArray[0];
            tempLongArray = stringNextLong(result, ',', i);
            i = (int)tempLongArray[1];
            if(index >= 0)
                realSellCountTemp = (int)tempLongArray[0];
            tempLongArray = stringNextLong(result, ',', i);
            i = (int)tempLongArray[1];
            if(index >= 0)
                legalSellCountTemp = (int)tempLongArray[0];
            tempLongArray = stringNextLong(result, ',', i);
            i = (int)tempLongArray[1];
            if(index >= 0)
                realSellVolumeTemp = tempLongArray[0];
            tempLongArray = stringNextLong(result, ';', i);
            i = (int)tempLongArray[1];
            if(index >= 0) {
                legalSellVolumeTemp = tempLongArray[0];
                if(realBuyCountTemp == 0)
                    realBuyCountTemp = 1;
                if(legalBuyCountTemp == 0)
                    legalBuyCountTemp = 1;
                if(realSellCountTemp == 0)
                    realSellCountTemp = 1;
                if(legalSellCountTemp == 0)
                    legalSellCountTemp = 1;
                realBuyCount2.set(index, realBuyCountTemp);
                legalBuyCount2.set(index, legalBuyCountTemp);
                realSellCount2.set(index, realSellCountTemp);
                legalSellCount2.set(index, legalSellCountTemp);
                realBuyVolume2.set(index, realBuyVolumeTemp);
                legalBuyVolume2.set(index, legalBuyVolumeTemp);
                realSellVolume2.set(index, realSellVolumeTemp);
                legalSellVolume2.set(index, legalSellVolumeTemp);

//                if (realBuyCount0.get(index) == 0 && legalBuyCount0.get(index) == 0 && realSellCount0.get(index) == 0 && legalSellCount0.get(index) == 0 &&
//                        realBuyCount1.get(index) == 0 && legalBuyCount1.get(index) == 0 && realSellCount1.get(index) == 0 && legalSellCount1.get(index) == 0) {
//                    realBuyCount0.set(index, realBuyCountTemp);
//                    legalBuyCount0.set(index, legalBuyCountTemp);
//                    realSellCount0.set(index, realSellCountTemp);
//                    legalSellCount0.set(index, legalSellCountTemp);
//                } else if (realBuyCount0.get(index) != realBuyCountTemp || legalBuyCount0.get(index) != legalBuyCountTemp ||
//                        realSellCount0.get(index) != realSellCountTemp || legalSellCount0.get(index) != legalSellCountTemp) {
//                    realBuyCount2.set(index, realBuyCountTemp);
//                    legalBuyCount2.set(index, legalBuyCountTemp);
//                    realSellCount2.set(index, realSellCountTemp);
//                    legalSellCount2.set(index, legalSellCountTemp);
//                    realBuyVolume2.set(index, realBuyVolumeTemp);
//                    legalBuyVolume2.set(index, legalBuyVolumeTemp);
//                    realSellVolume2.set(index, realSellVolumeTemp);
//                    legalSellVolume2.set(index, legalSellVolumeTemp);
//                }
            }
            if(i == resultLength)
                break;
        }
        LocalTime timeNow = LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS));
        int symbolIDSize = symbolID.size();
        if(timeNow.getHour() == 9 && timeNow.getMinute() < 5) {
            int tempCnt = 0;
            for (i = 0; i < symbolIDSize; i++) {
                index = symbolNickNameY.indexOf(symbolNickName.get(i));
                if (index >= 0 && exVolumeY.get(index) != 0 && exVolumeY.get(index) == realBuyVolume2.get(i) + legalBuyVolume2.get(i))
                    tempCnt++;
            }
            for (i = 0; i < symbolIDSize; i++) {
                if(tempCnt > 50 || exVolume2.get(i) < realBuyVolume2.get(i) + legalBuyVolume2.get(i)) {
                    realBuyCount2.set(i, 1);
                    legalBuyCount2.set(i, 1);
                    realSellCount2.set(i, 1);
                    legalSellCount2.set(i, 1);
                    realBuyVolume2.set(i, 0L);
                    legalBuyVolume2.set(i, 0L);
                    realSellVolume2.set(i, 0L);
                    legalSellVolume2.set(i, 0L);
                }
            }
        }
        // --------- Chart Data Start ---------
        for (i = 0; i < symbolIDSize; i++) {
            int L = buyVolChartData.get(i).size() - 1;
            realBuyVolumeTemp = realBuyVolume2.get(i);
            realSellVolumeTemp = realSellVolume2.get(i);
            realBuyCountTemp = realBuyCount2.get(i);
            realSellCountTemp = realSellCount2.get(i);
            if (realBuyVolumeTemp < 1000000000L && realSellVolumeTemp < 1000000000L) {
                buyVolChartData.get(i).set(L, (int) realBuyVolumeTemp);
                sellVolChartData.get(i).set(L, (int) realSellVolumeTemp);
            } else {
                buyVolChartData.get(i).set(L, (int) (realBuyVolumeTemp / 1000L));
                sellVolChartData.get(i).set(L, (int) (realSellVolumeTemp / 1000L));
                if (!volPassed1B.get(i)) {
                    for (int j = 0; j <= L; j++) {
                        buyVolChartData.get(i).set(j, buyVolChartData.get(i).get(j) / 1000);
                        sellVolChartData.get(i).set(j, sellVolChartData.get(i).get(j) / 1000);
                    }
                    volPassed1B.set(i, true);
                }
            }
            if (realBuyCountTemp > 0)
                buyCntChartData.get(i).set(L, realBuyCountTemp);
            if (realSellCountTemp > 0)
                sellCntChartData.get(i).set(L, realSellCountTemp);
        }

//                if(chartDataCounter == 1 || chartDataCounter >= 2 &&
//                        realBuyVolumeTemp >= buyVolChartData[i][chartDataCounter - 2] &&
//                        realSellVolumeTemp >= sellVolChartData[i][chartDataCounter - 2]) {
//                    if (realBuyVolumeTemp < 1000000000L && realSellVolumeTemp < 1000000000L) {
//                        buyVolChartData[i][chartDataCounter - 1] = (int) realBuyVolumeTemp;
//                        sellVolChartData[i][chartDataCounter - 1] = (int) realSellVolumeTemp;
//                    }
//                    else {
//                        buyVolChartData[i][chartDataCounter - 1] = (int) (realBuyVolumeTemp / 1000L);
//                        sellVolChartData[i][chartDataCounter - 1] = (int) (realSellVolumeTemp / 1000L);
//                        if (!volPassed1B[i]) {
//                            for (int j = 0; j <= (chartDataCounter - 2); j++) {
//                                buyVolChartData[i][j] /= 1000;
//                                sellVolChartData[i][j] /= 1000;
//                            }
//                            volPassed1B[i] = true;
//                        }
//                    }
//                    if(realBuyCountTemp > 0)
//                        buyCntChartData[i][chartDataCounter - 1] = realBuyCountTemp;
//                    if(realSellCountTemp > 0)
//                        sellCntChartData[i][chartDataCounter - 1] = realSellCountTemp;
//                }
//                else if(chartDataCounter >= 2 &&
//                        realBuyVolumeTemp < buyVolChartData[i][chartDataCounter - 2] &&
//                        realSellVolumeTemp < sellVolChartData[i][chartDataCounter - 2]) {
//                    buyVolChartData[i][chartDataCounter - 1] = buyVolChartData[i][chartDataCounter - 2];
//                    buyCntChartData[i][chartDataCounter - 1] = buyCntChartData[i][chartDataCounter - 2];
//                    sellVolChartData[i][chartDataCounter - 1] = sellVolChartData[i][chartDataCounter - 2];
//                    sellCntChartData[i][chartDataCounter - 1] = sellCntChartData[i][chartDataCounter - 2];
//                }
//                int sumBuy = 0, sumSell = 0;
//                int tempLen = (chartDataCounter >= avgChartDataAvgLen) ? avgChartDataAvgLen:chartDataCounter;
//                for (int k = 0; k < tempLen; k++){
//                    if(buyCntChartData[i][chartDataCounter - k - 1] > 0)
//                        sumBuy += (buyVolChartData[i][chartDataCounter - k - 1] / buyCntChartData[i][chartDataCounter - k - 1]);
//                    if(sellCntChartData[i][chartDataCounter - k - 1] > 0)
//                        sumSell += (sellVolChartData[i][chartDataCounter - k - 1] / sellCntChartData[i][chartDataCounter - k - 1]);
//                }
//                buyAvgChartDataAvg[i][chartDataCounter - 1] = sumBuy / tempLen;
//                sellAvgChartDataAvg[i][chartDataCounter - 1] = sumSell / tempLen;
        // --------- Chart Data End ---------


        System.out.print("D4");
    }
    private void checkAllArraysSize(){
        int size = symbolID.size();
        String tempStr = "";
        if(size != symbolNickName.size()) tempStr += "symbolNickName " + symbolNickName.size() + " ";
        if(size != symbolName.size()) tempStr += "symbolName " + symbolName.size() + " ";
        if(size != symbolType.size()) tempStr += "symbolType " + symbolType.size() + " ";
        if(size != lastExchangeTime.size()) tempStr += "lastExchangeTime " + lastExchangeTime.size() + " ";
        if(size != maxAllowedVolume.size()) tempStr += "maxAllowedVolume " + maxAllowedVolume.size() + " ";
        if(size != firstPrice.size()) tempStr += "firstPrice " + firstPrice.size() + " ";
        if(size != closingPrice1.size()) tempStr += "closingPrice1 " + closingPrice1.size() + " ";
        if(size != closingPrice2.size()) tempStr += "closingPrice2 " + closingPrice2.size() + " ";
        if(size != lastPrice1.size()) tempStr += "lastPrice1 " + lastPrice1.size() + " ";
        if(size != lastPrice2.size()) tempStr += "lastPrice2 " + lastPrice2.size() + " ";
        if(size != exQuantity1.size()) tempStr += "exQuantity1 " + exQuantity1.size() + " ";
        if(size != exQuantity2.size()) tempStr += "exQuantity2 " + exQuantity2.size() + " ";
        if(size != exVolume1.size()) tempStr += "exVolume1 " + exVolume1.size() + " ";
        if(size != exVolume2.size()) tempStr += "exVolume2 " + exVolume2.size() + " ";
        if(size != exValue1.size()) tempStr += "exValue1 " + exValue1.size() + " ";
        if(size != exValue2.size()) tempStr += "exValue2 " + exValue2.size() + " ";
        if(size != minPrice1.size()) tempStr += "minPrice1 " + minPrice1.size() + " ";
        if(size != minPrice2.size()) tempStr += "minPrice2 " + minPrice2.size() + " ";
        if(size != maxPrice1.size()) tempStr += "maxPrice1 " + maxPrice1.size() + " ";
        if(size != maxPrice2.size()) tempStr += "maxPrice2 " + maxPrice2.size() + " ";
        if(size != yesterdayClosingPrice.size()) tempStr += "yesterdayClosingPrice " + yesterdayClosingPrice.size() + " ";
        if(size != yesterdayLastPrice.size()) tempStr += "yesterdayLastPrice " + yesterdayLastPrice.size() + " ";
        if(size != EPS.size()) tempStr += "EPS " + EPS.size() + " ";
        if(size != maxAllowed.size()) tempStr += "maxAllowed " + maxAllowed.size() + " ";
        if(size != minAllowed.size()) tempStr += "minAllowed " + minAllowed.size() + " ";
        if(size != totalVolume.size()) tempStr += "totalVolume " + totalVolume.size() + " ";
        if(size != realBuyCount0.size()) tempStr += "realBuyCount0 " + realBuyCount0.size() + " ";
        if(size != realSellCount0.size()) tempStr += "realSellCount0 " + realSellCount0.size() + " ";
        if(size != legalBuyCount0.size()) tempStr += "legalBuyCount0 " + legalBuyCount0.size() + " ";
        if(size != legalSellCount0.size()) tempStr += "legalSellCount0 " + legalSellCount0.size() + " ";
        if(size != realBuyCount1.size()) tempStr += "realBuyCount1 " + realBuyCount1.size() + " ";
        if(size != realSellCount1.size()) tempStr += "realSellCount1 " + realSellCount1.size() + " ";
        if(size != legalBuyCount1.size()) tempStr += "legalBuyCount1 " + legalBuyCount1.size() + " ";
        if(size != legalSellCount1.size()) tempStr += "legalSellCount1 " + legalSellCount1.size() + " ";
        if(size != realBuyVolume1.size()) tempStr += "realBuyVolume1 " + realBuyVolume1.size() + " ";
        if(size != realSellVolume1.size()) tempStr += "realSellVolume1 " + realSellVolume1.size() + " ";
        if(size != legalBuyVolume1.size()) tempStr += "legalBuyVolume1 " + legalBuyVolume1.size() + " ";
        if(size != legalSellVolume1.size()) tempStr += "legalSellVolume1 " + legalSellVolume1.size() + " ";
        if(size != realBuyCount2.size()) tempStr += "realBuyCount2 " + realBuyCount2.size() + " ";
        if(size != realSellCount2.size()) tempStr += "realSellCount2 " + realSellCount2.size() + " ";
        if(size != legalBuyCount2.size()) tempStr += "legalBuyCount2 " + legalBuyCount2.size() + " ";
        if(size != legalSellCount2.size()) tempStr += "legalSellCount2 " + legalSellCount2.size() + " ";
        if(size != realBuyVolume2.size()) tempStr += "realBuyVolume2 " + realBuyVolume2.size() + " ";
        if(size != realSellVolume2.size()) tempStr += "realSellVolume2 " + realSellVolume2.size() + " ";
        if(size != legalBuyVolume2.size()) tempStr += "legalBuyVolume2 " + legalBuyVolume2.size() + " ";
        if(size != legalSellVolume2.size()) tempStr += "legalSellVolume2 " + legalSellVolume2.size() + " ";
        if(size != realBuyAccumulate.size()) tempStr += "realBuyAccumulate " + realBuyAccumulate.size() + " ";
        if(size != legalBuyAccumulate.size()) tempStr += "legalBuyAccumulate " + legalBuyAccumulate.size() + " ";
        if(size != realSellAccumulate.size()) tempStr += "realSellAccumulate " + realSellAccumulate.size() + " ";
        if(size != legalSellAccumulate.size()) tempStr += "legalSellAccumulate " + legalSellAccumulate.size() + " ";
        if(size != realBuyAccumulate2.size()) tempStr += "realBuyAccumulate2 " + realBuyAccumulate2.size() + " ";
        if(size != legalBuyAccumulate2.size()) tempStr += "legalBuyAccumulate2 " + legalBuyAccumulate2.size() + " ";
        if(size != realSellAccumulate2.size()) tempStr += "realSellAccumulate2 " + realSellAccumulate2.size() + " ";
        if(size != legalSellAccumulate2.size()) tempStr += "legalSellAccumulate2 " + legalSellAccumulate2.size() + " ";
        if(size != buyQuantity1a.size()) tempStr += "buyQuantity1a " + buyQuantity1a.size() + " ";
        if(size != sellQuantity1a.size()) tempStr += "sellQuantity1a " + sellQuantity1a.size() + " ";
        if(size != buyVolume1a.size()) tempStr += "buyVolume1a " + buyVolume1a.size() + " ";
        if(size != sellVolume1a.size()) tempStr += "sellVolume1a " + sellVolume1a.size() + " ";
        if(size != buyPrice1a.size()) tempStr += "buyPrice1a " + buyPrice1a.size() + " ";
        if(size != sellPrice1a.size()) tempStr += "sellPrice1a " + sellPrice1a.size() + " ";
        if(size != buyQuantity1.size()) tempStr += "buyQuantity1 " + buyQuantity1.size() + " ";
        if(size != buyQuantity2.size()) tempStr += "buyQuantity2 " + buyQuantity2.size() + " ";
        if(size != buyQuantity3.size()) tempStr += "buyQuantity3 " + buyQuantity3.size() + " ";
        if(size != buyQuantity4.size()) tempStr += "buyQuantity4 " + buyQuantity4.size() + " ";
        if(size != buyQuantity5.size()) tempStr += "buyQuantity5 " + buyQuantity5.size() + " ";
        if(size != sellQuantity1.size()) tempStr += "sellQuantity1 " + sellQuantity1.size() + " ";
        if(size != sellQuantity2.size()) tempStr += "sellQuantity2 " + sellQuantity2.size() + " ";
        if(size != sellQuantity3.size()) tempStr += "sellQuantity3 " + sellQuantity3.size() + " ";
        if(size != sellQuantity4.size()) tempStr += "sellQuantity4 " + sellQuantity4.size() + " ";
        if(size != sellQuantity5.size()) tempStr += "sellQuantity5 " + sellQuantity5.size() + " ";
        if(size != buyVolume1.size()) tempStr += "buyVolume1 " + buyVolume1.size() + " ";
        if(size != buyVolume2.size()) tempStr += "buyVolume2 " + buyVolume2.size() + " ";
        if(size != buyVolume3.size()) tempStr += "buyVolume3 " + buyVolume3.size() + " ";
        if(size != buyVolume4.size()) tempStr += "buyVolume4 " + buyVolume4.size() + " ";
        if(size != buyVolume5.size()) tempStr += "buyVolume5 " + buyVolume5.size() + " ";
        if(size != sellVolume1.size()) tempStr += "sellVolume1 " + sellVolume1.size() + " ";
        if(size != sellVolume2.size()) tempStr += "sellVolume2 " + sellVolume2.size() + " ";
        if(size != sellVolume3.size()) tempStr += "sellVolume3 " + sellVolume3.size() + " ";
        if(size != sellVolume4.size()) tempStr += "sellVolume4 " + sellVolume4.size() + " ";
        if(size != sellVolume5.size()) tempStr += "sellVolume5 " + sellVolume5.size() + " ";
        if(size != buyPrice1.size()) tempStr += "buyPrice1 " + buyPrice1.size() + " ";
        if(size != buyPrice2.size()) tempStr += "buyPrice2 " + buyPrice2.size() + " ";
        if(size != buyPrice3.size()) tempStr += "buyPrice3 " + buyPrice3.size() + " ";
        if(size != buyPrice4.size()) tempStr += "buyPrice4 " + buyPrice4.size() + " ";
        if(size != buyPrice5.size()) tempStr += "buyPrice5 " + buyPrice5.size() + " ";
        if(size != sellPrice1.size()) tempStr += "sellPrice1 " + sellPrice1.size() + " ";
        if(size != sellPrice2.size()) tempStr += "sellPrice2 " + sellPrice2.size() + " ";
        if(size != sellPrice3.size()) tempStr += "sellPrice3 " + sellPrice3.size() + " ";
        if(size != sellPrice4.size()) tempStr += "sellPrice4 " + sellPrice4.size() + " ";
        if(size != sellPrice5.size()) tempStr += "sellPrice5 " + sellPrice5.size() + " ";
        if(size != realBuyVolumeDiff.size()) tempStr += "realBuyVolumeDiff " + realBuyVolumeDiff.size() + " ";
        if(size != realSellVolumeDiff.size()) tempStr += "realSellVolumeDiff " + realSellVolumeDiff.size() + " ";
        if(size != legalBuyVolumeDiff.size()) tempStr += "legalBuyVolumeDiff " + legalBuyVolumeDiff.size() + " ";
        if(size != legalSellVolumeDiff.size()) tempStr += "legalSellVolumeDiff " + legalSellVolumeDiff.size() + " ";
        if(size != lastCheckedRow.size()) tempStr += "lastCheckedRow " + lastCheckedRow.size() + " ";
        if(size != lastCheckedRow2.size()) tempStr += "lastCheckedRow2 " + lastCheckedRow2.size() + " ";
        if(size != counter1.size()) tempStr += "counter1 " + counter1.size() + " ";
        if(size != counter2.size()) tempStr += "counter2 " + counter2.size() + " ";
        if(size != queueAnnounced.size()) tempStr += "queueAnnounced " + queueAnnounced.size() + " ";
        if(size != queueMaxVolume.size()) tempStr += "queueMaxVolume " + queueMaxVolume.size() + " ";
        if(size != counter2Timer.size()) tempStr += "counter2Timer " + counter2Timer.size() + " ";
        if(size != supportOrderTimer.size()) tempStr += "supportOrderTimer " + supportOrderTimer.size() + " ";
        if(size != supportOrderType.size()) tempStr += "supportOrderType " + supportOrderType.size() + " ";
        if(size != supportOrderCounter.size()) tempStr += "supportOrderCounter " + supportOrderCounter.size() + " ";
        if(size != inQueueDays.size()) tempStr += "inQueueDays " + inQueueDays.size() + " ";
        if(size != valAvg1Month.size()) tempStr += "valAvg1Month " + valAvg1Month.size() + " ";
        if(size != valAvg3Month.size()) tempStr += "valAvg3Month " + valAvg3Month.size() + " ";
        if(size != valAvg12Month.size()) tempStr += "valAvg12Month " + valAvg12Month.size() + " ";
        if(size != volAvg1Month.size()) tempStr += "volAvg1Month " + volAvg1Month.size() + " ";
        if(size != volAvg3Month.size()) tempStr += "volAvg3Month " + volAvg3Month.size() + " ";
        if(size != volAvg12Month.size()) tempStr += "volAvg12Month " + volAvg12Month.size() + " ";
        if(size != exQAvg1Month.size()) tempStr += "exQAvg1Month " + exQAvg1Month.size() + " ";
        if(size != exQAvg3Month.size()) tempStr += "exQAvg3Month " + exQAvg3Month.size() + " ";
        if(size != exQAvg12Month.size()) tempStr += "exQAvg12Month " + exQAvg12Month.size() + " ";
        if(size != totalCodeToCodeVolume.size()) tempStr += "totalCodeToCodeVolume " + totalCodeToCodeVolume.size() + " ";
        if(size != totalCodeToCodeCount.size()) tempStr += "totalCodeToCodeCount " + totalCodeToCodeCount.size() + " ";
        if(size != totalCodeToCodeVolume2.size()) tempStr += "totalCodeToCodeVolume2 " + totalCodeToCodeVolume2.size() + " ";
        if(size != totalCodeToCodeCount2.size()) tempStr += "totalCodeToCodeCount2 " + totalCodeToCodeCount2.size() + " ";
        if(size != volumeCounter.size()) tempStr += "volumeCounter " + volumeCounter.size() + " ";
        if(size != volumeCounterTimer.size()) tempStr += "volumeCounterTimer " + volumeCounterTimer.size() + " ";
        if(size != volumeAnnounced.size()) tempStr += "volumeAnnounced " + volumeAnnounced.size() + " ";
        if(size != lastSupportPrice.size()) tempStr += "lastSupportPrice " + lastSupportPrice.size() + " ";
        if(size != lastSupportVolume.size()) tempStr += "lastSupportVolume " + lastSupportVolume.size() + " ";
        if(size != firstSupportVolume.size()) tempStr += "firstSupportVolume " + firstSupportVolume.size() + " ";
        if(size != lastSupportTime.size()) tempStr += "lastSupportTime " + lastSupportTime.size() + " ";
        // if(size != buyAvgOver40.size()) tempStr += "buyAvgOver40 " + buyAvgOver40.size() + " ";
        // if(size != buyAvgOver60.size()) tempStr += "buyAvgOver60 " + buyAvgOver60.size() + " ";
        // if(size != buyAvgOver80.size()) tempStr += "buyAvgOver80 " + buyAvgOver80.size() + " ";
        if(size != buyAvgMax.size()) tempStr += "buyAvgMax " + buyAvgMax.size() + " ";
        // if(size != sellerPowU3.size()) tempStr += "sellerPowU3 " + sellerPowU3.size() + " ";
        // if(size != sellerPowU2.size()) tempStr += "sellerPowU2 " + sellerPowU2.size() + " ";
        // if(size != sellerPowU1_5.size()) tempStr += "sellerPowU1_5 " + sellerPowU1_5.size() + " ";
        // if(size != sellerPowU1_2.size()) tempStr += "sellerPowU1_2 " + sellerPowU1_2.size() + " ";
        // if(size != spu3BuyAvg.size()) tempStr += "spu3BuyAvg " + spu3BuyAvg.size() + " ";
        // if(size != spu3SellAvg.size()) tempStr += "spu3SellAvg " + spu3SellAvg.size() + " ";
        // if(size != spu2BuyAvg.size()) tempStr += "spu2BuyAvg " + spu2BuyAvg.size() + " ";
        // if(size != spu2SellAvg.size()) tempStr += "spu2SellAvg " + spu2SellAvg.size() + " ";
        // if(size != spu1_5BuyAvg.size()) tempStr += "spu1_5BuyAvg " + spu1_5BuyAvg.size() + " ";
        // if(size != spu1_5SellAvg.size()) tempStr += "spu1_5SellAvg " + spu1_5SellAvg.size() + " ";
        if(size != buyerPowerMax.size()) tempStr += "buyerPowerMax " + buyerPowerMax.size() + " ";
        if(size != buyAvgIncLastTime.size()) tempStr += "buyAvgIncLastTime " + buyAvgIncLastTime.size() + " ";
        if(size != rangeCounter.size()) tempStr += "rangeCounter " + rangeCounter.size() + " ";
        if(size != rangeCounterTimer.size()) tempStr += "rangeCounterTimer " + rangeCounterTimer.size() + " ";
        if(size != rangeAnnounced.size()) tempStr += "rangeAnnounced " + rangeAnnounced.size() + " ";
        if(size != priceChartData.size()) tempStr += "priceChartData " + priceChartData.size() + " ";
        if(size != priceChartDataBlackIndex.size()) tempStr += "priceChartDataBlackIndex " + priceChartDataBlackIndex.size() + " ";
        if(size != exValueChartData.size()) tempStr += "exValueChartData " + exValueChartData.size() + " ";
        if(size != buyVolChartData.size()) tempStr += "buyVolChartData " + buyVolChartData.size() + " ";
        if(size != buyCntChartData.size()) tempStr += "buyCntChartData " + buyCntChartData.size() + " ";
        if(size != sellVolChartData.size()) tempStr += "sellVolChartData " + sellVolChartData.size() + " ";
        if(size != sellCntChartData.size()) tempStr += "sellCntChartData " + sellCntChartData.size() + " ";
        if(size != volPassed1B.size()) tempStr += "volPassed1B " + volPassed1B.size() + " ";
        if(tempStr.length() > 0)
            System.out.println("\n" + tempStr);
    }
    private void analyze() throws TelegramApiException, IOException, InterruptedException{
        // checkAllArraysSize();
        int symbolIDSize = symbolID.size();
        System.out.print("C1");
        LocalTime timeNow = LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS));
        String timeNowToString = timeNow.toString();
        if(timeNow.toString().length() >= 8)
            timeNowToString = timeNow.toString().substring(0,8);
        String toSend5 = "", toSend6 = "";

        int secondsNow = timeNow.getHour() * 3600 + timeNow.getMinute() * 60 + timeNow.getSecond();
        if(secondsNow - lastExchangeTimeMax > 60 && secondsNow - lastSendTimeDelay > 15 && secondsNow > 32460 && secondsNow < 44940) {
            SendMessage message = new SendMessage().setText("Delay: " + (secondsNow - lastExchangeTimeMax ) + "s");
            customSendMessage(1, message, null, null, ID.FARDIN, false);
            lastSendTimeDelay = secondsNow;
        }
        ArrayList<String> codeToCodeSymbolID = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int today = calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);
        today = Integer.valueOf(changeGregorianToPersian(String.valueOf(today)));
        long allYMC = 0, allTMC = 0;
        for (int i = 0; i < symbolIDSize; i++) {
            String toSend0b = "", toSend1 = "", toSend1Mafia = "",  toSend2 = "", toSend2Mafia = "",
                    toSend4 = "", toSend4b = "", toSend5b = "", toSend6b = "";
            boolean send0 = false, send1 = false, send2 = false, send3 = false, send4 = false, send5 = false;
            long tv = totalVolume.get(i);
            int cp = closingPrice2.get(i);
            int lp = lastPrice2.get(i);
            long xv2 = exVolume2.get(i);
            long sp1a = sellPrice1a.get(i);
            long sp1 = sellPrice1.get(i);
            long sv1a = sellVolume1a.get(i);
            long sv1 = sellVolume1.get(i);
            long bp1 = buyPrice1.get(i);
            long bp2 = buyPrice2.get(i);
            long bp3 = buyPrice3.get(i);
            long bp4 = buyPrice4.get(i);
            long bp5 = buyPrice5.get(i);
            long bv1 = buyVolume1.get(i);
            long bv2 = buyVolume2.get(i);
            long bv3 = buyVolume3.get(i);
            long bv4 = buyVolume4.get(i);
            long bv5 = buyVolume5.get(i);
            long bq1 = buyQuantity1.get(i);
            long bq2 = buyQuantity2.get(i);
            long bq3 = buyQuantity3.get(i);
            long bq4 = buyQuantity4.get(i);
            long bq5 = buyQuantity5.get(i);
            long mnl = minAllowed.get(i);
            long mxl = maxAllowed.get(i);
            long mnp = minPrice2.get(i);
            long mxp = maxPrice2.get(i);
            int ycp = yesterdayClosingPrice.get(i);
            int ylp = yesterdayLastPrice.get(i);
            long marketCap = tv * cp / 10000000000L;
            long maxValueAvg = 0 , maxVolumeAvg = 0;
            int maxQuantityAvg = 0;
            if (i < valAvg1Month.size() && valAvg1Month.get(i) > maxValueAvg)
                maxValueAvg = valAvg1Month.get(i);
            if (i < valAvg3Month.size() && valAvg3Month.get(i) > maxValueAvg)
                maxValueAvg = valAvg3Month.get(i);
            if (i < valAvg12Month.size() && valAvg12Month.get(i) > maxValueAvg)
                maxValueAvg = valAvg12Month.get(i);

            if (i < volAvg1Month.size() && volAvg1Month.get(i) > maxVolumeAvg)
                maxVolumeAvg = volAvg1Month.get(i);
            if (i < volAvg3Month.size() && volAvg3Month.get(i) > maxVolumeAvg)
                maxVolumeAvg = volAvg3Month.get(i);
            if (i < volAvg12Month.size() && volAvg12Month.get(i) > maxVolumeAvg)
                maxVolumeAvg = volAvg12Month.get(i);

            if (i < exQAvg1Month.size() && exQAvg1Month.get(i) > maxQuantityAvg)
                maxQuantityAvg = exQAvg1Month.get(i);
            if (i < exQAvg3Month.size() && exQAvg3Month.get(i) > maxQuantityAvg)
                maxQuantityAvg = exQAvg3Month.get(i);
            if (i < exQAvg12Month.size() && exQAvg12Month.get(i) > maxQuantityAvg)
                maxQuantityAvg = exQAvg12Month.get(i);
            if(!symbolType.get(i).equals(SYMBOL_TYPE.HAGH_TAGHADOM)){
                allYMC += (tv * ylp);
                if(xv2 == 0 && bp1 >= mnl && bp1 <= mxl)
                    allTMC += (tv * bp1);
                else
                    allTMC += (tv * lp);
            }

            long queueVolumeRatio = 666;
            if(marketCap >= 3000)
                queueVolumeRatio = (marketCap / 20) + 500;
            if(marketCap >= 10000)
                queueVolumeRatio = (marketCap / 40) + 750;
            if(marketCap >= 50000)
                queueVolumeRatio = 2000;
            // Sell Queue
            if (sp1 == mnl || sp1 == mxl) {
                if(sp1 == mnl && xv2 == 0 && sv1 * sp1 > baseValue1 && tv / sv1 < queueVolumeRatio && (4 * bv1 > sv1) && (bv1 < sv1)
                        && (timeNow.getHour() >= 9 || timeNow.getMinute() >= 55)){
                    if (!counter1.get(i)) {
                        toSend0b += "#صف_فروش #" + symbolNickName.get(i) + " " + symbolType.get(i);
                        toSend0b += " " + redCircle + redCircle + redCircle + "\n" + "حجم صف ";
                        toSend0b += String.format("%.1f",(double)sv1/1000000.0) + "M ";
                        send0 = true;
                        System.out.print("Z1");
                    }
                    counter1.set(i, true);
                }
                else if (xv2 != 0 && (sv1a - sv1 >= baseVolume2) &&
                        (sv1a * sp1a > baseValue1 && tv / sv1a < queueVolumeRatio || counter2.get(i) > 0) &&
                        (sv1a - sv1 > (long) (queueSensivity1 * (double)sv1a))) {
                    if((secondsNow  - counter2Timer.get(i)) > 180 && counter2.get(i) > 0){
                        counter2.set(i, 1);
                        queueAnnounced.set(i,false);
                    }
                    else
                        counter2.set(i, counter2.get(i) + 1);
                    System.out.print("Z2");
                    counter2Timer.set(i, secondsNow);
                    if(counter2.get(i) == 1)
                        queueMaxVolume.set(i, sv1a);
                    if (!queueAnnounced.get(i) && (counter2.get(i) == 2 || counter2.get(i) == 1 && (sv1a - sv1 > baseVolume3
                            || sv1a - sv1 > (long) (queueSensivity2 * (double)sv1a)))) {
                        if(sp1 == mnl && mnp == mxp) {
                            toSend0b += "#صف_فروش #" + symbolNickName.get(i) + " " + symbolType.get(i) + " ";
                            toSend0b += redCircle + redCircle + redCircle + "\n" + "کاهش ";
                            toSend0b += Math.round(100F * (float)(sv1a - sv1) / (float)queueMaxVolume.get(i)) + "% از صف فروش ";
                            toSend0b += String.format("%.1f", (float)lp * (float)queueMaxVolume.get(i) / 10000000000F) + " میلیارد تومانی";
                            send0 = true;
                        }
                        if(sp1 == mxl){
                            toSend5b += "#تشکیل_صف_خرید #" + symbolNickName.get(i) + " " + symbolType.get(i) + " ";
                            toSend5b += greenCircle + greenCircle + greenCircle + "\n" + "کاهش ";
                            toSend5b += Math.round(100F * (float)(sv1a - sv1) / (float)queueMaxVolume.get(i)) + "% از اردر فروش ";
                            toSend5b += String.format("%.1f", (float)lp * (float)queueMaxVolume.get(i) / 10000000000F) + " میلیارد تومانی";
                            send5 = true;
                        }
                        queueAnnounced.set(i, true);
                        System.out.print("Z3");
                    }
                }
                if(send0){
                    toSend0b += "\n" + "قیمت: " + (sp1/10) + " تومان ";
                    if(ycp != 0)
                        toSend0b += String.format("%.2f", 100F * ((float)sp1 / (float)ycp - 1F)) + "% ";
                    double qRatio = (sv1a > 0) ? (double) (100 * sv1a) / (double) tv:(double) (100 * sv1) / (double) tv;
                    toSend0b += "\nنسبت حجم صف به کل: " + String.format("%.2f", qRatio) + "%\n";
                    toSend0b += "ارزش شرکت: " + String.format("%.2f", (double) marketCap / 1000D) + " همت  ";
                    toSend0b += "<a href=\"" + urlTablo + symbolID.get(i) + "\">تابلو</a>";
                    toSend0b += "   <a href=\"" + urlChart + symbolID.get(i) + "\">چارت</a>" + "\n";
                    if(sellQWatchlist.contains(symbolNickName.get(i) + " "))
                        toSend0b += "#مستعد_نوسان ⚠️\n";
                    toSend0b += "\n";
                    sendFullChart(symbolNickName.get(i), symbolID.get(i),
                            toSend0b + timeNowToString + "\n" + ID.TABLOBOT_CHANNEL, ID.PUBLIC_CHANNEL);
                }
            }
            // Buy Queue
            /*
            else if (bp1 == mxl) {
                if(xv2 == 0 && bv1 * bp1 > baseValue1 && tv / bv1 < queueVolumeRatio && (4 * sv1 > bv1) && (sv1 < bv1)){
                    if (!counter1.get(i)) {
                        toSend0b += "#صف_خرید #" + symbolNickName.get(i) + " " + symbolType.get(i);
                        toSend0b += " " + greenCircle + greenCircle + greenCircle + "\n" + "حجم صف ";
                        toSend0b += String.format("%.1f",(double)bv1/1000000.0) + "M ";
                        send0 = true;
                    }
                    counter1.set(i, true);
                }
                else if (xv2 != 0 && (bv1a - bv1 >= baseVolume2) &&
                        (bv1a * bp1a > baseValue1 && tv / bv1a < queueVolumeRatio || counter2.get(i) > 0) &&
                        (bv1a - bv1 > (long) (queueSensivity1 * (double)bv1a))){
                    if(     ( ((timeNow.getHour()  - counter2Timer.get(i).getHour()) * 3600)
                            + ((timeNow.getMinute() - counter2Timer.get(i).getMinute()) * 60)
                            + timeNow.getSecond() - counter2Timer.get(i).getSecond()) > 300
                            && counter2.get(i) > 0){
                        queueAnnounced.set(i,false);
                        counter2.set(i, 1);
                    }
                    else
                        counter2.set(i, counter2.get(i) + 1);
                    counter2Timer.set(i, timeNow);
                    if(counter2.get(i) == 1)
                        queueMaxVolume.set(i, bv1a);
                    if (mnp == mxp && !queueAnnounced.get(i)
                            && (counter2.get(i) == 2 || counter2.get(i) == 1 && (bv1a - bv1 > baseVolume3
                                || bv1a - bv1 > (long) (queueSensivity2 * (double)bv1a)))) {
                        toSend0b += "#صف_خرید #" + symbolNickName.get(i) + " " + symbolType.get(i);
                        toSend0b += greenCircle + greenCircle + greenCircle + "\n" + "کاهش حجم ";
                        toSend0b += String.format("%.1f",(double)(bv1a - bv1)/1000000.0) + "M از ";
                        toSend0b += String.format("%.1f",(double)queueMaxVolume.get(i)/1000000.0) + "M ";
                        send0 = true;
                        queueAnnounced.set(i,true);
                    }
                }
            }*/
            else if(counter2.get(i) > 0){
                counter2.set(i, 0);
                queueAnnounced.set(i,false);
            }
            /*
            int inQDays;
            if(send0 && i < inQueueDays.size()){
                inQDays = inQueueDays.get(i);
                if (xv2 == 0 && (bp1 == mxl || sp1 == mnl) || xv2 != 0 && mnp == mxp) {
                    if(inQDays < 0)
                        inQDays = -inQDays;
                    if(inQDays == 0)
                        toSend0b += " امروز قفل صف ";
                    else {
                        if (inQDays > 99)
                            inQDays = 99;
                        if (inQDays < 10)
                            toSend0b += " " + numberEmojis.get(inQDays % 10);
                        else
                            toSend0b += " " + numberEmojis.get(inQDays % 10) + numberEmojis.get((inQDays / 10) % 10);
                        if (inQDays == 99)
                            toSend0b += "➕";
                        toSend0b += " روز قفل صف ";
                    }
                    inQDays = inQueueDays.get(i);
                    if(sp1 == mnl && inQDays <= 0){
                        toSend0b += "فروش\n" + "قیمت: " + (sp1/10) + " تومان ";
                        if(ycp != 0)
                            toSend0b += String.format("%.2f", 100F * ((float)sp1 / (float)ycp - 1F)) + "% ";
                        double qRatio = (sv1a > 0) ? (double) (100 * sv1a) / (double) tv:(double) (100 * sv1) / (double) tv;
                        toSend0b += "\nنسبت حجم صف به کل: " + String.format("%.2f", qRatio) + "%\n";
                    }
//                    else if(bp1 == mxl && inQDays >= 0){
//                        toSend0b += "خرید\n";
//                        double qRatio = (bv1a > 0) ? (double) (100 * bv1a) / (double) tv:(double) (100 * bv1) / (double) tv;
//                        toSend0b += "نسبت حجم صف به کل: " + String.format("%.2f", qRatio) + "%\n";
//                    }
                    toSend0b += "ارزش شرکت: " + String.format("%.2f", (double) marketCap / 1000D) + " همت  ";
                    toSend0b += "<a href=\"" + urlTablo + symbolID.get(i) + "\">تابلو</a>";
                    toSend0b += "   <a href=\"" + urlChart + symbolID.get(i) + "\">چارت</a>" + "\n";
                    if(sellQWatchlist.contains(symbolNickName.get(i) + " "))
                        toSend0b += "#مستعد_نوسان ⚠️\n";
                    toSend0b += "\n";
                    System.out.print("Z4");
                    if(sp1 == mnl && inQDays <= 0){
                        sendLongMessage(toSend0b + timeNowToString + "\n", ID.PUBLIC_CHANNEL, false);
                        sendFullChart(symbolNickName.get(i), symbolID.get(i),
                                toSend0b + timeNowToString + "\n" + ID.TABLOBOT_CHANNEL, ID.PUBLIC_CHANNEL);
                    }
                }
            }
            */
            if(send5){
                toSend5b += "\n" + "قیمت: " + (sp1/10) + " تومان ";
                if(ycp != 0)
                    toSend5b += String.format("%.2f", 100F * ((float)sp1 / (float)ycp - 1F)) + "% ";
                double qRatio = (sv1a > 0) ? (double) (100 * sv1a) / (double) tv:(double) (100 * sv1) / (double) tv;
                toSend5b += "\nنسبت حجم صف به کل: " + String.format("%.2f", qRatio) + "%\n";
                toSend5b += "ارزش شرکت: " + String.format("%.2f", (double) marketCap / 1000D) + " همت  ";
                toSend5b += "<a href=\"" + urlTablo + symbolID.get(i) + "\">تابلو</a>";
                toSend5b += "   <a href=\"" + urlChart + symbolID.get(i) + "\">چارت</a>" + "\n\n";
                toSend5 += toSend5b;
                System.out.print("Z5");
            }
            long orderValueThreshold = 15 * marketCap / (marketCap + 6000L);
            // Sell-Buy Average
            int lastPriceTemp = (lastPrice1.get(i) < ((minAllowed.get(i) + maxAllowed.get(i)) / 2)) ? minAllowed.get(i):maxAllowed.get(i);
            int realBuyCountTemp = realBuyCount2.get(i) - realBuyCount1.get(i);
            int legalBuyCountTemp = legalBuyCount2.get(i) - legalBuyCount1.get(i);
            int realSellCountTemp = realSellCount2.get(i) - realSellCount1.get(i);
            int legalSellCountTemp = legalSellCount2.get(i) - legalSellCount1.get(i);
            long realBuyVolumeTemp = realBuyVolume2.get(i) - realBuyVolume1.get(i);
            long legalBuyVolumeTemp = legalBuyVolume2.get(i) - legalBuyVolume1.get(i);
            long realSellVolumeTemp = realSellVolume2.get(i) - realSellVolume1.get(i);
            long legalSellVolumeTemp = legalSellVolume2.get(i) - legalSellVolume1.get(i);
            int realBuyCountTemp2 = realBuyCountTemp;
            int legalBuyCountTemp2 = legalBuyCountTemp;
            int realSellCountTemp2 = realSellCountTemp;
            int legalSellCountTemp2 = legalSellCountTemp;
            if(realBuyCountTemp == 0)
                realBuyCountTemp = 1;
            if(legalBuyCountTemp == 0)
                legalBuyCountTemp = 1;
            if(realSellCountTemp == 0)
                realSellCountTemp = 1;
            if(legalSellCountTemp == 0)
                legalSellCountTemp = 1;
            long realBuyAvg = (realBuyCountTemp <= 0) ? 0:(realBuyVolumeTemp * (long)lastPriceTemp) / (10000000L * realBuyCountTemp);
            long legalBuyAvg = (legalBuyCountTemp <= 0) ? 0:(legalBuyVolumeTemp * (long)lastPriceTemp) / (10000000L * legalBuyCountTemp);
            long realSellAvg = (realSellCountTemp <= 0) ? 0:(realSellVolumeTemp * (long)lastPriceTemp) / (10000000L * realSellCountTemp);
            long legalSellAvg = (legalSellCountTemp <= 0) ? 0:(legalSellVolumeTemp * (long)lastPriceTemp) / (10000000L * legalSellCountTemp);
            if(realBuyAvg == 0)
                realBuyCountTemp = 0;
            if(legalBuyAvg == 0)
                legalBuyCountTemp = 0;
            if(realSellAvg == 0)
                realSellCountTemp = 0;
            if(legalSellAvg == 0)
                legalSellCountTemp = 0;
            if(!symbolType.get(i).equals(SYMBOL_TYPE.HAGH_TAGHADOM)
                    && (realBuyAvg > 0 || legalBuyAvg > 0 || realSellAvg > 0 || legalSellAvg > 0)) {
                if (lp != maxAllowed.get(i) &&
                        (realBuyAvg >= 500 && (realSellAvg <= 200 || lp == minAllowed.get(i))
                        || realBuyAvg >= 300 && (realSellAvg <= 200 || lp == minAllowed.get(i))
                        || legalBuyAvg >= 1000 && (legalSellAvg <= 500 && realSellAvg <= 200 || lp == minAllowed.get(i)))) {
                    toSend1 = "#خرید_سنگین\n";
                    if( (double)(legalSellCountTemp * legalSellAvg) / (double)(realSellCountTemp * realSellAvg) >= 3D
                            && (double)(realBuyCountTemp * realBuyAvg) / (double)(legalBuyCountTemp * legalBuyAvg) >= 3D
                            && (legalSellCountTemp * legalSellAvg) >= 2000 && (legalSellCountTemp * legalSellAvg) >= (orderValueThreshold * 300)
                            && legalSellCountTemp <= 2 && (realBuyCountTemp + legalBuyCountTemp) <= 20){
                        toSend1 += "#مشکوک_به_کد_به_کد\n";
                        send1 = true;
                    }
                    toSend1 += "#" +symbolNickName.get(i) + " " + symbolType.get(i) + " (ارزش شرکت: "
                            + String.format("%.2f",(double)marketCap / 1000D) + " همت)" + "\n";
                    toSend1 += "قیمت: " + (lp/10) + " تومان ";
                    if(ycp != 0)
                        toSend1 += String.format("%.2f", 100F * ((float)lp / (float)ycp - 1F)) + "% ";
                    if(realSellAvg > 0 &&
                        (legalSellCountTemp == 0 || legalSellCountTemp > 0 && legalSellAvg > 0
                                && (double)(realSellCountTemp * realSellAvg) / (double)(legalSellCountTemp * legalSellAvg) >= 2D)) {
                        if(legalBuyCountTemp == 0 || legalBuyCountTemp > 0 && legalBuyAvg > 0
                                && (double)(realBuyCountTemp * realBuyAvg) / (double)(legalBuyCountTemp * legalBuyAvg) >= 2D){
                            double buyRatio = (double)realBuyAvg/(double)realSellAvg;
                            double exchangeRatio = (double)(realBuyCountTemp * realBuyAvg) / (double)marketCap;
                            toSend1 += "\n" + "به ارزش " + String.format("%.2f", exchangeRatio / 10D) + " درصد شرکت" + "\n";
                            toSend1 += "قدرت خرید حقیقی به حقیقی: " + String.format("%.2f", buyRatio) + "\n";
                            if(buyRatio >= 10L && exchangeRatio >= 0.8 && marketCap < 10000)
                                send1 = true;
                        }
                        else if(realBuyCountTemp == 0 || realBuyCountTemp > 0 && realBuyAvg > 0
                                && (double)(legalBuyCountTemp * legalBuyAvg) / (double)(realBuyCountTemp * realBuyAvg) >= 2D){
                            double buyRatio = (double)legalBuyAvg/(double)realSellAvg;
                            double exchangeRatio = (double)(legalBuyCountTemp * legalBuyAvg) / (double)marketCap;
                            toSend1 += "\n" + "به ارزش " + String.format("%.2f", exchangeRatio / 10D) + " درصد شرکت" + "\n";
                            toSend1 += "قدرت خرید حقوقی به حقیقی: " + String.format("%.2f", buyRatio) + "\n";
                            if(buyRatio >= 10L && exchangeRatio >= 2L && marketCap < 10000)
                                send1 = true;
                        }
                        else
                            toSend1 += "\n";

                    }
                    else if(legalSellAvg > 0 &&
                            (realSellCountTemp == 0 || realSellCountTemp > 0 && realSellAvg > 0
                                    && (double)(legalSellCountTemp * legalSellAvg) / (double)(realSellCountTemp * realSellAvg) >= 2D)) {
                        if(legalBuyCountTemp == 0 || legalBuyCountTemp > 0 && legalBuyAvg > 0
                                && (double)(realBuyCountTemp * realBuyAvg) / (double)(legalBuyCountTemp * legalBuyAvg) >= 2D){
//                            double buyRatio = (double)realBuyAvg/(double)legalSellAvg;
//                            toSend1 += "\n" + "قدرت خرید حقیقی به حقوقی: ";
//                            toSend1 += String.format("%.2f", buyRatio);
//                            if(buyRatio >= 5)
//                                toSend1 += " " + greenCircle + greenCircle + greenCircle;
//                            else if(buyRatio <= 0.2)
//                                toSend1 += " " + redCircle + redCircle + redCircle;
                            toSend1 += "\n" + "به ارزش " + String.format("%.2f", (double)(legalSellCountTemp * legalSellAvg) / (double)marketCap / 10D)
                                    + " درصد شرکت" + "\n";
                        }
                        else
                            toSend1 += "\n";
                    }
                    else
                        toSend1 += "\n";

                    if (lp == maxAllowed.get(i))
                        toSend1 += "صف خرید با حجم " + String.format("%.1f", ((float) bv1 / 1000000.0D)) + "M\n";
                    else if (lp == minAllowed.get(i))
                        toSend1 += "صف فروش با حجم " + String.format("%.1f", ((float) sv1 / 1000000.0D)) + "M\n";

                    if(send1){
                        try {
                            long realLegalExVal = (realBuyVolumeTemp + legalBuyVolumeTemp) * (long)lastPriceTemp / 10000000L;
                            FileWriter fileWriter = new FileWriter("hotMoneyHistory", true);
                            fileWriter.write(symbolNickName.get(i) + " " + realLegalExVal + " "
                                    + String.format("%.2f", (double) marketCap / 1000D) + " "
                                    + String.format("%.2f",(double)realLegalExVal / (double)marketCap / 10D) + " " + today + "\n");
                            fileWriter.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                        checkHotMoneyAndOrderHistory(true);
                    }

                    ArrayList<String> argList = new ArrayList<>();

                    if(realBuyCountTemp == 0){
                        argList.add(" "); argList.add(" "); argList.add(" ");
                    }
                    else {
                        argList.add(String.format("%.1f", (double) (realBuyCountTemp * realBuyAvg) / 1000));
                        argList.add(String.format("%.1f", (double) realBuyAvg / 1000));
                        argList.add(String.valueOf(realBuyCountTemp));
                    }
                    if(legalBuyCountTemp == 0){
                        argList.add(" "); argList.add(" "); argList.add(" ");
                    }
                    else {
                        argList.add(String.format("%.1f", (double) (legalBuyCountTemp * legalBuyAvg) / 1000));
                        argList.add(String.format("%.1f", (double) legalBuyAvg / 1000));
                        argList.add(String.valueOf(legalBuyCountTemp));
                    }
                    if(realSellCountTemp == 0){
                        argList.add(" "); argList.add(" "); argList.add(" ");
                    }
                    else {
                        argList.add(String.format("%.1f", (double) (realSellCountTemp * realSellAvg) / 1000));
                        argList.add(String.format("%.1f", (double) realSellAvg / 1000));
                        argList.add(String.valueOf(realSellCountTemp));
                    }
                    if(legalSellCountTemp == 0){
                        argList.add(" "); argList.add(" "); argList.add(" ");
                    }
                    else {
                        argList.add(String.format("%.1f", (double) (legalSellCountTemp * legalSellAvg) / 1000));
                        argList.add(String.format("%.1f", (double) legalSellAvg / 1000));
                        argList.add(String.valueOf(legalSellCountTemp));
                    }

                    toSend1 += "<a href=\"" + urlTablo + symbolID.get(i) + "\">تابلو</a>";
                    toSend1 += "   <a href=\"" + urlChart + symbolID.get(i) + "\">چارت</a>" + "\n";
                    toSend1 += formattedDate(String.valueOf(today)) + "\n";
                    toSend1Mafia = toSend1;
                    toSend1 += ID.TABLOBOT_CHANNEL;
                    saveAverageImage(argList, 0);
                    saveAverageImage(argList, 1);
                    argList.clear();

//                    if (realSellAvg > 0) {
//                        toSend1b += "حقیقی: " + realSellCountTemp + " نفر میانگین ";
//                        toSend1b += String.format("%.1f", (double) realSellAvg / 1000) + "B مجموع ";
//                        toSend1b += String.format("%.1f", (double) (realSellCountTemp * realSellAvg) / 1000) + "B\n";
//                    }
//                    if (realBuyAvg > 0) {
//                        toSend1b += "حقیقی: " + realBuyCountTemp + " نفر میانگین ";
//                        toSend1b += String.format("%.1f", (double) realBuyAvg / 1000) + "B مجموع ";
//                        toSend1b += String.format("%.1f", (double) (realBuyCountTemp * realBuyAvg) / 1000) + "B\n";
//                    }
//                    if (legalSellAvg > 0) {
//                        toSend1b += "حقوقی: " + legalSellCountTemp + " نفر میانگین ";
//                        toSend1b += String.format("%.1f", (double) legalSellAvg / 1000) + "B مجموع ";
//                        toSend1b += String.format("%.1f", (double) (legalSellCountTemp * legalSellAvg) / 1000) + "B\n";
//                    }
//                    if (legalBuyAvg > 0) {
//                        toSend1b += "حقوقی: " + legalBuyCountTemp + " نفر میانگین ";
//                        toSend1b += String.format("%.1f", (double) legalBuyAvg / 1000) + "B مجموع ";
//                        toSend1b += String.format("%.1f", (double) (legalBuyCountTemp * legalBuyAvg) / 1000) + "B\n";
//                    }
//                    try{
//                        FileWriter fileWriter = new FileWriter("sbx1.txt", true);
//                        fileWriter.write(timeNow + " "  + toSend1 + "\n" + toSend1b + "\n");
//                        fileWriter.close();
//                    }
//                    catch (IOException e){
//                        e.printStackTrace();
//                    }



                }
                if ((realBuyVolume1.get(i) > 0 || legalBuyVolume1.get(i) > 0) && marketCap <= 30000 && lp < mxl) {
                    if(realSellAvg <= 300 && legalSellAvg <= 300){
                        if(realBuyAvg >= 30 && realBuyAvg * realBuyCountTemp <= 250){
                            realBuyAccumulate2.set(i, realBuyAccumulate2.get(i) + (int)realBuyAvg);
                            if(realBuyCountTemp2 == 0)
                                realBuyAccumulate.set(i, realBuyAccumulate.get(i) + (int)realBuyAvg);
                        }
                        if(legalBuyAvg >= 30 && legalBuyAvg * legalBuyCountTemp <= 250){
                            legalBuyAccumulate2.set(i, legalBuyAccumulate2.get(i) + (int)legalBuyAvg);
                            if(legalBuyCountTemp2 == 0)
                                legalBuyAccumulate.set(i, legalBuyAccumulate.get(i) + (int)legalBuyAvg);
                        }
                    }
                    if(realBuyAvg <= 300 && legalBuyAvg <= 300){
                        if(realSellAvg >= 30 && realSellAvg * realSellCountTemp <= 250){
                            realSellAccumulate2.set(i, realSellAccumulate2.get(i) + (int)realSellAvg);
                            if(realSellCountTemp2 == 0)
                                realSellAccumulate.set(i, realSellAccumulate.get(i) + (int)realSellAvg);
                        }
                        if(legalSellAvg >= 30 && legalSellAvg * legalSellCountTemp <= 250){
                            legalSellAccumulate2.set(i, legalSellAccumulate2.get(i) + (int)legalSellAvg);
                            if(legalSellCountTemp2 == 0)
                                legalSellAccumulate.set(i, legalSellAccumulate.get(i) + (int)legalSellAvg);
                        }
                    }
//                    if(realSellAvg <= 300 && legalSellAvg <= 300 &&
//                            (realBuyAvg >= 30 && realBuyAvg <= 250 && realBuyCountTemp2 == 0
//                                || legalBuyAvg >= 30 && legalBuyAvg <= 250 && legalBuyCountTemp2 == 0)){
//                        realBuyAccumulate.set(i, realBuyAccumulate.get(i) + (int)realBuyAvg);
//                        legalBuyAccumulate.set(i, legalBuyAccumulate.get(i) + (int)legalBuyAvg);
//                    }
//                    if(realBuyAvg <= 300 && legalBuyAvg <= 300 && lp > mnl &&
//                            (realSellAvg >= 30 && realSellAvg <= 250 && realSellCountTemp2 == 0
//                                || legalSellAvg >= 30 && legalSellAvg <= 250 && legalSellCountTemp2 == 0)){
//                        realSellAccumulate.set(i, realSellAccumulate.get(i) + (int)realSellAvg);
//                        legalSellAccumulate.set(i, legalSellAccumulate.get(i) + (int)legalSellAvg);
//                    }
//                    if(realSellAvg <= 300 && legalSellAvg <= 300 &&
//                            (realBuyAvg >= 30 && realBuyAvg * realBuyCountTemp <= 250
//                                || legalBuyAvg >= 30 && legalBuyAvg * legalBuyCountTemp <= 250)){
//                        realBuyAccumulate2.set(i, realBuyAccumulate2.get(i) + (int)realBuyAvg);
//                        legalBuyAccumulate2.set(i, legalBuyAccumulate2.get(i) + (int)legalBuyAvg);
//                    }
//                    if(realBuyAvg <= 300 && legalBuyAvg <= 300 && lp > mnl &&
//                            (realSellAvg >= 30 && realSellAvg * realSellCountTemp <= 250
//                                || legalSellAvg >= 30 && legalSellAvg * legalSellCountTemp <= 250)){
//                        realSellAccumulate2.set(i, realSellAccumulate2.get(i) + (int)realSellAvg);
//                        legalSellAccumulate2.set(i, legalSellAccumulate2.get(i) + (int)legalSellAvg);
//                    }
                }
            }
            // Code To Code
            double ctcRatioThreshold = 3D / ((double)(marketCap + 6000));
            ctcRatioThreshold = (ctcRatioThreshold > 0.0001) ? ctcRatioThreshold:0.0001;
            if(legalSellVolumeTemp > realSellVolumeTemp * 2 && realBuyVolumeTemp > legalBuyVolumeTemp * 2
                && (double) (realBuyVolumeTemp + legalBuyVolumeTemp) / (double) totalVolume.get(i) >= ctcRatioThreshold
                && (realBuyVolumeTemp + legalBuyVolumeTemp) * (long) lastPrice2.get(i) >= 10000000000L)
                    codeToCodeSymbolID.add(symbolID.get(i));

            // Buy Avg Over X
            if(realBuyCount1.get(i) == 0)
                realBuyCount1.set(i, 1);
            if(realBuyCount2.get(i) == 0)
                realBuyCount2.set(i, 1);
            if(realSellCount1.get(i) == 0)
                realSellCount1.set(i, 1);
            if(realSellCount2.get(i) == 0)
                realSellCount2.set(i, 1);
            long buyAvg = (long)cp * realBuyVolume2.get(i) / (long)realBuyCount2.get(i) / 10000000L;
            long sellAvg = (long)cp * realSellVolume2.get(i) / (long)realSellCount2.get(i) / 10000000L;
            if(buyAvg > buyAvgMax.get(i) && sellAvg > 0) {
                buyAvgMax.set(i, buyAvg);
                if (lp < mxl && (float)buyAvg / (float)sellAvg >= 1.5F
                        && secondsNow >= 33000 && mxp < mxl && mxp > mnl && exQuantity2.get(i) > 300) {
                    String caption = "";
                    if (buyAvg >= 80 && !buyAvgOver80.contains(symbolNickName.get(i))) {
                        caption = "#سرانه_خرید_بالای 80 ";
                        buyAvgOver80.add(symbolNickName.get(i));
                        if(!buyAvgOver60.contains(symbolNickName.get(i)))
                            buyAvgOver60.add(symbolNickName.get(i));
                        if(!buyAvgOver40.contains(symbolNickName.get(i)))
                            buyAvgOver40.add(symbolNickName.get(i));
                    }
                    else if (buyAvg >= 60 && !buyAvgOver60.contains(symbolNickName.get(i))) {
                        caption = "#سرانه_خرید_بالای 60 ";
                        buyAvgOver60.add(symbolNickName.get(i));
                        if(!buyAvgOver40.contains(symbolNickName.get(i)))
                            buyAvgOver40.add(symbolNickName.get(i));
                    }
                    else if (buyAvg >= 40 && !buyAvgOver40.contains(symbolNickName.get(i))) {
                        caption = "#سرانه_خرید_بالای 40 ";
                        buyAvgOver40.add(symbolNickName.get(i));
                    }
                    FileWriter fileWriter = new FileWriter("buyAvgOver");
                    for (int x = 0; x < buyAvgOver40.size(); x++)
                         fileWriter.write(buyAvgOver40.get(x) + " ");
                    fileWriter.write("*** ");
                   for (int x = 0; x < buyAvgOver60.size(); x++)
                         fileWriter.write(buyAvgOver60.get(x) + " ");
                    fileWriter.write("*** ");
                    for (int x = 0; x < buyAvgOver80.size(); x++)
                         fileWriter.write(buyAvgOver80.get(x) + " ");
                    fileWriter.write("*** ");
                    fileWriter.close();
                
                    if(caption.length() > 0) {
                        caption += orangeCircle + "\n#" + symbolNickName.get(i);
                        caption += " قدرت خریدار: " + String.format("%.1f", (float) buyAvg / (float) sellAvg) + "\n\n";
                        caption += "<a href=\"" + urlTablo + symbolID.get(i) + "\">تابلو</a>";
                        caption += "   <a href=\"" + urlChart + symbolID.get(i) + "\">چارت</a>";
                        caption += "\n" + timeNowToString + "\n" + ID.TABLOBOT_CHANNEL;
                        saveChartImage(true, symbolNickName.get(i), CHART_TYPE.WITHOUT_MOVING_AVERAGE);
                        SendPhoto message = new SendPhoto();
                        message.setPhoto(new File("chart.png"));
                        message.setCaption(caption);
                        message.setParseMode(ParseMode.HTML);
                        customSendMessage(2, null, message, null, ID.PUBLIC_CHANNEL, false);
                    }
                }
            }

            // Increase Buy AvM in x Minutes
//            int L2 = buyVolChartData.get(i).size();
//            if(secondsNow >= 34200 && L2 >= 20 && secondsNow - buyAvgIncLastTime.get(i) > 900){
//                long buyAvg5MinAgo = cp * buyVolChartData.get(i).get(L2 - 10) / buyCntChartData.get(i).get(L2 - 10) / 10000000L;
//                long sellAvg5MinAgo = cp * sellVolChartData.get(i).get(L2 - 10) / sellCntChartData.get(i).get(L2 - 10) / 10000000L;
//                long buyAvg10MinAgo = cp * buyVolChartData.get(i).get(L2 - 20) / buyCntChartData.get(i).get(L2 - 20) / 10000000L;
//                String caption = "#افزایش_سرانه_خرید ";
//                boolean send = false;
//                if(buyAvg - buyAvg10MinAgo >= 10 && buyAvg >= 20 && sellAvg < 20){
//                    caption += "10 ";
//                    send = true;
//                }
//                if(buyAvg - buyAvg5MinAgo >= 5 && sellAvg - sellAvg5MinAgo <= 2){
//                    caption += "5 ";
//                    send = true;
//                }
//                if(send){
//                    caption += "\n#" + symbolNickName.get(i);
//                    caption += " قدرت خریدار: " + String.format("%.1f", (float) buyAvg / (float) sellAvg) + "\n\n";
//                    caption += "<a href=\"" + urlTablo + symbolID.get(i) + "\">تابلو</a>";
//                    caption += "   <a href=\"" + urlChart + symbolID.get(i) + "\">چارت</a>";
//                    caption += "\n" + timeNowToString + "\n" + ID.TABLOBOT_CHANNEL;
//                    saveChartImage(true, symbolNickName.get(i), CHART_TYPE.WITHOUT_MOVING_AVERAGE);
//                    SendPhoto message = new SendPhoto();
//                    message.setPhoto(new File("chart.png"));
//                    message.setCaption(caption);
//                    message.setParseMode(ParseMode.HTML);
//                    customSendMessage(2, null, message, null, ID.ALI_MORADI_CHANNEL, false);
//                    buyAvgIncLastTime.set(i, secondsNow);
//                }
//            }

//            float buyerPower = 0;
//            if(realSellVolume2.get(i) > 0)
//                buyerPower = ((float)realBuyVolume2.get(i) / (float)realBuyCount2.get(i))
//                    / ((float)realSellVolume2.get(i) / (float)realSellCount2.get(i));
//            if(buyerPower > buyerPowerMax.get(i)){
//                buyerPowerMax.set(i, buyerPower);
//                if (secondsNow >= 33300 && maxPrice2.get(i) < maxAllowed.get(i) && maxPrice2.get(i) > minAllowed.get(i)) {
//                    String caption = "";
//                    if (buyerPower >= 0.833F && buyerPower <= 1.0
//                            && !sellerPowU1_2.contains(symbolNickName.get(i))
//                            && sellerPowU2.contains(symbolNickName.get(i))) {
//                        int index = sellerPowU2.indexOf(symbolNickName.get(i));
//                        if(index >= 0 &&(float)buyAvg / spu2BuyAvg.get(index) >= 1.05
//                                && (float)sellAvg / spu2SellAvg.get(index) <= 0.95){
//                            sellerPowU1_2.add(symbolNickName.get(i));
//                            if(!sellerPowU1_5.contains(symbolNickName.get(i)))
//                                sellerPowU1_5.add(symbolNickName.get(i));
//                            if(buyAvg >= 15)
//                                caption = "#قدرت_فروشنده_زیر 1.2 ";
//                        }
//                    }
//                    else if (buyerPower >= 0.666F && buyerPower <= 1.0
//                            && !sellerPowU1_5.contains(symbolNickName.get(i))
//                            && sellerPowU2.contains(symbolNickName.get(i))) {
//                        int index = sellerPowU2.indexOf(symbolNickName.get(i));
//                        if(index >= 0 &&(float)buyAvg / spu2BuyAvg.get(index) >= 1.05
//                                && (float)sellAvg / spu2SellAvg.get(index) <= 0.95){
//                            sellerPowU1_5.add(symbolNickName.get(i));
//                            spu1_5BuyAvg.add((float)buyAvg);
//                            spu1_5SellAvg.add((float)sellAvg);
//                            if(buyAvg >= 12)
//                                caption = "#قدرت_فروشنده_زیر 1.5 ";
//                        }
//                    }
//                    else if (buyerPower >= 0.5F && buyerPower <= 1.0
//                            && !sellerPowU2.contains(symbolNickName.get(i))
//                            && sellerPowU3.contains(symbolNickName.get(i))) {
//                        int index = sellerPowU3.indexOf(symbolNickName.get(i));
//                        if(index >= 0 && (float)buyAvg / spu3BuyAvg.get(index) >= 1.05
//                                && (float)sellAvg / spu3SellAvg.get(index) <= 0.95){
//                            sellerPowU2.add(symbolNickName.get(i));
//                            spu2BuyAvg.add((float)buyAvg);
//                            spu2SellAvg.add((float)sellAvg);
//                        }
//                    }
//                    else if (buyerPower >= 0.33F && buyerPower <= 1.0
//                            && !sellerPowU3.contains(symbolNickName.get(i))) {
//                        sellerPowU3.add(symbolNickName.get(i));
//                        spu3BuyAvg.add((float)buyAvg);
//                        spu3SellAvg.add((float)sellAvg);
//                    }
//                    if(caption.length() > 0) {
//                        caption += purpleCircle + "\n#" + symbolNickName.get(i) + "\n\n";
//                        caption += "<a href=\"" + urlTablo + symbolID.get(i) + "\">تابلو</a>";
//                        caption += "   <a href=\"" + urlChart + symbolID.get(i) + "\">چارت</a>";
//                        caption += "\n" + timeNowToString + "\n" + ID.TABLOBOT_CHANNEL;
//                        saveChartImage(true, symbolNickName.get(i), CHART_TYPE.WITHOUT_MOVING_AVERAGE);
//                        SendPhoto message = new SendPhoto();
//                        message.setPhoto(new File("chart.png"));
//                        message.setCaption(caption);
//                        message.setParseMode(ParseMode.HTML);
//                        customSendMessage(2, null, message, null, ID.PUBLIC_CHANNEL, false);
//                    }
//                }
//            }

            // Suspicious Board
            int boardType = 0;

            orderValueThreshold = 30 * marketCap / (marketCap + 6000L);
            long orderValueThreshold2 = 20 * marketCap / (marketCap + 6000L);
            long bvAll = bv1 + bv2 + bv3 + bv4 + bv5;
            double buyOrderRatio = (double)bvAll / (double)tv;
//            double sellOrderRatio = (double)(sv1 + sv2 + sv3 + sv4 + sv5) / (double)tv;
//            int mxlv = maxAllowedVolume.get(i);
            long buyOrdersSum = 0;
            if(bp1 >= mnl && bp1 < mxl && bq1 > 0)
                buyOrdersSum += bv1 * bp1;
            if(bp2 >= mnl && bp2 < mxl && bq2 > 0)
                buyOrdersSum += bv2 * bp2;
            if(bp3 >= mnl && bp3 < mxl && bq3 > 0)
                buyOrdersSum += bv3 * bp3;
            if(bp4 >= mnl && bp4 < mxl && bq4 > 0)
                buyOrdersSum += bv4 * bp4;
            if(bp5 >= mnl && bp5 < mxl && bq5 > 0)
                buyOrdersSum += bv5 * bp5;
            long maxVol = (bv2 > bv1) ? bv2:bv1;
            maxVol = (bv3 > maxVol) ? bv3:maxVol;
            maxVol = (bv4 > maxVol) ? bv4:maxVol;
            maxVol = (bv5 > maxVol) ? bv5:maxVol;
            long firstSupport = 0;
            boolean updateLastSupport = false;
            if(((float)mxl / (float)ycp) <= 1.1 && bp1 < mxl && maxVol > sv1 * 3){
                if(orderValueThreshold != 0 && buyOrdersSum > orderValueThreshold * 10000000000L){
                    boardType += 10;
                    if(firstSupportVolume.get(i) == 0L)
                        firstSupportVolume.set(i, bvAll);
                }
                if(orderValueThreshold2 != 0 && buyOrdersSum > orderValueThreshold2 * 10000000000L){
                    boardType += 1;
                    if(2*bv1 > maxVol)
                        firstSupport = bp1;
                    else if(2*bv2 > maxVol)
                        firstSupport = bp2;
                    else if(2*bv3 > maxVol)
                        firstSupport = bp3;
                    else if(2*bv4 > maxVol)
                        firstSupport = bp4;
                    else if(2*bv5 > maxVol)
                        firstSupport = bp5;
                    if(lastSupportPrice.get(i) > 0 && (float)firstSupport >= (float)lastSupportPrice.get(i) * 1.005)
                        boardType += 100;
                    if((float)firstSupport >= (float)lastSupportPrice.get(i) * 1.005)
                        updateLastSupport = true;
                }
            }
//            buyOrdersSum = 0;
//            if((bv1 % 100000L == 0L) && bp1 > mnl && bp1 < mxl && (bv1 * bp1) > 10000000000L && (bv1 * bp1 > orderValueThreshold * 3300000000L) && bv1 > 5*sv1)
//                buyOrdersSum += bv1 * bp1;
//            if((bv2 % 100000L == 0L) && bp2 > mnl && bp2 < mxl && (bv2 * bp2) > 10000000000L && (bv2 * bp2 > orderValueThreshold * 3300000000L))
//                buyOrdersSum += bv2 * bp2;
//            if((bv3 % 100000L == 0L) && bp3 > mnl && bp3 < mxl && (bv3 * bp3) > 10000000000L && (bv3 * bp3 > orderValueThreshold * 3300000000L))
//                buyOrdersSum += bv3 * bp3;
//            if((bv4 % 100000L == 0L) && bp4 > mnl && bp4 < mxl && (bv4 * bp4) > 10000000000L && (bv4 * bp4 > orderValueThreshold * 3300000000L))
//                buyOrdersSum += bv4 * bp4;
//            if((bv5 % 100000L == 0L) && bp5 > mnl && bp5 < mxl && (bv5 * bp5) > 10000000000L && (bv5 * bp5 > orderValueThreshold * 3300000000L))
//                buyOrdersSum += bv5 * bp5;
//            if(marketCap < 50000 && orderValueThreshold != 0 && ((float)mxl / (float)ycp <= 1.1) && bp1 < mxl &&
//                    buyOrdersSum > 10000000000L && buyOrdersSum > orderValueThreshold * 3300000000L)
//                boardType += 100;


            if(boardType > 0 && !symbolType.get(i).equals(SYMBOL_TYPE.HAGH_TAGHADOM) &&
                    (timeNow.getHour() >= 9 || timeNow.getMinute() >= 55) &&
                    (bvAll >= 2 * firstSupportVolume.get(i) && bvAll < 3 * firstSupportVolume.get(i) && supportOrderCounter.get(i) < 2 ||
                        bvAll >= 3 * firstSupportVolume.get(i) && supportOrderCounter.get(i) < 3 ||
                        boardType > supportOrderType.get(i) || supportOrderTimer.get(i) == 0)){
                if(firstSupportVolume.get(i) > 0 && bvAll < 2 * firstSupportVolume.get(i))
                    supportOrderCounter.set(i, 1);
                else if(bvAll >= 2 * firstSupportVolume.get(i) && bvAll < 3 * firstSupportVolume.get(i))
                    supportOrderCounter.set(i, 2);
                else if(bvAll >= 3 * firstSupportVolume.get(i))
                    supportOrderCounter.set(i, 3);
                toSend2 = "";
                if(boardType % 1000 >= 100){
                    toSend2 += "#اردر_حمایتی_صعودی ⬆️\n" + "#" + symbolNickName.get(i) + " " + symbolType.get(i) + " (ارزش شرکت: "
                            + String.format("%.2f",(double)marketCap / 1000D) + " همت)" + "\n";
                    toSend2 += "به ارزش " + String.format("%.2f",100.0 * buyOrderRatio) + " درصد شرکت" + "\n";
                    toSend2 += String.format("%.2f", 100F * ((float) firstSupport / (float) lastSupportPrice.get(i) - 1F)) + "% ";
                    toSend2 += "بالاتر از اردر حمایتی قبلی امروز به ارزش ";
                    toSend2 += String.format("%.1f", (double)lastSupportVolume.get(i) * (double)lastSupportPrice.get(i) / 10000000000L);
                    toSend2 += " میلیارد تومان";
                    if(lastSupportTime.get(i) >= 31500){
                        int h = lastSupportTime.get(i) / 3600;
                        int m = (lastSupportTime.get(i) % 3600) / 60;
                        int s = lastSupportTime.get(i) % 60;
                        String timeTemp = "";
                        if (h < 10)
                            timeTemp += "0" + h + ":";
                        else
                            timeTemp += h + ":";
                        if (m < 10)
                            timeTemp += "0" + m + ":";
                        else
                            timeTemp += m + ":";
                        if (s < 10)
                            timeTemp += "0" + s;
                        else
                            timeTemp += s;
                        toSend2 +=  " در " + timeTemp;
                    }
                    toSend2 += "\n";
                }
                else if(boardType % 100 >= 10) {
                    toSend2 += "#اردر_حمایتی\n" + "#" + symbolNickName.get(i) + " " + symbolType.get(i) + " (ارزش شرکت: "
                            + String.format("%.2f", (double) marketCap / 1000D) + " همت)" + "\n";
                    toSend2 += "به ارزش " + String.format("%.2f",100.0 * buyOrderRatio) + " درصد شرکت" + "\n";
                }
                toSend2 += "\n<a href=\"" + urlTablo + symbolID.get(i) + "\">تابلو</a>";
                toSend2 += "   <a href=\"" + urlChart + symbolID.get(i) + "\">چارت</a>" + "\n";
                toSend2 += formattedDate(String.valueOf(today)) + "\n";
                toSend2Mafia = toSend2;
                toSend2 += ID.TABLOBOT_CHANNEL;
                saveTableImage(i, 0);
                saveTableImage(i, 1);
                if(updateLastSupport){
                    lastSupportPrice.set(i, (int)firstSupport);
                    lastSupportVolume.set(i, bvAll);
                    lastSupportTime.set(i, secondsNow);
                }
                try {
                    FileWriter fileWriter = new FileWriter("supportOrderHistory", true);
                    fileWriter.write(symbolNickName.get(i) + " " + (buyOrdersSum / 10000000L) + " "
                            + String.format("%.2f", (double) marketCap / 1000D) + " "
                            + String.format("%.2f",100.0 * buyOrderRatio) + " " + today + "\n");
                    fileWriter.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
                supportOrderTimer.set(i, secondsNow);
                supportOrderType.set(i, boardType);
                if(boardType != 1){
                    checkHotMoneyAndOrderHistory(true);
                    send2 = true;
                }
            }

            if( !(timeNow.getHour() == 8 || timeNow.getHour() == 9 && timeNow.getMinute() <= 5)
                    && !symbolType.get(i).equals(SYMBOL_TYPE.HAGH_TAGHADOM)
                    && (secondsNow - lastExchangeTime.get(i) < 60) && exVolume1.get(i) != 0
                    && !lastPrice1.get(i).equals(minAllowed.get(i)) && !lastPrice1.get(i).equals(maxAllowed.get(i))){
                long tempVolume = 0;
                for (int j = 0; j < volumeBufferSize; j++){
                    tempVolume += volumeBuffer[i][j];
                }
                if(     priceBuffer[i][(priceBufferIndex + priceBufferSize - 36) % priceBufferSize] != maxAllowed.get(i) &&
                        priceBuffer[i][(priceBufferIndex + priceBufferSize - 36) % priceBufferSize] != minAllowed.get(i) &&
                        priceBuffer[i][(priceBufferIndex + priceBufferSize - 36) % priceBufferSize] != 0 &&
                        bv1 < tempVolume && bv2 < tempVolume && bv3 < tempVolume &&
                        maxValueAvg > 10000000000L && (tempVolume * (long)lp) > (maxValueAvg * 3L / 10L) &&
                        (timeNow.getHour() == 9 && timeNow.getMinute() < 30)
                        && (tempVolume * (long)lp) > orderValueThreshold * 3300000000L && (tempVolume * (long)lp) > 10000000000L){
                    toSend4 = "#حجم_مشکوک_لحظه_ای #" + symbolNickName.get(i) + " " + symbolType.get(i) + " \uD83D\uDD35\uD83D\uDD35\uD83D\uDD35\n";
                    toSend4 += "حجم: " + String.format("%.3f",(double)tempVolume / 1000000D) + "M ";
                    toSend4 += "ارزش: " + String.format("%.1f",(double)(tempVolume * lp) / 10000000000D) + " میلیارد تومان\n";
                    toSend4 += "قیمت: " + (lp/10) + " تومان ";
                    if(ycp != 0)
                        toSend4 += String.format("%.2f", 100F * ((float)lp / (float)ycp - 1F)) + "% ";
                    toSend4 += "\nارزش شرکت: " + String.format("%.2f", (double) marketCap / 1000D) + " همت  ";
                    toSend4 += "<a href=\"" + urlTablo + symbolID.get(i) + "\">تابلو</a>";
                    toSend4 += "   <a href=\"" + urlChart + symbolID.get(i) + "\">چارت</a>" + "\n";
                    toSend4b = toSend4;
                    toSend4b += "\n" + volumeBuffer[i][0] + " " + volumeBuffer[i][1] + " " + volumeBuffer[i][2] + " " + volumeBuffer[i][3] + " "+ volumeBuffer[i][4] + " " + volumeBuffer[i][5]+ "\n";
                    toSend4 += formattedDate(String.valueOf(today)) + " " + timeNowToString + "\n";
                    toSend4 += ID.TABLOBOT_CHANNEL;
                    send4 = true;
                }
                if(     priceBuffer[i][(priceBufferIndex + priceBufferSize - 36) % priceBufferSize] != maxAllowed.get(i) &&
                        priceBuffer[i][(priceBufferIndex + priceBufferSize - 36) % priceBufferSize] != minAllowed.get(i) &&
                        priceBuffer[i][(priceBufferIndex + priceBufferSize - 36) % priceBufferSize] != 0 &&
                        bv1 < tempVolume && bv2 < tempVolume && bv3 < tempVolume &&
                        (timeNow.getHour() == 9 && timeNow.getMinute() >= 30 || timeNow.getHour() >= 10) && (tempVolume > (exVolume2.get(i) * 3L / 10L))
                        && (tempVolume * (long)lp) >  orderValueThreshold * 2500000000L && (tempVolume * (long)lp) > 10000000000L){
                    toSend4 = "#حجم_مشکوک_لحظه_ای #" + symbolNickName.get(i) + " " + symbolType.get(i) + " \uD83D\uDD35\uD83D\uDD35\uD83D\uDD35\n";
                    toSend4 += "حجم: " + String.format("%.3f",(double)tempVolume / 1000000D) + "M ";
                    toSend4 += "ارزش: " + String.format("%.1f",(double)(tempVolume * lp) / 10000000000D) + " میلیارد تومان ";
                    toSend4 += "معادل " + String.format("%.1f", (double)(100 * tempVolume) / (double)exVolume2.get(i)) + "% حجم روز\n";
                    toSend4 += "قیمت: " + (lp/10) + " تومان ";
                    if(ycp != 0)
                        toSend4 += String.format("%.2f", 100F * ((float)lp / (float)ycp - 1F)) + "% ";
                    toSend4 += "\nارزش شرکت: " + String.format("%.2f", (double) marketCap / 1000D) + " همت  ";
                    toSend4 += "<a href=\"" + urlTablo + symbolID.get(i) + "\">تابلو</a>";
                    toSend4 += "   <a href=\"" + urlChart + symbolID.get(i) + "\">چارت</a>" + "\n";
                    toSend4b = toSend4;
                    toSend4b += "\n" + volumeBuffer[i][0] + " " + volumeBuffer[i][1] + " " + volumeBuffer[i][2] + " " + volumeBuffer[i][3] + " "+ volumeBuffer[i][4] + " " + volumeBuffer[i][5]+ "\n";
                    toSend4 += formattedDate(String.valueOf(today)) + " " + timeNowToString +  "\n";
                    toSend4 += ID.TABLOBOT_CHANNEL;
                    send4 = true;
                }
                if(send4){
                    try {
                        FileWriter fileWriter = new FileWriter("sbx3.txt", true);
                        fileWriter.write(toSend4b + "\n");
                        fileWriter.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                for (int j = 0; j < volumeBufferSize; j++)
                    volumeBuffer[i][j] = 0;

                // Increasing exchange volume
//                long volDiff = exVolume2.get(i) - exVolume1.get(i);
//                long vol10SecAvg = exVolume2.get(i) * 10 / (secondsNow - 32400);
//                long volMonthly10SecAvg = maxVolumeAvg / 1260;
//                if(volMonthly10SecAvg == 0)
//                    volMonthly10SecAvg = vol10SecAvg;
//                if(vol10SecAvg < (volMonthly10SecAvg / 2))
//                    vol10SecAvg = volMonthly10SecAvg / 2;
//                if(vol10SecAvg > (volMonthly10SecAvg * 2))
//                    vol10SecAvg = volMonthly10SecAvg * 2;
//                if(vol10SecAvg < (100000000 / lp))
//                    vol10SecAvg = 100000000 / lp;
//                int volDiffRatio = 12;
//                if(marketCap >= 1000)
//                    volDiffRatio = 10;
//                if(marketCap >= 5000)
//                    volDiffRatio = 8;
//                if(maxQuantityAvg > 500 &&
//                        priceBuffer[i][(priceBufferIndex + priceBufferSize - 36) % priceBufferSize] != maxAllowed.get(i) &&
//                        priceBuffer[i][(priceBufferIndex + priceBufferSize - 36) % priceBufferSize] != minAllowed.get(i) &&
//                        priceBuffer[i][(priceBufferIndex + priceBufferSize - 36) % priceBufferSize] != 0){
//                    if(volDiff >= vol10SecAvg * volDiffRatio && bv1 < volDiff && bv2 < volDiff && bv3 < volDiff){
//                        if((secondsNow  - volumeCounterTimer.get(i)) > 180){
//                            volumeCounter.set(i, 1);
//                            volumeAnnounced.set(i,false);
//                        }
//                        else if((secondsNow  - volumeCounterTimer.get(i)) <= 30)
//                            volumeCounter.set(i, volumeCounter.get(i) + 1);
//                        volumeCounterTimer.set(i, secondsNow);
//                        if(volumeCounter.get(i) >= 5 && volumeCounter.get(i) <= 15){
//                            if(volumeCounter.get(i) % 5 == 0) {
//                                toSend6 += "#حجم_مشکوک_لحظه_ای #" + symbolNickName.get(i) + " " + symbolType.get(i);
//                                toSend6 += " \uD83D\uDD35\n";
//                                toSend6 += "آخرین تغییر حجم: " + volDiff + "\n";
//                                toSend6 += "قیمت: " + (lp/10) + " تومان ";
//                                if(ycp != 0)
//                                    toSend6 += String.format("%.2f", 100F * ((float)lp / (float)ycp - 1F)) + "% ";
//                                toSend6 += "\nنسبت به میانگین دقیقه‌ای: " + String.format("%.1f", (double) volDiff / (double) vol10SecAvg) + "\n";
//                                toSend6 += "ارزش شرکت: " + String.format("%.2f", (double) marketCap / 1000D) + " همت  ";
//                                toSend6 += "<a href=\"" + urlTablo + symbolID.get(i) + "\">تابلو</a>";
//                                toSend6 += "   <a href=\"" + urlChart + symbolID.get(i) + "\">چارت</a>" + "\n\n";
//                            }
//                        }
//                    }
//                    else if((secondsNow  - volumeCounterTimer.get(i)) > 30 && volumeCounter.get(i) > 0){
//                        volumeCounter.set(i, volumeCounter.get(i) - 1);
//                    }
//                }


            }


            if(send1 || send2 || send3 || send4){
                SendPhoto sendPhoto1 = new SendPhoto()
                        .setCaption(toSend1)
                        .setPhoto(new File("avg.png"))
                        .setParseMode(ParseMode.HTML);
                SendPhoto sendPhoto2 = new SendPhoto()
                        .setCaption(toSend2)
                        .setPhoto(new File("table.png"))
                        .setParseMode(ParseMode.HTML);
                SendPhoto sendPhoto3 = new SendPhoto()
                        .setCaption(toSend1Mafia)
                        .setPhoto(new File("avg_mafia.png"))
                        .setParseMode(ParseMode.HTML);
                SendPhoto sendPhoto4 = new SendPhoto()
                        .setCaption(toSend2Mafia)
                        .setPhoto(new File("table_mafia.png"))
                        .setParseMode(ParseMode.HTML);

                SendMessage sendMessage4 = new SendMessage()
                        .setText(toSend4)
                        .setParseMode(ParseMode.HTML);
                if(send1 && programCounter > 0){
                    customSendMessage(2, null, sendPhoto1, null, ID.PUBLIC_CHANNEL, true);
                    customSendMessage(2, null, sendPhoto3, null, ID.MAFIA_CHANNEL, true);
                }
                if(send2){
                    if(programCounter > 0){
                        customSendMessage(2, null, sendPhoto2, null, ID.PUBLIC_CHANNEL, true);
                        if(boardType % 1000 >= 100)
                            customSendMessage(2, null, sendPhoto4, null, ID.MAFIA_CHANNEL, true);
                    }
                }
                if(send4 && programCounter > 0)
                    customSendMessage(1, sendMessage4, null, null, ID.PUBLIC_CHANNEL, true);
            }

            realBuyCount1.set(i, realBuyCount2.get(i));
            legalBuyCount1.set(i, legalBuyCount2.get(i));
            realBuyVolume1.set(i, realBuyVolume2.get(i));
            legalBuyVolume1.set(i, legalBuyVolume2.get(i));
            realSellCount1.set(i, realSellCount2.get(i));
            legalSellCount1.set(i, legalSellCount2.get(i));
            realSellVolume1.set(i, realSellVolume2.get(i));
            legalSellVolume1.set(i, legalSellVolume2.get(i));

            closingPrice1.set(i, closingPrice2.get(i));
            lastPrice1.set(i, lp);
            exQuantity1.set(i, exQuantity2.get(i));
            exVolume1.set(i, exVolume2.get(i));
            exValue1.set(i, exValue2.get(i));
            minPrice1.set(i, minPrice2.get(i));
            maxPrice1.set(i, maxPrice2.get(i));
            buyQuantity1a.set(i, buyQuantity1.get(i));
            sellQuantity1a.set(i, sellQuantity1.get(i));
            buyVolume1a.set(i, buyVolume1.get(i));
            sellVolume1a.set(i, sellVolume1.get(i));
            buyPrice1a.set(i, buyPrice1.get(i));
            sellPrice1a.set(i, sellPrice1.get(i));
        }
//        totalMarketCapChange.add(100F * (float)allTMC / (float)allYMC - 100F);
//        if(timeNow.getMinute() % 2 == 0 && timeNow.getSecond() < 5){
//            saveStockIndexChartImage();
//            String caption = "#ارزش_بازار\n\n" + "این پست هر ۲ دقیقه آپدیت می شود.\n" + ID.TABLOBOT_CHANNEL;
//            if(totalMarketCapMsgID == 0){
//                SendPhoto photoMsg1 = new SendPhoto();
//                photoMsg1.setChatId(ID.PUBLIC_CHANNEL);
//                photoMsg1.setPhoto(new File("chart.png"));
//                photoMsg1.setCaption(caption);
//                Message retmsg = execute(photoMsg1);
//                totalMarketCapMsgID = retmsg.getMessageId();
//            }
//            else{
//                InputMediaPhoto imp = new InputMediaPhoto();
//                imp.setMedia(new File("chart.png"), "chart.png");
//                EditMessageMedia editMsg1 = new EditMessageMedia();
//                editMsg1.setChatId(ID.PUBLIC_CHANNEL);
//                editMsg1.setMessageId(totalMarketCapMsgID);
//                editMsg1.setMedia(imp);
//                EditMessageCaption editMsg2 = new EditMessageCaption();
//                editMsg2.setChatId(ID.PUBLIC_CHANNEL);
//                editMsg2.setMessageId(totalMarketCapMsgID);
//                editMsg2.setCaption(caption);
//                execute(editMsg1);
//                execute(editMsg2);
//            }
//        }
//        int sIndexSize = totalMarketCapChange.size();
//        if(totalMarketCapMsgID != 0 && secondsNow - totalMarketCapLastSentTime > 600 && sIndexSize >= 121 &&
//                (totalMarketCapChange.get(sIndexSize  - 1) - totalMarketCapChange.get(sIndexSize  - 121) <= 0.5  ||
//                        totalMarketCapChange.get(sIndexSize  - 1) - totalMarketCapChange.get(sIndexSize  - 121) >= 0.5)){
//            ForwardMessage forwardMsg = new ForwardMessage();
//            forwardMsg.setChatId(ID.FARDIN);
//            forwardMsg.setFromChatId(ID.PUBLIC_CHANNEL);
//            forwardMsg.setMessageId(totalMarketCapMsgID);
//            execute(forwardMsg);
//            totalMarketCapLastSentTime = secondsNow;
//        }

        if(codeToCodeSymbolID.size() > 0){
            tradeDetails2(codeToCodeSymbolID);
            System.out.print("{" + codeToCodeSymbolID.size() + "}");
        }
        if(timeNow.getHour() * 3600 + timeNow.getMinute() * 60 < 45000){
            String theDate = formattedDate(String.valueOf(today));
            if(toSend5.length() > 0)
                sendLongMessage(toSend5 + timeNowToString + "\n", ID.PUBLIC_CHANNEL, false);
            if(toSend6.length() > 0)
                sendLongMessage(toSend6 + theDate + " " + timeNowToString + "\n", volumeChatID, false);
        }
        System.out.print("C2");
    }

    private void checkHotMoneyAndOrderHistory(boolean partial) throws IOException, InterruptedException, TelegramApiException{
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int today = calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);
        today = Integer.valueOf(changeGregorianToPersian(String.valueOf(today)));
        System.out.print("T1");
        ArrayList<String> nickName = new ArrayList<>();
        ArrayList<Long> value = new ArrayList<>();
        ArrayList<Float> marketCap = new ArrayList<>();
        ArrayList<Float> ratio = new ArrayList<>();
        ArrayList<Integer> date = new ArrayList<>();
        ArrayList<String> nickName2 = new ArrayList<>();
        ArrayList<Integer> date2 = new ArrayList<>();
        ArrayList<Integer> repeats = new ArrayList<>();
        int hotMoneysLength = 0;
        int daysToCheck = 5;
        for(int i = 0; i < 2; i++) {
            String fileName = "hotMoneyHistory";
            if(i == 1)
                fileName = "supportOrderHistory";
            Scanner sc = new Scanner(new FileInputStream(fileName));
            while (sc.hasNext()) {
                nickName.add(sc.next());
                value.add(sc.nextLong());
                marketCap.add(sc.nextFloat());
                ratio.add(sc.nextFloat());
                date.add(sc.nextInt());
                if(i == 0)
                    hotMoneysLength++;
            }
            sc.close();
        }
        System.out.print("T2");
        for(int i = hotMoneysLength; i < nickName.size(); i++){
            for(int j = i + 1; j < nickName.size(); j++){
                if(nickName.get(i).equals(nickName.get(j)) && date.get(i).equals(date.get(j))){
                    nickName.remove(i);
                    value.remove(i);
                    marketCap.remove(i);
                    ratio.remove(i);
                    date.remove(i);
                    i--;
                    break;
                }
            }
        }
        System.out.print("T3");
        nickName2.add(nickName.get(0));
        for(int i = 0; i < date.size(); i++){
            int minDate = 99999999;
            for(int j = i; j < date.size(); j++){
                if(minDate > date.get(j))
                    minDate = date.get(j);
            }
            if(date2.size() == 0 || minDate > date2.get(date2.size() - 1))
                date2.add(minDate);
        }
        int firstDayToCheck = 0;
        if(date2.size() > daysToCheck)
            firstDayToCheck = date2.get(date2.size() - daysToCheck);
        else if(date2.size() > 0)
            firstDayToCheck = date2.get(0);
        for(int i = 1; i < date.size(); i++)
            if(!nickName2.contains(nickName.get(i)))
                nickName2.add(nickName.get(i));

        for(int i = 0; i < nickName2.size(); i++)
            repeats.add(0);

        for(int i = 0; i < nickName.size(); i++){
            if(date.get(i) >= firstDayToCheck){
                int index = nickName2.indexOf(nickName.get(i));
                if(index >= 0)
                    repeats.set(index, repeats.get(index)+1);
            }
        }
        System.out.print("T4");
        String toSend = "";
        for(int i = 0; i < nickName2.size(); i++){
            boolean todayRepeated = false;
            for(int j = 0; j < nickName.size(); j++) {
                if (nickName2.get(i).equals(nickName.get(j)) && date.get(j).equals(today)) {
                    todayRepeated = true;
                    break;
                }
            }
            if(repeats.get(i) >= 3 && (todayRepeated || !partial)){
                toSend += "#" + nickName2.get(i) + "\n";
                for(int j = 0; j < nickName.size(); j++){
                    if(nickName2.get(i).equals(nickName.get(j)) && date.get(j) >= firstDayToCheck){
                        if(j < hotMoneysLength)
                            toSend += "خرید_سنگین ";
                        else
                            toSend += "اردر_حمایتی ";
                        toSend += value.get(j) + " " + marketCap.get(j) + " " + ratio.get(j) + " "
                                + formattedDate(String.valueOf(date.get(j))) + "\n";
                    }
                }
            }
        }
        System.out.print("T5");
        if(!lastSOHMSent.equals(toSend)){
            if(hotMoneySupportOrderMsgID > 0) {
                DeleteMessage deleteMessage = new DeleteMessage().setChatId(ID.FARDIN).setMessageId(hotMoneySupportOrderMsgID);
                execute(deleteMessage);
            }
            Message msg = sendLongMessage(toSend, ID.FARDIN, true);
            if(msg != null){
                hotMoneySupportOrderMsgID = msg.getMessageId();
                lastSOHMSent = toSend;
            }
        }
        nickName.clear(); value.clear(); marketCap.clear(); ratio.clear();
        date.clear(); date2.clear(); nickName2.clear(); repeats.clear();
        System.out.print("T6");
    }
    private void bufferedImageSection(JTable table, String fileName, String headerOrBackgroundFileName,
                                      String watermarkFileName, int rows) throws IOException{
        JTableHeader header = table.getTableHeader();
        BufferedImage headerOrBackground = ImageIO.read(new File(headerOrBackgroundFileName));
        BufferedImage watermark = ImageIO.read(new File(watermarkFileName));
        BufferedImage bufferedImage = new BufferedImage(table.getWidth(), header.getHeight() + table.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g2d.setColor(Color.BLACK);
        header.paint(g2d);
        g2d.drawImage(headerOrBackground, 0, 0, null);
        g2d.translate(0, header.getHeight());
        table.paint(g2d);
        if(watermarkFileName.equals("watermark_avg.png")
                || watermarkFileName.equals("watermark_order.png")
                || watermarkFileName.equals("watermark_rl.png"))
            g2d.drawImage(watermark, 0, -header.getHeight(), null);
        else if(watermarkFileName.equals("watermark_ctc.png") || watermarkFileName.equals("watermark_shareholder.png")){
            g2d.drawImage(headerOrBackground, 0, -header.getHeight(), null);
            for (int i = 0; i < rows; i++){
                g2d.drawImage(watermark, 0, i*30, null);
            }
        }
        g2d.dispose();

        File file = new File(fileName);
        try {
            ImageIO.write(bufferedImage, "png", file);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public String numberWithComma(long number){
        boolean neg = (number < 0);
        if(neg)
            number *= -1;
        String str = "";
        for (int i = 0; i < 6; i++){
            if(i == 0){
                if ((number % 1000) < 10 && number >= 1000)
                    str = "00" + String.valueOf(number % 1000);
                else if((number % 1000) < 100 && number >= 1000)
                    str = "0" + String.valueOf(number % 1000);
                else if((number % 1000) >= 100 || (number % 1000) < 100 && number < 1000)
                    str = String.valueOf(number % 1000);
            }
            else {
                if ((number % 1000) < 10 && number >= 1000)
                    str = "00" + (number % 1000) + "," + str;
                else if((number % 1000) < 100 && number >= 1000)
                    str = "0" + (number % 1000) + "," + str;
                else if((number % 1000) >= 100 || (number % 1000) < 100 && number < 1000)
                    str = (number % 1000) + "," + str;
            }
            number /= 1000;
            if(number == 0)
                break;
        }
        if(neg)
            str = "-" + str;
        return  str;
    }

    private void saveTableImage(int index, int destChannel) throws IOException{
        String bgFileName = "table_bg.png";
        String saveFileName = "table.png";
        if(destChannel == 1){
            bgFileName = "table_bg_mafia.png";
            saveFileName = "table_mafia.png";
        }
        int sp1 = sellPrice1.get(index);
        int sp2 = sellPrice2.get(index);
        int sp3 = sellPrice3.get(index);
        int sp4 = sellPrice4.get(index);
        int sp5 = sellPrice5.get(index);
        long sv1 = sellVolume1.get(index);
        long sv2 = sellVolume2.get(index);
        long sv3 = sellVolume3.get(index);
        long sv4 = sellVolume4.get(index);
        long sv5 = sellVolume5.get(index);
        int sq1 = sellQuantity1.get(index);
        int sq2 = sellQuantity2.get(index);
        int sq3 = sellQuantity3.get(index);
        int sq4 = sellQuantity4.get(index);
        int sq5 = sellQuantity5.get(index);
        int bp1 = buyPrice1.get(index);
        int bp2 = buyPrice2.get(index);
        int bp3 = buyPrice3.get(index);
        int bp4 = buyPrice4.get(index);
        int bp5 = buyPrice5.get(index);
        long bv1 = buyVolume1.get(index);
        long bv2 = buyVolume2.get(index);
        long bv3 = buyVolume3.get(index);
        long bv4 = buyVolume4.get(index);
        long bv5 = buyVolume5.get(index);
        int bq1 = buyQuantity1.get(index);
        int bq2 = buyQuantity2.get(index);
        int bq3 = buyQuantity3.get(index);
        int bq4 = buyQuantity4.get(index);
        int bq5 = buyQuantity5.get(index);
        int mnl = minAllowed.get(index);
        int mxl = maxAllowed.get(index);
        int ycp = yesterdayClosingPrice.get(index);

        long maxVol = 0;
        if(sv1 > maxVol) maxVol = sv1;
        if(sv2 > maxVol) maxVol = sv2;
        if(sv3 > maxVol) maxVol = sv3;
        if(sv4 > maxVol) maxVol = sv4;
        if(sv5 > maxVol) maxVol = sv5;
        if(bv1 > maxVol) maxVol = bv1;
        if(bv2 > maxVol) maxVol = bv2;
        if(bv3 > maxVol) maxVol = bv3;
        if(bv4 > maxVol) maxVol = bv4;
        if(bv5 > maxVol) maxVol = bv5;

        Object[][] cellData1 = {{"", "", "", "", "", ""}, {"", "", "", "", "", ""}, {"", "", "", "", "", ""},
                {"", "", "", "", "", ""}, {"", "", "", "", "", ""}, {"", "", "", "", "", ""},
                {"", "", "", "", "", ""}, {"", "", "", "", "", ""}, {"", "", "", "", "", ""}};
        Object[][] cellData2 = {{"", "", "", ""}, {"", "", "", ""}, {"", "", "", ""}, {"", "", "", ""}, {"", "", "", ""},
                {"", "", "", ""}, {"", "", "", ""}, {"", "", "", ""}, {"", "", "", ""}};

        if(sp1 >= mnl && sp1 <= mxl){
            cellData1[0][0] = numberWithComma(sq1);
            cellData1[0][1] = numberWithComma(sv1);
            cellData1[0][2] = numberWithComma(sp1);
            cellData2[0][0] = String.format("%.1f",((double)sv1 * (double)sp1 / 10000000000.0D));
            cellData2[0][1] = String.format("%.2f",100.0 * (((float)sp1 / (float)ycp) - 1.0));
        }
        if(bp1 >= mnl && bp1 <= mxl){
            cellData1[0][3] = numberWithComma(bp1);
            cellData1[0][4] = numberWithComma(bv1);
            cellData1[0][5] = numberWithComma(bq1);
            cellData2[0][2] = String.format("%.2f",100.0 * (((float)bp1 / (float)ycp) - 1.0));
            cellData2[0][3] = String.format("%.1f",((double)bv1 * (double)bp1 / 10000000000.0D));
        }
        if(sp2 >= mnl && sp2 <= mxl){
            cellData1[2][0] = numberWithComma(sq2);
            cellData1[2][1] = numberWithComma(sv2);
            cellData1[2][2] = numberWithComma(sp2);
            cellData2[2][0] = String.format("%.1f",((double)sv2 * (double)sp2 / 10000000000.0D));
            cellData2[2][1] = String.format("%.2f",100.0 * (((float)sp2 / (float)ycp) - 1.0));
        }
        if(bp2 >= mnl && bp2 <= mxl){
            cellData1[2][3] = numberWithComma(bp2);
            cellData1[2][4] = numberWithComma(bv2);
            cellData1[2][5] = numberWithComma(bq2);
            cellData2[2][2] = String.format("%.2f",100.0 * (((float)bp2 / (float)ycp) - 1.0));
            cellData2[2][3] = String.format("%.1f",((double)bv2 * (double)bp2 / 10000000000.0D));
        }
        if(sp3 >= mnl && sp3 <= mxl){
            cellData1[4][0] = numberWithComma(sq3);
            cellData1[4][1] = numberWithComma(sv3);
            cellData1[4][2] = numberWithComma(sp3);
            cellData2[4][0] = String.format("%.1f",((double)sv3 * (double)sp3 / 10000000000.0D));
            cellData2[4][1] = String.format("%.2f",100.0 * (((float)sp3 / (float)ycp) - 1.0));
        }
        if(bp3 >= mnl && bp3 <= mxl){
            cellData1[4][3] = numberWithComma(bp3);
            cellData1[4][4] = numberWithComma(bv3);
            cellData1[4][5] = numberWithComma(bq3);
            cellData2[4][2] = String.format("%.2f",100.0 * (((float)bp3 / (float)ycp) - 1.0));
            cellData2[4][3] = String.format("%.1f",((double)bv3 * (double)bp3 / 10000000000.0D));
        }
        if(sp4 >= mnl && sp4 <= mxl){
            cellData1[6][0] = numberWithComma(sq4);
            cellData1[6][1] = numberWithComma(sv4);
            cellData1[6][2] = numberWithComma(sp4);
            cellData2[6][0] = String.format("%.1f",((double)sv4 * (double)sp4 / 10000000000.0D));
            cellData2[6][1] = String.format("%.2f",100.0 * (((float)sp4 / (float)ycp) - 1.0));
        }
        if(bp4 >= mnl && bp4 <= mxl){
            cellData1[6][3] = numberWithComma(bp4);
            cellData1[6][4] = numberWithComma(bv4);
            cellData1[6][5] = numberWithComma(bq4);
            cellData2[6][2] = String.format("%.2f",100.0 * (((float)bp4 / (float)ycp) - 1.0));
            cellData2[6][3] = String.format("%.1f",((double)bv4 * (double)bp4 / 10000000000.0D));
        }
        if(sp5 >= mnl && sp5 <= mxl){
            cellData1[8][0] = numberWithComma(sq5);
            cellData1[8][1] = numberWithComma(sv5);
            cellData1[8][2] = numberWithComma(sp5);
            cellData2[8][0] = String.format("%.1f",((double)sv5 * (double)sp5 / 10000000000.0D));
            cellData2[8][1] = String.format("%.2f",100.0 * (((float)sp5 / (float)ycp) - 1.0));
        }
        if(bp5 >= mnl && bp5 <= mxl){
            cellData1[8][3] = numberWithComma(bp5);
            cellData1[8][4] = numberWithComma(bv5);
            cellData1[8][5] = numberWithComma(bq5);
            cellData2[8][2] = String.format("%.2f",100.0 * (((float)bp5 / (float)ycp) - 1.0));
            cellData2[8][3] = String.format("%.1f",((double)bv5 * (double)bp5 / 10000000000.0D));
        }
        String[] columnNames1 = {"", "", "", "", "", ""};
        JTable table1 = new JTable(cellData1, columnNames1);
        table1.getColumnModel().getColumn(0).setWidth(132);
        table1.getColumnModel().getColumn(1).setWidth(316);
        table1.getColumnModel().getColumn(2).setWidth(191);
        table1.getColumnModel().getColumn(3).setWidth(192);
        table1.getColumnModel().getColumn(4).setWidth(315);
        table1.getColumnModel().getColumn(5).setWidth(134);

        String[] columnNames2 = {"", "", "", ""};
        JTable table2 = new JTable(cellData2, columnNames2);
        for (int i = 0; i < 4; i++)
            table2.getColumnModel().getColumn(i).setWidth(192);

        table1.setRowHeight(68);
        table2.setRowHeight(68);
        table1.setFont(new Font("B Yekan", Font.PLAIN, 44));
        table2.setFont(new Font("B Yekan", Font.PLAIN, 44));
        table1.setSize(1280, table1.getPreferredSize().height);
        table2.setSize(768, table1.getPreferredSize().height);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment( JLabel.CENTER );
        for (int i = 0; i < 6; i++)
            table1.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        for (int i = 0; i < 4; i++)
            table2.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);

        table1.setOpaque(true);
        table2.setOpaque(true);
        table1.setBackground(new Color(0,0,0,0F));
        table2.setBackground(new Color(0,0,0,0F));
        table1.setGridColor(new Color(0,0,0,0F));
        table2.setGridColor(new Color(0,0,0,0F));


        BufferedImage headerOrBackground = ImageIO.read(new File(bgFileName));
        BufferedImage bufferedImage = new BufferedImage(1280, 822, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(headerOrBackground, 0, 0, null);

        g2d.setColor(new Color(1F, 0.2F, 0.2F, 0.6F));
        g2d.fillRoundRect(136, 142, (int)(310L * sv1 / maxVol), 62, 20, 20);
        g2d.fillRoundRect(136, 278, (int)(310L * sv2 / maxVol), 62, 20, 20);
        g2d.fillRoundRect(136, 414, (int)(310L * sv3 / maxVol), 62, 20, 20);
        g2d.fillRoundRect(136, 550, (int)(310L * sv4 / maxVol), 62, 20, 20);
        g2d.fillRoundRect(136, 686, (int)(310L * sv5 / maxVol), 62, 20, 20);
        g2d.setColor(new Color(0.1F, 1F, 0.5F, 0.6F));
        g2d.fillRoundRect(834 + (int)(310L * (maxVol - bv1) / maxVol), 142, (int)(310L * bv1 / maxVol), 62, 20, 20);
        g2d.fillRoundRect(834 + (int)(310L * (maxVol - bv2) / maxVol), 278, (int)(310L * bv2 / maxVol), 62, 20, 20);
        g2d.fillRoundRect(834 + (int)(310L * (maxVol - bv3) / maxVol), 414, (int)(310L * bv3 / maxVol), 62, 20, 20);
        g2d.fillRoundRect(834 + (int)(310L * (maxVol - bv4) / maxVol), 550, (int)(310L * bv4 / maxVol), 62, 20, 20);
        g2d.fillRoundRect(834 + (int)(310L * (maxVol - bv5) / maxVol), 686, (int)(310L * bv5 / maxVol), 62, 20, 20);

        g2d.translate(0, 138);
        table1.paint(g2d);
        g2d.translate(256, 68);
        table2.paint(g2d);

        g2d.dispose();

        File file = new File(saveFileName);
        try {
            ImageIO.write(bufferedImage, "png", file);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void saveAverageImage(ArrayList<String> dataStrList, int destChannel) throws IOException{
        String bgFileName = "avg_bg.png";
        String saveFileName = "avg.png";
        if(destChannel == 1){
            bgFileName = "avg_bg_mafia.png";
            saveFileName = "avg_mafia.png";
        }
        int numberOfColumns = 4;
        DefaultTableModel tableModel = new DefaultTableModel();
        for (int i = 0; i < numberOfColumns; i++)
            tableModel.addColumn("");

        for (int i = 0; i < numberOfColumns; i++)
            tableModel.addRow(new Object[] {dataStrList.get(i * 3), dataStrList.get(i * 3 + 1), dataStrList.get(i * 3 + 2), ""});

        JTable table = new JTable(tableModel);
        table.getColumnModel().getColumn(0).setWidth(221);
        table.getColumnModel().getColumn(1).setWidth(217);
        table.getColumnModel().getColumn(2).setWidth(174);
        table.getColumnModel().getColumn(3).setWidth(188);

        table.setRowHeight(92);
        table.setFont(new Font("B Yekan", Font.PLAIN, 44));
        table.setSize(800, table.getPreferredSize().height);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment( JLabel.CENTER );
        for (int i = 0; i < numberOfColumns; i++)
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);

        table.setOpaque(true);
        table.setBackground(new Color(0,0,0,0F));
        table.setForeground(Color.WHITE);
        table.setGridColor(new Color(0,0,0,0F));


        BufferedImage headerOrBackground = ImageIO.read(new File(bgFileName));
        BufferedImage bufferedImage = new BufferedImage(800, 474, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(headerOrBackground, 0, 0, null);
        g2d.translate(0, 100);
        table.paint(g2d);
        g2d.dispose();

        File file = new File(saveFileName);
        try {
            ImageIO.write(bufferedImage, "png", file);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /*
    private void saveAverageImageOld(ArrayList<String> dataStrList) throws IOException{
        int numberOfColumns = 7;
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("<html><center><p style=\"color:red\">مجموع<br>(میلیارد تومان)</p></html>");
        tableModel.addColumn("<html><center><p style=\"color:red\">میانگین<br>(میلیارد تومان)</p></html>");
        tableModel.addColumn("<html><center><p style=\"color:red\">تعداد</p></html>");
        tableModel.addColumn("<html><center><p style=\"color:green\">مجموع<br>(میلیارد تومان)</p></html>");
        tableModel.addColumn("<html><center><p style=\"color:green\">میانگین<br>(میلیارد تومان)</p></html>");
        tableModel.addColumn("<html><center><p style=\"color:green\">تعداد</p></html>");
        tableModel.addColumn("");

        tableModel.addRow(new Object[] {dataStrList.get(0), dataStrList.get(1), dataStrList.get(2),
                dataStrList.get(3), dataStrList.get(4), dataStrList.get(5), "حقیقی"});
        tableModel.addRow(new Object[] {dataStrList.get(6), dataStrList.get(7), dataStrList.get(8),
                dataStrList.get(9), dataStrList.get(10), dataStrList.get(11), "حقوقی"});

        JTable table = new JTable(tableModel);
        table.getColumnModel().getColumn(0).setWidth(110);
        table.getColumnModel().getColumn(1).setWidth(110);
        table.getColumnModel().getColumn(2).setWidth(60);
        table.getColumnModel().getColumn(3).setWidth(110);
        table.getColumnModel().getColumn(4).setWidth(110);
        table.getColumnModel().getColumn(5).setWidth(60);
        table.getColumnModel().getColumn(6).setWidth(80);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("B Homa", Font.PLAIN, 18));
        header.setSize(640, 50);
        table.setRowHeight(30);
        table.setFont(new Font("B Koodak", Font.PLAIN, 18));
        table.setSize(640, table.getPreferredSize().height);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment( JLabel.CENTER );
        for (int i = 0; i < numberOfColumns; i++)
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);

        bufferedImageSection(table, "avg.png", "avg_bg.png",
                "watermark_avg.png", 0);
    }
*/
    private void saveRealLegalImage(ArrayList<String> dataStrList) throws IOException{
        int numberOfColumns = 3;
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("<html><center><p style=\"color:red\">حجم فروش</p></html>");
        tableModel.addColumn("<html><center><p style=\"color:green\">حجم خرید</p></html>");
        tableModel.addColumn("");

        tableModel.addRow(new Object[] {dataStrList.get(0), dataStrList.get(1), "حقیقی"});
        tableModel.addRow(new Object[] {dataStrList.get(2), dataStrList.get(3), "حقوقی"});

        JTable table = new JTable(tableModel);
        table.getColumnModel().getColumn(0).setWidth(100);
        table.getColumnModel().getColumn(1).setWidth(100);
        table.getColumnModel().getColumn(2).setWidth(60);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("B Homa", Font.PLAIN, 18));
        header.setSize(260, 40);
        table.setRowHeight(30);
        table.setFont(new Font("B Koodak", Font.PLAIN, 18));
        table.setSize(260, table.getPreferredSize().height);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment( JLabel.CENTER );
        for (int i = 0; i < numberOfColumns; i++)
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);

        bufferedImageSection(table, "real_legal.png", "watermark_rl.png",
                "watermark_rl.png", 0);
    }

    private void saveStockIndexChartImage() throws IOException{
        int chartWidth = 2600, chartHeight = 1200,topMargin = 50, buttomMargin = 50, leftMargin = 100, rightMargin = 20;
        int width = chartWidth + leftMargin + rightMargin;
        int height = chartHeight + topMargin + buttomMargin;
        BufferedImage background = ImageIO.read(new File("chart_bg.jpg"));
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Color DarkGreen1 = new Color(0, 150, 0);
        Color DarkGreen2 = new Color(0, 170, 0);
        Color DarkRed = new Color(150, 0, 0);
        int L = totalMarketCapChange.size();
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(background, 0, 0, null);
        g2d.setFont(new Font("B Yekan", Font.PLAIN, 40));
        g2d.setStroke(new BasicStroke(3));
        int maxPercentInt = 6;
        for (int j = 1; j < 2*maxPercentInt; j++){
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.drawLine(leftMargin + 1, topMargin + j * chartHeight / maxPercentInt / 2,
                    width - rightMargin - 1,  topMargin + j * chartHeight / maxPercentInt / 2);
            if(j < maxPercentInt)
                g2d.setColor(DarkGreen1);
            else if(j > maxPercentInt)
                g2d.setColor(Color.RED);
            else
                g2d.setColor(Color.DARK_GRAY);
            String toWrite = (maxPercentInt-j) + "%" ;
            g2d.drawString( toWrite,5 , topMargin + 5 + j * chartHeight / maxPercentInt / 2);
        }
        g2d.setColor(Color.RED);
        g2d.drawString("-" + maxPercentInt + "%" ,5 , topMargin + chartHeight + 5);
        g2d.setColor(DarkGreen1);
        g2d.drawString(maxPercentInt + "%" ,5 , topMargin + 5);
        if (halfHoursIndex.size() > 0 && halfHours.get(0).equals("09:00")) {
            for (int j = 0; j < halfHoursIndex.size(); j++) {
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.drawLine(leftMargin + halfHoursIndex.get(j), topMargin, leftMargin + halfHoursIndex.get(j), topMargin + chartHeight - 1);
                g2d.setColor(Color.DARK_GRAY);
                g2d.drawString(halfHours.get(j), leftMargin + halfHoursIndex.get(j) - 53, height - 10);
            }
        }
        g2d.setStroke(new BasicStroke(4));
        for (int i = 1; i < L; i++) {
            int y1 = 1 + (int)((totalMarketCapChange.get(i - 1) / (float)(2*maxPercentInt) + 0.5F) * (float)chartHeight);
            int y2 = 1 + (int)((totalMarketCapChange.get(i) / (float)(2*maxPercentInt) + 0.5F) * (float)chartHeight);
            if (halfHoursIndex.size() > 0 && halfHours.get(0).equals("09:00") && i < halfHoursIndex.get(0))
                g2d.setColor(Color.GRAY);
            else
                g2d.setColor(Color.BLACK);
            if(y1 <= chartHeight && y2 <= chartHeight && y1 > 0 && y2 > 0)
                g2d.drawLine(leftMargin + i - 1, topMargin + chartHeight - y1,leftMargin + i, topMargin + chartHeight - y2);
        }
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawLine(leftMargin, topMargin, leftMargin + chartWidth, topMargin);
        g2d.drawLine(leftMargin, topMargin + chartHeight, leftMargin + chartWidth, topMargin + chartHeight);
        g2d.drawLine(leftMargin, topMargin, leftMargin, topMargin + chartHeight);
        g2d.drawLine(leftMargin + chartWidth, topMargin, leftMargin + chartWidth, topMargin + chartHeight);
        g2d.dispose();
        File file = new File("chart.png");
        ImageIO.write(bufferedImage, "png", file);
    }
    
    private void saveChartImage(boolean oneSymbol, String nickName, int chartType) throws IOException{
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int today = calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);
        int chartWidth = 2600, chartHeight = 800, barChartHeight = 200, topMargin = 50, buttomMargin = 50, leftMargin = 250, rightMargin = 20;
        int width = chartWidth + leftMargin + rightMargin;
        int height = chartHeight + 2*barChartHeight + topMargin + buttomMargin;
        BufferedImage background = ImageIO.read(new File("chart_bg.jpg"));
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Color DarkGreen1 = new Color(0, 150, 0);
        Color DarkGreen2 = new Color(0, 170, 0);
        Color DarkRed = new Color(150, 0, 0);
        int symbolsCount = (symbolID.size() < defaultSymbolsCount) ? symbolID.size():defaultSymbolsCount;
        LocalTime timeNow = LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS));
        int secondsNow = timeNow.getHour() * 3600 + timeNow.getMinute() * 60 + timeNow.getSecond();
        for (int i = 0; i < symbolsCount; i++) {
            int L = priceChartData.get(i).size();
            int L2 = buyVolChartData.get(i).size();
            float exQAvg1 = (float)exQuantity2.get(i) / (float) (secondsNow - 32400);
            if(oneSymbol && !symbolNickName.get(i).equals(nickName) || !oneSymbol && exQAvg1 < 0.05 || yesterdayClosingPrice.get(i) == 0)
                continue;
            Graphics2D g2d = bufferedImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawImage(background, 0, 0, null);
//        g2d.setColor(Color.WHITE);
//        g2d.fillRect(0, 0, width, height);
            g2d.setFont(new Font("B Yekan", Font.PLAIN, 40));
            g2d.setStroke(new BasicStroke(3));
            int mnl = minAllowed.get(i);
            int mxl = maxAllowed.get(i);
            int ycp = yesterdayClosingPrice.get(i);
            int tempMin = 100000000, tempMax = 0;
            int minPercentInt = Math.round(100F * ((float)mnl / (float)ycp - 1F));
            int maxPercentInt = Math.round(100F * ((float)mxl / (float)ycp - 1F));
            if(maxPercentInt <= 5 && minPercentInt >= -5){
                for (int j = 1; j < (maxPercentInt - minPercentInt); j++){
                    g2d.setColor(Color.LIGHT_GRAY);
                    g2d.drawLine(leftMargin + 1, topMargin + j * chartHeight / (maxPercentInt - minPercentInt),
                            width - rightMargin - 1,  topMargin + j * chartHeight / (maxPercentInt - minPercentInt));
                    if(j < maxPercentInt)
                        g2d.setColor(DarkGreen1);
                    else if(j > maxPercentInt)
                        g2d.setColor(Color.RED);
                    else
                        g2d.setColor(Color.DARK_GRAY);
                    String toWrite = String.valueOf((int)((float)ycp * (1F + (float)(maxPercentInt - j)/ 100F)));
                    toWrite += " (" + (maxPercentInt-j) + "%)" ;
                    g2d.drawString( toWrite,5 , topMargin + 5 + j * chartHeight / (maxPercentInt - minPercentInt));
                }
                g2d.setColor(Color.RED);
                g2d.drawString(String.valueOf(mnl) + " (" + minPercentInt + "%)" ,5 , topMargin + chartHeight + 5);
                g2d.setColor(DarkGreen1);
                g2d.drawString(String.valueOf(mxl) + " (" + maxPercentInt + "%)" ,5 , topMargin + 5);
            }
            else {
                for (int j = 0; j < L; j++) {
                    if (j >= priceChartDataBlackIndex.get(i) && priceChartData.get(i).get(j) > tempMax)
                        tempMax = priceChartData.get(i).get(j);
                    if (j >= priceChartDataBlackIndex.get(i) && priceChartData.get(i).get(j) < tempMin)
                        tempMin = priceChartData.get(i).get(j);
                }
                if(tempMin != 0){
                    ArrayList<Integer> prices = new ArrayList<>();
                    for(int j = 100; j > -100; j--){
                        int tempPrice = (int)((float)ycp * (1F + (float)j/100F));
                        if(tempPrice <= tempMax && tempPrice >= tempMin)
                            prices.add(tempPrice);
                    }
                    for (int j = 0; j < prices.size(); j++){
                        g2d.setColor(Color.LIGHT_GRAY);
                        float h = (float)chartHeight * (float)(tempMax - prices.get(j)) / (float)(tempMax - tempMin);
                        g2d.drawLine(leftMargin + 1, topMargin + (int)h,width - rightMargin - 1,  topMargin + (int)h);
                        if(prices.get(j) < ycp)
                            g2d.setColor(Color.RED);
                        else if (prices.get(j) > ycp)
                            g2d.setColor(DarkGreen1);
                        else
                            g2d.setColor(Color.DARK_GRAY);
                        String percent = String.format("%.1f", 100F * ((float)prices.get(j) / (float)ycp - 1F));
                        g2d.drawString(String.valueOf(prices.get(j)) + " (" + percent + "%)" ,5 , topMargin + (int)h);
                    }
                }
            }
            for (int j = 0; j < halfHoursIndex.size(); j++){
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.drawLine(leftMargin + halfHoursIndex.get(j), topMargin, leftMargin + halfHoursIndex.get(j), topMargin + chartHeight + 2*barChartHeight - 1);
                g2d.setColor(Color.DARK_GRAY);
                g2d.drawString(halfHours.get(j),leftMargin + halfHoursIndex.get(j) - 53, height - 10);
            }
            long divisor = volPassed1B.get(i) ? 10000:10000000;
            long avgMinimum = 30, valMinimum = 30;
            long maxAvg1 = 0, maxAvg2 = 0, maxValDiff = 0;
            int maxAvg2index = 0, maxValDiffIndex = 0, firstNonMnlMxlIndex = 0, lastNonMnlMxlIndex = 0;
            boolean maxAvg2isGreen = true;
            long tempDiff, tempAvg;
            for (int j = 1; j < L2; j++) {
                tempAvg = (long)priceChartData.get(i).get(j * barWidth) * (long)buyVolChartData.get(i).get(j) / (long)buyCntChartData.get(i).get(j);
                if(tempAvg > maxAvg1)
                    maxAvg1 = tempAvg;
                tempAvg = (long)priceChartData.get(i).get(j * barWidth) * (long)sellVolChartData.get(i).get(j) / (long)sellCntChartData.get(i).get(j);
                if(tempAvg > maxAvg1)
                    maxAvg1 = tempAvg;
                int bcdiff = buyCntChartData.get(i).get(j) - buyCntChartData.get(i).get(j - 1);
                int scdiff = sellCntChartData.get(i).get(j) - sellCntChartData.get(i).get(j - 1);
                if(bcdiff == 0)
                    bcdiff = 1;
                if(scdiff == 0)
                    scdiff = 1;
                tempAvg = (long)priceChartData.get(i).get((j) * barWidth) * (long)(buyVolChartData.get(i).get(j) - buyVolChartData.get(i).get(j - 1)) / (long)bcdiff / divisor;
                if(tempAvg >= avgMinimum && tempAvg * bcdiff > maxAvg2){
                    maxAvg2 = tempAvg * bcdiff;
                    maxAvg2index = j * barWidth;
                    maxAvg2isGreen = true;
                }
                tempAvg = (long)priceChartData.get(i).get((j) * barWidth) * (long)(sellVolChartData.get(i).get(j) - sellVolChartData.get(i).get(j - 1)) / (long)scdiff / divisor;
                if(tempAvg >= avgMinimum && tempAvg * scdiff > maxAvg2){
                    maxAvg2 = tempAvg * scdiff;
                    maxAvg2index = j * barWidth;
                    maxAvg2isGreen = false;
                }
                tempDiff = exValueChartData.get(i).get(j) - exValueChartData.get(i).get(j - 1);
                if (tempDiff >= valMinimum && tempDiff > maxValDiff) {
                    maxValDiff = tempDiff;
                    maxValDiffIndex = j * barWidth;
                }
                if(priceChartData.get(i).get(j * barWidth) > mnl && priceChartData.get(i).get(j * barWidth) < mxl){
                    lastNonMnlMxlIndex = j;
                    if(firstNonMnlMxlIndex == 0)
                        firstNonMnlMxlIndex = j;
                }
            }
            if(maxValDiff == 0)
                maxValDiff = 1;
            int y1, y2, h;
            for (int j = 1; j < L2; j++) {
                long bcdiff = buyCntChartData.get(i).get(j) - buyCntChartData.get(i).get(j - 1);
                long scdiff = sellCntChartData.get(i).get(j) - sellCntChartData.get(i).get(j - 1);
                if (bcdiff == 0)
                    bcdiff = 1;
                if (scdiff == 0)
                    scdiff = 1;
                long buyAvg = (long) priceChartData.get(i).get((j) * barWidth) * (long) (buyVolChartData.get(i).get(j) - buyVolChartData.get(i).get(j - 1)) / bcdiff / divisor;
                long sellAvg = (long) priceChartData.get(i).get((j) * barWidth) * (long) (sellVolChartData.get(i).get(j) - sellVolChartData.get(i).get(j - 1)) / scdiff / divisor;
                y1 = (int) ((double) ((long) (chartHeight - 50) * (long) priceChartData.get(i).get((j - 1) * barWidth) * (long) buyVolChartData.get(i).get(j - 1)) / (double) ((long) buyCntChartData.get(i).get(j - 1) * maxAvg1));
                y2 = (int) ((double) ((long) (chartHeight - 50) * (long) priceChartData.get(i).get(j * barWidth) * (long) buyVolChartData.get(i).get(j)) / (double) ((long) buyCntChartData.get(i).get(j) * maxAvg1));
                h = (int) ((double) ((long) (barChartHeight - 20) * buyAvg * bcdiff) / (double) maxAvg2);
                if (y1 > 0 && y2 > 0) { // && j > firstNonMnlMxlIndex && j < lastNonMnlMxlIndex
                    g2d.setStroke(new BasicStroke(4));
                    g2d.setColor(DarkGreen2);
                    g2d.drawLine(leftMargin + (j-1) * barWidth, topMargin + chartHeight - y1, leftMargin + j * barWidth, topMargin + chartHeight - y2);
                }
                if (h > 0 && buyAvg >= avgMinimum) {
                    g2d.setStroke(new BasicStroke(barWidth - 1));
                    g2d.setColor(DarkGreen1);
                    g2d.drawLine(leftMargin + j * barWidth, topMargin + chartHeight + 2 * barChartHeight - 1, leftMargin + j * barWidth, topMargin + chartHeight + 2 * barChartHeight - h);
                }
                y1 = (int) ((double) ((long) (chartHeight - 50) * (long) priceChartData.get(i).get((j - 1) * barWidth) * (long) sellVolChartData.get(i).get(j - 1)) / (double) ((long) sellCntChartData.get(i).get(j - 1) * maxAvg1));
                y2 = (int) ((double) ((long) (chartHeight - 50) * (long) priceChartData.get(i).get(j * barWidth) * (long) sellVolChartData.get(i).get(j)) / (double) ((long) sellCntChartData.get(i).get(j) * maxAvg1));
                h = (int) ((double) ((long) (barChartHeight - 20) * sellAvg * scdiff) / (double) maxAvg2);
                if (y1 > 0 && y2 > 0) { //  && j > firstNonMnlMxlIndex && j < lastNonMnlMxlIndex
                    g2d.setStroke(new BasicStroke(4));
                    g2d.setColor(Color.RED);
                    g2d.drawLine(leftMargin + (j-1) * barWidth, topMargin + chartHeight - y1, leftMargin + j * barWidth, topMargin + chartHeight - y2);
                }
                if (h > 0 && sellAvg >= avgMinimum) {
                    g2d.setStroke(new BasicStroke(barWidth - 1));
                    g2d.setColor(DarkRed);
                    g2d.drawLine(leftMargin + j * barWidth + barWidth/2, topMargin + chartHeight + 2 * barChartHeight - 1, leftMargin + j * barWidth + barWidth/2, topMargin + chartHeight + 2 * barChartHeight - h);
                }
                if (exValueChartData.get(i).get(L2 - 1) > 0) {
                    h = (int) ((long) (barChartHeight - 20) * (long) (exValueChartData.get(i).get(j) - exValueChartData.get(i).get(j - 1)) / maxValDiff);
                    if (h > 0 && (exValueChartData.get(i).get(j) - exValueChartData.get(i).get(j - 1)) >= valMinimum) {
                        g2d.setStroke(new BasicStroke(barWidth - 1));
                        g2d.setColor(Color.BLUE);
                        g2d.drawLine(leftMargin + j * barWidth, topMargin + chartHeight + barChartHeight - 1, leftMargin + j * barWidth, topMargin + chartHeight + barChartHeight - h);
                    }
                }
            }
            g2d.setStroke(new BasicStroke(4));
            for (int j = 1; j < L; j++) {
                if (j < priceChartDataBlackIndex.get(i))
                    g2d.setColor(Color.GRAY);
                else
                    g2d.setColor(Color.BLACK);
                if (maxPercentInt <= 5 && minPercentInt >= -5 && mxl != mnl) {
                    y1 = 1 + ((priceChartData.get(i).get(j - 1)- mnl) * chartHeight / (mxl - mnl));
                    y2 = 1 + ((priceChartData.get(i).get(j) - mnl) * chartHeight / (mxl - mnl));
                    if(y1 <= chartHeight && y2 <= chartHeight && y1 > 0 && y2 > 0)
                        g2d.drawLine(leftMargin + j - 1, topMargin + chartHeight - y1,leftMargin + j, topMargin + chartHeight - y2);
                }
                else if (maxPercentInt > 5 && minPercentInt < -5 && tempMax > tempMin) {
                    y1 = 1 + ((priceChartData.get(i).get(j - 1) - tempMin) * chartHeight / (tempMax - tempMin));
                    y2 = 1 + ((priceChartData.get(i).get(j) - tempMin) * chartHeight / (tempMax - tempMin));
                    if(y1 <= chartHeight && y2 <= chartHeight && y1 > 0 && y2 > 0)
                        g2d.drawLine(leftMargin + j - 1, topMargin + chartHeight - y1,leftMargin + j, topMargin + chartHeight - y2);
                }
            }
            int avgNumber = 10;
            if(chartType == CHART_TYPE.MOVING_AVERAGE10)
                avgNumber = 10;
            else if(chartType == CHART_TYPE.MOVING_AVERAGE7)
                avgNumber = 7;
            else if(chartType == CHART_TYPE.MOVING_AVERAGE5)
                avgNumber = 5;
            else if(chartType == CHART_TYPE.MOVING_AVERAGE3)
                avgNumber = 3;
            if (chartType != CHART_TYPE.WITHOUT_MOVING_AVERAGE) {
                g2d.setStroke(new BasicStroke(2));
                g2d.setColor(Color.ORANGE);
                for (int j = (avgNumber - 1); j < (L - 1); j++) {
                    int temp1 = 0, temp2 = 0;
                    for (int k = 0; k < avgNumber; k++) {
                        temp1 += priceChartData.get(i).get(j - k);
                        temp2 += priceChartData.get(i).get(j - k + 1);
                    }
                    temp1 /= avgNumber;
                    temp2 /= avgNumber;
                    if (((float) mxl / (float) ycp <= 1.1F) && mxl > mnl) {
                        g2d.drawLine(leftMargin + j,
                                topMargin + chartHeight - 1 - ((temp1 - mnl) * chartHeight / (mxl - mnl)),
                                leftMargin + j + 1,
                                topMargin + chartHeight - 1 - ((temp2 - mnl) * chartHeight / (mxl - mnl)));
                    } else if (tempMax > tempMin) {
                        g2d.drawLine(leftMargin + j,
                                topMargin + chartHeight - 1 - ((temp1 - tempMin) * chartHeight / (tempMax - tempMin)),
                                leftMargin + j + 1,
                                topMargin + chartHeight - 1 - ((temp2 - tempMin) * chartHeight / (tempMax - tempMin)));
                    }

                }
            }
            g2d.setStroke(new BasicStroke(3));
            g2d.setColor(Color.DARK_GRAY);
            g2d.drawLine(leftMargin, topMargin, leftMargin + chartWidth, topMargin);
            g2d.drawLine(leftMargin, topMargin + chartHeight, leftMargin + chartWidth, topMargin + chartHeight);
            g2d.drawLine(leftMargin, topMargin + chartHeight + barChartHeight, leftMargin + chartWidth, topMargin + chartHeight + barChartHeight);
            g2d.drawLine(leftMargin, topMargin + chartHeight + 2*barChartHeight, leftMargin + chartWidth, topMargin + chartHeight + 2*barChartHeight);
            g2d.drawLine(leftMargin, topMargin, leftMargin, topMargin + chartHeight + 2*barChartHeight);
            g2d.drawLine(leftMargin + chartWidth, topMargin, leftMargin + chartWidth, topMargin + chartHeight + 2*barChartHeight);
            ArrayList<Integer> y = new ArrayList<>();
            ArrayList<String> title = new ArrayList<>();
            ArrayList<Color> colors = new ArrayList<>();
            colors.add(Color.BLUE);
            colors.add(DarkGreen2);
            colors.add(Color.RED);
            colors.add(maxAvg2isGreen ? DarkGreen1 : DarkRed);
            y.add(chartHeight + 25);
            if(maxValDiff < 1000)
                title.add(String.valueOf(maxValDiff) + " میلیون");
            else
                title.add(String.format("%.1f", (float)maxValDiff / 1000F) + " میلیارد");
            y.add(chartHeight - (int)((double)((long)(chartHeight - 50) * (long)priceChartData.get(i).get(L - 1) * (long)buyVolChartData.get(i).get(L2 - 1)) / (double)((long)buyCntChartData.get(i).get(L2 - 1) * maxAvg1)));
            y.add(chartHeight - (int)((double)((long)(chartHeight - 50) * (long)priceChartData.get(i).get(L - 1) * (long)sellVolChartData.get(i).get(L2 - 1)) / (double)((long)sellCntChartData.get(i).get(L2 - 1) * maxAvg1)));
            title.add(String.valueOf(Math.round((float)priceChartData.get(i).get(L - 1) * (float)buyVolChartData.get(i).get(L2 - 1) / (float) buyCntChartData.get(i).get(L2 - 1) / (float)divisor)) + " میلیون");
            title.add(String.valueOf(Math.round((float)priceChartData.get(i).get(L - 1) * (float)sellVolChartData.get(i).get(L2 - 1) / (float) sellCntChartData.get(i).get(L2 - 1) / (float)divisor)) + " میلیون");
            y.add(chartHeight + barChartHeight + 25);
            if(maxAvg2 < 1000)
                title.add(String.valueOf(maxAvg2) + " میلیون");
            else
                title.add(String.format("%.1f", (float)maxAvg2 / 1000F) + " میلیارد");
            int x1 = (L < chartWidth + rightMargin - 200) ?  leftMargin + L + 5:width - 200;
            int x2 = (maxAvg2index < chartWidth + rightMargin - 200) ?  leftMargin + maxAvg2index + 8:width - 200;
            int x3 = (maxValDiffIndex < chartWidth + rightMargin - 200) ?  leftMargin + maxValDiffIndex + 8:width - 200;
            if(x1 == width - 200){
                y.set(1, y.get(1) - 20);
                y.set(2, y.get(2) - 20);
            }
            if(y.get(2) >= y.get(1) && y.get(2) - y.get(1) < 40){
                if(y.get(2) < chartHeight - 60)
                    y.set(2, y.get(1) + 40);
                else
                    y.set(1, y.get(2) - 40);
            }
            else if(y.get(1) > y.get(2) && y.get(1) - y.get(2) < 40){
                if(y.get(1) < chartHeight - 60)
                    y.set(1, y.get(2) + 40);
                else
                    y.set(2, y.get(1) - 40);
            }

            for (int j = 0; j <= 3; j++){
                g2d.setColor(colors.get(j));
                if(colors.get(j) == DarkGreen1 || colors.get(j) == DarkRed){
                    if(maxAvg2 >= avgMinimum)
                        g2d.drawString(title.get(j), x2, topMargin + y.get(j));
                }
                else if(colors.get(j) == Color.BLUE){
                    if(maxValDiff >= valMinimum)
                        g2d.drawString(title.get(j), x3, topMargin + y.get(j));
                }
                else
                    g2d.drawString(title.get(j), x1, topMargin + y.get(j));
            }

            g2d.dispose();
            String todayPersian = changeGregorianToPersian(String.valueOf(today));
            String fileName = "charts/" + symbolNickName.get(i) + "-" + todayPersian;
            if(oneSymbol)
                fileName = "chart";
            File file = new File(fileName + ".png");
            ImageIO.write(bufferedImage, "png", file);
            FileWriter writer = new FileWriter(fileName + ".txt");
            String tempStr = "";
            for (int j = 0; j < L; j++)
                tempStr += priceChartData.get(i).get(j) + " ";
            writer.write(tempStr + "\n");
            tempStr = "";
            for (int j = 0; j < L2; j++)
                tempStr += buyVolChartData.get(i).get(j) + " ";
            writer.write(tempStr + "\n");
            tempStr = "";
            for (int j = 0; j < L2; j++)
                tempStr += buyCntChartData.get(i).get(j) + " ";
            writer.write(tempStr + "\n");
            tempStr = "";
            for (int j = 0; j < L2; j++)
                tempStr += sellVolChartData.get(i).get(j) + " ";
            writer.write(tempStr + "\n");
            tempStr = "";
            for (int j = 0; j < L2; j++)
                tempStr += sellCntChartData.get(i).get(j) + " ";
            writer.write(tempStr + "\n");
            tempStr = "";
            for (int j = 0; j < L2; j++)
                tempStr += exValueChartData.get(i).get(j) + " ";
            writer.write(tempStr + "\n");
            writer.close();

            symbolsCount = (symbolID.size() < defaultSymbolsCount) ? symbolID.size():defaultSymbolsCount;
        }
    }

    private void positiveRange() throws IOException, TelegramApiException, InterruptedException{
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int today = calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);
        LocalTime timeNow = LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS));
        int secondsNow = timeNow.getHour() * 3600 + timeNow.getMinute() * 60 + timeNow.getSecond();
        int timeFrameMin = 6, timeFrameMax = priceBufferSize;
        int L = priceChartData.get(0).size();
        if(L <= timeFrameMin || secondsNow <= 33000)
            return;
        if(L < timeFrameMax)
            timeFrameMax = L;
        int symbolsCount = (symbolID.size() < defaultSymbolsCount) ? symbolID.size():defaultSymbolsCount;
        for (int i = 0; i <symbolsCount; i++) {
            L = priceChartData.get(i).size();
            int mnl = minAllowed.get(i);
            int mxl = maxAllowed.get(i);
            float exQAvg1 = (float)exQuantity2.get(i) / (float) (secondsNow - 32400);
            long exValAvg = exValue2.get(i) / 333333L / (long)(secondsNow - 32400);
            int L2 = exValueChartData.get(i).size();
            float rising = 0, falling = 0;
            if(exQAvg1 < 0.1 || L2 < 2 || (float)(exValueChartData.get(i).get(L2 - 1) - exValueChartData.get(i).get(L2 - 2)) / (float)exValAvg < 10.0)
                continue;
//            long divisor = volPassed1B.get(i) ? 10:10000;
//            long avgMinimum = 50;
//            float buyAvgNow = (float)priceChartData.get(i).get(L - 1) * (float)buyVolChartData.get(i).get((L - 1) / barWidth) / (float)buyCntChartData.get(i).get((L - 1) / barWidth);
            //            float sellAvgNow = (float)priceChartData.get(i).get(L - 1) * (float)sellVolChartData.get(i).get(L - 1) / (float)sellCntChartData.get(i).get(L - 1);
//            if(!volPassed1B.get(i) && buyAvgNow < 100000000F || volPassed1B.get(i) && buyAvgNow < 100F)
//                continue;
//            long topBuySum = 0, topSellSum = 1;
//            for (int j = 0; j < (L2 - 1); j++) {
//                long bcdiff = buyCntChartData.get(i).get(j + 1) - buyCntChartData.get(i).get(j);
//                long scdiff = sellCntChartData.get(i).get(j + 1) - sellCntChartData.get(i).get(j);
//                if (bcdiff == 0)
//                    bcdiff = 1;
//                if (scdiff == 0)
//                    scdiff = 1;
//                long buyAvg = (long) priceChartData.get(i).get((j + 1) * barWidth) * (long) (buyVolChartData.get(i).get(j + 1) - buyVolChartData.get(i).get(j)) / bcdiff / divisor;
//                long sellAvg = (long) priceChartData.get(i).get((j + 1) * barWidth) * (long) (sellVolChartData.get(i).get(j + 1) - sellVolChartData.get(i).get(j)) / scdiff / divisor;
//                if (j >= 2 && priceChartData.get(i).get((j - 2) * barWidth) > mnl && j >= 2 && priceChartData.get(i).get((j - 2) * barWidth) < mxl) {
//                    if (buyAvg >= avgMinimum)
//                        topBuySum += (buyAvg * bcdiff);
//                    if (sellAvg >= avgMinimum)
//                        topSellSum += (sellAvg * scdiff);
//                }
//            }
//            long marketCap = totalVolume.get(i) * closingPrice2.get(i) / 10000000000L;
//            long buySumThreshold = 10000 * marketCap / (marketCap + 6000L);
//            if(topBuySum < buySumThreshold || (float)topBuySum / (float)topSellSum < 1.5F)
//                continue;
//            float risingMinTime = 0, fallingMinTime = 0;
            for (int j = 1; j <= (timeFrameMax - 2); j++){
//                if(buyVolChartData.get(i).get((L - 1) / barWidth) == 0 || sellVolChartData.get(i).get((L - 1) / barWidth) == 0)
//                    break;
                if(priceAvgBuffer[i][(priceBufferIndex + priceBufferSize - j - 1) % priceBufferSize] < priceAvgBuffer[i][(priceBufferIndex + priceBufferSize - j) % priceBufferSize])
                    rising += 1.0;
                else if(priceAvgBuffer[i][(priceBufferIndex + priceBufferSize - j - 1) % priceBufferSize] > priceAvgBuffer[i][(priceBufferIndex + priceBufferSize - j) % priceBufferSize])
                    falling += 1.0;
//                if(j == timeFrameMin){
//                    risingMinTime = rising / (float)j;
//                    fallingMinTime = falling / (float)j;
//                }
                if(priceChartData.get(i).get(L - j) > mnl && priceChartData.get(i).get(L - j) < mxl
                        && (float)priceChartData.get(i).get(L - 1) / (float)priceChartData.get(i).get(L - j) >= 1.012
                        && priceChartData.get(i).get(L - 1) > priceChartData.get(i).get(L - 2)
                        && j >= timeFrameMin && (rising / (float)j) >= 0.65 && (falling / (float)j) <= 0.25) {
//                        && buyAvgNow / ((float)priceChartData.get(i).get(L - j) * (float)buyVolChartData.get(i).get((L - j) / barWidth) / (float)buyCntChartData .get(i).get((L - j) / barWidth)) >= 1.1
//                        && sellAvgNow / ((float)priceChartData.get(i).get(L - j) * (float)sellVolChartData.get(i).get(L - j) / (float)sellCntChartData.get(i).get(L - j)) <= 1.05

                    if ((secondsNow - rangeCounterTimer.get(i)) > 600) {
                        rangeCounter.set(i, 1);
                        rangeAnnounced.set(i, false);
                    } else if ((secondsNow - rangeCounterTimer.get(i)) <= 15) {
                        rangeCounter.set(i, rangeCounter.get(i) + 1);
                    }
                    rangeCounterTimer.set(i, secondsNow);
                    if (!rangeAnnounced.get(i) && rangeCounter.get(i) == 1) {
                        saveChartImage(true, symbolNickName.get(i), CHART_TYPE.WITHOUT_MOVING_AVERAGE);
                        timeNow = LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS));
                        String caption = "#رنج_مثبت_حجم_مشکوک #" + symbolNickName.get(i) + " " + symbolType.get(i) + "\n";
                        caption += "قدرت خریدار حقیقی: ";
                        caption += String.format("%.1f", ((double) realBuyVolume2.get(i) / (double) realBuyCount2.get(i)) / ((double) realSellVolume2.get(i) / (double) realSellCount2.get(i))) + "\n";
                        caption += "<a href=\"" + urlTablo + symbolID.get(i) + "\">تابلو</a>";
                        caption += "   <a href=\"" + urlChart + symbolID.get(i) + "\">چارت</a>";
                        caption += "\n" + formattedDate(changeGregorianToPersian(String.valueOf(today))) + " ";
                        String timeNowToString = timeNow.toString();
                        if (timeNow.toString().length() >= 8)
                            timeNowToString = timeNow.toString().substring(0, 8);
                        caption += timeNowToString + "\n" + ID.TABLOBOT_CHANNEL;


//                        float ratio1 = (float)(exValueChartData.get(i).get(L2 - 1) - exValueChartData.get(i).get(L2 - 2)) / (float)exValAvg;
//                        caption += String.format("\n\n%.2f %.2f ", ratio1, exQAvg1)
//                                + (exValueChartData.get(i).get(L2 - 1) - exValueChartData.get(i).get(L2 - 2)) + " " + exValAvg + "\n";


                        SendPhoto message = new SendPhoto();
                        message.setPhoto(new File("chart.png"));
                        message.setCaption(caption);
                        message.setParseMode(ParseMode.HTML);
                        customSendMessage(2, null, message, null, rangeChatID, false);
                        rangeAnnounced.set(i, true);
                    }
                    break;
                }
            }
        }
    }
//    private void findDailyElliottWave(){
//        float waveSensivity = 0.003F;
//        int symbolsCount = (symbolID.size() < defaultSymbolsCount) ? symbolID.size():defaultSymbolsCount;
//        int symbolsForElliottSize = dailyElliottSymbols.size();
//        for (int i = 0; i <symbolsCount; i++) {
//            boolean symbolExist = false;
//            for (int j = 0; j < symbolsForElliottSize; j++){
//                if(symbolNickName.get(i).equals(dailyElliottSymbols.get(j))){
//                    symbolExist = true;
//                    break;
//                }
//            }
//            if(symbolExist){
//                int floor1 = 0, ceiling1 = 0, floor2 = 0, ceiling2 = 0;
//                int a1 = -1, a2 = -1, b1 = -1, b2 = -1;
//                boolean ceiling1Found = false, floor1Found = false, floor2Found = false;
//                int prevPoint = 0, currentPoint = 0;
//                for (int j = (chartDataCounter - 1); j >= 2; j--){
////                    prevPoint = currentPoint;
//                    currentPoint = priceChartData.get(i][j - 2] + priceChartData[i][j - 1] + priceChartData[i][j];
//                    if(j == (chartDataCounter - 1)){
//                        floor2 = currentPoint;
//                        ceiling2 = currentPoint;
//                        a2 = j;
//                        b2 = j;
//                    }
//                    else {
//                        if(!floor2Found) {
//                            if ((float)floor2 / (float)currentPoint > (1F + waveSensivity)) {
//                                floor2 = currentPoint;
//                            } else if ((float)currentPoint / (float) ceiling2 > (1F + waveSensivity)) {
//                                ceiling1 = currentPoint;
//                                floor2Found = true;
//                            }
//                        }
//                        if(floor2Found && !ceiling1Found){
//                            if (currentPoint > prevPoint) {
//                                ceiling1 = currentPoint;
//                            } else if((float)currentPoint / (float)floor2 < waveSensivity){
//                                floor1 = currentPoint;
//                                ceiling1Found = true;
//                            }
//                        }
//                        if(ceiling1Found && !floor1Found){
//                            if (currentPoint < prevPoint) {
//                                floor1 = currentPoint;
//                            } else {
//                                // check if wave 2 is not short
////                                if()
//                                floor1Found = true;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }

    public void tradeDetails(int threadIndex, int totalThreads) throws IOException, InterruptedException{
        FileWriter writer1 = new FileWriter("ctc" + threadIndex, true);
        FileWriter writer2 = new FileWriter("ctcOut" + threadIndex, true);
        writer2.write("s " + threadIndex + "   " + LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS)) + "\n");

//        ArrayList<Integer> realBuyCount = new ArrayList<>();
//        ArrayList<Integer> realSellCount = new ArrayList<>();
//        ArrayList<Integer> legalBuyCount = new ArrayList<>();
//        ArrayList<Integer> legalSellCount = new ArrayList<>();

//        writer2.write("A1");
//        Scanner sc = new Scanner(new FileInputStream("instTradeHistory"));
//        if(sc.hasNext()) {
//            sc.next();
//            String tempStr;
//            int symbolIndex;
//            while (sc.hasNext()) {
//                tempStr = sc.next();
//                symbolIndex = symbolID.indexOf(tempStr);
//                tempStr = sc.next();
//                if(symbolIndex > -1)
//                    oneMonthValueAvg.set(symbolIndex, Long.valueOf(tempStr));
//                sc.next();
//                sc.next();
//                sc.next();
//            }
//            sc.close();
//        }
//        sc.close();

        ArrayList<String> symbolIDTemp = new ArrayList<>();
        ArrayList<Integer> lastCheckedRowTemp = new ArrayList<>();
        writer2.write("B1,");
        writer2.write(symbolID.size() + "," + lastCheckedRow.size() + ",");
        int i_low = symbolID.size() * threadIndex / totalThreads;
        int i_hi = symbolID.size() * (threadIndex + 1) / totalThreads;
        for (int i = i_low; i < i_hi; i++){
            symbolIDTemp.add(symbolID.get(i));
            lastCheckedRowTemp.add(lastCheckedRow.get(i));
        }
        int symbolsNumber = symbolIDTemp.size();
        final StringBuilder sb = new StringBuilder("");
        final StringBuilder sb2 = new StringBuilder("");
//        ArrayList<Thread> threads = new ArrayList<>();
        writer2.write("B2\n");
        for (int i = 0; i < symbolsNumber; i++) {
//                if(!str1.contains(symbolNickName.get(i)))
//                    continue;

            final int symbolIndex = symbolID.indexOf(symbolIDTemp.get(i));
            final int i2 = i;
            if(symbolIndex < 0)
                continue;
            writer2.write(symbolNickName.get(symbolIndex) + " ");
            if (exQuantity2.get(symbolIndex) >= 15 && totalVolume.get(symbolIndex) * closingPrice2.get(symbolIndex) < 500000000000000L) {

                final String result = httpTask2(threadIndex + 1,urlTradeDetail + symbolID.get(symbolIndex), 200, 5);
//                Runnable task1 = () -> {
                    String temp, temp2;
                    int resultLength = result.length();
                    long codeToCodeVolume = 0, codeToCodeCount = 0, codeToCodeVolumeTemp = 0, codeToCodeCountTemp = 0;
                    int tempVolume = 0, volume2 = -1, tempTime = 0, lastTime = 0, tempCounter = 0, lastRow = 0, tempRow = 1, tempPrice = 0;
                    long[] tempLongArray;
                    for (int j = 0; j < resultLength; j++) {
                        if (j + 5 < resultLength && result.substring(j, j + 5).equals("<row>")) {
                            j += 13;
                            tempLongArray = stringNextLong(result, '<', j);
                            j = (int)tempLongArray[1] + 14;
                            tempRow = (int)tempLongArray[0];
                            if (tempRow >= lastCheckedRowTemp.get(i2) - 1) { //ERR
                                temp = "";
                                temp2 = "";

                                for (; j < resultLength; j++) {
                                    if (result.charAt(j) == '<') {
                                        j += 15;
                                        break;
                                    }
                                    if (result.charAt(j) >= '0' && result.charAt(j) <= '9' || result.charAt(j) == ':') {
                                        temp2 += result.charAt(j);
                                        if (result.charAt(j) != ':')
                                            temp += result.charAt(j);
                                    } else {
                                        temp = "";
                                        break;
                                    }
                                }
                                tempTime = (temp.equals("")) ? 0 : Integer.valueOf(temp);
                                tempTime = tempTime / 10000 * 3600 + (tempTime % 10000) / 100 * 60 + (tempTime % 100);
                                tempLongArray = stringNextLong(result, '<', j);
                                j = (int)tempLongArray[1] + 14;
                                tempVolume = (int)tempLongArray[0];
                                tempLongArray = stringNextLong(result, '.', j);
                                j = (int)tempLongArray[1] + 16;
                                tempPrice = (int)tempLongArray[0];
                                if (tempTime >= 32700 && ((tempVolume % 100000) == 0 || (tempVolume + volume2) % 50000 == 0)) {
//                                        try {
//                                            writer.write(tempRow + " "  + temp2 + " " + volume2 + " " + tempVolume  + "\n");
//                                        } catch (IOException e) {
//                                            e.printStackTrace();
//                                        }
                                    if (!(tempTime - lastTime <= 1 && tempRow - lastRow <= 5)) {
                                        if (tempCounter >= 5 && codeToCodeVolumeTemp * tempPrice >= 5000000000L) {
                                            try {
                                                writer1.write(symbolNickName.get(symbolIndex) + " " + tempRow + " " + temp2 + " " + codeToCodeVolumeTemp + "\n");
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                                
                                            }
                                            codeToCodeVolume += codeToCodeVolumeTemp;
                                            codeToCodeCount += codeToCodeCountTemp;
                                        }
                                        tempCounter = 0;
                                        codeToCodeVolumeTemp = 0;
                                        codeToCodeCountTemp = 0;
                                    }
                                    if (tempCounter == 0 || (tempTime - lastTime <= 1 && tempRow - lastRow <= 5)) {
                                        if (tempCounter == 0 || tempRow - lastRow > 1) {
                                            tempCounter++;
                                            codeToCodeVolumeTemp += volume2;
                                            codeToCodeCountTemp++;
                                        }
                                        tempCounter++;
                                        codeToCodeVolumeTemp += tempVolume;
                                        codeToCodeCountTemp++;
//                                            try {
//                                                writer.write( "******* " + tempCounter + " " + codeToCodeVolumeTemp  + "\n");
//                                            } catch (IOException e) {
//                                                e.printStackTrace();
//                                                
//                                            }
                                    }
                                    lastRow = tempRow;
                                    lastTime = tempTime;
                                } else {
                                    if (tempCounter >= 5 && codeToCodeVolumeTemp * tempPrice >= 5000000000L
                                            && !(tempTime - lastTime <= 1 && tempRow - lastRow <= 5)) {
                                        try {
                                            writer1.write(symbolNickName.get(symbolIndex) + " " + tempRow + " " + temp2 + " " + codeToCodeVolumeTemp + "\n");
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                            
                                        }
                                        codeToCodeVolume += codeToCodeVolumeTemp;
                                        codeToCodeCount += codeToCodeCountTemp;
                                        tempCounter = 0;
                                        codeToCodeVolumeTemp = 0;
                                        codeToCodeCountTemp = 0;
                                    }
                                }
                                volume2 = tempVolume;
                            }
                        }
                    }
                    if(tempRow > 1)
                        lastCheckedRowTemp.set(i2, tempRow); //ERR
//                        int realBuyCountTemp, realSellCountTemp, legalBuyCountTemp, legalSellCountTemp;
//                        realBuyCountTemp = realBuyCount2.get(symbolIndex) - realBuyCount.get(symbolIndex);
//                        realSellCountTemp = realSellCount2.get(symbolIndex) - realSellCount.get(symbolIndex);
//                        legalBuyCountTemp = legalBuyCount2.get(symbolIndex) - legalBuyCount.get(symbolIndex);
//                        legalSellCountTemp = legalSellCount2.get(symbolIndex) - legalSellCount.get(symbolIndex);
                    long realBuyVolumeTemp, realSellVolumeTemp, legalBuyVolumeTemp, legalSellVolumeTemp;
                    realBuyVolumeTemp = realBuyVolume2.get(symbolIndex) - realBuyVolumeDiff.get(symbolIndex);
                    realSellVolumeTemp = realSellVolume2.get(symbolIndex) - realSellVolumeDiff.get(symbolIndex);
                    legalBuyVolumeTemp = legalBuyVolume2.get(symbolIndex) - legalBuyVolumeDiff.get(symbolIndex);
                    legalSellVolumeTemp = legalSellVolume2.get(symbolIndex) - legalSellVolumeDiff.get(symbolIndex);
                    realBuyVolumeDiff.set(symbolIndex, realBuyVolume2.get(symbolIndex));
                    realSellVolumeDiff.set(symbolIndex, realSellVolume2.get(symbolIndex));
                    legalBuyVolumeDiff.set(symbolIndex, legalBuyVolume2.get(symbolIndex));
                    legalSellVolumeDiff.set(symbolIndex, legalSellVolume2.get(symbolIndex));
                    String codeToCodeType = "";
                    int codeToCodeTypeInt = 0;
                    if(realSellVolumeTemp > legalSellVolumeTemp * 2){
                        codeToCodeType += "حقیقی به ";
                        codeToCodeTypeInt = 10;
                    }
                    else if(legalSellVolumeTemp > realSellVolumeTemp * 2){
                        codeToCodeType += "حقوقی به ";
                        codeToCodeTypeInt = 20;
                    }
                    else{
                        codeToCodeType += "نامعلوم به ";
                        codeToCodeTypeInt = 30;
                    }
                    if(realBuyVolumeTemp > legalBuyVolumeTemp * 2){
                        codeToCodeType += "حقیقی";
                        codeToCodeTypeInt += 1;
                    }
                    else if(legalBuyVolumeTemp > realBuyVolumeTemp * 2){
                        codeToCodeType += "حقوقی";
                        codeToCodeTypeInt += 2;
                    }
                    else{
                        codeToCodeType += "نامعلوم";
                        codeToCodeTypeInt += 3;
                    }
                    long marketCap = totalVolume.get(symbolIndex) * closingPrice2.get(symbolIndex) / 10000000000L;
                    double ctcRatioThreshold1 = 15D / ((double) marketCap + 6000D);
                    ctcRatioThreshold1 = (ctcRatioThreshold1 > 0.0005) ? ctcRatioThreshold1:0.0005;
                    double ctcRatioThreshold2 = ctcRatioThreshold1 / 5D;
                    int criteria = 0;
                    boolean crit1 = (double) codeToCodeVolume / (double) totalVolume.get(symbolIndex) >= ctcRatioThreshold1;
                    boolean crit2 = (double) codeToCodeVolume / (double) totalVolume.get(symbolIndex) >= ctcRatioThreshold2;
                    boolean crit3 = codeToCodeVolume * (long) lastPrice2.get(symbolIndex) >= 50000000000L;
                    boolean crit4 = codeToCodeVolume * (long) lastPrice2.get(symbolIndex) >= 10000000000L;
                    boolean crit5 = exVolume2.get(symbolIndex) != 0 && (double) codeToCodeVolume / (double) exVolume2.get(symbolIndex) >= 0.2D;
                    boolean crit6 = symbolIndex < valAvg1Month.size() && valAvg1Month.get(symbolIndex) != 0 && exVolume2.get(symbolIndex) / valAvg1Month.get(symbolIndex) >= 3;
                    boolean crit7 = realSellVolume2.get(symbolIndex) != 0 && realBuyCount2.get(symbolIndex) != 0
                            && (((double) realBuyVolume2.get(symbolIndex) * (double) realSellCount2.get(symbolIndex)) / ((double) realSellVolume2.get(symbolIndex) * (double) realBuyCount2.get(symbolIndex))) > 1.5D;
                    if (crit5)
                        criteria++;
                    if (crit6)
                        criteria++;
                    if (crit7)
                        criteria++;
                    if(codeToCodeTypeInt == 21)
                        criteria++;
                    if (crit2 && crit4
                            && (codeToCodeTypeInt == 21 || codeToCodeTypeInt == 23 ||  codeToCodeTypeInt == 31)
                            && !symbolType.get(symbolIndex).equals(SYMBOL_TYPE.HAGH_TAGHADOM)) {
                        long tempVol = totalCodeToCodeVolume.get(symbolIndex) + codeToCodeVolume;
                        long tempCnt = totalCodeToCodeCount.get(symbolIndex) + codeToCodeCount;
                        totalCodeToCodeVolume.set(symbolIndex, tempVol);
                        totalCodeToCodeCount.set(symbolIndex, tempCnt);
                    }
                    boolean crit8 = (double) totalCodeToCodeVolume.get(symbolIndex) / (double) totalVolume.get(symbolIndex) >= ctcRatioThreshold1;
                    boolean crit9 = totalCodeToCodeVolume.get(symbolIndex) * (long) closingPrice2.get(symbolIndex) >= 50000000000L;
                    if (crit8)
                        criteria++;
                    if (crit9)
                        criteria++;
                    if(codeToCodeCount >= 5){
                        String crit1_0 = String.format("%.1f", 1000D * (double) codeToCodeVolume / (double) totalVolume.get(symbolIndex));
                        String crit3_0 = String.valueOf(codeToCodeVolume * (long) lastPrice2.get(symbolIndex) / 10000000000L);
                        String crit8_0 = String.format("%.1f", 1000D * (double)  totalCodeToCodeVolume.get(symbolIndex) / (double) totalVolume.get(symbolIndex));
                        String crit9_0 = String.valueOf( totalCodeToCodeVolume.get(symbolIndex) * (long) lastPrice2.get(symbolIndex) / 10000000000L);
                        try {
                            writer1.write(symbolNickName.get(symbolIndex) + " " + crit1_0 + " " + crit3_0 + " " + crit8_0 + " " + crit9_0 + " " + codeToCodeTypeInt +  "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                            
                        }
                    }
                    if (codeToCodeCount >= 5 && totalCodeToCodeCount.get(symbolIndex) > 5 && (crit1 && crit3 || crit8 && crit9)
                            && (codeToCodeTypeInt == 21 || codeToCodeTypeInt == 23 ||  codeToCodeTypeInt == 31)
                            && !symbolType.get(symbolIndex).equals(SYMBOL_TYPE.HAGH_TAGHADOM)) {
                        String tempStr1 = criteria + ";" + codeToCodeType + ";";
                        tempStr1 += String.format("%.2f", ((double) realBuyVolume2.get(symbolIndex) / (double) realBuyCount2.get(symbolIndex))
                                                        / ((double) realSellVolume2.get(symbolIndex) / (double) realSellCount2.get(symbolIndex))) + ";";
                        if (symbolIndex < valAvg1Month.size() && valAvg1Month.get(symbolIndex) > 0L)
                            tempStr1 += String.format("%.2f", (double) exValue2.get(symbolIndex) / (double) valAvg1Month.get(symbolIndex)) + ";";
                        else
                            tempStr1 += "0;";
                        tempStr1 += String.format("%.1f", (double) exVolume2.get(symbolIndex) / 1000000D) + ";";
                        tempStr1 += String.format("%.1f", (double) totalCodeToCodeVolume.get(symbolIndex) * 100D / (double) exVolume2.get(symbolIndex)) + ";";
                        tempStr1 += String.format("%.1f", (double) (totalVolume.get(symbolIndex) * lastPrice2.get(symbolIndex)) / 10000000000000D) + ";";
                        tempStr1 += String.format("%.2f", (double) totalCodeToCodeVolume.get(symbolIndex) * 1000D / (double) totalVolume.get(symbolIndex)) + ";";
                        tempStr1 += String.format("%.2f", (double) (totalCodeToCodeVolume.get(symbolIndex) * (long) lastPrice2.get(symbolIndex)) / 10000000000D) + ";";
                        tempStr1 += String.format("%.1f", (double) totalCodeToCodeVolume.get(symbolIndex) / 1000000D) + ";";
                        tempStr1 += totalCodeToCodeCount.get(symbolIndex) + ";" + symbolNickName.get(symbolIndex) + "\n";
                        sb.append(tempStr1);
                        if (criteria >= 2)
                            sb2.append("#" + symbolNickName.get(symbolIndex) + " ");
                    }

//                };
//                Thread thread1 = new Thread(task1);
//                threads.add(thread1);
//                threads.get(threads.size() - 1).start();
            }
        }
        writer2.write("\nB3");
        int index;
        for (int i = 0; i < symbolsNumber; i++) {
            index = symbolID.indexOf(symbolIDTemp.get(i)); //ERR
            if(index >= 0)
                lastCheckedRow.set(index, lastCheckedRowTemp.get(i));
            else
                lastCheckedRow.set(index, 2);
        }
//        for (int i = 0; i < threads.size(); i++) {
//            threads.get(i).join();
//        }
        writer2.write("B4");
        if (sb.length() > 0 && programCounter > 30) {
            writer1.write("\n################################\n" + sb.toString());
            int numOfRows = saveCodeToCodeImage(sb.toString(), threadIndex);
            writer2.write("B5");
            if(numOfRows > 0) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                int today = calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);
                String toSend = "#کد_به_کد ";
                toSend +=  sb2.toString() + "\n";
                toSend += formattedDate(changeGregorianToPersian(String.valueOf(today))) + "\n";
                toSend += ID.TABLOBOT_CHANNEL;
                SendPhoto message = new SendPhoto();
                message.setPhoto(new File("ctc" + threadIndex + ".png"));
                message.setCaption(toSend);
                try {
                    customSendMessage(2, null, message, null, ID.PUBLIC_CHANNEL, true);
                    customSendMessage(2, null, message, null, ID.PRIVATE_CODETOCODE, true);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                    
                }
            }
        }
        writer2.write("f " + threadIndex + "   " + LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS)) + "\nB6\n");
        writer1.close();
        writer2.close();
    }

    public void tradeDetails2(ArrayList<String> symbolIDs) throws IOException, InterruptedException{
        FileWriter writer1 = new FileWriter("ctc", true);
        FileWriter writer2 = new FileWriter("ctcOut", true);
        LocalTime time = LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS));
        writer2.write("s " + symbolIDs.size() + " " + time + "\n");

        int symbolsNumber = symbolIDs.size();
        String str1 = "", str2 = "";
        for (int i = 0; i < symbolsNumber; i++) {
            int symbolIndex = symbolID.indexOf(symbolIDs.get(i));
            if(symbolIndex < 0)
                continue;
            writer2.write(symbolNickName.get(symbolIndex) + " ");
            if (exQuantity2.get(symbolIndex) >= 15 && totalVolume.get(symbolIndex) * closingPrice2.get(symbolIndex) < 500000000000000L) {
                time = LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS));
                int secondsNow = time.getHour() * 3600 + time.getMinute() * 60 + time.getSecond();
                final String result = httpTask2(1,urlTradeDetail + symbolID.get(symbolIndex), 200, 5);
                String temp, temp2;
                int resultLength = result.length();
                long codeToCodeVolume = 0, codeToCodeCount = 0, codeToCodeVolumeTemp = 0, codeToCodeCountTemp = 0;
                int tempVolume = 0, volume2 = -1, tempTime = 0, lastTime = 0, tempCounter = 0, lastRow = 0, tempRow = 1, tempPrice = 0;
                long[] tempLongArray;
                for (int j = 0; j < resultLength; j++) {
                    if (j + 5 < resultLength && result.substring(j, j + 5).equals("<row>")) {
                        j += 13;
                        tempLongArray = stringNextLong(result, '<', j);
                        j = (int)tempLongArray[1] + 14;
                        tempRow = (int)tempLongArray[0];
                        temp = "";
                        temp2 = "";
                        for (; j < resultLength; j++) {
                            if (result.charAt(j) == '<') {
                                j += 15;
                                break;
                            }
                            if (result.charAt(j) >= '0' && result.charAt(j) <= '9' || result.charAt(j) == ':') {
                                temp2 += result.charAt(j);
                                if (result.charAt(j) != ':')
                                    temp += result.charAt(j);
                            } else {
                                temp = "";
                                break;
                            }
                        }
                        tempTime = (temp.equals("")) ? 0 : Integer.valueOf(temp);
                        tempTime = tempTime / 10000 * 3600 + (tempTime % 10000) / 100 * 60 + (tempTime % 100);

                        if (tempRow >= lastCheckedRow2.get(symbolIndex) - 1 && tempTime >= secondsNow - 60) {

                            tempLongArray = stringNextLong(result, '<', j);
                            j = (int)tempLongArray[1] + 14;
                            tempVolume = (int)tempLongArray[0];
                            tempLongArray = stringNextLong(result, '.', j);
                            j = (int)tempLongArray[1] + 16;
                            tempPrice = (int)tempLongArray[0];
                            if (tempTime >= 32700 && ((tempVolume % 100000) == 0 || (tempVolume + volume2) % 50000 == 0)) {
                                if (!(tempTime - lastTime <= 1 && tempRow - lastRow <= 5)) {
                                    if (tempCounter >= 5 && codeToCodeVolumeTemp * tempPrice >= 5000000000L) {
                                        try {
                                            writer1.write(symbolNickName.get(symbolIndex) + " " + tempRow + " " + temp2 + " " + codeToCodeVolumeTemp + "\n");
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                            
                                        }
                                        codeToCodeVolume += codeToCodeVolumeTemp;
                                        codeToCodeCount += codeToCodeCountTemp;
                                    }
                                    tempCounter = 0;
                                    codeToCodeVolumeTemp = 0;
                                    codeToCodeCountTemp = 0;
                                }
                                if (tempCounter == 0 || (tempTime - lastTime <= 1 && tempRow - lastRow <= 5)) {
                                    if (tempCounter == 0 || tempRow - lastRow > 1) {
                                        tempCounter++;
                                        codeToCodeVolumeTemp += volume2;
                                        codeToCodeCountTemp++;
                                    }
                                    tempCounter++;
                                    codeToCodeVolumeTemp += tempVolume;
                                    codeToCodeCountTemp++;
                                }
                                lastRow = tempRow;
                                lastTime = tempTime;
                            } else {
                                if (tempCounter >= 5 && codeToCodeVolumeTemp * tempPrice >= 5000000000L
                                        && !(tempTime - lastTime <= 1 && tempRow - lastRow <= 5)) {
                                    try {
                                        writer1.write(symbolNickName.get(symbolIndex) + " " + tempRow + " " + temp2 + " " + codeToCodeVolumeTemp + "\n");
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                        
                                    }
                                    codeToCodeVolume += codeToCodeVolumeTemp;
                                    codeToCodeCount += codeToCodeCountTemp;
                                    tempCounter = 0;
                                    codeToCodeVolumeTemp = 0;
                                    codeToCodeCountTemp = 0;
                                }
                            }
                            volume2 = tempVolume;
                        }
                    }
                }
                if(tempRow > 1)
                    lastCheckedRow2.set(symbolIndex, tempRow);

                long marketCap = totalVolume.get(symbolIndex) * closingPrice2.get(symbolIndex) / 10000000000L;
                double ctcRatioThreshold1 = 15D / ((double) marketCap + 6000D);
                ctcRatioThreshold1 = (ctcRatioThreshold1 > 0.0005) ? ctcRatioThreshold1:0.0005;
                double ctcRatioThreshold2 = ctcRatioThreshold1 / 5D;
                int criteria = 1;
                boolean crit1 = (double) codeToCodeVolume / (double) totalVolume.get(symbolIndex) >= ctcRatioThreshold1;
                boolean crit2 = (double) codeToCodeVolume / (double) totalVolume.get(symbolIndex) >= ctcRatioThreshold2;
                boolean crit3 = codeToCodeVolume * (long) lastPrice2.get(symbolIndex) >= 50000000000L;
                boolean crit4 = codeToCodeVolume * (long) lastPrice2.get(symbolIndex) >= 10000000000L;
                boolean crit5 = exVolume2.get(symbolIndex) != 0 && (double) codeToCodeVolume / (double) exVolume2.get(symbolIndex) >= 0.2D;
                boolean crit6 = symbolIndex < valAvg1Month.size() && valAvg1Month.get(symbolIndex) != 0 && exVolume2.get(symbolIndex) / valAvg1Month.get(symbolIndex) >= 3;
                boolean crit7 = realSellVolume2.get(symbolIndex) != 0 && realBuyCount2.get(symbolIndex) != 0
                        && (((double) realBuyVolume2.get(symbolIndex) * (double) realSellCount2.get(symbolIndex)) / ((double) realSellVolume2.get(symbolIndex) * (double) realBuyCount2.get(symbolIndex))) > 1.5D;
                if (crit5)
                    criteria++;
                if (crit6)
                    criteria++;
                if (crit7)
                    criteria++;
                if (crit2 && crit4
                        && !symbolType.get(symbolIndex).equals(SYMBOL_TYPE.HAGH_TAGHADOM)) {
                    long tempVol = totalCodeToCodeVolume2.get(symbolIndex) + codeToCodeVolume;
                    long tempCnt = totalCodeToCodeCount2.get(symbolIndex) + codeToCodeCount;
                    totalCodeToCodeVolume2.set(symbolIndex, tempVol);
                    totalCodeToCodeCount2.set(symbolIndex, tempCnt);
                }
                boolean crit8 = (double) totalCodeToCodeVolume2.get(symbolIndex) / (double) totalVolume.get(symbolIndex) >= ctcRatioThreshold1;
                boolean crit9 = totalCodeToCodeVolume2.get(symbolIndex) * (long) closingPrice2.get(symbolIndex) >= 50000000000L;
                if (crit8)
                    criteria++;
                if (crit9)
                    criteria++;
                if(codeToCodeCount >= 5){
                    String crit1_0 = String.format("%.1f", 1000D * (double) codeToCodeVolume / (double) totalVolume.get(symbolIndex));
                    String crit3_0 = String.valueOf(codeToCodeVolume * (long) lastPrice2.get(symbolIndex) / 10000000000L);
                    String crit8_0 = String.format("%.1f", 1000D * (double)  totalCodeToCodeVolume2.get(symbolIndex) / (double) totalVolume.get(symbolIndex));
                    String crit9_0 = String.valueOf( totalCodeToCodeVolume2.get(symbolIndex) * (long) lastPrice2.get(symbolIndex) / 10000000000L);
                    try {
                        writer1.write(symbolNickName.get(symbolIndex) + " " + crit1_0 + " " + crit3_0 + " " + crit8_0 + " " + crit9_0 + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                        
                    }
                }
                if (codeToCodeCount >= 5 && totalCodeToCodeCount2.get(symbolIndex) > 5 && (crit1 && crit3 || crit8 && crit9)
                        && !symbolType.get(symbolIndex).equals(SYMBOL_TYPE.HAGH_TAGHADOM)) {
                    String tempStr1 = criteria + ";حقوقی به حقیقی;";
                    tempStr1 += String.format("%.2f", ((double) realBuyVolume2.get(symbolIndex) / (double) realBuyCount2.get(symbolIndex))
                            / ((double) realSellVolume2.get(symbolIndex) / (double) realSellCount2.get(symbolIndex))) + ";";
                    if (symbolIndex < valAvg1Month.size() && valAvg1Month.get(symbolIndex) > 0L)
                        tempStr1 += String.format("%.2f", (double) exValue2.get(symbolIndex) / (double) valAvg1Month.get(symbolIndex)) + ";";
                    else
                        tempStr1 += "0;";
                    tempStr1 += String.format("%.1f", (double) exVolume2.get(symbolIndex) / 1000000D) + ";";
                    tempStr1 += String.format("%.1f", (double) totalCodeToCodeVolume2.get(symbolIndex) * 100D / (double) exVolume2.get(symbolIndex)) + ";";
                    tempStr1 += String.format("%.1f", (double) (totalVolume.get(symbolIndex) * lastPrice2.get(symbolIndex)) / 10000000000000D) + ";";
                    tempStr1 += String.format("%.2f", (double) totalCodeToCodeVolume2.get(symbolIndex) * 1000D / (double) totalVolume.get(symbolIndex)) + ";";
                    tempStr1 += String.format("%.2f", (double) (totalCodeToCodeVolume2.get(symbolIndex) * (long) lastPrice2.get(symbolIndex)) / 10000000000D) + ";";
                    tempStr1 += String.format("%.1f", (double) totalCodeToCodeVolume2.get(symbolIndex) / 1000000D) + ";";
                    tempStr1 += totalCodeToCodeCount2.get(symbolIndex) + ";" + symbolNickName.get(symbolIndex) + "\n";
                    str1 += tempStr1;
                    if (criteria >= 2)
                        str2 += "#" + symbolNickName.get(symbolIndex) + " ";
                }
            }
        }
        writer2.write("\n" + str1 + "\n");
        if (str1.length() > 0 && programCounter > 30) {
            writer1.write("\n################################\n" + str1);
            int numOfRows = saveCodeToCodeImage(str1, 0);
            writer2.write("B2");
            if(numOfRows > 0) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                int today = calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);
                String toSend = "#کد_به_کد ";
                toSend +=  str2 + "\n";
                toSend += formattedDate(changeGregorianToPersian(String.valueOf(today))) + "\n";
                toSend += ID.TABLOBOT_CHANNEL;
                SendPhoto message = new SendPhoto();
                message.setPhoto(new File("ctc0.png"));
                message.setCaption(toSend);
                try {
//                    customSendMessage(2, null, message, null, ID.PUBLIC_CHANNEL, true);
                    customSendMessage(2, null, message, null, ID.PUBLIC_CHANNEL, true);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                    
                }
            }
        }
        writer2.write("f " + LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS)) + "\n");
        writer1.close();
        writer2.close();
    }

    private int saveCodeToCodeImage(String dataStr, int threadIndex) throws IOException{
        int numberOfColumns = 12;
        DefaultTableModel tableModel = new DefaultTableModel();
//        tableModel.addColumn("تعداد معیار");
//        tableModel.addColumn("<html><p style=\"color:orange\">نوع احتمالی</p></html>");
//        tableModel.addColumn("<html><p style=\"color:orange\">قدرت خریدار</p></html>");
//        tableModel.addColumn("<html><center><p style=\"font-size:13px;color:orange\">نسبت ارزش معاملات<br>به میانگین ماه</p></html>");
//        tableModel.addColumn("<html><center><p style=\"color:orange\">نسبت به<br>حجم روز ٪</p></html>");
//        tableModel.addColumn("<html><center>حجم روز<br>(میلیون)</html>");
//        tableModel.addColumn("<html><center>ارزش شرکت<br>(هـ.م.ت)</html>");
//        tableModel.addColumn("<html><center><p style=\"color:orange\">نسبت به حجم<br>کل (هزارم)</p></html>");
//        tableModel.addColumn("<html><center>حجم کدبه‌کد<br>(میلیون)</html>");
//        tableModel.addColumn("<html><center><p style=\"color:orange\">ارزش کدبه‌کد<br>(میلیارد تومان)</p></html>");
//        tableModel.addColumn("<html><center>تعداد<br>کدبه‌کد</html>");
//        tableModel.addColumn("نماد");
        for (int i = 0; i < numberOfColumns; i++)
            tableModel.addColumn("");

        String[][] tableData;
        int dataStrLength = dataStr.length();
        int numberOfRows = 0;

        for (int i = 0; i < dataStrLength; i++){
            if(dataStr.charAt(i) == '\n'){
                numberOfRows++;
            }
        }
        if(numberOfRows > 100)
            numberOfRows = 100;
        tableData = new String[numberOfRows][numberOfColumns];

        String tempStr = "";
        int x = 0, y = 0;
        for (int i = 0; (i < dataStrLength && x < numberOfRows); i++){
            if(dataStr.charAt(i) == ';' || dataStr.charAt(i) == '\n'){
                tableData[x][y] = tempStr;
                tempStr = "";
                y = (y + 1) % numberOfColumns;
                if(y == 0)
                    x++;
            }
            else {
                tempStr += dataStr.charAt(i);
            }
        }

        int rowsN = 0;
        for (int i = 6; i >= 2; i--){
            for (int j = 0; j < numberOfRows; j++){
                if(tableData[j][0].equals(String.valueOf(i))){
                    tableModel.addRow(new Object[] {tableData[j][0], tableData[j][1], tableData[j][2],
                            tableData[j][3], tableData[j][4], tableData[j][5], tableData[j][6],
                            tableData[j][7], tableData[j][8], tableData[j][9], tableData[j][10], tableData[j][11]});
                    rowsN++;
                    if(rowsN >= 20)
                        break;
                }
            }
            if(rowsN >= 20)
                break;
        }

        JTable table = new JTable(tableModel);
        table.getColumnModel().getColumn(0).setWidth(83);
        table.getColumnModel().getColumn(1).setWidth(110);
        table.getColumnModel().getColumn(2).setWidth(102);
        table.getColumnModel().getColumn(3).setWidth(147);
        table.getColumnModel().getColumn(4).setWidth(71);
        table.getColumnModel().getColumn(5).setWidth(81);
        table.getColumnModel().getColumn(6).setWidth(96);
        table.getColumnModel().getColumn(7).setWidth(106);
        table.getColumnModel().getColumn(8).setWidth(105);
        table.getColumnModel().getColumn(9).setWidth(96);
        table.getColumnModel().getColumn(10).setWidth(58);
        table.getColumnModel().getColumn(11).setWidth(80);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("B Homa", Font.PLAIN, 18));
        header.setSize(1134, 50);
        table.setRowHeight(30);
        table.setFont(new Font("B Koodak", Font.PLAIN, 18));
        table.setSize(1134, table.getPreferredSize().height);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment( JLabel.CENTER );
        for (int i = 0; i < numberOfColumns; i++)
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);

        bufferedImageSection(table, "ctc" + threadIndex + ".png", "ctc_header.png",
                "watermark_ctc.png", numberOfRows);
        return tableModel.getRowCount();
    }
    public void instTradeHistory() throws IOException{
        valAvg1Month.clear();
        volAvg1Month.clear();
        exQAvg1Month.clear();
        inQueueDays.clear();
        int symbolsNumber = symbolID.size();
        for (int i = 0; i < symbolsNumber; i++){
            valAvg1Month.add(0L);
            volAvg1Month.add(0L);
            exQAvg1Month.add(0);
            inQueueDays.add(0);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int today = calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);
        Scanner sc = new Scanner(new FileInputStream("instTradeHistory"));
        String tempStr;
        int symbolIndex;
        if(sc.hasNext()) {
            tempStr = sc.next();
            if (tempStr.equals(String.valueOf(today))) {
                while (sc.hasNext()) {
                    tempStr = sc.next();
                    symbolIndex = symbolID.indexOf(tempStr);
                    tempStr = sc.next();
                    if(symbolIndex > -1)
                        valAvg1Month.set(symbolIndex, Long.valueOf(tempStr));
                    tempStr = sc.next();
                    if(symbolIndex > -1)
                        volAvg1Month.set(symbolIndex, Long.valueOf(tempStr));
                    tempStr = sc.next();
                    if(symbolIndex > -1)
                        exQAvg1Month.set(symbolIndex, Integer.valueOf(tempStr));
                    tempStr = sc.next();
                    if(symbolIndex > -1)
                        inQueueDays.set(symbolIndex, Integer.valueOf(tempStr));
                }
                sc.close();
                return;
            }
        }
        sc.close();

        FileWriter writer = new FileWriter("instTradeHistory");
        writer.write(today + "\n");
        System.out.println("\ntradeHistory " + symbolsNumber);
        for (int i = 0; i < symbolsNumber; i++){

            System.out.print(i + " ");
            String result = "";
            for (int k = 0; k < 5; k++){
                result = httpTask2(5,urlTradeHistory + symbolID.get(i) + urlTradeHistoryPF, 200, 3);
                if(result.length() > 2 && result.substring(0, 2).equals("20"))
                    break;
            }
            if(result.length() > 2 && !result.substring(0, 2).equals("20"))
                continue;
            FileWriter fw = new FileWriter("priceHistory/" + symbolNickName.get(i));
            int inQDays = 0, date;
            int pmax1 = -1, pmin1 = -1, cp1 = -1, pl1 = -1, pf1 = -1, py1 = -1, q1 = -1;
            int pmax2, pmin2, cp2, pl2, pf2, py2, q2, pmax3, pmin3, cp3, pl3, pf3;
            long val2, vol2;
            int resultLength =  result.length();
            int yearpmax = 0, yearpmin = 10000000;
            boolean reachedQHead = false;
            int days = 0;
            long val1MonthSum = 0, vol1MonthSum = 0;
            int exQ1MonthSum = 0, oneMonthDays = 0;
            boolean oneMonthSumCalculated = false;
            float coeff = 1;
            long[] tempLongArray;
            for (int j = 0; j < resultLength; j++) {
                days++;
                tempLongArray = stringNextLong(result, '@', j);
                j = (int)tempLongArray[1];
                date = (int)tempLongArray[0];
                tempLongArray = stringNextLong(result, '.', j);
                j = (int)tempLongArray[1] + 3;
                pmax2 = (int)tempLongArray[0];
                tempLongArray = stringNextLong(result, '.', j);
                j = (int)tempLongArray[1] + 3;
                pmin2 = (int)tempLongArray[0];
                tempLongArray = stringNextLong(result, '.', j);
                j = (int)tempLongArray[1] + 3;
                cp2 = (int)tempLongArray[0];
                tempLongArray = stringNextLong(result, '.', j);
                j = (int)tempLongArray[1] + 3;
                pl2 = (int)tempLongArray[0];
                tempLongArray = stringNextLong(result, '.', j);
                j = (int)tempLongArray[1] + 3;
                pf2 = (int)tempLongArray[0];
                tempLongArray = stringNextLong(result, '.', j);
                j = (int)tempLongArray[1] + 3;
                py2 = (int)tempLongArray[0];
                tempLongArray = stringNextLong(result, '.', j);
                j = (int)tempLongArray[1] + 3;
                val2 = tempLongArray[0];
                tempLongArray = stringNextLong(result, '@', j);
                j = (int)tempLongArray[1];
                vol2 = tempLongArray[0];
                tempLongArray = stringNextLong(result, ';', j);
                j = (int)tempLongArray[1] - 1;
                q2 = (int)tempLongArray[0];

                if(cp2 != py1 && py1 != -1)
                    coeff *= (float)cp2 / (float)py1;
                pmax3 = (int)((float)pmax2 / coeff);
                pmin3 = (int)((float)pmin2 / coeff);
                cp3 = (int)((float)cp2 / coeff);
                pl3 = (int)((float)pl2 / coeff);
                pf3 = (int)((float)pf2 / coeff);
                if(pmax3 > 0 && pmin3 > 0 && cp3 > 0 && pl3 > 0 && pf3 > 0){
                    fw.write(changeGregorianToPersian(String.valueOf(date)) + " " +
                            pmax3 + " " + pmin3 + " " + cp3 + " " + pl3 + " " + pf3 + " " + val2 + " " + "\n");
                }
                
                if(pmax1 != -1 && !reachedQHead) {
                    if ((pf1 == pl1 && pf1 == pmax1 && pf1 == pmin1) || q1 == 0) {
                        if(pl1 == 1000 && cp1 == 1000 && py1 == 1000)
                            reachedQHead = true;
                        if (inQDays <= 0 && pl1 <= py1)
                            inQDays--;
                        else if (inQDays >= 0 && pl1 >= py1)
                            inQDays++;
                    }
                    else{
                        reachedQHead = true;
                    }
                }
                int oneMonthAgo = (today % 10000 >= 200) ? (today - 100):(today - 8900);
//                int threeMonthsAgo = (today % 10000 >= 400) ? (today - 300):(today - 9100);
//                int sixMonthsAgo = (today % 10000 >= 700) ? (today - 600):(today - 9400);
                if(!oneMonthSumCalculated){
                    if(date > oneMonthAgo){
                        val1MonthSum += val2;
                        vol1MonthSum += vol2;
                        exQ1MonthSum += q2;
                    }
                    else{
                        oneMonthDays = days;
                        oneMonthSumCalculated = true;
                    }
                }
//                if(date > (today - 10000)){
//                    if(pmax1 > yearpmax)
//                        yearpmax = pmax1;
//                    if(pmin1 < yearpmin && pmin1 > 0)
//                        yearpmin = pmin1;
//                }
//                else
//                    break;

                if(date < (today - 20000))
                    break;

                pmax1 = pmax2;
                pmin1 = pmin2;
                cp1 = cp2;
                pl1 = pl2;
                pf1 = pf2;
                py1 = py2;
                q1 = q2;
            }
            fw.close();
            if(!oneMonthSumCalculated)
                oneMonthDays = days;
            if(oneMonthDays > 0){
                valAvg1Month.set(i, val1MonthSum / oneMonthDays);
                volAvg1Month.set(i, vol1MonthSum / oneMonthDays);
                exQAvg1Month.set(i, exQ1MonthSum / oneMonthDays);
            }

            inQueueDays.set(i, inQDays);
            writer.write(symbolID.get(i) + " " + valAvg1Month.get(i) + " " + volAvg1Month.get(i)
                    + " " + exQAvg1Month.get(i) + " " + inQueueDays.get(i) + "\n");
        }
        writer.close();
        instTradeHistory();
    }

    public void statistics() throws IOException {
        valAvg3Month.clear();
        valAvg12Month.clear();
        volAvg3Month.clear();
        volAvg12Month.clear();
        exQAvg3Month.clear();
        exQAvg12Month.clear();
        int symbolsNumber = symbolID.size();
        for (int i = 0; i < symbolsNumber; i++){
            valAvg3Month.add(0L);
            valAvg12Month.add(0L);
            volAvg3Month.add(0L);
            volAvg12Month.add(0L);
            exQAvg3Month.add(0);
            exQAvg12Month.add(0);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int today = calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);

        Scanner sc = new Scanner(new FileInputStream("statistics"));
        String tempStr;
        int symbolIndex;
        if(sc.hasNext()) {
            tempStr = sc.next();
            if (tempStr.equals(String.valueOf(today))) {
                while (sc.hasNext()) {
                    tempStr = sc.next();
                    symbolIndex = symbolID.indexOf(tempStr);
                    tempStr = sc.next();
                    if(symbolIndex > -1)
                        valAvg3Month.set(symbolIndex, Long.valueOf(tempStr));
                    tempStr = sc.next();
                    if(symbolIndex > -1)
                        valAvg12Month.set(symbolIndex, Long.valueOf(tempStr));
                    tempStr = sc.next();
                    if(symbolIndex > -1)
                        volAvg3Month.set(symbolIndex, Long.valueOf(tempStr));
                    tempStr = sc.next();
                    if(symbolIndex > -1)
                        volAvg12Month.set(symbolIndex, Long.valueOf(tempStr));
                    tempStr = sc.next();
                    if(symbolIndex > -1)
                        exQAvg3Month.set(symbolIndex, Integer.valueOf(tempStr));
                    tempStr = sc.next();
                    if(symbolIndex > -1)
                        exQAvg12Month.set(symbolIndex, Integer.valueOf(tempStr));
                }
                sc.close();
                return;
            }
        }
        sc.close();

        FileWriter writer = new FileWriter("statistics");
        writer.write(today + "\n");
        System.out.print("\nstatistics\n");
        for (int i = 0; i < symbolsNumber; i++){
            if(symbolType.get(i).equals(SYMBOL_TYPE.HAGH_TAGHADOM) || symbolType.get(i).equals(SYMBOL_TYPE.SANDOGH)
                    || symbolNickName.get(i).equals("کیا") || symbolNickName.get(i).equals("غنیلی") || symbolNickName.get(i).equals("فماک"))
                continue;
            System.out.print(i + " ");
            String result = "";
            for (int k = 0; k < 5; k++){
                result = httpTask2(5,urlStatistics + symbolID.get(i) + urlStatisticsPF, 200, 3);
                if(result.length() > 2 && result.substring(0, 2).equals("1,"))
                    break;
            }
            int resultLength =  result.length();
            long[] tempLongArray;
            for (int j = 0; j < resultLength; j++) {
                if(result.substring(j, j + 2).equals("1,")){
                    j += 2;
                    tempLongArray = stringNextLong(result, ';', j);
                    j = (int)tempLongArray[1];
                    valAvg3Month.set(i, tempLongArray[0]);
                }
                if(result.substring(j, j + 2).equals("2,")){
                    j += 2;
                    tempLongArray = stringNextLong(result, ';', j);
                    j = (int)tempLongArray[1];
                    valAvg12Month.set(i, tempLongArray[0]);
                }
                if(result.substring(j, j + 2).equals("3,")) {
                    j += 2;
                    tempLongArray = stringNextLong(result, ';', j);
                    j = (int)tempLongArray[1];
                }
                if(result.substring(j, j + 2).equals("4,")) {
                    j += 2;
                    tempLongArray = stringNextLong(result, ';', j);
                    j = (int)tempLongArray[1];
                }

                if(result.substring(j, j + 2).equals("5,")){
                    j += 2;
                    tempLongArray = stringNextLong(result, ';', j);
                    j = (int)tempLongArray[1];
                    volAvg3Month.set(i, tempLongArray[0]);
                }
                if(result.substring(j, j + 2).equals("6,")){
                    j += 2;
                    tempLongArray = stringNextLong(result, ';', j);
                    j = (int)tempLongArray[1];
                    volAvg12Month.set(i, tempLongArray[0]);
                }
                if(result.substring(j, j + 2).equals("7,")) {
                    j += 2;
                    tempLongArray = stringNextLong(result, ';', j);
                    j = (int)tempLongArray[1];
                }
                if(result.substring(j, j + 2).equals("8,")) {
                    j += 2;
                    tempLongArray = stringNextLong(result, ';', j);
                    j = (int)tempLongArray[1];
                }
                if(result.substring(j, j + 2).equals("9,")){
                    j += 2;
                    tempLongArray = stringNextLong(result, ';', j);
                    j = (int)tempLongArray[1];
                    exQAvg3Month.set(i, (int)tempLongArray[0]);
                }
                if(result.substring(j, j + 3).equals("10,")){
                    j += 3;
                    tempLongArray = stringNextLong(result, ';', j);
                    exQAvg12Month.set(i, (int)tempLongArray[0]);
                }
                break;
            }
            writer.write(symbolID.get(i) + " " + valAvg3Month.get(i) + " " + valAvg12Month.get(i)
                    + " " + volAvg3Month.get(i) + " " + volAvg12Month.get(i)
                    + " " + exQAvg3Month.get(i) + " " + exQAvg12Month.get(i) + "\n");
        }
        writer.close();
        statistics();
    }

    private int saveShareholdersChangeImage(String dataStr) throws IOException{
        int numberOfColumns = 5;

        DefaultTableModel tableModel = new DefaultTableModel();
//        tableModel.addColumn("ضریب");
//        tableModel.addColumn("<html><center>ارزش شرکت<br>(هـ.م.ت)</html>");
//        tableModel.addColumn("<html><center><p>ارزش معامله<br>(میلیارد تومان)</p></html>");
//        tableModel.addColumn("نماد");
//        tableModel.addColumn("سهامدار");
        for (int i = 0; i < numberOfColumns; i++)
            tableModel.addColumn("");

        String[][] tableData;
        int dataStrLength = dataStr.length();
        int numberOfRows = 0;

        for (int i = 0; i < dataStrLength; i++){
            if(dataStr.charAt(i) == '\n'){
                numberOfRows++;
            }
        }
        if(numberOfRows > 100)
            numberOfRows = 100;
        tableData = new String[numberOfRows][numberOfColumns];

        String tempStr = "";
        int x = 0, y = 0;
        for (int i = 0; (i < dataStrLength && x < numberOfRows); i++){
            if(dataStr.charAt(i) == ';' || dataStr.charAt(i) == '\n'){
                tableData[x][y] = tempStr;
                tempStr = "";
                y = (y + 1) % numberOfColumns;
                if(y == 0)
                    x++;
            }
            else {
                tempStr += dataStr.charAt(i);
            }
        }


        float max = 0;
        int maxIndex = -1;
        ArrayList<Integer> sortedIndexes = new ArrayList<>();
        for (int i = 0; i < numberOfRows; i++){
            for (int j = 0; j < numberOfRows; j++){
                float tempFloat = Float.valueOf(tableData[j][0]);
                if(tempFloat > max && !sortedIndexes.contains(j)){
                    max = tempFloat;
                    maxIndex = j;
                }
            }
            sortedIndexes.add(maxIndex);
            tableModel.addRow(new Object[] {tableData[maxIndex][0], tableData[maxIndex][1],
                    tableData[maxIndex][2], tableData[maxIndex][3], tableData[maxIndex][4]});
            max = 0;
        }

        JTable table = new JTable(tableModel);
        table.getColumnModel().getColumn(0).setWidth(100);
        table.getColumnModel().getColumn(1).setWidth(100);
        table.getColumnModel().getColumn(2).setWidth(100);
        table.getColumnModel().getColumn(3).setWidth(100);
        table.getColumnModel().getColumn(4).setWidth(400);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("B Homa", Font.PLAIN, 18));
        header.setSize(800, 50);
        table.setRowHeight(30);
        table.setFont(new Font("B Koodak", Font.PLAIN, 18));
        table.setSize(800, table.getPreferredSize().height);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment( JLabel.CENTER );
        for (int i = 0; i < numberOfColumns; i++)
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);

        bufferedImageSection(table, "shareholderchange.png", "shareholderchange_header.png",
                "watermark_shareholder.png", numberOfRows);
        return tableModel.getRowCount();
    }

    public void findCIsin() throws InterruptedException, IOException, TelegramApiException{
        marketWatchInit(false, true);
        initSomeArrayList(true);
        System.out.println("init done.");
        ArrayList<String> symbolNickName2 = new ArrayList<>();
        ArrayList<String> symbolCode2 = new ArrayList<>();
        Scanner sc = new Scanner(new FileInputStream("cisin"));
        String tempStr = "", tempStr2 = "";
        while (sc.hasNext()) {
            tempStr = sc.next();
            if(sc.hasNext())
                tempStr2 = sc.next();
            if (!(tempStr.equals("") || tempStr2.equals("") || symbolNickName2.contains(tempStr) || symbolCode2.contains(tempStr2))
                    && isSaham(tempStr)){
                symbolNickName2.add(tempStr);
                symbolCode2.add(tempStr2);
            }
        }
        sc.close();

        for (int i = 0; i < symbolID.size(); i++) {
            String nickname = symbolNickName.get(i);
            if(symbolNickName2.contains(nickname) || !isSaham(nickname) || nickname.charAt(nickname.length() - 1) == 'ح')
//                    || nickname.length() > 4 && nickname.substring(0,4).equals("سنفت")
//                    || nickname.length() > 3 && nickname.substring(0,3).equals("پست")
//                    || nickname.length() > 3 && nickname.substring(0,3).equals("زعف")
//                    || nickname.length() > 3 && nickname.substring(0,3).equals("زیر")
//                    || nickname.length() > 3 && nickname.substring(0,3).equals("سکه")
//                    || nickname.length() > 3 && nickname.substring(0,3).equals("کشم")
//                    || nickname.length() > 3 && nickname.substring(0,3).equals("رطب")
//                    || nickname.length() > 7 && nickname.substring(0,7).equals("عسناسنگ")
//                    || nickname.length() > 6 && nickname.substring(0,6).equals("عفولاد")
//                    || nickname.length() > 5 && nickname.substring(0,5).equals("عکاوه")
//                    || nickname.length() > 5 && nickname.substring(0,5).equals("عیلام")
//                    || nickname.length() > 7 && nickname.substring(0,7).equals("عپلی_جم")
//                    || nickname.length() > 3 && nickname.substring(0,3).equals("تسه")
//                    || nickname.length() > 4 && nickname.substring(0,4).equals("تملی"))
                continue;
            String result;
            String temp;
            result = httpTask2(5,urlTablo + symbolID.get(i), 500, 3);
            int resultLength = result.length();
            int j = 0;
            for (; j < resultLength; j++) {
                if (j + 6 < resultLength && result.substring(j, j + 6).equals("CIsin=")) {
                    j += 7;
                    break;
                }
            }
            temp = "";
            for (; j < resultLength; j++) {
                if (result.charAt(j) == '\'') {
                    break;
                }
                temp += result.charAt(j);
            }
            if(!symbolCode2.contains(temp) && !nickname.equals("") && !temp.equals("")){
                System.out.print(nickname + " ");
                symbolNickName2.add(nickname);
                symbolCode2.add(temp);
            }
        }
        FileWriter writer = new FileWriter("cisin");
        for (int i = 0; i < symbolNickName2.size(); i++) {
            writer.write(symbolNickName2.get(i) + " " + symbolCode2.get(i) + "\n");
        }
        writer.close();
        symbolNickName2.clear();
        symbolCode2.clear();

    }
    public void checkShareholders(boolean sendPublic) throws IOException, InterruptedException, TelegramApiException{
        System.out.println("T1");
        marketWatchInit(false, true);
        initSomeArrayList(true);
        System.out.println("init done.");
        shareholders.clear();

        FileWriter writer = new FileWriter("exVolumes2");
        Scanner sc = new Scanner(new FileInputStream("exVolumes"));
        while (sc.hasNextLine()) {
            writer.write(sc.nextLine() + "\n");
        }
        writer.close();
        sc.close();

        symbolNickNameY.clear();
        exVolumeY.clear();
        sc = new Scanner(new FileInputStream("exVolumes"));
        while (sc.hasNext()) {
            symbolNickNameY.add(sc.next());
            if(sc.hasNext())
                exVolumeY.add(sc.nextLong());
            else if(symbolNickNameY.size() > 0)
                symbolNickNameY.remove(symbolNickNameY.size() - 1);
        }
        sc.close();

        System.out.println("T2");
        sc = new Scanner(new FileInputStream("shareholders"));
        Shareholder tempShareholder;
        Share tempShare;
        String tempStr;
        while (sc.hasNext()) {
            tempShareholder = new Shareholder();
            tempShareholder.id = sc.nextLine();
            tempShareholder.name = sc.nextLine();
            while (true){
                tempStr = sc.next();
                if(tempStr.equals("***"))
                    break;
                tempShare = new Share();
                tempShare.shareName = tempStr;
                tempShare.sharesNumber = Long.valueOf(sc.next());
                tempShare.percent = Float.valueOf(sc.next());
                tempShare.enterDate = sc.next();
                if(isSaham(tempStr))
                    tempShareholder.currentShares.add(tempShare);
            }
            while (true){
                tempStr = sc.next();
                if(tempStr.equals("***"))
                    break;
                tempShare = new Share();
                tempShare.shareName = tempStr;
                tempShare.sharesNumber = Long.valueOf(sc.next());
                tempShare.percent = Float.valueOf(sc.next());
                tempShare.enterDate = sc.next();
                tempShare.exitDate = sc.next();
                if(isSaham(tempStr))
                    tempShareholder.prevShares.add(tempShare);
            }
            sc.nextLine();
//            if(!tempShareholder.name.substring(0,3).equals("BFM") && !tempShareholder.name.substring(0,3).equals("IFM") && !tempShareholder.name.substring(0,3).equals("PRX"))
            if(tempShareholder.currentShares.size() > 0 || tempShareholder.prevShares.size() > 0)
                shareholders.add(tempShareholder);
        }
        sc.close();
        System.out.println("T3");
        shareholdersFull.clear();
        sc = new Scanner(new FileInputStream("shareholdersFull"));
        while (sc.hasNext()) {
            Shareholder2 tempShareholder2 = new Shareholder2();
            tempShareholder2.id = sc.nextLine();
            tempShareholder2.name = sc.nextLine();
            while (sc.hasNext()) {
                Share2 tempShare2 = new Share2();
                tempShare2.enterOrExit = sc.next();
                if(tempShare2.enterOrExit.equals("*"))
                    break;
                tempShare2.shareName = sc.next();
                tempShare2.date = sc.next();
                tempShare2.pnl1 = sc.nextFloat();
                tempShare2.pnl2 = sc.nextFloat();
                tempShare2.pnl3 = sc.nextFloat();
                tempShareholder2.prevShares.add(tempShare2);
            }
            sc.nextLine();
            shareholdersFull.add(tempShareholder2);
        }
        System.out.println("T4");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int today = calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);
        today = Integer.valueOf(changeGregorianToPersian(String.valueOf(today)));
        int fourMonthsAgo = (today % 10000 >= 500) ? (today - 400):(today - 9200);
        
        ArrayList<String> symbolNickName2 = new ArrayList<>();
        ArrayList<String> symbolCode2 = new ArrayList<>();
        ArrayList<Boolean> symbolDone2 = new ArrayList<>();
        System.out.println("T5");
        findCIsin();
        System.out.println("T6");
        String toSendPublicAll = "";
        sc = new Scanner(new FileInputStream("cisin"));
        while (sc.hasNext()) {
            tempStr = sc.next();
            boolean isHaghTaghaddom = tempStr.length() > 0 && tempStr.charAt(tempStr.length() - 1) == 'ح';
            if (!tempStr.equals("") && !isHaghTaghaddom)
                symbolNickName2.add(tempStr);
            if(sc.hasNext()){
                tempStr = sc.next();
                if (!isHaghTaghaddom) {
                    symbolCode2.add(tempStr);
                    symbolDone2.add(false);
                }
            }
        }
        sc.close();

        FileWriter writer3 = new FileWriter("shhInSymbols");
        int notDone = -1, lengthLT500 = -1;
        int[] cdncount = new int[11];
        String otherId = "", increasedCap = "", noShareholder = "";
        int symbolsNumber = symbolCode2.size();
        notDone = 0;
        lengthLT500 = 0;
        for (int i = 0; i < 11; i++)
            cdncount[i] = 0;
        System.out.println("T7 " + symbolsNumber);
        String toSendMoradi = "";
        for (int i = 0; i < symbolsNumber; i++) {
            if(symbolDone2.get(i))
                continue;
            System.out.print(i + " ");
            String result = "", temp, cdnType = "";
            int resultLength = 0;
            int x = 0;
            for (; x < 5; x++) {
                result = httpTask2(5, urlShareholders + symbolCode2.get(i), 200, 3);
                resultLength = result.length();
                writer = new FileWriter("sh/" + symbolNickName2.get(i));
                writer.write(result);
                writer.close();
                cdnType = "";
                if (resultLength > 500 && (result.contains("cdn7") || result.contains("cdn8") || result.contains("cdn9"))) {
                    if (result.contains("cdn7")) {
                        cdncount[7]++;
                        cdnType = "cdn7";
                    } else if (result.contains("cdn8")) {
                        cdncount[8]++;
                        cdnType = "cdn8";
                    } else if (result.contains("cdn9")) {
                        cdncount[9]++;
                        cdnType = "cdn9";
                    }
                } else {
                    for (int j = 0; j < shareholders.size(); j++) {
                        for (int k = 0; k < shareholders.get(j).currentShares.size(); k++) {
                            if (shareholders.get(j).currentShares.get(k).shareName.equals(symbolNickName2.get(i))) {
                                shareholders.get(j).currentShares.get(k).todayExist = true;
                            }
                        }
                    }
                    for (int j = 0; j < 11; j++) {
                        if (j != 7 && j != 8 && j != 9 && result.contains("cdn" + j)) {
                            cdncount[j]++;
                            cdnType = "cdn" + j;
                            break;
                        }
                    }
                    notDone++;
                    if (resultLength < 500) {
                        lengthLT500++;
                        continue;
                    }
                }
                if(result.contains("ShowShareHolder") || symbolNickName2.get(i).equals("انرژی3"))
                    break;
                else
                    Thread.sleep(5000);
            }
            if(x == 5){
                noShareholder += symbolNickName2.get(i) + " ";
                for (int j = 0; j < shareholders.size(); j++) {
                    for (int k = 0; k < shareholders.get(j).currentShares.size(); k++) {
                        if (shareholders.get(j).currentShares.get(k).shareName.equals(symbolNickName2.get(i))) {
                            shareholders.get(j).currentShares.get(k).todayExist = true;
                        }
                    }
                }
                continue;
            }
            double marketCap = 0;
            int symbolIndex2 = symbolNickName.indexOf(symbolNickName2.get(i));
            if(symbolIndex2 >= 0)
                marketCap = (double)totalVolume.get(symbolIndex2) * (double)closingPrice2.get(symbolIndex2) / 10000000000000D;
            writer3.write("*" + symbolNickName2.get(i) + "\n" + String.format("%.2f\n", (float)marketCap));


            long changeSum = 0, shareSum = 0;
            for (int j = 0; j < resultLength; j++) {
                Shareholder shareholder = new Shareholder();
                Share share = new Share();
                boolean hasNextShareholder = false;
                for (; j < resultLength; j++) {
                    if (j + 15 < resultLength && result.substring(j, j + 15).equals("ShowShareHolder")) {
                        hasNextShareholder = true;
                        share.shareName = symbolNickName2.get(i);
                        j += 17;
                        break;
                    }
                }
                temp = "";
                for (; j < resultLength; j++) {
                    if (result.charAt(j) == ',') {
                        break;
                    }
                    temp += result.charAt(j);
                }
                if(temp.equals("69489") || temp.equals("69490"))
                    shareholder.id = "69488";
                else if(temp.equals("69818"))
                    shareholder.id = "69795";
                else if(temp.equals("69793"))
                    shareholder.id = "69816";
                else if(temp.equals("69803"))
                    shareholder.id = "69806";
                else if(temp.equals("69843"))
                    shareholder.id = "69846";
                else if(temp.equals("69845"))
                    shareholder.id = "69842";
                else if(temp.equals("69489"))
                    shareholder.id = "69490";
                else if(temp.equals("69498"))
                    shareholder.id = "69495";
                else if(temp.equals("70164"))
                    shareholder.id = "69958";
                else if(temp.equals("70227"))
                    shareholder.id = "70226";
                else if(temp.equals("70768"))
                    shareholder.id = "70371";
                else if(temp.equals("70372"))
                    shareholder.id = "70371";
                else if(temp.equals("70373"))
                    shareholder.id = "70371";
                else if(temp.equals("70374"))
                    shareholder.id = "70371";
                else if(temp.equals("70846"))
                    shareholder.id = "70260";
                else if(temp.equals("70348"))
                    shareholder.id = "70260";
                else if(temp.equals("70349"))
                    shareholder.id = "70260";
                else if(temp.equals("70122"))
                    shareholder.id = "70123";
                else if(temp.equals("70124"))
                    shareholder.id = "70123";
                else if(temp.equals("69799"))
                    shareholder.id = "69821";
                else if(temp.equals("69800"))
                    shareholder.id = "69822";
                else if(temp.equals("69801"))
                    shareholder.id = "69823";
                else if(temp.equals("69797"))
                    shareholder.id = "69811";
                else if(temp.equals("69798"))
                    shareholder.id = "69820";
                else if(temp.equals("69819"))
                    shareholder.id = "69811";
                else if(temp.equals("70651"))
                    shareholder.id = "70650";
                else if(temp.equals("70652"))
                    shareholder.id = "70650";
                else if(temp.equals("70653"))
                    shareholder.id = "70650";
                else if(temp.equals("70687"))
                    shareholder.id = "70684";
                else if(temp.equals("69497"))
                    shareholder.id = "69494";
                else if(temp.equals("70686"))
                    shareholder.id = "70761";
                else if(temp.equals("70067"))
                    shareholder.id = "70761";
                else if(temp.equals("70974"))
                    shareholder.id = "70975";
                else
                    shareholder.id = temp;
                for (; j < resultLength; j++) {
                    if (j + 4 < resultLength && result.substring(j, j + 4).equals("<td>")) {
                        j += 4;
                        break;
                    }
                }
                temp = "";
                for (; j < resultLength; j++) {
                    if (result.charAt(j) == '<') {
                        j += 5;
                        break;
                    }
                    temp += result.charAt(j);
                }
                shareholder.name = temp;
                for (; j < resultLength; j++) {
                    if (j + 4 < resultLength && result.substring(j, j + 4).equals("<td>")) {
                        j += 4;
                        break;
                    }
                }
                boolean hasTitleTag = false;
                if (j + 4 < resultLength && result.substring(j, j + 4).equals("<div"))
                    hasTitleTag = true;
                if (hasTitleTag) {
                    for (; j < resultLength; j++) {
                        if (j + 6 < resultLength && result.substring(j, j + 6).equals("title=")) {
                            j += 7;
                            break;
                        }
                    }
                }
                temp = "";
                for (; j < resultLength; j++) {
                    if (hasTitleTag && result.charAt(j) == '\"' || !hasTitleTag && result.charAt(j) == '<') {
                        break;
                    }

                    if (result.charAt(j) >= '0' && result.charAt(j) <= '9' || result.charAt(j) == ',') {
                        if (result.charAt(j) != ',')
                            temp += result.charAt(j);
                    } else {
                        temp = "";
                        break;
                    }
                }
                temp = (temp.equals("")) ? "0" : temp;
                share.sharesNumber = Long.valueOf(temp);
                for (; j < resultLength; j++) {
                    if (j + 4 < resultLength && result.substring(j, j + 4).equals("<td>")) {
                        j += 4;
                        break;
                    }
                }
                double[] tempDoubleArray = stringNextDouble(result, '<', j);
                j = (int)tempDoubleArray[1] + 4;
                share.percent = (float) tempDoubleArray[0];
                for (; j < resultLength; j++) {
                    if (j + 4 < resultLength && result.substring(j, j + 4).equals("<td>")) {
                        j += 4;
                        break;
                    }
                }
                hasTitleTag = false;
                if (j + 4 < resultLength && result.substring(j, j + 4).equals("<div"))
                    hasTitleTag = true;
                if (hasTitleTag) {
                    for (; j < resultLength; j++) {
                        if (j + 6 < resultLength && result.substring(j, j + 6).equals("title=")) {
                            j += 7;
                            break;
                        }
                    }
                }
                temp = "";
                for (; j < resultLength; j++) {
                    if (hasTitleTag && result.charAt(j) == '\"' || !hasTitleTag && result.charAt(j) == '<') {
                        break;
                    }
                    if (result.charAt(j) >= '0' && result.charAt(j) <= '9' || result.charAt(j) == ',' || result.charAt(j) == '-') {
                        if (result.charAt(j) != ',')
                            temp += result.charAt(j);
                    } else {
                        temp = "";
                        break;
                    }
                }
                temp = (temp.equals("")) ? "0" : temp;
                share.change = Long.valueOf(temp);
                shareSum += share.sharesNumber;
                if (share.change > 0) {
                    changeSum += share.change;
                    for (; j < resultLength; j++) {
                        if (j + 7 < resultLength && result.substring(j, j + 7).equals("ArrowUp")) {
                            share.positiveChange = true;
                            j += 7;
                            break;
                        }
                    }

                } else {
                    for (; j < resultLength; j++) {
                        if (j + 5 < resultLength && result.substring(j, j + 5).equals("</tr>")) {
                            j += 6;
                            break;
                        }
                    }
                }
                if(hasNextShareholder){
                    float shareValue = 0;
                    int index2 = symbolNickName.indexOf(share.shareName);
                    if(index2 >= 0)
                        shareValue = (float) share.sharesNumber * (float)closingPrice2.get(index2) / 10000000000F;
                    writer3.write(shareholder.name + "\n" +
                            String.format("%.1f\n", (float)share.sharesNumber / 1000000F) +
                            String.format("%.1f\n", shareValue) +
                            share.percent + "%\n");
                }
                if(!cdnType.equals("cdn7") && !cdnType.equals("cdn8")  && !cdnType.equals("cdn9") && (shareholder.name.equals("شخص حقيقي")
                        || shareholder.name.equals("سبد شخص حقیقی") || shareholder.name.equals("شخص حقیقی خارجی"))) {
                    int shareholdersSize = shareholders.size();
                    int k1;
                    boolean foundOtherIdSameNumber = false;
                    boolean foundSameIdSameNumber = false;
                    boolean foundSameId = false;
                    for (k1 = 0; k1 < shareholdersSize; k1++) {
                        tempShareholder = shareholders.get(k1);
                        if(tempShareholder.id.equals(shareholder.id))
                            foundSameId = true;
                        for (int l = 0; l < tempShareholder.currentShares.size(); l++) {
                            String shareName1 = tempShareholder.currentShares.get(l).shareName;
                            long sharesNumber1 = tempShareholder.currentShares.get(l).sharesNumber;
                            if (tempShareholder.name.equals(shareholder.name) && shareName1.equals(share.shareName)
                                    && (share.positiveChange && sharesNumber1 == (share.sharesNumber - share.change)
                                    || !share.positiveChange && sharesNumber1 == (share.sharesNumber + share.change))) {
                                if(tempShareholder.id.equals(shareholder.id))
                                    foundSameIdSameNumber = true;
                                else
                                    foundOtherIdSameNumber = true;
                            }
                        }


//                            if (tempShareholder.id.equals(shareholder.id) && tempShareholder.name.equals(shareholder.name)
//                                    && !tempShareholder.name.equals("شخص حقيقي") && !tempShareholder.name.equals("سبد شخص حقیقی")){
//                                cdnIsOK = true;
//                                break;
//                            }
//                            else if (tempShareholder.id.equals(shareholder.id) && !tempShareholder.name.equals(shareholder.name)) {
//                                otherId += "1:" + cdnType + ";" + shareholder.id + ";" + shareholder.name
//                                        + ";" + share.shareName + ";" + share.sharesNumber + "\n";
//                                break;
//                            }
//                            else if (tempShareholder.id.equals(shareholder.id) && tempShareholder.name.equals(shareholder.name)
//                                    && (tempShareholder.name.equals("شخص حقيقي") || tempShareholder.name.equals("سبد شخص حقیقی"))){
//                                boolean identicalShareholder = false;
//                                for (int k2 = 0; k2 < shareholdersSize; k2++){
//                                    if(k1 == k2)
//                                        continue;
//                                    int sameShares = 0;
//                                    if(shareholders.get(k2).name.equals(tempShareholder.name)
//                                            && shareholders.get(k2).currentShares.size() == tempShareholder.currentShares.size()){
//                                        for (int l = 0; l < shareholders.get(k2).currentShares.size(); l++){
//                                            String shareName1 = shareholders.get(k2).currentShares.get(l).shareName;
//                                            long sharesNumber1 = shareholders.get(k2).currentShares.get(l).sharesNumber;
//                                            String enterDate1 = shareholders.get(k2).currentShares.get(l).enterDate;
//                                            for (int m = 0; m < tempShareholder.currentShares.size(); m++){
//                                                String shareName2 = tempShareholder.currentShares.get(m).shareName;
//                                                long sharesNumber2 = tempShareholder.currentShares.get(m).sharesNumber;
//                                                long sharesChange2 = tempShareholder.currentShares.get(m).change;
//                                                boolean positiveChange2 = tempShareholder.currentShares.get(m).positiveChange;
//                                                String enterDate2 = tempShareholder.currentShares.get(m).enterDate;
//                                                if(shareName1.equals(shareName2) && enterDate1.equals(enterDate2)
//                                                        && (positiveChange2 && sharesNumber1 == (sharesNumber2 - sharesChange2)
//                                                        || !positiveChange2 && sharesNumber1 == (sharesNumber2 + sharesChange2))){
//                                                    sameShares++;
//                                                    break;
//                                                }
//                                            }
//                                        }
//                                    }
//                                    if(sameShares > 0 && sameShares == shareholders.get(k2).currentShares.size()){
//                                        identicalShareholder = true;
//                                        break;
//                                    }
//                                }
//                                if(identicalShareholder)
//                                    cdnIsOK = true;
//                                else
//                                    otherId += "2:" + cdnType + ";" + shareholder.id + ";" + shareholder.name + ";"
//                                            + share.shareName + ";" + share.sharesNumber + "\n";
//                                break;
//                            }
                    }
                    if(!foundSameId){
                        otherId += "1:" + cdnType + ";" + shareholder.id + ";" + shareholder.name
                                + ";" + share.shareName + ";" + share.sharesNumber + "\n";
                    }
                }
                if (hasNextShareholder && (cdnType.equals("cdn7") || cdnType.equals("cdn8") || cdnType.equals("cdn9"))) {
//                    System.out.println("\nVV:" + shareholder.id + " " + shareholder.name +  " " + share.shareName
//                            +  " " + share.sharesNumber +  " " + share.change+  " " + share.percent);
                    boolean shareholderExist = false;
                    int shareholdersSize = shareholders.size();
                    int k = 0;
                    boolean realPerson = shareholder.name.equals("شخص حقيقي") || shareholder.name.equals("سبد شخص حقیقی")
                            || shareholder.name.equals("شخص حقیقی خارجی");
                    for (; k < shareholdersSize; k++) {
                        if (shareholders.get(k).name.equals(shareholder.name) &&
                                (shareholders.get(k).id.equals(shareholder.id) || !realPerson)) {
                            if(!shareholders.get(k).id.equals(shareholder.id)) {
                                otherId += shareholders.get(k).id + ";" + shareholder.id + ";"
                                        + shareholder.name + ";" + share.shareName + ";" + share.sharesNumber + "\n";
                                shareholders.get(k).id = shareholder.id;
                            }
                            shareholderExist = true;
                            break;
                        }
                    }
                    boolean shareExist = false;
                    boolean isBazargardan = shareholder.name.substring(0, 3).equals("BFM") ||
                            shareholder.name.substring(0, 3).equals("IFM") || shareholder.name.substring(0, 3).equals("PRX");
                    if (shareholderExist) {
                        int currentSharesSize = shareholders.get(k).currentShares.size();
                        for (int l = 0; l < currentSharesSize; l++) {
                            if (shareholders.get(k).currentShares.get(l).shareName.equals(share.shareName)) {
                                shareholders.get(k).currentShares.get(l).sharesNumber = share.sharesNumber;
                                shareholders.get(k).currentShares.get(l).percent = share.percent;
                                shareholders.get(k).currentShares.get(l).change = share.change;
                                shareholders.get(k).currentShares.get(l).positiveChange = share.positiveChange;
                                shareholders.get(k).currentShares.get(l).todayExist = true;
                                shareExist = true;
                                break;
                            }
                        }
                        tempShareholder = shareholders.get(k);
                        if (!shareExist) {
                            int shareExistDays = 0;
                            int symbolIndex = symbolNickName2.indexOf(share.shareName);
                            boolean isWorkDayBetweenVar = false, isLastWorkDayVar = false;
                            if (symbolIndex >= 0) {
                                String result2 = httpTask2(5,urlShareholderDetails + shareholder.id +
                                        urlShareholderDetailsPF + symbolCode2.get(symbolIndex), 10, 3);
                                long[] tempLongArray = stringNextLong(result2, ',', 0);
                                if (tempLongArray[0] == 0)
                                    share.enterDate = "00000000";
                                else
                                    share.enterDate = changeGregorianToPersian(String.valueOf(gregorianCalendarMinusOne((int)tempLongArray[0])));
                                String lastDay1 = "0", lastDay2 = "0", tempDay = "";
                                for (int l = 0; l < result2.length(); l++){
                                    if(result2.charAt(l) == ','){
                                        lastDay2 = lastDay1;
                                        lastDay1 = tempDay;
                                        shareExistDays++;
                                    }
                                    if(result2.charAt(l) == '#')
                                        break;
                                    if(l > 0 && result2.charAt(l - 1) == ';')
                                        tempDay = "";
                                    tempDay += result2.charAt(l);
                                }
                                isWorkDayBetweenVar = isWorkDayBetween(Integer.valueOf(lastDay1), Integer.valueOf(lastDay2));
                                isLastWorkDayVar = isLastWorkDay((int)tempLongArray[0]);
                                if(shareExistDays > 1 && isWorkDayBetweenVar)
                                    share.enterDate = changeGregorianToPersian(String.valueOf(gregorianCalendarMinusOne(Integer.valueOf(lastDay1))));
                            }
                            share.todayExist = true;
                            tempShareholder.currentShares.add(share);
                            if (isSaham(share.shareName) && !isBazargardan &&
                                    (shareExistDays > 1 && isWorkDayBetweenVar || shareExistDays == 1 && isLastWorkDayVar)) {
                                toSendMoradi += "ورود;" + tempShareholder.id + ";" + tempShareholder.name + ";"
                                        + share.shareName + ";" + share.sharesNumber + ";" + share.percent + ";" + share.enterDate + "\n";
                                String toSendPublic = "#ورود_سهامدار " + greenCircle + "\n" ;
                                if(shareExistDays > 1 && isWorkDayBetweenVar)
                                    toSendPublic += "(ورود مجدد)" + "\n";
                                toSendPublic += "سهامدار: " + tempShareholder.name + "\n";
                                toSendPublic += "#" + share.shareName + " ";
                                toSendPublic += "درصد: " + share.percent + " ";
                                toSendPublic += "تاریخ ورود: " + formattedDate(share.enterDate) + "\n";
                                String toSendPrivate = toSendPublic;
                                int tempCnt = 0;
                                if (tempShareholder.name.equals("شخص حقيقي")) {
                                    for (int l = 0; l < tempShareholder.currentShares.size(); l++) {
                                        if (isSaham(tempShareholder.currentShares.get(l).shareName)) {
                                            if (tempCnt == 0)
                                                toSendPrivate += "\nسهام فعلی:\n";
                                            tempCnt++;
                                            toSendPrivate += "#" + tempShareholder.currentShares.get(l).shareName + " ";
                                            toSendPrivate += "درصد: " + tempShareholder.currentShares.get(l).percent + " ";
                                            if(!tempShareholder.currentShares.get(l).enterDate.equals("13800101"))
                                                toSendPrivate += "ورود: " + formattedDate(tempShareholder.currentShares.get(l).enterDate);
                                            toSendPrivate += "\n";
                                        }
                                    }
                                }
                                String prevSharesTemp = "";
//                                tempCnt = 0;
//                                for (int l = tempShareholder.prevShares.size() - 1; l >= 0; l--) {
//                                    if (!tempShareholder.name.equals("شخص حقيقي") && tempCnt == 7) {
//                                        prevSharesTemp += "و چند سهام دیگر\n";
//                                        break;
//                                    }
//                                    if (isSaham(tempShareholder.prevShares.get(l).shareName)) {
//                                        if (tempCnt == 0)
//                                            prevSharesTemp += "سهام قبلی:\n";
//                                        tempCnt++;
//                                        prevSharesTemp += "#" + tempShareholder.prevShares.get(l).shareName + " ";
//                                        if(!tempShareholder.prevShares.get(l).enterDate.equals("13800101"))
//                                            prevSharesTemp += "ورود: " + formattedDate(tempShareholder.prevShares.get(l).enterDate) + " ";
//                                        prevSharesTemp += "خروج: " + formattedDate(tempShareholder.prevShares.get(l).exitDate) + "\n";
//                                    }
//                                }
                                Shareholder2 shh2 = new Shareholder2();
                                boolean foundShh2 = false;
                                int shhfSize = shareholdersFull.size();
                                for (int l = 0; l < shhfSize; l++){
                                    if(tempShareholder.name.equals(shareholdersFull.get(l).name) &&
                                            (tempShareholder.id.equals(shareholdersFull.get(l).id) || !realPerson)){
                                        shh2 = shareholdersFull.get(l);
                                        foundShh2 = true;
                                        break;
                                    }
                                }
                                float pnlTemp = 0, pnlCnt = 0;
                                int pnlAvg = 0;
                                if(foundShh2) {
                                    boolean titleAdded = false;
                                    for (int l = 0; l < shh2.prevShares.size(); l++) {
                                        if(shh2.prevShares.get(l).enterOrExit.equals("ورود") && shh2.prevShares.get(l).pnl3 > 0){
                                            pnlTemp += shh2.prevShares.get(l).pnl3;
                                            pnlCnt++;
                                        }
                                    }
                                    pnlAvg = (int)(100F  * pnlTemp / pnlCnt - 100F);
                                    for (int l = shh2.prevShares.size() - 1; l >= 0; l--) {
                                        if (isSaham(shh2.prevShares.get(l).shareName)) {
                                            if (!titleAdded) {
                                                prevSharesTemp += "\nسهام قبلی:\nمیانگین بازدهی یک ماه: ";
                                                prevSharesTemp += pnlAvg + "% از ";
                                                prevSharesTemp += (int)pnlCnt + " سهم\n\n";
                                                titleAdded = true;
                                            }
                                            prevSharesTemp += "#" + shh2.prevShares.get(l).shareName + " ";
                                            prevSharesTemp += shh2.prevShares.get(l).enterOrExit + ": ";
                                            prevSharesTemp += formattedDate(shh2.prevShares.get(l).date) + " ";
                                            if(shh2.prevShares.get(l).enterOrExit.equals("ورود")) {
                                                if(shh2.prevShares.get(l).pnl1 > 0F || shh2.prevShares.get(l).pnl2 > 0F || shh2.prevShares.get(l).pnl3 > 0F)
                                                    prevSharesTemp += "\n";
                                                if(shh2.prevShares.get(l).pnl1 > 0F){
                                                    prevSharesTemp += "یک هفته: " + (int) ((shh2.prevShares.get(l).pnl1 - 1F) * 100F) + "%";
                                                    if(shh2.prevShares.get(l).pnl1 > 1F)
                                                        prevSharesTemp += greenCircle + " ";
                                                    else
                                                        prevSharesTemp += redCircle + " ";
                                                }
                                                if(shh2.prevShares.get(l).pnl2 > 0F){
                                                    prevSharesTemp += "دو هفته: " + (int) ((shh2.prevShares.get(l).pnl2 - 1F) * 100F) + "%";
                                                    if(shh2.prevShares.get(l).pnl2 > 1F)
                                                        prevSharesTemp += greenCircle + " ";
                                                    else
                                                        prevSharesTemp += redCircle + " ";
                                                }
                                                if(shh2.prevShares.get(l).pnl3 > 0F){
                                                    prevSharesTemp += "یک ماه: " + (int) ((shh2.prevShares.get(l).pnl3 - 1F) * 100F) + "%";
                                                    if(shh2.prevShares.get(l).pnl3 > 1F)
                                                        prevSharesTemp += greenCircle;
                                                    else
                                                        prevSharesTemp += redCircle;
                                                }
                                            }
                                            prevSharesTemp += "\n";
                                        }
                                    }
                                }
                                toSendPrivate += prevSharesTemp;
//                                if(!tempShareholder.name.equals("شخص حقيقي"))
//                                    toSendPublic += prevSharesTemp;
                                sendLongMessage(toSendPrivate, ID.PRIVATE_SHAREHOLDERS, true);
                                toSendPublicAll += toSendPublic + "\n";
                            }
                        }
                    }
                    else {
                        int shareExistDays = 0;
                        boolean isWorkDayBetweenVar = false, isLastWorkDayVar = false;
                        int symbolIndex = symbolNickName2.indexOf(share.shareName);
                        if (symbolIndex >= 0) {
                            String result2 = httpTask2(5,urlShareholderDetails + shareholder.id +
                                    urlShareholderDetailsPF + symbolCode2.get(symbolIndex), 10, 3);
                            long[] tempLongArray = stringNextLong(result2, ',', 0);
                            if (tempLongArray[0] == 0)
                                share.enterDate = "00000000";
                            else
                                share.enterDate = changeGregorianToPersian(String.valueOf(gregorianCalendarMinusOne((int)tempLongArray[0])));
                            String lastDay1 = "0", lastDay2 = "0", tempDay = "";
                            for (int l = 0; l < result2.length(); l++){
                                if(result2.charAt(l) == ','){
                                    lastDay2 = lastDay1;
                                    lastDay1 = tempDay;
                                    shareExistDays++;
                                }
                                if(result2.charAt(l) == '#')
                                    break;
                                if(l > 0 && result2.charAt(l - 1) == ';')
                                    tempDay = "";
                                tempDay += result2.charAt(l);
                            }
                            isWorkDayBetweenVar = isWorkDayBetween(Integer.valueOf(lastDay1), Integer.valueOf(lastDay2));
                            isLastWorkDayVar = isLastWorkDay((int)tempLongArray[0]);
                            if(shareExistDays > 1 && isWorkDayBetweenVar)
                                share.enterDate = changeGregorianToPersian(String.valueOf(gregorianCalendarMinusOne(Integer.valueOf(lastDay1))));
                        }
                        share.todayExist = true;
                        shareholder.currentShares.add(share);
                        shareholders.add(shareholder);
                        if (isSaham(share.shareName) &&
                                !shareholder.name.substring(0, 3).equals("BFM") &&
                                !shareholder.name.substring(0, 3).equals("IFM") &&
                                !shareholder.name.substring(0, 3).equals("PRX") &&
                                (shareExistDays > 1 && isWorkDayBetweenVar || shareExistDays == 1 && isLastWorkDayVar)) {
                            toSendMoradi += "ورود;" + shareholder.id + ";" + shareholder.name + ";"
                                    + share.shareName + ";" + share.sharesNumber + ";" + share.percent + ";" + share.enterDate + "\n";
                            String toSend = "#ورود_سهامدار " + greenCircle + "\n";
                            if(shareExistDays > 1 && isWorkDayBetweenVar)
                                toSend += "(ورود مجدد)" + "\n";
                            toSend += "سهامدار: " + shareholder.name + "\n";
                            toSend += "#" + share.shareName + " ";
                            toSend += "درصد: " + share.percent + " ";
                            toSend += "تاریخ ورود: " + formattedDate(share.enterDate) + "\n";
                            Shareholder2 shh2 = new Shareholder2();
                            boolean foundShh2 = false;
                            int shhfSize = shareholdersFull.size();
                            String prevSharesTemp = "";
                            for (int l = 0; l < shhfSize; l++){
                                if(shareholder.name.equals(shareholdersFull.get(l).name) &&
                                        (shareholder.id.equals(shareholdersFull.get(l).id) || !realPerson)){
                                    shh2 = shareholdersFull.get(l);
                                    foundShh2 = true;
                                    break;
                                }
                            }
                            float pnlTemp = 0, pnlCnt = 0;
                            int pnlAvg = 0;
                            if(foundShh2) {
                                boolean titleAdded = false;
                                for (int l = 0; l < shh2.prevShares.size(); l++) {
                                    if(shh2.prevShares.get(l).enterOrExit.equals("ورود") && shh2.prevShares.get(l).pnl3 > 0){
                                        pnlTemp += shh2.prevShares.get(l).pnl3;
                                        pnlCnt++;
                                    }
                                }
                                pnlAvg = (int)(100F  * pnlTemp / pnlCnt - 100F);
                                for (int l = shh2.prevShares.size() - 1; l >= 0; l--) {
                                    if (isSaham(shh2.prevShares.get(l).shareName)) {
                                        if (!titleAdded) {
                                            prevSharesTemp += "\nسهام قبلی:\nمیانگین بازدهی یک ماه: ";
                                            prevSharesTemp += pnlAvg + "% از ";
                                            prevSharesTemp += (int)pnlCnt + " سهم\n\n";
                                            titleAdded = true;
                                        }
                                        prevSharesTemp += "#" + shh2.prevShares.get(l).shareName + " ";
                                        prevSharesTemp += shh2.prevShares.get(l).enterOrExit + ": ";
                                        prevSharesTemp += formattedDate(shh2.prevShares.get(l).date) + " ";
                                        if(shh2.prevShares.get(l).enterOrExit.equals("ورود")) {
                                            if(shh2.prevShares.get(l).pnl1 > 0F || shh2.prevShares.get(l).pnl2 > 0F || shh2.prevShares.get(l).pnl3 > 0F)
                                                prevSharesTemp += "\n";
                                            if(shh2.prevShares.get(l).pnl1 > 0F){
                                                prevSharesTemp += "یک هفته: " + (int) ((shh2.prevShares.get(l).pnl1 - 1F) * 100F) + "%";
                                                if(shh2.prevShares.get(l).pnl1 > 1F)
                                                    prevSharesTemp += greenCircle + " ";
                                                else
                                                    prevSharesTemp += redCircle + " ";
                                            }
                                            if(shh2.prevShares.get(l).pnl2 > 0F){
                                                prevSharesTemp += "دو هفته: " + (int) ((shh2.prevShares.get(l).pnl2 - 1F) * 100F) + "%";
                                                if(shh2.prevShares.get(l).pnl2 > 1F)
                                                    prevSharesTemp += greenCircle + " ";
                                                else
                                                    prevSharesTemp += redCircle + " ";
                                            }
                                            if(shh2.prevShares.get(l).pnl3 > 0F){
                                                prevSharesTemp += "یک ماه: " + (int) ((shh2.prevShares.get(l).pnl3 - 1F) * 100F) + "%";
                                                if(shh2.prevShares.get(l).pnl3 > 1F)
                                                    prevSharesTemp += greenCircle;
                                                else
                                                    prevSharesTemp += redCircle;
                                            }
                                        }
                                        prevSharesTemp += "\n";
                                    }
                                }
                            }
                            if(!isBazargardan || pnlAvg >= 60 && (int)pnlCnt >= 1 ||
                                    pnlAvg >= 50 && (int)pnlCnt >= 2 ||
                                    pnlAvg >= 40 && (int)pnlCnt >= 3 ||
                                    pnlAvg >= 30 && (int)pnlCnt >= 4) {
                                sendLongMessage(toSend + prevSharesTemp, ID.PRIVATE_SHAREHOLDERS, true);
                                toSendPublicAll += toSend + "\n";
                            }
                        }
                    }
                    if(!shareholderExist || !shareExist){
                        boolean shareholderExist2 = false;
                        int shareholdersFullSize = shareholdersFull.size();
                        k = 0;
                        for (; k < shareholdersFullSize; k++) {
                            if (shareholdersFull.get(k).name.equals(shareholder.name) &&
                                    (shareholdersFull.get(k).id.equals(shareholder.id) || !realPerson)) {
                                shareholderExist2 = true;
                                break;
                            }
                        }
                        Share2 tempShare2 = new Share2();
                        tempShare2.shareName = share.shareName;
                        tempShare2.enterOrExit = "ورود";
                        tempShare2.date = share.enterDate;
                        if(shareholderExist2){
                            shareholdersFull.get(k).prevShares.add(tempShare2);
                        }
                        else {
                            Shareholder2 tempShareholder2 = new Shareholder2();
                            tempShareholder2.id = shareholder.id;
                            tempShareholder2.name = shareholder.name;
                            tempShareholder2.prevShares.add(tempShare2);
                            shareholdersFull.add(tempShareholder2);
                        }
                    }
                }
            }
            if(((double)changeSum / (double)(shareSum - changeSum) >= 0.3))
                increasedCap += "#" + symbolNickName2.get(i) + " ";
            symbolDone2.set(i,true);
        }
        writer3.close();
        System.out.print("\nnot done: " + notDone + " lt500: " + lengthLT500 + " ");
        for (int i = 0; i < 11; i++)
            System.out.print("cdn" + i + ": " + cdncount[i] + " ");
        System.out.println("");
        if(increasedCap.length() > 0){
            SendMessage message = new SendMessage().setText("اضافه شدن سهام افزایش سرمایه\n" + increasedCap);
            customSendMessage(1, message, null, null, ID.PUBLIC_CHANNEL, true);
        }
        if(noShareholder.length() > 0){
            SendMessage message = new SendMessage().setText("بدون درصدی\n" + noShareholder);
            customSendMessage(1, message, null, null, ID.FARDIN, true);
        }
        if(notDone > 0){
            String notCheckedShareholders = "\nnot done: " + notDone + " lt500: " + lengthLT500 + " ";
            for (int i = 0; i < 11; i++)
                notCheckedShareholders += "cdn" + i + ": " + cdncount[i] + " ";
//            notCheckedShareholders += "\n#سهام_چک_نشده ";
//            for (int i = 0; i < symbolsNumber; i++){
//                if(!symbolDone2.get(i))
//                    notCheckedShareholders += symbolNickName2.get(i) + " ";
//            }
            sendLongMessage(notCheckedShareholders, ID.FARDIN, true);
        }
        sendLongMessage(otherId, ID.FARDIN, true);

        System.out.println("T8");
        int shareholdersSize = shareholders.size();
        for (int i = 0; i < shareholdersSize; i++){
            for (int j = 0; j < shareholders.get(i).currentShares.size(); j++){
                if(!symbolNickName2.contains(shareholders.get(i).currentShares.get(j).shareName)){
                    String result3;
                    String temp3;
                    result3 = httpTask2(5,urlTablo + getSymbolIDByNickName(shareholders.get(i).currentShares.get(j).shareName), 500, 3);
                    int resultLength3 = result3.length();
                    int k = 0;
                    for (; k < resultLength3; k++) {
                        if (k + 6 < resultLength3 && result3.substring(k, k + 6).equals("CIsin=")) {
                            k += 7;
                            break;
                        }
                    }
                    temp3 = "";
                    for (; k < resultLength3; k++) {
                        if (result3.charAt(k) == '\'') {
                            break;
                        }
                        temp3 += result3.charAt(k);
                    }
                    String toSend = shareholders.get(i).currentShares.get(j).shareName + " " + temp3;
                    SendMessage message = new SendMessage().setText(toSend);
                    customSendMessage(1, message, null, null, ID.FARDIN, true);
                }
                if(!shareholders.get(i).currentShares.get(j).todayExist
                        && symbolNickName2.contains(shareholders.get(i).currentShares.get(j).shareName)
                        && isSaham(shareholders.get(i).currentShares.get(j).shareName)){
                    boolean identicalShareholder = false;
                    boolean realPerson = shareholders.get(i).name.equals("شخص حقيقي") ||
                            shareholders.get(i).name.equals("سبد شخص حقیقی") || shareholders.get(i).name.equals("شخص حقیقی خارجی");
                    /*
                    if(realPerson){
                        for (int k = 0; k < shareholdersSize; k++){
                            if(i == k)
                                continue;
                            int sameShares = 0;
                            if(shareholders.get(k).name.equals(shareholders.get(i).name)
                                && shareholders.get(k).currentShares.size() == shareholders.get(i).currentShares.size()){
                                for (int l = 0; l < shareholders.get(k).currentShares.size(); l++){
                                    for (int m = 0; m < shareholders.get(i).currentShares.size(); m++){
                                        String shareName1 = shareholders.get(k).currentShares.get(l).shareName;
                                        String shareName2 = shareholders.get(i).currentShares.get(m).shareName;
                                        long sharesNumber1 = shareholders.get(k).currentShares.get(l).sharesNumber;
                                        long sharesNumber2 = shareholders.get(i).currentShares.get(m).sharesNumber;
                                        long sharesChange2 = shareholders.get(i).currentShares.get(m).change;
                                        boolean positiveChange2 = shareholders.get(i).currentShares.get(m).positiveChange;
                                        String enterDate1 = shareholders.get(k).currentShares.get(l).enterDate;
                                        String enterDate2 = shareholders.get(i).currentShares.get(m).enterDate;
                                        if(shareName1.equals(shareName2) && enterDate1.equals(enterDate2)
                                                && (positiveChange2 && sharesNumber1 == sharesNumber2 - sharesChange2
                                                    || !positiveChange2 && sharesNumber1 == sharesNumber2 + sharesChange2)){
                                            sameShares++;
                                            break;
                                        }
                                    }
                                }
                            }
                            if(sameShares > 0 && sameShares == shareholders.get(k).currentShares.size()
                                    || sameShares > 1 && sameShares == shareholders.get(k).currentShares.size() - 1){
                                identicalShareholder = true;
//                                String toSend = "YY " + shareholders.get(i).id + " " + shareholders.get(i).name +  " " + shareholders.get(i).currentShares.get(j).shareName;
//                                SendMessage message = new SendMessage().setText(toSend).setChatId(ID.FARDIN);
//                                execute(message);
//                                if(messageCnt == 18)
//                                {
//                                    Thread.sleep(60000);
//                                    messageCnt = 0;
//                                }
//                                messageCnt++;
                                break;
                            }
                        }
                    }
                    if(identicalShareholder)
                        continue;
                    */
                    String shareName = shareholders.get(i).currentShares.get(j).shareName;
                    String enterDate = shareholders.get(i).currentShares.get(j).enterDate;
                    String exitDate = changeGregorianToPersian(String.valueOf(lastWorkDayGreg()));
                    toSendMoradi += "خروج;" + shareholders.get(i).id + ";" + shareholders.get(i).name + ";" + shareName + ";" + exitDate + "\n";
                    String toSendPublic = "#خروج_سهامدار " + redCircle + "\n" + "سهامدار: " + shareholders.get(i).name + "\n";
                    toSendPublic += "#" + shareName + " ";
                    if(!enterDate.equals("13800101"))
                        toSendPublic += "ورود: " + formattedDate(enterDate) + " ";
                    toSendPublic += "خروج: " + formattedDate(exitDate) + "\n";
                    String toSendPrivate = toSendPublic;
                    int tempCnt = 0;
//                        if (shareholders.get(i).currentShares.size() > 0)
//                            toSend += "سهام فعلی:\n";
                    String currentSharesTemp = "", prevSharesTemp = "";
                    for (int l = 0; l < shareholders.get(i).currentShares.size(); l++) {
                        if (!shareholders.get(i).name.equals("شخص حقيقي") && tempCnt == 7) {
                            currentSharesTemp += "و چند سهام دیگر\n";
                            break;
                        }
                        if(isSaham(shareholders.get(i).currentShares.get(l).shareName) && !shareName.equals(shareholders.get(i).currentShares.get(l).shareName)) {
                            if(tempCnt == 0)
                                currentSharesTemp += "\nسهام فعلی:\n";
                            tempCnt++;
                            currentSharesTemp += "#" + shareholders.get(i).currentShares.get(l).shareName + " ";
                            currentSharesTemp += "درصد: " + shareholders.get(i).currentShares.get(l).percent + " ";
                            if(!shareholders.get(i).currentShares.get(l).enterDate.equals("13800101"))
                                currentSharesTemp += "ورود: " + formattedDate(shareholders.get(i).currentShares.get(l).enterDate);
                            currentSharesTemp += "\n";
                        }
                    }
//                    tempCnt = 0;
//                    for (int l = shareholders.get(i).prevShares.size() - 1; l >= 0; l--) {
//                        if (!shareholders.get(i).name.equals("شخص حقيقي") && tempCnt == 7) {
//                            prevSharesTemp += "و چند سهام دیگر\n";
//                            break;
//                        }
//                        if(isSaham(shareholders.get(i).prevShares.get(l).shareName)
//                                && !(shareName.equals(shareholders.get(i).prevShares.get(l).shareName)
//                                    && exitDate.equals(shareholders.get(i).prevShares.get(l).exitDate))) {
//                            if (tempCnt == 0)
//                                prevSharesTemp += "سهام قبلی:\n";
//                            tempCnt++;
//                            prevSharesTemp += "#" + shareholders.get(i).prevShares.get(l).shareName + " ";
//                            if(!shareholders.get(i).prevShares.get(l).enterDate.equals("13800101"))
//                                prevSharesTemp += "ورود: " + formattedDate(shareholders.get(i).prevShares.get(l).enterDate) + " ";
//                            prevSharesTemp += "خروج: " + formattedDate(shareholders.get(i).prevShares.get(l).exitDate) + "\n";
//                        }
//                    }
                    Shareholder2 shh2 = new Shareholder2();
                    boolean foundShh2 = false;
                    int shhfSize = shareholdersFull.size();
                    for (int l = 0; l < shhfSize; l++){
                        if(shareholders.get(i).id.equals(shareholdersFull.get(l).id) &&
                                shareholders.get(i).name.equals(shareholdersFull.get(l).name)){
                            shh2 = shareholdersFull.get(l);
                            foundShh2 = true;
                            break;
                        }
                    }
                    if(foundShh2) {
                        boolean titleAdded = false;
                        for (int l = shh2.prevShares.size() - 1; l >= 0; l--) {
                            if (isSaham(shh2.prevShares.get(l).shareName)) {
                                if (!titleAdded) {
                                    prevSharesTemp += "\nسهام قبلی:\n";
                                    titleAdded = true;
                                }
                                prevSharesTemp += "#" + shh2.prevShares.get(l).shareName + " ";
                                prevSharesTemp += shh2.prevShares.get(l).enterOrExit + ": ";
                                prevSharesTemp += formattedDate(shh2.prevShares.get(l).date) + " ";
                                if(shh2.prevShares.get(l).enterOrExit.equals("خروج")) {
                                    if(shh2.prevShares.get(l).pnl1 > 0F || shh2.prevShares.get(l).pnl2 > 0F || shh2.prevShares.get(l).pnl3 > 0F)
                                        prevSharesTemp += "\n";
                                    if(shh2.prevShares.get(l).pnl1 > 0F){
                                        prevSharesTemp += "یک هفته: " + (int) ((shh2.prevShares.get(l).pnl1 - 1F) * 100F) + "%";
                                        if(shh2.prevShares.get(l).pnl1 > 1F)
                                            prevSharesTemp += greenCircle + " ";
                                        else
                                            prevSharesTemp += redCircle + " ";
                                    }
                                    if(shh2.prevShares.get(l).pnl2 > 0F){
                                        prevSharesTemp += "دو هفته: " + (int) ((shh2.prevShares.get(l).pnl2 - 1F) * 100F) + "%";
                                        if(shh2.prevShares.get(l).pnl2 > 1F)
                                            prevSharesTemp += greenCircle + " ";
                                        else
                                            prevSharesTemp += redCircle + " ";
                                    }
                                    if(shh2.prevShares.get(l).pnl3 > 0F){
                                        prevSharesTemp += "یک ماه: " + (int) ((shh2.prevShares.get(l).pnl3 - 1F) * 100F) + "%";
                                        if(shh2.prevShares.get(l).pnl3 > 1F)
                                            prevSharesTemp += greenCircle;
                                        else
                                            prevSharesTemp += redCircle;
                                    }
                                }
                                prevSharesTemp += "\n";
                            }
                        }
                    }
                    toSendPrivate += currentSharesTemp + prevSharesTemp;
                    if(!shareholders.get(i).name.equals("شخص حقيقي"))
                        toSendPublic += currentSharesTemp;

                    shareholders.get(i).currentShares.get(j).exitDate = changeGregorianToPersian(String.valueOf(lastWorkDayGreg()));
                    shareholders.get(i).prevShares.add(shareholders.get(i).currentShares.get(j));
                    shareholders.get(i).currentShares.remove(j);

                    boolean shareholderExist2 = false;
                    int shareholdersFullSize = shareholdersFull.size();
                    int k = 0;
                    for (; k < shareholdersFullSize; k++) {
                        if (shareholdersFull.get(k).name.equals(shareholders.get(i).name) &&
                                (shareholdersFull.get(k).id.equals(shareholders.get(i).id) || !realPerson)) {
                            shareholderExist2 = true;
                            break;
                        }
                    }
                    Share2 tempShare2 = new Share2();
                    tempShare2.shareName = shareName;
                    tempShare2.enterOrExit = "خروج";
                    tempShare2.date = exitDate;
                    if(shareholderExist2){
                        shareholdersFull.get(k).prevShares.add(tempShare2);
                    }
                    else {
                        Shareholder2 tempShareholder2 = new Shareholder2();
                        tempShareholder2.id = shareholders.get(i).id;
                        tempShareholder2.name = shareholders.get(i).name;
                        tempShareholder2.prevShares.add(tempShare2);
                        shareholdersFull.add(tempShareholder2);
                    }
                    if(!shareholders.get(i).name.substring(0, 3).equals("BFM") &&
                            !shareholders.get(i).name.substring(0, 3).equals("IFM") &&
                            !shareholders.get(i).name.substring(0, 3).equals("PRX")) {
                        sendLongMessage(toSendPrivate, ID.PRIVATE_SHAREHOLDERS, true);
                        toSendPublicAll += toSendPublic + "\n";
                    }
                }
            }
        }
//        if(sendPublic)
//            sendLongMessage(toSendPublicAll, ID.PUBLIC_CHANNEL, true);
        sendLongMessage(toSendPublicAll, ID.PRIVATE_SHAREHOLDERS, true);
        sendLongMessage(toSendMoradi, ID.ALI_MORADI, false);
        System.out.println("T9");
        writer = new FileWriter("shareholders");
        FileWriter writer2;// = new FileWriter("shareholders2", true);
        String buyList1 = "", buyList2 = "", buyList3 = "", sellList1 = "", sellList2 = "", sellList3 = "";
        long totalBuy1 = 0, totalSell1 = 0, totalBuy2 = 0, totalSell2 = 0, totalBuy3 = 0, totalSell3 = 0;
        String blockEx = "";
        for (int i = 0; i < shareholdersSize; i++){
            writer.write(shareholders.get(i).id + "\n");
            writer.write(shareholders.get(i).name + "\n");
            String shareholderName = shareholders.get(i).name;
            for (int j = 0; j < shareholders.get(i).currentShares.size(); j++){
                tempShare = shareholders.get(i).currentShares.get(j);
//                if(tempShare.shareName.charAt(tempShare.shareName.length() - 1) < '0'
//                    || tempShare.shareName.charAt(tempShare.shareName.length() - 1) > '9'
//                    || tempShare.shareName.equals("انرژی1") || tempShare.shareName.equals("انرژی2") || tempShare.shareName.equals("انرژی3")) {
                    int tempIndex = symbolNickName.indexOf(tempShare.shareName);
                    int tempIndexY = symbolNickNameY.indexOf(tempShare.shareName);
                    long shareChangeAbs = (tempShare.change < 0) ? (tempShare.change * -1):tempShare.change;
                    int pc = 0;
                    long tv = 0, marketCap = 0;
                    long changeThreshold = 0;
                    if(!blockEx.contains(tempShare.shareName + " ") && !increasedCap.contains(tempShare.shareName + " ") &&
                            tempIndexY >= 0 && shareChangeAbs > exVolumeY.get(tempIndexY)){
                        blockEx += tempShare.shareName + " ";
                    }
                    if (tempIndex >= 0 && !increasedCap.contains(tempShare.shareName + " ")
                            && (tempIndexY >= 0 && shareChangeAbs <= exVolumeY.get(tempIndexY) || tempIndexY < 0)) {
                        tv = totalVolume.get(tempIndex);
                        pc = closingPrice2.get(tempIndex);
                        marketCap = tv * pc / 10000000000L;
                        changeThreshold = 15 * tv / (marketCap + 6000);
                        if (marketCap < 10000L)
                            changeThreshold /= 3;
                        if (isSaham(tempShare.shareName)) {
                            if (shareholderName.equals("شخص حقيقي") || shareholderName.equals("سبد شخص حقیقی")) {
                                if (tempShare.change > 0)
                                    totalBuy1 += (tempShare.change * pc);
                                else if (tempShare.change < 0)
                                    totalSell1 -= (tempShare.change * pc);
                            } else if (shareholderName.contains("IFM") || shareholderName.contains("BFM") || shareholderName.contains("PRX")
                                    || shareholderName.contains("ذذذ") || shareholderName.contains("بازارگر")) {
                                if (tempShare.change > 0)
                                    totalBuy3 += (tempShare.change * pc);
                                else if (tempShare.change < 0)
                                    totalSell3 -= (tempShare.change * pc);
                            } else {
                                if (tempShare.change > 0)
                                    totalBuy2 += (tempShare.change * pc);
                                else if (tempShare.change < 0)
                                    totalSell2 -= (tempShare.change * pc);
                            }
                        }

                        if ((tempShare.change * pc >= 5000000000L || tempShare.change * pc <= -5000000000L) &&
                                ((double) tempShare.change / (double) tempShare.sharesNumber > 0.1
                                        || (double) tempShare.change / (double) tempShare.sharesNumber < -0.1
                                        || tempIndex >= 0 && tempShare.change > changeThreshold
                                        || tempIndex >= 0 && tempShare.change < -changeThreshold
                                        || tempIndex >= 0 && tempShare.change / (double) tv > 0.005
                                        || tempIndex >= 0 && tempShare.change / (double) tv < -0.005)) {

                            if (isSaham(tempShare.shareName)) {
                                if (shareholderName.equals("شخص حقيقي") || shareholderName.equals("سبد شخص حقیقی")) {
                                    if (tempShare.change > 0) {
                                        buyList1 += String.format("%.1f", (double) tempShare.change / (double) changeThreshold) + ";"
                                                + String.format("%.1f", (double) marketCap / 1000D) + ";"
                                                + String.format("%.1f", (double) (tempShare.change * pc) / 10000000000D) + ";"
                                                + tempShare.shareName + ";" + shareholders.get(i).name + "\n";
                                    } else if (tempShare.change < 0) {
                                        sellList1 += String.format("%.1f", (double) (-tempShare.change) / (double) changeThreshold) + ";"
                                                + String.format("%.1f", (double) marketCap / 1000D) + ";"
                                                + String.format("%.1f", (double) (-tempShare.change * pc) / 10000000000D) + ";"
                                                + tempShare.shareName + ";" + shareholders.get(i).name + "\n";
                                    }
                                } else if (shareholderName.contains("IFM") || shareholderName.contains("BFM") || shareholderName.contains("PRX")
                                        || shareholderName.contains("ذذذ") || shareholderName.contains("بازارگر")) {
                                    String tempShhName = shareholders.get(i).name;
                                    if(shareholderName.contains("IFM") || shareholderName.contains("BFM") || shareholderName.contains("PRX"))
                                        tempShhName = tempShhName.substring(3);
                                    if (tempShare.change > 0) {
                                        buyList3 += String.format("%.1f", (double) tempShare.change / (double) changeThreshold) + ";"
                                                + String.format("%.1f", (double) marketCap / 1000D) + ";"
                                                + String.format("%.1f", (double) (tempShare.change * pc) / 10000000000D) + ";"
                                                + tempShare.shareName + ";" + tempShhName + "\n";
                                    } else if (tempShare.change < 0) {
                                        sellList3 += String.format("%.1f", (double) (-tempShare.change) / (double) changeThreshold) + ";"
                                                + String.format("%.1f", (double) marketCap / 1000D) + ";"
                                                + String.format("%.1f", (double) (-tempShare.change * pc) / 10000000000D) + ";"
                                                + tempShare.shareName + ";" + tempShhName + "\n";
                                    }
                                } else {
                                    if (tempShare.change > 0) {
                                        buyList2 += String.format("%.1f", (double) tempShare.change / (double) changeThreshold) + ";"
                                                + String.format("%.1f", (double) marketCap / 1000D) + ";"
                                                + String.format("%.1f", (double) (tempShare.change * pc) / 10000000000D) + ";"
                                                + tempShare.shareName + ";" + shareholders.get(i).name + "\n";
                                    } else if (tempShare.change < 0) {
                                        sellList2 += String.format("%.1f", (double) (-tempShare.change) / (double) changeThreshold) + ";"
                                                + String.format("%.1f", (double) marketCap / 1000D) + ";"
                                                + String.format("%.1f", (double) (-tempShare.change * pc) / 10000000000D) + ";"
                                                + tempShare.shareName + ";" + shareholders.get(i).name + "\n";
                                    }
                                }
                            }
//                        else {
//                            if(tempShare.change > 0){
//                                buyList4 += String.format("%.1f", (double) tempShare.change / (double) changeThreshold) + ";"
//                                        + String.format("%.1f", (double)marketCap / 1000D)+ ";"
//                                        + String.format("%.1f", (double)(tempShare.change * pc) / 10000000000D) + ";"
//                                        + tempShare.shareName + ";" + shareholders.get(i).name + "\n";
//                            }
//                            else if(tempShare.change < 0){
//                                sellList4 += String.format("%.1f", (double)(-tempShare.change) / (double) changeThreshold) + ";"
//                                        + String.format("%.1f", (double)marketCap / 1000D)+ ";"
//                                        + String.format("%.1f", (double)(-tempShare.change * pc) / 10000000000D) + ";"
//                                        + tempShare.shareName + ";" + shareholders.get(i).name + "\n";
//                            }
//                        }


//                        writer2.write(shareholders.get(i).name + " | " + tempShare.shareName + " | " + marketCap  + " | "
//                                + String.format("%.1f", (double)(tempShare.change * pc) / 10000000000D) + " | "
//                                + String.format("%.2f", (double) tempShare.change / (double) tempShare.sharesNumber));
//                        if (tempIndex >= 0) {
//                            writer2.write(" | " + String.format("%.3f", (double) tempShare.change / (double) tv)
//                                    + " | " + String.format("%.1f", (double) tempShare.change / (double) changeThreshold) + "\n");
//                        } else
//                            writer2.write(" | 0 | 0\n");
                        }
                    }
//                }
                writer.write(tempShare.shareName + " ");
                writer.write(tempShare.sharesNumber + " ");
                writer.write(tempShare.percent + " ");
                if(shareholders.get(i).currentShares.get(j).enterDate.equals(""))
                    writer.write("0 ");
                else
                    writer.write(shareholders.get(i).currentShares.get(j).enterDate + " ");
            }
            writer.write("*** ");
//            if(shareholders.get(i).prevShares.size() > 0)
//                System.out.println(shareholders.get(i).id + " " + shareholders.get(i).name + "\n");
            for (int j = 0; j < shareholders.get(i).prevShares.size(); j++){
//                boolean isInCurr = false;
//                for (int k = 0; k < shareholders.get(i).currentShares.size(); k++){
//                    if(shareholders.get(i).prevShares.get(j).shareName.equals(shareholders.get(i).currentShares.get(k).shareName)){
//                        isInCurr = true;
//                        break;
//                    }
//                }
//                if(isInCurr){
//                    System.out.print(shareholders.get(i).prevShares.get(j).shareName + " ");
//                    System.out.print(shareholders.get(i).prevShares.get(j).sharesNumber + " ");
//                    System.out.print(shareholders.get(i).prevShares.get(j).percent + " ");
//                    System.out.print(shareholders.get(i).prevShares.get(j).enterDate + " ");
//                    System.out.print(shareholders.get(i).prevShares.get(j).exitDate + "\n");
//                }
                writer.write(shareholders.get(i).prevShares.get(j).shareName + " ");
                writer.write(shareholders.get(i).prevShares.get(j).sharesNumber + " ");
                writer.write(shareholders.get(i).prevShares.get(j).percent + " ");
                if(shareholders.get(i).prevShares.get(j).enterDate.equals(""))
                    writer.write("0 ");
                else
                    writer.write(shareholders.get(i).prevShares.get(j).enterDate + " ");
                if(shareholders.get(i).prevShares.get(j).exitDate.equals(""))
                    writer.write("0 ");
                else
                    writer.write(shareholders.get(i).prevShares.get(j).exitDate + " ");
            }
            writer.write("***\n");
        }
        writer.close();
//        writer2.close();

        if(increasedCap.length() > 0){
            SendMessage message = new SendMessage().setText("معاملات بلوکی\n" + blockEx);
            customSendMessage(1, message, null, null, ID.FARDIN, true);
        }
        writer = new FileWriter("buyList1", true);
        writer.write(today + "\n" + buyList1);
        writer.close();
        writer = new FileWriter("buyList2", true);
        writer.write(today + "\n" + buyList2);
        writer.close();
        writer = new FileWriter("buyList3", true);
        writer.write(today + "\n" + buyList3);
        writer.close();
        writer = new FileWriter("sellList1", true);
        writer.write(today + "\n" + sellList1);
        writer.close();
        writer = new FileWriter("sellList2", true);
        writer.write(today + "\n" + sellList2);
        writer.close();
        writer = new FileWriter("sellList3", true);
        writer.write(today + "\n" + sellList3);
        writer.close();
        System.out.println("T10");
        totalBuy1 /= 10000000000L;
        totalSell1 /= 10000000000L;
        totalBuy2 /= 10000000000L;
        totalSell2 /= 10000000000L;
        totalBuy3 /= 10000000000L;
        totalSell3 /= 10000000000L;
        ArrayList<String> titles = new ArrayList<>();
        titles.add("خرید سهامداران عمده حقیقی");
        titles.add("فروش سهامداران عمده حقیقی");
        titles.add("خرید سهامداران عمده حقوقی (به جز بازارگردان)");
        titles.add("فروش سهامداران عمده حقوقی (به جز بازارگردان)");
        titles.add("خرید سهامداران عمده بازارگردان");
        titles.add("فروش سهامداران عمده بازارگردان");
        ArrayList<String> buySellLists = new ArrayList<>();
        buySellLists.add(buyList1);
        buySellLists.add(sellList1);
        buySellLists.add(buyList2);
        buySellLists.add(sellList2);
        buySellLists.add(buyList3);
        buySellLists.add(sellList3);
        int numOfRows;
        SendPhoto message = new SendPhoto();
        SendMessage message2 = new SendMessage();
        if(notDone < 50) {
            for (int i = 0; i < 6; i++) {
                numOfRows = saveShareholdersChangeImage(buySellLists.get(i));
                if (numOfRows > 0) {
                    message.setPhoto(new File("shareholderchange.png"));
                    message.setCaption(titles.get(i) + " در تاریخ " + formattedDate(changeGregorianToPersian(String.valueOf(lastWorkDayGreg())))
                            + "\n" + ID.TABLOBOT_CHANNEL);
                    customSendMessage(2, null, message, null, ID.PRIVATE_SHAREHOLDERS, true);
                    if (sendPublic)
                        customSendMessage(2, null, message, null, ID.PUBLIC_CHANNEL, true);

                }
            }
            message2.setText("مبلغ کل خرید و فروش سهامداران عمده (غیر از اوراق، اختیار معامله و صندوق درآمد ثابت) در تاریخ "
                    + formattedDate(changeGregorianToPersian(String.valueOf(lastWorkDayGreg()))) + "\n\n"
                    + "حقیقی:" + "\n" + "خرید: " + totalBuy1 + " میلیارد تومان" + "\n" + "فروش: " + totalSell1 + " میلیارد تومان" + "\n\n"
                    + "حقوقی (به جز بازارگردان):" + "\n" + "خرید: " + totalBuy2 + " میلیارد تومان" + "\n" + "فروش: " + totalSell2 + " میلیارد تومان" + "\n\n"
                    + "بازارگردان:" + "\n" + "خرید: " + totalBuy3 + " میلیارد تومان" + "\n" + "فروش: " + totalSell3 + " میلیارد تومان" + "\n\n"
                    + "مجموع:" + "\n" + "خرید: " + (totalBuy1 + totalBuy2 + totalBuy3) + " میلیارد تومان" + "\n"
                    + "فروش: " + (totalSell1 + totalSell2 + totalSell3)+ " میلیارد تومان" + "\n" + ID.TABLOBOT_CHANNEL);
            customSendMessage(1, message2, null, null, ID.PRIVATE_SHAREHOLDERS, true);
            if (sendPublic)
                customSendMessage(1, message2, null, null, ID.PUBLIC_CHANNEL, true);
        }

        System.out.println("T11");

//        List<String> closedSymbolsPrice = Arrays.asList("شزنگ", "116163", "فسلیر", "26571", "کهرام", "58784", "وحافظ", "59562",
//                "وشمال", "109132", "فلامی", "34040", "تپولا", "27565", "ثقزوی", "30140", "قپیرا", "87150", "زنجان", "6265", "وزمین", "14036",
//                "پلوله", "18986", "شرنگی", "107516", "وآداک", "11259", "تراک", "1803", "کرمان", "72562", "تلیسه", "4396", "نوآور", "326666",
//                "دهدشت", "8527", "توسعه", "2717");

        writer = new FileWriter("realShareholders.txt");
        for (int i = 0; i < shareholdersSize; i++) {
            if (shareholders.get(i).currentShares.size() > 1 && shareholders.get(i).name.equals("شخص حقيقي")) {
                for (int j = 0; j < shareholders.get(i).currentShares.size(); j++) {
                    String shareName = shareholders.get(i).currentShares.get(j).shareName;
                    long sharesNumber = shareholders.get(i).currentShares.get(j).sharesNumber;
                    double percent = shareholders.get(i).currentShares.get(j).percent;
                    int symbolIndex = symbolNickName.indexOf(shareName);
                    int closingPrice = 0;
                    if (symbolIndex >= 0)
                        closingPrice = closingPrice2.get(symbolIndex);
//                    else{
//                        int tempSymbolIndex = closedSymbolsPrice.indexOf(shareName);
//                        if (tempSymbolIndex >= 0)
//                            closingPrice = Integer.valueOf(closedSymbolsPrice.get(tempSymbolIndex + 1));
//                        else
//                            closingPrice = getAllowedMinPrice(getSymbolIDByNickName(shareName));
//                    }
                    writer.write(shareName + " " + String.format("%.3f", percent) + "% ");
                    if (closingPrice != 0) {
                        if(sharesNumber * closingPrice >= 1000000000L)
                            writer.write(String.format("%.1f", ((double)sharesNumber * (double) closingPrice / 10000000000D))
                                + " میلیارد تومان\n");
                        else
                            writer.write(String.format("%.3f", ((double)sharesNumber * (double) closingPrice / 10000000000D))
                                    + " میلیارد تومان\n");
                    }
                    else
                        writer.write("\n");
                }
                writer.write("\n");
            }
        }
        writer.close();
        System.out.println("T12");
        writer = new FileWriter("shareholdersLast4Months.txt");
        writer2 = new FileWriter("realShareholdersLast4Months.txt");
        for (int i = 0; i < shareholders.size(); i++) {
            for (int j = 0; j < shareholders.get(i).currentShares.size(); j++) {
                String shareName = shareholders.get(i).currentShares.get(j).shareName;
                if(Integer.valueOf(shareholders.get(i).currentShares.get(j).enterDate) > fourMonthsAgo
                        && (shareName.charAt(shareName.length() - 1) < '0'
                        || shareName.charAt(shareName.length() - 1) > '9'
                        || shareName.equals("انرژی1") || shareName.equals("انرژی2") || shareName.equals("انرژی3"))){
                    writer.write(shareholders.get(i).name + " | " +
                            shareholders.get(i).currentShares.get(j).shareName + " | " +
                            shareholders.get(i).currentShares.get(j).percent + " | " +
                            formattedDate(shareholders.get(i).currentShares.get(j).enterDate) + "\n");
                    if(shareholders.get(i).name.equals("شخص حقيقي")){
                        writer2.write(shareholders.get(i).currentShares.get(j).shareName + " | " +
                                shareholders.get(i).currentShares.get(j).percent + " | " +
                                formattedDate(shareholders.get(i).currentShares.get(j).enterDate) + "\n");
                    }
                }
            }
        }
        writer.close();
        writer2.close();

        writer = new FileWriter("realShareholdersForExcel.txt");
        for (int i = 0; i < shareholders.size(); i++) {
            if((shareholders.get(i).name.equals("شخص حقيقي") || shareholders.get(i).name.equals("سبد شخص حقیقی"))
                    && shareholders.get(i).prevShares.size() > 0){
                if(shareholders.get(i).currentShares.size() > 0)
                    writer.write("سهام فعلی" + "\n");
                for(int j = 0; j < shareholders.get(i).currentShares.size(); j++){
                    writer.write(shareholders.get(i).currentShares.get(j).shareName + ";" +
                            shareholders.get(i).currentShares.get(j).sharesNumber + ";" +
                            shareholders.get(i).currentShares.get(j).percent + ";" +
                            formattedDate(shareholders.get(i).currentShares.get(j).enterDate) + "\n");
                }
                writer.write("سهام قبلی" + "\n");
                for(int j = 0; j < shareholders.get(i).prevShares.size(); j++){
                    writer.write(shareholders.get(i).prevShares.get(j).shareName + ";" +
                            shareholders.get(i).prevShares.get(j).sharesNumber + ";" +
                            shareholders.get(i).prevShares.get(j).percent + ";" +
                            formattedDate(shareholders.get(i).prevShares.get(j).enterDate) + ";" +
                            formattedDate(shareholders.get(i).prevShares.get(j).exitDate) + "\n");
                }
                writer.write("----------;----------;----------;----------;----------\n");
            }
        }
        for (int i = 0; i < shareholders.size(); i++) {
            if((shareholders.get(i).name.equals("شخص حقيقي") || shareholders.get(i).name.equals("سبد شخص حقیقی"))
                    && shareholders.get(i).prevShares.size() == 0){
                if(shareholders.get(i).currentShares.size() > 0)
                    writer.write("سهام فعلی" + "\n");
                for(int j = 0; j < shareholders.get(i).currentShares.size(); j++){
                    writer.write(shareholders.get(i).currentShares.get(j).shareName + ";" +
                            shareholders.get(i).currentShares.get(j).sharesNumber + ";" +
                            shareholders.get(i).currentShares.get(j).percent + ";" +
                            formattedDate(shareholders.get(i).currentShares.get(j).enterDate) + "\n");
                }
                writer.write("----------;----------;----------;----------;----------\n");
            }
        }
        writer.close();
        writer = new FileWriter("shareholdersFull");
        for(int i = 0; i < shareholdersFull.size(); i++){
            writer.write(shareholdersFull.get(i).id + "\n" + shareholdersFull.get(i).name + "\n");
            for (int j = 0; j < shareholdersFull.get(i).prevShares.size(); j++)
                writer.write(shareholdersFull.get(i).prevShares.get(j).enterOrExit + " " +
                        shareholdersFull.get(i).prevShares.get(j).shareName + " " +
                        shareholdersFull.get(i).prevShares.get(j).date + " " +
                        String.format("%.2f ", shareholdersFull.get(i).prevShares.get(j).pnl1) +
                        String.format("%.2f ", shareholdersFull.get(i).prevShares.get(j).pnl2) +
                        String.format("%.2f ", shareholdersFull.get(i).prevShares.get(j).pnl3));
            writer.write("*\n");
        }
        writer.close();

        SendDocument message1 = new SendDocument();
        message1.setDocument(new File("realShareholders.txt"));
        customSendMessage(3, null, null, message1, ID.FARDIN, true);
        message1.setDocument(new File("shareholders"));
        customSendMessage(3, null, null, message1, ID.FARDIN, true);
        message1.setDocument(new File("shareholdersFull"));
        customSendMessage(3, null, null, message1, ID.FARDIN, true);

        System.out.println("T13");
        checkShHistory();
        writeToExcel();
        shareholders.clear();
        shareholdersFull.clear();
        System.out.println("T14");
    }
    private void checkShHistory() throws IOException, InterruptedException, TelegramApiException{
        System.out.print("S1");
        ArrayList<String> shareholderName = new ArrayList<>();
        ArrayList<String> shareName = new ArrayList<>();
        ArrayList<Float> value = new ArrayList<>();
        ArrayList<Float> marketCap = new ArrayList<>();
        ArrayList<Float> ratio = new ArrayList<>();
        ArrayList<Integer> date = new ArrayList<>();
        ArrayList<String> shareName2 = new ArrayList<>();
        ArrayList<Integer> date2 = new ArrayList<>();
        ArrayList<Integer> buyRepeats = new ArrayList<>();
        ArrayList<Integer> sellRepeats = new ArrayList<>();
        ArrayList<Integer> typesOffset = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int today = calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);
        today = Integer.valueOf(changeGregorianToPersian(String.valueOf(today)));
        int daysToCheck = 5;
        int cnt = 0;
        for(int i = 0; i < 6; i++) {
            String fileName = "buyList1";
            if(i == 1)
                fileName = "buyList2";
            else if(i == 2)
                fileName = "buyList3";
            else if(i == 3)
                fileName = "sellList1";
            else if(i == 4)
                fileName = "sellList2";
            else if(i == 5)
                fileName = "sellList3";
            Scanner sc = new Scanner(new FileInputStream(fileName));
            String tempLine;
            int tempDate = 0;
            String[] tempStringArray;
            double[] tempDoubleArray;
            while (sc.hasNextLine()) {
                tempLine = sc.nextLine();
                if (!tempLine.contains(";"))
                    tempDate = Integer.valueOf(tempLine);
                else {
                    int j = 0;
                    tempDoubleArray = stringNextDouble(tempLine, ';', j);
                    j = (int) tempDoubleArray[1];
                    ratio.add((float) tempDoubleArray[0]);
                    tempDoubleArray = stringNextDouble(tempLine, ';', j);
                    j = (int) tempDoubleArray[1];
                    marketCap.add((float) tempDoubleArray[0]);
                    tempDoubleArray = stringNextDouble(tempLine, ';', j);
                    j = (int) tempDoubleArray[1];
                    value.add((float) tempDoubleArray[0]);
                    tempStringArray = stringNextString(tempLine, ';', j);
                    j = Integer.valueOf(tempStringArray[1]);
                    shareName.add(tempStringArray[0]);
                    tempStringArray = stringNextString(tempLine, ';', j);
                    shareholderName.add(tempStringArray[0]);
                    date.add(tempDate);
                    cnt++;
                }
            }
            sc.close();
            typesOffset.add(cnt);
        }
        System.out.print("S2");
        shareName2.add(shareName.get(0));
        for(int i = 0; i < date.size(); i++){
            int minDate = 99999999;
            for(int j = i; j < date.size(); j++){
                if(minDate > date.get(j))
                    minDate = date.get(j);
            }
            if(date2.size() == 0 || minDate > date2.get(date2.size() - 1))
                date2.add(minDate);
        }
        int firstDayToCheck = 0;
        if(date2.size() > daysToCheck)
            firstDayToCheck = date2.get(date2.size() - daysToCheck);
        else if(date2.size() > 0)
            firstDayToCheck = date2.get(0);
        for(int i = 1; i < date.size(); i++){
            if(!shareName2.contains(shareName.get(i)))
                shareName2.add(shareName.get(i));
        }
        for(int i = 0; i < shareName2.size(); i++){
            buyRepeats.add(0);
            sellRepeats.add(0);
        }
        System.out.print("S3");
        for(int i = 0; i < shareName.size(); i++){
            if(date.get(i) >= firstDayToCheck){
                int index = shareName2.indexOf(shareName.get(i));
                if(index >= 0){
                    if(i < typesOffset.get(2))
                        buyRepeats.set(index, buyRepeats.get(index)+1);
                    else
                        sellRepeats.set(index, sellRepeats.get(index)+1);
                }
            }
        }
        System.out.print("S4");
        String toSend = "";
        for(int i = 0; i < shareName2.size(); i++){
            String toSendTemp = "";
            boolean todayHasBuy = false;
            if(buyRepeats.get(i) >= 3 && buyRepeats.get(i) >= sellRepeats.get(i) + 2){
                toSendTemp = "#" + shareName2.get(i) + "\n";
                String tmp1, tmp2 = "";
                for(int j = 0; j < shareName.size(); j++){
                    tmp1 = "";
                    if(shareName2.get(i).equals(shareName.get(j)) && date.get(j) >= firstDayToCheck){
                        if (j < typesOffset.get(2)){
                            if(date.get(j) == today)
                                todayHasBuy = true;
                            tmp1 += "خرید: ";
                        }
                        else
                            tmp1 += "فروش: ";
                        tmp1 += shareholderName.get(j);
                        if(!tmp1.equals(tmp2))
                            toSendTemp += tmp1 + "\n";
                        toSendTemp += value.get(j) + " " + marketCap.get(j) + " " + ratio.get(j) + " "
                                + formattedDate(String.valueOf(date.get(j))) + "\n";
                        tmp2 = tmp1;
                    }
                }
            }
            if(todayHasBuy)
                toSend += toSendTemp;
        }
        System.out.print("S5");
        sendLongMessage(toSend, ID.FARDIN, true);
        shareholderName.clear(); shareName.clear(); value.clear(); marketCap.clear(); ratio.clear(); date.clear();
        date2.clear(); shareName2.clear(); buyRepeats.clear(); sellRepeats.clear(); typesOffset.clear();
        System.out.print("S6");
    }

    public void writeToExcel() throws IOException, TelegramApiException{
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet1 = workbook.createSheet("Sheet1");
        XSSFSheet sheet2 = workbook.createSheet("Sheet2");
        sheet1.setRightToLeft(true);
        sheet2.setRightToLeft(true);
        CellStyle style = workbook.createCellStyle();
        org.apache.poi.ss.usermodel.Font font = workbook.createFont();
        font.setFontHeightInPoints((short)13);
        font.setFontName("B Nazanin");
        style.setFont(font);
        style.setShrinkToFit(true);
        style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.RIGHT);
        Row row = sheet1.createRow(0);
        Cell cell;
        int rowNum = 0;
        int columnNum = 1;
        int maxColumns = 0;
        cell = row.createCell(0);
        cell.setCellValue("نماد");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("ارزش شرکت");
        cell.setCellStyle(style);
        for(int i = 0; i < 3; i++) {
            cell = row.createCell(i*4+2);
            cell.setCellValue("سهامدار");
            cell.setCellStyle(style);
            cell = row.createCell(i*4+3);
            cell.setCellValue("تعداد سهم");
            cell.setCellStyle(style);
            cell = row.createCell(i*4+4);
            cell.setCellValue("ارزش سهم");
            cell.setCellStyle(style);
            cell = row.createCell(i*4+5);
            cell.setCellValue("درصد");
            cell.setCellStyle(style);
        }
        rowNum++;
        ArrayList<String> shNickNames = new ArrayList<>();
        Scanner sc = new Scanner(new FileInputStream("shNick"));
        while (sc.hasNextLine())
            shNickNames.add(sc.nextLine());
        sc.close();
        sc = new Scanner(new FileInputStream("shhInSymbols"));
        String tempStr = "";
        while (sc.hasNextLine()) {
            tempStr = sc.nextLine();
//            String tempStr2 = "";
//            if(tempStr.length() > 5  && tempStr.substring(0,5).equals("شركت "))
//                tempStr2 = tempStr.substring(5);
//            int dashIndex = tempStr2.indexOf('-');
//            if(dashIndex >= 0)
//                tempStr2 = tempStr2.substring(0,dashIndex);
//            int index2 = symbolName.indexOf(tempStr2);
//            if(index2 >= 0)
//                tempStr2 = symbolNickName.get(index2);
//            else{
//                index2 = symbolName.indexOf(tempStr);
//                if(index2 >= 0)
//                    tempStr2 = symbolNickName.get(index2);
//            }
            if(tempStr.charAt(0) == '*'){
                row = sheet1.createRow(rowNum++);
                cell = row.createCell(0);
                cell.setCellValue(tempStr.substring(1));
                cell.setCellStyle(style);
                columnNum = 1;
            }
            else{
                cell = row.createCell(columnNum++);
                int index2 = shNickNames.indexOf(tempStr);
                if(index2 >= 0)
                    cell.setCellValue(shNickNames.get(index2 + 1) + " (" + tempStr + ")");
                else
                    cell.setCellValue(tempStr);
                cell.setCellStyle(style);
            }
            if(columnNum > maxColumns)
                maxColumns = columnNum;
        }
        sc.close();
        for(int i = 0; i < maxColumns; i++)
            sheet1.autoSizeColumn(i);
        rowNum = 0;
        maxColumns = 0;
        row = sheet2.createRow(0);
        cell = row.createCell(0);
        cell.setCellValue("سهامدار");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("نماد اختصاری");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("ارزش شرکت");
        cell.setCellStyle(style);
        for(int i = 0; i < 3; i++) {
            cell = row.createCell(i*4+3);
            cell.setCellValue("نماد");
            cell.setCellStyle(style);
            cell = row.createCell(i*4+4);
            cell.setCellValue("تعداد سهم");
            cell.setCellStyle(style);
            cell = row.createCell(i*4+5);
            cell.setCellValue("ارزش سهم");
            cell.setCellStyle(style);
            cell = row.createCell(i*4+6);
            cell.setCellValue("درصد");
            cell.setCellStyle(style);
        }
        rowNum++;
        for(int i = 0; i < shareholders.size(); i++){
            Shareholder shh = shareholders.get(i);
            boolean realPerson = shh.name.equals("شخص حقيقي") || shh.name.equals("سبد شخص حقیقی") || shh.name.equals("شخص حقیقی خارجی");
            String tempshhname = shh.name, tempshhname2 = "";
//            if(tempshhname.length() > 5  && tempshhname.substring(0,5).equals("شركت "))
//                tempshhname2 = tempshhname.substring(5);
//            int dashIndex = tempshhname2.indexOf('-');
//            if(dashIndex >= 0)
//                tempshhname2 = tempshhname2.substring(0,dashIndex);
//            int index2 = symbolName.indexOf(tempshhname2);
//            if(index2 >= 0)
//                tempshhname2 = symbolNickName.get(index2);
//            else{
//                index2 = symbolName.indexOf(tempshhname);
//                if(index2 >= 0)
//                    tempshhname2 = symbolNickName.get(index2);
//            }
            if(!realPerson && shh.currentShares.size() > 0){
                row = sheet2.createRow(rowNum++);
                cell = row.createCell(0);
                cell.setCellValue(shh.name);
                cell.setCellStyle(style);
                int index2 = shNickNames.indexOf(shh.name);
                if(index2 >= 0){
                    cell = row.createCell(1);
                    cell.setCellValue(shNickNames.get(index2 + 1));
                    cell.setCellStyle(style);
                    int index3 = symbolNickName.indexOf(shNickNames.get(index2 + 1));
                    double marketCap = 0;
                    if(index3 >= 0)
                        marketCap = (double)totalVolume.get(index3) * (double)closingPrice2.get(index3) / 10000000000000D;
                    cell = row.createCell(2);
                    cell.setCellValue(String.format("%.2f", (float)marketCap));
                    cell.setCellStyle(style);
                }
                else{
                    cell = row.createCell(1);
                    cell.setCellValue("");
                    cell.setCellStyle(style);
                    cell = row.createCell(2);
                    cell.setCellValue("");
                    cell.setCellStyle(style);
                }
                columnNum = 3;
                for(int j = 0; j < shh.currentShares.size(); j++){
                    int symbolIndex = symbolNickName.indexOf(shh.currentShares.get(j).shareName);
                    cell = row.createCell(columnNum++);
                    cell.setCellValue(shh.currentShares.get(j).shareName);
                    cell.setCellStyle(style);
                    cell = row.createCell(columnNum++);
                    cell.setCellValue(String.format("%.1f", (float)shh.currentShares.get(j).sharesNumber / 1000000));
                    cell.setCellStyle(style);
                    cell = row.createCell(columnNum++);
                    if(symbolIndex >= 0){
                        long val = shh.currentShares.get(j).sharesNumber * closingPrice2.get(symbolIndex) / 10000000;
                        cell.setCellValue(String.format("%.1f", (float)val / 1000));
                    }
                    else
                        cell.setCellValue("0.0");
                    cell.setCellStyle(style);
                    cell = row.createCell(columnNum++);
                    cell.setCellValue(shh.currentShares.get(j).percent + "%");
                    cell.setCellStyle(style);
                }
                if(columnNum > maxColumns)
                    maxColumns = columnNum;
            }
        }
        for(int i = 0; i < maxColumns; i++)
            sheet2.autoSizeColumn(i);
        FileOutputStream fileOut = new FileOutputStream("sh.xlsx");
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
        SendDocument message1 = new SendDocument();
        message1.setDocument(new File("sh.xlsx"));
        message1.setChatId(ID.IMAN);
        execute(message1);
    }

    public void addShareholdersRecords(String filePath) throws IOException {
        shareholdersFull.clear();
        Scanner sc = new Scanner(new FileInputStream("shareholdersFull"));
        while (sc.hasNext()) {
            Shareholder2 tempShareholder = new Shareholder2();
            tempShareholder.id = sc.nextLine();
            tempShareholder.name = sc.nextLine();
            while (sc.hasNext()) {
                Share2 tempShare = new Share2();
                tempShare.enterOrExit = sc.next();
                if(tempShare.enterOrExit.equals("*"))
                    break;
                tempShare.shareName = sc.next();
                tempShare.date = sc.next();
                tempShare.pnl1 = sc.nextFloat();
                tempShare.pnl2 = sc.nextFloat();
                tempShare.pnl3 = sc.nextFloat();
                tempShareholder.prevShares.add(tempShare);
            }
            sc.nextLine();
            shareholdersFull.add(tempShareholder);
        }
        sc.close();
        File file = new File(filePath);
        File[] fileList = file.listFiles();
        System.out.println(fileList.length);
        for (int i = 0; i < fileList.length; i++) {
            sc = new Scanner(fileList[i]);
            String line = "", id = "", name = "";
            String[] tempStringArray;
            while (sc.hasNextLine()) {
                Share2 share = new Share2();
                share.shareName = fileList[i].getName();
                line = sc.nextLine();
                int index = 0;
                tempStringArray = stringNextString(line, ',', index);
                index = Integer.valueOf(tempStringArray[1]);
                share.enterOrExit = tempStringArray[0];
                tempStringArray = stringNextString(line, ',', index);
                index = Integer.valueOf(tempStringArray[1]);
                id = tempStringArray[0];
                tempStringArray = stringNextString(line, ',', index);
                index = Integer.valueOf(tempStringArray[1]);
                name = tempStringArray[0];
                share.date = line.substring(index, line.length());
                boolean shareholderFound = false;
                boolean realPerson = name.equals("شخص حقيقي") || name.equals("سبد شخص حقیقی") || name.equals("شخص حقیقی خارجی");
                for (int j = 0; j < shareholdersFull.size(); j++){
                    if(shareholdersFull.get(j).name.equals(name) && (shareholdersFull.get(j).id.equals(id) || !realPerson)){
                        shareholdersFull.get(j).prevShares.add(share);
                        shareholderFound = true;
                        break;
                    }
                }
                if(!shareholderFound){
                    Shareholder2 shareholder = new Shareholder2();
                    shareholder.id = id;
                    shareholder.name = name;
                    shareholder.prevShares.add(share);
                    shareholdersFull.add(shareholder);
                }
            }
            sc.close();
        }
        FileWriter fileWriter = new FileWriter("shareholdersFull");
        for(int i = 0; i < shareholdersFull.size(); i++){
            fileWriter.write(shareholdersFull.get(i).id + "\n" + shareholdersFull.get(i).name + "\n");
            for (int j = 0; j < shareholdersFull.get(i).prevShares.size(); j++)
                fileWriter.write(shareholdersFull.get(i).prevShares.get(j).enterOrExit + " " +
                        shareholdersFull.get(i).prevShares.get(j).shareName + " " +
                        shareholdersFull.get(i).prevShares.get(j).date + " " +
                        String.format("%.2f ", shareholdersFull.get(i).prevShares.get(j).pnl1) +
                        String.format("%.2f ", shareholdersFull.get(i).prevShares.get(j).pnl2) +
                        String.format("%.2f ", shareholdersFull.get(i).prevShares.get(j).pnl3));
            fileWriter.write("*\n");
        }
        fileWriter.close();
    }
    public void sortShareholdersRecords() throws IOException {
        shareholdersFull.clear();
        Scanner sc = new Scanner(new FileInputStream("shareholdersFull"));
        while (sc.hasNext()) {
            Shareholder2 tempShareholder = new Shareholder2();
            tempShareholder.id = sc.nextLine();
            tempShareholder.name = sc.nextLine();
            while (sc.hasNext()) {
                Share2 tempShare = new Share2();
                tempShare.enterOrExit = sc.next();
                if(tempShare.enterOrExit.equals("*"))
                    break;
                tempShare.shareName = sc.next();
                tempShare.date = sc.next();
                tempShare.pnl1 = sc.nextFloat();
                tempShare.pnl2 = sc.nextFloat();
                tempShare.pnl3 = sc.nextFloat();
                tempShareholder.prevShares.add(tempShare);
            }
            sc.nextLine();
            shareholdersFull.add(tempShareholder);
        }
        ArrayList<Integer> indexes = new ArrayList<>();
        for(int i = 0; i < shareholdersFull.size(); i++){
            int minId = 999999999, minIdIndex = 0;
            for(int j = 0; j < shareholdersFull.size(); j++){
                int id = Integer.valueOf(shareholdersFull.get(j).id);
                if(id < minId && !indexes.contains(j)){
                    minId = id;
                    minIdIndex = j;
                }
            }
            indexes.add(minIdIndex);
        }
        ArrayList<Shareholder2> shareholdersFull2 = new ArrayList<>();
        for (int i = 0; i < indexes.size(); i++){
            Shareholder2 tempShareholder1 = shareholdersFull.get(indexes.get(i));
            Shareholder2 tempShareholder2 = new Shareholder2();
            tempShareholder2.id = tempShareholder1.id;
            tempShareholder2.name = tempShareholder1.name;
            ArrayList<Integer> dateIndexes = new ArrayList<>();
            for (int j = 0; j < tempShareholder1.prevShares.size(); j++){
                int minDate = 99999999, minDateIndex = 0;
                for (int k = 0; k < tempShareholder1.prevShares.size(); k++){
                    int date = Integer.valueOf(tempShareholder1.prevShares.get(k).date);
                    if(date < minDate && !dateIndexes.contains(k)){
                        minDate = date;
                        minDateIndex = k;
                    }
                }
                dateIndexes.add(minDateIndex);
            }
            for (int j = 0; j < dateIndexes.size(); j++)
                tempShareholder2.prevShares.add(tempShareholder1.prevShares.get(dateIndexes.get(j)));
            shareholdersFull2.add(tempShareholder2);
        }
        FileWriter fileWriter = new FileWriter("shareholdersFull");
        for(int i = 0; i < shareholdersFull2.size(); i++){
            fileWriter.write(shareholdersFull2.get(i).id + "\n" + shareholdersFull2.get(i).name + "\n");
            for (int j = 0; j < shareholdersFull2.get(i).prevShares.size(); j++)
                fileWriter.write(shareholdersFull2.get(i).prevShares.get(j).enterOrExit + " " +
                        shareholdersFull2.get(i).prevShares.get(j).shareName + " " +
                        shareholdersFull2.get(i).prevShares.get(j).date + " " +
                        String.format("%.2f ", shareholdersFull.get(i).prevShares.get(j).pnl1) +
                        String.format("%.2f ", shareholdersFull.get(i).prevShares.get(j).pnl2) +
                        String.format("%.2f ", shareholdersFull.get(i).prevShares.get(j).pnl3));
            fileWriter.write("*\n");
        }
        fileWriter.close();
    }
    public void playersStatus(String fileName, boolean partial) throws IOException, TelegramApiException, InterruptedException{
        System.out.print("W1");
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Integer> days = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int today = calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);
        today = Integer.valueOf(changeGregorianToPersian(String.valueOf(today)));
        int daysToCheck = 5;
        int baseDate = 0;
        Scanner sc = new Scanner(new FileInputStream(fileName));
        String shareName;
        while (sc.hasNext()) {
            sc.next();
            sc.next();
            sc.next();
            sc.next();
            sc.next();
            sc.next();
            sc.next();
            sc.next();
            int date = sc.nextInt();
            if (days.size() == 0 || days.size() > 0 && date > days.get(days.size() - 1))
                days.add(date);
        }
        sc.close();
        System.out.print("W2");
        int index = days.size() - daysToCheck;
        if(partial)
            index++;
        if(index < 0)
            index = 0;
        baseDate = days.get(index);

        sc = new Scanner(new FileInputStream(fileName));
        while (sc.hasNext()) {
            PlayerDay playerDay = new PlayerDay();
            shareName = sc.next();
            playerDay.marketCap = sc.nextFloat();
            playerDay.buyRatio = sc.nextFloat();
            playerDay.sellRatio = sc.nextFloat();
            playerDay.realBuy = sc.nextInt();
            playerDay.legalBuy = sc.nextInt();
            playerDay.realSell = sc.nextInt();
            playerDay.legalSell = sc.nextInt();
            playerDay.date = sc.next();
            if(Integer.valueOf(playerDay.date) >= baseDate) {
                boolean playerFound = false;
                int playersSize = players.size();
                for (int i = 0; i < playersSize; i++) {
                    if (players.get(i).shareName.equals(shareName)) {
                        players.get(i).playerDays.add(playerDay);
                        players.get(i).realBuySum += playerDay.realBuy;
                        players.get(i).legalBuySum += playerDay.legalBuy;
                        players.get(i).realSellSum += playerDay.realSell;
                        players.get(i).legalSellSum += playerDay.legalSell;
                        if (playerDay.realBuy >= playerDay.realSell)
                            players.get(i).realBuyDays++;
                        else
                            players.get(i).realSellDays++;
                        playerFound = true;
                        break;
                    }
                }
                if (!playerFound) {
                    Player player = new Player();
                    player.shareName = shareName;
                    player.playerDays.add(playerDay);
                    player.realBuySum = playerDay.realBuy;
                    player.legalBuySum = playerDay.legalBuy;
                    player.realSellSum = playerDay.realSell;
                    player.legalSellSum = playerDay.legalSell;
                    if (playerDay.realBuy >= playerDay.realSell)
                        player.realBuyDays = 1;
                    else
                        player.realSellDays = 1;
                    players.add(player);
                }
            }
        }
        sc.close();
        int playersSize = players.size();
        if(partial){
            for (int j = 0; j < playersSize; j++) {
                int i = symbolNickName.indexOf(players.get(j).shareName);
                if(i >= 0){
                    long marketCap = totalVolume.get(i) * closingPrice2.get(i) / 10000000L; // Million Toman
                    int rba = 0, rsa = 0, lba = 0, lsa = 0;
                    float buyRatio = 0, sellRatio = 0;
                    if(fileName.equals("players/all")) {
                        rba = (realBuyAccumulate.get(i) >= 100) ? realBuyAccumulate.get(i) : 0;
                        rsa = (realSellAccumulate.get(i) >= 100) ? realSellAccumulate.get(i) : 0;
                        lba = (legalBuyAccumulate.get(i) >= 100) ? legalBuyAccumulate.get(i) : 0;
                        lsa = (legalSellAccumulate.get(i) >= 100) ? legalSellAccumulate.get(i) : 0;
                        buyRatio = (float) (realBuyAccumulate.get(i) * 100) / (float) marketCap;
                        sellRatio = (float) (realSellAccumulate.get(i) * 100) / (float) marketCap;
                    }
                    else if(fileName.equals("players/all2")) {
                        rba = (realBuyAccumulate2.get(i) >= 100) ? realBuyAccumulate2.get(i) : 0;
                        rsa = (realSellAccumulate2.get(i) >= 100) ? realSellAccumulate2.get(i) : 0;
                        lba = (legalBuyAccumulate2.get(i) >= 100) ? legalBuyAccumulate2.get(i) : 0;
                        lsa = (legalSellAccumulate2.get(i) >= 100) ? legalSellAccumulate2.get(i) : 0;
                        buyRatio = (float)(realBuyAccumulate2.get(i) * 100) / (float)marketCap;
                        sellRatio = (float)(realSellAccumulate2.get(i) * 100) / (float)marketCap;
                    }
                    PlayerDay playerDay = new PlayerDay();
                    playerDay.marketCap = marketCap / 1000000F;
                    playerDay.date = String.valueOf(today);
                    playerDay.buyRatio = buyRatio;
                    playerDay.sellRatio = sellRatio;
                    playerDay.realBuy = rba;
                    playerDay.legalBuy = lba;
                    playerDay.realSell = rsa;
                    playerDay.legalSell = lsa;

                    if (fileName.equals("players/all") &&
                            (rba >= 500 && rba >= rsa * 2 && buyRatio >= 0.05 ||
                            rba >= 700 && rba >= rsa * 3 / 2 && buyRatio >= 0.06 ||
                            rba >= 1000 && rba >= rsa && buyRatio >= 0.07 ||
                            rsa >= 500 && rsa >= rba * 2 && sellRatio >= 0.05 ||
                            rsa >= 700 && rsa >= rba * 3 / 2 && sellRatio >= 0.06 ||
                            rsa >= 1000 && rsa > rba && sellRatio >= 0.07)
                            || fileName.equals("players/all2") &&
                            (rba >= 1000 && rba >= rsa * 2 && buyRatio >= 0.15 ||
                            rba >= 1500 && rba >= rsa * 3 / 2 && buyRatio >= 0.20 ||
                            rba >= 2000 && rba >= rsa && buyRatio >= 0.25 ||
                            rsa >= 1000 && rsa >= rba * 2 && sellRatio >= 0.15 ||
                            rsa >= 1500 && rsa >= rba * 3 / 2 && sellRatio >= 0.20 ||
                            rsa >= 2000 && rsa > rba && sellRatio >= 0.25)) {
                        players.get(j).playerDays.add(playerDay);
                        players.get(j).realBuySum += playerDay.realBuy;
                        players.get(j).legalBuySum += playerDay.legalBuy;
                        players.get(j).realSellSum += playerDay.realSell;
                        players.get(j).legalSellSum += playerDay.legalSell;
                        if (playerDay.realBuy >= playerDay.realSell)
                            players.get(j).realBuyDays++;
                        else
                            players.get(j).realSellDays++;
                    }
                }
            }
        }
        System.out.print("W3");
        String toSend = "";
        for (int i = 0; i < playersSize; i++) {
            int pdsize = players.get(i).playerDays.size();
            if(players.get(i).realBuyDays >= 2 && (players.get(i).realBuyDays - players.get(i).realSellDays) >= 2 &&
                    (!partial || players.get(i).playerDays.get(pdsize - 1).date.equals(String.valueOf(today)))){
                float marketCap = players.get(i).playerDays.get(pdsize - 1).marketCap;
                toSend += "#" + players.get(i).shareName + "\nمجموع\n"
                        + String.format("%.2f ", (float)players.get(i).realBuySum / marketCap / 10000L)
                        + String.format("%.2f ", (float)players.get(i).realSellSum / marketCap / 10000L)
                        + players.get(i).realBuySum + " " + players.get(i).legalBuySum + " "
                        + players.get(i).realSellSum + " " + players.get(i).legalSellSum + "\n";

                String toSendBuy = "", toSendSell = "";
                for (int j = 0; j < pdsize; j++) {
                    String tempStr = String.format("%.1f",players.get(i).playerDays.get(j).marketCap) + " "
                            + String.format("%.2f", players.get(i).playerDays.get(j).buyRatio) + " "
                            + String.format("%.2f", players.get(i).playerDays.get(j).sellRatio) + " "
                            + players.get(i).playerDays.get(j).realBuy + " "
                            + players.get(i).playerDays.get(j).legalBuy + " "
                            + players.get(i).playerDays.get(j).realSell + " "
                            + players.get(i).playerDays.get(j).legalSell + " "
                            + formattedDate(players.get(i).playerDays.get(j).date) + "\n";
                    if(players.get(i).playerDays.get(j).realBuy >= players.get(i).playerDays.get(j).realSell)
                        toSendBuy += tempStr;
                    else
                        toSendSell += tempStr;
                }
                toSend += "خریدار\n" + toSendBuy + "\n";
                if(toSendSell.length() > 0)
                    toSend += "فروشنده\n" + toSendSell + "\n";
            }
        }
        System.out.print("W4");
        if(!lastPSSent1.equals(toSend) && fileName.equals("players/all") ||
                !lastPSSent2.equals(toSend) && fileName.equals("players/all2") || !partial){
            if(fileName.equals("players/all")){
                if(players1MsgID > 0) {
                    DeleteMessage deleteMessage = new DeleteMessage().setChatId(ID.PLAYERS).setMessageId(players1MsgID);
                    execute(deleteMessage);
                }
                Message msg = sendLongMessage("نوع یک\n" + toSend, ID.PLAYERS, true);
                if(msg != null){
                    players1MsgID = msg.getMessageId();
                    lastPSSent1 = toSend;
                }
            }
            else{
                if(players2MsgID > 0) {
                    DeleteMessage deleteMessage = new DeleteMessage().setChatId(ID.PLAYERS).setMessageId(players2MsgID);
                    execute(deleteMessage);
                }
                Message msg = sendLongMessage("نوع دو\n" + toSend, ID.PLAYERS, true);
                if(msg != null){
                    players2MsgID = msg.getMessageId();
                    lastPSSent2 = toSend;
                }
            }
        }
        players.clear();
        days.clear();
        System.out.print("W5");
    }
    public void playersStatus2(String type) throws IOException, TelegramApiException, InterruptedException{
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int today = calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<String> days = new ArrayList<>();
        Scanner sc = new Scanner(new FileInputStream("players/" + changeGregorianToPersian(String.valueOf(today)).substring(0,6) + type));
        String date, shareName;
        while (sc.hasNext()) {
            date = sc.next();
            while (sc.hasNext()) {
                PlayerDay playerDay = new PlayerDay();
                shareName = sc.next();
                if(shareName.equals("***"))
                    break;
                days.add(date);
                playerDay.date = date;
                playerDay.marketCap = sc.nextFloat();
                playerDay.buyRatio = sc.nextFloat();
                playerDay.sellRatio = sc.nextFloat();
                playerDay.realBuy = sc.nextInt();
                playerDay.legalBuy = sc.nextInt();
                playerDay.realSell = sc.nextInt();
                playerDay.legalSell = sc.nextInt();
                boolean playerFound = false;
                for (int i = 0; i < players.size(); i++){
                    if(players.get(i).shareName.equals(shareName)){
                        players.get(i).playerDays.add(playerDay);
                        playerFound = true;
                        break;
                    }
                }
                if(!playerFound){
                    Player player = new Player();
                    player.shareName = shareName;
                    player.playerDays.add(playerDay);
                    players.add(player);
                }
            }
        }
        sc.close();
        ArrayList<Integer> buyRatioIndexSorted = new ArrayList<>();
        ArrayList<Integer> sellRatioIndexSorted = new ArrayList<>();
        for (int i = 0; i < players.size(); i++){
            float maxBuyRatioSum = 0, maxSellRatioSum = 0;
            int maxBuyRatioIndex = 0, maxSellRatioIndex = 0;
            for (int j = 0; j < players.size(); j++){
                float buyRatioSum = 0, sellRatioSum = 0;
                for (int k = 0; k < players.get(j).playerDays.size(); k++){
                    buyRatioSum += players.get(j).playerDays.get(k).buyRatio;
                    sellRatioSum += players.get(j).playerDays.get(k).sellRatio;
                }
                if(!buyRatioIndexSorted.contains(j) && buyRatioSum > maxBuyRatioSum){
                    maxBuyRatioSum = buyRatioSum;
                    maxBuyRatioIndex = j;
                }
                if(!sellRatioIndexSorted.contains(j) && sellRatioSum > maxSellRatioSum){
                    maxSellRatioSum = sellRatioSum;
                    maxSellRatioIndex = j;
                }
            }
            buyRatioIndexSorted.add(maxBuyRatioIndex);
            sellRatioIndexSorted.add(maxSellRatioIndex);
        }
        boolean send = false;
        String toSend = "---خرید---\n";
        for (int i = 0; i < buyRatioIndexSorted.size(); i++){
            int playerIndex = buyRatioIndexSorted.get(i);
            int symbolIndex = symbolNickName.indexOf(players.get(playerIndex).shareName);
            float buyRatioSum = 0, sellRatioSum = 0;
            int rbs = 0, lbs = 0, rss = 0, lss = 0;
            for (int j = 0; j < players.get(playerIndex).playerDays.size(); j++){
                buyRatioSum += players.get(playerIndex).playerDays.get(j).buyRatio;
                sellRatioSum += players.get(playerIndex).playerDays.get(j).sellRatio;
                rbs += players.get(playerIndex).playerDays.get(j).realBuy;
                lbs += players.get(playerIndex).playerDays.get(j).legalBuy;
                rss += players.get(playerIndex).playerDays.get(j).realSell;
                lss += players.get(playerIndex).playerDays.get(j).legalSell;
            }
            double marketCap = 0;
            if(symbolIndex >= 0)
                marketCap = (double)totalVolume.get(symbolIndex) * (double)closingPrice2.get(symbolIndex) / 10000000000000D; // HMT
            else{
                int tempI = players.get(playerIndex).playerDays.size() - 1;
                marketCap = players.get(playerIndex).playerDays.get(tempI).marketCap;
            }
            if(type.equals("") && buyRatioSum > 0.3 || type.equals("_2") && buyRatioSum > 1){
                toSend += players.get(playerIndex).shareName + " "
                        + String.format("%.1f", marketCap) + " "
                        + String.format("%.2f", (float)buyRatioSum) + " "
                        + String.format("%.2f", (float)sellRatioSum) + " "
                        + String.format("%.1f", (float)rbs / 1000F) + " "
                        + String.format("%.1f", (float)lbs / 1000F) + " "
                        + String.format("%.1f", (float)rss / 1000F) + " "
                        + String.format("%.1f", (float)lss / 1000F) + "\n";
                send = true;
            }
        }
        toSend += "---فروش---\n";
        for (int i = 0; i < sellRatioIndexSorted.size(); i++){
            int playerIndex = sellRatioIndexSorted.get(i);
            int symbolIndex = symbolNickName.indexOf(players.get(playerIndex).shareName);
            float buyRatioSum = 0, sellRatioSum = 0;
            int rbs = 0, lbs = 0, rss = 0, lss = 0;
            for (int j = 0; j < players.get(playerIndex).playerDays.size(); j++){
                buyRatioSum += players.get(playerIndex).playerDays.get(j).buyRatio;
                sellRatioSum += players.get(playerIndex).playerDays.get(j).sellRatio;
                rbs += players.get(playerIndex).playerDays.get(j).realBuy;
                lbs += players.get(playerIndex).playerDays.get(j).legalBuy;
                rss += players.get(playerIndex).playerDays.get(j).realSell;
                lss += players.get(playerIndex).playerDays.get(j).legalSell;
            }
            double marketCap = 0;
            if(symbolIndex >= 0)
                marketCap = (double)totalVolume.get(symbolIndex) * (double)closingPrice2.get(symbolIndex) / 10000000000000D; // HMT
            else{
                int tempI = players.get(playerIndex).playerDays.size() - 1;
                marketCap = players.get(playerIndex).playerDays.get(tempI).marketCap;
            }
            if(type.equals("") && sellRatioSum > 0.3 || type.equals("_2") && sellRatioSum > 1){
                toSend += players.get(playerIndex).shareName + " "
                        + String.format("%.1f", marketCap) + " "
                        + String.format("%.2f", (float)buyRatioSum) + " "
                        + String.format("%.2f", (float)sellRatioSum) + " "
                        + String.format("%.1f", (float)rbs / 1000F) + " "
                        + String.format("%.1f", (float)lbs / 1000F) + " "
                        + String.format("%.1f", (float)rss / 1000F) + " "
                        + String.format("%.1f", (float)lss / 1000F) + "\n";
                send = true;
            }
        }
        if(send)
            sendLongMessage(toSend, ID.PLAYERS, true);
    }


    public String getSymbolIDByNickName(String symbolNickName){
        String result;
        int resultLength;
        result = httpTask2(5,urlSearch + symbolNickName, 50, 3);
        resultLength = result.length();

        String temp;
        int j = 0;
        for (; j < resultLength; j++) {
            temp = "";
            for (; j < resultLength; j++) {
                if (result.charAt(j) == ',') {
                    j++;
                    break;
                }
                else if(result.charAt(j) == ' ')
                    temp += '_';
                else if(result.charAt(j) == 'ك')
                    temp += 'ک';
                else if(result.charAt(j) == 'ي')
                    temp += 'ی';
                else
                    temp += result.charAt(j);
            }
            if(temp.equals(symbolNickName)){
                for (; j < resultLength; j++) {
                    if (result.charAt(j) == ',') {
                        j++;
                        break;
                    }
                }
                temp = "";
                for (; j < resultLength; j++) {
                    if (result.charAt(j) == ',') {
                        j++;
                        break;
                    }
                    temp += result.charAt(j);
                }
                int tempcnt = 0;
                for (; j < resultLength; j++) {
                    if (result.charAt(j) == ',') {
                        tempcnt++;
                    }
                    if (tempcnt == 4) {
                        j++;
                        break;
                    }
                }
                if (result.charAt(j) == '1'){
                    return temp;
                }
            }
            else{
                for (; j < resultLength; j++) {
                    if (result.charAt(j) == ';') {
                        j++;
                        break;
                    }
                }
            }
        }

        return "";
    }
    public int getAllowedMinPrice(String symbolID){
        String result = "";
        result = httpTask2(5, urlTablo + symbolID, 500, 3);
        int resultLength = result.length();
        String temp;
        int j = 0;
        for (; j < resultLength; j++) {
            if (j + 12 < resultLength && result.substring(j, j + 12).equals("PSGelStaMin=")) {
                j += 13;
                break;
            }
        }
        temp = "";
        for (; j < resultLength; j++) {
            if (result.charAt(j) == '.') {
                break;
            }
            if(result.charAt(j) >= '0' && result.charAt(j) <= '9')
                temp += result.charAt(j);
            else{
                temp = "";
                break;
            }
        }
        return temp.equals("") ? 0:Integer.valueOf(temp);
    }
    public String formattedDate(String date){
        if(date.length() == 8)
            return (date.substring(0,4) + "/" + date.substring(4,6) + "/" + date.substring(6,8));
        else
            return date;
    }
    public String changeGregorianToPersian(String gregorianStr){
        int gregorian = Integer.valueOf(gregorianStr);
        int tempYear = gregorian / 10000;
        int tempMonth = (gregorian % 10000) / 100;
        int tempDay = gregorian % 100;
        int persianYear = 0, persianMonth = 0, persianDay = 0;
        if(tempYear % 4 == 0){
            if((gregorian % 10000) >= 320)
                persianYear = tempYear - 621;
            else
                persianYear = tempYear - 622;
            if(tempMonth == 1 && tempDay <= 20 || tempMonth == 2 && tempDay <= 19 || tempMonth == 3 && tempDay <= 19 ||
                    tempMonth == 4 && tempDay <= 19 || tempMonth == 5 && tempDay <= 20 || tempMonth == 6 && tempDay <= 20 ||
                    tempMonth == 7 && tempDay <= 21 || tempMonth == 8 && tempDay <= 21 || tempMonth == 9 && tempDay <= 21 ||
                    tempMonth == 10 && tempDay <= 21 || tempMonth == 11 && tempDay <= 20 || tempMonth == 12 && tempDay <= 20){
                persianMonth = ((tempMonth + 8) % 12) + 1;
                persianDay = tempDay + 10; // 10-11-10-12-11-11-10-10-10-9-10-10
                if(tempMonth == 10)
                    persianDay = tempDay + 9;
                else if(tempMonth == 4)
                    persianDay = tempDay + 12;
                else if(tempMonth == 2 || tempMonth == 5 || tempMonth == 6)
                    persianDay = tempDay + 11;
            }
            else {
                persianMonth = ((tempMonth + 9) % 12) + 1;
                persianDay = tempDay - 20; // 20-19-19-19-20-20-21-21-21-21-20-20
                if(tempMonth == 2 || tempMonth == 3 || tempMonth == 4)
                    persianDay = tempDay - 19;
                else if(tempMonth == 7 || tempMonth == 8 || tempMonth == 9 || tempMonth == 10)
                    persianDay = tempDay - 21;
            }
        }
        else if(tempYear % 4 == 1){
            if((gregorian % 10000) >= 321)
                persianYear = tempYear - 621;
            else
                persianYear = tempYear - 622;

            if(tempMonth == 1 && tempDay <= 19 || tempMonth == 2 && tempDay <= 18 || tempMonth == 3 && tempDay <= 20 ||
                    tempMonth == 4 && tempDay <= 20 || tempMonth == 5 && tempDay <= 21 || tempMonth == 6 && tempDay <= 21 ||
                    tempMonth == 7 && tempDay <= 22 || tempMonth == 8 && tempDay <= 22 || tempMonth == 9 && tempDay <= 22 ||
                    tempMonth == 10 && tempDay <= 22 || tempMonth == 11 && tempDay <= 21 || tempMonth == 12 && tempDay <= 21){
                persianMonth = ((tempMonth + 8) % 12) + 1;
                persianDay = tempDay + 9; // 11-12-10-11-10-10-9-9-9-8-9-9
                if(tempMonth == 10)
                    persianDay = tempDay + 8;
                else if(tempMonth == 3 || tempMonth == 5 || tempMonth == 6)
                    persianDay = tempDay + 10;
                else if(tempMonth == 1 || tempMonth == 4)
                    persianDay = tempDay + 11;
                else if(tempMonth == 2)
                    persianDay = tempDay + 12;

            }
            else {
                persianMonth = ((tempMonth + 9) % 12) + 1;
                persianDay = tempDay - 22; // 19-18-20-20-21-21-22-22-22-22-21-21
                if(tempMonth == 2)
                    persianDay = tempDay - 18;
                else if(tempMonth == 1)
                    persianDay = tempDay - 19;
                else if(tempMonth == 3 || tempMonth == 4)
                    persianDay = tempDay - 20;
                else if(tempMonth == 5 || tempMonth == 6 || tempMonth == 11 || tempMonth == 12)
                    persianDay = tempDay - 21;
            }
        }
        else {
            if((gregorian % 10000) >= 321)
                persianYear = tempYear - 621;
            else
                persianYear = tempYear - 622;

            if(tempMonth == 1 && tempDay <= 20 || tempMonth == 2 && tempDay <= 19 || tempMonth == 3 && tempDay <= 20 ||
                    tempMonth == 4 && tempDay <= 20 || tempMonth == 5 && tempDay <= 21 || tempMonth == 6 && tempDay <= 21 ||
                    tempMonth == 7 && tempDay <= 22 || tempMonth == 8 && tempDay <= 22 || tempMonth == 9 && tempDay <= 22 ||
                    tempMonth == 10 && tempDay <= 22 || tempMonth == 11 && tempDay <= 21 || tempMonth == 12 && tempDay <= 21){
                persianMonth = ((tempMonth + 8) % 12) + 1;
                persianDay = tempDay + 9; // 10-11-9-11-10-10-9-9-9-8-9-9
                if(tempMonth == 10)
                    persianDay = tempDay + 8;
                else if(tempMonth == 1 || tempMonth == 5 || tempMonth == 6)
                    persianDay = tempDay + 10;
                else if(tempMonth == 2 || tempMonth == 4)
                    persianDay = tempDay + 11;

            }
            else {
                persianMonth = ((tempMonth + 9) % 12) + 1;
                persianDay = tempDay - 22; // 20-19-20-20-21-21-22-22-22-22-21-21
                if(tempMonth == 2)
                    persianDay = tempDay - 19;
                else if(tempMonth == 1 || tempMonth == 3 || tempMonth == 4)
                    persianDay = tempDay - 20;
                else if(tempMonth == 5 || tempMonth == 6 || tempMonth == 11 || tempMonth == 12)
                    persianDay = tempDay - 21;
            }
        }
        return String.valueOf(persianYear * 10000 + persianMonth * 100 + persianDay);
    }
    private int gregorianCalendarMinusOne(int baseDate){
        int year = baseDate / 10000;
        int month = (baseDate % 10000) / 100;
        int day = baseDate % 100;
        if(day == 1){
            if(month == 1){
                year--;
                month = 12;
            }
            else {


                month--;
            }
            if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
                day = 31;
            else if(month == 4 || month == 6 || month == 9 || month == 11)
                day = 30;
            else if(month == 2 && year % 4 == 0)
                day = 29;
            else if(month == 2 && year % 4 != 0)
                day = 28;
        }
        else {
            day--;
        }
        return (year * 10000 + month * 100 + day);
    }
    private int persianCalendarDiff(int date1, int date2){
        int year1 = date1 / 10000;
        int month1 = (date1 % 10000) / 100;
        int day1 = date1 % 100;
        int year2 = date2 / 10000;
        int month2 = (date2 % 10000) / 100;
        int day2 = date2 % 100;
        int k1 = (month1 <= 6) ? 31:30;
        int k2 = (month2 <= 6) ? 31:30;
        return (day1 - day2 + (7 - month2) * k2 + (month1 - 7) * k1 + (year1 - year2) * 365);
    }
    private int lastWorkDayGreg(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int todayGreg = calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);
        while (true){
            todayGreg = gregorianCalendarMinusOne(todayGreg);
            calendar.set(todayGreg / 10000, ((todayGreg % 10000) / 100) - 1, todayGreg % 100);
            if(!isOffDay(calendar) && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY
                    && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY)
                break;
        }
        return todayGreg;
    }

    private boolean isLastWorkDay(int enterDateGreg){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int todayGreg = calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);
        while (true){
            todayGreg = gregorianCalendarMinusOne(todayGreg);
            calendar.set(todayGreg / 10000, ((todayGreg % 10000) / 100) - 1, todayGreg % 100);
            if(!isOffDay(calendar) && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY
                    && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY)
                break;
        }
        while (true){
            enterDateGreg = gregorianCalendarMinusOne(enterDateGreg);
            calendar.set(enterDateGreg / 10000, ((enterDateGreg % 10000) / 100) - 1, enterDateGreg % 100);
            if(!isOffDay(calendar) && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY
                    && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY)
                break;
        }
        if(todayGreg == enterDateGreg)
            return true;
        else
            return false;
    }
    private boolean isWorkDayBetween(int lastDay1, int lastDay2){

        Calendar calendar = Calendar.getInstance();
        while (true){
            lastDay1 = gregorianCalendarMinusOne(lastDay1);
            calendar.set(lastDay1 / 10000, ((lastDay1 % 10000) / 100) - 1, lastDay1 % 100);
            if(!isOffDay(calendar) && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY
                    && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY)
                break;
        }
        if(lastDay1 == lastDay2)
            return false;
        else
            return true;
    }
    /*
    private int persianCalendarPlusMinus(int baseDate, int offsetDays){
        int year = baseDate / 10000;
        int month = (baseDate % 10000) / 100;
        int day = baseDate % 100;
        if(offsetDays < 0) {
            while (offsetDays < 0){
                if(day == 1){
                    if(month == 1){
                        year--;
                        month = 12;
                        if(year % 4 == 3)
                            day = 30;
                        else
                            day = 29;
                    }
                    else {
                        month--;
                        if(month <= 6)
                            day = 31;
                        else if(month <= 11)
                            day = 30;
                    }
                    offsetDays++;
                }
                else {
                    if(day > (-offsetDays)){
                        day += offsetDays;
                        offsetDays = 0;
                    }
                    else {
                        offsetDays += (day - 1);
                        day = 1;
                    }
                }
            }

        }
        else if(offsetDays > 0){
            while (offsetDays > 0){
                if(year % 4 == 3 && month == 12 && day == 30 || year % 4 != 3 && month == 12 && day == 29){
                    year++;
                    month = 1;
                    day = 1;
                    offsetDays--;
                }
                else if(month <= 6 && day == 31 || month <= 11 && month >= 7 && day == 30){
                    month++;
                    day = 1;
                    offsetDays--;
                }
                else{
                    if(month <= 6 && (day + offsetDays) > 31){
                        offsetDays -= (31 - day);
                        day = 31;
                    }
                    else if((month <= 11 && month >= 7 || month == 12 && year % 4 == 3) && (day + offsetDays) > 30){
                        offsetDays -= (30 - day);
                        day = 30;
                    }
                    else if(month == 12 && year % 4 != 3 && (day + offsetDays) > 29 ){
                        offsetDays -= (29 - day);
                        day = 29;
                    }
                    else {
                        day += offsetDays;
                        offsetDays = 0;
                    }
                }
            }
        }
        return year * 10000 + month * 100 + day;
    }
    */
    private Message sendLongMessage(String toSend, String chatId, boolean waitForTime) throws TelegramApiException, InterruptedException{
        Message msg = new Message();
        boolean returnNull = true;
        if(toSend.length() > 0) {
            SendMessage message = new SendMessage().setChatId(chatId);
            if(toSend.contains("<a href"))
                message.setParseMode(ParseMode.HTML);
            for (int i = 1; i <= 3; i++) {
                String toSendTemp;
                if (toSend.length() > (i * 4000))
                    toSendTemp = toSend.substring((i - 1) * 4000, i * 4000);
                else{
                    toSendTemp = toSend.substring((i - 1) * 4000, toSend.length());
                    i = 3;
                }
                if(toSendTemp.charAt(toSendTemp.length() - 1) == '\n')
                    toSendTemp += ID.TABLOBOT_CHANNEL;
                else
                    toSendTemp += "\n" + ID.TABLOBOT_CHANNEL;
                message.setText(toSendTemp);
                if(chatId.equals(ID.PUBLIC_CHANNEL) || chatId.equals(ID.FARDIN) || chatId.equals(ID.PRIVATE_SHAREHOLDERS) ||
                        chatId.equals(ID.PUBLIC_CHANNEL2) || chatId.equals(ID.PRIVATE_CODETOCODE) || chatId.equals(ID.PRIVATE_LARGE_BUY)
                        || chatId.equals(ID.PLAYERS)) {
                    msg = customSendMessage(1, message, null, null, chatId, waitForTime);
                    if (msg != null)
                        returnNull = false;
                }
                else{
                    message.setChatId(chatId);
                    msg = execute(message);
                    returnNull = false;
                }
            }
        }
        if(returnNull)
            return null;
        else
            return msg;
    }
    private Message customSendMessage(int messageType, SendMessage textMsg, SendPhoto photoMsg, SendDocument docMsg,
                                   String chatId, boolean waitForTime)
            throws TelegramApiException, InterruptedException{
        Message msg = new Message();
        boolean returnNull = true;
        while (true) {
            LocalTime timeNow = LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS));
            int secondsNow = timeNow.getHour() * 3600 + timeNow.getMinute() * 60 + timeNow.getSecond();
            if (chatId.equals(ID.FARDIN) && (secondsNow - fardinSendTime[fsti]) > 65 ||
                    chatId.equals(ID.PUBLIC_CHANNEL) && (secondsNow - publicSendTime[psti]) > 65 ||
                    chatId.equals(ID.PUBLIC_CHANNEL2) && (secondsNow - public2SendTime[p2sti]) > 65 ||
                    chatId.equals(ID.PRIVATE_SHAREHOLDERS) && (secondsNow - shareholdersSendTime[shsti]) > 65 ||
                    (chatId.equals(ID.PRIVATE_LARGE_BUY) || chatId.equals(ID.PRIVATE_CODETOCODE) || chatId.equals(ID.PLAYERS)
                            || chatId.equals(ID.MAFIA_CHANNEL))
                            && (secondsNow - otherPrivateSendTime[opsti]) > 65) {
                if(messageType == 1){
                    textMsg.setChatId(chatId);
                    msg = execute(textMsg);
                    returnNull = false;
                }
                else if(messageType == 2){
                    photoMsg.setChatId(chatId);
                    msg = execute(photoMsg);
                    returnNull = false;
                }
                else if(messageType == 3){
                    docMsg.setChatId(chatId);
                    msg = execute(docMsg);
                    returnNull = false;
                }
                if (chatId.equals(ID.FARDIN)) {
                    fardinSendTime[fsti] = secondsNow;
                    fsti = (fsti + 1) % 20;
                }
                else if (chatId.equals(ID.PUBLIC_CHANNEL)) {
                    publicSendTime[psti] = secondsNow;
                    psti = (psti + 1) % 20;
                }
                else if (chatId.equals(ID.PUBLIC_CHANNEL2)) {
                    public2SendTime[p2sti] = secondsNow;
                    p2sti = (p2sti + 1) % 20;
                }
                else if (chatId.equals(ID.PRIVATE_SHAREHOLDERS)) {
                    shareholdersSendTime[shsti] = secondsNow;
                    shsti = (shsti + 1) % 20;
                }
                else if(chatId.equals(ID.PRIVATE_LARGE_BUY) || chatId.equals(ID.PRIVATE_CODETOCODE) || chatId.equals(ID.PLAYERS)){
                    otherPrivateSendTime[opsti] = secondsNow;
                    opsti = (opsti + 1) % 20;
                }
                break;
            }
            else if(waitForTime)
                Thread.sleep(3000);
            else {
                if(messageType == 1){
                    textMsg.setChatId(chatId);
                    msg = execute(textMsg);
                    returnNull = false;
                }
                else if(messageType == 2){
                    photoMsg.setChatId(chatId);
                    msg = execute(photoMsg);
                    returnNull = false;
                }
                else if(messageType == 3){
                    docMsg.setChatId(chatId);
                    msg = execute(docMsg);
                    returnNull = false;
                }
                break;
            }
        }
        if(returnNull)
            return null;
        else
            return msg;
    }

    public void shareHoldersAllRecords(int dateFrom, int dateTo, int serverNumbers, int serverIndex) throws IOException, InterruptedException{
        httpClients.clear();
        httpResponses.clear();
        httpGets.clear();
        for (int i = 0; i < 10; i++) {
            httpClients.add(HttpClients.createDefault());
            httpResponses.add(null);
            httpGets.add(null);
        }

        ArrayList<String> symbolID2 = new ArrayList<>();
        ArrayList<String> symbolNickName2 = new ArrayList<>();
        Scanner sc = new Scanner(new FileInputStream("nick_id.txt"));
        while (sc.hasNext()) {
            symbolNickName2.add(sc.next());
            symbolID2.add(sc.next());
        }
        sc.close();

        ArrayList<Thread> threads = new ArrayList<>();
        int threadNumbers = 4;
        int symbolID2Size = symbolID2.size();
        for(int t = 0; t < threadNumbers; t++) {
            final int t2 = t;
            Runnable task1 = () -> {
                float fromItemp = ((float) symbolID2Size /(float)(serverNumbers * threadNumbers)) * (float)(serverIndex * threadNumbers + t2);
                float toItemp = ((float)symbolID2Size /(float)(serverNumbers * threadNumbers)) * (float)(serverIndex * threadNumbers + t2 + 1);
                int fromI = (int) Math.ceil(fromItemp);
                int toI = (int) Math.ceil(toItemp);
                System.out.println("s" + serverIndex + " t" + t2 + " from: " + fromI + " to: " + (toI - 1));
                int sumday = 0;
                for (int k = fromI; k < toI; k++) {
                    ArrayList<String> days = new ArrayList<>();
                    ArrayList<String> tempdays = new ArrayList<>();
                    ArrayList<Integer> exQ = new ArrayList<>();
                    int daysSize = 0;
                    String result;
                    int resultLength;
                    long[] tempLongArray;
                    for(int l = 0; l < 3; l++) {
                        days = new ArrayList<>();
                        tempdays = new ArrayList<>();
                        daysSize = 0;
                        result = httpTask2(t2, urlTradeHistory + symbolID2.get(k) + urlTradeHistoryPF, 200, 3);
                        if (result.length() > 2 && !result.substring(0, 2).equals("20"))
                            continue;
                        resultLength = result.length();
                        for (int i = 0; i < resultLength; i++) {
                            tempLongArray = stringNextLong(result, '@', i);
                            i = (int) tempLongArray[1];
                            int day = (int) tempLongArray[0];
                            if (day > 20000000 && day <= 20250000)
                                tempdays.add(String.valueOf(day));
                            if (day > dateFrom && day <= dateTo) // 20200320
                                days.add(String.valueOf(day));
//                          else
//                              System.out.print(days.size() + ":" + day + " ");

                            i = stringCountChar(result, '@', 8, i);
                            tempLongArray = stringNextLong(result, ';', i);
                            i = (int)tempLongArray[1] - 1;
                            if (day > dateFrom && day <= dateTo)
                                exQ.add((int)tempLongArray[0]);
                        }
                        daysSize = days.size();
                        sumday += daysSize;
                        if(tempdays.size() > 0) {
//                            try {
//                                if(daysSize > 0) {
//                                    String fileName = "nick_id" + String.valueOf(dateFrom) + String.valueOf(dateTo) + ".txt";
//                                    FileWriter fileWriter = new FileWriter(fileName, true);
//                                    fileWriter.write(symbolNickName2.get(k) + " " + symbolID2.get(k) + "\n");
//                                    fileWriter.close();
//                                }
//                            }
//                            catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                            System.out.println("\n" + k + " " + symbolNickName2.get(k) + " s" + serverIndex + " t" + t2 + " days:" + daysSize);
                            System.out.println(symbolNickName2.get(k) + " " + symbolID2.get(k) + " " + daysSize + " " + sumday);
                            break;
                        }
//                        try {
//                            Thread.sleep(1000);
//                        }
//                        catch (InterruptedException e){
//                            e.printStackTrace();
//                        }
                    }
                    if(tempdays.size() == 0)
                        System.out.println("\nERROR " + symbolNickName2.get(k) + " " + symbolID2.get(k));

                    System.out.println("START " + symbolNickName2.get(k));
                    ArrayList<String> yesterdayIds, yesterdayNames, todayIds, todayNames, tomorrowIds, tomorrowNames;
                    tomorrowIds = new ArrayList<>();
                    tomorrowNames = new ArrayList<>();
                    ArrayList<Integer> l0 = new ArrayList<>();
                    for (int j = 0; j < daysSize; j++) {
                        if(exQ.get(j) == 0)
                            continue;
                        if(j % 10 == 0)
                            System.out.print(k + "_" + j + " ");
//                        System.out.println(((t2 + 1) * 1000000 + j) + " " + LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS)).toString().substring(0, 8)  + ";");
                        result = httpTask2(t2, urlShareholdersChange + "/" + symbolID2.get(k) + "/" + days.get(j),
                                200, 10);
                        if(result.equals("{\"shareShareholder\":[]}")){
                            continue;
                        }
                        resultLength = result.length();
                        if(resultLength == 0){
                            l0.add(j);
                            continue;
                        }
                        try {
                            FileWriter fileWriter = new FileWriter("sh/" + symbolNickName2.get(k), true);
                            fileWriter.write(result + "\n");
                            fileWriter.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        FileWriter fileWriter = new FileWriter("l0.txt", true);
                        String id = symbolID2.get(k);
                        for(int j = 0; j < l0.size(); j++)
                            fileWriter.write(id + " " + days.get(l0.get(j)) + "\n");
                        fileWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("\nDONE " + symbolNickName2.get(k));
                }
            };
            threads.add(new Thread(task1));
            threads.get(t).start();
        }
        for(int t = 0; t < threadNumbers; t++) {
            threads.get(t).join();
        }
    }
    public void shareHoldersAllRecords2() throws IOException, InterruptedException{
        httpClients.clear();
        httpResponses.clear();
        httpGets.clear();
        for (int i = 0; i < 4; i++) {
            httpClients.add(HttpClients.createDefault());
            httpResponses.add(null);
            httpGets.add(null);
        }

        ArrayList<String> symbolID2 = new ArrayList<>();
        ArrayList<String> symbolNickName2 = new ArrayList<>();
        Scanner sc = new Scanner(new FileInputStream("nick_id.txt"));
        while (sc.hasNext()) {
            symbolNickName2.add(sc.next());
            symbolID2.add(sc.next());
        }
        sc.close();

        ArrayList<String> symbolNickName3 = new ArrayList<>();
        ArrayList<String> days2 = new ArrayList<>();

        sc = new Scanner(new FileInputStream("l0.txt"));
        while (sc.hasNext()) {
            symbolNickName3.add(sc.next());
            days2.add(sc.next());
        }
        sc.close();
        PrintWriter pw = new PrintWriter("l0a.txt");
        pw.print("");
        pw.close();

        ArrayList<Thread> threads = new ArrayList<>();
        int threadNumbers = 4;
        int days2Size = days2.size();
        for(int t = 0; t < threadNumbers; t++) {
            final int t2 = t;
            Runnable task1 = () -> {
                int fromI = (days2Size / threadNumbers) * t2;
                int toI = (days2Size / threadNumbers) * (t2 + 1);
                if(t2 == threadNumbers - 1)
                    toI = days2Size;
                System.out.println("t" + t2 + " from: " + fromI + " to: " + (toI - 1));
                for (int k = fromI; k < toI; k++) {
                    if(k % 10 == 0)
                        System.out.print(t2 + "_" + k + " ");
                    String result = "";
                    int resultLength;

                    ArrayList<String> todayIds, todayNames, yesterdayIds, yesterdayNames;
                    int index = symbolNickName2.indexOf(symbolNickName3.get(k));
                    if(index >= 0)
                        result = httpTask2(t2, urlShareholdersChange + "/" + symbolID2.get(index) + "/" + days2.get(k),
                                200, 10);
                    if(result.equals("{\"shareShareholder\":[]}"))
                        continue;
                    resultLength = result.length();
                    if(resultLength == 0){
                        try {
                            FileWriter fileWriter = new FileWriter("l0a.txt", true);
                            fileWriter.write(symbolNickName3.get(k) + " " + days2.get(k) + "\n");
                            fileWriter.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }
                    try {
                        FileWriter fileWriter = new FileWriter("sh/" + symbolNickName3.get(k), true);
                        fileWriter.write(result + "\n");
                        fileWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            threads.add(new Thread(task1));
            threads.get(t).start();
        }
        for(int t = 0; t < threadNumbers; t++) {
            threads.get(t).join();
        }
        pw = new PrintWriter("l0.txt");
        pw.print("");
        pw.close();
        FileWriter fileWriter = new FileWriter("l0.txt", true);
        sc = new Scanner(new FileInputStream("l0a.txt"));
        while (sc.hasNext()) {
            fileWriter.write(sc.nextLine() + "\n");
        }
        fileWriter.close();
        sc.close();
    }

    public void shareHoldersAllRecords3() throws IOException, InterruptedException{
        httpClients.clear();
        httpResponses.clear();
        httpGets.clear();
        for (int i = 0; i < 4; i++) {
            httpClients.add(HttpClients.createDefault());
            httpResponses.add(null);
            httpGets.add(null);
        }

        ArrayList<String> symbolID2 = new ArrayList<>();
        ArrayList<String> symbolNickName2 = new ArrayList<>();
        Scanner sc = new Scanner(new FileInputStream("nick_id.txt"));
        while (sc.hasNext()) {
            symbolNickName2.add(sc.next());
            symbolID2.add(sc.next());
        }
        sc.close();

        ArrayList<String> symbolNickName3 = new ArrayList<>();
        ArrayList<String> days3 = new ArrayList<>();
        ArrayList<Boolean> daysDone = new ArrayList<>();

        sc = new Scanner(new FileInputStream("size0.txt"));
        while (sc.hasNext()) {
            symbolNickName3.add(sc.next());
            days3.add(sc.next());
            daysDone.add(false);
        }
        sc.close();

        ArrayList<Thread> threads = new ArrayList<>();
        int threadNumbers = 4;
        int symbolID2Size = symbolID2.size();
        for(int t = 0; t < threadNumbers; t++) {
            final int t2 = t;
            Runnable task1 = () -> {
                float fromItemp = (float) (symbolID2Size * t2)/ (float)threadNumbers;
                float toItemp = (float) (symbolID2Size * (t2 + 1))/ (float)threadNumbers;
                int fromI = (int) Math.ceil(fromItemp);
                int toI = (int) Math.ceil(toItemp);
                System.out.println(" t" + t2 + " from: " + fromI + " to: " + (toI - 1));
                for (int k = fromI; k < toI; k++) {
                    if (!symbolNickName3.contains(symbolNickName2.get(k)))
                        continue;
                    ArrayList<String> days = new ArrayList<>();
                    int daysSize = 0;
                    String result;
                    int resultLength;
                    long[] tempLongArray;
                    for(int l = 0; l < 3; l++) {
                        days = new ArrayList<>();
                        daysSize = 0;
                        result = httpTask2(t2, urlTradeHistory + symbolID2.get(k) + urlTradeHistoryPF, 200, 3);
                        if (result.length() > 2 && !result.substring(0, 2).equals("20"))
                            continue;
                        resultLength = result.length();
                        for (int i = 0; i < resultLength; i++) {
                            tempLongArray = stringNextLong(result, '@', i);
                            i = (int) tempLongArray[1];
                            int day = (int) tempLongArray[0];
                            if (day > 20000000 && day <= 20200320)
                                days.add(String.valueOf(day));
//                          else
//                              System.out.print(days.size() + ":" + day + " ");

                            i = stringCountChar(result, '@', 8, i);
                            tempLongArray = stringNextLong(result, ';', i);
                            i = (int)tempLongArray[1] - 1;
                        }
                        daysSize = days.size();
                        if(daysSize > 0) {
                            break;
                        }
                    }
                    if(days.size() == 0)
                        System.out.println("\nERROR " + symbolNickName2.get(k) + " " + symbolID2.get(k));

                    System.out.println("START " + symbolNickName2.get(k));
                    ArrayList<String> yesterdayIds1, yesterdayNames1, todayIds1, todayNames1,
                            yesterdayIds2, yesterdayNames2, todayIds2, todayNames2;
                    for (int j = 0; j < daysSize; j++) {
                        int m = 0;
                        for (; m < days3.size(); m++){
                            if(symbolNickName3.get(m).equals(symbolNickName2.get(k)) &&
                                    (days3.get(m).equals(changeGregorianToPersian(days.get(j))) || days3.get(m).equals(days.get(j))))
                                break;
                        }
                        if(m < days3.size()){
                            if(j == 0){
                                System.out.println("j0 " + symbolNickName2.get(k) + " " + changeGregorianToPersian(days.get(j)));
                                continue;
                            }
                            result = httpTask2(t2, urlShareholdersChange + "/" + symbolID2.get(k) + "/" + days.get(j),
                                    200, 10);
                            resultLength = result.length();
                            if(resultLength == 0){
                                try {
                                    FileWriter fileWriter = new FileWriter("l0.txt", true);
                                    fileWriter.write(symbolNickName2.get(k) + " " + days.get(j) + "\n");
                                    fileWriter.close();
                                }
                                catch (IOException e) {
                                    e.printStackTrace();
                                }
                                continue;
                            }
                            int cdnIndex = result.indexOf("cdn");
                            char cdn = 'x';
                            if(cdnIndex >= 0)
                                cdn = result.charAt(cdnIndex + 3);
                            if(cdn != '7' && cdn != '8'){
                                try {
                                    FileWriter fileWriter = new FileWriter("cdn.txt", true);
                                    fileWriter.write("cdn" + cdn + " " + symbolNickName2.get(k) + " " + days.get(j) + "\n");
                                    fileWriter.close();
                                }
                                catch (IOException e) {
                                    e.printStackTrace();
                                }
                                continue;
                            }
                            String[] tempStringArray;
                            todayIds1 = new ArrayList<>();
                            todayNames1 = new ArrayList<>();
                            yesterdayIds1 = new ArrayList<>();
                            yesterdayNames1 = new ArrayList<>();
                            for (int i = 0; i < resultLength; i++) {
                                if (result.substring(i, i + 16).equals("ShareHolderData=")) {
                                    i += 17;
                                    String id = "", name = "";
                                    for (; i < resultLength; i++) {
                                        if (result.charAt(i) == ';')
                                            break;
                                        if (result.charAt(i) == '[') {
                                            tempStringArray = stringNextString(result, ',', i + 1);
                                            i = Integer.valueOf(tempStringArray[1]);
                                            id = tempStringArray[0];
                                            i = stringCountChar(result, '\'', 5, i);
                                            tempStringArray = stringNextString(result, '\'', i);
                                            i = Integer.valueOf(tempStringArray[1]);
                                            name = tempStringArray[0];
                                            if(!todayIds1.contains(id)){
                                                todayIds1.add(id);
                                                todayNames1.add(name);
                                            }
                                        }
                                    }
                                }
                                if (result.substring(i, i + 25).equals("ShareHolderDataYesterday=")) {
                                    i += 26;
                                    String id = "", name = "";
                                    for (; i < resultLength; i++) {
                                        if (result.charAt(i) == ';')
                                            break;
                                        if (result.charAt(i) == '[') {
                                            tempStringArray = stringNextString(result, ',', i + 1);
                                            i = Integer.valueOf(tempStringArray[1]);
                                            id = tempStringArray[0];
                                            i = stringCountChar(result, '\'', 5, i);
                                            tempStringArray = stringNextString(result, '\'', i);
                                            i = Integer.valueOf(tempStringArray[1]);
                                            name = tempStringArray[0];
                                            if(!yesterdayIds1.contains(id)){
                                                yesterdayIds1.add(id);
                                                yesterdayNames1.add(name);
                                            }
                                        }
                                    }
                                    break;
                                }
                            }
                            boolean toYesterday = todayIds1.size() > 0 && yesterdayIds1.size() == 0;
                            boolean toTomorrow = todayIds1.size() == 0 && yesterdayIds1.size() > 0;
                            if(toYesterday || toTomorrow){
                                int l = toYesterday ? j + 1:j - 1;
                                while(true){
                                    if(toYesterday && l == daysSize || toTomorrow && l == -1)
                                        break;

                                    result = httpTask2(t2, urlShareholdersChange + "/" + symbolID2.get(k) + "/" + days.get(l),
                                            200, 10);
                                    resultLength = result.length();
                                    if(resultLength == 0){
                                        continue;
                                    }
                                    cdnIndex = result.indexOf("cdn");
                                    cdn = 'x';
                                    if(cdnIndex >= 0)
                                        cdn = result.charAt(cdnIndex + 3);
                                    if(cdn != '7' && cdn != '8'){
                                        continue;
                                    }
                                    todayIds2 = new ArrayList<>();
                                    todayNames2 = new ArrayList<>();
                                    yesterdayIds2 = new ArrayList<>();
                                    yesterdayNames2 = new ArrayList<>();
                                    for (int i = 0; i < resultLength; i++) {
                                        if (result.substring(i, i + 16).equals("ShareHolderData=")) {
                                            i += 17;
                                            String id = "", name = "";
                                            for (; i < resultLength; i++) {
                                                if (result.charAt(i) == ';')
                                                    break;
                                                if (result.charAt(i) == '[') {
                                                    tempStringArray = stringNextString(result, ',', i + 1);
                                                    i = Integer.valueOf(tempStringArray[1]);
                                                    id = tempStringArray[0];
                                                    i = stringCountChar(result, '\'', 5, i);
                                                    tempStringArray = stringNextString(result, '\'', i);
                                                    i = Integer.valueOf(tempStringArray[1]);
                                                    name = tempStringArray[0];
                                                    if(!todayIds2.contains(id)){
                                                        todayIds2.add(id);
                                                        todayNames2.add(name);
                                                    }
                                                }
                                            }
                                        }
                                        if (result.substring(i, i + 25).equals("ShareHolderDataYesterday=")) {
                                            i += 26;
                                            String id = "", name = "";
                                            for (; i < resultLength; i++) {
                                                if (result.charAt(i) == ';')
                                                    break;
                                                if (result.charAt(i) == '[') {
                                                    tempStringArray = stringNextString(result, ',', i + 1);
                                                    i = Integer.valueOf(tempStringArray[1]);
                                                    id = tempStringArray[0];
                                                    i = stringCountChar(result, '\'', 5, i);
                                                    tempStringArray = stringNextString(result, '\'', i);
                                                    i = Integer.valueOf(tempStringArray[1]);
                                                    name = tempStringArray[0];
                                                    if(!yesterdayIds2.contains(id)){
                                                        yesterdayIds2.add(id);
                                                        yesterdayNames2.add(name);
                                                    }
                                                }
                                            }
                                            break;
                                        }
                                    }
                                    if(toYesterday && yesterdayIds2.size() > 0){
                                        for (int i = 0; i < todayIds1.size(); i++) {
                                            if (!yesterdayIds2.contains(todayIds1.get(i))) {
                                                try {
                                                    FileWriter fileWriter = new FileWriter("sh/" + symbolNickName2.get(k), true);
                                                    fileWriter.write("ورود," + todayIds1.get(i) + "," + todayNames1.get(i) + "," + changeGregorianToPersian(days.get(l)) + "\n");
                                                    fileWriter.close();
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                        for (int i = 0; i < yesterdayIds2.size(); i++) {
                                            if (!todayIds1.contains(yesterdayIds2.get(i))) {
                                                try {
                                                    FileWriter fileWriter = new FileWriter("sh/" + symbolNickName2.get(k), true);
                                                    fileWriter.write("خروج," + yesterdayIds2.get(i) + "," + yesterdayNames2.get(i) + "," + changeGregorianToPersian(days.get(l)) + "\n");
                                                    fileWriter.close();
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }

                                            }
                                        }
                                        daysDone.set(m, true);
                                        break;
                                    }
                                    else if(toTomorrow && todayIds2.size() > 0){
                                        for (int i = 0; i < todayIds2.size(); i++) {
                                            if (!yesterdayIds1.contains(todayIds2.get(i))) {
                                                try {
                                                    FileWriter fileWriter = new FileWriter("sh/" + symbolNickName2.get(k), true);
                                                    fileWriter.write("ورود," + todayIds2.get(i) + "," + todayNames2.get(i) + "," + changeGregorianToPersian(days.get(j)) + "\n");
                                                    fileWriter.close();
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                        for (int i = 0; i < yesterdayIds1.size(); i++) {
                                            if (!todayIds2.contains(yesterdayIds1.get(i))) {
                                                try {
                                                    FileWriter fileWriter = new FileWriter("sh/" + symbolNickName2.get(k), true);
                                                    fileWriter.write("خروج," + yesterdayIds1.get(i) + "," + yesterdayNames1.get(i) + "," + changeGregorianToPersian(days.get(j)) + "\n");
                                                    fileWriter.close();
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }

                                            }
                                        }
                                        daysDone.set(m, true);
                                        break;
                                    }
                                    l = toYesterday ? l + 1:l - 1;
                                }
                            }
                        }
                    }
                }
            };
            threads.add(new Thread(task1));
            threads.get(t).start();
        }
        for(int t = 0; t < threadNumbers; t++) {
            threads.get(t).join();
        }
        for (int i = 0; i < daysDone.size(); i++){
            if(!daysDone.get(i))
                System.out.println(symbolNickName3.get(i) + " " + days3.get(i));
        }
    }
    public void deletedOldSymbols() throws IOException {
        httpClients.clear();
        httpResponses.clear();
        httpGets.clear();
        for (int i = 0; i < 10; i++) {
            httpClients.add(HttpClients.createDefault());
            httpResponses.add(null);
            httpGets.add(null);
        }
        ArrayList<String> symbolNickName2 = new ArrayList<>();
        Scanner sc = new Scanner(new FileInputStream("nick_id_temp.txt"));
        while (sc.hasNext())
            symbolNickName2.add(sc.next());
        sc.close();
        int symbolsNumber = symbolNickName2.size();
        for (int i = 0; i < symbolsNumber; i++) {
            String result = ";" + httpTask2(5, urlSearch + symbolNickName2.get(i), 50, 3);
            int resultLength = result.length();
            int repetetion = 0;
            ArrayList<String> symbolID2 = new ArrayList<>();
            for (int j = 0; j < resultLength - symbolNickName2.get(i).length() - 5; j++){
                if (result.substring(j, j + symbolNickName2.get(i).length() + 2).equals(";" + symbolNickName2.get(i) + ",")){
                    repetetion++;
                    j += symbolNickName2.get(i).length() + 2;
                    String[] tempStringArray;
                    tempStringArray = stringNextString(result, ',', j);
                    j = Integer.valueOf(tempStringArray[1]);
                    tempStringArray = stringNextString(result, ',', j);
                    symbolID2.add(tempStringArray[0]);
                }
            }
            if(repetetion > 1)
                for (int j = 0; j < symbolID2.size(); j++)
                    System.out.println(symbolNickName2.get(i) + " " + symbolID2.get(j));
        }
    }

    public void checkCapIncrease() throws IOException{
        int symbolsNumber = symbolID.size();
        int resultLength, j, date;
        String result;
        long[] tempLongArray;
        char c;
        FileWriter fw = new FileWriter("capinc");
        System.out.println(symbolsNumber);
        for (int i = 0; i < symbolsNumber; i++){
            System.out.print(i + " ");
            result = httpTask2(6, urlCapInc + symbolID.get(i), 200, 3);
            resultLength = result.length();
            ArrayList<Integer> dates = new ArrayList<>();
            ArrayList<Float> capIncs = new ArrayList<>();
            j = 0;
            while (true) {
                j = result.indexOf("<tr><td>", j);
                if (j == -1)
                    break;
                j += 8;
                tempLongArray = stringNextLong(result, '/', j);
                j = (int) tempLongArray[1];
                date = (int) (tempLongArray[0] * 10000);
                tempLongArray = stringNextLong(result, '/', j);
                j = (int) tempLongArray[1];
                date += (int) (tempLongArray[0] * 100);
                tempLongArray = stringNextLong(result, '<', j);
                j = (int) tempLongArray[1];
                date += (int) tempLongArray[0];
                String newCap = "", oldCap = "";
                j = result.indexOf("title=", j) + 7;
                for (; j < resultLength; j++) {
                    c = result.charAt(j);
                    if (c == '\"')
                        break;
                    if (c != ',')
                        newCap += c;
                }
                j = result.indexOf("title=", j) + 7;
                for (; j < resultLength; j++) {
                    c = result.charAt(j);
                    if (c == '\"')
                        break;
                    if (c != ',')
                        oldCap += c;
                }
                float tempCapInc = (float)(Double.valueOf(newCap) / Double.valueOf(oldCap));
                if(tempCapInc > 1) {
                    dates.add(date);
                    capIncs.add(tempCapInc);
                }
                else {
                    dates.remove(dates.size() - 1);
                    capIncs.remove(capIncs.size() - 1);
                }
            }
            if(dates.size() > 0){
                fw.write(symbolNickName.get(i) + " " + dates.size() + " ");
                for (j = 0; j < dates.size(); j++)
                    fw.write(dates.get(j) + " " + String.format("%.3f", capIncs.get(j)) + " ");
                fw.write("\n");
            }
        }
        fw.close();
    }
    public void instTradeHistory2() throws IOException{
        ArrayList<String> symbolID2 = new ArrayList<>();
        ArrayList<String> symbolNickName2 = new ArrayList<>();
        Scanner sc = new Scanner(new FileInputStream("nick_id.txt"));
        while (sc.hasNext()) {
            symbolNickName2.add(sc.next());
            symbolID2.add(sc.next());
        }
        sc.close();
        int symbolsNumber = symbolID2.size();
        for (int i = 0; i < symbolsNumber; i++){
            System.out.print(i + " ");
            String result = "";
            for (int k = 0; k < 5; k++){
                result = httpTask2(5,urlTradeHistory + symbolID2.get(i) + urlTradeHistoryPF, 200, 3);
                if(result.length() > 2 && result.substring(0, 2).equals("20"))
                    break;
            }
            int resultLength = result.length();
            if(resultLength > 2 && !result.substring(0, 2).equals("20"))
                continue;
            FileWriter fw = new FileWriter("priceHistory/" + symbolNickName2.get(i));
            long[] tempLongArray;
            int date, pmax, pmin, cp, pl, pf, py = -1, py2 = -1;
            float coeff = 1;
            for (int j = 0; j < resultLength; j++) {
                tempLongArray = stringNextLong(result, '@', j);
                j = (int)tempLongArray[1];
                date = (int)tempLongArray[0];
                if(date < 20081121)
                    break;
                tempLongArray = stringNextLong(result, '.', j);
                j = (int)tempLongArray[1] + 3;
//                pmax = (int)tempLongArray[0];
                tempLongArray = stringNextLong(result, '.', j);
                j = (int)tempLongArray[1] + 3;
//                pmin = (int)tempLongArray[0];
                tempLongArray = stringNextLong(result, '.', j);
                j = (int)tempLongArray[1] + 3;
                cp = (int)tempLongArray[0];
                tempLongArray = stringNextLong(result, '.', j);
                j = (int)tempLongArray[1] + 3;
//                pl = (int)tempLongArray[0];
                tempLongArray = stringNextLong(result, '.', j);
                j = (int)tempLongArray[1] + 3;
//                pf = (int)tempLongArray[0];
                tempLongArray = stringNextLong(result, '.', j);
                j = (int)tempLongArray[1] + 3;
                py = (int)tempLongArray[0];
                for(; j < resultLength; j++){
                    if(result.charAt(j) == ';')
                        break;
                }
                if(cp != py2 && py2 != -1)
                    coeff *= (float)cp / (float)py2;
//                pmax = (int)((float)pmax / coeff);
//                pmin = (int)((float)pmin / coeff);
                cp = (int)((float)cp / coeff);
//                pl = (int)((float)pl / coeff);
//                pf = (int)((float)pf / coeff);
                py2 = py;
                fw.write(changeGregorianToPersian(String.valueOf(date)) + " "  + cp + "\n");
            }
            fw.close();
        }
    }

    public void sendFullChart(String symbolNickName1, String symbolID1, String caption, String chatID){
        ArrayList<Integer> dateHist = new ArrayList<>();
        ArrayList<Integer> pmaxHist = new ArrayList<>();
        ArrayList<Integer> pminHist = new ArrayList<>();
        ArrayList<Integer> cpHist = new ArrayList<>();
        ArrayList<Integer> plHist = new ArrayList<>();
        ArrayList<Integer> pfHist = new ArrayList<>();
        ArrayList<Long> valHist = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int today = calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);
        int symbolIndex = symbolID.indexOf(symbolID1);
        if(symbolIndex >= 0) {
            if(exVolume2.get(symbolIndex) == 0){
                pmaxHist.add(buyPrice1.get(symbolIndex));
                pminHist.add(buyPrice1.get(symbolIndex));
                cpHist.add(yesterdayClosingPrice.get(symbolIndex));
                plHist.add(buyPrice1.get(symbolIndex));
                pfHist.add(buyPrice1.get(symbolIndex));
                valHist.add(0L);
            }
            else {
                pmaxHist.add(maxPrice2.get(symbolIndex));
                pminHist.add(minPrice2.get(symbolIndex));
                cpHist.add(closingPrice2.get(symbolIndex));
                plHist.add(lastPrice2.get(symbolIndex));
                pfHist.add(firstPrice.get(symbolIndex));
                valHist.add(exValue2.get(symbolIndex));
            }
            dateHist.add(Integer.valueOf(changeGregorianToPersian(String.valueOf(today))));
        }
        String result = "";
        String filePath = "priceHistory/" + symbolNickName1;
        File file = new File(filePath);
        if (file.exists()) {
            try {
                Scanner sc = new Scanner(new FileInputStream(filePath));
                while (sc.hasNext()) {
                    dateHist.add(sc.nextInt());
                    pmaxHist.add(sc.nextInt());
                    pminHist.add(sc.nextInt());
                    cpHist.add(sc.nextInt());
                    plHist.add(sc.nextInt());
                    pfHist.add(sc.nextInt());
                    valHist.add(sc.nextLong());
                }
                sc.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        else {
            for (int i = 0; i < 5; i++){
                result = httpTask2(5,urlTradeHistory + symbolID1 + urlTradeHistoryPF, 200, 3);
                if(result.length() > 2 && result.substring(0, 2).equals("20"))
                    break;
            }
            int date, py1 = -1, pmax2, pmin2, cp2, pl2, pf2, py2, q2, pmax3, pmin3, cp3, pl3, pf3;
            long val2, vol2;
            float coeff = 1;
            long[] tempLongArray;
            int resultLength =  result.length();
            for (int j = 0; j < resultLength; j++) {
                tempLongArray = stringNextLong(result, '@', j);
                j = (int) tempLongArray[1];
                date = (int) tempLongArray[0];
                if(date < (today - 20000))
                    break;
                tempLongArray = stringNextLong(result, '.', j);
                j = (int) tempLongArray[1] + 3;
                pmax2 = (int) tempLongArray[0];
                tempLongArray = stringNextLong(result, '.', j);
                j = (int) tempLongArray[1] + 3;
                pmin2 = (int) tempLongArray[0];
                tempLongArray = stringNextLong(result, '.', j);
                j = (int) tempLongArray[1] + 3;
                cp2 = (int) tempLongArray[0];
                tempLongArray = stringNextLong(result, '.', j);
                j = (int) tempLongArray[1] + 3;
                pl2 = (int) tempLongArray[0];
                tempLongArray = stringNextLong(result, '.', j);
                j = (int) tempLongArray[1] + 3;
                pf2 = (int) tempLongArray[0];
                tempLongArray = stringNextLong(result, '.', j);
                j = (int) tempLongArray[1] + 3;
                py2 = (int) tempLongArray[0];
                tempLongArray = stringNextLong(result, '.', j);
                j = (int) tempLongArray[1] + 3;
                val2 = tempLongArray[0];
                tempLongArray = stringNextLong(result, '@', j);
                j = (int) tempLongArray[1];
                vol2 = tempLongArray[0];
                tempLongArray = stringNextLong(result, ';', j);
                j = (int) tempLongArray[1] - 1;
                q2 = (int) tempLongArray[0];

                if(cp2 != py1 && py1 != -1)
                    coeff *= (float)cp2 / (float)py1;
                pmax3 = (int)((float)pmax2 / coeff);
                pmin3 = (int)((float)pmin2 / coeff);
                cp3 = (int)((float)cp2 / coeff);
                pl3 = (int)((float)pl2 / coeff);
                pf3 = (int)((float)pf2 / coeff);
                if(pmax3 > 0 && pmin3 > 0 && cp3 > 0 && pl3 > 0 && pf3 > 0){
                    pmaxHist.add(pmax3);
                    pminHist.add(pmin3);
                    cpHist.add(cp3);
                    plHist.add(pl3);
                    pfHist.add(pf3);
                    valHist.add(val2);
                    dateHist.add(Integer.valueOf(changeGregorianToPersian(String.valueOf(date))));
                }
                py1 = py2;
            }
        }
        if(pmaxHist.size() < 2)
            return;

        int chartWidth = 1400, chartHeight = 900, topMargin = 20, buttomMargin = 50, leftMargin = 100, rightMargin = 100;
        int width = chartWidth + leftMargin + rightMargin;
        int height = chartHeight + topMargin + buttomMargin;

        int maxPrice = 0, minPrice = 999999999;
        long maxVal = 0;
        for(int i = 0; i < pmaxHist.size(); i++){
            if(i*6 > width - rightMargin - leftMargin - 20)
                break;
            if(pmaxHist.get(i) > maxPrice)
                maxPrice = pmaxHist.get(i);
            if(pminHist.get(i) < minPrice)
                minPrice = pminHist.get(i);
            if(valHist.get(i) > maxVal)
                maxVal = valHist.get(i);
        }

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(new Font("B Yekan", Font.PLAIN, 20));
        g2d.setStroke(new BasicStroke(1));
        Color DarkGreen1 = new Color(0, 150, 0);
        Color DarkGreen2 = new Color(0, 170, 0);
        Color DarkRed = new Color(150, 0, 0);
        Color TransparentBlue = new Color(0F, 0F, 1F, 0.4F);
        Color LightGray = new Color(230, 230, 230);
        g2d.setColor(LightGray);
        g2d.fillRect(0,0, width, height);
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawLine(leftMargin, topMargin, width - rightMargin, topMargin);
        g2d.drawLine(leftMargin, height - buttomMargin, width - rightMargin, height - buttomMargin);
        g2d.drawLine(leftMargin, topMargin, leftMargin, height - buttomMargin);
        g2d.drawLine(width - rightMargin, topMargin, width - rightMargin, height - buttomMargin);
        g2d.drawString("میلیارد", 30, height - buttomMargin - chartHeight / 5 - 50);
        g2d.drawString("تومان", 35, height - buttomMargin - chartHeight / 5 - 30);

        int hInterval = (maxPrice - minPrice) / 20;
        String tempInt = String.valueOf(hInterval);
        hInterval = tempInt.charAt(0) - '0';
        for(int i = 1; i < tempInt.length(); i++)
            hInterval *= 10;
        int newNumber = 0;
        while (true){
            if(newNumber > maxPrice)
                break;
            if(newNumber > minPrice){
                int y = height - buttomMargin - 25 - ((newNumber - minPrice) * (chartHeight - 50) / (maxPrice - minPrice));
                g2d.setColor(Color.DARK_GRAY);
                g2d.drawString(String.valueOf(newNumber), width - rightMargin + 10, y);
                if((height - buttomMargin - y) <= (chartHeight / 5)) {
                    long valLabel = maxVal * (long) (height - buttomMargin - y) * 5L / (long) chartHeight / 10000000000L;
                    g2d.drawString(String.valueOf(valLabel), 40, y);
                }
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.drawLine(leftMargin, y, width - rightMargin, y);
            }
            newNumber += hInterval;
        }

        int lastDate = dateHist.get(0) / 100, lastDateIndex = 0;
        for(int i = 1; i < dateHist.size(); i++){
            if(i*6 > width - rightMargin - leftMargin - 20)
                break;
            if(dateHist.get(i) / 100 < lastDate){
                if(i - 1 - lastDateIndex > 10 || lastDateIndex == 0) {
                    String label = String.valueOf(dateHist.get(i - 1)).substring(2, 4) + "/";
                    if (dateHist.get(i - 1) % 10000 >= 1000)
                        label += String.valueOf(dateHist.get(i - 1)).substring(4, 6);
                    else
                        label += String.valueOf(dateHist.get(i - 1)).substring(5, 6);
                    g2d.setColor(Color.DARK_GRAY);
                    g2d.drawString(label, width - rightMargin - i * 6 - 30, height - buttomMargin + 20);
                    lastDateIndex = i - 1;
                }
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.drawLine(width - rightMargin - i*6 - 2, topMargin, width - rightMargin - i*6 - 2, height - buttomMargin);
                lastDate = dateHist.get(i) / 100;
            }
        }


        g2d.setColor(TransparentBlue);
        for(int i = 0; i < valHist.size(); i++){
            if(i*6 > width - rightMargin - leftMargin - 20)
                break;
            int h = (int)(valHist.get(i) * (long)(chartHeight / 5) / maxVal);
            g2d.fillRect(width - rightMargin - i*6 - 10, height - buttomMargin - h, 5, h);
        }

        for(int i = 0; i < pmaxHist.size(); i++){
            int y = 0, h = 0;
            if(pfHist.get(i) > plHist.get(i)){
                g2d.setColor(Color.RED);
                y = height - buttomMargin - 25 - ((pfHist.get(i) - minPrice) * (chartHeight - 50) / (maxPrice - minPrice));
                h = (pfHist.get(i) - plHist.get(i)) * (chartHeight - 50) / (maxPrice - minPrice);
            }
            else{
                g2d.setColor(DarkGreen1);
                y = height - buttomMargin - 25 - ((plHist.get(i) - minPrice) * (chartHeight - 50) / (maxPrice - minPrice));
                h = (plHist.get(i) - pfHist.get(i)) * (chartHeight - 50) / (maxPrice - minPrice);
            }
            if(i*6 > width - rightMargin - leftMargin - 20)
                break;
            g2d.fillRect(width - rightMargin - i*6 - 10, y, 5, h);
            int y1 = height - buttomMargin - 25 - ((pmaxHist.get(i) - minPrice) * (chartHeight - 50) / (maxPrice - minPrice));
            int y2 = height - buttomMargin - 25 - ((pminHist.get(i) - minPrice) * (chartHeight - 50) / (maxPrice - minPrice));
            g2d.drawLine(width - rightMargin - i*6 - 8, y1, width - rightMargin - i*6 - 8, y2);
            if(pfHist.get(i).equals(plHist.get(i)))
                g2d.drawLine(width - rightMargin - i*6 - 10, y, width - rightMargin - i*6 - 5, y);

        }
        g2d.dispose();
        try {
            file = new File("fullchart.png");
            ImageIO.write(bufferedImage, "png", file);
            SendPhoto message = new SendPhoto();
            message.setChatId(chatID);
            message.setCaption(caption);
            message.setPhoto(file);
            message.setParseMode(ParseMode.HTML);
            execute(message);
        }
        catch (IOException | TelegramApiException e){
            e.printStackTrace();
            
        }

    }

    public void updateProfitAndLoss() throws IOException{
//        ArrayList<String> symbolNickName2 = new ArrayList<>();
//        Scanner sc = new Scanner(new FileInputStream("nick_id.txt"));
//        while (sc.hasNext()) {
//            symbolNickName2.add(sc.next());
//            sc.next();
//        }
//        sc.close();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int today = calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);
        int todayPersian = Integer.valueOf(changeGregorianToPersian(String.valueOf(today)));
        Scanner sc;
        ArrayList< ArrayList<Integer> > dates = new ArrayList<>();
        ArrayList< ArrayList<Integer> > prices = new ArrayList<>();
        int symbolsNumber = symbolNickName.size();
        for(int i = 0; i < symbolsNumber; i++) {
            dates.add(new ArrayList<>());
            prices.add(new ArrayList<>());
            String filePath = "priceHistory/" + symbolNickName.get(i);
            File file = new File(filePath);
            if (file.exists()) {
                sc = new Scanner(new FileInputStream(filePath));
                while (sc.hasNext()) {
                    dates.get(i).add(sc.nextInt());
                    sc.next();
                    sc.next();
                    prices.get(i).add(sc.nextInt());
                    sc.next();
                    sc.next();
                    sc.next();
                }
                sc.close();
            }
        }
        shareholdersFull.clear();
        sc = new Scanner(new FileInputStream("shareholdersFull"));
        while (sc.hasNext()) {
            Shareholder2 tempShareholder2 = new Shareholder2();
            tempShareholder2.id = sc.nextLine();
            tempShareholder2.name = sc.nextLine();
            while (sc.hasNext()) {
                Share2 tempShare2 = new Share2();
                tempShare2.enterOrExit = sc.next();
                if(tempShare2.enterOrExit.equals("*"))
                    break;
                tempShare2.shareName = sc.next();
                tempShare2.date = sc.next();
                tempShare2.pnl1 = sc.nextFloat();
                tempShare2.pnl2 = sc.nextFloat();
                tempShare2.pnl3 = sc.nextFloat();
                tempShareholder2.prevShares.add(tempShare2);
            }
            sc.nextLine();
            shareholdersFull.add(tempShareholder2);
        }
        int shNumber = shareholdersFull.size();
        for(int i = 0; i < shNumber; i++){
            int prevSharesSize = shareholdersFull.get(i).prevShares.size();
            for(int j = 0; j < prevSharesSize; j++){
                int enterOrExitDate = Integer.valueOf(shareholdersFull.get(i).prevShares.get(j).date);
                if(persianCalendarDiff(todayPersian, enterOrExitDate) <= 30) {
                    int symbolIndex = symbolNickName.indexOf(shareholdersFull.get(i).prevShares.get(j).shareName);
                    if(symbolIndex >= 0){
                        int datesSize = dates.get(symbolIndex).size();
                        int enterOrExitPrice = 0;
                        for(int k = 0; k < datesSize; k++){
                            if(dates.get(symbolIndex).get(k) < enterOrExitDate){
                                if(k > 0)
                                    k--;
                                enterOrExitPrice = prices.get(symbolIndex).get(k);
                                break;
                            }
                        }
                        if(enterOrExitPrice > 0) {
                            boolean pnl1 = false, pnl2 = false, pnl3 = false;
                            for (int k = 0; k < datesSize; k++) {
                                if (pnl1 && pnl2 && pnl3)
                                    break;
                                int currentDate = dates.get(symbolIndex).get(k);
                                if (!pnl3 && persianCalendarDiff(currentDate, enterOrExitDate) <= 30 && persianCalendarDiff(currentDate, enterOrExitDate) > 20) {
                                    shareholdersFull.get(i).prevShares.get(j).pnl3 = (float) prices.get(symbolIndex).get(k) / (float) enterOrExitPrice;
                                    pnl3 = true;
                                }
                                else if (!pnl2 && persianCalendarDiff(currentDate, enterOrExitDate) <= 14 && persianCalendarDiff(currentDate, enterOrExitDate) > 7) {
                                    shareholdersFull.get(i).prevShares.get(j).pnl2 = (float) prices.get(symbolIndex).get(k) / (float) enterOrExitPrice;
                                    pnl2 = true;
                                }
                                else if (!pnl1 && persianCalendarDiff(currentDate, enterOrExitDate) <= 7 && persianCalendarDiff(currentDate, enterOrExitDate) > 0) {
                                    shareholdersFull.get(i).prevShares.get(j).pnl1 = (float) prices.get(symbolIndex).get(k) / (float) enterOrExitPrice;
                                    pnl1 = true;
                                }
                            }
                        }
                    }
                }
            }
        }

        FileWriter fileWriter = new FileWriter("shareholdersFull");
        int shSize = shareholdersFull.size();
        int[] averagePNL = new int[shSize];
        int[] averagePNLTimes = new int[shSize];
        for(int i = 0; i < shSize; i++){
            fileWriter.write(shareholdersFull.get(i).id + "\n" + shareholdersFull.get(i).name + "\n");
            float pnlTemp = 0F,  pnlCnt = 0F;
            for (int j = 0; j < shareholdersFull.get(i).prevShares.size(); j++) {
                if(shareholdersFull.get(i).prevShares.get(j).enterOrExit.equals("ورود")
                        && shareholdersFull.get(i).prevShares.get(j).pnl3 > 0){
                    pnlTemp += shareholdersFull.get(i).prevShares.get(j).pnl3;
                    pnlCnt++;
                }
                fileWriter.write(shareholdersFull.get(i).prevShares.get(j).enterOrExit + " " +
                        shareholdersFull.get(i).prevShares.get(j).shareName + " " +
                        shareholdersFull.get(i).prevShares.get(j).date + " " +
                        String.format("%.2f ", shareholdersFull.get(i).prevShares.get(j).pnl1) +
                        String.format("%.2f ", shareholdersFull.get(i).prevShares.get(j).pnl2) +
                        String.format("%.2f ", shareholdersFull.get(i).prevShares.get(j).pnl3));
            }
            fileWriter.write("*\n");
            averagePNL[i] = (int)(100F  * pnlTemp / pnlCnt - 100F);
            averagePNLTimes[i] = (int)pnlCnt;
        }
        fileWriter.close();
        ArrayList<Integer> maxsIndex = new ArrayList<>();
        for(int i = 0; i < shSize; i++){
            int maxpnl = 0, maxI = -1;
            for(int j = 0; j < shSize; j++){
                if(averagePNL[j] > maxpnl && averagePNL[j] >= 30 && !maxsIndex.contains(j)){
                    maxpnl = averagePNL[j];
                    maxI = j;
                }
            }
            if(maxI != -1)
                maxsIndex.add(maxI);
        }

        shareholders = new ArrayList<>();
        sc = new Scanner(new FileInputStream("shareholders"));
        Shareholder tempShareholder;
        Share tempShare;
        String tempStr;
        while (sc.hasNext()) {
            tempShareholder = new Shareholder();
            tempShareholder.id = sc.nextLine();
            tempShareholder.name = sc.nextLine();
            while (true){
                tempStr = sc.next();
                if(tempStr.equals("***"))
                    break;
                tempShare = new Share();
                tempShare.shareName = tempStr;
                tempShare.sharesNumber = Long.valueOf(sc.next());
                tempShare.percent = Float.valueOf(sc.next());
                tempShare.enterDate = sc.next();
                if(isSaham(tempStr))
                    tempShareholder.currentShares.add(tempShare);
            }
            while (true){
                tempStr = sc.next();
                if(tempStr.equals("***"))
                    break;
                tempShare = new Share();
                tempShare.shareName = tempStr;
                tempShare.sharesNumber = Long.valueOf(sc.next());
                tempShare.percent = Float.valueOf(sc.next());
                tempShare.enterDate = sc.next();
                tempShare.exitDate = sc.next();
                if(isSaham(tempStr))
                    tempShareholder.prevShares.add(tempShare);
            }
            sc.nextLine();
            if(tempShareholder.currentShares.size() > 0)
                shareholders.add(tempShareholder);
        }
        sc.close();
        fileWriter = new FileWriter("bestShareholders");
        for(int i = 0; i < maxsIndex.size(); i++){
            int index = maxsIndex.get(i);
            if(averagePNL[index] >= 60 && averagePNLTimes[index] >= 1 ||
                    averagePNL[index] >= 50 && averagePNLTimes[index] >= 2 ||
                    averagePNL[index] >= 40 && averagePNLTimes[index] >= 3 ||
                    averagePNL[index] >= 30 && averagePNLTimes[index] >= 4) {
                boolean idFound = false;
                for (int j = 0; j < shareholders.size(); j++) {
                    boolean realPerson = shareholders.get(j).name.equals("شخص حقيقي")
                            || shareholders.get(j).name.equals("سبد شخص حقیقی")
                            || shareholders.get(j).name.equals("شخص حقیقی خارجی");
                    if (shareholders.get(j).name.equals(shareholdersFull.get(index).name)
                            && (!realPerson || shareholders.get(j).id.equals(shareholdersFull.get(index).id))) {
                        fileWriter.write(shareholders.get(j).id + " * " + shareholders.get(j).name + " * " +
                                averagePNL[index] + "% * " + averagePNLTimes[index] + " * ");
                        for (int k = 0; k < shareholders.get(j).currentShares.size(); k++)
                            fileWriter.write(shareholders.get(j).currentShares.get(k).shareName + " " +
                                    shareholders.get(j).currentShares.get(k).percent + "% ");
                        fileWriter.write("\n");
                        idFound = true;
                        break;
                    }
                }
                if (!idFound)
                    fileWriter.write(shareholdersFull.get(index).id + " * " + shareholdersFull.get(index).name + " * " +
                            averagePNL[index] + "% * " + averagePNLTimes[index] + "\n");
            }
        }
        fileWriter.close();
        shareholdersFull.clear();
        dates.clear();
        prices.clear();
    }

    public void runOffline(){
        try {
            httpClients.clear();
            httpResponses.clear();
            httpGets.clear();
            for (int i = 0; i < 10; i++) {
                httpClients.add(HttpClients.createDefault());
                httpResponses.add(null);
                httpGets.add(null);
            }
            marketWatchInit(false, true);
            instTradeHistory();
            checkShareholders(false);
            updateProfitAndLoss();
        }
        catch (IOException | InterruptedException | TelegramApiException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        String userID, userName = "", name = "", messageText;
        boolean hasText, hasGif;
        if (update.hasMessage()) {
            hasText = update.getMessage().hasText();
            hasGif = update.getMessage().hasAnimation();
            userID = String.valueOf(update.getMessage().getChatId());
            if(update.getMessage().getChat().getUserName() != null)
                userName = "@" + update.getMessage().getChat().getUserName();
            if(update.getMessage().getChat().getFirstName() != null)
                name = update.getMessage().getChat().getFirstName();
            if(update.getMessage().getChat().getLastName() != null)
                name += " " + update.getMessage().getChat().getLastName();
            if(!userID.equals(ID.FARDIN)) {
                try {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(new Date());
                    int today = calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);
                    LocalTime timeNow = LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS));
                    String timeNowToString = timeNow.toString();
                    if(timeNow.toString().length() >= 8)
                        timeNowToString = timeNow.toString().substring(0,8);
                    FileWriter fileWriter = new FileWriter("membersHistory", true);
                    fileWriter.write(formattedDate(changeGregorianToPersian(String.valueOf(today))) + " "
                            + timeNowToString + " ");
                    fileWriter.write(userID + " " + userName + " " + name + "\n");
                    if (hasText)
                        fileWriter.write(update.getMessage().getText() + "\n");
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(hasGif){
                if(userID.equals(ID.FARDIN)){
                    SendMessage message = new SendMessage();
                    message.setChatId(userID);
                    message.setText(update.getMessage().getAnimation().getFileId());
                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
            if(hasText) {
                messageText = update.getMessage().getText();
                if(messageText.equals("کیه کیه")){
                    try {
                        SendMessage message = new SendMessage();
                        message.setChatId(userID);
                        message.setText("منم تهی");
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                else if(messageText.equals("سلام") || messageText.equals("/start")){
                    SendMessage message = new SendMessage();
                    message.setChatId(ID.FARDIN);
                    message.setText(userID + "\n" + userName + "\n" + name);
                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                if(userID.equals(ID.FARDIN)) {
                    if(!(messageText.length() > 4 && messageText.substring(0,4).equals("چارت"))
                            && !(messageText.length() > 3 && messageText.substring(0,3).equals("واچ"))
                            && httpClients.size() != 10) {
                        httpClients.clear();
                        httpResponses.clear();
                        httpGets.clear();
                        for (int i = 0; i < 10; i++) {
                            httpClients.add(HttpClients.createDefault());
                            httpResponses.add(null);
                            httpGets.add(null);
                        }
                    }
                    if(messageText.equals("t1")){
                        SendMessage message = new SendMessage();
                        message.setChatId(userID);
                        try {
                            marketWatchInit(false, true);
                            initSomeArrayList(true);
                            System.out.println("\nX1: " + LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS)));
                            instTradeHistory();
                            System.out.println("\nX2: " + LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS)));
                            statistics();
                            System.out.println("\nX3: " + LocalTime.now().plus(Duration.of(UTCDiff, ChronoUnit.SECONDS)));

//                            final int totalThreads = Integer.valueOf(messageText.substring(messageText.length() - 2, messageText.length()));
//                            ArrayList<Thread> tradeDetailsThreads = new ArrayList<>();
//                            for (int i = 0; i < totalThreads; i++)
//                                tradeDetailsThreads.add(new Thread());
//                            for (int i = 0; i < totalThreads; i++){
//                                if(!tradeDetailsThreads.get(i).isAlive()){
//                                    final int i2 = i;
//                                    Runnable tradeDetailsTask = () -> {
//                                        try {
//                                            tradeDetails(i2, totalThreads);
//                                        }catch (IOException | InterruptedException e){
//                                            e.printStackTrace();
//                                            
//                                        }
//                                    };
//                                    tradeDetailsThreads.set(i, new Thread(tradeDetailsTask));
//                                    tradeDetailsThreads.get(i).start();
//                                }
//                            }
//                            for (int i = 0; i < totalThreads; i++) {
//                                tradeDetailsThreads.get(i).join();
//                            }

                        } catch (IOException | InterruptedException | TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(messageText.equals("t2")){
                        SendMessage message = new SendMessage();
                        message.setChatId(userID);
                        message.setText("done");
                        try {
                            marketWatchInit(false, true);
                            initSomeArrayList(true);
                            instTradeHistory();
                            execute(message);
                        } catch (IOException | InterruptedException | TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(messageText.equals("t3")){
                        try {
                            marketWatchInit(false, true);
                            initSomeArrayList(true);

                            priceChartData = new ArrayList<>();
                            priceChartDataBlackIndex = new ArrayList<>();
                            exValueChartData = new ArrayList<>();
                            buyVolChartData = new ArrayList<>();
                            buyCntChartData = new ArrayList<>();
                            sellVolChartData = new ArrayList<>();
                            sellCntChartData = new ArrayList<>();
                            volPassed1B = new ArrayList<>();
                            priceChartData.add(new ArrayList<>());
                            priceChartDataBlackIndex.add(0);
                            exValueChartData.add(new ArrayList<>());
                            buyVolChartData.add(new ArrayList<>());
                            buyCntChartData.add(new ArrayList<>());
                            sellVolChartData.add(new ArrayList<>());
                            sellCntChartData.add(new ArrayList<>());
                            volPassed1B.add(false);
                            int L2 = 217;
                            halfHours = new ArrayList<>();
                            halfHoursIndex = new ArrayList<>();
                            priceAvgBuffer = new long[1][priceBufferSize];
                            Scanner sc = new Scanner(new FileInputStream("chart1.txt"));

                            for(int i = 0; i < L2 * barWidth; i++)
                                priceChartData.get(0).add(Integer.valueOf(sc.next()));
                            for(int i = 0; i < priceBufferSize; i++){
                                int sum = 0;
                                for (int k = 0; k < priceChartDataAvgLen; k++)
                                    sum += priceChartData.get(0).get(L2 * barWidth - priceBufferSize - k + i);
                                priceAvgBuffer[0][i] = sum / priceChartDataAvgLen;
//                                System.out.print(priceAvgBuffer[0][i] + " ");
                            }
                            for(int i = 0; i < L2; i++)
                                buyVolChartData.get(0).add(Integer.valueOf(sc.next()));
                            for(int i = 0; i < L2; i++)
                                buyCntChartData.get(0).add(Integer.valueOf(sc.next()));
                            for(int i = 0; i < L2; i++)
                                sellVolChartData.get(0).add(Integer.valueOf(sc.next()));
                            for(int i = 0; i < L2; i++)
                                sellCntChartData.get(0).add(Integer.valueOf(sc.next()));
                            for(int i = 0; i < L2; i++)
                                exValueChartData.get(0).add(Integer.valueOf(sc.next()));
                            sc.close();


                            int index = symbolNickName.indexOf("شستا");
                            if(index >= 0) {
                                String name1 = symbolNickName.get(0);
                                int min = minAllowed.get(0);
                                int max = maxAllowed.get(0);
                                int y = yesterdayClosingPrice.get(0);
                                symbolNickName.set(0, symbolNickName.get(index));
                                minAllowed.set(0, minAllowed.get(index));
                                maxAllowed.set(0, maxAllowed.get(index));
                                yesterdayClosingPrice.set(0, yesterdayClosingPrice.get(index));
                                symbolNickName.set(index, name1);
                                minAllowed.set(index, min);
                                maxAllowed.set(index, max);
                                yesterdayClosingPrice.set(index, y);
                                positiveRange();
                                saveChartImage(true, "شستا", CHART_TYPE.WITHOUT_MOVING_AVERAGE);
                                SendPhoto message = new SendPhoto();
                                message.setPhoto(new File("chart.png"));
                                customSendMessage(2, null, message, null, ID.FARDIN, false);
                                SendDocument message2 = new SendDocument();
                                message2.setDocument(new File("chart.png"));
                                customSendMessage(3, null, null, message2, ID.FARDIN, false);
                            }
                        }
                        catch (IOException | TelegramApiException | InterruptedException e){
                            e.printStackTrace();
                        }

                    }
                    else if(messageText.equals("t4")){
                        String argStr = "";
                        argStr += "5;حقوقی به نامعلوم;99.99;99.99;9999.9;99.9;999.9;99.9;999.9;99.9;999;دزهراویح\n";
                        argStr += "5;نامعلوم به حقیقی;99.99;99.99;9999.9;99.9;999.9;99.9;999.9;99.9;999;کی_بی_سی\n";
                        argStr += "5;حقوقی به حقیقی;99.99;99.99;9999.9;99.9;999.9;99.9;999.9;99.9;999;دارا_یکم\n";

                        SendPhoto message = new SendPhoto().setChatId(ID.FARDIN);
                        try {
                            saveCodeToCodeImage(argStr , 0);
                            message.setPhoto(new File("ctc0.png"));
                            execute(message);
                        } catch (TelegramApiException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(messageText.equals("t5")){
                        try {
                            marketWatchInit(false, true);
                            initSomeArrayList(true);
                            FileWriter fileWriter = new FileWriter("exVolumes");
                            for (int i = 0; i < symbolNickName.size(); i++){
                                fileWriter.write(symbolNickName.get(i) + " " + exVolume2.get(i) + "\n");
                            }
                            fileWriter.close();
                        } catch (TelegramApiException | IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(messageText.equals("t6")){
                        ArrayList<String> argList = new ArrayList<>();
                        argList.add(String.format("%.1f", 78.9));
                        argList.add(String.format("%.1f", 78.9));
                        argList.add(String.valueOf(1));
                        argList.add(String.format("%.1f", 7.8));
                        argList.add(String.format("%.1f", 6.5));
                        argList.add(String.valueOf(12));
                        argList.add(String.format("%.1f", 4.3));
                        argList.add(String.format("%.1f", 2.1));
                        argList.add(String.valueOf(123));
                        argList.add(String.format("%.1f", 1.0));
                        argList.add(String.format("%.1f", 0.0));
                        argList.add(String.valueOf(1234));

                        SendPhoto message = new SendPhoto().setChatId(ID.FARDIN);
                        try {
                            saveAverageImage(argList, 0);
                            message.setPhoto(new File("avg.png"));
                            execute(message);
                        } catch (TelegramApiException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(messageText.equals("t8")){
                        try {
                            saveTableImage(0, 0);
                            SendPhoto message = new SendPhoto();
                            message.setPhoto(new File("table.png"));
                            customSendMessage(2, null, message, null, ID.FARDIN, false);
//                            SendDocument message2 = new SendDocument();
//                            message2.setDocument(new File("table.png"));
//                            customSendMessage(3, null, null, message2, ID.FARDIN, false);
                            } catch (IOException | InterruptedException | TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(messageText.equals("t9")){
                        try {
                            System.out.println(LocalTime.now());
                            marketWatchInit(false, true);
                            updateProfitAndLoss();
                            System.out.println(LocalTime.now());
                        }
                        catch (IOException | TelegramApiException | InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                    else if(messageText.equals("t10")){
                        try {
                            marketWatchInit(false, true);
                            Scanner sc = new Scanner(new FileInputStream("shareholders"));
                            Shareholder tempShareholder;
                            Share tempShare;
                            String tempStr;
                            while (sc.hasNext()) {
                                tempShareholder = new Shareholder();
                                tempShareholder.id = sc.nextLine();
                                tempShareholder.name = sc.nextLine();
                                while (true) {
                                    tempStr = sc.next();
                                    if (tempStr.equals("***"))
                                        break;
                                    tempShare = new Share();
                                    tempShare.shareName = tempStr;
                                    tempShare.sharesNumber = Long.valueOf(sc.next());
                                    tempShare.percent = Float.valueOf(sc.next());
                                    tempShare.enterDate = sc.next();
                                    if (isSaham(tempStr))
                                        tempShareholder.currentShares.add(tempShare);
                                }
                                while (true) {
                                    tempStr = sc.next();
                                    if (tempStr.equals("***"))
                                        break;
                                    tempShare = new Share();
                                    tempShare.shareName = tempStr;
                                    tempShare.sharesNumber = Long.valueOf(sc.next());
                                    tempShare.percent = Float.valueOf(sc.next());
                                    tempShare.enterDate = sc.next();
                                    tempShare.exitDate = sc.next();
                                    if (isSaham(tempStr))
                                        tempShareholder.prevShares.add(tempShare);
                                }
                                sc.nextLine();
                                if (tempShareholder.currentShares.size() > 0 || tempShareholder.prevShares.size() > 0)
                                    shareholders.add(tempShareholder);
                            }
                            sc.close();
                            System.out.println(LocalTime.now());
                            writeToExcel();
                            System.out.println(LocalTime.now());
                        }
                        catch (IOException | TelegramApiException | InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                    else if(messageText.equals("t11")){
                        int aaa;
                    }
                    else if(messageText.equals("money_acc")){
                        try{
                            checkHotMoneyAndOrderHistory(false);
                        }
                        catch (IOException | TelegramApiException | InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                    else if(messageText.equals("sh_acc")){
                        try{
                            checkShHistory();
                        }
                        catch (IOException | TelegramApiException | InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                    else if(messageText.equals("player_acc")){
                        try{
                            playersStatus("players/all", false);
                            playersStatus("players/all2", false);
                        }
                        catch (IOException | TelegramApiException | InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                    else if(messageText.length() > 4 && messageText.substring(0,4).equals("چارت")){
                        String nickName = messageText.substring(5,messageText.length());
                        try {
                            FileWriter fw = new FileWriter("oneChart");
                            fw.write(nickName);
                            fw.close();
                        }
                        catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    else if(messageText.equals("sh0") || messageText.equals("sh1")){
                        SendMessage message = new SendMessage();
                        message.setChatId(ID.FARDIN);
                        try {
                            message.setText("start");
                            execute(message);
                            Runnable task2 = () -> {
                                try {
                                    marketWatchInit(false, true);
                                    instTradeHistory();
                                    if(messageText.equals("sh0"))
                                        checkShareholders(false);
                                    else if(messageText.equals("sh1"))
                                        checkShareholders(true);
                                    updateProfitAndLoss();
                                    execute(new SendMessage().setChatId(ID.FARDIN).setText("checkShareholders done"));
                                }catch (IOException | InterruptedException | TelegramApiException e){
                                    e.printStackTrace();
                                    
                                }
                            };
                            Thread thread2 = new Thread(task2);
                            thread2.start();
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(messageText.equals("vol")){
                        try {
                            marketWatchInit(false, true);
                            FileWriter fileWriter = new FileWriter("exVolumes");
                            for (int i = 0; i < symbolNickName.size(); i++)
                                fileWriter.write(symbolNickName.get(i) + " " + exVolume2.get(i) + "\n");
                            fileWriter.close();
                        }
                        catch (IOException | InterruptedException | TelegramApiException e){
                            e.printStackTrace();
                        }
                    }
                    else if(messageText.length() > 3 && messageText.substring(0,3).equals("واچ")){
                        try {
                            FileWriter fw = new FileWriter("watchlist");
                            fw.write(messageText.substring(4,messageText.length()) + " ");
                            fw.close();
                        }
                        catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    else if(messageText.length() > 5 && messageText.substring(0,5).equals("دستور")){
                        try {
                            FileWriter fw = new FileWriter("cmd");
                            fw.write(messageText.substring(6,messageText.length()));
                            fw.close();
                        }
                        catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    else if(messageText.length() > 4 && messageText.substring(0,4).equals("add ")){
                        try {
                            long[] tempLongArray = stringNextLong(messageText, ' ', 4);
                            int index = (int)tempLongArray[1];
                            long userID2 = tempLongArray[0];
                            tempLongArray = stringNextLong(messageText, ' ', index);
                            long monthlyLimit = tempLongArray[0];
                            FileWriter fileWriter = new FileWriter("shClients", true);
                            fileWriter.write(userID2 + " " + monthlyLimit + " 0\n");
                            fileWriter.close();
                        }
                        catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
                if(messageText.length() > 5 && messageText.substring(0,5).equals("درصدی")){
                    try {
                        ArrayList<String> shClients = new ArrayList<>();
                        ArrayList<Integer> limits = new ArrayList<>();
                        ArrayList<Integer> accessNumbers = new ArrayList<>();
                        Scanner sc = new Scanner(new FileInputStream("shClients"));
                        while (sc.hasNext()){
                            shClients.add(sc.next());
                            limits.add(sc.nextInt());
                            accessNumbers.add(sc.nextInt());
                        }
                        sc.close();
                        int userIndex = shClients.indexOf(userID);
                        if(userIndex >= 0) {
                            String nickName = "";
                            String[] tempStringArray;
                            int index = 6, row = 0;
                            tempStringArray = stringNextString(messageText, ' ', index);
                            index = Integer.valueOf(tempStringArray[1]);
                            nickName = tempStringArray[0];
                            if (messageText.length() > index && messageText.charAt(index) >= '1' && messageText.charAt(index) <= '9') {
                                row = (messageText.charAt(index) - '0');
                                index++;
                                if (messageText.length() > index && messageText.charAt(index) >= '0' && messageText.charAt(index) <= '9')
                                    row = row * 10 + (messageText.charAt(index) - '0');
                            }
                            sc = new Scanner(new FileInputStream("shareholders"));
                            String tempStr;
                            ArrayList<String> shhName = new ArrayList<>();
                            ArrayList<String> shhID = new ArrayList<>();
                            ArrayList<String> shhPercent = new ArrayList<>();
                            String id, name2, shareName, percent;
                            while (sc.hasNext()) {
                                id = sc.nextLine();
                                name2 = sc.nextLine();
                                while (true) {
                                    shareName = sc.next();
                                    if (shareName.equals("***"))
                                        break;
                                    sc.next();
                                    percent = sc.next();
                                    sc.next();
                                    if (nickName.equals(shareName)) {
                                        shhName.add(name2);
                                        shhID.add(id);
                                        shhPercent.add(percent);
                                    }
                                }
                                while (true) {
                                    tempStr = sc.next();
                                    if (tempStr.equals("***"))
                                        break;
                                    sc.next();
                                    sc.next();
                                    sc.next();
                                    sc.next();
                                }
                                sc.nextLine();
                            }
                            sc.close();
                            String toSend1 = "", toSend2 = "";

                            if (row == 0) {
                                for (int i = 0; i < shhName.size(); i++) {
                                    String name3 = shhName.get(i);
                                    if (name3.substring(0, 3).equals("BFM") || name3.substring(0, 3).equals("IFM") || name3.substring(0, 3).equals("PRX"))
                                        name3 = name3.substring(3, name3.length());
                                    toSend1 += (i + 1) + ". " + name3 + " " + shhPercent.get(i) + "%\n";
                                }
                                sendLongMessage(toSend1, userID, false);
                            }
                            else if (accessNumbers.get(userIndex) < limits.get(userIndex)){
                                String theID = shhID.get(row - 1);
                                String theName = shhName.get(row - 1);
                                boolean realPerson = theName.equals("شخص حقيقي") || theName.equals("سبد شخص حقیقی") || theName.equals("شخص حقیقی خارجی");
                                sc = new Scanner(new FileInputStream("shareholders"));
                                ArrayList<Share> shares = new ArrayList<>();
                                while (sc.hasNext()) {
                                    id = sc.nextLine();
                                    name2 = sc.nextLine();
                                    Share tempShare;
                                    if (theName.equals(name2) && (theID.equals(id) || !realPerson)) {
                                        while (true) {
                                            tempStr = sc.next();
                                            if (tempStr.equals("***"))
                                                break;
                                            tempShare = new Share();
                                            tempShare.shareName = tempStr;
                                            tempShare.sharesNumber = Long.valueOf(sc.next());
                                            tempShare.percent = Float.valueOf(sc.next());
                                            tempShare.enterDate = sc.next();
                                            shares.add(tempShare);
                                        }
                                    } else {
                                        while (true) {
                                            tempStr = sc.next();
                                            if (tempStr.equals("***"))
                                                break;
                                            sc.next();
                                            sc.next();
                                            sc.next();
                                        }
                                    }
                                    while (true) {
                                        tempStr = sc.next();
                                        if (tempStr.equals("***"))
                                            break;
                                        sc.next();
                                        sc.next();
                                        sc.next();
                                        sc.next();
                                    }
                                    sc.nextLine();
                                }
                                sc.close();
                                if (shares.size() > 0) {
                                    toSend1 += "سهام فعلی:\n";
                                    for (int i = 0; i < shares.size(); i++) {
                                        toSend1 += shares.get(i).shareName + " ";
                                        toSend1 += String.format("%.2f", shares.get(i).percent) + "% ";
                                        if(!shares.get(i).enterDate.equals("13800101"))
                                            toSend1 += "ورود: " + formattedDate(shares.get(i).enterDate);
                                        toSend1 += "\n";
                                    }
                                    toSend1 += "\n";
                                }
                                sc = new Scanner(new FileInputStream("shareholdersFull"));
                                ArrayList<Share2> shares2 = new ArrayList<>();
                                while (sc.hasNext()) {
                                    id = sc.nextLine();
                                    name2 = sc.nextLine();
                                    while (sc.hasNext()) {
                                        Share2 tempShare = new Share2();
                                        tempShare.enterOrExit = sc.next();
                                        if (tempShare.enterOrExit.equals("*"))
                                            break;
                                        tempShare.shareName = sc.next();
                                        tempShare.date = sc.next();
                                        tempShare.pnl1 = sc.nextFloat();
                                        tempShare.pnl2 = sc.nextFloat();
                                        tempShare.pnl3 = sc.nextFloat();
                                        if (theName.equals(name2) && (theID.equals(id) || !realPerson))
                                            shares2.add(tempShare);
                                    }
                                    sc.nextLine();
                                }
                                sc.close();
                                boolean send1 = false, send2 = false;
                                if (shares2.size() > 0) {
                                    float pnlTemp = 0, pnlCnt = 0;
                                    for (int i = 0; i < shares2.size(); i++) {
                                        if(shares2.get(i).enterOrExit.equals("ورود") && shares2.get(i).pnl3 > 0){
                                            pnlTemp += shares2.get(i).pnl3;
                                            pnlCnt++;
                                        }
                                    }
                                    toSend1 += "سهام قبلی:\n";
                                    toSend2 = toSend1;
                                    toSend1 += "میانگین بازدهی یک ماه: ";
                                    toSend1 += (int)(100F  * pnlTemp / pnlCnt - 100F) + "% از ";
                                    toSend1 += (int)pnlCnt + " سهم\n";
                                    for (int i = (shares2.size() - 1); i >= 0; i--) {
                                        if(shares2.get(i).enterOrExit.equals("ورود")){
                                            toSend1 += "\n" + shares2.get(i).shareName + " " + shares2.get(i).enterOrExit + ": "
                                                    + formattedDate(shares2.get(i).date) + " ";
                                            send1 = true;
                                        }
                                        else{
                                            toSend2 += "\n" + shares2.get(i).shareName + " " + shares2.get(i).enterOrExit + ": "
                                                    + formattedDate(shares2.get(i).date) + " ";
                                            send2 = true;
                                        }
                                        String toSendTemp = "";
                                        if(shares2.get(i).pnl1 > 0F || shares2.get(i).pnl2 > 0F || shares2.get(i).pnl3 > 0F)
                                            toSendTemp += "\n";
                                        if(shares2.get(i).pnl1 > 0F){
                                            toSendTemp += "یک هفته: " + (int) ((shares2.get(i).pnl1 - 1F) * 100F) + "%";
                                            if(shares2.get(i).pnl1 > 1F)
                                                toSendTemp += greenCircle + " ";
                                            else
                                                toSendTemp += redCircle + " ";
                                        }
                                        if(shares2.get(i).pnl2 > 0F){
                                            toSendTemp += "دو هفته: " + (int) ((shares2.get(i).pnl2 - 1F) * 100F) + "%";
                                            if(shares2.get(i).pnl2 > 1F)
                                                toSendTemp += greenCircle + " ";
                                            else
                                                toSendTemp += redCircle + " ";
                                        }
                                        if(shares2.get(i).pnl3 > 0F){
                                            toSendTemp += "یک ماه: " + (int) ((shares2.get(i).pnl3 - 1F) * 100F) + "%";
                                            if(shares2.get(i).pnl3 > 1F)
                                                toSendTemp += greenCircle;
                                            else
                                                toSendTemp += redCircle;
                                        }
                                        if(shares2.get(i).enterOrExit.equals("ورود"))
                                            toSend1 += toSendTemp;
                                        else
                                            toSend2 += toSendTemp;
                                    }
                                }
                                if(send1){
                                    sendLongMessage(toSend1, userID, false);
                                }
                                if(send2){
                                    sendLongMessage(toSend2, userID, false);
                                }
                                accessNumbers.set(userIndex, accessNumbers.get(userIndex) + 1);
                                FileWriter fileWriter = new FileWriter("shClients");
                                for (int i = 0; i < shClients.size(); i++)
                                    fileWriter.write(shClients.get(i) + " " + limits.get(i) + " " + accessNumbers.get(i) + " ");
                                fileWriter.close();
                            }
                            else {
                                SendMessage message = new SendMessage().setChatId(userID)
                                        .setText("تعداد درخواست های شما به حداکثر مجاز رسیده است.");
                                execute(message);
                            }
                        }
                    }
                    catch (TelegramApiException | InterruptedException | IOException e){
                        e.printStackTrace();
                    }
                }
                if(messageText.length() > 4 && messageText.substring(0,4).equals("بازه")){
                    try {
                        ArrayList<String> shClients = new ArrayList<>();
                        ArrayList<Integer> limits = new ArrayList<>();
                        ArrayList<Integer> accessNumbers = new ArrayList<>();
                        Scanner sc = new Scanner(new FileInputStream("shClients"));
                        while (sc.hasNext()){
                            shClients.add(sc.next());
                            limits.add(sc.nextInt());
                            accessNumbers.add(sc.nextInt());
                        }
                        sc.close();
                        int userIndex = shClients.indexOf(userID);
                        if(userIndex >= 0 && accessNumbers.get(userIndex) < limits.get(userIndex)) {
                            String nickName = "";
                            int index = 5, begin = 0, end = 0;
                            String[] tempStringArray;
                            long[] tempLongArray;
                            tempStringArray = stringNextString(messageText, ' ', index);
                            index = Integer.valueOf(tempStringArray[1]);
                            nickName = tempStringArray[0];
                            tempLongArray = stringNextLong(messageText, ' ', index);
                            index = (int)tempLongArray[1];
                            begin = (int)tempLongArray[0];
                            tempLongArray = stringNextLong(messageText, ' ', index);
                            end = (int)tempLongArray[0];
                            if(begin > 1387000 && begin < 14100000 && end > 1387000 && end < 14100000 && begin < end && end - begin <= 10000){
                                sc = new Scanner(new FileInputStream("shareholdersFull"));
                                ArrayList<Shareholder2> shareholdersFull2 = new ArrayList<>();
                                String toSend = "", id, name2, shareName, enterOrExit, percent, dateStr;
                                int date;
                                float pnl1 = 0F, pnl2 = 0F, pnl3 = 0F;
                                while (sc.hasNext()) {
                                    id = sc.nextLine();
                                    name2 = sc.nextLine();
                                    while (sc.hasNext()) {
                                        enterOrExit = sc.next();
                                        if (enterOrExit.equals("*"))
                                            break;
                                        shareName = sc.next();
                                        date = sc.nextInt();
                                        pnl1 = sc.nextFloat();
                                        pnl2 = sc.nextFloat();
                                        pnl3 = sc.nextFloat();
                                        if (nickName.equals(shareName) && date >= begin && date <= end){
                                            Share2 sh = new Share2();
                                            sh.shareName = shareName;
                                            sh.enterOrExit = enterOrExit;
                                            sh.date = String.valueOf(date);
                                            int i = 0;
                                            for(; i < shareholdersFull2.size(); i++){
                                                if(shareholdersFull2.get(i).id.equals(id) && shareholdersFull2.get(i).name.equals(name2))
                                                    break;
                                            }
                                            if(i < shareholdersFull2.size()){
                                                shareholdersFull2.get(i).prevShares.add(sh);
                                            }
                                            else {
                                                Shareholder2 shh = new Shareholder2();
                                                shh.id = id;
                                                shh.name = name2;
                                                shh.prevShares.add(sh);
                                                shareholdersFull2.add(shh);
                                            }
                                        }

                                    }
                                    sc.nextLine();
                                }
                                sc.close();
                                sc = new Scanner(new FileInputStream("shareholders"));
                                while (sc.hasNext()) {
                                    id = sc.nextLine();
                                    name2 = sc.nextLine();
                                    int i = 0;
                                    for(; i < shareholdersFull2.size(); i++){
                                        if(shareholdersFull2.get(i).id.equals(id) && shareholdersFull2.get(i).name.equals(name2))
                                            break;
                                    }
                                    if(i < shareholdersFull2.size()){
                                        if (name2.substring(0, 3).equals("BFM") || name2.substring(0, 3).equals("IFM") || name2.substring(0, 3).equals("PRX"))
                                            name2 = name2.substring(3, name2.length());
                                        String currentShares = "";
                                        while (true) {
                                            shareName = sc.next();
                                            if (shareName.equals("***"))
                                                break;
                                            sc.next();
                                            percent = sc.next();
                                            dateStr = sc.next();
                                            currentShares += shareName + " " + percent + "% ";
                                            if(!dateStr.equals("13800101"))
                                                currentShares += "ورود: " + formattedDate(dateStr);
                                            currentShares +=  "\n";
                                        }
                                        if(currentShares.length() > 0) {
                                            toSend += name2 + "\n" + "سهام فعلی:\n" + currentShares + "سابقه در " + nickName + ":\n";
                                            for (int j = 0; j < shareholdersFull2.get(i).prevShares.size(); j++) {
                                                toSend += shareholdersFull2.get(i).prevShares.get(j).enterOrExit + ": "
                                                        + formattedDate(shareholdersFull2.get(i).prevShares.get(j).date);
                                                if (j < shareholdersFull2.get(i).prevShares.size() - 1 &&
                                                        shareholdersFull2.get(i).prevShares.get(j).enterOrExit.equals("ورود") &&
                                                        shareholdersFull2.get(i).prevShares.get(j + 1).enterOrExit.equals("خروج"))
                                                    toSend += " ";
                                                else
                                                    toSend += "\n";
                                            }
                                            toSend += "\n";
                                            shareholdersFull2.remove(i);
                                        }
                                        sc.nextLine();
                                    }
                                    else
                                        sc.nextLine();
                                }
                                sc.close();
                                if(userID.equals(ID.FARDIN)) {
                                    for (int i = 0; i < shareholdersFull2.size(); i++) {
                                        toSend += shareholdersFull2.get(i).name + "\n" + "سابقه در " + nickName + ":\n";
                                        for (int j = 0; j < shareholdersFull2.get(i).prevShares.size(); j++) {
                                            toSend += shareholdersFull2.get(i).prevShares.get(j).enterOrExit + ": "
                                                    + formattedDate(shareholdersFull2.get(i).prevShares.get(j).date);
                                            if (j < shareholdersFull2.get(i).prevShares.size() - 1 &&
                                                    shareholdersFull2.get(i).prevShares.get(j).enterOrExit.equals("ورود") &&
                                                    shareholdersFull2.get(i).prevShares.get(j + 1).enterOrExit.equals("خروج"))
                                                toSend += " ";
                                            else
                                                toSend += "\n";
                                        }
                                        toSend += "\n";
                                    }
                                }
                                sendLongMessage(toSend, userID, false);
                                accessNumbers.set(userIndex, accessNumbers.get(userIndex) + 1);
                                FileWriter fileWriter = new FileWriter("shClients");
                                for (int i = 0; i < shClients.size(); i++)
                                    fileWriter.write(shClients.get(i) + " " + limits.get(i) + " " + accessNumbers.get(i) + " ");
                                fileWriter.close();
                            }
                        }
                        else if(userIndex >= 0 && accessNumbers.get(userIndex)>= limits.get(userIndex)) {
                            SendMessage message = new SendMessage().setChatId(userID)
                                    .setText("تعداد درخواست های شما به حداکثر مجاز رسیده است.");
                            execute(message);
                        }
                    }
                    catch (TelegramApiException | InterruptedException | IOException e){
                        e.printStackTrace();
                    }
                }
                if(messageText.equals("مانده")){
                    try{
                        ArrayList<String> shClients = new ArrayList<>();
                        ArrayList<Integer> limits = new ArrayList<>();
                        ArrayList<Integer> accessNumbers = new ArrayList<>();
                        Scanner sc = new Scanner(new FileInputStream("shClients"));
                        while (sc.hasNext()){
                            shClients.add(sc.next());
                            limits.add(sc.nextInt());
                            accessNumbers.add(sc.nextInt());
                        }
                        sc.close();
                        int userIndex = shClients.indexOf(userID);
                        if(userIndex >= 0){
                            SendMessage message = new SendMessage().setChatId(userID)
                                    .setText(String.valueOf(limits.get(userIndex) - accessNumbers.get(userIndex)));
                            execute(message);
                        }
                    }
                    catch (IOException | TelegramApiException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "tablo1bot";
//        return "test8542bot"; // TESTING
    }

    @Override
    public String getBotToken() {
        return "1313115577:AAHHgwkRdONQK9q_Y3APz1mB1AEPgmsnxh0";
//        return "1339815367:AAHbs17rd3S5uE3wQneKP9s9ybkF5UaF7fA"; // TESTING
    }
}

// https://api.telegram.org/bot1313115577:AAHHgwkRdONQK9q_Y3APz1mB1AEPgmsnxh0/sendMessage?chat_id=-1001500793717&text=aaaa
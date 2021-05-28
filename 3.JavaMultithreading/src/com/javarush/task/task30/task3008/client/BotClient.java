package com.javarush.task.task30.task3008.client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BotClient extends Client {

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + ((int)(Math.random() * 100));
    }

    public class BotSocketThread extends SocketThread {

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            super.processIncomingMessage(message);
            int delimiterIndex = message.indexOf(":");
            if (delimiterIndex == -1) return;
            Calendar calendar = new GregorianCalendar();
            SimpleDateFormat dateFormat = new SimpleDateFormat();
            String name = message.substring(0, delimiterIndex).trim();
            String text = message.substring(delimiterIndex + 1).trim();
            switch (text) {
                case "дата":
                    dateFormat.applyPattern("d.MM.yyyy");
                    break;
                case "день":
                    dateFormat.applyPattern("d");
                    break;
                case "месяц":
                    dateFormat.applyPattern("MMMM");
                    break;
                case "год":
                    dateFormat.applyPattern("yyyy");
                    break;
                case "время":
                    dateFormat.applyPattern("H:mm:ss");
                    break;
                case "час":
                    dateFormat.applyPattern("H");
                    break;
                case "минуты":
                    dateFormat.applyPattern("m");
                    break;
                case "секунды":
                    dateFormat.applyPattern("s");
                    break;
                default: return;
            }
            sendTextMessage(String.format("Информация для %s: %s", name, dateFormat.format(calendar.getTime())));
        }
    }

    public static void main(String[] args) {
        new BotClient().run();
    }
}

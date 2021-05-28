package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

public class Client {

    protected Connection connection;
    private volatile boolean clientConnected;

    protected String getServerAddress() {
        ConsoleHelper.writeMessage("Введите адресс сервера.");
        return ConsoleHelper.readString();
    }

    protected int getServerPort() {
        ConsoleHelper.writeMessage("Введите порт сервера.");
        return ConsoleHelper.readInt();
    }

    protected String getUserName() {
        ConsoleHelper.writeMessage("Введите имя.");
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole() {
        return true;
    }

    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Произошла ошибка при отправке сообщения.");
            clientConnected = false;
        }
    }

    public void run() {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException e) {
                ConsoleHelper.writeMessage("Во время ожидания произошло исключение.");
            }
        }
        if (clientConnected) {
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
        } else {
            ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
        }

        while (clientConnected) {
            String text = ConsoleHelper.readString();
            if ("exit".equals(text)) break;
            if (shouldSendTextFromConsole()) sendTextMessage(text);
        }
    }

    public class SocketThread extends Thread {

        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage(userName);
        }

        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage(userName);
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            synchronized (Client.this) {
                Client.this.clientConnected = clientConnected;
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException {
            while (true) {
                Message incomingMessage = connection.receive();
                if (incomingMessage.getType() == null) throw new IOException("Unexpected MessageType");
                switch (incomingMessage.getType()) {
                    case NAME_REQUEST:
                        if (incomingMessage.getData() != null) ConsoleHelper.writeMessage(incomingMessage.getData());
                        connection.send(new Message(MessageType.USER_NAME, getUserName()));
                        break;
                    case NAME_ACCEPTED:
                        notifyConnectionStatusChanged(true);
                        return;
                    default:
                        throw new IOException("Unexpected MessageType");
                }
            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            while (clientConnected) {
                Message incomingMessage = connection.receive();
                if (incomingMessage.getType() == null) throw new IOException("Unexpected MessageType");
                switch (incomingMessage.getType()) {
                    case TEXT:
                        processIncomingMessage(incomingMessage.getData());
                        break;
                    case USER_ADDED:
                        informAboutAddingNewUser(incomingMessage.getData());
                        break;
                    case USER_REMOVED:
                        informAboutDeletingNewUser(incomingMessage.getData());
                        break;
                    default:
                        throw new IOException("Unexpected MessageType");
                }
            }
        }

        @Override
        public void run() {
            try {
                connection = new Connection(new Socket(getServerAddress(), getServerPort()));
                clientHandshake();
                clientMainLoop();
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Произошла ошибка при подкдючении к серверу или чату.");
                notifyConnectionStatusChanged(false);
            }
        }
    }

    public static void main(String[] args) {
        new Client().run();
    }
}
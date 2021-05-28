package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void sendBroadcastMessage(Message message) {
        connectionMap.values().forEach(connection -> {
            try {
                connection.send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Ошибка при отправке сообщения.");
            }
        });
    }

    public static void main(String[] args) {

        ConsoleHelper.writeMessage("Ввведите порт сервера.");

        try (ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt())) {
            ConsoleHelper.writeMessage("Сервер запущен.");
            while (!serverSocket.isClosed()) {
                new Handler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Произошла ошибка при получении сокета клиента.");
        }
    }

    private static class Handler extends Thread {

        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {

            ConsoleHelper.writeMessage("Запрос имени клиента.");
            connection.send(new Message(MessageType.NAME_REQUEST));
            while (true) {
                Message message = connection.receive();
                if (message.getType() != MessageType.USER_NAME) {
                    ConsoleHelper.writeMessage("Передан неверный MessageType: " + message.getType() + ".");
                    connection.send(new Message(MessageType.NAME_REQUEST, "Передан неверный MessageType: " + message.getType() + "."));
                    continue;
                }
                if (message.getData().isEmpty()) {
                    ConsoleHelper.writeMessage("Передана пустая строка.");
                    connection.send(new Message(MessageType.NAME_REQUEST, "Имя не может быть пустым."));
                    continue;
                }
                if (connectionMap.containsKey(message.getData())) {
                    ConsoleHelper.writeMessage("Передано имя уже существующего пользователя.");
                    connection.send(new Message(MessageType.NAME_REQUEST, "Пользователь с таким именем уже существует."));
                    continue;
                }
                connectionMap.put(message.getData(), connection);
                connection.send(new Message(MessageType.NAME_ACCEPTED));
                ConsoleHelper.writeMessage("Имя принято.");
                return message.getData();
            }
        }

        private void notifyUsers(Connection connection, String userName) throws IOException {
            for (String name : connectionMap.keySet()) {
                if (!name.equals(userName)) connection.send(new Message(MessageType.USER_ADDED, name));
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (!interrupted()) {
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    sendBroadcastMessage(new Message(MessageType.TEXT, userName + ": " + message.getData()));
                } else {
                    ConsoleHelper.writeMessage("Сообщение не является текстом.");
                }
            }
        }

        @Override
        public void run() {
            ConsoleHelper.writeMessage("Соединение утановлено c " + socket.getRemoteSocketAddress());
            String userName = null;
            try (Connection connection = new Connection(socket)) {
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                notifyUsers(connection, userName);
                serverMainLoop(connection, userName);
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом.");
            }
            if (userName != null) {
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
            }
            ConsoleHelper.writeMessage("Соединение с удаленным адресом " + socket.getRemoteSocketAddress() + " закрыто");
        }
    }
}
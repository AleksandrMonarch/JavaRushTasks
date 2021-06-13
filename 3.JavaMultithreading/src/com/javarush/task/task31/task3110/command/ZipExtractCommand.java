package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.ZipFileManager;
import com.javarush.task.task31.task3110.exception.WrongZipFileException;

import java.nio.file.Paths;

public class ZipExtractCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        try {
            ConsoleHelper.writeMessage("Распокавка архива.");
            ZipFileManager zipFileManager = getZipFileManager();
            ConsoleHelper.writeMessage("Введите путь распаковки архива.");
            zipFileManager.extractAll(Paths.get(ConsoleHelper.readString()));
        } catch (WrongZipFileException e) {
            ConsoleHelper.writeMessage("Введен неверный путь файла архива.");
        }
    }
}
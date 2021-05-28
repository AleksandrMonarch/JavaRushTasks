package com.javarush.task.task31.task3110;

public enum Operation {
    CREATE("упаковать файлы в архив."),
    ADD("Добавить файл в архив."),
    REMOVE("Удалить файл из архива."),
    EXTRACT("Распаковать архив."),
    CONTENT("Просмотреть содержимое архива."),
    EXIT("Выход.");

    private final String description;

    Operation(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
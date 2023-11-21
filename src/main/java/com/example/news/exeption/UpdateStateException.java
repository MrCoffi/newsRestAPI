package com.example.news.exeption;

public class UpdateStateException extends RuntimeException{
    public UpdateStateException(String message) {
        super(message);
    }

    public UpdateStateException() {
        super("Не удалось изменить состояние объекта");
    }
}

package org.skypro.skyshopone.exception;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException() {
        super("Продукт не найден");
    }
}

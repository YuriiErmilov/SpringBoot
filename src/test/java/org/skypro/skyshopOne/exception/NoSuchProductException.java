package org.skypro.skyshopOne.exception;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException() {
        super("Продукт не найден");
    }
}

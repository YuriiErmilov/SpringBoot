package org.skypro.skyshopOne.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private final int price;

    public SimpleProduct(UUID id, String name, int price) {
        super(id,name);
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        } else {
            this.price = price;
        }
    }

    public int getPrice() {
        return this.price;
    }
}

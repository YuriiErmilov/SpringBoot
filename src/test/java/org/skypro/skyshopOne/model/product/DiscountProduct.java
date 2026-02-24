package org.skypro.skyshopOne.model.product;

import java.util.UUID;

public class DiscountProduct extends Product {
    private final int basePrice;
    private final int discount;

    public DiscountProduct(UUID id,String name, int price, int discount) {
        super(id,name);
        if (discount > 0 && discount <= 100) {
            this.basePrice = price;
            this.discount = discount;
        } else {
            throw new IllegalArgumentException("Discount must be between 0 and 100");
        }
    }

    public int getPrice() {
        return this.basePrice * (100 - this.discount) / 100;
    }

    public boolean isSpecial() {
        return true;
    }

    public String toString() {
        String var10000 = this.getName();
        return " Название : " + var10000 + " : " + this.getPrice() + " ( скидка " + this.discount + " %)";
    }
}

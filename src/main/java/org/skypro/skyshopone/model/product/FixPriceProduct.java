package org.skypro.skyshopone.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final int FIX_PRICE = 1000;


    public FixPriceProduct(UUID id,String name) {
        super(id,name);
    }

    public int getPrice() {
        return 1000;
    }

    public boolean isSpecial() {
        return true;
    }

    public String toString() {
        return " Название : " + this.getName() + ": фиксированная цена 1000";
    }

}

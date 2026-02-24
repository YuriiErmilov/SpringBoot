package org.skypro.skyshopOne.model.product;

import org.skypro.skyshopOne.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final String name;
    private final UUID id;

    public Product(UUID id, String name) {
        if (id == null) {
            throw   new IllegalArgumentException("id cannot be null");
        } if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be null");
        }
        this.id = id;
        this.name = name;
    }

    @Override
    public UUID getId() {
        return id;
    }


    public String getSearchTerm() {
        return this.name;
    }

    public String getSearchableType() {
        return "PRODUCT";
    }

    public String getName() {
        return this.name;
    }

    public abstract int getPrice();

    public boolean isSpecial() {
        return false;
    }

    public String toString() {
        String var10000 = this.name;
        return " Название : " + var10000 + " : " + this.getPrice();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Product product = (Product)o;
            return Objects.equals(this.name, product.name);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.name});
    }


}

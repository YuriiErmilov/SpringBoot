package org.skypro.skyshopOne.model.basket;

import org.skypro.skyshopOne.model.product.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Component
@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> basketProducts = new HashMap<>();

    public void addProduct(UUID id) {
        basketProducts.merge(id, 1, Integer::sum);
    }


    public Map<UUID, Integer> getBasketProducts() {
        return Collections.unmodifiableMap(basketProducts);
    }
}

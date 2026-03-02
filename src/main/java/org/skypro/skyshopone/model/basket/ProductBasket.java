package org.skypro.skyshopone.model.basket;

import org.springframework.stereotype.Component;
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

package org.skypro.skyshopone.service;


import org.skypro.skyshopone.exception.NoSuchProductException;
import org.skypro.skyshopone.model.basket.BasketItem;
import org.skypro.skyshopone.model.basket.ProductBasket;
import org.skypro.skyshopone.model.basket.UserBasket;
import org.skypro.skyshopone.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class BasketService {
    private final StorageService storageService;
    private final ProductBasket basketProducts;

    public BasketService(StorageService storageService, ProductBasket basketProducts) {
        this.storageService = storageService;
        this.basketProducts = basketProducts;
    }

    public void addProduct (UUID id) {
        Optional<Product> product = storageService.getProductById(id);
        if(product.isEmpty()) {
            throw new NoSuchProductException();
        }
        basketProducts.addProduct(id);
    }

    public UserBasket getUserBasket() {
        Map<UUID, Integer> basketMap = basketProducts.getBasketProducts();

        List<BasketItem> basketItems = basketMap.entrySet().stream().map(entry -> {
            Product product = storageService.getProductById(entry.getKey())
                    .orElseThrow(NoSuchProductException::new);
            return new BasketItem(product, entry.getValue());
        }).collect(Collectors.toList());
        return new UserBasket(basketItems);
    }
}

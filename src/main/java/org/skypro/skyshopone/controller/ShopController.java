package org.skypro.skyshopone.controller;

import org.skypro.skyshopone.model.article.Article;
import org.skypro.skyshopone.model.basket.UserBasket;
import org.skypro.skyshopone.model.product.Product;
import org.skypro.skyshopone.model.search.SearchResult;
import org.skypro.skyshopone.service.BasketService;
import org.skypro.skyshopone.service.SearchService;
import org.skypro.skyshopone.service.StorageService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
public class ShopController {
    private final StorageService storageService;
    private final SearchService searchService;
     private final BasketService basketService;

    public ShopController(StorageService storageService, SearchService searchService, BasketService basketService) {
        this.storageService = storageService;
        this.searchService = searchService;
        this.basketService = basketService;
    }

    @GetMapping("/products")
    public Collection<Product> getProducts() {
        return storageService.getProducts();
    }
    @GetMapping("/articles")
    public Collection<Article> getArticles() {
        return storageService.getArticles();
    }
    @GetMapping("/search")
    public Collection<SearchResult> search(@RequestParam String pattern) {
        return searchService.search(pattern);
    }

    @GetMapping("/basket/{id}")
        public String addProduct(@PathVariable("id")UUID id) {
        basketService.addProduct(id);
        return "Продукт успешно добавлен";
    }

    @GetMapping("/basket")
    public UserBasket getUserBasket() {
        return basketService.getUserBasket();
    }

}

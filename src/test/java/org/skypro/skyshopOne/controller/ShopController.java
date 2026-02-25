package org.skypro.skyshopOne.controller;

import org.skypro.skyshopOne.model.article.Article;
import org.skypro.skyshopOne.model.product.Product;
import org.skypro.skyshopOne.model.search.SearchResult;
import org.skypro.skyshopOne.service.SearchService;
import org.skypro.skyshopOne.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ShopController {
    private final StorageService storageService;
    private final SearchService searchService;

    public ShopController(StorageService storageService, SearchService searchService) {
        this.storageService = storageService;
        this.searchService = searchService;
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
}

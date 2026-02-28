package org.skypro.skyshopone.service;

import org.skypro.skyshopone.model.article.Article;
import org.skypro.skyshopone.model.product.DiscountProduct;
import org.skypro.skyshopone.model.product.FixPriceProduct;
import org.skypro.skyshopone.model.product.Product;
import org.skypro.skyshopone.model.product.SimpleProduct;
import org.skypro.skyshopone.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StorageService {
    private final Map<UUID, Product> products;
    private final Map<UUID, Article>  articles;

    public StorageService() {
        this.products = new ConcurrentHashMap<>();
        this.articles = new ConcurrentHashMap<>();
        initestData();
    }
    private void initestData() {
        Product hookah = new SimpleProduct(UUID.randomUUID(), "Кальян", 9000);
        Product colaud = new FixPriceProduct(UUID.randomUUID(), "Калауд");
        Product cup = new DiscountProduct(UUID.randomUUID(),"Чаша", 500, 20);
        products.put(hookah.getId(), hookah);
        products.put(colaud.getId(), colaud);
        products.put(cup.getId(), cup);

        Article article1 = new Article(UUID.randomUUID(), "Как выбрать кальян","Обратите внимание на то как вы хотите курить" );
        Article article2 = new Article(UUID.randomUUID(), "Какую чашу выбрать первой","Важно понимать");

        articles.put(article1.getId(), article1);
        articles.put(article2.getId(), article2);
    }
    public Collection<Product> getProducts() {
        return products.values();
    }

    public Collection<Article> getArticles() {
        return articles.values();
    }
    public Collection<Searchable> getAllSearchables() {
        return Stream.concat(
                products.values().stream(),
                articles.values().stream()
        ).collect(Collectors.toList());
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }


}

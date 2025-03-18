package org.skypro.skyshop.service;

import org.apache.tomcat.util.http.parser.Cookie;
import org.skypro.skyshop.model.Article;
import org.skypro.skyshop.model.product.Electronic;
import org.skypro.skyshop.model.product.Food;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map <UUID, Product> products = new HashMap<>();
    private final Map<UUID, Article> articles = new HashMap<>();

    public StorageService () {
        initializeData ();
    }
    private void initializeData () {
        products.put(UUID.randomUUID(), new Electronic(UUID.randomUUID(), "Ноутбук", 200_000, "MSI"));
        products.put(UUID.randomUUID(), new Electronic(UUID.randomUUID(), "Телефон", 50_000, "Samsung"));
        products.put(UUID.randomUUID(), new Food(UUID.randomUUID(), "Яблоко", 50, "20.12.2025"));
        products.put(UUID.randomUUID(), new Food(UUID.randomUUID(), "Груша", 30, "21.12.2025"));

        articles.put(UUID.randomUUID(),new Article(UUID.randomUUID(), "Яблочное варенье", "Как сделать варенье из яблок"));
        articles.put(UUID.randomUUID(),new Article(UUID.randomUUID(), "Грушевое варенье", "Как сделать варенье из груш"));

    }
    public Collection<Product> getAllProducts () {
        return products.values();
    }
    public Collection<Article> getAllArticles () {
        return articles.values();
    }
    public Collection <Searchable> getAllSearchable () {
        List<Searchable> allItems = new ArrayList<>();
        allItems.addAll(products.values());
        allItems.addAll(articles.values());
        return allItems;
    }
}

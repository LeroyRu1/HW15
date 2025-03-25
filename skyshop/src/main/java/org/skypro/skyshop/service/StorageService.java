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
    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }

    private void initializeData () {

        var id1 = UUID.fromString("4fbd6db7-9706-4998-a7b2-e9298f1dcdf7");
        var id2 = UUID.fromString("16303b8b-f138-4d14-919c-11d9d318dce2");
        var id3 = UUID.fromString("1baf4791-bd33-4be7-93ae-4b9678dd53f6");
        var id4 = UUID.fromString("936bf3e4-75c9-424a-aeb5-eeed966838b3");
        var id5 = UUID.fromString("936bf3e4-75c9-425a-aeb5-eeed966838b3");
        var id6 = UUID.fromString("936bf3e4-75c9-426a-aeb5-eeed966838b3");

        products.put(id1, new Electronic( id1,"Ноутбук", 200_000, "MSI"));
        products.put(id2, new Electronic(id2, "Телефон", 50_000, "Samsung"));
        products.put(id3, new Food(id3, "Яблоко", 50, "20.12.2025"));
        products.put(id4, new Food(id4, "Груша", 30, "21.12.2025"));

        articles.put(id5,new Article(id5, "Яблочное варенье", "Как сделать варенье из яблок"));
        articles.put(id6,new Article(id6, "Грушевое варенье", "Как сделать варенье из груш"));

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

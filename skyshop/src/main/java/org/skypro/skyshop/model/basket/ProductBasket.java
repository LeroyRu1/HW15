package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Component
@SessionScope
public class ProductBasket {
    private final Map <UUID, Integer> items = new HashMap<>();
    public void addProduct (UUID id) {
        items.put(id, items.getOrDefault(id, 0) + 1);
    }
    public Map <UUID, Integer> getAllItems() {
        return Collections.unmodifiableMap(items);
    }
}

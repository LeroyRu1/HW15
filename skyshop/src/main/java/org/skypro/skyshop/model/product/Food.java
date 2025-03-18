package org.skypro.skyshop.model.product;

import java.util.UUID;

public class Food extends Product {
    private final String expirationDate;

    public Food (UUID id, String name, double price, String expirationDate) {
        super(id, name, price);
        this.expirationDate = expirationDate;
    }
    public String getExpirationDate () {
        return expirationDate;
    }

}

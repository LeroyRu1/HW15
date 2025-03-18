package org.skypro.skyshop.model.product;

import java.util.UUID;

public class Electronic extends Product {
    private final String brand;

    public Electronic (UUID id, String name, double price, String brand) {
        super(id, name, price);
        this.brand = brand;
    }
    public String getBrand () {
        return brand;
    }

}

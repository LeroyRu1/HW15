package org.skypro.skyshop.model;

public class ShopError {
    private final String code;
    private final String message;
     public ShopError (String code, String massage) {
         this.code = code;
         this.message = massage;
     }

    public String getCode() {
        return code;
    }

    public String getMassage() {
        return message;
    }
}

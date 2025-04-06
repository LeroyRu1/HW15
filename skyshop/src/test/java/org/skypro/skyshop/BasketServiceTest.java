package org.skypro.skyshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    private UUID productId;
    private Product product;

    @BeforeEach
    void setUp() {
        productId = UUID.randomUUID();
        product = mock(Product.class);
    }

    @Test
    void addProduct_WhenProductNotFound_ShouldThrowException() {
        when(storageService.getProductById(productId)).thenThrow(new NoSuchProductException("Прлдукт не найден"));
        assertThrows(NoSuchProductException.class, () -> basketService.addProduct(productId));
    }

    @Test
    void addProduct_WhenProductExists_ShouldCallAddProductOnBasket() {
        when(storageService.getProductById(productId)).thenReturn(product);
        basketService.addProduct(productId);
        verify(productBasket).addProduct(productId);
    }

    @Test
    void getUserBasket_WhenBasketIsEmpty_ShouldReturnEmptyUserBasket() {
        when(productBasket.getAllItems()).thenReturn(Collections.emptyMap());
        UserBasket userBasket = basketService.getUserBasket();
        assertTrue(userBasket.getItems().isEmpty());
        assertEquals(0, userBasket.getTotal());
    }

    @Test
    void getUserBasket_WhenBasketHasItems_ShouldReturnCorrectUserBasket() {
        when(productBasket.getAllItems()).thenReturn(Map.of(productId, 2));
        when(storageService.getProductById(productId)).thenReturn(product);
        when(product.getPrice()).thenReturn(100.0);

        UserBasket userBasket = basketService.getUserBasket();

        assertFalse(userBasket.getItems().isEmpty());
        assertEquals(200.0, userBasket.getTotal());
    }

}

package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.service.StorageService;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StorageServiceTest {
    @Mock
    private Map<UUID, Product> products;

    @InjectMocks
    private StorageService storageService;

    @Test
    void getProductById_FixedId_ReturnsProduct() {
        UUID fixedId = UUID.fromString("16303b8b-f138-4d14-919c-11d9d318dcc3");
        Product product = mock(Product.class);

        when(products.get(fixedId)).thenReturn(product);
        Product result = storageService.getProductById(fixedId);

        assertEquals(product, result);
    }

    @Test
    void getProductById_NonExistingProduct_ThrowsException() {
        UUID productId = UUID.fromString("");
        assertThrows(NoSuchProductException.class, () -> storageService.getProductById(productId));
    }
}

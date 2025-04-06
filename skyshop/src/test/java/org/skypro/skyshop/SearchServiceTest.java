package org.skypro.skyshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.Article;
import org.skypro.skyshop.model.product.Electronic;
import org.skypro.skyshop.model.product.Food;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.product.Electronic;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {
    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @BeforeEach
    void setUp() {
        when(storageService.getAllSearchable()).thenReturn(Collections.emptyList());
    }

    @Test
    void searchReturnsEmptyWhenNoObjects() {
        List<SearchResult> results = (List<SearchResult>) searchService.search("");
        assertTrue(results.isEmpty());
    }

    @Test
    void searchReturnsEmptyWhenNoMatchingObjects() {
        Product product = Mockito.mock(Product.class);
        when(product.getSearchTerm()).thenReturn("Яблоко");
        when(storageService.getAllSearchable()).thenReturn(List.of(product));

        List<SearchResult> results = (List<SearchResult>) searchService.search("Банан");
        assertTrue(results.isEmpty());
    }

    @Test
    void searchReturnsResultsWhenMatchingObjectsExist() {
        Product product = Mockito.mock(Product.class);
        UUID mockId = UUID.randomUUID();
        when(product.getId()).thenReturn(mockId);
        when(product.getSearchTerm()).thenReturn("Яблоко");
        when(storageService.getAllSearchable()).thenReturn(List.of(product));

        List<SearchResult> results = (List<SearchResult>) searchService.search("Яблоко");
        assertFalse(results.isEmpty());
    }


}

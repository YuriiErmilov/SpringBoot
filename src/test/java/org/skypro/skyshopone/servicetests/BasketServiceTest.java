package org.skypro.skyshopone.servicetests;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshopone.exception.NoSuchProductException;
import org.skypro.skyshopone.model.basket.ProductBasket;
import org.skypro.skyshopone.model.basket.UserBasket;
import org.skypro.skyshopone.model.product.Product;
import org.skypro.skyshopone.service.BasketService;
import org.skypro.skyshopone.service.SearchService;
import org.skypro.skyshopone.service.StorageService;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    private StorageService storageService;

    @Mock
    private ProductBasket  productBasket;

    @InjectMocks
    private BasketService basketService;

    @Test
    void addProductTrowsExceptionWhenProductNoExists(){
        UUID id = UUID.randomUUID();

        when(storageService.getProductById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class, () -> basketService.addProduct(id));
    }

    @Test
    void addProductClassBasketWhenProductExists() {
        UUID id = UUID.randomUUID();

        Product product = mock(Product.class);

        when(storageService.getProductById(id)).thenReturn(Optional.of(product));
        basketService.addProduct(id);
        verify(productBasket).addProduct(id);
    }

    @Test
    void getUserBasketReturnEmptyBasket() {
        when(productBasket.getBasketProducts()).thenReturn(Collections.emptyMap());
        UserBasket result = basketService.getUserBasket();

        assertTrue(result.getBasketItems().isEmpty());
        assertEquals(0, result.getTotal());
    }

    @Test
    void getUserBasketCalculatesTotalCorrectly() {
        UUID id = UUID.randomUUID();
        Product product = mock(Product.class);
        when(product.getPrice()).thenReturn(100);
        when(storageService.getProductById(id)).thenReturn(Optional.of(product));

        when(productBasket.getBasketProducts()).thenReturn(Map.of(id, 2));

        UserBasket result = basketService.getUserBasket();

        assertEquals(1, result.getBasketItems().size());
        assertEquals(100, result.getTotal());
    }


}

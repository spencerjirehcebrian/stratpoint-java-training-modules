package com.miniproject.three;

import com.miniproject.three.entity.Product;
import com.miniproject.three.entity.cart.Cart;
import com.miniproject.three.entity.cart.CartHistory;
import com.miniproject.three.entity.products.*;
import com.miniproject.three.services.impl.CartServiceImpl;
import com.miniproject.three.services.impl.ProductServiceImpl;
import com.miniproject.three.services.impl.UserInterfaceServiceImpl;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class UserInterfaceServiceImplTest {

    private ProductServiceImpl productServiceMock;
    private CartServiceImpl cartServiceMock;
    private UserInterfaceServiceImpl userInterfaceService;
    private Cart cart;
    private Product phone;
    private Product shirt;
    private Product apple;
    private Product book;
    private Product table;

    @Before
    public void setUp() {
        productServiceMock = mock(ProductServiceImpl.class);
        cartServiceMock = mock(CartServiceImpl.class);
        userInterfaceService = new UserInterfaceServiceImpl(productServiceMock, cartServiceMock);

        phone = new ElectronicProduct("1001", "Smartphone", 699.99, "24 months warranty", 1);
        shirt = new ClothingProduct("2002", "T-Shirt", 19.99, "L", "Blue", 1);
        apple = new FoodProduct("3003", "Apple", 0.99, "2023-12-31", 1);
        book = new BookProduct("4004", "Java Programming", 49.99, "Author Name", 1);
        table = new FurnitureProduct("5005", "Dining Table", 299.99, "Wood", 1);

        cart = new Cart();
        cart.addProduct(phone);
        cart.addProduct(shirt);
        cart.addProduct(apple);
        cart.addProduct(book);
        cart.addProduct(table);
        when(cartServiceMock.getCart()).thenReturn(cart);
    }

    @Test
    public void testPrintDataElectronicProduct() {
        when(productServiceMock.getProductById("1001")).thenReturn(phone);
        userInterfaceService.printData("1001", false);
        verify(productServiceMock, times(1)).getProductById("1001");
    }

    @Test
    public void testPrintDataClothingProduct() {
        when(productServiceMock.getProductById("2002")).thenReturn(shirt);
        userInterfaceService.printData("2002", false);
        verify(productServiceMock, times(1)).getProductById("2002");
    }

    @Test
    public void testPrintDataFoodProduct() {
        when(productServiceMock.getProductById("3003")).thenReturn(apple);
        userInterfaceService.printData("3003", false);
        verify(productServiceMock, times(1)).getProductById("3003");
    }

    @Test
    public void testPrintDataBookProduct() {
        when(productServiceMock.getProductById("4004")).thenReturn(book);
        userInterfaceService.printData("4004", false);
        verify(productServiceMock, times(1)).getProductById("4004");
    }

    @Test
    public void testPrintDataFurnitureProduct() {
        when(productServiceMock.getProductById("5005")).thenReturn(table);
        userInterfaceService.printData("5005", false);
        verify(productServiceMock, times(1)).getProductById("5005");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPrintDataProductNotFound() {
        when(productServiceMock.getProductById("9999")).thenReturn(null);
        userInterfaceService.printData("9999", false);
    }

    @Test
    public void testPrintDataCart() {
        userInterfaceService.printData(phone.getCartId(), true);
        verify(cartServiceMock, times(1)).getCart();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPrintDataCartNotFound() {
        when(cartServiceMock.getCart()).thenReturn(new Cart());
        userInterfaceService.printData("9999", true);
    }

    @Test
    public void testPrintCartHistory() {
        List<CartHistory> cartHistoryList = new ArrayList<>();
        CartHistory cartHistory = new CartHistory(new Date(), true, cart);
        cartHistoryList.add(cartHistory);

        when(cartServiceMock.getCartHistory()).thenReturn(cartHistoryList);
        userInterfaceService.printCartHistory(cartHistoryList);
        verify(cartServiceMock, times(1)).getCartHistory();
    }
}

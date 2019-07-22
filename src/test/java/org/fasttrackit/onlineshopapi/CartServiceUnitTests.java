package org.fasttrackit.onlineshopapi;

import org.fasttrackit.onlineshopapi.domain.Cart;
import org.fasttrackit.onlineshopapi.domain.Customer;
import org.fasttrackit.onlineshopapi.domain.Product;
import org.fasttrackit.onlineshopapi.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshopapi.repository.CartRepository;
import org.fasttrackit.onlineshopapi.service.CartService;
import org.fasttrackit.onlineshopapi.service.CustomerService;
import org.fasttrackit.onlineshopapi.service.ProductService;
import org.fasttrackit.onlineshopapi.transfer.cart.AddProductToCartRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceUnitTests {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CustomerService customerService;

    @Mock
    private ProductService productService;

    // we don't mock the tested service!
    private CartService cartService;

    @Before
    public void setup() {
        cartService = new CartService(
                cartRepository, customerService, productService);
    }

    @Test
    public void testAddToCart_whenValidRequestForNewCart_thenProductIsAddedToCart() throws ResourceNotFoundException {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName("Mock first name");
        customer.setLastName("Mock last name");
        customer.setAge(10);

        when(customerService.getCustomer(anyLong())).thenReturn(customer);

        when(cartRepository.findById(anyLong())).thenReturn(Optional.empty());

        Product product = new Product();
        product.setId(1);
        product.setName("Mock product");
        product.setQuantity(10);
        product.setPrice(100);

        when(productService.getProduct(anyLong())).thenReturn(product);

        when(cartRepository.save(any(Cart.class))).thenReturn(null);

        AddProductToCartRequest request = new AddProductToCartRequest();
        request.setCustomerId(1L);
        request.setProductId(1L);

        cartService.addProductToCart(request);

        verify(customerService).getCustomer(anyLong());
        verify(cartRepository).findById(anyLong());
        verify(productService).getProduct(anyLong());
        verify(cartRepository).save(any(Cart.class));
    }
}

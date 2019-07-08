package org.fasttrackit.onlineshopapi.service;

import org.fasttrackit.onlineshopapi.domain.Cart;
import org.fasttrackit.onlineshopapi.domain.Customer;
import org.fasttrackit.onlineshopapi.domain.Product;
import org.fasttrackit.onlineshopapi.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshopapi.repository.CartRepository;
import org.fasttrackit.onlineshopapi.transfer.cart.AddProductToCartRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

    private final CartRepository cartRepository;
    private final CustomerService customerService;
    private final ProductService productService;

    @Autowired
    public CartService(CartRepository cartRepository, CustomerService customerService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.customerService = customerService;
        this.productService = productService;
    }

    @Transactional
    public void addProductToCart(AddProductToCartRequest request) throws ResourceNotFoundException {
        LOGGER.info("Saving cart {}", request);

        Customer customer = customerService.getCustomer(request.getCustomerId());

        Cart cart = new Cart();
        cart.setCustomer(customer);

        Product product = productService.getProduct(request.getProductId());
        cart.addProduct(product);

        cartRepository.save(cart);
    }

    @Transactional
    public Cart getCart(Long customerId) throws ResourceNotFoundException {
        return cartRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cart " + customerId + " does not exist"));
    }
}

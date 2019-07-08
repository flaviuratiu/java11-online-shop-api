package org.fasttrackit.onlineshopapi.service;

import org.fasttrackit.onlineshopapi.domain.Cart;
import org.fasttrackit.onlineshopapi.domain.Customer;
import org.fasttrackit.onlineshopapi.domain.Product;
import org.fasttrackit.onlineshopapi.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshopapi.repository.CartRepository;
import org.fasttrackit.onlineshopapi.transfer.cart.AddProductToCartRequest;
import org.fasttrackit.onlineshopapi.transfer.cart.CartResponse;
import org.fasttrackit.onlineshopapi.transfer.customer.CustomerResponse;
import org.fasttrackit.onlineshopapi.transfer.product.ProductResponse;
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
    public CartResponse getCart(Long customerId) throws ResourceNotFoundException {
        Cart cart = cartRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cart " + customerId + " does not exist"));

        // using a DTO and reading all necessary properties (with getters)
        // to avoid LazyInitializationException when properties are loaded lazily
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(cart.getCustomer().getId());
        customerResponse.setFirstName(cart.getCustomer().getFirstName());
        customerResponse.setLastName(cart.getCustomer().getLastName());

        CartResponse cartResponse = new CartResponse();
        cartResponse.setId(cart.getId());
        cartResponse.setCustomer(customerResponse);

        cart.getProducts().forEach(product -> {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());

            cartResponse.getProducts().add(productResponse);
        });

        return cartResponse;
    }
}

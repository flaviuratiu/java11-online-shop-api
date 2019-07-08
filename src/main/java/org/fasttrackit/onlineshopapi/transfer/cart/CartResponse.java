package org.fasttrackit.onlineshopapi.transfer.cart;

import org.fasttrackit.onlineshopapi.transfer.customer.CustomerResponse;
import org.fasttrackit.onlineshopapi.transfer.product.ProductResponse;

import java.util.HashSet;
import java.util.Set;

public class CartResponse {

    private Long id;
    private CustomerResponse customer;
    private Set<ProductResponse> products = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductResponse> products) {
        this.products = products;
    }

    public CustomerResponse getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerResponse customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "CartResponse{" +
                "id=" + id +
                ", customer=" + customer +
                '}';
    }
}

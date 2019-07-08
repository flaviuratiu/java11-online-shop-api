package org.fasttrackit.onlineshopapi.transfer.cart;

import org.fasttrackit.onlineshopapi.transfer.customer.CustomerResponse;

public class CartResponse {

    private Long id;
    private CustomerResponse customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

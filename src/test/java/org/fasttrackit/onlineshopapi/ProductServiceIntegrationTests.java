package org.fasttrackit.onlineshopapi;

import org.fasttrackit.onlineshopapi.domain.Product;
import org.fasttrackit.onlineshopapi.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshopapi.service.ProductService;
import org.fasttrackit.onlineshopapi.steps.ProductSteps;
import org.fasttrackit.onlineshopapi.transfer.CreateProductRequest;
import org.fasttrackit.onlineshopapi.transfer.UpdateProductRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceIntegrationTests {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSteps productSteps;

    @Test
    public void testCreateProduct_whenValidRequest_thenReturnCreatedProduct() {
        productSteps.createProduct();
    }

    @Test(expected = TransactionSystemException.class)
    public void testCreateProduct_whenMissingMandatoryProperties_thenThrowException() {
        CreateProductRequest request = new CreateProductRequest();
        productService.createProduct(request);
    }

    @Test
    public void testGetProduct_whenExistingId_thenReturnProduct() throws ResourceNotFoundException {
        Product createdProduct = productSteps.createProduct();

        Product product = productService.getProduct(createdProduct.getId());

        assertThat(product, notNullValue());
        assertThat(product.getId(), is(createdProduct.getId()));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetProduct_whenNonExistingId_thenThrowResourceNotFoundException() throws ResourceNotFoundException {
        productService.getProduct(9999L);
    }

    @Test
    public void testUpdateProduct_whenValidRequest_thenReturnUpdatedProduct() throws ResourceNotFoundException {
        Product createdProduct = productSteps.createProduct();

        UpdateProductRequest request = new UpdateProductRequest();
        request.setName(createdProduct.getName() + "Updated");
        request.setPrice(createdProduct.getPrice() + 10);
        request.setQuantity(createdProduct.getQuantity() + 10);

        Product updatedProduct = productService.updateProduct(createdProduct.getId(), request);

        assertThat(updatedProduct, notNullValue());
        assertThat(updatedProduct.getId(), is(createdProduct.getId()));

        assertThat(updatedProduct.getQuantity(), not(is(createdProduct.getQuantity())));
        assertThat(updatedProduct.getQuantity(), is(request.getQuantity()));

        assertThat(updatedProduct.getPrice(), not(is(createdProduct.getPrice())));
        assertThat(updatedProduct.getPrice(), is(request.getPrice()));

        assertThat(updatedProduct.getName(), not(is(createdProduct.getName())));
        assertThat(updatedProduct.getName(), is(request.getName()));
    }
}

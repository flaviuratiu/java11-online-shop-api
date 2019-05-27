package org.fasttrackit.onlineshopapi.transfer;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UpdateProductRequest {

    @NotNull
    private String name;
    @NotNull
    @Min(0)
    private int quantity;
    @NotNull
    private double price;
    private String imagePath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "CreateProductRequest{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}

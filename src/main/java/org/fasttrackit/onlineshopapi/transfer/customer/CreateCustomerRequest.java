package org.fasttrackit.onlineshopapi.transfer.customer;

import javax.validation.constraints.NotNull;

public class CreateCustomerRequest {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private double age;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "CreateCustomerRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}

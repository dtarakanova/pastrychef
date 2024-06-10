package org.example.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CakeOrder {
    @NotBlank(message = "Your first name is required")
    private String firstName;

    @NotBlank(message = "Your last name is required")
    private String lastName;

    @NotBlank(message = "Your city is required")
    private String city;

    @NotBlank(message = "Your address is required")
    private String address;

    private List<Cake> cakes = new ArrayList<>();

    public void addCake(Cake cake) {
        this.cakes.add(cake);
    }

}

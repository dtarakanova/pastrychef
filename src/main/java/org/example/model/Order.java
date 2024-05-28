package org.example.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Order {
    private String firstName;
    private String lastName;
    private String city;
    private String address;
    private String cardNumber;
    private String cardExpiration;
    private Integer cardCVV;
    private List<Cake> cakes = new ArrayList<>();

    public void addCake(Cake cake) {
        this.cakes.add(cake);
    }


}

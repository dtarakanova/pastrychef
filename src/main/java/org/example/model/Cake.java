package org.example.model;

import lombok.Data;

import java.util.List;

@Data

public class Cake {
    private String inscription;
    private List<Ingredient> ingredients;
}

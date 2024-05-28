package org.example.model;

import com.fasterxml.jackson.databind.util.EnumValues;
import lombok.Data;

@Data
public class Ingredient {
    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        FILLING, ICING, WEIGHT
    }
}

package org.example;

import org.example.model.Ingredient;
import org.example.model.Ingredient.Type;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
    private Map<String, Ingredient> ingredientMap = new HashMap<>();

    public IngredientByIdConverter() {
        ingredientMap.put("CHCK",
                new Ingredient("CHCK", "Cheesecake", Type.FILLING));
        ingredientMap.put("BLFO",
                new Ingredient("BLFO", "Black Forrest", Type.FILLING));
        ingredientMap.put("STCR",
                new Ingredient("STCR", "Strawberry Cream", Type.FILLING));
        ingredientMap.put("CARR",
                new Ingredient("CARR", "Carrot Cake", Type.FILLING));
        ingredientMap.put("PRAL",
                new Ingredient("PRAL", "Praline", Type.FILLING));
        ingredientMap.put("WHIT",
                new Ingredient("WHIT","White", Type.ICING));
        ingredientMap.put("PINK",
                new Ingredient("PINK","Pink", Type.ICING));
        ingredientMap.put("BLUE",
                new Ingredient("BLUE","Blue", Type.ICING));
        ingredientMap.put("YELL",
                new Ingredient("YELL","Yellow", Type.ICING));
        ingredientMap.put("GREN",
                new Ingredient("GREN","Green", Type.ICING));
        ingredientMap.put("SMLL",
                new Ingredient("SMLL", "2 kg", Type.WEIGHT));
        ingredientMap.put("MEDI",
                new Ingredient("MEDI", "4 kg", Type.WEIGHT));
        ingredientMap.put("LARG",
                new Ingredient("LARG", "6 kg", Type.WEIGHT));
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientMap.get(id);
    }
}
package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.model.Cake;
import org.example.model.Ingredient;
import org.example.model.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.model.Ingredient.Type.*;

@Slf4j
@Controller
@RequestMapping("/designcake")
@SessionAttributes("cakeOrder")

public class DesignCakeController {
@ModelAttribute
    public void addIngredientsToModel (Model model) {
    List<Ingredient> ingredients = Arrays.asList(
            new Ingredient("CHCK", "Cheesecake", FILLING),
            new Ingredient("BLFO", "Black Forrest", FILLING),
            new Ingredient("STCR", "Strawberry Cream", FILLING),
            new Ingredient("CARR", "Carrot Cake", FILLING),
            new Ingredient("PRAL", "Praline", FILLING),
            new Ingredient("WHIT","White", ICING),
            new Ingredient("PINK","Pink", ICING),
            new Ingredient("BLUE","Blue", ICING),
            new Ingredient("YELL","Yellow", ICING),
            new Ingredient("YELL","Green", ICING),
            new Ingredient("SMLL", "2KG", WEIGHT),
            new Ingredient("MEDI", "4KG", WEIGHT),
            new Ingredient("LARG", "6KG", WEIGHT)
            );

    //Фильтруем ингр-ты по типам:

    Ingredient.Type[] types = values();
    for (Ingredient.Type type : types) {
        model.addAttribute(type.toString().toLowerCase(),
                filterByType(ingredients, type));
    }
}

@ModelAttribute(name = "cakeOrder")
public Order order() {
    return new Order();
}

@ModelAttribute(name = "cake")
public Cake cake() {
    return new Cake();
}

@GetMapping
public String showDesignForm() {
    return "designform";
}


    private Object filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
    return ingredients
            .stream()
            .filter(x -> x.getType().equals(type))
            .collect(Collectors.toList());
    }
}

package org.example.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Cake;
import org.example.model.Ingredient;
import org.example.model.Ingredient.Type;
import org.example.model.CakeOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("cakeOrder")

public class DesignCakeController {
@ModelAttribute
    public void addIngredientsToModel (Model model) {
    List<Ingredient> ingredients = Arrays.asList(
            new Ingredient("CHCK", "Cheesecake", Type.FILLING),
            new Ingredient("BLFO", "Black Forrest", Type.FILLING),
            new Ingredient("STCR", "Strawberry Cream", Type.FILLING),
            new Ingredient("CARR", "Carrot Cake", Type.FILLING),
            new Ingredient("PRAL", "Praline", Type.FILLING),
            new Ingredient("WHIT","White", Type.ICING),
            new Ingredient("PINK","Pink", Type.ICING),
            new Ingredient("BLUE","Blue", Type.ICING),
            new Ingredient("YELL","Yellow", Type.ICING),
            new Ingredient("GREN","Green", Type.ICING),
            new Ingredient("SMLL", "2 kg", Type.WEIGHT),
            new Ingredient("MEDI", "4 kg", Type.WEIGHT),
            new Ingredient("LARG", "6 kg", Type.WEIGHT)
            );

    //Фильтруем ингр-ты по типам:
    Type[] types = Ingredient.Type.values();
    for (Type type : types) {
        model.addAttribute(type.toString().toLowerCase(),
                filterByType(ingredients, type));
    }
}

@ModelAttribute(name = "cakeOrder")
public CakeOrder order() {
    return new CakeOrder();
}

@ModelAttribute(name = "cake")
public Cake cake() {
    return new Cake();
}

@GetMapping
public String showDesignForm() {
    return "designform";
}


@PostMapping
public String processCake(@Valid Cake cake, Errors errors, @ModelAttribute CakeOrder cakeOrder) {
    if(errors.hasErrors()) {
        return "designform";
    }
    cakeOrder.addCake(cake);
    log.info("Processing cake: {}", cake);
    return "redirect:/orders/current";
}

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
    return ingredients
            .stream()
            .filter(x -> x.getType().equals(type))
            .collect(Collectors.toList());
    }
}

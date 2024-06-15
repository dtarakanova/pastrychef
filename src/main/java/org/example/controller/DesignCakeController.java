package org.example.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Cake;
import org.example.model.Ingredient;
import org.example.model.Ingredient.Type;
import org.example.model.CakeOrder;
import org.example.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("cakeOrder")

public class DesignCakeController {

private final IngredientRepository ingredientRepository;
@Autowired
public DesignCakeController (IngredientRepository ingredientRepository) {
    this.ingredientRepository = ingredientRepository;

}

@GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(i -> ingredients.add(i));

        Type[] types = Ingredient.Type.values();
        for(Type type : types){
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
        model.addAttribute("designform", new Cake());
        return "designform";
}



@ModelAttribute(name = "cakeOrder")
public CakeOrder order() {
    return new CakeOrder();
}

@ModelAttribute(name = "cake")
public Cake cake() {
    return new Cake();
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

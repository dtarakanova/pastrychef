package org.example.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data

public class Cake {
    private Long id;
    private Date createdAt;

    @NotNull
    @Size(min=4, message ="Please give name to your cake")
    private String inscription;

    //Сейчас можно выбрать несколько вариантов в одном типе ингр-та - исправить
    @NotNull
    @Size(min=1, message = "Please choose at least one ingredient")
    private List<Ingredient> ingredients;
}

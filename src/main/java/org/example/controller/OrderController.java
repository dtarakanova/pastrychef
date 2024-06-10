package org.example.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.model.CakeOrder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("cakeOrder")
public class OrderController {
    @GetMapping("/current")
    public String orderForm() {
        return "orderform";
    }

    @PostMapping
    public String processOrder(@Valid CakeOrder order, Errors errors, SessionStatus sessionStatus) {
        if(errors.hasErrors()) {
            return "orderform";
        }
        log.info("Order submitted: {}", order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
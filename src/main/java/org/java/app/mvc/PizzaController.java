package org.java.app.mvc;

import java.math.BigDecimal;
import java.util.List;

import org.java.app.db.pojo.Pizza;
import org.java.app.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping
	public String getIndex(@RequestParam(value = "nome", required = false) String nome, Model model) {
	    List<Pizza> pizzas;
	    if (nome != null) {
	        pizzas = pizzaService.findByNomeContaining(nome);
	    } else {
	        pizzas = pizzaService.findAll();
	    }

	    if (pizzas.isEmpty()) {
	        model.addAttribute("noPizzas", true);
	    } else {
	        model.addAttribute("pizzas", pizzas);
	    }

	    return "pizza-index";
	}


	
	@GetMapping("/{id}")
	public String getPizzaDetails(@PathVariable int id, Model model) {
	    Pizza pizza = pizzaService.findById(id);
	    model.addAttribute("pizza", pizza);
	    return "pizza-show";
	}
	
	@GetMapping("/create")
    public String getNewPizzaForm(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "pizza-create";
    }

	@PostMapping("/create")
	public String createPizza(@Valid @ModelAttribute("pizza") Pizza pizza, BindingResult bindingResult) {
	    if (bindingResult.hasErrors()) {
	        return "pizza-create";
	    }
	    pizzaService.save(pizza);
	    return "redirect:/pizzas";
	}
	
	@GetMapping("/{id}/edit")
	public String getEditPizzaForm(@PathVariable int id, Model model) {
	    Pizza pizza = pizzaService.findById(id);
	    model.addAttribute("pizza", pizza);
	    return "pizza-edit"; 
	}
	 
	 @PostMapping("/{id}/edit")
	    public String editPizza(@PathVariable int id, @Valid @ModelAttribute("pizza") Pizza updatedPizza, BindingResult bindingResult) {
	        if (bindingResult.hasErrors()) {
	            return "pizza-edit";
	        }
	        updatedPizza.setId(id);

	        pizzaService.save(updatedPizza);
	        return "redirect:/pizzas";
	    }
	 
	 @PostMapping("/{id}/delete")
	 public String deletePizza(@PathVariable int id) {
	     pizzaService.deleteById(id);
	     return "redirect:/pizzas";
	 }



}

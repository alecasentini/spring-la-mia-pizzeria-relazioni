package org.java.app.mvc;

import java.util.List;

import org.java.app.db.pojo.Pizza;
import org.java.app.db.pojo.Ingredient;
import org.java.app.db.serv.PizzaService;
import org.java.app.db.serv.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@GetMapping
	public String getIndex(Model model) {
		
		List<Ingredient> ingredients = ingredientService.findAll();
		model.addAttribute("ingredients", ingredients);
		
		return "ingredient-index";
	}
	
	@GetMapping("/create")
	public String getCreate(Model model) {
		
		List<Pizza> pizzas = pizzaService.findAll();
		Ingredient ingredient = new Ingredient();
		
		model.addAttribute("ingredient", ingredient);
		model.addAttribute("pizzas", pizzas);
		
		return "ingredient-create";
	}
	
	@PostMapping("/create")
	public String storeIngredient(
			@Valid @ModelAttribute Ingredient ingredient,
			BindingResult bindingResult,
			Model model
		) {
		
		ingredientService.save(ingredient);
		
		List<Pizza> pizzas = pizzaService.findAll();
		for (Pizza pizza : pizzas) {
			
			if (ingredient.hasPizza(pizza)) 
				pizza.addIngredient(ingredient);
			else pizza.removeIngredient(ingredient);
			
			pizzaService.save(pizza);
		}
		return "redirect:/ingredients";
	}
	
	@GetMapping("/{id}/delete")
	public String deleteIngredient(@PathVariable int id) {
	    Ingredient ingredient = ingredientService.findById(id);

	    List<Pizza> pizzas = pizzaService.findAll();
	    for (Pizza pizza : pizzas) {
	        if (pizza.hasIngredient(ingredient)) {
	            pizza.removeIngredient(ingredient);
	            pizzaService.save(pizza);
	        }
	    }

	    ingredientService.delete(ingredient);

	    return "redirect:/ingredients";
	}

}

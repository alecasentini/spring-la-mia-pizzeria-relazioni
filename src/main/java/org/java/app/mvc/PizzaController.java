package org.java.app.mvc;

import java.math.BigDecimal;
import java.util.List;

import org.java.app.db.pojo.Ingredient;
import org.java.app.db.pojo.Pizza;
import org.java.app.db.serv.IngredientService;
import org.java.app.db.serv.PizzaService;
import org.java.app.db.pojo.SpecialOffer;
import org.java.app.db.serv.SpecialOfferService;
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
	
	@Autowired
    private SpecialOfferService specialOfferService;
	
	@Autowired
    private IngredientService ingredientService;
	
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
		List<Ingredient> ingredients =ingredientService.findAll();
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredients", ingredients);
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
		List<Ingredient> ingredients = ingredientService.findAll();
		Pizza pizza = pizzaService.findById(id);
	    model.addAttribute("pizza", pizza);
	    model.addAttribute("ingredients", ingredients);
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

	 //Special Offer
	 
	 @GetMapping("/{pizzaId}/special-offer/create")
	 public String showCreateSpecialOfferForm(@PathVariable int pizzaId, Model model) {
	     Pizza pizza = pizzaService.findById(pizzaId);
	     model.addAttribute("pizza", pizza);
	     model.addAttribute("specialOffer", new SpecialOffer());
	     return "special-offer-create";
	 }


	 @PostMapping("/{pizzaId}/special-offer/create")
	 public String createSpecialOffer(@PathVariable int pizzaId, @Valid @ModelAttribute SpecialOffer specialOffer, BindingResult bindingResult, Model model) {
	     if (bindingResult.hasErrors()) {
	         Pizza pizza = pizzaService.findById(pizzaId);
	         model.addAttribute("pizza", pizza);
	         return "special-offer-create";
	     }
	     Pizza pizza = pizzaService.findById(pizzaId);
	     specialOffer.setPizza(pizza);
	     specialOfferService.createSpecialOffer(specialOffer);
	     return "redirect:/pizzas/" + pizzaId;
	 }

	 @GetMapping("/{pizzaId}/special-offer/{offerId}/edit")
	 public String showEditSpecialOfferForm(@PathVariable int pizzaId, @PathVariable int offerId, Model model) {
	     Pizza pizza = pizzaService.findById(pizzaId);
	     SpecialOffer specialOffer = specialOfferService.getSpecialOfferById(offerId);
	     model.addAttribute("pizza", pizza);
	     model.addAttribute("specialOffer", specialOffer);
	     return "special-offer-edit";
	 }

	 @PostMapping("/{pizzaId}/special-offer/{offerId}/edit")
	 public String editSpecialOffer(@PathVariable int pizzaId, @PathVariable int offerId, @Valid @ModelAttribute SpecialOffer specialOffer, BindingResult bindingResult, Model model) {
	     if (bindingResult.hasErrors()) {
	         Pizza pizza = pizzaService.findById(pizzaId);
	         model.addAttribute("pizza", pizza);
	         return "special-offer-edit";
	     }
	     Pizza pizza = pizzaService.findById(pizzaId);
	     specialOffer.setPizza(pizza);
	     specialOffer.setId(offerId);
	     specialOfferService.updateSpecialOffer(specialOffer);
	     return "redirect:/pizzas/" + pizzaId;
	 }

}

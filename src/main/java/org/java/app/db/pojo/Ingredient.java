package org.java.app.db.pojo;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	@ManyToMany(mappedBy = "ingredients")
	private List<Pizza> pizzas;
	
	public Ingredient() { }
	public Ingredient(String name, Pizza... pizzas) {
		
		setName(name);
		setPizzas(Arrays.asList(pizzas));
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Pizza> getPizzas() {
		return pizzas;
	}
	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}
	
	public boolean hasPizza(Pizza pizza) {
		
		if (getPizzas() == null) return false;
		
		for (Pizza p : getPizzas()) 
			if (p.getId() == pizza.getId())
				return true;
		
		return false;
	}
	public void addPizzas(Pizza... pizzas) {
		
		getPizzas().addAll(Arrays.asList(pizzas));
	}
	public void removePizzas(Pizza... pizzas) {
		
		getPizzas().removeAll(Arrays.asList(pizzas));
	}
	
	@Override
	public String toString() {
		
		return "[" + getId() + "] " + getName();
	}
}
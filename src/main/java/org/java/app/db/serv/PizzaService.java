package org.java.app.db.serv;

import org.java.app.db.pojo.Pizza;
import org.java.app.db.repo.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

	@Autowired
	private PizzaRepo pizzaRepo;
	
	public void save(Pizza pizza) {
		
		pizzaRepo.save(pizza);
	}
	public List<Pizza> findAll() {
		
		return pizzaRepo.findAll();
	}
	public Pizza findById(int id) {
		
		return pizzaRepo.findById(id).get();
	}
	public List<Pizza> findByNomeContaining(String nome) {
	    return pizzaRepo.findByNomeContaining(nome);
	}
	public void deleteById(int id) {
	    pizzaRepo.deleteById(id);
	}

}


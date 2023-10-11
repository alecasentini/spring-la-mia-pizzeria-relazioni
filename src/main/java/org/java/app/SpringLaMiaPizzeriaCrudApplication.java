package org.java.app;

import java.math.BigDecimal;

import org.java.app.db.pojo.Pizza;
import org.java.app.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {

	@Autowired
	private PizzaService pizzaService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Pizza pizza1 = new Pizza("Margherita", "Pomodoro, mozzarella, basilico, olio", "https://images.prismic.io/eataly-us/ed3fcec7-7994-426d-a5e4-a24be5a95afd_pizza-recipe-main.jpg?auto=compress,format", new BigDecimal("5.00"));
		Pizza pizza2 = new Pizza("Diavola", "Pomodoro, mozzarella, salame piccante", "https://www.misya.info/wp-content/uploads/2007/08/Pizza-alla-diavola.jpg", new BigDecimal("6.00"));
		Pizza pizza3 = new Pizza("Capricciosa", "Pomodoro, mozzarella, funghi, prosciutto cotto, olive, carciofini", "https://www.buttalapasta.it/wp-content/uploads/2017/11/pizza-capricciosa.jpg", new BigDecimal("7.00"));
		Pizza pizza4 = new Pizza("Quattro Stagioni", "Pomodoro, mozzarella, funghi, prosciutto cotto, olive nere, carciofini", "https://primochef.it/wp-content/uploads/2020/04/SH_pizza_quattro_stagioni.jpg", new BigDecimal("7.50"));
		Pizza pizza5 = new Pizza("Quattro Formaggi", "Mozzarella, gorgonzola, fontina, parmigiano", "https://www.silviocicchi.com/pizzachef/wp-content/uploads/2015/01/42.jpg", new BigDecimal("6.50"));

		
		pizzaService.save(pizza1);
		pizzaService.save(pizza2);
		pizzaService.save(pizza3);
		pizzaService.save(pizza4);
		pizzaService.save(pizza5);
		
		System.out.println("Inserimento OK!");
	}
}



package org.java.app;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.java.app.db.pojo.Ingredient;
import org.java.app.db.pojo.Pizza;
import org.java.app.db.pojo.SpecialOffer;
import org.java.app.db.serv.IngredientService;
import org.java.app.db.serv.PizzaService;
import org.java.app.db.serv.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {

	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
    private SpecialOfferService specialOfferService;
	
	@Autowired
	private IngredientService ingredientService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Ingredient pomodoro = new Ingredient("Pomodoro");
		Ingredient mozzarella = new Ingredient("Mozzarella");
		Ingredient basilico = new Ingredient("Basilico");
		Ingredient olio = new Ingredient("Olio");
		Ingredient salamePiccante = new Ingredient("Salame piccante");
		Ingredient funghi = new Ingredient("Funghi");
		Ingredient prosciuttoCotto = new Ingredient("Prosciutto cotto");
		Ingredient olive = new Ingredient("Olive");
		Ingredient carciofini = new Ingredient("Carciofini");
		Ingredient gorgonzola = new Ingredient("Gorgonzola");
		Ingredient fontina = new Ingredient("Fontina");
		Ingredient parmigiano = new Ingredient("Parmigiano");
		
		ingredientService.save(pomodoro);
        ingredientService.save(mozzarella);
        ingredientService.save(basilico);
        ingredientService.save(olio);
        ingredientService.save(salamePiccante);
        ingredientService.save(funghi);
        ingredientService.save(prosciuttoCotto);
        ingredientService.save(olive);
        ingredientService.save(carciofini);
        ingredientService.save(gorgonzola);
        ingredientService.save(fontina);
        ingredientService.save(parmigiano);
		
        Pizza pizza1 = new Pizza("Margherita", "Pizza classica", "https://images.prismic.io/eataly-us/ed3fcec7-7994-426d-a5e4-a24be5a95afd_pizza-recipe-main.jpg?auto=compress,format", new BigDecimal("5.00"), pomodoro, mozzarella, basilico, olio);
        Pizza pizza2 = new Pizza("Diavola", "Pizza classica", "https://www.misya.info/wp-content/uploads/2007/08/Pizza-alla-diavola.jpg", new BigDecimal("6.00"), pomodoro, mozzarella, salamePiccante);
        Pizza pizza3 = new Pizza("Capricciosa", "Pizza classica", "https://www.buttalapasta.it/wp-content/uploads/2017/11/pizza-capricciosa.jpg", new BigDecimal("7.00"), pomodoro, mozzarella, funghi, prosciuttoCotto, olive, carciofini);
        Pizza pizza4 = new Pizza("Quattro Stagioni", "Pizza classica", "https://primochef.it/wp-content/uploads/2020/04/SH_pizza_quattro_stagioni.jpg", new BigDecimal("7.50"), pomodoro, mozzarella, funghi, prosciuttoCotto, olive, carciofini);
        Pizza pizza5 = new Pizza("Quattro Formaggi", "Pizza classica", "https://www.silviocicchi.com/pizzachef/wp-content/uploads/2015/01/42.jpg", new BigDecimal("6.50"), mozzarella, gorgonzola, fontina, parmigiano);
		
		pizzaService.save(pizza1);
		pizzaService.save(pizza2);
		pizzaService.save(pizza3);
		pizzaService.save(pizza4);
		pizzaService.save(pizza5);
		
		SpecialOffer offer1 = new SpecialOffer();
		offer1.setDataInizio(LocalDate.now());
		offer1.setDataFine(LocalDate.now().plusDays(3));
		offer1.setTitolo("Offerta Speciale 1");
		offer1.setScontoPercentuale(new BigDecimal("10.0"));
		offer1.setPizza(pizza1);

		SpecialOffer offer2 = new SpecialOffer();
		offer2.setDataInizio(LocalDate.now());
		offer2.setDataFine(LocalDate.now().plusDays(7));
		offer2.setTitolo("Offerta Speciale 2");
		offer2.setScontoPercentuale(new BigDecimal("20.0"));
		offer2.setPizza(pizza2);

		SpecialOffer offer3 = new SpecialOffer();
		offer3.setDataInizio(LocalDate.now());
		offer3.setDataFine(LocalDate.now().plusDays(14));
		offer3.setTitolo("Offerta Speciale 3");
		offer3.setScontoPercentuale(new BigDecimal("25.0"));
		offer3.setPizza(pizza3);

		specialOfferService.createSpecialOffer(offer1);
		specialOfferService.createSpecialOffer(offer2);
		specialOfferService.createSpecialOffer(offer3);

        System.out.println("Inserimento OK!");
	}
}



package org.java.app.db.pojo;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class Pizza {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(length = 50, nullable = false)
    @NotBlank(message = "Il nome è obbligatorio")
    @Size(max = 50, message = "Il nome non può superare i 50 caratteri")
    private String nome;
    
    @Column(length = 256)
    @NotBlank(message = "La descrizione è obbligatoria")
    @Size(max = 256, message = "La descrizione non può superare i 256 caratteri")
    private String descrizione;
    
    @NotBlank(message = "La foto è obbligatoria")
    private String foto;
    
    @NotNull(message = "Il prezzo è obbligatorio")
    @DecimalMin(value = "0.01", message = "Il prezzo deve essere maggiore di 0")
    private BigDecimal prezzo;
    
    @OneToMany(mappedBy="pizza")
	private List<SpecialOffer> offerteSpeciali;
    
    @ManyToMany
	private List<Ingredient> ingredients;
	
    public Pizza() { }
    public Pizza(String nome, String descrizione, String foto, BigDecimal prezzo, Ingredient... ingredients) {

    	setNome(nome);
    	setDescrizione(descrizione);
    	setFoto(foto);
    	setPrezzo(prezzo);
    	setIngredients(Arrays.asList(ingredients));
    }
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public BigDecimal getPrezzo() {
	    return prezzo;
	}
	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}
		
	public List<SpecialOffer> getOfferteSpeciali() {
	    return offerteSpeciali;
	}
	public void setOfferteSpeciali(List<SpecialOffer> offerteSpeciali) {
	    this.offerteSpeciali = offerteSpeciali;
	}
	
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public boolean hasIngredient(Ingredient ingredient) {
		if (getIngredients() == null) return false;
		for (Ingredient i : getIngredients()) 
			if (ingredient.getId() == i.getId())
				return true;
		return false;
	}
	public void addIngredient(Ingredient ingredient) {

		getIngredients().add(ingredient);
	}
	public void removeIngredient(Ingredient ingredient) {

		getIngredients().remove(ingredient);
	}
	
	@Override
	public String toString() {
		
		return "[" + getId() + "] Pizza:\n"
					+ "nome: " + getNome() + "\n"
					+ "descrizione: " + getDescrizione() + "\n"
					+ "foto: " + getFoto() + "\n"
					+ "prezzo: " + getPrezzo();
	}


}	
	
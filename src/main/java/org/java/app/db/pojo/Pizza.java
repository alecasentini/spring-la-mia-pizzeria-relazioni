package org.java.app.db.pojo;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	public Pizza() { }
	public Pizza(String nome, String descrizione, String foto, BigDecimal prezzo) {
	
		setNome(nome);
		setDescrizione(descrizione);
		setFoto(foto);
		setPrezzo(prezzo);
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
	
	@Override
	public String toString() {
		
		return "[" + getId() + "] Pizza:\n"
					+ "nome: " + getNome() + "\n"
					+ "descrizione: " + getDescrizione() + "\n"
					+ "foto: " + getFoto() + "\n"
					+ "prezzo: " + getPrezzo();
	}
}
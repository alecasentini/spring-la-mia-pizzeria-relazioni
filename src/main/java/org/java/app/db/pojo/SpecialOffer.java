package org.java.app.db.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
public class SpecialOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull(message = "La data di inizio è obbligatoria")
    private LocalDate dataInizio;
    
    @NotNull(message = "La data di fine è obbligatoria")
    private LocalDate dataFine;
    
    @Column(length = 50, nullable = false)
    @NotBlank(message = "Il titolo è obbligatorio")
    @Size(max = 50, message = "Il titolo non può superare i 50 caratteri")
    private String titolo;
    
    @ManyToOne
    @JoinColumn(name="pizza_id", nullable=false)
    private Pizza pizza;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

}

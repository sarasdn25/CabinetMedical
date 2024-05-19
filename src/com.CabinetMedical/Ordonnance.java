package com.CabinetMedical;

import java.util.ArrayList;
import java.util.List;

public class Ordonnance {
    private List<String> nomsMedicaments;
    private List<String> doses;
    private List<Integer> dureesJours;
    //constructeur
    public Ordonnance() {
        this.nomsMedicaments = new ArrayList<>();
        this.doses = new ArrayList<>();
        this.dureesJours = new ArrayList<>();
    }

 
    // Getters et setters
    public List<String> getNomsMedicaments() {
        return nomsMedicaments;
    }

    public List<String> getDoses() {
        return doses;
    }

    public List<Integer> getDureesJours() {
        return dureesJours;
    }
    //methode utiliser dans la classe GestionCabinet
    public void ajouterMedicament(String nomMedicament, String dose, int dureePriseJours) {
        nomsMedicaments.add(nomMedicament);
        doses.add(dose);
        dureesJours.add(dureePriseJours);
    }
}
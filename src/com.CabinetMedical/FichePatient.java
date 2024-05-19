package com.CabinetMedical;

import java.util.ArrayList;
import java.util.List;

public class FichePatient {
    private Patient patient;
    private String poids;
    private String taille;
    private String constantes;
    private List<Antecedent> antecedents;

    // Constructeur avec tous les champs
    public FichePatient(Patient patient, String poids, String taille, String constantes, List<Antecedent> antecedents) {
        this.patient = patient;
        this.poids = poids;
        this.taille = taille;
        this.constantes = constantes;
        this.antecedents = new ArrayList<>(antecedents);
    }

 // Getters et setters
 public Patient getPatient() {
     return patient;
 }

 public String getPoids() {
     return poids;
 }

 public String getTaille() {
     return taille;
 }

 public String getConstantes() {
     return constantes;
 }
 public List<Antecedent> getAntecedents() {
     return new ArrayList<>(antecedents); // Retourne une copie de la liste
 }

 public void setPatient(Patient patient) {
     this.patient = patient;
 }

 public void setPoids(String poids) {
     this.poids = poids;
 }

 public void setTaille(String taille) {
     this.taille = taille;
 }

 public void setConstantes(String constantes) {
     this.constantes = constantes;
 }


 public void setAntecedents(List<Antecedent> antecedents) {
     this.antecedents = new ArrayList<>(antecedents);
 }

}
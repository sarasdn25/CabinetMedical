package com.CabinetMedical;

import java.util.List;

public class Patient {
    private String nom;
    private String prenom;
    private String telephone;
    private List<Antecedent> antecedents;
    private List<Consultation> consultations;

    // Constructeur
    public Patient(String nom, String prenom, String telephone, List<Antecedent> antecedents, List<Consultation> consultations) {
        setNom(nom);
        setPrenom(prenom);
        setTelephone(telephone);
        this.antecedents = antecedents;
        this.consultations = consultations;
    }

    // Getters et setters

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (isValidName(nom)) {
            this.nom = nom;
        } else {
            throw new IllegalArgumentException("Le nom du patient doit contenir uniquement des caractères alphabétiques et des espaces.");
        }
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        if (isValidPrenom(prenom)) {
            this.prenom = prenom;
        } else {
            throw new IllegalArgumentException("Le prénom du patient doit contenir uniquement des caractères alphabétiques et des espaces.");
        }
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        if (isValidPhoneNumber(telephone)) {
            this.telephone = telephone;
        } else {
            throw new IllegalArgumentException("Le numéro de téléphone doit contenir uniquement des chiffres.");
        }
    }

    public List<Antecedent> getAntecedents() {
        return antecedents;
    }

    public void setAntecedents(List<Antecedent> antecedents) {
        this.antecedents = antecedents;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    // Méthodes de validation

    public static boolean isValidName(String name) {
        // Vérifier si le nom ne contient que des caractères alphabétiques et des espaces
        return name.matches("[a-zA-Z\\s]+");
    }

    public static boolean isValidPrenom(String prenom) {
        // Vérifier si le prénom ne contient que des caractères alphabétiques et des espaces
        return prenom.matches("[a-zA-Z\\s]+");
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Vérifier si le numéro de téléphone ne contient que des chiffres
        return phoneNumber.matches("\\d+");
    }
}
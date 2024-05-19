package com.CabinetMedical;

import java.util.ArrayList;
import java.util.List;

public class DossierMedical {
    private Patient patient;
    private List<Consultation> consultations;
  

    // Constructeur
    public DossierMedical(Patient patient) {
        this.patient = patient;
        this.consultations = new ArrayList<>();
       
    }

    // Getters et setters
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    

    // Méthodes pour ajouter des consultations, traitements et examens
    public void ajouterConsultation(Consultation consultation) {
        consultations.add(consultation);
    }

}

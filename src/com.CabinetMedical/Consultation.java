package com.CabinetMedical;

import java.util.Date;

//Classe Consultation
public class Consultation {
    private Date date;
    private String observation;
    private Ordonnance ordonnance;
    private String certificat;
    private RendezVous rendezVous;
    // Constructeur par defaut
    public Consultation() {
        this.date = new Date();
        this.ordonnance = new Ordonnance(); 
    }
    // Constructeur avec tous les champs
    public Consultation(Date date, String observation, Ordonnance ordonnance, String certificat, RendezVous rendezVous) {
        this.date = date;
        this.observation = observation;
        this.ordonnance = ordonnance;
        this.certificat = certificat;
        this.rendezVous = rendezVous;
    }

    // Getters et setters
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Ordonnance getOrdonnance() {
        return ordonnance;
    }

    public void setOrdonnance(Ordonnance ordonnance) {
        this.ordonnance = ordonnance;
    }
    public String getCertificat() {
        return certificat;
    }

    public void setCertificat(String certificat) {
        this.certificat = certificat;
    }
    public RendezVous getRendezVous() {
        return rendezVous;
    }

    public void setRendezVous(RendezVous rendezVous) {
        this.rendezVous = rendezVous;
    }
}
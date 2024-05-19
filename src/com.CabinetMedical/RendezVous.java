package com.CabinetMedical;

import java.util.Date;

public class RendezVous {
    private Patient patient;
    private Date date;
    private Date heureRendezVous; 
    // Constructeur
    public RendezVous(Patient patient, Date date, Date heureRendezVous) {
        this.patient = patient;
        this.date = date;
        this.heureRendezVous = heureRendezVous;
    }
    // Getters et setters
    public Patient getPatient() {
        return patient;
    }
    
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }

    public Date getHeureRendezVous() {
        return heureRendezVous;
    }

    public void setHeureRendezVous(Date heureRendezVous) {
        this.heureRendezVous = heureRendezVous;
    }

    
}

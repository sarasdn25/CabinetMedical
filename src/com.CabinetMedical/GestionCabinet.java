package com.CabinetMedical;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestionCabinet {
    private List<Patient> patients;
    public List<RendezVous> rendezVous;
    private List<FichePatient> fichePatients;
    private List<DossierMedical> dossiersMedicaux;

    public GestionCabinet() {
        patients = new ArrayList<>();
        rendezVous = new ArrayList<>();
        fichePatients = new ArrayList<>();
        dossiersMedicaux = new ArrayList<>();
    }
    //point important: tout c'est methode vont etre utiliser dans la interface InterfaceCabinet.java
    
//methode pour ajouter un patient
    public void ajouterPatient(Patient patient) {
        patients.add(patient);
    }
  //methode pour prendre rendez vous

    public void prendreRendezVous(Patient patient, Date date, Date heureRendezVous) {
        RendezVous rdv = new RendezVous(patient, date, heureRendezVous);
        rendezVous.add(rdv);
    }
    //methode pour  crée fiche patient

    public void creerFichePatient(Patient patient, String poids, String taille, String constantes, List<Antecedent> antecedents) {
        FichePatient fiche = new FichePatient(patient, poids, taille, constantes, antecedents);
        fichePatients.add(fiche);
    }
    //methode pour consulter
    public void consulter(Patient patient, Date dateRendezVous, Date heureRendezVous, String observation, List<String> nomsMedicaments, List<String> doses, List<Integer> dureesJours, String certificat) {
        RendezVous rendezVous = new RendezVous(patient, dateRendezVous, heureRendezVous);
        Ordonnance ordonnance = new Ordonnance();
        for (int i = 0; i < nomsMedicaments.size(); i++) {
            ordonnance.ajouterMedicament(nomsMedicaments.get(i), doses.get(i), dureesJours.get(i));
        }
        Consultation consultation = new Consultation(dateRendezVous, observation, ordonnance, certificat, rendezVous);

        // Récupérer le dossier médical du patient
        DossierMedical dossier = getDossierMedical(patient);
        if (dossier != null) {
            dossier.ajouterConsultation(consultation);
        } else {
            // Créer un nouveau dossier médical pour le patient
            dossier = new DossierMedical(patient);
            dossier.ajouterConsultation(consultation);
            dossiersMedicaux.add(dossier);
        }
    }

    //tout ce qui suis se sont des methode get qui vont etre utiliser pour afficher les different resultat des methode
    
    //getter pour afficher la liste des patients
  	 public List<Patient> getPatients() {
          return patients;
      }
  	 
     //getter pour afficher consultation
 	 public List<Consultation> getConsultations(Patient patient) {
 	        List<Consultation> consultations = new ArrayList<>();
 	        for (DossierMedical dossier : dossiersMedicaux) {
 	            if (dossier.getPatient().equals(patient)) {
 	                consultations.addAll(dossier.getConsultations());
 	            }
 	        }
 	        return consultations;
 	    }
 	 //getter pour afficher fiche patient
	 public FichePatient getFichePatient(Patient patient) {
	        for (FichePatient fiche : fichePatients) {
	            if (fiche.getPatient().equals(patient)) {
	                return fiche;
	            }
	        }
	        return null;
	    }
 	
  	 
    //getter pour afficher dossier medicale
    public DossierMedical getDossierMedical(Patient patient) {
        for (DossierMedical dossier : dossiersMedicaux) {
            if (dossier.getPatient().equals(patient)) {
                return dossier;
            }
        }
        return null;
    }
    
   
	
}
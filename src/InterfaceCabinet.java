package com.CabinetMedical;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
//cette partie concerne la partie de interface

public class InterfaceCabinet {
    private static final GestionCabinet cabinet = new GestionCabinet();
    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//different switch pour faire un choix
    public static void main(String[] args) {
        boolean continuer = true;
        while (continuer) {
            afficherMenu();
            int choix = lireEntier("Votre choix : ");
            switch (choix) {
                case 1:
                    ajouterPatient();
                    break;
                case 2:
                    prendreRendezVous();
                    break;
                case 3:
                    creerFichePatient();
                    break;
                case 4:
                    consulter();
                    break;
                case 5:
                    afficherFichePatient();
                    break;
                case 6:
                    afficherConsultation();
                    break;
                case 7:
                    afficherDossierMedical();
                    break;
                case 8:
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        }
    }
    //contenu du menu d'affichage
    private static void afficherMenu() {
        System.out.println("\nBienvenue dans le cabinet médical");
        System.out.println("1. Ajouter un patient");
        System.out.println("2. Prendre un rendez-vous");
        System.out.println("3. Créer une fiche patient");
        System.out.println("4. Consulter un patient");
        System.out.println("5. Afficher une fiche patient");
        System.out.println("6. Afficher une consultation");
        System.out.println("7. Afficher un dossier médical");
        System.out.println("8. Quitter");
    }
//a partir d'ici en va traiter le deroulement et  l'affichage des 7 choix:
    
    // choix 1  Ajouter un patient
    private static void ajouterPatient() {
        System.out.println("\nAjout d'un nouveau patient");
        String nom, prenom, telephone;

        do {
            nom = lireChaine("Nom du patient : ");
            if (!Patient.isValidName(nom)) {
                System.out.println("Le nom du patient doit contenir uniquement des caractéres alphabétiques et des espaces.");
            }
        } while (!Patient.isValidName(nom));

        do {
            prenom = lireChaine("Prénom du patient : ");
            if (!Patient.isValidPrenom(prenom)) {
                System.out.println("Le prénom du patient doit contenir uniquement des caractères alphabéiques et des espaces.");
            }
        } while (!Patient.isValidPrenom(prenom));

        do {
            telephone = lireChaine("Téléphone du patient : ");
            if (!Patient.isValidPhoneNumber(telephone)) {
                System.out.println("Le numéro de téléphone doit contenir uniquement des chiffres.");
            }
        } while (!Patient.isValidPhoneNumber(telephone));

        List<Antecedent> antecedents = new ArrayList<>();
        List<Consultation> consultations = new ArrayList<>();
        Patient patient = new Patient(nom, prenom, telephone, antecedents, consultations);
        cabinet.ajouterPatient(patient);
        System.out.println("Patient ajouté avec succès");
    }
    //choix 2  Prendre un rendez-vous
    private static void prendreRendezVous() {
        System.out.println("\nPrise de rendez-vous");
        Patient patient = selectionnerPatient();
        if (patient != null) {
            Date date = lireDate("Date du rendez-vous (jj/mm/aaaa) : ");
            Date heureRendezVous = creerDateDepuisHeureMinutes(lireChaine("Heure du rendez-vous (format HH:mm) : "));
            cabinet.prendreRendezVous(patient, date, heureRendezVous);
            System.out.println("Rendez-vous pris avec succès");
        } else {
            System.out.println("Patient non trouvé");
        }
    }
    //choix 3 CrÃ©er une fiche patient
    private static void creerFichePatient() {
        System.out.println("\nCréation d'une fiche patient");
        Patient patient = selectionnerPatient();
        if (patient != null) {
            String poids = lireChaine("Poids du patient : ");
            String taille = lireChaine("Taille du patient : ");
            String constantes = lireChaine("Constantes du patient : ");
            List<Antecedent> antecedents = lireListeAntecedents("Entrez les antécédents du patient (tapez 'fin' pour terminer) : ");
            cabinet.creerFichePatient(patient, poids, taille, constantes, antecedents);            System.out.println("Fiche patient créée avec succès");
        } else {
            System.out.println("Patient non trouvé");
        }
    }
    //choix 4 Consulter un patient
    private static void consulter() {
        System.out.println("\nConsultation d'un patient");
        Patient patient = selectionnerPatient();
        if (patient != null) {
            Date dateRendezVous = lireDate("Date du rendez-vous (jj/mm/aaaa) : ");
            if (dateRendezVous == null) {
                System.out.println("Date incorrecte.");
                return;
            }

            String heureMinutesStr = lireChaine("Heure du rendez-vous (format HH:mm) : ");
            Date heureRendezVous = creerDateDepuisHeureMinutes(heureMinutesStr);
            if (heureRendezVous == null) {
                System.out.println("Heure incorrecte.");
                return;
            }

            // VÃ©rifier que la date et l'heure du rendez-vous correspondent Ã  un rendez-vous existant
            RendezVous rdvExistant = trouverRendezVous(patient, dateRendezVous, heureRendezVous);
            if (rdvExistant == null) {
                System.out.println("Aucun rendez-vous trouvé avec ces informations.");
                return;
            }

            String observation = lireChaine("Observation de la consultation : ");
            List<String> nomsMedicaments = lireListeChaines("Entrez les noms des médicaments (tapez 'fin' pour terminer) : ");
            List<String> doses = lireListeChaines("Entrez les doses correspondantes (tapez 'fin' pour terminer) : ");
            List<Integer> dureesJours = lireListeEntiers("Entrez les durées de prise en jours (tapez -1 pour terminer) : ");
            String certificat = lireChaine("Certificat de la consultation (tapez 'null' si aucun) : ");
            cabinet.consulter(patient, dateRendezVous, heureRendezVous, observation, nomsMedicaments, doses, dureesJours, certificat.equals("null") ? null : certificat);
            System.out.println("Consultation enregistrée avec succès");
        } else {
            System.out.println("Patient non trouvé");
        }
    }

    private static RendezVous trouverRendezVous(Patient patient, Date dateRendezVous, Date heureRendezVous) {
        for (RendezVous rdv : cabinet.rendezVous) {
            if (rdv.getPatient().equals(patient) && rdv.getDate().equals(dateRendezVous) && rdv.getHeureRendezVous().equals(heureRendezVous)) {
                return rdv;
            }
        }
        return null;
    }
    //choix 5 Afficher une fiche patient
    private static void afficherFichePatient() {
        System.out.println("\nAffichage d'une fiche patient");
        Patient patient = selectionnerPatient();
        if (patient != null) {
            FichePatient fiche = cabinet.getFichePatient(patient);
            if (fiche != null) {
                System.out.println("Fiche patient de " + patient.getNom() + " " + patient.getPrenom() + " :");
                System.out.println("Poids : " + fiche.getPoids());
                System.out.println("Taille : " + fiche.getTaille());
                System.out.println("Constantes : " + fiche.getConstantes());
                System.out.println("Antécédents :");
                for (Antecedent antecedent : fiche.getAntecedents()) {
                    System.out.println("Type : " + antecedent.getType() + ", Description : " + antecedent.getDescription());
                }
            } else {
                System.out.println("Aucune fiche patient trouvée pour ce patient.");
            }
        } else {
            System.out.println("Patient non trouvé");
        }
    }
    //choix 6 Afficher une consultation
    private static void afficherConsultation() {
        System.out.println("\nAffichage d'une consultation");
        Patient patient = selectionnerPatient();
        if (patient != null) {
            Date dateConsultation = lireDate("Date de la consultation (jj/mm/aaaa) : ");
            if (dateConsultation == null) {
                System.out.println("Date incorrecte.");
                return;
            }
            List<Consultation> consultations = cabinet.getConsultations(patient);
            boolean consultationTrouvee = false;
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat heureFormatter = new SimpleDateFormat("HH:mm");
            for (Consultation consultation : consultations) {
                String dateConsultationStr = dateFormatter.format(consultation.getDate());
                String dateEntreeStr = dateFormatter.format(dateConsultation);
                if (dateConsultationStr.equals(dateEntreeStr)) {
                    System.out.println("Consultation pour " + patient.getNom() + " " + patient.getPrenom() + " :");
                    System.out.println("Date du rendez-vous : " + dateFormatter.format(consultation.getDate()) + " Ã  " + heureFormatter.format(consultation.getRendezVous().getHeureRendezVous()));
                    System.out.println("Observation : " + consultation.getObservation());
                    System.out.println("Ordonnance :");
                    Ordonnance ordonnance = consultation.getOrdonnance();
                    List<String> nomsMedicaments = ordonnance.getNomsMedicaments();
                    List<String> doses = ordonnance.getDoses();
                    List<Integer> dureesJours = ordonnance.getDureesJours();
                    for (int i = 0; i < nomsMedicaments.size(); i++) {
                        System.out.println("  - " + nomsMedicaments.get(i) + ", dose : " + doses.get(i) + ", durée : " + dureesJours.get(i) + " jours");
                    }
                    System.out.println("Certificat : " + (consultation.getCertificat() != null ? consultation.getCertificat() : "Aucun"));
                    consultationTrouvee = true;
                    break;
                }
            }
            if (!consultationTrouvee) {
                System.out.println("Aucune consultation trouvée pour cette date et ce patient.");
            }
        } else {
            System.out.println("Patient non trouvé");
        }
    }
    //choix 7 afficher Dossier Medical
    private static void afficherDossierMedical() {
        System.out.println("\nAffichage d'un dossier médical");
        Patient patient = selectionnerPatient();
        if (patient != null) {
            DossierMedical dossier = cabinet.getDossierMedical(patient);
            if (dossier != null) {
                System.out.println("Dossier médical de " + patient.getNom() + " " + patient.getPrenom() + " :");
                FichePatient fiche = cabinet.getFichePatient(patient);
                if (fiche != null) {
                    System.out.println("Fiche patient :");
                    System.out.println("Poids : " + fiche.getPoids());
                    System.out.println("Taille : " + fiche.getTaille());
                    System.out.println("Constantes : " + fiche.getConstantes());
                    System.out.println("Antécédents :");
                    for (Antecedent antecedent : fiche.getAntecedents()) {
                        System.out.println("Type : " + antecedent.getType() + ", Description : " + antecedent.getDescription());
                    }
                }
                System.out.println("\nConsultations :");
                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat heureFormatter = new SimpleDateFormat("HH:mm");
                for (Consultation consultation : dossier.getConsultations()) {
                    System.out.println("Date : " + dateFormatter.format(consultation.getDate()) + " Ã  " + heureFormatter.format(consultation.getRendezVous().getHeureRendezVous()));
                    System.out.println("Observation : " + consultation.getObservation());
                    System.out.println("Ordonnance :");
                    Ordonnance ordonnance = consultation.getOrdonnance();
                    List<String> nomsMedicaments = ordonnance.getNomsMedicaments();
                    List<String> doses = ordonnance.getDoses();
                    List<Integer> dureesJours = ordonnance.getDureesJours();
                    for (int i = 0; i < nomsMedicaments.size(); i++) {
                        System.out.println("  - " + nomsMedicaments.get(i) + ", dose : " + doses.get(i) + ", durée : " + dureesJours.get(i) + " jours");
                    }
                    System.out.println("Certificat : " + (consultation.getCertificat() != null ? consultation.getCertificat() : "Aucun"));
                    System.out.println();
                }
            } else {
                System.out.println("Aucun dossier médical trouvé pour ce patient.");
            }
        } else {
            System.out.println("Patient non trouvé");
        }
    }
//a partir de cette partie se sont differente methode quand va utiliser dans les methode qui sont en desus s'a peut etre
    // lecteur de chiffre ou de lettre...
    
    //utiliser pour selectionner un patient c'est parmis les plus importante,c'est elle qui est utiliser a chaque fois pour
    //selectionner un patient
    private static Patient selectionnerPatient() {
        String nom = lireChaine("Nom du patient : ");
        String prenom = lireChaine("Prénom du patient : ");
        return trouverPatient(nom, prenom);
    }
    //cette methode permet de verifier que le nom et prenom s'aisie d'un patient exite vraiment
    private static Patient trouverPatient(String nom, String prenom) {
        for (Patient patient : cabinet.getPatients()) {
            if (patient.getNom().equalsIgnoreCase(nom) && patient.getPrenom().equalsIgnoreCase(prenom)) {
                return patient;
            }
        }
        return null;
    }

    //permet de lire les string en d'autre terme les lettres
    private static String lireChaine(String invite) {
        System.out.print(invite);
        return scanner.nextLine();
    }
    //permet de lire les entiers en d'autre terme les chiffres
    private static int lireEntier(String invite) {
        System.out.print(invite);
        while (!scanner.hasNextInt()) {
            System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
            scanner.nextLine();
            System.out.print(invite);
        }
        int valeur = scanner.nextInt();
        scanner.nextLine(); // Consommer le retour Ã  la ligne
        return valeur;
    }
    //permet de lire les dates 
    private static Date lireDate(String invite) {
        System.out.print(invite);
        String dateStr = scanner.nextLine();
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Date invalide. Veuillez entrer une date au format jj/mm/aaaa.");
            return lireDate(invite);
        }
    }
    //permet de lire les la liste des antecedent avec cette  structure
    //Type d'antÃ©cÃ©dent:.....
    //Description de l'antÃ©cÃ©dent:.....
    private static List<Antecedent> lireListeAntecedents(String invite) {
        List<Antecedent> liste = new ArrayList<>();
        System.out.println(invite);
        String entree;
        while (!(entree = scanner.nextLine()).equals("fin")) {
            System.out.print("Type d'antécédent : ");
            String type = scanner.nextLine();
            System.out.print("Description de l'antécédent : ");
            String description = scanner.nextLine();
            Antecedent antecedent = new Antecedent(type, description);
            liste.add(antecedent);
        }
        return liste;
    }
    //permet de lire la liste des string,en va utiliser fe ordonance vue que tout ce qui est medicament  fihom des
    //string fe une liste donc il faut une methode pour lire ce type de cas
    private static List<String> lireListeChaines(String invite) {
        List<String> liste = new ArrayList<>();
        System.out.println(invite);
        String entree;
        while (!(entree = scanner.nextLine()).equals("fin")) {
            liste.add(entree);
        }
        return liste;
    }
  //permet de lire la liste des entier,en va utiliser fe ordonance vue que tout ce qui est dose et durÃ©e fihom des
    //entier fe une liste donc il faut une methode pour lire ce type de cas
    private static List<Integer> lireListeEntiers(String invite) {
        List<Integer> liste = new ArrayList<>();
        System.out.println(invite);
        int entree;
        while ((entree = lireEntier("Entrez un nombre entier : ")) != -1) {
            liste.add(entree);
        }
        return liste;
    }
    //cette methode c'est elle qui permet de faire afficher hada format  HH:mm fe les heure
    private static Date creerDateDepuisHeureMinutes(String heureMinutesStr) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            return formatter.parse(heureMinutesStr);
        } catch (ParseException e) {
            System.out.println("Format d'heure invalide. Veuillez entrer une heure au format HH:mm.");
            return creerDateDepuisHeureMinutes(lireChaine("Heure du rendez-vous (format HH:mm) : "));
        }
    }
}
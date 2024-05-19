package com.CabinetMedical;

public class Antecedent {
    private String type; 
    private String description;

    // Constructeur
    public Antecedent(String type, String description) {
        this.type = type;
        this.description = description;
    }

    // Getters et setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

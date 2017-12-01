package i.taskmanager;

import java.io.Serializable;
import java.util.Date;

/*
 * Auteur : Groupe de travail i++
 * Fichier : Tache.java
 * Description : Classe permettant de créer une tache, puis d'obtenir les valeurs associées à celle-ci.
 */

public class Tache implements Serializable {

    //Variable d'instances
    private Double compensation;
    private String description;
    private Boolean isCompleted;
    private String note;
    private String dateDebut;
    private String dateLimite;
    private String destinataire;

    public Tache() {

    }

    public Tache(Double compensation, String description, Boolean isCompleted, String note, String dateDebut, String dateLimite, String destinaire) {

        this.compensation = compensation;
        this.description = description;
        this.isCompleted = isCompleted;
        this.note = note;
        this.dateDebut = dateDebut;
        this.dateLimite = dateLimite;
        this.destinataire = destinaire;

    }

    public Tache(Double compensation, String description, Boolean isCompleted, String dateDebut, String dateLimite, String destinataire) {

        this.compensation = compensation;
        this.description = description;
        this.isCompleted = isCompleted;
        this.note = "";
        this.dateDebut = dateDebut;
        this.dateLimite = dateLimite;
        this.destinataire = destinataire;

    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public Double getCompensation() {
        return compensation;
    }


    public String getDescription() {
        return description;
    }

    public String getNote() {
        return note;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getDestinataire() {
        return destinataire;
    }


}

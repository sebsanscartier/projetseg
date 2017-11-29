package i.taskmanager;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by newuser on 11/20/17.
 */

public class Tache implements Serializable{

    private Double compensation;
    private String description;
    private Boolean isCompleted;
    private String note;
    private String dateDebut;
    private String dateLimite;
    private String destinataire;

    public Tache(){

    }
    public Tache(Double compensation, String description, Boolean isCompleted, String note, String dateDebut, String dateLimite, String destinaire){

        this.compensation = compensation;
        this.description = description;
        this.isCompleted = isCompleted;
        this.note = note;
        this.dateDebut = dateDebut;
        this.dateLimite = dateLimite;
        this.destinataire = destinaire;

    }

    public Tache(Double compensation, String description, Boolean isCompleted, String dateDebut, String dateLimite, String destinataire){

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

    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateLimite() {
        return dateLimite;
    }

    public String getDescription() {
        return description;
    }

    public String getNote() {
        return note;
    }



    public void setCompensation(Double compensation) {
        this.compensation = compensation;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateLimite(String dateLimite) {
        this.dateLimite = dateLimite;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }
}

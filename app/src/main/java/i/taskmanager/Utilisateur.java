package i.taskmanager;

import java.io.Serializable;

/*
 * Auteur : Groupe de travail i++
 * Fichier : Utilisateur.java
 * Description : Classe permettant de créer et d'obtenir l'instance d'un utilisateur.
 */
public class Utilisateur implements Serializable {

    //Variables d'instance
    private String prenom, nom;
    private int nbreTachesAccomplies;
    private Double nbreDePoints;
    private Role role;
    private String user;
    private int pass;

    public Utilisateur() {

    }

    public Utilisateur(String prenom, String nom, int nbreTachesAccomplies, Double nbreDePoints, Role role, String user, int pass) {
        this.prenom = prenom;
        this.nom = nom;
        this.role = role;
        this.nbreDePoints = nbreDePoints;
        this.nbreTachesAccomplies = nbreTachesAccomplies;
        this.user = user;
        this.pass = pass;
    }

    public Double getNbreDePoints() {
        return nbreDePoints;
    }

    public int getNbreTachesAccomplies() {
        return nbreTachesAccomplies;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Role getRole() {
        return role;
    }

    public String getUser() {
        return user;
    }

    public int getPass() {
        return pass;
    }

    public void setNbreDePoints(Double nbreDePoints) {
        this.nbreDePoints = nbreDePoints;
    }

    public void setNbreTachesAccomplies(int nbreTachesAccomplies) {
        this.nbreTachesAccomplies = nbreTachesAccomplies;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setUser(String user) {
        this.user = user;
    }
}

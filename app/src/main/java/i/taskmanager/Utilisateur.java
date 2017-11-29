package i.taskmanager;

import java.io.Serializable;

/**
 * Created by newuser on 11/20/17.
 */

public class Utilisateur implements Serializable{

    private String prenom,nom;
    private int nbreTachesAccomplies;
    private Double nbreDePoints;
    private Role role;
    private String user;
    private int pass;

    public Utilisateur(String prenom, String nom, int nbreTachesAccomplies, Double nbreDePoints, Role role, String user, int pass){
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

    public String getUser() {return user;}

    public int getPass() {return pass;}
}

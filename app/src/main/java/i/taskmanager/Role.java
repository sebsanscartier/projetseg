package i.taskmanager;

import android.graphics.Bitmap;
import android.media.Image;

import java.io.Serializable;


/*
 * Auteur : Groupe de travail i++
 * Fichier : Role.java
 * Description : Classe permettant d'obtenir un rôle, associé à un utilisateur.
 */

public class Role implements Serializable {


    //Variables d'instance
    private Boolean isAdministrative;
    private String description;


    public Role() {

    }

    //Constructeur par défaut.
    public Role(Boolean isAdministrative, String description) {
        this.isAdministrative = isAdministrative;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getAdministrative() {
        return isAdministrative;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAdministrative(Boolean administrative) {
        isAdministrative = administrative;
    }
}

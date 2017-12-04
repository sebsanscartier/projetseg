package i.taskmanager;

/*
 * Auteur : Groupe de travail i++
 * Fichier : Session.java
 * Description : Classe qui permet d'instancier toutes les variables statiques qui pourraient être utilisées dans l'application.
 * Ce sont des variables qui ne changeront jamais et doivent être disponibles dans toutes les classes!
 */

public class Session {

    //Role
    public final static Role parent = new Role(true, "Parent (Admin)");
    public final static Role fille = new Role(false, "Fille");
    public final static Role garcon = new Role(false, "Garçon");

}

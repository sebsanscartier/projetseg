package i.taskmanager;

/**
 * Created by Jérémie St-Pierre on 2017-11-26.
 */

public class readFireBase {

    private String prenom,nom;
    private int nbreTachesAccomplies;
    private Double nbreDePoints;
    private Role role;
    private String user;
    private int pass;

    public readFireBase(){
    }

    public void setUser(String user){
        this.user = user;
    }

    public void setPass(int pass){
        this.pass = pass;
    }
}

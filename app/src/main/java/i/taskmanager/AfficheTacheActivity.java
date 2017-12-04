package i.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/*
 * Auteur : Groupe de travail i++
 * Fichier : AfficheTacheActivity.java
 * Description : Activité permettant d'afficher une tache sélectionnée dans la liste. On peut compléter celle-ci,
 * ou la supprimer si nous sommes un administrateur.
 */

public class AfficheTacheActivity extends AppCompatActivity {

    private Tache tache;
    private Utilisateur user;
    private ImageView delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.affiche_tache_activity);

        //Définir les objets qui sont utilisés dans la classe.
        TextView description = (TextView) findViewById(R.id.affiche_tache_description);
        TextView point = (TextView) findViewById(R.id.affiche_tache_nbPoints);
        TextView note = (TextView) findViewById(R.id.affiche_tache_note);
        delete = (ImageView) findViewById(R.id.affiche_tache_supprimer);

        //Obtenir la tâche qu'on a envoyé à travers le Intent. (Ici, il s'agit d'un serializable), ainsi que l'utilisateur
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        Tache t = (Tache) bundle.getSerializable("tache");
        user = (Utilisateur) bundle.getSerializable("user");

        //Mettre le bouton supprimer une tâche invisible si la personne n'est pas un administrateur. Il ne pourra pas supprimer celle-ci.
        if (!user.getRole().getAdministrative()) {
            delete.setVisibility(View.INVISIBLE);
        }

        //Question de debugging, on affiche les valeurs si elles sont bien là. On s'évite ici de retrouver des NullPointers
        if (t != null) {

            tache = t;

            //Afficher dans l'interface graphique toutes les valeurs obtenues à travers la tâche passée dans le Intent
            description.setText(tache.getDescription());
            point.setText(tache.getCompensation() + "");

            if (t.getNote().length() != 0) {
                note.setText(tache.getNote());
            } else {
                note.setText("");
                note.setHeight(0);
            }


        } else {
            //Il y a un null, on doit donc afficher un message d'erreur pour debugging, puis retourner la personne à l'ancienne activity.
            finish();
        }


    }


    //Méthode permettant la suppression d'une tâche dans la base de données lors d'un clique.
    public void deleteTache(View v) {
        //On crée l'instance de la base de données et on prend la "table" tache.
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("tache");

        //On supprimer la tâche avec la clée qui est la description
        ref.child(tache.getDescription()).removeValue();

        //On affiche que la tâche est complétée et on termine l'activité pour retourner au menu.
        Toast.makeText(getApplicationContext(), "Tâche supprimée avec succès.", Toast.LENGTH_LONG).show();
        finish();

    }


    //Méthode permettant de terminer une tâche, soit mettre sa variable complétée à true.
    public void terminerTache(View v) {

        //Obtenir l'instance de la base de donnée, puis changer sa valeur de complétée à true.
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("tache");
        ref.child(tache.getDescription()).child("completed").setValue(true);

        //Obtenir la partie users et ajouter le nombre de points de la tâche à l'utilisateur qui l'a complétée.
        DatabaseReference ref2 = database.getReference("users");
        user.setNbreDePoints(user.getNbreDePoints() + tache.getCompensation());
        ref2.child(user.getUser()).child("nbreDePoints").setValue(user.getNbreDePoints());

        //Tout est ajouté dans le meilleur des cas, on peut donc terminer l'activité et afficher un message qui indique que c'est réussi.
        Intent t = new Intent(getBaseContext(), ListeTacheAdapter.class);
        Toast.makeText(getApplicationContext(), "Tâche complétée", Toast.LENGTH_LONG).show();
        finish();

    }
}

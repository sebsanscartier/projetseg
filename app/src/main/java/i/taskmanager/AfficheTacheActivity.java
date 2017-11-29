package i.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by newuser on 11/20/17.
 */

public class AfficheTacheActivity extends AppCompatActivity {

    private Tache tache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.affiche_tache_activity);

        //Définir les objets qui sont utilisés dans la classe.
        TextView description = (TextView) findViewById(R.id.affiche_tache_description);
        TextView point = (TextView) findViewById(R.id.affiche_tache_nbPoints);
        TextView note = (TextView) findViewById(R.id.affiche_tache_note);

        //Obtenir la tâche qu'on a envoyé à travers le Intent. (Ici, il s'agit d'un serializable)
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        Tache t = (Tache) bundle.getSerializable("tache");

        //Question de debugging, on affiche les valeurs si elles sont bien là. On s'évite ici de retrouver des NullPointers
        if(t != null) {
            tache = t;
            Log.d("TACHE", tache.getDescription());


            //Afficher dans l'interface graphique toutes les valeurs obtenues à travers la tâche passée dans le Intent
            description.setText(tache.getDescription());
            point.setText(tache.getCompensation()+"");

            if(t.getNote().length() != 0){
                note.setText(tache.getNote());
            }else{
                note.setText("");
                note.setHeight(0);
            }



        }else{
            //Il y a un null, on doit donc afficher un message d'erreur pour debugging, puis retourner la personne à l'ancienne activity.
            Log.d("TACHE", "Erreur, nous n'avons pas pu trouver la tâche...");
            finish();
        }



    }


    public void deleteTache(View v){
        Intent t = new Intent(getApplicationContext(),ListeTacheActivity.class);
        startActivity(t);
        finish();

    }
}

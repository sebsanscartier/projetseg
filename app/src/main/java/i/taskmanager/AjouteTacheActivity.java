package i.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/*
 * Auteur : Groupe de travail i++
 * Fichier : AjouteTacheActivity.java
 * Description : Activité permettant d'ajouter une tâche. Selon les intent, celle-ci ne s'affichera que si nous sommes un administrateur.
 */

public class AjouteTacheActivity extends AppCompatActivity {


    //Variables d'instance
    private EditText description, nombrePoints, dateLimite, note;
    private Spinner destinataire;
    private List<String> utilisateurs = new ArrayList<String>();
    private ArrayAdapter<String> adapteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajoute_tache_activity);

        //Obtenir toutes les composantes qu'on va utiliser dans la classe.
        description = (EditText) findViewById(R.id.ajoute_tache_titre);
        nombrePoints = (EditText) findViewById(R.id.ajoute_tache_nbPoints);
        dateLimite = (EditText) findViewById(R.id.ajoute_tache_date);
        note = (EditText) findViewById(R.id.ajoute_tache_note);
        destinataire = (Spinner) findViewById(R.id.ajoute_tache_destinataire);

    }


    //Méthode permettant l'ajout d'une tâche dans la base de données.
    public void addTache(View v) {

        //Obtenir toutes les valeurs des composantes pour créer la tâche.
        String descriptionValeur = description.getText().toString().trim();
        double compensationValeur = Double.parseDouble(nombrePoints.getText().toString().trim());
        String dateLimiteValeur = dateLimite.getText().toString().trim();
        String noteValeur = note.getText().toString().trim();
        String destinataireValeur = destinataire.getSelectedItem().toString();

        //Créer une nouvelle tâche avec les valeurs
        Tache task = new Tache(compensationValeur, descriptionValeur, false, noteValeur, dateLimiteValeur, "", destinataireValeur);


        //Créer la tâche dans la base de données.
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("tache").child(descriptionValeur);
        ref.setValue(task);

        //Afficher la réussite à l'écran
        Toast.makeText(getApplicationContext(), descriptionValeur + " ajouté", Toast.LENGTH_LONG).show();

        //Terminer l'activité comme tout est correct.
        finish();

    }


    //Méthode qui permet, dans le départ de l'application, de remplir le spinner avec tous les utilisateurs dans la base de données.
    @Override
    protected void onStart() {
        super.onStart();


        //Obtenir les utilisateurs dans la base de données.
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users");

        //Recommencer le tableau avec des valeurs vides.
        utilisateurs.clear();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Passer à travers tous les éléments pour les ajouter au tableau.
                for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                    Utilisateur tmp = snapShot.getValue(Utilisateur.class);
                    utilisateurs.add(tmp.getPrenom() + " " + tmp.getNom());
                }

                //Créer l'adapteur, puis afficher le tout dans le spinner! Un simple adapteur avec du texte fait l'affaire pour un spinner!
                adapteur = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, utilisateurs);

                adapteur.setDropDownViewResource(android.R.layout.simple_spinner_item);
                destinataire.setAdapter(adapteur);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Méthode obligatoire, mais elle nous sert à rien!
            }
        });


    }
}
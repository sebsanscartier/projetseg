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

/**
 * Created by newuser on 11/20/17.
 */

public class AjouteTacheActivity extends AppCompatActivity {


    private EditText description, nombrePoints, dateLimite, note;
    private Spinner destinataire;
    private List<String> utilisateurs = new ArrayList<String>();
    private ArrayAdapter<String> adapteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajoute_tache_activity);


        description = (EditText) findViewById(R.id.ajoute_tache_titre);
        nombrePoints = (EditText) findViewById(R.id.ajoute_tache_nbPoints);
        dateLimite = (EditText) findViewById(R.id.ajoute_tache_date);
        note = (EditText) findViewById(R.id.ajoute_tache_note);
        destinataire = (Spinner) findViewById(R.id.ajoute_tache_destinataire);

    }


    public void addTache(View v) {
        String didi = description.getText().toString().trim();
        double didi2 = Double.parseDouble(nombrePoints.getText().toString().trim());
        String didi3 = dateLimite.getText().toString().trim();
        String didi4 = note.getText().toString().trim();
        String didi5 = destinataire.getSelectedItem().toString();


        Tache task = new Tache(didi2, didi, false, didi4, didi3, "",didi5);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference lili = database.getReference("tache").child(didi);
        lili.setValue(task);
        Toast.makeText(getApplicationContext(), didi + " ajouté", Toast.LENGTH_LONG).show();
        finish();

    }



    @Override
    protected void onStart() {
        super.onStart();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users");
        utilisateurs.clear();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                    Utilisateur tmp = snapShot.getValue(Utilisateur.class);
                    utilisateurs.add(tmp.getPrenom() + " " + tmp.getNom());
                }

                adapteur = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, utilisateurs);

                adapteur.setDropDownViewResource(android.R.layout.simple_spinner_item);
                destinataire.setAdapter(adapteur);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
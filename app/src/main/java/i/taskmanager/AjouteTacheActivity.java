package i.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by newuser on 11/20/17.
 */

public class AjouteTacheActivity extends AppCompatActivity {


    private EditText description, nombrePoints, dateLimite, note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajoute_tache_activity);


        description = (EditText) findViewById(R.id.ajoute_tache_titre);
        nombrePoints = (EditText) findViewById(R.id.ajoute_tache_nbPoints);
        dateLimite = (EditText) findViewById(R.id.ajoute_tache_date);
        note = (EditText) findViewById(R.id.ajoute_tache_note);

    }


    public void addTache(View v) {
        String didi = description.getText().toString().trim();
        double didi2 = Double.parseDouble(nombrePoints.getText().toString().trim());
        String didi3 = dateLimite.getText().toString().trim();
        String didi4 = note.getText().toString().trim();


        Tache task = new Tache(didi2, didi, false, didi4, didi3, "");


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference lili = database.getReference().child(didi);
        lili.setValue(task);
        Toast.makeText(getApplicationContext(), didi + " ajouté", Toast.LENGTH_LONG).show();


    }

}
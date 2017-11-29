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


    private EditText description,nombrePoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajoute_tache_activity);
    }


    public void addUser(View v){
            String prenomString = prenom.getText().toString().trim();
            String nomString = nom.getText().toString().trim();
            String userString = user.getText().toString().trim();
            int passInt = Integer.parseInt(pass.getText().toString().trim());

            Role roleChoisi = Session.parent;

            if(role.getSelectedItemPosition() == 0){
                roleChoisi = Session.parent;
            }else if(role.getSelectedItemPosition() == 1){
                roleChoisi = Session.garcon;
            }else if(role.getSelectedItemPosition() == 2){
                roleChoisi = Session.fille;
            }

            Utilisateur user = new Utilisateur(prenomString, nomString, 0, 0.0, roleChoisi, userString, passInt);



            FirebaseDatabase database = FirebaseDatabase.getInstance("taches");
            DatabaseReference ref = database.getReference().child(userString);
            ref.setValue(user);
            Toast.makeText(getApplicationContext(), userString+" ajouté", Toast.LENGTH_LONG).show();
        }

}

package i.taskmanager;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by newuser on 11/20/17.
 */

public class AjoutUserActivity extends AppCompatActivity {

    private Spinner role;
    private EditText prenom;
    private EditText nom;
    private EditText user;
    private EditText pass;
    private ImageView image;

    //Simple méthode de création, qui permet d'afficher les informations relatives à l'utilisateur.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajout_user_activity);

        role = (Spinner)findViewById(R.id.ajout_user_role);
        prenom = (EditText)findViewById(R.id.ajout_user_prenom);
        nom = (EditText)findViewById(R.id.ajout_user_nom);
        user = (EditText)findViewById(R.id.ajout_user_username);
        pass = (EditText)findViewById(R.id.ajout_user_password);
        image = (ImageView)findViewById(R.id.ajout_user_imageRole);




        //Créer les roles et les mettre dans le spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array_roles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        role.setAdapter(adapter);


        //Mettre en place le changement de l'image si on change de role
        role.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(position == 0){
                    image.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.king));
                }else if(position == 1){
                    image.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.monsieur));
                }else if(position == 2){
                    image.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.madame));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                //On s'en fou!
            }

        });


    }

    public void addUser(View v){
        if (verifyInfo()){
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



            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference().child(userString);
            ref.setValue(user);
            Toast.makeText(getApplicationContext(), userString+" ajouté", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "mot de passe invalide", Toast.LENGTH_LONG).show();
        }
    }

    private boolean verifyInfo(){
        try{
            int passInt = Integer.parseInt(pass.getText().toString().trim());
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public void mainActivity(View v){
        this.finish();
    }
}

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

/*
 * Auteur : Groupe de travail i++
 * Fichier : AjoutUserActivity.java
 * Description : Activité permettant d'ajouter un nouvel utilisateur. Nous avons laissé celle-ci libre, car au début de l'application,
 * Il n'y aura aucun utilisateur dans la base de données. C'est pourquoi, nous ne pouvions pas cacher celle-ci dans l'application de façon sécurisée.
 */

public class AjoutUserActivity extends AppCompatActivity {

    //Les Variables d'instance
    private Spinner role;
    private EditText prenom,nom,user,pass;
    private ImageView image;

    //Simple méthode de création, qui permet d'afficher les informations relatives à l'utilisateur.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajout_user_activity);


        //Obtenir les composantes du layout, pour utilisation future.
        role = (Spinner) findViewById(R.id.ajout_user_role);
        prenom = (EditText) findViewById(R.id.ajout_user_prenom);
        nom = (EditText) findViewById(R.id.ajout_user_nom);
        user = (EditText) findViewById(R.id.ajout_user_username);
        pass = (EditText) findViewById(R.id.ajout_user_password);
        image = (ImageView) findViewById(R.id.ajout_user_imageRole);


        //Créer les roles et les mettre dans le spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array_roles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        role.setAdapter(adapter);


        //Mettre en place le changement de l'image si on change de role
        role.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 0) {
                    image.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.king));
                } else if (position == 1) {
                    image.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.monsieur));
                } else if (position == 2) {
                    image.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.madame));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                //On s'en fou! Obligatoire...
            }

        });


    }

    //Méthode permettant l'ajout de l'utilisateur dans la base de données
    public void addUser(View v) {
        if (verifyInfo()) {

            //Obtenir toutes les valeurs des composantes dans le layout.
            String prenomString = prenom.getText().toString().trim();
            String nomString = nom.getText().toString().trim();
            String userString = user.getText().toString().trim();
            int passInt = Integer.parseInt(pass.getText().toString().trim());

            //Création d'un rôle initial qui nous permet d'être redéfini par la suite selon le choix.
            Role roleChoisi = Session.parent;


            //Choix du rôle choisi dans le spinner.
            if (role.getSelectedItemPosition() == 0) {
                roleChoisi = Session.parent;
            } else if (role.getSelectedItemPosition() == 1) {
                roleChoisi = Session.garcon;
            } else if (role.getSelectedItemPosition() == 2) {
                roleChoisi = Session.fille;
            }

            //Création d'un utilisateur pour le mettre dans la base de données.
            Utilisateur user = new Utilisateur(prenomString, nomString, 0, 0.0, roleChoisi, userString, passInt);


            //Mettre l'utilisateur dans la base de données.
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference("users").child(userString);
            ref.setValue(user);

            //Afficher l'ajout réussi pour l'utilisateur en question.
            Toast.makeText(getApplicationContext(), userString + " ajouté", Toast.LENGTH_LONG).show();
        } else {

            //Afficher ceci si le mot de passe n'est pas numérique! Donc une erreur, sachant qu'on veut des chiffres.
            Toast.makeText(getApplicationContext(), "mot de passe invalide", Toast.LENGTH_LONG).show();
        }
    }


    //Méthode permettant de savoir si le mot de passe est numérique.
    private boolean verifyInfo() {
        try {
            int passInt = Integer.parseInt(pass.getText().toString().trim());
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    //Méthode permettant de quitter l'activité, dans le cas où on a ajouté l'utilisateur.
    public void mainActivity(View v) {
        this.finish();
    }
}

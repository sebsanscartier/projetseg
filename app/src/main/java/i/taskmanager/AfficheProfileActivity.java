package i.taskmanager;

import android.content.Intent;
import android.graphics.BitmapFactory;
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
 * Fichier : AfficheProfileActivity.java
 * Description : Activité permettant l'affichage du profil.
 */

public class AfficheProfileActivity extends AppCompatActivity {


    private Utilisateur u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.affiche_profile_activity);


        //Obtenir l'utilisateur qu'on a envoyé à travers le Intent. (Ici, il s'agit d'un serializable)
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        u = (Utilisateur) bundle.getSerializable("user");

        //Obtenir les différentes composantes de la vue.
        TextView nom = (TextView) findViewById(R.id.affiche_profile_nom);
        TextView prenom = (TextView) findViewById(R.id.affiche_profile_prenom);
        TextView nbPoints = (TextView) findViewById(R.id.affiche_profile_nbPoints);
        TextView nbTaches = (TextView) findViewById(R.id.affiche_profile_nbTachesAccomplies);
        ImageView image = (ImageView) findViewById(R.id.affiche_profile_image);
        ImageView supprimer = (ImageView) findViewById(R.id.affiche_profile_supprimer);


        //Mettre à jour les différentes composantes
        nom.setText(u.getNom());
        prenom.setText(u.getPrenom());
        nbPoints.setText(u.getNbreDePoints() + "");
        nbTaches.setText(u.getNbreTachesAccomplies() + "");


        //Vérifer de quel rôle est l'utilisateur, de fonction à changer l'image en fonction de celui-ci.
        if (u.getRole().getDescription().trim() == "Parent (Admin)") {
            image.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.king));
        } else if (u.getRole().getDescription().trim().equals("Fille")) {
            image.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.madame));
        } else if (u.getRole().getDescription().trim().equals("Garçon")) {
            image.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.monsieur));
        }

        if(!u.getRole().getAdministrative()){
            supprimer.setVisibility(View.INVISIBLE);
        }


    }


    //Méthode qui permet de supprimer un utilisateur en visitant son profil.
    public void supprimerProfil(View v){
        //On crée l'instance de la base de données et on prend la "table" tache.
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users");

        //On supprimer l'utilisateur avec la clée qui est la description
        ref.child(u.getUser()).removeValue();

        //On affiche que l'utilisateur est supprimé et on termine l'activité pour retourner au menu.
        Toast.makeText(getApplicationContext(), "Profil supprimé avec succès.", Toast.LENGTH_LONG).show();
        finish();


    }
}

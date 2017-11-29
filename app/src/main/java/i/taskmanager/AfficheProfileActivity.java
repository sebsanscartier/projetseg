package i.taskmanager;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by newuser on 11/20/17.
 */

public class AfficheProfileActivity extends AppCompatActivity {


    //Simple méthode de création, qui permet d'afficher les informations relatives à l'utilisateur.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.affiche_profile_activity);


        //Obtenir l'utilisateur qu'on a envoyé à travers le Intent. (Ici, il s'agit d'un serializable)
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        Utilisateur u = (Utilisateur) bundle.getSerializable("user");

        //Obtenir les différentes composantes de la vue.
        TextView nom = (TextView) findViewById(R.id.affiche_profile_nom);
        TextView prenom = (TextView) findViewById(R.id.affiche_profile_prenom);
        TextView nbPoints = (TextView) findViewById(R.id.affiche_profile_nbPoints);
        TextView nbTaches = (TextView) findViewById(R.id.affiche_profile_nbTachesAccomplies);
        ImageView image = (ImageView) findViewById(R.id.affiche_profile_image);


        //Mettre à jour les différentes composantes
        nom.setText(u.getNom());
        prenom.setText(u.getPrenom());
        nbPoints.setText(u.getNbreDePoints()+"");
        nbTaches.setText(u.getNbreTachesAccomplies()+"");

        if(u.getRole().getDescription().trim() == "Parent (Admin)"){
            Log.d("POUF","Parent");
            image.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.king));
        }else if(u.getRole().getDescription().trim().equals("Fille")){
            Log.d("POUF","madame");
            image.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.madame));
        }else if(u.getRole().getDescription().trim().equals("Garçon")){
            Log.d("POUF","Monsieur");
            Log.d("POUF",u.getRole().getDescription());
            image.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.monsieur));
        }




    }
}

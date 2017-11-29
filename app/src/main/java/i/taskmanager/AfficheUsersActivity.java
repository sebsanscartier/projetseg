package i.taskmanager;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by newuser on 11/20/17.
 */

public class AfficheUsersActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.affiche_users_activity);

        //On obtient le RecyclerView que nous avons créé dans le fichier XML de layout.
        RecyclerView v = (RecyclerView) findViewById(R.id.affiche_users_recyclerView);

        //On rempli le tableau avec les différentes tâches.
        ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
        Role parent = new Role(true,"Parent (Admin)");
        Role fille = new Role(true,"Fille");
        Role garcon = new Role(true,"Garçon");


        Utilisateur u1 = new Utilisateur("Steve","Macacou",20,35.0,parent, "user", 123);
        Utilisateur u2 = new Utilisateur("Niève","Landry",3,20.0,fille, "user", 123);
        Utilisateur u3 = new Utilisateur("Carry","Rouge",60,567.0,garcon, "user", 123);
        Utilisateur u4 = new Utilisateur("Karine","Padoudou",78,1034.0,parent, "user", 123);

        utilisateurs.add(u1);
        utilisateurs.add(u2);
        utilisateurs.add(u3);
        utilisateurs.add(u4);
        utilisateurs.add(u1);
        utilisateurs.add(u2);
        utilisateurs.add(u3);
        utilisateurs.add(u4);
        utilisateurs.add(u1);
        utilisateurs.add(u2);
        utilisateurs.add(u3);
        utilisateurs.add(u4);


        //On fait en sorte d'ajouter une orientation et un layout au recycler view pour qu'il puisse se construire correctement
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        v.setLayoutManager(manager);

        //On ajoute un diviseur noir entre les cases du recycler view, question de style!
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(v.getContext(), manager.getOrientation());
        v.addItemDecoration(mDividerItemDecoration);

        //On fait en sorte de mettre l'adapter au recycler view, afin de pouvoir afficher toutes les différentes tâches.
        AfficheUsersAdapter adapter = new AfficheUsersAdapter(utilisateurs,getApplicationContext());
        v.setAdapter(adapter);




    }




}

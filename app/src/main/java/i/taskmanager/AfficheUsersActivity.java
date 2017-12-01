package i.taskmanager;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/*
 * Auteur : Groupe de travail i++
 * Fichier : AfficheUsersActivity.java
 * Description : Activité qui permet d'afficher tous les utilisateurs inscrits au système. On prend ceux-ci à partir de la base de données
 */

public class AfficheUsersActivity extends AppCompatActivity {


    //Toutes les variables d'instance
    private ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
    private RecyclerView v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.affiche_users_activity);

        //On obtient le RecyclerView que nous avons créé dans le fichier XML de layout.
        v = (RecyclerView) findViewById(R.id.affiche_users_recyclerView);


        //On fait en sorte d'ajouter une orientation et un layout au recycler view pour qu'il puisse se construire correctement
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        v.setLayoutManager(manager);

        //On ajoute un diviseur noir entre les cases du recycler view, question de style!
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(v.getContext(), manager.getOrientation());
        v.addItemDecoration(mDividerItemDecoration);


    }

    //Méthode permettant d'obtenir tous les utilisateurs de la base de données.
    @Override
    protected void onStart() {
        super.onStart();

        //Obtenir la base de données
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users");

        //Supprimer tout ce qui est dans la liste des utilisateurs pour la recommencer à nouveau.
        utilisateurs.clear();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            //On affiche dans cette méthode tous les utilisateurs qui sont dans la base de donneés.
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                    Utilisateur tmp = snapShot.getValue(Utilisateur.class);
                    utilisateurs.add(tmp);
                }

                //On terminer par créer un nouvel adapteur, qui va afficher les différents utilisateurs entrés dans le tableau.
                AfficheUsersAdapter adapter = new AfficheUsersAdapter(utilisateurs, getApplicationContext());
                v.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Cette méthode ne nous intéresse pas, mais est nécessaire au fonctionnement du valueEventListener.
            }
        });


    }


}

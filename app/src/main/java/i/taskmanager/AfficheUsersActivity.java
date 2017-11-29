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

/**
 * Created by newuser on 11/20/17.
 */

public class AfficheUsersActivity extends AppCompatActivity {

    private ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
    RecyclerView v;

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

        //On fait en sorte de mettre l'adapter au recycler view, afin de pouvoir afficher toutes les différentes tâches.





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
                for (DataSnapshot snapShot : dataSnapshot.getChildren()){
                    Utilisateur tmp = snapShot.getValue(Utilisateur.class);
                    Log.d("SOURCE",tmp.getNom());
                    utilisateurs.add(tmp);
                }



                AfficheUsersAdapter adapter = new AfficheUsersAdapter(utilisateurs,getApplicationContext());
                v.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


}

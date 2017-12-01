package i.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by newuser on 11/20/17.
 */

public class ListeTacheActivity extends AppCompatActivity {

    private Utilisateur userPrincipal;
    private ImageView imagePlus;

    ArrayList<Tache> taches = taches = new ArrayList<>();
    RecyclerView v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_liste_tache_activity);


        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        userPrincipal = (Utilisateur) bundle.getSerializable("user");
        imagePlus = (ImageView) findViewById(R.id.liste_tache_ajouterTache);

        if(!userPrincipal.getRole().getAdministrative()){
            imagePlus.setVisibility(View.INVISIBLE);
        }

        //On obtient le RecyclerView que nous avons créé dans le fichier XML de layout.
        v = (RecyclerView) findViewById(R.id.listetacheactivity_recyclerView);


        //On fait en sorte d'ajouter une orientation et un layout au recycler view pour qu'il puisse se construire correctement
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        v.setLayoutManager(manager);

        //On ajoute un diviseur noir entre les cases du recycler view, question de style!
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(v.getContext(), manager.getOrientation());
        v.addItemDecoration(mDividerItemDecoration);

        //On fait en sorte de mettre l'adapter au recycler view, afin de pouvoir afficher toutes les différentes tâches.
        ListeTacheAdapter adapter = new ListeTacheAdapter(taches,getApplicationContext());
        v.setAdapter(adapter);




    }



    //Méthode permettant de lancer l'affichage du profil de l'utilisateur, lorsque celui-ci clique sur son avatar.
    public void afficheProfileActivity(View v){
        Intent t = new Intent(getApplicationContext(),AfficheProfileActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", userPrincipal);
        t.putExtras(bundle);
        startActivity(t);
    }

    public void ajouteTacheActivity(View v){
        Intent t = new Intent(getApplicationContext(),AjouteTacheActivity.class);
        startActivity(t);
    }

    public void afficheUsersActivity(View v){
        Intent t = new Intent(getApplicationContext(),AfficheUsersActivity.class);
        startActivity(t);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("tache");
        taches.clear();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapShot : dataSnapshot.getChildren()){
                        Tache tmp = snapShot.getValue(Tache.class);
                        String nomPersonne = userPrincipal.getPrenom()+" "+userPrincipal.getNom();

                        if(tmp.getDestinataire().equals(nomPersonne)){
                            taches.add(tmp);
                        }

                }



                ListeTacheAdapter adapter = new ListeTacheAdapter(taches,getApplicationContext());
                v.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


}

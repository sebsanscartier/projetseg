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
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/*
 * Auteur : Groupe de travail i++
 * Fichier : ListeTacheActivity.java
 * Description : Activité principale après la connexion. Celle-ci permet d'obtenir plusieurs fenêtre, en plus d'afficher
 * toutes les tâches actives reliées à un utilisateur en particulier.
 */

public class ListeTacheActivity extends AppCompatActivity {


    //Variables d'instance
    private Utilisateur userPrincipal;
    private ImageView imagePlus, imageIcone;
    private ArrayList<Tache> taches = taches = new ArrayList<>();
    private RecyclerView v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_liste_tache_activity);

        //On obtient l'information transmise par l'intent, soit l'utilisateur.
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        userPrincipal = (Utilisateur) bundle.getSerializable("user");

        //On obtient toutes les composantes nécessaires dans la classe.
        imagePlus = (ImageView) findViewById(R.id.liste_tache_ajouterTache);
        imageIcone = (ImageView) findViewById(R.id.liste_tache_image);

        //On change l'image affichant le rôle de l'utilisateur en fonction de celui-ci.
        if (userPrincipal.getRole().getDescription().trim() == "Parent (Admin)") {
            imageIcone.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.king));
        } else if (userPrincipal.getRole().getDescription().trim().equals("Fille")) {
            imageIcone.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.madame));
        } else if (userPrincipal.getRole().getDescription().trim().equals("Garçon")) {
            imageIcone.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.monsieur));
        }

        //Si l'utilisateur n'est pas un administrateur, celui-ci ne peut pas afficher de tâche, on cache donc le bouton.
        if (!userPrincipal.getRole().getAdministrative()) {
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


    }


    //Méthode permettant de lancer l'affichage du profil de l'utilisateur, lorsque celui-ci clique sur son avatar.
    public void afficheProfileActivity(View v) {
        Intent t = new Intent(getApplicationContext(), AfficheProfileActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", userPrincipal);
        t.putExtras(bundle);
        startActivity(t);
    }


    //Méthode permettant d'afficher l'activité d'ajout d'une tâche.
    public void ajouteTacheActivity(View v) {
        Intent t = new Intent(getApplicationContext(), AjouteTacheActivity.class);
        startActivity(t);
    }


    //Méthode permettant d'afficher l'activité qui affiche la liste des utilisateurs.
    public void afficheUsersActivity(View v) {
        Intent t = new Intent(getApplicationContext(), AfficheUsersActivity.class);
        startActivity(t);
    }

    //Méthode permettant d'afficher toutes les tâches relatives à une utilisateur
    @Override
    protected void onStart() {
        super.onStart();

        //On obtient l'objet de la base de données.
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("tache");

        //On vide la liste qui contient toutes les tâches, au cas où il y aura des ajouts. On recommence à neuf!
        taches.clear();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //On passe à travers toutes les valeurs trouvées, puis on tent de les ajouter dans la liste qui va être affichée dans le recyclerView.
                for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                    Tache tmp = snapShot.getValue(Tache.class);
                    String nomPersonne = userPrincipal.getPrenom() + " " + userPrincipal.getNom();

                    if (tmp.getDestinataire().equals(nomPersonne) && !tmp.getCompleted()) {
                        taches.add(tmp);
                    }

                }

                //On crée un nouvel adapteur qu'on va placer dans le recyclerView et qui va afficher les valeurs qu'on a trouvé dans la base de données.
                ListeTacheAdapter adapter = new ListeTacheAdapter(taches, getApplicationContext(), userPrincipal);
                v.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Méthode obligatoire que l'on utilise pas réellement, car ce n'est pas ce qu'on désire faire!.
            }
        });


    }


}

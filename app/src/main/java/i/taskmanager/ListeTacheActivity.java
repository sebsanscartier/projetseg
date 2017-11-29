package i.taskmanager;

import android.content.Intent;
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

public class ListeTacheActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_liste_tache_activity);

        //On obtient le RecyclerView que nous avons créé dans le fichier XML de layout.
        RecyclerView v = (RecyclerView) findViewById(R.id.listetacheactivity_recyclerView);

        //On rempli le tableau avec les différentes tâches.
        ArrayList<Tache> taches = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Tache c = new Tache((i*10.0 + 4),"Laver le chien",false,"2017-11-21","2017-12-01","mimi");
            Tache w = new Tache((i*10.0 + 4),"Laver le chien",false,"Le faire avec une vieille brosse s'il-vous plait! Ne pas prendre les nouvelles brosses de maman, elle va être extrêmement fâchée","2017-11-21","2017-12-01","mama");
            taches.add(c);
            taches.add(w);
        }

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


}

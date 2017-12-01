package i.taskmanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by newuser on 11/21/17.
 */

public class ListeTacheAdapter extends RecyclerView.Adapter<ListeTacheAdapter.ViewHolder> {

    public ArrayList<Tache> taches = new ArrayList<>();
    public Context context;
    public Utilisateur user;

    public ListeTacheAdapter(ArrayList<Tache> items,Context context, Utilisateur user) {
        this.user = user;
        this.taches = items;
        this.context = context;


    }



    //Méthode permettant de créer la vue, qui sera ensuite utilisée dans l'affichage de toutes les vues, dans le recyclerView.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Obtenir la sous-vue créée.
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.liste_tache_activity_case, parent, false);
        return new ViewHolder(v);

    }


    //Méthode qui est appelée à chaque rafraichissement du recycler view. C'est ici qu'on insère le texte nécessaire dans la view,
    //En fonction de la tâche à laquelle nous sommes rendu à afficher.
    @Override public void onBindViewHolder(ViewHolder holder, int position) {

        Tache item = taches.get(position);
        holder.description.setText(item.getDescription());

        if(item.getNote().length() > 0){
            holder.note.setText(item.getNote());
        }else{
            holder.note.setText("Aucune Note");

        }

        //Méthode Lambda qui remplace simplement un nouveau ClickListener, ainsi qu'une méthode de callback.
        // Je sais, j'ai dû remplacer le language par un niveau 8 pour faire ceci... mais ce n'est dit nulpart qu'on ne peut pas le faire!
        holder.itemView.setOnClickListener((a)->{
            Intent w = new Intent(context,AfficheTacheActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("tache", item);
            bundle.putSerializable("user", user);
            w.putExtras(bundle);
            context.startActivity(w);
        });


    }



    //Méthode qui retourne le nombre d'items qui sont présents dans notre ArrayList contenant toutes les tâches.
    @Override
    public int getItemCount() {

        return taches.size();

    }



    //Sous-Classe, permettant de créer les vues sur mesure, puis de changer les attributs de celle-ci, à travers le code,
    //permettant d'obtenir des vues dynamiques et non pré-définies par Android, nous laissant place au design.
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView description,note, noteTitle;
        public Boolean pasChanger = true;
        //Variable qui va nous permettre d'afficher ou non la note dans la vue. C'est simplement question de style, afin de ne pas
        //Avoir des cases trop grandes et séparées entre elles... ça serait un peu étrange :S...
        public int maxHeight = 0;


        public ViewHolder(View itemView) {
            super(itemView);
            description = (TextView) itemView.findViewById(R.id.liste_tache_case_description);
            note = (TextView) itemView.findViewById(R.id.liste_tache_case_note);
            noteTitle = (TextView) itemView.findViewById(R.id.liste_tache_case_noteTitle);




        }

    }



}





package i.taskmanager;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/*
 * Auteur : Groupe de travail i++
 * Fichier : AfficheUsersAdapter.java
 * Description : Adapteur permettant de créer des cases spécifiques pour le recycler view, contenant l'information d'un utilisateur.
 * Nous avons utilisé le recyclerView, car il utilise la mémoire cache, et permet un calcul plus rapide des informations d'affichage,
 * contrairement à la listview. Il permet donc une performance supplémentaire pour l'utilisateur.
 */
public class AfficheUsersAdapter extends RecyclerView.Adapter<AfficheUsersAdapter.ViewHolder> {

    //Les variables d'instance
    private ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
    private Context context;


    //Constructeur principal
    public AfficheUsersAdapter(ArrayList<Utilisateur> items, Context context) {

        this.utilisateurs = items;
        this.context = context;


    }


    //Méthode permettant de créer la vue, qui sera ensuite utilisée dans l'affichage de toutes les vues, dans le recyclerView.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Obtenir la sous-vue créée.
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_view, parent, false);
        return new ViewHolder(v);

    }


    //Méthode qui est appelée à chaque rafraichissement du recycler view. C'est ici qu'on insère le texte nécessaire dans la view,
    //En fonction de la tâche à laquelle nous sommes rendu à afficher.
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //Obtenir l'utilisateur à la position position, puis mettre à jour l'affichage des composantes dans la vue.
        Utilisateur u = utilisateurs.get(position);
        holder.nom.setText(u.getPrenom() + " " + u.getNom());
        holder.points.setText(u.getNbreDePoints() + " points");


        //Dépendamment du role, on change l'image d'affichage de la case de la recyclerview.
        if (u.getRole().getDescription() == "Parent (Admin)") {
            holder.image.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.king));
        } else if (u.getRole().getDescription() == "Fille") {
            holder.image.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.madame));
        } else {
            holder.image.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.monsieur));
        }


        //Méthode Lambda qui remplace simplement un nouveau ClickListener, ainsi qu'une méthode de callback.
        // Je sais, j'ai dû remplacer le language par un niveau 8 pour faire ceci... mais ce n'est dit nulpart qu'on ne peut pas le faire!
        holder.itemView.setOnClickListener((a) -> {
            Intent w = new Intent(context, AfficheProfileActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("user", u);
            w.putExtras(bundle);
            context.startActivity(w);
        });


    }


    //Méthode qui retourne le nombre d'items qui sont présents dans notre ArrayList contenant toutes les tâches.
    @Override
    public int getItemCount() {

        return utilisateurs.size();

    }


    //Sous-Classe, permettant de créer les vues sur mesure, puis de changer les attributs de celle-ci, à travers le code,
    //permettant d'obtenir des vues dynamiques et non pré-définies par Android, nous laissant place au design.
    public static class ViewHolder extends RecyclerView.ViewHolder {

        //Variables d'instance.
        public TextView nom, points;
        public ImageView image;


        public ViewHolder(View itemView) {
            super(itemView);
            nom = (TextView) itemView.findViewById(R.id.user_view_nom);
            points = (TextView) itemView.findViewById(R.id.user_view_nbPoints);
            image = (ImageView) itemView.findViewById(R.id.user_view_photo);


        }

    }


}





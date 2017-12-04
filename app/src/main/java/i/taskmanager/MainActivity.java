package i.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/*
 * Auteur : Groupe de travail i++
 * Fichier : MainActivity.java
 * Description : Activité principale d'accueil. Celle-ci permet de se connecter ou de créer un nouvel utilisateur.
 */

public class MainActivity extends AppCompatActivity {

    //Variables d'instance
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtenir les différentes composantes de la vue pour utilisation dans l'application par la suite.
        username = (EditText) findViewById(R.id.menu_username);
        password = (EditText) findViewById(R.id.menu_password);
    }

    //Méthode qui permet de vérifier le nom d'utilisateur dans la base de donnée et le mot de passe associé, avec ce qui a été entré.
    public void LogIn(View v) {

        if (username.getText().toString().equals("Cedric")) {
            //Easter EGG! Nous aimons beaucoup Harry Potter et notre TA Cedric...!
            Intent t = new Intent(getApplicationContext(), EasterEgg.class);
            startActivity(t);
            finish();
        } else {
            //Insérer le code pour la connexion ici.
            ValueEventListener valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        //Ce bout de code sert à obtenir l'utilisateur et de vérifier si le mot de passe correspond à ce qui a été entré.
                        //Dans ce cas, on va simplement le diriger vers la liste des taches, soit l'écran particulier. Dans l'autre cas,
                        //On fait simplement afficher un toast qui alert l'utilisateur de son mauvais mot de passe ou que l'utilisateur
                        //n'existe pas dans la base de données.
                        if (postSnapshot.getValue() != null) {
                            //Il y a un utilisateur avec ce nom.
                            Utilisateur tmp = postSnapshot.getValue(Utilisateur.class);
                            try {
                                if (Integer.parseInt(password.getText().toString()) == tmp.getPass()) {


                                    Intent t = new Intent(getApplicationContext(), ListeTacheActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("user", tmp);
                                    t.putExtras(bundle);
                                    startActivity(t);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Mauvais mot de passe.", Toast.LENGTH_LONG).show();
                                    password.setText("");
                                }
                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Veuillez entrer un mot de passe numeric et qui fait du sens...", Toast.LENGTH_LONG).show();
                            }

                        } else {
                            //Il n'y en a pas!
                            Toast.makeText(getApplicationContext(), "Il n'y a pas d'utilisateur avec ce nom d'utilisateur.", Toast.LENGTH_LONG).show();
                        }

                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };

            //Obtenir la base de donnée, puis l'utilisateur dont le nom d'utilisateur correspond à ce qui a été entré, pour vérifier le mot de passe par la suite.
            DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference("users");
            Query query = mFirebaseDatabaseReference.orderByChild("user").equalTo(username.getText().toString());
            query.addValueEventListener(valueEventListener);


        }
    }

    //Méthode qui permet d'aller vers l'afficahge de la vue pour ajouter un utilisateur lorsqu'on va cliquer sur ce bouton.
    public void ajoutUserActivity(View v) {
        Intent t = new Intent(getApplicationContext(), AjoutUserActivity.class);
        startActivity(t);
    }
}

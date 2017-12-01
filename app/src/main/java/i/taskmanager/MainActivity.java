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

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.menu_username);
        password = (EditText) findViewById(R.id.menu_password);
    }

    public void LogIn(View v){

        if(username.getText().toString().equals("Cedric")) {
            //Easter EGG! Nous aimons beaucoup Harry Potter et notre TA Cedric...!
            Intent t = new Intent(getApplicationContext(), EasterEgg.class);
            startActivity(t);
            finish();
        }else {
            //Insérer le code pour la connexion ici.
            // 1. obtenir, de la base de donnée, la personne

            ValueEventListener valueEventListener = new ValueEventListener()
            {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot)
                {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                    {

                        if(postSnapshot.getValue() != null) {
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
                            }catch(NumberFormatException e){
                                Toast.makeText(getApplicationContext(), "Veuillez entrer un mot de passe numeric et qui fait du sens...", Toast.LENGTH_LONG).show();
                            }

                        }else{
                            //Il n'y en a pas!
                            Toast.makeText(getApplicationContext(), "Il n'y a pas d'utilisateur avec ce nom d'utilisateur.", Toast.LENGTH_LONG).show();
                        }

                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError)
                {

                }
            };


            DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference("users");
            Query query = mFirebaseDatabaseReference.orderByChild("user").equalTo(username.getText().toString());
            query.addValueEventListener(valueEventListener);


        }
    }

    public void ajoutUserActivity(View v){
        Intent t = new Intent(getApplicationContext(),AjoutUserActivity.class);
        startActivity(t);
    }
}

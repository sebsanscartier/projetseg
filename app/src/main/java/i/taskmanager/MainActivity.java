package i.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
            // 1. obtenir, de la base de donnée, la personne!
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference lili = database.getReference("users").child(didi);

            Intent t = new Intent(getApplicationContext(), ListeTacheActivity.class);
            startActivity(t);
            finish();
        }
    }

    public void ajoutUserActivity(View v){
        Intent t = new Intent(getApplicationContext(),AjoutUserActivity.class);
        startActivity(t);
    }
}

package i.taskmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by newuser on 11/22/17.
 */

public class EasterEgg extends AppCompatActivity {


    //Classe qui permet d'amener un EasterEgg dans le projet, pour notre plaisir personnel!
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.digori_layout);

        Toast.makeText(this, "MON FILS! C'EST MON FILS!!", Toast.LENGTH_LONG).show();

    }




}

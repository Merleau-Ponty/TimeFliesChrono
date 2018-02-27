package com.lmp.timeflies.player;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lmp.timeflies.technique.ChronoService;

/**
 * Created by Laure on 24/02/2018.
 */

public class Play_FinDePartie extends AppCompatActivity {
    SharedPreferences mpref;
    SharedPreferences.Editor mEditor;
    private Button button3;
    private TextView resultatTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_fin);

        resultatTV = findViewById(R.id.chrono_arret);
        mpref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mEditor = mpref.edit();
        String str_value = mpref.getString("data", "");
        Toast.makeText(getApplicationContext(),"data : "+str_value, Toast.LENGTH_LONG).show();

        Bundle extras = getIntent().getExtras();

        String temps = extras.getString("time");
        long mins = mpref.getLong("minsutes",0);
        long mins2 = extras.getLong("mins");
        Toast.makeText(getApplicationContext(),"v2 : "+temps+"\n"+"minutes restantes :"+mins+"/"+mins2, Toast.LENGTH_LONG).show();
        resultatTV.setText("Temps restant : "+ temps);
        Intent intent = new Intent(getApplicationContext(),ChronoService.class);
        stopService(intent);
        //mEditor.clear().commit();
        // Classement
        button3 = (Button) findViewById(R.id.button3);
        button3.setEnabled(true);
        button3.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {
            Intent stats = new Intent(getApplicationContext(), Play_Classement.class);
            startActivity(stats);

        }
        });
    }



}

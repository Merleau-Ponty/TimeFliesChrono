package com.lmp.timeflies.player;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lmp.timeflies.technique.Play_Chat;
import com.lmp.timeflies.technique.Play_ScanActivity;

/**
 * Created by casti on 13/02/2018.
 */

public class Play_Enigme extends AppCompatActivity {
    TextView afficherEnigme = null;
    EditText insererReponse = null;
    Button buttonValider = null;
    Button buttonIndice = null;
    Button buttonAide = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_enigme);

        buttonValider = (Button) findViewById(R.id.xbtn_valider);
        //buttonValider.setEnabled(true);
        buttonIndice = (Button) findViewById(R.id.xbtn_indice);
        //buttonValider.setEnabled(true);
        buttonAide = (Button) findViewById(R.id.xbtn_aide);
        //buttonValider.setEnabled(true);

        Bundle extras = getIntent().getExtras();

        buttonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insererReponse = (EditText) findViewById(R.id.xrep);
                String laReponse = insererReponse.getText().toString();
            }
        });

        buttonIndice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent indice = new Intent(getApplicationContext(), Play_ScanActivity.class);
                startActivity(indice);
            }
        });

        buttonAide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aide = new Intent(getApplicationContext(), Play_Chat.class);
                startActivity(aide);
            }
        });
    }
}

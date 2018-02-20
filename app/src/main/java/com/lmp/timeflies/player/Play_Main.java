package com.lmp.timeflies.player;

import android.app.Activity;

/**
 * Created by Laure on 18/02/2018.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Play_Main extends AppCompatActivity {

    Button button1 = null;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_main);

        button1 = (Button) findViewById(R.id.button1);
        button1.setEnabled(true);
        button1.setOnClickListener(new View.OnClickListener() {
            // button1.setOnClickListener(){
            @Override
            public void onClick(View v) {
                button1.setEnabled(false);
                Intent inscript = new Intent(getApplicationContext(), Play_Inscription.class);
                startActivity(inscript);
                //button1.setEnabled(false);
            }
        });
//commencer à jouer
        button2 = (Button) findViewById(R.id.button2);
        button2.setEnabled(true);
        button2.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {
            Intent demarrer = new Intent(getApplicationContext(), Play_DemarrerPartie.class);
            startActivity(demarrer);
            button2.setEnabled(false);
        }
        });
//Classement
        button3 = (Button) findViewById(R.id.button3);
        button3.setEnabled(true);
        button3.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {
        Intent stats = new Intent(getApplicationContext(), Play_Classement.class);
        startActivity(stats);
        button2.setEnabled(false);
        }
        });


    }
}
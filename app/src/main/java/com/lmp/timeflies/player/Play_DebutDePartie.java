package com.lmp.timeflies.player;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lmp.timeflies.technique.ChronoService;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by casti on 12/02/2018.
 */

public class Play_DebutDePartie extends Activity {
    Button button1 = null;
    private Button button2;
    private Button button3;

    SharedPreferences mpref;
    SharedPreferences.Editor mEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_debut);
        mpref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mEditor = mpref.edit();
        //mEditor.putBoolean("en_cours",false).commit();

        button1 = (Button) findViewById(R.id.button1);
        button1.setEnabled(true);
        button1.setOnClickListener(new View.OnClickListener() {
            // button1.setOnClickListener(){
            @Override
            public void onClick(View v) {
                Intent inscript = new Intent(getApplicationContext(), Play_Inscription.class);
                startActivity(inscript);
                button1.setEnabled(false);
            }
        });
//commencer à jouer
        button2 = (Button) findViewById(R.id.button2);
        button2.setEnabled(true);
        button2.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {
            Intent commencer = new Intent(getApplicationContext(), Play_Objectifs.class);
            startActivity(commencer);
            //button2.setEnabled(false);
            String textOriginal = (String) getApplicationContext().getString(R.string.demarrer);
            if (button2.getText().toString() == textOriginal){
                mEditor.putString("en_cours", "on_demarre").commit();
                button2.setText("Continuer la partie");
                button1.setEnabled(false);
                /*mEditor.putString("minutes","2").commit();
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                String date_time = simpleDateFormat.format(calendar.getTime());
                mEditor.putString("data",date_time).commit();*/
            }
            else {
                mEditor.putString("en_cours","partie_en_cours").commit();
            }
        }
        });
//Classement
        button3 = (Button) findViewById(R.id.button3);
        button3.setEnabled(true);
        button3.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {
            Intent stats = new Intent(getApplicationContext(), Play_Classement.class);
            startActivity(stats);
        }
        });

    }

   /* @Override
    protected void onRestart() {
        super.onRestart();
        mEditor.putString("en_cours", "partie_en_cours").commit();
        String txt_btn = mpref.getString("en_cours", "");
        Toast msg = null;
        msg.makeText(getApplicationContext(), "" + txt_btn, Toast.LENGTH_LONG).show();
    }*/


}

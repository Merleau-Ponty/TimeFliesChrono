package com.lmp.timeflies.player;

import android.app.ActionBar;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lmp.timeflies.technique.ChronoService;
import com.lmp.timeflies.technique.Play_Chat;
import com.lmp.timeflies.technique.Play_ScanActivity;

/**
 * Created by casti on 13/02/2018.
 */

public class Play_Enigme extends AppCompatActivity {
    TextView afficherEnigme = null;
    TextView sous_titre= null;
    EditText insererReponse = null;
    Button buttonValider = null;
    Button buttonIndice = null;
    Button buttonAide = null;
    Button buttonStop = null;

    SharedPreferences mpref;
    SharedPreferences.Editor mEditor;

    private String date_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_enigme);

        mpref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mEditor = mpref.edit();
        buttonValider = (Button) findViewById(R.id.xbtn_valider);
        //buttonValider.setEnabled(true);
        buttonIndice = (Button) findViewById(R.id.xbtn_indice);
        //buttonValider.setEnabled(true);
        buttonAide = (Button) findViewById(R.id.xbtn_aide);
        //buttonValider.setEnabled(true);
        buttonStop = (Button) findViewById(R.id.xbtn_cancel);

        sous_titre = (TextView) findViewById(R.id.sous_titre_enigme);
        Bundle extras = getIntent().getExtras();

        //sous_titre.setText(sous_titre.getText().toString()+date_time);

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

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ChronoService.class);
                stopService(intent);
                mEditor.putBoolean("finish", true).commit();

                Intent fin = new Intent(getApplicationContext(), Play_FinDePartie.class);
                //fin.putExtra("time",tv_timer.getText().toString());
                fin.putExtra("time",date_time);
                startActivity(fin);
                //mEditor.clear().commit();

            }
        });
    }

       private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String str_time = intent.getStringExtra("time");
            //tv_timer.setText(str_time);
            date_time = str_time;
            sous_titre.setText("Résoudre l'énigme - Temps restant : "+date_time);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver,new IntentFilter(ChronoService.str_receiver));

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

}

package com.lmp.timeflies.player;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lmp.timeflies.dao.DAO_Global;
import com.lmp.timeflies.technique.ChronoService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by casti on 13/02/2018.
 */

public class Play_Objectifs extends ListActivity implements AdapterView.OnItemClickListener {

    private ArrayList<String> lesObjectifs = new ArrayList<String>();
    private ListView listV;

    DAO_Global sgbd;
    ArrayAdapter<String> adapter;

    String date_time;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    TextView sous_titre = null;

    SharedPreferences mpref;
    SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_objectifs);
        sous_titre = (TextView) findViewById(R.id.title_list);

        sgbd = new DAO_Global(this);
        sgbd.open();
        init_liste();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lesObjectifs);
        // listV = findViewById(R.id.listObj);
        // listV.setAdapter(adapter);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
        mpref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mEditor = mpref.edit();
        //intialisation service

        mEditor.putString("data", date_time).commit();

        //modification ci-dessous pour stocker la valeur initiale du chrono
        if(mpref.getString("en_cours", "")=="on_demarre") {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            date_time = simpleDateFormat.format(calendar.getTime());
            mEditor.putString("data", date_time).commit();
            String ok =  mpref.getString("en_cours", "");
            //Toast.makeText(getApplicationContext(), "" + ok, Toast.LENGTH_LONG).show();
            //mEditor.putString("hours", "1").commit();
            Intent intent_service = new Intent(getApplicationContext(), ChronoService.class);
            startService(intent_service);
            mEditor.putString("en_cours","partie_en_cours").commit();
            //Toast.makeText(getApplicationContext(), "passage dans démarrage", Toast.LENGTH_LONG).show();
        }
        mEditor.putBoolean("finish", false).commit();
        //Toast.makeText(getApplicationContext(), "passage dans onCreate", Toast.LENGTH_LONG).show();
        //fin init
    }

    protected void init_liste() {
        /*lesObjectifs.add("Mission # 1");
        lesObjectifs.add("Mission # 2");
        lesObjectifs.add("Mission # 3");*/
        lesObjectifs = sgbd.donneLesNomsDesObjectifs();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
       /* Toast msg = null;
        msg.makeText(getApplicationContext(), "" + i, Toast.LENGTH_LONG).show();*/
        Intent lesEnigmes = new Intent(getApplicationContext(), Play_ListeEnigmes.class);
        lesEnigmes.putExtra("o_id", lesObjectifs.get(i));
        lesEnigmes.putExtra("o_lieu", lesObjectifs.get(i));
        lesEnigmes.putExtra("o_nom", lesObjectifs.get(i));
        lesEnigmes.putExtra("o_description", lesObjectifs.get(i));
        startActivity(lesEnigmes);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String str_time = intent.getStringExtra("time");
            sous_titre.setText("Voici vos objectifs - Temps restant : "+str_time);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver,new IntentFilter(ChronoService.str_receiver));
    }

   /* protected void onRestart() {
        super.onRestart();
        registerReceiver(broadcastReceiver,new IntentFilter(ChronoService.str_receiver));
    }*/
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

}
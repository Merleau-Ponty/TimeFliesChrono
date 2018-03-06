package com.lmp.timeflies.player;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.lmp.timeflies.dao.DAO_Global;
import com.lmp.timeflies.metier.Enigme;
import com.lmp.timeflies.metier.Objectif;
import com.lmp.timeflies.technique.ChronoService;

import java.util.ArrayList;

/**
 * Created by casti on 13/02/2018.
 */

public class Play_ListeEnigmes extends ListActivity implements AdapterView.OnItemClickListener {

    Objectif ox;
    ArrayList<String> lesEnigmesObj ;

    TextView nomObjectif;
    TextView descrObjectif;
    private TextView sous_titre;
    public String date_time;

    String id_obj;
    String lieu;
    String nom;
    String descr;


    //ListView listeEnigmes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_liste_enigmes);

        //on récupère le nom de l'Objectif sélectionné
        //et la description de l'Objectif
        Bundle extras = getIntent().getExtras();

        id_obj = extras.getString("o_id");
        lieu = extras.getString("o_lieu");
        nom = extras.getString("o_nom");
        descr = extras.getString("o_description");

        //on affiche les deux
        sous_titre = (TextView) findViewById(R.id.xnom_tv);
        descrObjectif = (TextView) findViewById(R.id.xdescr_tv);

        descrObjectif.setText(lieu+" : "+nom);
        //on affiche la liste des énigmes de l'Objectif
        liste_enigmes(id_obj);

        //adapter pour lier la Collection et la ListView
        ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lesEnigmesObj);
        setListAdapter(adapt);

        //c'est l'héritage de ListActivity qui permet d'avoir la méthode getListView()
        getListView().setOnItemClickListener(this); //il set depuis notre méthode overriden

    }//fin onCreate()


    protected void liste_enigmes(String nom){
        //on prend dans la BDD les énigmes de l'Objectif sélectionné
        //int id_o = (int) Integer.parseInt(id_obj);
        lesEnigmesObj = new ArrayList<>();

        DAO_Global dao = new DAO_Global(this);

        //ox = (Objectif) dao.donneObjectif(nom) ;//on utilise la requête pour prendre l'objectif where id=id_obj
        ArrayList<Enigme> lesEnigmes = new ArrayList<Enigme>();
        Enigme e1 = new Enigme(1,100,"énigme 1","voici ce que...", "reponse A", 1);
        Enigme e2 = new Enigme(2,100,"énigme 2","voici ce que...", "reponse B", 1);
        Enigme e3 = new Enigme(3,100,"énigme 3","voici ce que...", "reponse C", 1);
        lesEnigmes.add(e1);lesEnigmes.add(e2);lesEnigmes.add(e3);
        //ox.setLesEnigmes(lesEnigmes);
       /* for(Enigme ex : ox.getLesEnigmes()){
            lesEnigmesObj.add(ex.getE_titre());
        }*/
        for(Enigme ex : lesEnigmes){
            lesEnigmesObj.add(ex.getE_titre());
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //quand on clique sur une des énigmes, on affiche l'EnigmeActivity
        //on lui envoie donc l'id de l'énigme
        Intent enigmeCliquee = new Intent(getApplicationContext(),Play_Enigme.class);
        String labExtra  = "idEnigme";
        String keyExtra = lesEnigmesObj.get(i);
        enigmeCliquee.putExtra(labExtra,keyExtra);
        startActivity(enigmeCliquee);
    }


    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String str_time = intent.getStringExtra("time");
            //tv_timer.setText(str_time);
            date_time = str_time;
            //sous_titre.setText("Votre objectif: "+nom+" - Temps restant : "+date_time);
            sous_titre.setText("Votre objectif: - Temps restant : "+date_time);
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


package com.lmp.timeflies.player;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lmp.timeflies.dao.DAO_Global;
import com.lmp.timeflies.metier.Enigme;
import com.lmp.timeflies.metier.Objectif;

import java.util.ArrayList;

/**
 * Created by casti on 13/02/2018.
 */

public class Play_ListeEnigmes extends ListActivity implements AdapterView.OnItemClickListener {

    Objectif ox;
    ArrayList<String> lesEnigmesObj ;

    TextView nomObjectif;
    TextView descrObjectif;
    //ListView listeEnigmes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_liste_enigmes);

        //on récupère le nom de l'Objectif sélectionné
        //et la description de l'Objectif
        Bundle extras = getIntent().getExtras();

        String id_obj = extras.getString("o_id");
        String nom = extras.getString("o_nom");
        String descr = extras.getString("o_description");

        //on affiche les deux
        nomObjectif= (TextView) findViewById(R.id.xnom_obj);


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
}


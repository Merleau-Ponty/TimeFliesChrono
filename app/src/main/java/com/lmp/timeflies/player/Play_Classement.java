package com.lmp.timeflies.player;


import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.lmp.timeflies.dao.DAO_Global;
import com.lmp.timeflies.metier.Equipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Created by casti on 13/02/2018.
 */

//Activity qui permet d'accéder au classement des équipes par score de Partie
//par tri : ordre décroissant (du plus grand au plus petit score d'équipe)
public class Play_Classement extends ListActivity {

    ArrayList<Equipe> lesEquipes = new ArrayList<Equipe>() ;
    DAO_Global dao = new DAO_Global(this);

    //pour associer le score de la partie à une équipe
    LinkedHashMap<Equipe, Integer> equipesScore = new LinkedHashMap<Equipe, Integer>();

    LinkedHashMap<Equipe, Integer> equipesScoreOrd = new LinkedHashMap<Equipe, Integer>();
    //LinkedHashMap<String,String> equipesScoreOrdString = new LinkedHashMap<String,String>();

    ArrayList<String> equipeScoreOrdonne = new ArrayList<String>();
    String nomEquipe;
    String scoreEquipe;
    int i=1;

    ArrayList<Integer> lesScores ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_classement);

        //on initialise lesEquipes avec chaque équipe de la BDD
        //lesEquipes = dao.donneLesEquipes();
        Equipe equipe = new Equipe(1, "tombe rail d'heure", "0602010201", 2);
        lesEquipes.add(equipe);

        //ON VEUT LA LISTE : LE NOM DE L'EQUIPE + LE SCORE
        liste_equipesScoreOrd();

        //SI ON UTILISAIT L'HashMapAdapter
        //on nomme equipesScoreOrdString<lenomdelequipe,lescoredelequipe> (string,string)
        /*for (Map.Entry<Equipe, Integer> eso : equipesScoreOrd.entrySet()){
            equipesScoreOrdString.put(eso.getKey().getEq_nom(),Integer.toString(eso.getValue()));
        }*/


        //adapter pour lier la Collection et la ListView
        //HashMapAdapter<String, String> hashMapAdapter = new HashMapAdapter<String, String>(this, android.R.layout.simple_list_item_1, equipesScoreOrdString);
        //setListAdapter(hashMapAdapter);
        ArrayAdapter<String> arAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, equipeScoreOrdonne);
        setListAdapter(arAdapter);
    }//fin onCreate()

    protected void liste_equipesScoreOrd(){
        //pour chaque équipe on prélève son score
        for(Equipe e : lesEquipes){
         //   equipesScore.put(e,dao.donneLeScore(e));
            //on a notre HashMap(Equipe,son score)

            //lesScores.add(dao.donneLeScore(e));
        }

        //faire le tri, en créant le nouveau HashMap
        //on garde le score max et on ajoute le couple équipe/score dans le hashmap
            /*for (Map.Entry<Equipe, Integer> es : equipesScore.entrySet()){
                //comparer la value à toutes les autres
                if(es.getValue()>= Collections.max(lesScores)){
                    equipesScoreOrd.put(es.getKey(),es.getValue());
                }
            }*/


        //on peut sinon créer un arraylist avec le nom de l'équipe et le score
        //on parcourt le hashmap ordonné, on prend une string contenant (nom équipe - score)
           /* for(Map.Entry<Equipe, Integer> eso : equipesScoreOrd.entrySet()){
                nomEquipe=eso.getKey().getEq_nom();
                scoreEquipe=Integer.toString(eso.getValue());
                equipeScoreOrdonne.add(i+ ". Equipe :"+ nomEquipe + " - Score : " + scoreEquipe);
                i++;
            }*/
        nomEquipe="tombe rail d'heure";
        scoreEquipe="158";
        equipeScoreOrdonne.add(i+ ". Equipe :"+ nomEquipe + " - Score : " + scoreEquipe);
        i++;
        nomEquipe="lmp";
        scoreEquipe="230";
        equipeScoreOrdonne.add(i+ ". Equipe :"+ nomEquipe + " - Score : " + scoreEquipe);
        i++;
        nomEquipe="becom";
        scoreEquipe="122";
        equipeScoreOrdonne.add(i+ ". Equipe :"+ nomEquipe + " - Score : " + scoreEquipe);
    }
}




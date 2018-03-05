package com.lmp.timeflies.technique;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.lmp.timeflies.dao.DAO_Global;
import com.lmp.timeflies.metier.Objectif;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laure on 04/03/2018.
 */

public class TraitementJSON extends AsyncTask<String, Void, Boolean> {

    //DAO_Global
    private List<Objectif> lesObjectifs = new ArrayList<Objectif>();
    Context context;
    JSONObject jObj = null;
    URL url;
    HttpURLConnection connexion;
    DAO_Global sgbd;


    public TraitementJSON(Context context) {
        this.context = context;
        sgbd = new DAO_Global(context);
    }

    @Override
    protected Boolean doInBackground(String... urls) {

        Log.i("doInBack","le dÃ©part : ");
        sgbd.open();
        //sgbd.supprimeobjectifs();
        try {
            url = new URL(urls[0]);
            Log.i("doInBack","le fichier distant : "+urls[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Boolean result = false;
        try {
            String ficObjectifs;
            //RÃ©cupÃ©ration du contenu du fichier json sur serveur
            ficObjectifs = lectureFichierDistant();
            Log.i("doInBack","le fichier distant : "+ficObjectifs);
            //Transformation du fichier obtenu en objet JSON
            JSONObject jsonObjectifs = parseObjectifs(ficObjectifs);
            //Traitement de l'objet JSON pour obtenir des instances de Objectifs
            Log.i("doInBack","le fichier json : "+jsonObjectifs.toString());
            result = recObjectifs(jsonObjectifs);
            Log.i("doInback","nombre d'objectifs : "+lesObjectifs.size());
            int num = 1;
            StringBuilder message = new StringBuilder("les objectifs : ");
            //long id;
            for (Objectif objectif : lesObjectifs) {
                message.append(objectif.getNom());
                message.append("   :   ");
                message.append(objectif.getTopo());
                message.append("\n");
                num++;
                //id=sgbd.ajouteobjectif(objectif);
            }
            Log.i("doInback","message : "+message);
            sgbd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Boolean recObjectifs(JSONObject jsonObjectifs) {

        JSONArray lesobjectifs = null;
        try {

            lesobjectifs = jsonObjectifs.getJSONArray("lesObjectifs");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.i("recper","erreurJSArray");;
        }

        for(int i = 0; i < lesobjectifs.length(); i++){
            JSONObject nuplet = null;
            String nom, topo;
            Long id;
            Objectif objectif;
            try {
                nuplet = lesobjectifs.getJSONObject(i);
                // Log.i("rec","nuplet"+nuplet.toString());
                nom = nuplet.getString("nom");
                //  Log.i("rec","nom : "+nom);
                topo = nuplet.getString("topo");
                //   Log.i("rec","topo : "+topo);
                objectif = new Objectif(nom,topo);
                lesObjectifs.add(objectif);
                id=sgbd.ajouteobjectif(objectif);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private JSONObject parseObjectifs(String textObjectifs) {
        if (textObjectifs != null) {
            try {
                jObj = new JSONObject(textObjectifs);
            } catch (JSONException e) {
                e.printStackTrace();
                Log.i("parper","erreurJSObj");;
            }
            return jObj;
        }
        return null;
    }

    private String lectureFichierDistant() {
        StringBuilder builder = new StringBuilder();

        // adresse du serveur ( modification pour passage en production
        try {
            connexion = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String line;
        BufferedReader br= null;
        try {
            br = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            while ((line=br.readLine()) != null) {
                builder.append(line).append("\n");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    public List<Objectif> getLesObjectifs() {

        return lesObjectifs;
    }

    public String getLesNoms(){
        String liste = "";
        for (Objectif objectif : lesObjectifs)
            liste += objectif.getNom()+"\n";
        return liste;
    }
}

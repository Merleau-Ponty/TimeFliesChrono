package com.lmp.timeflies.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.lmp.timeflies.metier.Avatar;
import com.lmp.timeflies.metier.Enigme;
import com.lmp.timeflies.metier.Equipe;
import com.lmp.timeflies.metier.Indice;
import com.lmp.timeflies.metier.Joueur;
import com.lmp.timeflies.metier.Objectif;
import com.lmp.timeflies.metier.Partie;

import java.util.ArrayList;

/**
 * Created by valou on 23/01/2018.
 */

public class DAO_Global {

    private SQLiteDatabase maBase;
    private BDHelper monBDHelper;

    public DAO_Global(Context context) {
        monBDHelper = new BDHelper(context, "basetf", null, 1);
    }

    public void open() {
        maBase = monBDHelper.getWritableDatabase();
    }

    public void close() {
        maBase.close();
    }

    public long ajouteJoueur(Joueur unJoueur) {
        ContentValues v = new ContentValues();
        v.put("j_id", unJoueur.getJ_id());
        v.put("j_nom", unJoueur.getJ_nom());
        v.put("j_prenom", unJoueur.getJ_prenom());
        v.put("j_age", unJoueur.getJ_age());
        v.put("eq_id", unJoueur.getEq_id());
        return maBase.insert("joueur", null, v);
    }

    public long ajouteEquipe(Equipe equipe) {
        ContentValues v = new ContentValues();
        v.put("eq_id", equipe.getEq_id());
        v.put("eq_nom", equipe.getEq_nom());
        v.put("eq_numtel", equipe.getEq_numTel());
        v.put("a_id", equipe.getA_id());
        return maBase.insert("equipe", null, v);
    }

    public long ajoutePartie(Partie unePartie) {
        ContentValues v = new ContentValues();
        v.put("p_id", unePartie.getP_id());
        v.put("p_score", unePartie.getP_score());
        v.put("eq_id", unePartie.getEq_id());
        return maBase.insert("partie", null, v);
    }

    public long ajouteIndice(Indice unIndice) {
        ContentValues v = new ContentValues();
        v.put("i_id", unIndice.getI_id());
        v.put("i_libelle", unIndice.getI_libelle());
        v.put("i_penalite", unIndice.getI_penalite());
        v.put("i_type", unIndice.getI_type());
        v.put("e_id", unIndice.getE_id());
        return maBase.insert("indice", null, v);
    }

    public long ajouteEnigme(Enigme uneEnigme) {
        ContentValues v = new ContentValues();
        v.put("e_id", uneEnigme.getE_id());
        v.put("e_points", uneEnigme.getE_points());
        v.put("e_titre", uneEnigme.getE_titre());
        v.put("e_texte", uneEnigme.getE_texte());
        v.put("e_reponse", uneEnigme.getE_reponse());
        v.put("o_id", uneEnigme.getO_id());
        return maBase.insert("enigme", null, v);
    }

    public long ajouteAvatar(Avatar unObjectif) {
        ContentValues v = new ContentValues();
        v.put("a_id", unObjectif.getA_id());
        v.put("a_libelle", unObjectif.getA_libelle());
        v.put("a_nomfichier", unObjectif.getA_nomfichier());
        return maBase.insert("enigme", null, v);
    }

    public ArrayList<String> donneLesJoueurDuneEquipe(Equipe uneEquipe) {
        ArrayList<String> liste = new ArrayList<String>();
        Cursor c = maBase.rawQuery("select j_nom, j_prenom from joueur where eq_id = " + uneEquipe.getEq_id() + ";", null);
        while (c.moveToNext()){
            String nom = c.getString(0);
            String prenom = c.getString(1);
            liste.add(nom+" "+prenom);
        }
        if(liste == null){
            liste.add("Erreur de bdd!");
        }
        return liste;
    }

    public ArrayList<Indice> donneLesIndicesDuneEnigme(Enigme enigme){
        ArrayList<Indice> liste = new ArrayList<Indice>();
        Cursor c = maBase.rawQuery("select i_id, i_libelle, i_penalite, i_type from indice where e_id = "+enigme.getE_id()+";", null);
        while (c.moveToNext()){
            int id = c.getInt(0);
            String libelle = c.getString(1);
            int penalite = c.getInt(2);
            String type = c.getString(3);
            int e_id = enigme.getE_id();
            Indice indice = new Indice(id,libelle,type,e_id);
            liste.add(indice);
        }
        return liste;
    }

    public Partie donneLaPartieDuneEquipe(Equipe equipe){
        int id = 0;
        int score = 0;
        int e_id = 0;
        Cursor c = maBase.rawQuery("select p_id, p_score, e_id from equipe where e_id = "+equipe.getEq_id()+";", null);
        while (c.moveToNext()){
            id = c.getInt(0);
            score = c.getInt(1);
            e_id = equipe.getEq_id();
        }
        Partie laPartie = new Partie(id, score, e_id);
        return laPartie;
    }

    public Long ajouteObjectif(Objectif unObjectif) {
        ContentValues v = new ContentValues();
        v.put("o_id", unObjectif.getO_id());
        v.put("o_lieu", unObjectif.getO_lieu());
        v.put("o_nom", unObjectif.getO_nom());
        v.put("o_description", unObjectif.getO_description());
        return maBase.insert("objectif", null, v);
    }

    public ArrayList<String> donneLesNomsDesObjectifs(){
                    ArrayList<String> liste = new ArrayList<String>();
        Log.i("dans DAO avant req: ",liste.toString());
            Cursor c = maBase.rawQuery("select o_id, o_nom from objectif;", null);
            while (c.moveToNext()){
                //int id = c.getInt(0);
                String nom = c.getString(1);
                Log.i("dans DAO : ",nom);
                //liste.add(id+" # "+nom);
                liste.add(nom);
            }
        Log.i("dans DAO apres boucle:",liste.toString());
            return liste;
        }

    public ArrayList<Objectif> donneLesObjectifs(){
        ArrayList<Objectif> liste = new ArrayList<Objectif>();
        Cursor c = maBase.rawQuery("select o_id, o_lieu, o_nom, o_description from objectif;", null);
        while (c.moveToNext()){
            int id = c.getInt(0);
            String lieu = c.getString(1);
            String nom = c.getString(2);
            String topo = c.getString(3);
            Objectif objectif = new Objectif(id, lieu, nom, topo);
            liste.add(objectif);
        }
        return liste;
    }
    public void supprimeObjectifs(){
        maBase.delete("objectif", null, null);
    }
}


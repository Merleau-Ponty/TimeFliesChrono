package com.lmp.timeflies.metier;

/**
 * Created by Dark_kikou_du78 on 23/01/2018.
 */

public class Joueur {

    private int j_id;
    private String j_nom;
    private String j_prenom;
    private int j_age;
    private int eq_id;

    public Joueur(int j_id, String j_nom, String j_prenom, int j_age, int eq_id){
        this.j_id = j_id;
        this.j_nom = j_nom;
        this.j_prenom = j_prenom;
        this.j_age = j_age;
        this.eq_id = eq_id;
    }

    public String getJ_nom() {
        return j_nom;
    }

    public void setJ_nom(String j_nom) {
        this.j_nom = j_nom;
    }

    public String getJ_prenom() {
        return j_prenom;
    }

    public void setJ_prenom(String j_prenom) {
        this.j_prenom = j_prenom;
    }

    public int getJ_id() {

        return j_id;
    }

    public int getJ_age() {

        return j_age;
    }

    public void setJ_age(int j_age) {
        this.j_age = j_age;
    }

    public int getEq_id() {
        return eq_id;
    }

    public void setEq_id(int eq_id) {
        this.eq_id = eq_id;
    }
}

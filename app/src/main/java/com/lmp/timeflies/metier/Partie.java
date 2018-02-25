package com.lmp.timeflies.metier;

import java.util.ArrayList;

/**
 * Created by valou on 23/01/2018.
 */

public class Partie {

    private int p_id;
    private int p_score;
    private ArrayList<Objectif> lesObjectifs;
    private int eq_id;

    public Partie(int p_id, int p_score, int eq_id){
        this.p_id = p_id;
        this.p_score = p_score;
        this.eq_id = eq_id;
    }

    public int getP_id() {
        return p_id;
    }

    public int getP_score() {
        return p_score;
    }

    public void setP_score(int p_score) {
        this.p_score = p_score;
    }

    public ArrayList<Objectif> getLesObjectifs() {
        return lesObjectifs;
    }

    public void setLesObjectifs(ArrayList<Objectif> lesObjectifs) {
        this.lesObjectifs = lesObjectifs;
    }

    public int getEq_id() {
        return eq_id;
    }

    public void setE_id(int eq_id) {
        this.eq_id = eq_id;
    }
}

package com.lmp.timeflies.metier;

import java.util.ArrayList;

/**
 * Created by valou on 23/01/2018.
 */

public class Objectif {

    private int o_id;
    private String o_nom;
    private String o_description;
    private int o_points_bonus;
    ArrayList<Enigme> lesEnigmes;

    public Objectif(int o_id, String o_nom, String o_description, int o_points_bonus){
        this.o_id = o_id;
        this.o_nom = o_nom;
        this.o_description = o_description;
        this.o_points_bonus = o_points_bonus;
    }

    public int getO_id() {
        return o_id;
    }

    public String getO_nom() {
        return o_nom;
    }

    public void setO_nom(String o_nom) {
        this.o_nom = o_nom;
    }

    public String getO_description() {
        return o_description;
    }

    public void setO_description(String o_description) {
        this.o_description = o_description;
    }

    public int getO_points_bonus() {
        return o_points_bonus;
    }

    public void setO_points_bonus(int o_points_bonus) {
        this.o_points_bonus = o_points_bonus;
    }

    public ArrayList<Enigme> getLesEnigmes() {
        return lesEnigmes;
    }

    public void setLesEnigmes(ArrayList<Enigme> lesEnigmes) {this.lesEnigmes = lesEnigmes;
    }
}

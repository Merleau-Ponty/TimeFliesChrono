package com.lmp.timeflies.metier;

/**
 * Created by valou on 23/01/2018.
 */

public class Indice {

    private int i_id;
    private String i_libelle;
    private int i_penalite;
    private String i_type;
    private int e_id;

    public Indice(int i_id, String i_libelle, String i_type, int e_id){
        this.i_id = i_id;
        this.i_libelle = i_libelle;
        this.i_penalite = 0;
        this.i_type = i_type;
        this.e_id = e_id;
    }

    public int getI_id() {
        return i_id;
    }

    public String getI_libelle() {
        return i_libelle;
    }

    public void setI_libelle(String i_libelle) {
        this.i_libelle = i_libelle;
    }

    public int getI_penalite() {
        return i_penalite;
    }

    public void setI_penalite(int i_penalite) {
        this.i_penalite = i_penalite;
    }

    public String getI_type() {
        return i_type;
    }

    public void setI_type(String i_type) {
        this.i_type = i_type;
    }

    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
    }
}

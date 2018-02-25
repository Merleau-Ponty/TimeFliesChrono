package com.lmp.timeflies.metier;

/**
 * Created by valou on 26/01/2018.
 */

public class Avatar {

    private int a_id;
    private String a_libelle;
    private String a_nomfichier;

    public Avatar(int a_id, String a_libelle, String a_nomfichier){
        this.a_id = a_id;
        this.a_libelle = a_libelle;
        this.a_nomfichier = a_nomfichier;
    }

    public int getA_id() {
        return a_id;
    }

    public String getA_libelle() {
        return a_libelle;
    }

    public void setA_libelle(String a_libelle) {
        this.a_libelle = a_libelle;
    }

    public String getA_nomfichier() {
        return a_nomfichier;
    }

    public void setA_nomfichier(String a_nomfichier) {
        this.a_nomfichier = a_nomfichier;
    }
}

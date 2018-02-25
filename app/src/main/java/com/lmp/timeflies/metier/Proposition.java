package com.lmp.timeflies.metier;

/**
 * Created by valou on 23/01/2018.
 */

public class Proposition {

    private int pr_id;
    private int p_id;
    private int e_id;
    private String pr_texte;
    private boolean pr_valide = false;

    public Proposition(int pr_id, int p_id, int e_id, String pr_texte){
        this.pr_id = pr_id;
        this.p_id = p_id;
        this.e_id = e_id;
        this.pr_texte = pr_texte;
    }

    public int getPr_id() {
        return pr_id;
    }

    public String getPr_texte() {
        return pr_texte;
    }

    public void setPr_texte(String pr_texte) {
        this.pr_texte = pr_texte;
    }

    public boolean isPr_valide() {
        return pr_valide;
    }

    public void setPr_valide(boolean pr_valide) {
        this.pr_valide = pr_valide;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
    }
}

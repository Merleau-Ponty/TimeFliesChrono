package com.lmp.timeflies.metier;

/**
 * Created by valou on 23/01/2018.
 */

public class DemandeIntervention {

    private int di_id;
    private int p_id;
    private int e_id;
    private boolean di_click = false;

    public DemandeIntervention(int di_id, int p_id, int e_id){
        this.di_id = di_id;
        this.p_id = p_id;
        this.e_id = e_id;
    }

    public int getDi_id() {
        return di_id;
    }

    public void setDi_click(boolean di_click) {
        this.di_click = di_click;
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

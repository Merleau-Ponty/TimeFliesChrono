package com.lmp.timeflies.player;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by casti on 13/02/2018.
 */

public class Play_Objectifs extends ListActivity implements AdapterView.OnItemClickListener {

    private ArrayList<String> lesObjectifs = new ArrayList<String>();
    private ListView listV;
    //GestionBD sgbd;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_objectifs);
        //sgbd = new GestionBD(this);
        init_liste();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lesObjectifs);
        // listV = findViewById(R.id.listObj);
        // listV.setAdapter(adapter);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    protected void init_liste() {
        lesObjectifs.add("Mission # 1");
        lesObjectifs.add("Mission # 2");
        lesObjectifs.add("Mission # 3");
        //lesObjectifs = sgbd.getLesObjectifs();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast msg = null;
        msg.makeText(getApplicationContext(), "" + i, Toast.LENGTH_LONG).show();
        Intent lesEnigmes = new Intent(getApplicationContext(), Play_ListeEnigmes.class);
        lesEnigmes.putExtra("o_id", lesObjectifs.get(i));
        lesEnigmes.putExtra("o_nom", lesObjectifs.get(i));
        lesEnigmes.putExtra("o_description", lesObjectifs.get(i));
        startActivity(lesEnigmes);
    }

}
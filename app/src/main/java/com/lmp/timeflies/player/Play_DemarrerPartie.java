package com.lmp.timeflies.player;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by casti on 12/02/2018.
 */

public class Play_DemarrerPartie extends Activity {
    Button button1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_demarrer);

        button1 = (Button) findViewById(R.id.button1);
        button1.setEnabled(true);
        button1.setOnClickListener(new View.OnClickListener() {
            // button1.setOnClickListener(){
            @Override
            public void onClick(View v) {
                Intent objectifs = new Intent(getApplicationContext(), Play_Objectifs.class);
                startActivity(objectifs);
            }
        });
    }
}

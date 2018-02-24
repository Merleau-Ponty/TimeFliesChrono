package com.lmp.timeflies.player;
//imports du projet
import com.lmp.timeflies.metier.*;
import com.lmp.timeflies.technique.ImageAdapter;
//imports android
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;
//imports java
import java.util.ArrayList;

/**
 * Created by Utilisateur on 23/01/2018.
 */

public class Play_Inscription extends Activity {

    EditText nom;
    Button play;
    EditText num;
    ArrayList<Avatar> lesAvatar = new ArrayList<Avatar>();
    ArrayList<Equipe> lesEquipe = new ArrayList<Equipe>();

    int id_a = 0;
    long test = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_inscription);

        final GridView gridview = (GridView) findViewById(R.id.avatars);
        gridview.setAdapter(new ImageAdapter(this));
        gridview.setSelection(1);


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(Play_Inscription.this, "" + position,
                        Toast.LENGTH_SHORT).show();

                test = gridview.getItemIdAtPosition(position);
                //convertir test en int
                //id_a = Integer.parseInt(new Long(test).toString());
                id_a = (int) test;
            }
        });




        //ajouter le boutton jouer
        play = (Button) findViewById(R.id.play);
        //Edit text du nom de l'équipe
        nom = (EditText) findViewById(R.id.nom);
        final String nomEquipe = nom.getText().toString();
        //Edit Text du numéro de téléphone
        num = (EditText) findViewById(R.id.num);

        final String numeroTel = num.getText().toString();
        final int nombreEquipe = lesEquipe.size();

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //appelle de la fonction d'insertion de l'équipe
                Equipe uneEquipe = new Equipe (nombreEquipe + 1, nomEquipe, numeroTel, id_a);
                Intent laccueil = new Intent(getApplicationContext(), Play_DebutDePartie.class);
                laccueil.putExtra("inscrption","ok");
                startActivity(laccueil);
            }
        });


    }



}



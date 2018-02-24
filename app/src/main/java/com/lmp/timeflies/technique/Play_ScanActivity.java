package com.lmp.timeflies.technique;

import com.lmp.timeflies.*;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.lmp.timeflies.player.Play_Enigme;
import com.lmp.timeflies.player.R;

public class Play_ScanActivity extends AppCompatActivity {

    private Button buttonScan;
    private  Button buttonRetour;
    private TextView resultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_scan);
        buttonScan = (Button) findViewById(R.id.bouton_scan);
        buttonRetour = (Button) findViewById(R.id.buttonRetour);
        resultat =(TextView) findViewById(R.id.resultat_scan);
        final Activity activity = this;
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Scannez le QR Code !");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });
        buttonRetour.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent enigmeCliquee = new Intent(getApplicationContext(),Play_Enigme.class);
                String labExtra  = "id_enigme";
                String keyExtra = "test Enigme";//lesEnigmesObj.get(i);
                enigmeCliquee.putExtra(labExtra,keyExtra);
                startActivity(enigmeCliquee);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {

                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {

                resultat.setText(result.getContents());
                String res = (String) resultat.getText();
                if(res.startsWith("http")) {
                    Uri uri = Uri.parse(result.getContents());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            }

        }
    }
}

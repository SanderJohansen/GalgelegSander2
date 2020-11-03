package com.example.galgeleg_sander;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WinnerScreen extends AppCompatActivity {
    SharedPreferences prefs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winner_screen);

        Intent intent = getIntent();
        final String text = intent.getStringExtra(MainActivity.EXTRA_TEXT);
        final int number = intent.getIntExtra(MainActivity.EXTRA_NUMBER, 0);
        TextView ordetOgFejl = (TextView) findViewById(R.id.ordetOgFejl);

        ordetOgFejl.setText("Du skrev ordet  "+  text  +" hvor du mistede "  + number + " liv");

        //Preferencemanager til at gemme ordet og antallet af forsøg
        prefs = PreferenceManager.getDefaultSharedPreferences(this);


        Button button = (Button) findViewById(R.id.prøvIgenKnapVinder);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String gemTekst = "";
                prefs.edit().putString("editText", gemTekst).apply();
                spilIgen();
        }
    });




     }
        private void spilIgen(){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
    }


}

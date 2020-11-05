package com.example.galgeleg_sander;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class WinnerScreen extends AppCompatActivity {
    public static final String SHARED_PREFS = "Sharedprefs";
    public static final String SCORE = "score";
    SharedPreferences prefs;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winner_screen);

        Intent intent = getIntent();
        final String text = intent.getStringExtra(MainActivity.EXTRA_TEXT);
        final int number = intent.getIntExtra(MainActivity.EXTRA_NUMBER, 0);
        final TextView ordetOgFejl = (TextView) findViewById(R.id.ordetOgFejl);

        ordetOgFejl.setText("Du skrev ordet "+  text  +" og brugte "  + number + " forsøg");
        prefs = PreferenceManager.getDefaultSharedPreferences(this);





        Button button = (Button) findViewById(R.id.prøvIgenKnapVinder);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveDate(text,number);
        }
    });

        }


        private void spilIgen(){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);


    }

    private void saveDate(String ord, int forsøg){
        String gemtTekst = ord + " med " + forsøg;
        prefs.edit().putString("editText", gemtTekst).apply();


        Toast.makeText(this, "Data saved as: "  + gemtTekst, Toast.LENGTH_SHORT).show();



        spilIgen();
    }


}

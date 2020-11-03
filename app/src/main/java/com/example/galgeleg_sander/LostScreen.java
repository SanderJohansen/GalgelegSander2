package com.example.galgeleg_sander;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LostScreen extends AppCompatActivity {


    TextView ordetDerSkalGættes;
    Button prøvIgenKnap;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lost_screen);

        text = getIntent().getExtras().getString("Text");
        ordetDerSkalGættes = (TextView)findViewById(R.id.ordetDerSkalGættes);
        ordetDerSkalGættes.setText("ordet du gættede på var" + text +"" );

        prøvIgenKnap = (Button)findViewById(R.id.prøvIgenKnapTaber);
        prøvIgenKnap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spilIgen();
            }
        });
    }

    private void spilIgen() {
        Intent intent = new Intent(LostScreen.this,MainActivity.class);
        startActivity(intent);
    }
}


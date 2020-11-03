package com.example.galgeleg_sander;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LossScreen extends AppCompatActivity {


    TextView ordetDerSkalGættes;
    Button prøvIgenKnap;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loss_screen);

        text = getIntent().getExtras().getString("Text");
        ordetDerSkalGættes = (TextView)findViewById(R.id.ordetDerSkalGættes);
        ordetDerSkalGættes.setText(text);

        prøvIgenKnap = (Button)findViewById(R.id.button2);
        prøvIgenKnap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LossScreen.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}


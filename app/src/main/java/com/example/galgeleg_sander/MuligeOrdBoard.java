package com.example.galgeleg_sander;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class MuligeOrdBoard extends AppCompatActivity {


    private ArrayList<String> muligeOrd = new ArrayList<String>();
    private ListView scoreBoard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mulige_ord);


        //https://www.youtube.com/watch?v=Mja5YoL9Jak
        scoreBoard = (ListView) findViewById(R.id.scoreBoard);
        Button tilbageKnap = (Button) findViewById(R.id.tilbagetilhovedmenu);

        //fuckede op med at jeg ikke kunne referer til galgelogik, så vil bare vise jeg godt kan se hvordan man gør
        muligeOrd.add("bil");
        muligeOrd.add("computer");
        muligeOrd.add("programmering");
        muligeOrd.add("motorvej");
        muligeOrd.add("busrute");
        muligeOrd.add("gangsti");
        muligeOrd.add("skovsnegl");
        muligeOrd.add("solsort");
        muligeOrd.add("tyve");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, muligeOrd);

        scoreBoard.setAdapter(arrayAdapter);


        tilbageKnap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                backToGame();

            }


        });

    }

    private void backToGame() {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
}



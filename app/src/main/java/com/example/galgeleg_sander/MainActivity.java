package com.example.galgeleg_sander;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Random;




public class MainActivity extends AppCompatActivity {


    public static final String EXTRA_TEXT = "com.example.application.example.EXTRA_TEXT";
    public static final String EXTRA_NUMBER = "com.example.application.example.EXTRA_NUMBER";
    SharedPreferences prefs;



    private ImageView galgebillede;
    private TextView deGætteBogstaver;
    private TextView findEtBogstav;
    private TextView ordetDerSkalGættes;
    private TextView scoreBoard;
    private ArrayList<String> muligeOrd = new ArrayList<String>();
    private String ordet;
    private ArrayList<String> brugteBogstaver = new ArrayList<String>();
    private String synligtOrd;
    private int antalForkerteBogstaver = 0;
    private boolean sidsteBogstavVarKorrekt;
    private boolean spilletErVundet;
    private boolean spilletErTabt;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        galgebillede = findViewById(R.id.galge);
        deGætteBogstaver = findViewById(R.id.gættedeBogstaver);
        findEtBogstav = findViewById(R.id.findEtBogstav);
        scoreBoard = findViewById(R.id.scoreBoard);
        ordetDerSkalGættes =findViewById(R.id.ordetDerSkalGættes);
        Button bekæftKnap = (Button) findViewById(R.id.BekræftKnap);
        Button muligOrdKnap = (Button) findViewById(R.id.muligOrdKnap);


        prefs = PreferenceManager.getDefaultSharedPreferences(this);




        muligeOrd.add("bil");
        muligeOrd.add("computer");
        muligeOrd.add("programmering");
        muligeOrd.add("motorvej");
        muligeOrd.add("busrute");
        muligeOrd.add("gangsti");
        muligeOrd.add("skovsnegl");
        muligeOrd.add("solsort");
        muligeOrd.add("tyve");

        startNytSpil();
        loadDataAndPutItIntoScore();


        bekæftKnap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String input = findEtBogstav.getText().toString();
                gætBogstav(input);
                findEtBogstav.setText("");



            }
        });

        muligOrdKnap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                goToMuligeOrd();



            }
        });
    ;}




    public ArrayList<String> getBrugteBogstaver() {
        return brugteBogstaver;
    }

    public String getSynligtOrd() {
        return synligtOrd;
    }

    public String getOrdet() {
        return ordet;
    }

    public int getAntalForkerteBogstaver() {
        return antalForkerteBogstaver;
    }

    public boolean erSidsteBogstavKorrekt() {
        return sidsteBogstavVarKorrekt;
    }

    public boolean erSpilletVundet() {
        return spilletErVundet;
    }

    public boolean erSpilletTabt() {
        return spilletErTabt;
    }

    public boolean erSpilletSlut() {
        return spilletErTabt || spilletErVundet;
    }



        public void startNytSpil () {
            brugteBogstaver.clear();
            antalForkerteBogstaver = 0;
            spilletErVundet = false;
            spilletErTabt = false;
            if (muligeOrd.isEmpty())
                throw new IllegalStateException("Listen over mulige ord er tom!");
            ordet = muligeOrd.get(new Random().nextInt(muligeOrd.size()));
            System.out.println("Nyt spil - det skjulte ord er: " + ordet);

            opdaterSynligtOrd();
            logStatus();
            }



        private void opdaterBilledet(int antalForkerteBogstaver) {

        if (antalForkerteBogstaver >= 1) {
            int detOpdateredeBillede = getResources().getIdentifier("forkert" + antalForkerteBogstaver, "drawable",
                    getPackageName());
            galgebillede.setImageResource(detOpdateredeBillede);
        }
    }
    private void opdaterBrugteBogstaver(){
        StringBuilder DeGættedeBogstaverStringBuilder = new StringBuilder();
        for (String s : brugteBogstaver) {
            DeGættedeBogstaverStringBuilder.append(s);
            deGætteBogstaver.setText(DeGættedeBogstaverStringBuilder.toString());
        }

    }

        private void opdaterSynligtOrd () {
            synligtOrd = "";
            spilletErVundet = true;
            for (int n = 0; n < ordet.length(); n++) {
                String bogstav = ordet.substring(n, n + 1);
                if (brugteBogstaver.contains(bogstav)) {
                    synligtOrd = synligtOrd + bogstav;
                } else {
                    synligtOrd = synligtOrd + "*";
                    spilletErVundet = false;

                }
                ordetDerSkalGættes.setText(synligtOrd);
            }
        }



            public void gætBogstav (String bogstav) {

                if (bogstav.length() != 1) return;
                System.out.println("Der gættes på bogstavet: " + bogstav);
                if (brugteBogstaver.contains(bogstav)) return;
                if (spilletErVundet || spilletErTabt) return;

                brugteBogstaver.add(bogstav);

                if (ordet.contains(bogstav)) {
                    sidsteBogstavVarKorrekt = true;
                    System.out.println("Bogstavet var korrekt: " + bogstav);
                    opdaterSynligtOrd();
                    opdaterBrugteBogstaver();


                    //Function til at se om du har vundet, den er sygt funky, men jakobs metode buggede mig totalt.

                    if(!((synligtOrd).contains("*"))) {

                        Intent intent = new Intent(this, WinnerScreen.class);
                        intent.putExtra(EXTRA_TEXT, ordet);
                        intent.putExtra(EXTRA_NUMBER, antalForkerteBogstaver);

                        startActivity(intent);
                    }
                } else {
                    // Vi gættede på et bogstav der ikke var i ordet.
                    sidsteBogstavVarKorrekt = false;
                    System.out.println("Bogstavet var IKKE korrekt: " + bogstav);
                    antalForkerteBogstaver = antalForkerteBogstaver + 1;
                    opdaterBilledet(antalForkerteBogstaver);
                    opdaterBrugteBogstaver();

                    if (antalForkerteBogstaver >= 6) {
                        spilletErTabt = true;
                    //taber funktionen
                        if (spilletErTabt == true) {
                            String debrugteBogstaver = brugteBogstaver.toString();
                            Intent intent = new Intent(this, LostScreen.class);
                            intent.putExtra(EXTRA_TEXT, ordet);
                            intent.putExtra(EXTRA_TEXT, debrugteBogstaver);

                            startActivity(intent);
                        }
                    }
                    logStatus();
                }
            }
    private void goToMuligeOrd() {
        Intent intent = new Intent(this, MuligeOrdBoard.class);
        startActivity(intent);
    }



            public void loadDataAndPutItIntoScore(){
        //Koden til preferencemanager er taget fra Jakob
            //https://github.com/nordfalk/AndroidElementer/blob/master/app/src/main/java/lekt06_data/BenytPreferenceManager.java
         String gemtTekst = prefs.getString("editText", "Ingen gemt score fundet");
        scoreBoard.setText("Tidligere score: " + gemtTekst);


            }

        public void logStatus () {
            System.out.println("---------- ");
            System.out.println("- ordet (skult) = " + ordet);
            System.out.println("- synligtOrd = " + synligtOrd);
            System.out.println("- forkerteBogstaver = " + antalForkerteBogstaver);
            System.out.println("- brugeBogstaver = " + brugteBogstaver);
            if (spilletErTabt) System.out.println("- SPILLET ER TABT");
            if (spilletErVundet) System.out.println("- SPILLET ER VUNDET");
            System.out.println("---------- ");
        }

    }





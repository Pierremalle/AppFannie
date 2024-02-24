package com.example.appfannie;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

import tinydb.TinyDB;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tinyDB = new TinyDB(getBaseContext());
        if(!tinyDB.getBoolean("created")){
            PopulateSimpleDatabase();
        }

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Spinner MenuSpinner = findViewById(R.id.MenuSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.modes,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MenuSpinner.setAdapter(adapter);
        MenuSpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        Intent intent = new Intent();
        switch (pos){
            case 0: break;
            case 1: intent = new Intent(this, DrinkAndDiceActivity.class);finish();startActivity(intent); break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    private void PopulateSimpleDatabase(){

        ArrayList<String> listeQuestions = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Action)));
        tinyDB.putListString("Action", listeQuestions);
        listeQuestions = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Chiche)));
        tinyDB.putListString("Chiche", listeQuestions);
        listeQuestions = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Verite)));
        tinyDB.putListString("Vérité", listeQuestions);

        listeQuestions = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.GameModes)));
        tinyDB.putListString("GamesModes", listeQuestions);
    }
}
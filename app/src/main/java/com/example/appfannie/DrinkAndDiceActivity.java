package com.example.appfannie;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import tinydb.TinyDB;

public class DrinkAndDiceActivity extends AppCompatActivity {

    private TinyDB tinyDB;
    private Random random;
    private String mode;
    private String question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_and_dice);

        this.tinyDB = new TinyDB(getBaseContext());
        this.random = new Random();

        Log.d("info", String.valueOf(tinyDB.getListString("GamesModes")));

        ArrayList<String> listesModes = tinyDB.getListString("GamesModes");
        Log.d("info", String.valueOf(listesModes));

        Button questionButton = findViewById(R.id.NewQuestionButton);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView categoryTextView = findViewById(R.id.CategoryDisplayer);
        TextView questionTextView = findViewById(R.id.QuestionDisplayer);
        questionButton.setOnClickListener(v -> new Thread(()->{

            this.mode = listesModes.get(this.random.nextInt(listesModes.size()));
            ArrayList<String> listeQuestion = tinyDB.getListString(mode);

            this.question = listeQuestion.get(this.random.nextInt(listeQuestion.size()));

            runOnUiThread(()-> categoryTextView.setText(this.mode));
            runOnUiThread(()-> questionTextView.setText(this.question));
        }).start());
    }
}
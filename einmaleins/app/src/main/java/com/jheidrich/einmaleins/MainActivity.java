package com.jheidrich.einmaleins;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    ImageView answerImage;

    private TextView challengeText;
    private ImageView resultImage;
    private Button[] answers;
    private Button resetButton;
    private int selectedRow = 0;
    private int correctResult = 0;
    private int correctResultAt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answers = new Button[3];
        answers[0] = (Button) findViewById(R.id.answer1);
        answers[1] = (Button) findViewById(R.id.answer2);
        answers[2] = (Button) findViewById(R.id.answer3);

        resetButton = (Button) findViewById(R.id.nextChallenge) ;

        resultImage = (ImageView) findViewById(R.id.resultImage);
        challengeText = (TextView) findViewById(R.id.challengeText);

        selectedRow = Integer.parseInt(getIntent().getSerializableExtra("selectedRow").toString());
        Log.d("EinMalEins", String.valueOf(selectedRow) );

        setTitle(String.format("Die %der Reihe", selectedRow)); // d = decimal, f= float, s = string

        nextChallenge();

        answerImage = (ImageView) findViewById(R.id.resultImage);
        /*
        how to load image in image view
        imageView = (ImageView) findViewById(R.id.imageView2);

        imageView.setImageResource(R.mipmap.ic_launcher);*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.einmaleins_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_restart:
                Log.d("EinMalEins", "Reihe beendet");
                finish();
                return true;
            case  R.id.menu_item_settings:
                Toast toast = Toast.makeText(this, "Einstellungen", Toast.LENGTH_SHORT);
                toast.show();
                Log.d("EinMalEins", "Einstellungen gewählt");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void resetChallenge(View view) {
        for(Button answerButton : answers){
            answerButton.setVisibility(View.VISIBLE);
        }
        answerImage.setVisibility(View.INVISIBLE);
        resetButton.setVisibility(View.INVISIBLE);
        nextChallenge();
    }

    public void nextChallenge(){
        int operand = (int) (Math.random() * 10) + 1;
        correctResult = selectedRow * operand;
        challengeText.setText(String.format("%d * %d = ", operand, selectedRow));

        int ruse[] = new int[2];
        ruse[0] = generateRuse(correctResult, ruse);
        ruse[1] = generateRuse(correctResult, ruse);

        correctResultAt = (int)( Math.random() * 3);

        int currentRuse = 0;

        for(int position = 0; position < answers.length; position++) {
            if(position == correctResultAt) {
                answers[position].setText(String.valueOf(correctResult));
            } else {
                answers[position].setText(String.valueOf(ruse[currentRuse++]));
            }
        }

    }

    private int generateRuse(int correctResult, int ruse[]) {
        int tmpResult;
        do {
            tmpResult = correctResult + (int) (Math.random() * 4) +1;
        } while (tmpResult == correctResult || tmpResult == ruse[0]);

        return tmpResult;
    }

    public void answerChallenge(View view){
        int position = Integer.parseInt(view.getTag().toString());

        Toast toast;
        if(position == correctResultAt) {
            answerImage.setImageResource(R.mipmap.ic_correct_answer);
            toast = Toast.makeText(this, "Richtig", Toast.LENGTH_SHORT);
            toast.show();
            for(Button answerButton : answers){
                answerButton.setVisibility(View.INVISIBLE);
            }
            resetButton.setVisibility(View.VISIBLE);
        } else {
            answerImage.setImageResource(R.mipmap.ic_false_answer);
            toast = Toast.makeText(this,"Rechne lieber noch einmal nach!", Toast.LENGTH_SHORT);
            toast.show();
        }
        answerImage.setVisibility(View.VISIBLE);
    }
}

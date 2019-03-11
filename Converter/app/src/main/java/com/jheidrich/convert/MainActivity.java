package com.jheidrich.convert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {

    Button euroToSekButton;
    Button sekToEuroButton;
    Button cmToInchButton;
    Button inchToCmButton;

    EditText inputText;
    TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);

        euroToSekButton = (Button) findViewById(R.id.euroToSek);
        sekToEuroButton = (Button) findViewById(R.id.sekToEuro);
        cmToInchButton = (Button) findViewById(R.id.cmToInch);
        inchToCmButton = (Button) findViewById(R.id.inchToCm);

        inputText = (EditText) findViewById(R.id.inputText);
        outputText = (TextView) findViewById(R.id.outputText);

        /*sekToEuroButton.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast toast = Toast.makeText(MainActivity.this,"sekToEuroButton", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        ); */

        /*outputText.setVisibility(View.VISIBLE);
        outputText.setText("hier steht was");*/
    }

    public void convert(View view){

        String userInputString = inputText.getText().toString();

        /*if(userInputString.length() < 1){
            Toast warning = Toast.makeText(this, "Bitte geben Sie einen Wert zum Berechnen ein.", Toast.LENGTH_LONG);
            warning.show();
            return;
        }*/

        Double userInputValue;
        try{
            userInputValue = Double.parseDouble(userInputString);
        } catch (NumberFormatException e){
            Toast warning = Toast.makeText(this, R.string.invalid_number_input, Toast.LENGTH_LONG);
            // third Param = Toast Duration => Toast.LENGTH_LONG
            warning.show();
            return;
        }


        switch(view.getId()){
            case R.id.euroToSek:
                calculateEuroToSek(userInputValue);
                break;
            case R.id.sekToEuro:
                calculateSekToEuro(userInputValue);
                break;
            case R.id.cmToInch:
                calculateCmToInch(userInputValue);
                break;
            case R.id.inchToCm:
                calculateInchToCm(userInputValue);
                break;

        }
    }


    protected void calculateEuroToSek(double input){
        showResult(input / 0.095, "SEK");
    }

    protected void calculateSekToEuro(double input){
        showResult(input * 0.095, "€");
    }

    protected void calculateInchToCm(double input){
        showResult(input * 2.54, "CM");
    }

    protected void calculateCmToInch(double input){
        showResult(input / 2.54, "Zoll");
    }

    protected void showResult(Double result, String targetUnit){

        BigDecimal roundedResult = new BigDecimal(result);
        roundedResult = roundedResult.setScale(2, RoundingMode.HALF_UP);

        String resultString = getString(R.string.result) + " " + roundedResult +" "+ targetUnit;
        // R == R liefert nur die Ressource ID, getString sorgt dafür, dass der String hinter der ID verwendet wird.
        outputText.setVisibility(View.VISIBLE);
        outputText.setText(resultString);
    }

    public void reset(View view) {
        inputText.setText("");
        outputText.setText("");
        outputText.setVisibility(View.INVISIBLE);
    }


}

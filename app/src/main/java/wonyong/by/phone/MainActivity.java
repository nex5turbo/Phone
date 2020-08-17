package wonyong.by.phone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<Integer> numberArray = new ArrayList<Integer>();
    ArrayList<Button> buttonArray = new ArrayList<>();
    Map buttonValue = new HashMap<Button, Integer>();
    String numberText = "";
    Vibrator vibrator;
    Boolean trickCondition = false;

    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVibrator();
        initTextView();
        initList();
        initButton();
        initValue();
        initListener();
        textViewListener();
        textingButton();

    }

    private void initVibrator() {
        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
    }

    private void textViewListener() {
        textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                vibrator.vibrate(100);
                if(!trickCondition) {
                    trickCondition = true;
                    initTrick();
                }else{
                    trickCondition = false;
                    initListener();
                }
                return false;
            }
        });
    }

    private void initTrick(){
        numberText = "51230584";
        for (Button b : buttonArray) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    textingTextView();
                }
            });
        }
    }
    private void initTextView() {
        textView = findViewById(R.id.textView3);
    }

    private void initButton() {
        button0 = findViewById(R.id.button0);
        buttonArray.add(button0);
        button1 = findViewById(R.id.button1);
        buttonArray.add(button1);
        button2 = findViewById(R.id.button2);
        buttonArray.add(button2);
        button3 = findViewById(R.id.button3);
        buttonArray.add(button3);
        button4 = findViewById(R.id.button4);
        buttonArray.add(button4);
        button5 = findViewById(R.id.button5);
        buttonArray.add(button5);
        button6 = findViewById(R.id.button6);
        buttonArray.add(button6);
        button7 = findViewById(R.id.button7);
        buttonArray.add(button7);
        button8 = findViewById(R.id.button8);
        buttonArray.add(button8);
        button9 = findViewById(R.id.button9);
        buttonArray.add(button9);
    }

    private void initListener() {
        for(final Button b : buttonArray){
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    numberText = numberText + buttonValue.get(b).toString();
                    if(numberText.length() > 8){
                        numberText = numberText.substring(1, 9);
                    }
                    textingTextView();
                    shuffleList();
                    insertValue();
                    textingButton();
                }
            });
        }
    }

    private void initList() {
        for(int i = 0; i < 10; i++){
            numberArray.add(i);
        }
    }

    private void shuffleList(){
        long seed = System.nanoTime();
        Collections.shuffle(numberArray, new Random(seed));
    }

    private void initValue(){
        int i = 0;
        for(Button b : buttonArray) {
            buttonValue.put(b, i);
            i++;
        }
    }

    private void insertValue(){
        int i = 0;
        for(Button b : buttonArray){
            buttonValue.put(b,numberArray.get(i));
            i++;
        }
    }
    private void textingButton(){
        for(Button b : buttonArray){
            String text = buttonValue.get(b).toString();
            b.setText(text);
        }
    }
    private void textingTextView(){
        textView.setText(numberText);
    }
}
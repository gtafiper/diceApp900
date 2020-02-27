package com.example.littlediceapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;


import java.util.ArrayList;
import java.util.HashMap;


import java.util.Random;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    //ExspanListAdapter adapter;
    //ExpandableListView expandableListView;
    private Random random = new Random();
    int turn = 0;
    public ArrayList<Integer> listGroup = new ArrayList<>();
    HashMap<String, ArrayList<Integer>> listItem = new HashMap<>();
    TextView closeToTop;
    EditText numDice;
    public Button goToLogBt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonRoll = findViewById(R.id.btRoll);
        Button buttonAdd = findViewById(R.id.btAddDice);
        buttonAdd.setOnClickListener(this);
        buttonRoll.setOnClickListener(this);

        closeToTop = findViewById(R.id.closeToTp);
        numDice = findViewById(R.id.etNumOfDice);

        goToLogBt = findViewById(R.id.btLog);
        goToLogBt.setOnClickListener(this);

        //expandableListView = findViewById(R.id.exList);
        //adapter = new ExspanListAdapter(this,listGroup,listItem);
        //expandableListView.setAdapter(adapter);


    }

    public void addDice(){

    try {


        FlexboxLayout diceLayout = findViewById(R.id.llDiceImgContainer);
        diceLayout.removeAllViews();


        int dices = 0;
        String numOfDice = numDice.getText().toString();
        if (!"".equals(numDice)) {
            dices = Integer.parseInt(numOfDice);
        }


        for (int i = 0; i < dices; i++) {
            ImageView img = new ImageView(this);

            img.setImageResource(R.drawable.dice1);

            img.setLayoutParams(new android.view.ViewGroup.LayoutParams(100, 100));

            diceLayout.addView(img);
            closeToTop.setText("pick the number og dice you wont");

        }
    } catch (NumberFormatException e) {
        closeToTop.setTextColor(Color.RED);
        closeToTop.setText(" It must be a number between 1 and 9000 ");
    }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btAddDice:
                addDice();
                break;

            case R.id.btRoll:
                roll();
                break;

            case R.id.btLog:
                goToLog();
                break;

        }

    }

    public void goToLog(){
       Intent intent = new Intent(MainActivity.this, LogView.class);
        Bundle bundle = new Bundle();
        bundle.putIntegerArrayList("key", listGroup);

        intent.putExtras(bundle);
       startActivity(intent);

    }


    private void roll(){
        turn++;
        String round = "Turn " + turn ;

        ViewGroup diceLayout = findViewById(R.id.llDiceImgContainer);
        for(int i = 0; i<diceLayout.getChildCount(); i++){

            View nexChild = diceLayout.getChildAt(i);
            if(nexChild instanceof ImageView){
                int rando = random.nextInt(6) + 1;
                listGroup.add(rando);
                switch (rando){
                    case 1:
                        ((ImageView) nexChild).setImageResource(R.drawable.dice1);
                        break;
                    case 2:
                        ((ImageView) nexChild).setImageResource(R.drawable.dice2);
                        break;
                    case 3:
                        ((ImageView) nexChild).setImageResource(R.drawable.dice3);
                        break;
                    case 4:
                        ((ImageView) nexChild).setImageResource(R.drawable.dice4);
                        break;
                    case 5:
                        ((ImageView) nexChild).setImageResource(R.drawable.dice5);
                        break;
                    case 6:
                        ((ImageView) nexChild).setImageResource(R.drawable.dice6);
                }

            }

        }

        listItem.put(round, listGroup);

        //textView.setText(listItem.toString());
        Context context = getApplicationContext();

        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, listItem.toString(), duration);
        toast.show();
        //listItem.clear();
        //adapter.notifyDataSetChanged();

    }






}

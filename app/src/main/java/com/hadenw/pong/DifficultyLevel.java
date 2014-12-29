package com.hadenw.pong;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;


public class DifficultyLevel extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_level);
        final Button settingsButton = (Button) findViewById(R.id.button_Easy);
        settingsButton.setOnClickListener(this);
        final Button settingsButton1 = (Button) findViewById(R.id.button_medium);
        settingsButton1.setOnClickListener(this);
        final Button settingsButton2 = (Button) findViewById(R.id.button_Hard);
        settingsButton2.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_Easy:
                startActivity(new Intent(this, Easy.class));
                break;
            case R.id.button_medium:
                startActivity(new Intent(this, Medium.class));
                break;
            case R.id.button_Hard:
                startActivity(new Intent(this, Hard.class));
                break;
        }
    }
}
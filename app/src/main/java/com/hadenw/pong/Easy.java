package com.hadenw.pong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;


public class Easy extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy);
        new CountDownTimer(30000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                TextView time = (TextView) findViewById(R.id.time);
                time.setText(Integer.toString((int)(millisUntilFinished/1000)));
            }

            @Override
            public void onFinish() {
                startActivity(new Intent(Easy.this, DifficultyLevel.class));
            }
        }.start();
    }


}

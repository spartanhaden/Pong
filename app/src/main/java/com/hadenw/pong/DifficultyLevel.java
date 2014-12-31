package com.hadenw.pong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class DifficultyLevel extends Activity implements View.OnClickListener {
    public static float difficultyLevel;
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
		switch (v.getId()) {
			case R.id.button_Easy:
                difficultyLevel = 5f;
				startActivity(new Intent(this, Easy.class));
                finish();
				break;
			case R.id.button_medium:
				startActivity(new Intent(this, Easy.class));
                difficultyLevel = 10f;
                finish();
				break;
			case R.id.button_Hard:
				startActivity(new Intent(this, Easy.class));
                difficultyLevel = 15f;
                finish();
				break;
		}
	}
}
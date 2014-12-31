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

		final Button easyButton = (Button) findViewById(R.id.button_easy);
		final Button mediumButton = (Button) findViewById(R.id.button_medium);
		final Button hardButton = (Button) findViewById(R.id.button_hard);
		easyButton.setOnClickListener(this);
		mediumButton.setOnClickListener(this);
		hardButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		startActivity(new Intent(this, Game.class));
		switch (v.getId()) {
			case R.id.button_easy:
				difficultyLevel = 5f;
				break;
			case R.id.button_medium:
				difficultyLevel = 10f;
				break;
			case R.id.button_hard:
				difficultyLevel = 15f;
				break;
		}
		finish();
	}
}
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

		final Button easyButton = (Button) findViewById(R.id.button_easy);
		final Button mediumButton = (Button) findViewById(R.id.button_medium);
		final Button hardButton = (Button) findViewById(R.id.button_hard);

		easyButton.setOnClickListener(this);
		mediumButton.setOnClickListener(this);
		hardButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.button_easy:
				startActivity(new Intent(this, Easy.class));
				finish();
				break;
			case R.id.button_medium:
				startActivity(new Intent(this, Medium.class));
				finish();
				break;
			case R.id.button_hard:
				startActivity(new Intent(this, Hard.class));
				finish();
				break;
		}
	}
}
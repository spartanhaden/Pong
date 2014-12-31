package com.hadenw.pong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PostGameActivity extends Activity implements View.OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_game);
		final Button playAgainButton = (Button) findViewById(R.id.button_Play_Again);
		final Button shareButton = (Button) findViewById(R.id.button_Share);
		playAgainButton.setOnClickListener(this);
		shareButton.setOnClickListener(this);
		TextView textView = (TextView) findViewById(R.id.scoreText);
		textView.setText("Score: " + Integer.toString(GameView.pScore));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.button_Play_Again:
				GameView.pScore = 0;
				startActivity(new Intent(this, DifficultyLevel.class));
				finish();
				break;
			case R.id.button_Share:
				Intent sendIntent = new Intent();
				sendIntent.setAction(Intent.ACTION_SEND);
				sendIntent.putExtra(Intent.EXTRA_TEXT, "I just scored " + GameView.pScore +
						" in an awesome, new pong game! #tbt #pong #bringingSexyBack");
				sendIntent.setType("text/plain");
				startActivity(Intent.createChooser(sendIntent, ""));
		}
	}
}
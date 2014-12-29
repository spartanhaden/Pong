package com.hadenw.pong;

import android.app.Activity;
import android.os.Bundle;


public class SinglePlayer extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new BallView(this));
	}
}

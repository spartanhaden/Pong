package com.hadenw.pong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Haden Wasserbaech on 12/29/14.
 */
public class GameView extends View {
	private float opponentX = 50f;
	private float playerX = 50f;
	private float paddleWidth = 300f;
	private float paddleHeight = 100f;
	private RectF playerPaddle = new RectF(playerX - paddleWidth, getHeight() - paddleHeight - 100f, playerX + paddleWidth, getHeight() - 100f);
	private RectF opponentPaddle = new RectF(opponentX - paddleWidth, 100f, opponentX + paddleWidth, paddleHeight + 100f);
	private float ballX = 500f;
	private float ballY = 500f;
	private float ballXVelocity = 5f;
	private float ballYVelocity = 5f;
	private float ballRadius = 50f;
	private RectF ball = new RectF(ballX - ballRadius, ballY - ballRadius, ballX + ballRadius, ballY + ballRadius);
	private Paint ballColor = new Paint();
	private Paint playerColor = new Paint();
	private Paint opponentColor = new Paint();

	public GameView(Context context) {
		super(context);
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	protected void onDraw(Canvas canvas) {
		physics();
		ballColor.setARGB(255, (int) ballX % 255, (int) ballY % 255, 50);
		playerColor.setARGB(255, 255, 0, 0);
		opponentColor.setARGB(255, 255, 255, 0);
		canvas.drawOval(ball, ballColor);
		canvas.drawRect(playerPaddle, playerColor);
		canvas.drawRect(opponentPaddle, opponentColor);
		try {
			Thread.sleep(16);
		} catch (InterruptedException e) {
		}
		invalidate();
	}

	private void physics() {
		if (ballX + ballRadius >= getWidth())
			ballXVelocity = -ballXVelocity;
		if (ballX - ballRadius <= 0f)
			ballXVelocity = -ballXVelocity;
		if (ballY + ballRadius >= getHeight())
			ballYVelocity = -ballYVelocity;
		if (ballY - ballRadius <= 0f)
			ballYVelocity = -ballYVelocity;
		ballX += ballXVelocity;
		ballY += ballYVelocity;
		ball.set(ballX - ballRadius, ballY - ballRadius, ballX + ballRadius, ballY + ballRadius);
		playerPaddle.set(playerX - paddleWidth, getHeight() - paddleHeight - 100f, playerX + paddleWidth, getHeight() - 100f);
		opponentPaddle.set(opponentX - paddleWidth, 100f, opponentX + paddleWidth, paddleHeight + 100f);
		if (ballXVelocity > -30 && ballXVelocity < 30) {
			ballXVelocity *= 1.01f;
			ballYVelocity *= 1.01f;
		}
	}
}
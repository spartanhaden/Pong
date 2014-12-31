package com.hadenw.pong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Haden Wasserbaech on 12/29/14.
 */
public class GameView extends View {
    public static int pScore = 0;
    private final float paddleWidth = 200f;
    private final float paddleHeight = 50f;
    private float touchX = 0f;
    private float ballX = 250f;
    private float ballY = 500f;
    private float ballXVelocity = DifficultyLevel.difficultyLevel;
    private float ballYVelocity = DifficultyLevel.difficultyLevel;
    private float ballRadius = 35f;
    private float playerX = 0f;
    private float opponentX = 0f;
    private RectF ball = new RectF(ballX - ballRadius, ballY - ballRadius, ballX + ballRadius, ballY + ballRadius);
    private RectF playerPaddle = new RectF(playerX - paddleWidth, getHeight() - paddleHeight - 100f, playerX + paddleWidth, getHeight() - 100f);
    private RectF opponentPaddle = new RectF(opponentX - paddleWidth, 100f, opponentX + paddleWidth, paddleHeight + 100f);
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
        ballColor.setARGB(255, 0, 255, 50);
        playerColor.setARGB(255, 255, 0, 0);
        opponentColor.setARGB(255, 255, 255, 0);
        canvas.drawOval(ball, ballColor);
        canvas.drawRect(playerPaddle, playerColor);
        canvas.drawRect(opponentPaddle, opponentColor);
        invalidate();
    }

    private void physics() {
        ballX += ballXVelocity;
        ballY += ballYVelocity;
        if ((ball.left >= playerPaddle.left) && (ball.right <= playerPaddle.right) &&
                (ballY >= playerPaddle.top) && (ballY > playerPaddle.bottom))
            ballY = playerPaddle.top - ballRadius;

        ball.set(ballX - ballRadius, ballY - ballRadius, ballX + ballRadius, ballY + ballRadius);
        setPlayerPaddle();
        setOpponentPaddle();
        // Accelerates the ball until a max velocity is reached.
        if (ballXVelocity > -DifficultyLevel.difficultyLevel + 15 && ballXVelocity < DifficultyLevel.difficultyLevel + 15) {
            ballXVelocity *= 1.01f;
            ballYVelocity *= 1.01f;
        }
        getBallVelocity();
    }

    private void setPlayerPaddle() {
        if (touchX - paddleWidth/2 < 0) {
            playerPaddle.set(0, getHeight() - paddleHeight - 100f, paddleWidth, getHeight() - 100f);
        } else if (touchX + paddleWidth/2 > getWidth()) {
            playerPaddle.set(getWidth() - paddleWidth, getHeight() - paddleHeight - 100f,
                    getWidth(), getHeight() - 100f);
        } else
            playerPaddle.set(touchX - paddleWidth / 2, getHeight() - paddleHeight - 100f,
                    touchX + paddleWidth / 2, getHeight() - 100f);
    }

    private void setOpponentPaddle() {
        if (ball.left + paddleWidth >= getWidth())
            opponentPaddle.set(getWidth() - paddleWidth, 100f, getWidth(), paddleHeight + 100f);

        else
            opponentPaddle.set(ball.left, 100f, ball.left + paddleWidth, paddleHeight + 100f);
    }

    private void getBallVelocity() {

        if ((ball.left >= playerPaddle.left) && (ball.right <= playerPaddle.right) &&
                (ball.bottom >= playerPaddle.top) && (ball.bottom <= playerPaddle.top + ballYVelocity)) {
            ballXVelocity = (Math.random() > 0.5) ? ballXVelocity : -ballXVelocity;
            ballYVelocity = -ballYVelocity;
            pScore++;
        }
        if (((ball.left >= opponentPaddle.left) && (ball.right <= opponentPaddle.right)) && (ball.top <= opponentPaddle.bottom)) {
            ballXVelocity = (Math.random() > 0.5) ? ballXVelocity : -ballXVelocity;
            ballYVelocity = Math.abs(ballYVelocity);
        }
        if (ball.right >= getWidth())
            ballXVelocity = -ballXVelocity;
        if (ball.left <= 0f)
            ballXVelocity = -ballXVelocity;
        if (ball.top >= getHeight())
            ballYVelocity = -ballYVelocity;
        if (ball.bottom <= 0f)
            ballYVelocity = -ballYVelocity;
        if (ball.bottom > playerPaddle.bottom && (ball.left < playerPaddle.left
                || ball.right > playerPaddle.right)) {
            ballX = getWidth() / 2;
            ballY = getHeight() / 2;
            ballXVelocity = (Math.random() > 0.5) ? -5f : 5f;
            ballYVelocity = 10;
            try {
                pScore -= pScore < 2 ? 0 : 2;
                Thread.sleep(3000);
            } catch (InterruptedException ee) {
            }
        }
        if (ball.top < opponentPaddle.top) {
            ballX = getWidth() / 2;
            ballY = getHeight() / 2;
            ballXVelocity = 5f;
            ballYVelocity = 10;
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        final MotionEvent t = e;
                final float x = t.getRawX();
                touchX = x;
        return true;
    }
}
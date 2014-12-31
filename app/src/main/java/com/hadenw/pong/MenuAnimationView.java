package com.hadenw.pong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Robin Onsay on 12/31/2014.
 */
public class MenuAnimationView extends View {
    private float opponentX = 0f;
    private float playerX = 0f;
    private float paddleWidth = 200f;
    private float paddleHeight = 50f;
    public RectF playerPaddle = new RectF(playerX - paddleWidth, getHeight() - paddleHeight - 100f, playerX + paddleWidth, getHeight() - 100f);
    private RectF opponentPaddle = new RectF(opponentX - paddleWidth, 100f, opponentX + paddleWidth, paddleHeight + 100f);
    private float ballX = 250f;
    private float ballY = 500f;
    private float ballXVelocity = 5f;
    private float ballYVelocity = 5f;
    private float ballRadius = 35f;
    public RectF ball = new RectF(ballX - ballRadius, ballY - ballRadius, ballX + ballRadius, ballY + ballRadius);
    private Paint ballColor = new Paint();
    private Paint playerColor = new Paint();
    private Paint opponentColor = new Paint();


    public MenuAnimationView(Context context) {
        super(context);
    }

    public MenuAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MenuAnimationView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void onDraw(Canvas canvas) {
        physics();
        ballColor.setARGB(255, 255,  255, 50);
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
        if((ball.left>=playerPaddle.left)&&(ball.right<=playerPaddle.right)&&
                (ballY >= playerPaddle.top)&&(ballY>playerPaddle.bottom))
            ballY = playerPaddle.top-ballRadius;
        setBall();
        setPlayerPaddle();
        setOpponentPaddle();
        if (ballXVelocity > -20 && ballXVelocity < 20) {
            ballXVelocity *= 1.01f;
            ballYVelocity *= 1.01f;
        }
        getBallVelocity();
    }
    private void setBall(){
        ball.set(ballX - ballRadius, ballY - ballRadius, ballX + ballRadius, ballY + ballRadius);
    }
    private void setPlayerPaddle(){
        if(ball.left+paddleWidth>=getWidth())
            playerPaddle.set(getWidth()-paddleWidth, getHeight() - paddleHeight - 100f,
                    getWidth(), getHeight() - 100f);

        else
            playerPaddle.set(ball.left, getHeight() - paddleHeight - 100f, ball.left + paddleWidth, getHeight() - 100f);
    }
    private void setOpponentPaddle(){
        if(ball.left+paddleWidth>=getWidth())
            opponentPaddle.set(getWidth()-paddleWidth, 100f, getWidth(), paddleHeight + 100f);

        else
            opponentPaddle.set(ball.left, 100f, ball.left + paddleWidth, paddleHeight + 100f);
    }

    private void getBallVelocity(){

        if((ball.left>=playerPaddle.left)&&(ball.right<=playerPaddle.right)&&
                (ball.bottom >= playerPaddle.top)&&(ball.bottom<=playerPaddle.top+ballYVelocity)) {
            ballXVelocity = (Math.random()>0.5)?ballXVelocity:-ballXVelocity;
            ballYVelocity = -ballYVelocity;
        }
        if(((ball.left>=opponentPaddle.left)&&(ball.right<=opponentPaddle.right))&&(ball.top <= opponentPaddle.bottom)) {
            ballXVelocity = (Math.random()>0.5)?ballXVelocity:-ballXVelocity;
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
    }
}

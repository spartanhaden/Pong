package com.hadenw.pong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by Haden Wasserbaech on 12/29/14.
 */
public class BallView extends View {

	private float x = 500f;
	private float y = 500f;
	private float size = 50f;
	private RectF rectF = new RectF(x - size, y - size, x + size, y + size);
	private Paint paint = new Paint(Color.WHITE);

	public BallView(Context context) {
		super(context);
	}

	protected void onDraw(Canvas canvas) {
		rectF.set(x - size, y - size, x + size, y + size);
		canvas.drawOval(rectF, paint);
		x += 1f;
		y += 2f;
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
		}
		invalidate();
	}
}
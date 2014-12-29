package com.hadenw.pong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.View;

/**
 * Created by Haden Wasserbaech on 12/29/14.
 */
public class BallView extends View{
	private ShapeDrawable mDrawable;
	public BallView(Context context){
		super(context);
		int size = 50;

		mDrawable = new ShapeDrawable(new OvalShape());
		mDrawable.getPaint().setColor(0x00FFFF);
		mDrawable.setBounds(500,500,size,size);
	}

	protected void onDraw(Canvas canvas){
		mDrawable.draw(canvas);
	}
}
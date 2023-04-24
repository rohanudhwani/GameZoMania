package com.example.parseemailverification;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DotView extends View {

    private int size;
    private Paint paint;

    public DotView(Context context) {
        super(context);

        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.themeYellow));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(size , size , size / 2, paint);
    }
}
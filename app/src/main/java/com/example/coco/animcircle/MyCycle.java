package com.example.coco.animcircle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by coco on 2017/9/11.
 */

public class MyCycle extends View {

    private Paint paint, paint2;
    private int rWidth;
    private float radius;
    private int speed;

    public MyCycle(Context context) {
        super(context);
        init();
    }

    public MyCycle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCycle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.red));
        paint.setStrokeWidth(getResources().getDimensionPixelSize(R.dimen.width));
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);


        paint2 = new Paint();
        paint2.setColor(getResources().getColor(R.color.pink));
        paint2.setStrokeWidth(getResources().getDimensionPixelSize(R.dimen.width1));
        paint2.setStyle(Paint.Style.FILL_AND_STROKE);
        paint2.setAntiAlias(true);

        if (rWidth == 0) {
            radius = getResources().getDimensionPixelSize(R.dimen.radius);
        } else {
            radius = (rWidth - 2 * paint.getStrokeWidth()) / 2;
        }
        drawCycle();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(paint.getStrokeWidth()*0.5f+radius,paint.getStrokeWidth()*0.5f+radius, radius, paint);
        canvas.drawArc(0.5f*paint.getStrokeWidth(),
                0.5f*paint.getStrokeWidth(),
                2 * radius + paint.getStrokeWidth()*0.5f,
                2 * radius + paint.getStrokeWidth()*0.5f,
                0.0f, speed, true, paint2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.AT_MOST || MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) {
            int width = (int) (2 * (radius + paint.getStrokeWidth()));
            int height = (int) (2 * (radius + paint.getStrokeWidth()));
            setMeasuredDimension(width, height);
        } else {
            int size = MeasureSpec.getSize(widthMeasureSpec);
            rWidth = size;
            radius = (rWidth - 2 * paint.getStrokeWidth()) / 2;
            postInvalidate();
            int size1 = MeasureSpec.getSize(heightMeasureSpec);
            int wm = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
            int hm = MeasureSpec.makeMeasureSpec(size1, MeasureSpec.EXACTLY);
            super.onMeasure(wm, hm);
        }

    }

    public void drawCycle() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (speed >= 360) {
                        speed = 0;
                    }
                    postInvalidate();
                    SystemClock.sleep(100);
                    speed += 5;

                }
            }
        }).start();
    }
}

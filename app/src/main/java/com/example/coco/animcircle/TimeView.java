package com.example.coco.animcircle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by coco on 2017/9/12.
 */

public class TimeView extends View {
    private Context context;
    private Paint paint;
    private float mSecondDegree, mMinDegree, mHourDegree, mTotalSecond, minScaleLength, midScaleLength, maxScaleLength,
            textSize, secondPointLength, minPointLength, hourPointLength, centerPointSize, borderWidth, secondPointSize,
            minPointSize, hourPointSize, centerPointRadiu;
    private boolean isNight, isSecondGoSmooth, isDrawText;
    private int borderColor, minScaleColor, midScaleColor, maxScaleColor, textColor, secondPointColor, minPointColor,
            hourPointColor, centerPointColor, circleBackGround, parentSize, sizeMode, sleepTime, textHeight, textWidth;
    private String centerPointType;
    private Timer timer = new Timer();
    private float totalSecond;


    public TimeView(Context context) {
        super(context);
        this.context = context;
        initPainter();
    }


    public TimeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initPainter();
    }

    private void initPainter() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.black));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(getResources().getDimensionPixelSize(R.dimen.paintWidth));


        borderColor = getResources().getColor(R.color.black);
        circleBackGround = getResources().getColor(R.color.white);
        borderWidth = getResources().getDimensionPixelSize(R.dimen.borderWidth);
        minScaleColor = getResources().getColor(R.color.black);
        midScaleColor = getResources().getColor(R.color.black);
        maxScaleColor = getResources().getColor(R.color.black);
        minScaleLength = getResources().getDimensionPixelSize(R.dimen.minLength);
        midScaleLength = getResources().getDimensionPixelSize(R.dimen.midLength);
        maxScaleLength = getResources().getDimensionPixelSize(R.dimen.maxLength);
        textColor = getResources().getColor(R.color.black);
        textSize = getResources().getDimensionPixelSize(R.dimen.textSize);
        isDrawText = true;
        secondPointColor = getResources().getColor(R.color.red);
        minPointColor = getResources().getColor(R.color.black);
        hourPointColor = getResources().getColor(R.color.black);
        secondPointLength = getWidth() / 3 * 2 / 3;
        minPointLength = getWidth() / 3 / 2;
        hourPointLength = getWidth() / 3 / 3;
        secondPointSize = getResources().getDimensionPixelSize(R.dimen.secondPointSize);
        minPointSize = getResources().getDimensionPixelSize(R.dimen.minPointSize);
        hourPointSize = getResources().getDimensionPixelSize(R.dimen.hourPointSize);
        centerPointColor = getResources().getColor(R.color.black);
        centerPointSize = getResources().getDimensionPixelSize(R.dimen.hourPointSize);
        centerPointRadiu = getResources().getDimensionPixelSize(R.dimen.secondPointSize);
//        centerPointType=
        isSecondGoSmooth = false;
        if (isSecondGoSmooth) {
            sleepTime = 50;
        } else {
            sleepTime = 1000;
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measurWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureHeight(int heightMeasureSpec) {
        int result;
        int specSize = MeasureSpec.getSize(heightMeasureSpec);
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 300;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private int measurWidth(int widthMeasureSpec) {
        int result;
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 300;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //外圆
        paint.setColor(borderColor);
        paint.setStrokeWidth(borderWidth);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 3, paint);
        //背景
        paint.setColor(circleBackGround);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 3 - borderWidth / 2, paint);
        //圆心
        paint.setColor(centerPointColor);
        paint.setStrokeWidth(centerPointSize);
//        if (centerPointType.equals("rect")) {
            canvas.drawPoint(getWidth() / 2, getHeight() / 2, paint);
//        } else if (centerPointType.equals("circle")) {
//            paint.setStyle(Paint.Style.FILL);
//            canvas.drawCircle(getWidth() / 2, getHeight() / 2, centerPointRadiu, paint);
//        }
        //刻度
        canvas.save();
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.STROKE);
        for (int i = 0; i < 360; i++) {
            if (i % 30 == 0) {
                paint.setColor(maxScaleColor);
                canvas.drawLine(getWidth() / 2, getHeight() / 2 - (getWidth() / 3 - borderWidth / 2), getWidth() / 2,
                        getHeight() / 2 - (getWidth() / 3 - borderWidth / 2) + maxScaleLength, paint);

            } else if (i % 6 == 0) {
                paint.setColor(midScaleColor);
                canvas.drawLine(getWidth() / 2, getHeight() / 2 - (getWidth() / 3 - borderWidth / 2), getWidth() / 2,
                        getHeight() / 2 - (getWidth() / 3 - borderWidth / 2) + maxScaleLength, paint);

            } else {
                paint.setColor(midScaleColor);
                canvas.drawLine(getWidth() / 2, getHeight() / 2 - (getWidth() / 3 - borderWidth / 2), getWidth() / 2,
                        getHeight() / 2 - (getWidth() / 3 - borderWidth / 2) + maxScaleLength, paint);
            }
            canvas.rotate(1, getWidth() / 2, getHeight() / 2);
        }
        canvas.restore();


        if (isDrawText) {
            canvas.save();
            paint.setColor(textColor);
            paint.setStyle(Paint.Style.FILL);
            paint.setStrokeWidth(1);
            paint.setTextSize(textSize);
            canvas.translate(getWidth() / 2, getHeight() / 2);
            for (int i = 0; i < 12; i++) {
                if (i == 0) {
                    drawNum(canvas, i * 30, 12 + "", paint);
                } else {
                    drawNum(canvas, i * 30, i + "", paint);
                }
            }
            canvas.restore();
        }


        canvas.save();
        paint.setColor(hourPointColor);
        paint.setStrokeWidth(hourPointSize);
        canvas.rotate(mHourDegree, getWidth() / 2, getHeight() / 2);
        canvas.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2, getHeight() / 2 - hourPointLength, paint);
        canvas.restore();


        canvas.save();
        paint.setColor(minPointColor);
        paint.setStrokeWidth(minPointSize);
        canvas.rotate(mMinDegree, getWidth() / 2, getHeight() / 2);
        canvas.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2, getHeight() / 2 - minPointLength, paint);
        canvas.restore();


        canvas.save();
        paint.setColor(secondPointColor);
        paint.setStrokeWidth(secondPointSize);
        canvas.rotate(mSecondDegree, getWidth() / 2, getHeight() / 2);
        canvas.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2, getHeight() / 2 - secondPointLength, paint);
        canvas.restore();

    }

    private void drawNum(Canvas canvas, int degree, String text, Paint paint) {
        Rect textBounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), textBounds);
        canvas.rotate(degree);
        canvas.translate(0, 50 - getWidth() / 3);
        canvas.rotate(-degree);
        canvas.drawText(text, -textBounds.width() / 2, textBounds.height() / 2, paint);
        canvas.rotate(degree);
        canvas.translate(0, getWidth() / 3 - 50);
        canvas.rotate(-degree);
    }

    public void setTime(int hour, int min) {
        setTotalSecond(hour * 3600f + min * 60f);
    }

    public int getSecond() {
        return (int) (getTotalTimeSecond()-getHour()*3600-getMin()*60);
    }

    public void start() {
        timer.schedule(task, 0, sleepTime);
    }

    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            if (mSecondDegree == 360) {
                mSecondDegree = 0;
            }
            if (mMinDegree == 360) {
                mMinDegree = 0;
            }
            if (mHourDegree == 360) {
                mHourDegree = 0;
                isNight = !isNight;
            }
            mSecondDegree = mSecondDegree + 0.3f * sleepTime / 50;
            mMinDegree = mMinDegree + 0.005f * sleepTime / 50;
            mHourDegree = mHourDegree + 1.0f * sleepTime / 50 / 2400.0f;
            postInvalidate();

        }
    };

    public void setTotalSecond(float totalSecond) {
        if (totalSecond >= 24 * 3600) {
            this.totalSecond = totalSecond - 24 * 3600;
        } else {
            this.totalSecond = totalSecond;
        }
        int hour = (int) (totalSecond / 3600);
        int min = (int) ((totalSecond - hour * 3600) * 1.0 / 60);
        int second = (int) (totalSecond - hour * 3600 - min * 60);
        setTime(hour, min, second);
    }

    public void setTime(int hour, int min, int second) {
        if (hour >= 24 || hour < 0 || min >= 60 || min < 0 || second >= 60 || second < 0) {
            Toast.makeText(getContext(), "时间不合法", Toast.LENGTH_SHORT).show();
            return;
        }
        if (hour >= 12) {
            isNight = true;
            mHourDegree = (hour + min * 1.0f / 60f + second * 1.0f / 3600f - 12) * 30f;
        } else {
            isNight = false;
            mHourDegree = (hour + min * 1.0f / 60f + second * 1.0f / 3600f) * 30f;
        }
        mMinDegree = (min + second * 1.0f / 60f) * 6f;
        mSecondDegree = second * 6f;
        invalidate();
    }
    public float getTotalTimeSecond(){
        if (isNight){
            totalSecond=mHourDegree*120+12*3600;
            return totalSecond;
        }else {
            totalSecond=mHourDegree*120;
            return totalSecond;
        }
    }
    public int getHour(){
        return (int) (getTotalTimeSecond()/3600);
    }
    public int getMin(){
        return (int) ((getTotalTimeSecond()-getHour()*3600)/60);
    }

}

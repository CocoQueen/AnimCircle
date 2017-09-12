package com.example.coco.animcircle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MyCycle cycle;
    private int count;
    private TimeView timeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //自定义动态画圆
//        cycle = (MyCycle) findViewById(R.id.mCycle);
//        cycle = new MyCycle(this);
//        cycle.drawCycle();

        //圆形时钟
        timeView = (TimeView) findViewById(R.id.timeView);
//        timeView.setTime(9,35,43);
        timeView.start();


    }


}

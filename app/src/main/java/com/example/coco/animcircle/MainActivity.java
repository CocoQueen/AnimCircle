package com.example.coco.animcircle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MyCycle cycle;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        cycle = (MyCycle) findViewById(R.id.mCycle);
        cycle = new MyCycle(this);
        cycle.drawCycle();

    }


}

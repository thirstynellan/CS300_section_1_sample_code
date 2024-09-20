package edu.byuh.cis.c300.helloworld.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import edu.byuh.cis.c300.helloworld.sprites.Duck;

public class HikaruView extends View {

    private Paint grace;
    //private Bitmap duckImg;
    private Duck kanaan;
    private boolean initialized;

    public HikaruView(Context k) {
        super(k);
        initialized = false;
        grace = new Paint();
        grace.setColor(Color.rgb(255,200,200));
        grace.setStyle(Paint.Style.STROKE);
        grace.setTextSize(100);
        grace.setTextAlign(Paint.Align.CENTER);
        //duckImg = BitmapFactory.decodeResource(getResources(), R.drawable.duck);
    }

    @Override
    public void onDraw(Canvas c) {
        c.drawColor(Color.GREEN);
        float w = getWidth();
        float h = getHeight();
        float rectLeft = w * 0.25f;
        float rectRight = w * 0.75f;
        float rectTop = h * 0.2f;
        float rectBottom = h * 0.4f;
        if (!initialized) {
            kanaan = new Duck(getResources(), w);
            kanaan.setLocation(w*0.4f, h*0.6f);
            grace.setStrokeWidth(w * 0.01f);
            initialized = true;
        }
        c.drawRect(rectLeft, rectTop, rectRight, rectBottom, grace);
        c.drawLine(w*0.5f, h*0.3f, w*0.8f, h*0.9f, grace);
        c.drawText("Hello CS300", w*0.5f, h*0.5f, grace);
        //c.drawBitmap(duckImg, w*0.4f, h*0.6f, grace);
        kanaan.draw(c);
    }
}

package edu.byuh.cis.c300.helloworld;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class HikaruView extends View {

    private Paint grace;

    public HikaruView(Context k) {
        super(k);
        grace = new Paint();
        grace.setColor(Color.rgb(255,200,200));
        grace.setStyle(Paint.Style.STROKE);
        grace.setTextSize(100);
        grace.setTextAlign(Paint.Align.CENTER);
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
        grace.setStrokeWidth(w * 0.01f);
        c.drawRect(rectLeft, rectTop, rectRight, rectBottom, grace);
        c.drawLine(w*0.5f, h*0.3f, w*0.8f, h*0.9f, grace);
        c.drawText("Hello CS300", w*0.5f, h*0.5f, grace);
    }
}

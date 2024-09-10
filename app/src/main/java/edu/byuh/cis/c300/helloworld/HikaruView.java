package edu.byuh.cis.c300.helloworld;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

public class HikaruView extends View {

    public HikaruView(Context k) {
        super(k);
    }

    @Override
    public void onDraw(Canvas c) {
        c.drawColor(Color.GREEN);
    }
}

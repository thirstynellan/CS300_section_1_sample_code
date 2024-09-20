package edu.byuh.cis.c300.helloworld.sprites;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

import edu.byuh.cis.c300.helloworld.R;

public class Duck {
    private Bitmap img;
    private RectF bounds;

    public Duck(Resources res, float w) {
        int duckSize = (int)(w*0.25);
        img = BitmapFactory.decodeResource(res, R.drawable.duck);
        bounds = new RectF(0, 0, duckSize, duckSize);
        img = Bitmap.createScaledBitmap(img, duckSize, duckSize, true);
    }

    public void setLocation(float x, float y) {
        bounds.offsetTo(x,y);
    }

    public void draw(Canvas c) {
        c.drawBitmap(img, bounds.left, bounds.right, null);
    }
}

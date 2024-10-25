package edu.byuh.cis.c300.helloworld.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.byuh.cis.c300.helloworld.Observer;
import edu.byuh.cis.c300.helloworld.Timer;
import edu.byuh.cis.c300.helloworld.sprites.Duck;


public class HikaruView extends View implements Observer {

    private Paint grace;
    //private Duck kanaan;
    private List<Duck> flock;
    private boolean initialized;
    private Toast toasty;
    private Timer tim;

    public HikaruView(Context k) {
        super(k);
        initialized = false;
        flock = new ArrayList<>();
        grace = new Paint();
        //grace.setColor(Color.rgb(255,200,200));
        grace.setColor(Color.BLUE);
        grace.setStyle(Paint.Style.STROKE);
        grace.setTextSize(100);
        grace.setTextAlign(Paint.Align.CENTER);
    }

    private void createDucks(int n) {
        for (int j=0; j<n; j++) {
            Duck kanaan = new Duck(getResources(), getWidth());
            float duckX = (float) (getWidth() * 0.75 * Math.random());
            float duckY = (float) (getHeight() * 0.75 * Math.random());
            kanaan.setLocation(duckX, duckY);
            tim.subscribe(kanaan);
            flock.add(kanaan);
        }
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
            tim = new Timer();
            createDucks(70);
            grace.setStrokeWidth(w * 0.01f);
            tim.subscribe(this);
            initialized = true;
        }
        c.drawRect(rectLeft, rectTop, rectRight, rectBottom, grace);
        c.drawLine(w*0.5f, h*0.3f, w*0.8f, h*0.9f, grace);
        c.drawText("Hello CS300", w*0.5f, h*0.5f, grace);
        //c.drawBitmap(duckImg, w*0.4f, h*0.6f, grace);
        for (var d : flock) {
            d.draw(c, grace);
        }
            /*toasty = Toast.makeText(getContext(),
                    "CS300 is my favorite class",
                    Toast.LENGTH_LONG);
            toasty.show();*/
    }

    @Override
    public boolean onTouchEvent(MotionEvent m) {
        if (m.getAction() == MotionEvent.ACTION_DOWN) {
            float x = m.getX();
            float y = m.getY();
            List<Duck> doomed = new ArrayList<>();
            for (var d : flock) {
                if (d.contains(x, y)) {
                    doomed.add(d);
                    tim.unsubscribe(d);
                }
            }
            flock.removeAll(doomed);
            if (flock.isEmpty()) {
                AlertDialog.Builder ab = new AlertDialog.Builder(getContext());
                ab.setTitle("Congratulations!");
                ab.setMessage("You have successfully cleared the sector of the duck invasion! The federation is in need of a captain for a similar mission. Do you want to volunteer?");
                ab.setCancelable(false);
                ab.setPositiveButton("Yes, play again.", (d, i) -> createDucks(70));
                ab.setNegativeButton("No, I quit.", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface d, int i) {
                        //gross, but effective.
                        ((Activity)getContext()).finish();
                    }
                });
                AlertDialog box = ab.create();
                box.show();
            }
        }
        //true means "we handled the event. It's done now."
        //false means "pass the event on to the next object in the CoR
        return true;
    }

    @Override
    public void update() {
        invalidate();
    }
}















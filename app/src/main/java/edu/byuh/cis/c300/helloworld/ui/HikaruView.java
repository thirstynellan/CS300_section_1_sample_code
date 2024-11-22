package edu.byuh.cis.c300.helloworld.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import edu.byuh.cis.c300.helloworld.Observer;
import edu.byuh.cis.c300.helloworld.Prefs;
import edu.byuh.cis.c300.helloworld.R;
import edu.byuh.cis.c300.helloworld.Timer;
import edu.byuh.cis.c300.helloworld.sprites.Duck;


public class HikaruView extends AppCompatImageView implements Observer {

    private Paint grace;
    private List<Duck> flock;
    private boolean initialized;
    private Toast toasty;
    private Timer tim;
    private MediaPlayer music;
    private boolean duckDir;

    public HikaruView(Context k, boolean dd) {
        super(k);
        initialized = false;
        duckDir = dd;
        flock = new ArrayList<>();
        grace = new Paint();
        //grace.setColor(Color.rgb(255,200,200));
        grace.setColor(Color.BLUE);
        grace.setStyle(Paint.Style.STROKE);
        grace.setTextSize(100);
        grace.setTextAlign(Paint.Align.CENTER);
        music = MediaPlayer.create(getContext(), R.raw.zhaytee_microcomposer_1);
        music.setLooping(true);
        if (Prefs.getMusicPref(k)) {
            music.start();
        }
        setImageResource(R.drawable.bkgd2);
        setScaleType(ScaleType.FIT_XY);
    }

    public void pauseMusic() {
        if (Prefs.getMusicPref(getContext())) {
            music.pause();
        }
    }

    public void resumeMusic() {
        if (Prefs.getMusicPref(getContext())) {
            music.start();
        }
    }

    private void createDucks(int n) {
        final int duckSpeed = Prefs.getSpeedPref(getContext());
        for (int j=0; j<n; j++) {
            Duck kanaan = new Duck(getResources(), getWidth(), duckDir);
            float duckX = (float) (getWidth() * 0.75 * Math.random());
            float duckY = (float) (getHeight() * 0.75 * Math.random());
            kanaan.setLocation(duckX, duckY);
            kanaan.setDanceSpeed(duckSpeed);
            tim.subscribe(kanaan);
            flock.add(kanaan);
        }
    }

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);
        //c.drawColor(Color.GREEN);
        float w = getWidth();
        float h = getHeight();
        float rectLeft = w * 0.25f;
        float rectRight = w * 0.75f;
        float rectTop = h * 0.2f;
        float rectBottom = h * 0.4f;
        if (!initialized) {
            tim = new Timer();
            createDucks(10);
            grace.setStrokeWidth(w * 0.01f);
            tim.subscribe(this);
            initialized = true;
        }
        //c.drawRect(rectLeft, rectTop, rectRight, rectBottom, grace);
        //c.drawLine(w*0.5f, h*0.3f, w*0.8f, h*0.9f, grace);
        String greeting = getResources().getString(R.string.aloha);
        c.drawText(greeting, w*0.5f, h*0.5f, grace);
//        class Japeth implements Consumer<Duck> {
//            @Override
//            public void accept(Duck d) {
//                d.draw(c, grace);
//            }
//        }
//        var j = new Japeth();
        flock.forEach(d -> d.draw(c, grace));
        //for (var d : flock) {
        //    d.draw(c, grace);
        //}
            toasty = Toast.makeText(getContext(),
                    R.string.app_name,
                    Toast.LENGTH_LONG);
            toasty.show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent m) {
        if (m.getAction() == MotionEvent.ACTION_DOWN) {
            float x = m.getX();
            float y = m.getY();
//            class Andre implements Predicate<Duck> {
//
//                @Override
//                public boolean test(Duck d) {
//                    return d.contains(x,y);
//                }
//            }
//            var a = new Andre();
            flock.removeIf(d -> d.contains(x,y));
            flock.stream().filter(d -> d.contains(x,y)).forEach(d -> tim.unsubscribe(d));
            //flock.stream().filter(d -> d.contains(x,y)).collect(Collectors.toCollection());
//            List<Duck> doomed = new ArrayList<>();
//            for (var d : flock) {
//                if (d.contains(x, y)) {
//                    doomed.add(d);
//                    tim.unsubscribe(d);
//                }
//            }
//            flock.removeAll(doomed);
            if (flock.isEmpty()) {
                AlertDialog.Builder ab = new AlertDialog.Builder(getContext());
                ab.setTitle(R.string.endgame_title)
                  .setMessage(R.string.endgame_message)
                  .setCancelable(false)
                  .setPositiveButton(R.string.play_again, (d, i) -> createDucks(10))
                  .setNegativeButton(R.string.quit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface d, int i) {
                        //gross, but effective.
                        ((Activity)getContext()).finish();
                    }
                });
                //AlertDialog box = ab.create();
                //box.show();
                ab.create().show(); // method chaining
            }
        }
        //true means "we handled the event. It's done now."
        //false means "pass the event on to the next object in the CoR
        return true;
    }

    @Override
    public void update() {
        if (Math.random() < 0.1) {
            post(() -> createDucks(1));
        }
        invalidate();
    }
}















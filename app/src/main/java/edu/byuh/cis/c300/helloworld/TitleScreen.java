package edu.byuh.cis.c300.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class TitleScreen extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        iv = new ImageView(this);
        iv.setImageResource(R.drawable.duck_splash);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        setContentView(iv);
    }

    @Override
    public boolean onTouchEvent(MotionEvent m) {
        if (m.getAction() == MotionEvent.ACTION_DOWN) {
            float w = iv.getWidth();
            float h = iv.getHeight();
            float x = m.getX();
            float y = m.getY();
            if (y < h / 3) {
                if (x < w / 2) {
                    //open the "about box"
                    AlertDialog.Builder ab = new AlertDialog.Builder(this);
                    ab.setTitle("About Duck World 3000")
                            .setMessage("TODO something inspiring here")
                            .setCancelable(true)
                            .setNeutralButton("OK", null);
                    AlertDialog box = ab.create();
                    box.show();

                } else {
                    //TODO open the "settings"
                    Intent japeth = new Intent(this, Prefs.class);
                    startActivity(japeth);
                }
            } else if (y > 2 * h / 3) {
                //launch main activity
                Intent rosa = new Intent(this, MainActivity.class);
                if (x < w/2) {
                    //TODO right-facing ducks
                    rosa.putExtra("DUCK_DIR", false);

                } else {
                    //TODO left-facing ducks
                    rosa.putExtra("DUCK_DIR", true);
                }
                startActivity(rosa);
                finish();
            }
        }
        return true;
    }
}

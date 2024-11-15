package edu.byuh.cis.c300.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

import edu.byuh.cis.c300.helloworld.ui.HikaruView;

public class MainActivity extends AppCompatActivity {

    private HikaruView hv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent kanaan = getIntent();
        boolean duckDir = kanaan.getBooleanExtra("DUCK_DIR", true);
        hv = new HikaruView(this, duckDir);
        setContentView(hv);
    }

    @Override
    public void onPause() {
        super.onPause();
        //pause the soundtrack
        hv.pauseMusic();
    }

    @Override
    public void onResume() {
        super.onResume();
        //continue the soundtrack
        hv.resumeMusic();
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent m) {
//        if (m.getAction() == MotionEvent.ACTION_DOWN) {
//            Log.d("CS300", "Hello from the Activity!!!");
//        }
//        return true;
//    }
}
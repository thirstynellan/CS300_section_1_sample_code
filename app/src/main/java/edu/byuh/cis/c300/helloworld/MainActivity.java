package edu.byuh.cis.c300.helloworld;

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
        hv = new HikaruView(this);
        setContentView(hv);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent m) {
//        if (m.getAction() == MotionEvent.ACTION_DOWN) {
//            Log.d("CS300", "Hello from the Activity!!!");
//        }
//        return true;
//    }
}
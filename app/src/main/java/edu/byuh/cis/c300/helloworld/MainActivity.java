package edu.byuh.cis.c300.helloworld;

import android.os.Bundle;
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
}
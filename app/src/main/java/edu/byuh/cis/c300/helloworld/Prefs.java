package edu.byuh.cis.c300.helloworld;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;

public class Prefs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle the back button on the toolbar
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static boolean getMusicPref(Context c) {
        return PreferenceManager.getDefaultSharedPreferences(c).getBoolean("MUSIC_PREF", false);
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle b, String s) {
            Context context = getPreferenceManager().getContext();
            PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(context);

            //TODO add preference widgets here
            SwitchPreference music = new SwitchPreference(context);
            music.setTitle("Music");
            music.setSummaryOn("Background music will play");
            music.setSummaryOff("Background music will NOT play");
            music.setDefaultValue(false);
            music.setKey("MUSIC_PREF");
            screen.addPreference(music);

            setPreferenceScreen(screen);
        }
    }
}
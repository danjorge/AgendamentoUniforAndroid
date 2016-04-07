package br.com.danielsouza.ssa.database;

import android.app.Application;
import android.content.ContextWrapper;

import com.pixplicity.easyprefs.library.Prefs;

import br.com.danielsouza.ssa.activity.LoginActivity;

/**
 * Created by Daniel Jorge on 05/04/2016.
 */
public class PrefsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        new Prefs.Builder()
                .setContext(getApplicationContext())
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();

    }
}

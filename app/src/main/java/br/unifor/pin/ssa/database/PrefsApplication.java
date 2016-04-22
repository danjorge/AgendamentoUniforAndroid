package br.unifor.pin.ssa.database;

import android.app.Application;
import android.content.ContextWrapper;

import com.pixplicity.easyprefs.library.Prefs;

/**
 * Classe responsavel por instanciar o framework EasyPreferences dentro da aplicacao
 * @author Daniel Jorge
 * Created by Daniel Jorge on 05/04/2016.
 */
public class PrefsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * Instancia do framework
         */
        new Prefs.Builder()
                .setContext(getApplicationContext())
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();

    }
}

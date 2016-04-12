package fr.gerdevstudio.zoomobile;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by ger on 12/04/2016.
 */
public class ZooApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //including stetho library
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}

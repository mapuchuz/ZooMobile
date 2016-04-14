package fr.gerdevstudio.zoomobile;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

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

        Intent intent = new Intent(this, GetAllAnimalsService.class);
        startService(intent);
        final boolean b = bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                GetAllAnimalsService service = (GetAllAnimalsService) iBinder;
                Toast.makeText(
                        getApplicationContext()
                        , "retour webservoce: " + service.resultFromApiCall
                        , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        }, BIND_AUTO_CREATE);
    }
}


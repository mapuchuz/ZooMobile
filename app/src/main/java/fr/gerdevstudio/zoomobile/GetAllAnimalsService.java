package fr.gerdevstudio.zoomobile;

import android.app.DownloadManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetAllAnimalsService extends Service {

    OkHttpClient client = new OkHttpClient();
    public String resultFromApiCall = "";
    private final IBinder mBinder = new Binder(){
        GetAllAnimalsService getService()
        {
            return GetAllAnimalsService.this;
        };
    };
    public GetAllAnimalsService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        final Request request = new Request.Builder()
                .url("localhost:8080/ZooBuild/rest/animal/1")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                resultFromApiCall = response.body().string();
            }
        });

      return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}

package com.subliminals_central.subliminalscentral.subliminalscentral.Notifications;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.subliminals_central.subliminalscentral.subliminalscentral.MainActivity;
import com.subliminals_central.subliminalscentral.subliminalscentral.Models.EventsModel;
import com.subliminals_central.subliminalscentral.subliminalscentral.R;
import com.subliminals_central.subliminalscentral.subliminalscentral.UserActions.UserData;
import com.subliminals_central.subliminalscentral.subliminalscentral.YouTube.AsyncGetChannelVideos;
import com.subliminals_central.subliminalscentral.subliminalscentral.YouTube.AsyncVideoGet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;


public class MyNotifyService extends IntentService {

    private static long LastUpdateTime = 0;
    private static int lastId;

    public MyNotifyService(String name) {
        super(name);
      //  LastUpdateTime = new Date().getTime();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        long tempTime = new Date().getTime();
        if((tempTime - LastUpdateTime) == 1000*60*30 || LastUpdateTime == 0){

            SharedPreferences prefs = getApplicationContext().getSharedPreferences(
                    getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
           // editor.putInt("points", current);
          //  editor.commit();

            lastId = prefs.getInt("lastId", 0);

            String myUrl = "http://gamedevapi.tioo.ru/api/index.php?api=";

            Request request = new Request.Builder()
                    .url(myUrl+"notify&id="+lastId)
                    .build();

            OkHttpClient client = new OkHttpClient();
            Response response = null;
            String toReturn = "";
            try {
                response = client.newCall(request).execute();
               toReturn = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String code = "";
            String lastId = "";
            String title = "";
            String description = "";
            try {
                JSONObject json = new JSONObject(toReturn);

                code = json.getString("code");
                lastId = json.getString("id");
                title = json.getString("title");
                description = json.getString("description");

            } catch(JSONException |RuntimeException ex){
                ex.printStackTrace();
            }

            if(code.equalsIgnoreCase("200")){
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(this)
                                .setSmallIcon(R.drawable.ic_magnify)
                                .setContentTitle(title)
                                .setContentText(description);
// Creates an explicit intent for an Activity in your app
                Intent resultIntent = new Intent(this, MainActivity.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
// Adds the back stack for the Intent (but not the Intent itself)
                stackBuilder.addParentStack(MainActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder.setContentIntent(resultPendingIntent);
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
                mNotificationManager.notify(0, mBuilder.build());

                int tempId = Integer.parseInt(lastId);
                 editor.putInt("lastId", tempId);
                 editor.commit();
            }
            LastUpdateTime = tempTime;
        }
        this.startService(intent);
    }
}

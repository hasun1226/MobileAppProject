package team19.notes4u.pollHelpers;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import org.json.JSONException;
import org.json.JSONObject;
import android.R;

import java.io.IOException;
import java.util.List;

import team19.notes4u.DB.Wrapper;

/**
 * Created by Kyle on 2016-12-04.
 */

public class PollAlarmManager extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        //connect to server..
        String user = "1";
        Bundle extras = intent.getExtras();
        if (extras != null) {
            user = extras.getString("user");
        }
        String urlString = urlString = "notifications/new_notifications/" + user;;

        Wrapper wrapper = new Wrapper(urlString);
        List<JSONObject> jsonObjects = wrapper.getJsonObjects();
        for (JSONObject jObj: jsonObjects) {
            try {

                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(context)
                                .setSmallIcon(R.drawable.stat_notify_chat)
                                .setContentTitle("Notes4u")
                                .setContentText((String) jObj.get("message"));

            } catch (JSONException e){
                e.printStackTrace();
            }


        }
        if(jsonObjects.size() > 0){

        }
    }
}

package team19.notes4u.pollHelpers;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import org.json.JSONException;
import org.json.JSONObject;
import android.R;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import team19.notes4u.DB.Wrapper;

/**
 * Created by Kyle on 2016-12-04.
 */

public class PollAlarmManager extends BroadcastReceiver {
    private String user_name;
    private String user_id;
    private Context context;

    public void onReceive(Context context, Intent intent) {
        user_name = intent.getExtras().getString("username");
        user_id = intent.getExtras().getString("user");
        this.context = context;
//        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//
//        String urlString = urlString = "notifications/new_notifications/" + user_id;
//        System.out.println(urlString);
//
//        Wrapper wrapper = new Wrapper(urlString);
//        List<JSONObject> noti_list = wrapper.getJsonObjects();
//        System.out.println(noti_list.toString());
//        for (JSONObject jObj: noti_list) {
//            System.out.println("Shouldn't print");
//            try {
//                System.out.println("HERE");
//                // Populate notifications and store them to the user's device
//                // Notifications goes off
//
//                NotificationCompat.Builder mBuilder =
//                                 new NotificationCompat.Builder(context)
//                                .setSmallIcon(R.drawable.stat_notify_chat)
//                                .setContentTitle("Notes4u")
//                                .setContentText((String) jObj.get("message"));
//                nm.notify(0, mBuilder.build());
//
//
//            } catch (JSONException e){
//                System.out.println("BYE");
//                e.printStackTrace();
//            }
//
//
//        }
//        System.out.println("Task done");
//        if(noti_list.size() > 0){
//
//        }
        new PollNotifList().execute();
    }

    private class PollNotifList extends AsyncTask<Object, Object, Void> {

        @Override
        protected Void doInBackground(Object... params) {
            NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            String urlString = urlString = "notifications/new_notifications/" + user_id;
            System.out.println(urlString);

            Wrapper wrapper = new Wrapper(urlString);
            List<JSONObject> noti_list = wrapper.getJsonObjects();
            System.out.println(noti_list.toString());
            int i = 0;
            for (JSONObject jObj : noti_list) {
                try {
                    // Populate notifications and store them to the user's device
                    // Notifications goes off

                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(context)
                                    .setSmallIcon(R.drawable.stat_notify_chat)
                                    .setContentTitle("Notes4u")
                                    .setContentText((String) jObj.get("message"));
                    nm.notify(i, mBuilder.build());
                    i++;

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }
    }
}

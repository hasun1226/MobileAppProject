package team19.notes4u.pollHelpers;

import android.app.Service;
import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by Kyle on 2016-12-04.
 */

public class PollService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class PollBinder extends Binder {
        PollService getService() {
            return PollService.this;
        }
    }

}

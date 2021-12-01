package ch.zli.aal.buyit.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class StoreService extends Service {
    public StoreService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
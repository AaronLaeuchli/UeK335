package ch.zli.aal.buyit.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class StoreService extends Service {
    public StoreService() {
    }

    private final IBinder iBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        public StoreService getService(){
            return StoreService.this;
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }
}
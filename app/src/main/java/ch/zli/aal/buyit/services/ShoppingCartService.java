package ch.zli.aal.buyit.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class ShoppingCartService extends Service {
    public ShoppingCartService() {
    }

    private final IBinder iBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        public ShoppingCartService getService(){
            return ShoppingCartService.this;
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }
}
package ch.zli.aal.buyit.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class ProductService extends Service {
    public ProductService() {
    }
    private final IBinder iBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        public ProductService getService(){
            return ProductService.this;
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }
}
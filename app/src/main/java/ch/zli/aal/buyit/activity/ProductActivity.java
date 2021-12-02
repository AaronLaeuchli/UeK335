package ch.zli.aal.buyit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import ch.zli.aal.buyit.R;
import ch.zli.aal.buyit.model.Product;

public class ProductActivity extends AppCompatActivity {

    public static final String PRODUCT = "PRODUCT";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        ImageView iv = findViewById(R.id.imageView);
        TextView et = findViewById(R.id.productName);

        Bundle bundle = getIntent().getExtras();
        Product product = (Product) bundle.get(PRODUCT);

        try {
            if (!product.getPath().isEmpty()) {
                iv.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(product.getPath())));
                et.setText(product.getProductName());
            }
            else {
                et.setText(product.getProductName() + "(no image found)");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
    private final ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            ProductService.LocalBinder binder = (ProductService.LocalBinder) service;
            productService = binder.getService();
        }
        @Override
        public void onServiceDisconnected(ComponentName arg0) {

        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, ProductService.class);
        bindService(intent, connection, this.BIND_AUTO_CREATE);
    }


    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
    }

 */


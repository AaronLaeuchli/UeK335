package ch.zli.aal.buyit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import ch.zli.aal.buyit.R;
import ch.zli.aal.buyit.model.Product;
import ch.zli.aal.buyit.model.Store;
import ch.zli.aal.buyit.services.ProductService;

public class ProductActivity extends AppCompatActivity {

    public static final String STORE_LIST = "STORE_LIST";
    private SharedPreferences mPref;
    private List<Store> mStoreList;
    //ProductService productService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Button btnAddToList = (Button)findViewById(R.id.btnAddStoreToList);
        EditText editProduct = (EditText) findViewById(R.id.editStore);
        Button btnPicture = (Button) findViewById(R.id.btnPicture);

        mPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        Gson gson = new Gson();
        String json = mPref.getString(STORE_LIST, gson.toJson(new ArrayList<Store>()));
        mStoreList = gson.fromJson(json, new TypeToken<List<Store>>(){}.getType());

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                mStoreList.stream().map(Store::getStoreName).collect(Collectors.toList()));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        btnAddToList.setOnClickListener(v -> {
            Product product = new Product(editProduct.getText().toString());
            mStoreList.stream()
                    .filter(store -> store.getStoreName().equals(spinner.getSelectedItem().toString()))
                    .findAny()
                    .get()
                    .getProducts()
                    .add(product);
            SharedPreferences.Editor prefsEditor = mPref.edit();
            prefsEditor.putString(STORE_LIST, gson.toJson(mStoreList));
            prefsEditor.apply();
            Intent intent = new Intent (this, ShoppingCartActivity.class);
            startActivity(intent);
        });


/*
        btnPicture.setOnClickListener(v -> {
            public void add(View view) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST);
            }

            @SuppressLint("SimpleDateFormat")
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                if (requestCode == CAMERA_REQUEST) {
                    SimpleDateFormat formatter = new SimpleDateFormat(FORMAT);
                    Date date = new Date();
                    String path = MediaStore.Images.Media.insertImage(
                            getContentResolver(),
                            (Bitmap) data.getExtras().get(DATA),
                            "Progress - " + formatter.format(date),
                            "Progress picture for SetTracker");
                    Progression progression = new Progression(path, date);
                    mProgressions.add(progression);
                    save();
                    display(progression);
                }
            }
        });
    }
*/}

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

}
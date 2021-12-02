package ch.zli.aal.buyit.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.permissionx.guolindev.PermissionX;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import ch.zli.aal.buyit.R;
import ch.zli.aal.buyit.model.Product;
import ch.zli.aal.buyit.model.Store;


public class ProductFormActivity extends AppCompatActivity {

    public static final String STORE_LIST = "STORE_LIST";
    public static final String PRODUCT_NAME = "PRODUCT_NAME";
    public static final String DATA = "data";
    public static final int CAMERA_REQUEST = 1;
    private SharedPreferences mPref;
    private List<Store> mStoreList;
    private String mPath = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_form);

        Button btnAddToList = findViewById(R.id.btnAddStoreToList);
        EditText editProduct = findViewById(R.id.editStore);
        Button btnPicture = findViewById(R.id.btnPicture);

        mPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        Gson gson = new Gson();
        String json = mPref.getString(STORE_LIST, gson.toJson(new ArrayList<Store>()));
        mStoreList = gson.fromJson(json, new TypeToken<List<Store>>() {
        }.getType());
        editProduct.setText(mPref.getString(PRODUCT_NAME, ""));

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                mStoreList.stream().map(Store::getStoreName).collect(Collectors.toList()));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        btnAddToList.setOnClickListener(v -> {
            Product product = new Product(editProduct.getText().toString(), mPath);
            mStoreList.stream()
                    .filter(store -> store.getStoreName().equals(spinner.getSelectedItem().toString()))
                    .findAny()
                    .get()
                    .getProducts()
                    .add(product);
            SharedPreferences.Editor prefsEditor = mPref.edit();
            prefsEditor.putString(STORE_LIST, gson.toJson(mStoreList));
            prefsEditor.putString(PRODUCT_NAME, "");
            prefsEditor.apply();
            Intent intent = new Intent(this, ShoppingCartActivity.class);
            startActivity(intent);
        });

        btnPicture.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            String txt = editProduct.getText().toString();
            SharedPreferences.Editor prefsEditor = mPref.edit();
            prefsEditor.putString(PRODUCT_NAME, txt);
            prefsEditor.apply();
            startActivityForResult(intent, CAMERA_REQUEST);
        });
    }

    @SuppressLint("SimpleDateFormat")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST) {
            mPath = MediaStore.Images.Media.insertImage(
                    getContentResolver(),
                    (Bitmap) data.getExtras().get(DATA),
                    "Product Picture",
                    "Picture of a product from BuyIt");
            try {
                ((ImageView) findViewById(R.id.productImageView)).setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(mPath)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onStop () {
        SharedPreferences.Editor prefsEditor = mPref.edit();
        prefsEditor.putString(PRODUCT_NAME, "");
        prefsEditor.apply();
        super.onStop();
    }

}
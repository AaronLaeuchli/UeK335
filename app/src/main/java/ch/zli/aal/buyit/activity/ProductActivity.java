package ch.zli.aal.buyit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ch.zli.aal.buyit.R;
import ch.zli.aal.buyit.model.Product;
import ch.zli.aal.buyit.model.Store;

public class ProductActivity extends AppCompatActivity {

    public static final String STORE_LIST = "STORE_LIST";
    private SharedPreferences mPref;
    private List<Store> mStoreList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Button btnAddToList = (Button)findViewById(R.id.btnAddStoreToList);
        EditText editProduct = (EditText) findViewById(R.id.editStore);
        // Button btnPicture = (Button) findViewById(R.id.btnPicture);

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
    }
}
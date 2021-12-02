package ch.zli.aal.buyit.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import ch.zli.aal.buyit.ProductRecyclerViewAdapter;
import ch.zli.aal.buyit.R;
import ch.zli.aal.buyit.StoreRecyclerViewAdapter;
import ch.zli.aal.buyit.model.Product;
import ch.zli.aal.buyit.model.Store;

public class StoreActivity extends AppCompatActivity implements StoreRecyclerViewAdapter.ItemClickListener{
    StoreRecyclerViewAdapter adapter;

    SharedPreferences preferences;
    public static final String STOREPREF = "STOREPREF" ;

    List<Store> storeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        Button btnAddStoreToList = (Button) findViewById(R.id.btnAddStoreToList);
        EditText txStoreName = findViewById(R.id.editStore);

        preferences = getSharedPreferences(STOREPREF, Context.MODE_PRIVATE);

        storeList = new ArrayList<>();

        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(storeList);
        prefsEditor.putString("storeList", json);
        prefsEditor.apply();

        btnAddStoreToList.setOnClickListener(v -> {
            storeList.add(new Store(txStoreName.getText().toString()));




        });



        if(storeList != null){
            RecyclerView recyclerView = findViewById(R.id.rvStores);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new StoreRecyclerViewAdapter(this, storeList);
            adapter.setClickListener((StoreRecyclerViewAdapter.ItemClickListener) this);
            recyclerView.setAdapter(adapter);
        }




    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
package ch.zli.aal.buyit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ch.zli.aal.buyit.R;
import ch.zli.aal.buyit.adapter.ProductListAdapter;
import ch.zli.aal.buyit.adapter.StoreListAdapter;
import ch.zli.aal.buyit.model.Product;
import ch.zli.aal.buyit.model.Store;
import ch.zli.aal.buyit.services.ProductService;
import ch.zli.aal.buyit.services.ShoppingCartService;

public class ShoppingCartActivity extends AppCompatActivity {

    public static final String STORE_LIST = "STORE_LIST";
    private SharedPreferences mPref;
    private List<Store> mStoreList;
    private ProductListAdapter mAdapter;
    private List<Product> showProducts;

    //private ShoppingCartService shoppingCartService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        ImageButton btnAddProduct = (ImageButton) findViewById(R.id.btnAddProduct);
        TabLayout tl = findViewById(R.id.tabLayout);

        mPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        Gson gson = new Gson();
        String json = mPref.getString(STORE_LIST, gson.toJson(new ArrayList<Store>()));
        mStoreList = gson.fromJson(json, new TypeToken<List<Store>>() {
        }.getType());

        if (!mStoreList.isEmpty()) {
            showProducts = mStoreList.get(0).getProducts();
        }
        else {
            showProducts = new ArrayList<>();
        }

        mAdapter = new ProductListAdapter(this, showProducts);
        ListView lv = findViewById(R.id.productListView);
        lv.setAdapter(mAdapter);

        for (Store store : mStoreList) {
            tl.addTab(tl.newTab().setText(store.getStoreName()).setTag(store));
        }

        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                showProducts.clear();
                Store store = (Store) tab.getTag();
                showProducts.addAll(store.getProducts());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        btnAddProduct.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
            startActivity(intent);
        });
    }



/*
    private final ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            ShoppingCartService.LocalBinder binder = (ShoppingCartService.LocalBinder) service;
            shoppingCartService = binder.getService();
        }
        @Override
        public void onServiceDisconnected(ComponentName arg0) {

        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, ShoppingCartService.class);
        bindService(intent, connection, this.BIND_AUTO_CREATE);
    }


    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
    }

 */
}
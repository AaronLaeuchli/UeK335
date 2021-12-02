package ch.zli.aal.buyit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class ShoppingCartActivity extends AppCompatActivity {

    public static final String STORE_LIST = "STORE_LIST";
    private SharedPreferences mPref;
    private List<Store> mStoreList;
    private ProductListAdapter mAdapter;
    private List<Product> showProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        ImageButton btnAddProduct = (ImageButton) findViewById(R.id.btnAddProduct);
        //ImageButton btnAddPicture = (ImageButton) findViewById(R.id.btnAddPicture);
        TabLayout tl = findViewById(R.id.tabLayout);

        mPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        Gson gson = new Gson();
        String json = mPref.getString(STORE_LIST, gson.toJson(new ArrayList<Store>()));
        mStoreList = gson.fromJson(json, new TypeToken<List<Store>>() {
        }.getType());

        for (Store store : mStoreList) {
            String storeName = store.getStoreName();
            tl.addTab(tl.newTab().setText(storeName).setTag(store));
        }

        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Store store = (Store) tab.getTag();
                showProducts = store.getProducts();
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        mAdapter = new ProductListAdapter(this, new ArrayList<>());
        ListView lv = findViewById(R.id.productListView);
        lv.setAdapter(mAdapter);
        Objects.requireNonNull(tl.getTabAt(0)).select();

        btnAddProduct.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
            startActivity(intent);
        });
    }
}
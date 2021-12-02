package ch.zli.aal.buyit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import ch.zli.aal.buyit.R;
import ch.zli.aal.buyit.adapter.StoreListAdapter;
import ch.zli.aal.buyit.model.Store;

public class StoreActivity extends AppCompatActivity {

    public static final String STORE_LIST = "STORE_LIST";
    private SharedPreferences mPref;
    private List<Store> mStoreList;
    private StoreListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        Button btnAddStoreToList = (Button) findViewById(R.id.btnAddStoreToList);

        mPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        Gson gson = new Gson();
        String json = mPref.getString(STORE_LIST, gson.toJson(new ArrayList<Store>()));
        mStoreList = gson.fromJson(json, new TypeToken<List<Store>>(){}.getType());

        mAdapter = new StoreListAdapter(this, mStoreList);
        ListView lv = findViewById(R.id.storeListView);
        lv.setAdapter(mAdapter);

        btnAddStoreToList.setOnClickListener(v -> {
            EditText txStoreName = findViewById(R.id.editStore);
            mStoreList.add(new Store(txStoreName.getText().toString(), new ArrayList<>()));
            SharedPreferences.Editor prefsEditor = mPref.edit();
            prefsEditor.putString(STORE_LIST, gson.toJson(mStoreList));
            prefsEditor.apply();
            mAdapter.notifyDataSetChanged();
        });
    }


}
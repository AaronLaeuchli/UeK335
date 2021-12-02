package ch.zli.aal.buyit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hudomju.swipe.SwipeToDismissTouchListener;
import com.hudomju.swipe.adapter.ListViewAdapter;

import java.util.ArrayList;
import java.util.List;

import ch.zli.aal.buyit.MainActivity;
import ch.zli.aal.buyit.R;
import ch.zli.aal.buyit.adapter.StoreListAdapter;
import ch.zli.aal.buyit.model.Store;

public class StoreActivity extends AppCompatActivity {

    public static final String STORE_LIST = "STORE_LIST";
    private SharedPreferences mPref;
    private List<Store> mStoreList;
    private StoreListAdapter mAdapter;

    private SwipeMenuListView mListView;
    private ArrayList<String> mArrayList=new ArrayList<>();
    private ListDataAdapter mListDataAdapter;

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


        //Mit dieser Methode sollte auf jedem Store ein swipe nach links ausgeführt werden können (wie in MyFitnessPal) funktioniet aber iwi nicht.
        //initControls();


    }


    private void initControls() {
        mListView= (SwipeMenuListView)findViewById(R.id.storeListView);
        mListView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        for(Store store : mStoreList){
            mArrayList.add(store.getStoreName());
        }

        mListDataAdapter=new ListDataAdapter();

        mListView.setAdapter(mListDataAdapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem goodItem = new SwipeMenuItem(
                        getApplicationContext());
                goodItem.setBackground(new ColorDrawable(Color.rgb(0x30, 0xB1,
                        0xF5)));
                goodItem.setWidth(dp2px(90));
                //Set Icon
                //goodItem.setIcon(R.drawable.ic_action_good);
                menu.addMenuItem(goodItem);

                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                deleteItem.setWidth(dp2px(90));
                // set a icon
                //deleteItem.setIcon(R.drawable.ic_action_discard);
                menu.addMenuItem(deleteItem);
            }
        };

// set creator

        mListView.setMenuCreator(creator);

        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {

                switch (index) {
                    case 0:
                        Toast.makeText(StoreActivity.this,"Like button press",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        mArrayList.remove(position);
                        mListDataAdapter.notifyDataSetChanged();
                        Toast.makeText(StoreActivity.this,"Item deleted",Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

//mListView

        mListView.setOnMenuStateChangeListener(new SwipeMenuListView.OnMenuStateChangeListener() {
            @Override
            public void onMenuOpen(int position) {

            }

            @Override
            public void onMenuClose(int position) {

            }

        });

        mListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {
            @Override
            public void onSwipeStart(int position) {

            }

            @Override
            public void onSwipeEnd(int position) {

            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    class ListDataAdapter extends BaseAdapter {

        ViewHolder holder;
        @Override
        public int getCount() {
            return mArrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override

        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView==null){

                holder=new ViewHolder();

                convertView=getLayoutInflater().inflate(R.layout.list_item,null);

                holder.mTextview=(TextView)convertView.findViewById(R.id.textView);

                convertView.setTag(holder);

            }else {

                holder= (ViewHolder) convertView.getTag();

            }

            holder.mTextview.setText(mArrayList.get(position));

            return convertView;

        }

        class ViewHolder {

            TextView mTextview;

        }

    }

    private int dp2px(int dp) {

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,

                getResources().getDisplayMetrics());

    }

}
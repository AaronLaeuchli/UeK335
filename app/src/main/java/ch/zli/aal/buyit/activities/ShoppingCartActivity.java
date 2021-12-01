package ch.zli.aal.buyit.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ch.zli.aal.buyit.MyRecyclerViewAdapter;
import ch.zli.aal.buyit.R;
import ch.zli.aal.buyit.model.Product;

public class ShoppingCartActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {
    MyRecyclerViewAdapter adapter;

    private ArrayList<Product> productList = new ArrayList<>();
    private Product tmpProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        ImageButton btnAddProduct = (ImageButton)findViewById(R.id.btnAddProduct);
        ImageButton btnAddPicture = (ImageButton)findViewById(R.id.btnAddPicture);

        Intent i = getIntent();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            tmpProduct = (Product) i.getParcelableExtra("product");
            productList.add(tmpProduct);
        }

        /*
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            tmpProduct = (Product) bundle.getSerializable("product");

        }
        *
         */

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, productList);
        adapter.setClickListener((MyRecyclerViewAdapter.ItemClickListener) this);
        recyclerView.setAdapter(adapter);







        btnAddProduct.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
            startActivity(intent);
        });



    }



    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();


    }
}
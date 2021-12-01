package ch.zli.aal.buyit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import ch.zli.aal.buyit.activities.ShoppingCartActivity;
import ch.zli.aal.buyit.activities.StoreActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton btnShopppingCart = (ImageButton) findViewById(R.id.btnShoppingCart);
        ImageButton btnStores = (ImageButton) findViewById(R.id.btnStores);

        btnShopppingCart.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ShoppingCartActivity.class);

            startActivity(intent);
        });

        btnStores.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), StoreActivity.class);
            startActivity(intent);
        });

    }
}
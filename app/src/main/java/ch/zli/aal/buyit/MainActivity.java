package ch.zli.aal.buyit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import ch.zli.aal.buyit.activity.ShoppingCartActivity;
import ch.zli.aal.buyit.activity.StoreActivity;

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
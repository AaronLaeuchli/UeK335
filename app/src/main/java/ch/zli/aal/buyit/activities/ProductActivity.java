package ch.zli.aal.buyit.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ch.zli.aal.buyit.R;
import ch.zli.aal.buyit.model.Product;
import ch.zli.aal.buyit.model.Store;

public class ProductActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ArrayList<String> storeList;
    private Product selectedPrduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Button btnAddToList = (Button)findViewById(R.id.btnAddStoreToList);
        Button btnPicture = (Button) findViewById(R.id.btnPicture);
        EditText editProduct = (EditText) findViewById(R.id.editStore);

        Intent i = getIntent();
        storeList = i.getStringArrayListExtra("stores");
        List<Store> stores = new ArrayList<Store>();
        for (String s:storeList
             ) {
            System.out.println(s);
            stores.add(new Store(s));
        }


        ArrayAdapter<Store> dataAdapter = new ArrayAdapter<Store>(this, android.R.layout.simple_spinner_item, stores);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);


        btnAddToList.setOnClickListener(v -> {
            Product product = createProduct((Store) spinner.getSelectedItem(), editProduct.getText().toString());
            Intent intent = new Intent (this, ShoppingCartActivity.class);
                intent.putExtra("product", product);
            startActivity(intent);
        });

        btnPicture.setOnClickListener(v -> {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        });


    }


    private Product createProduct(Store selectedStore, String productName){
        return new Product(productName, selectedStore);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
}
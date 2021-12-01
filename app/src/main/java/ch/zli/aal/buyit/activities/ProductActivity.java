package ch.zli.aal.buyit.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ch.zli.aal.buyit.R;

public class ProductActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String selectedStore;
    private String selectedPrduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Button btnAddToList = (Button)findViewById(R.id.btnAddToList);
        Button btnPicture = (Button) findViewById(R.id.btnPicture);
        EditText editProduct = (EditText) findViewById(R.id.editProduct);

        List<String> categories = new ArrayList<String>();
        categories.add("Item 1");
        categories.add("Item 2");
        categories.add("Item 3");
        categories.add("Item 4");
        categories.add("Item 5");
        categories.add("Item 6");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        btnAddToList.setOnClickListener(v -> {
            Intent intent = new Intent (this, ShoppingCartActivity.class);
            selectedStore = (String) spinner.getSelectedItem();
            selectedPrduct = (String) editProduct.getText().toString();
                intent.putExtra("store", selectedStore);
                intent.putExtra("product", selectedStore);


            startActivity(intent);

        });

        btnPicture.setOnClickListener(v -> {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        });


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
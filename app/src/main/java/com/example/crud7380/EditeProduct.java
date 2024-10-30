package com.example.crud7380;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditeProduct extends AppCompatActivity {

    EditText editTextUpdateName, editTextUpdateDescription, editTextUpdatePrice;

    Button buttonSaveUpdate;
    DatabaseReference databaseProducts;

    String productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edite_product);

        editTextUpdateName = findViewById(R.id.editTextUpdateName);
        editTextUpdateDescription = findViewById(R.id.editTextUpdateDescription);
        editTextUpdatePrice = findViewById(R.id.editTextUpdatetPrice);

        buttonSaveUpdate = findViewById(R.id.updateProduct);
        databaseProducts = FirebaseDatabase.getInstance().getReference("products");
        productId = getIntent().getStringExtra("productId");
        String name = getIntent().getStringExtra("productName");
        String description = getIntent().getStringExtra("productDescription");
        double price = getIntent().getDoubleExtra("productPrice", 0.0);

        editTextUpdateName.setText(name);
        editTextUpdateDescription.setText(description);
        editTextUpdatePrice.setText(String.valueOf(price));
        buttonSaveUpdate.setOnClickListener(v -> updateProduct());

    }

    private void updateProduct() {
        String name = editTextUpdateName.getText().toString().trim();
        String description = editTextUpdateDescription.getText().toString().trim();
        String priceStr = editTextUpdatePrice.getText().toString().trim();

        if (name.isEmpty() || description.isEmpty() || priceStr.isEmpty()) {
            Toast.makeText(this, "Please filled", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceStr);
        Product product = new Product(productId,name, description, price);
        databaseProducts.child(productId).setValue(product);
        finish();
    }
}
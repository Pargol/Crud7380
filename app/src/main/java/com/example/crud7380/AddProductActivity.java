package com.example.crud7380;

import android.os.Bundle;
import android.text.TextUtils;
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

public class AddProductActivity extends AppCompatActivity {

    EditText editTextName, editTextDescription, editTextPrice;
    Button buttonAddProduct;

    DatabaseReference database, databaseReference2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        editTextName = findViewById(R.id.editTextProductName);
        editTextDescription = findViewById(R.id.editTextProductDescription);
        editTextPrice = findViewById(R.id.editTextProductPrice);
        buttonAddProduct = findViewById(R.id.buttonaddProduct);

        database = FirebaseDatabase.getInstance().getReference("products");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("dsdd"); // For updating the product in real-time. You can use the same reference as above.

        buttonAddProduct.setOnClickListener(v -> addProduct());
    }

    private void addProduct() {
        String name =  editTextName.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        String price = editTextPrice.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(description) || TextUtils.isEmpty(price)) {
            Toast.makeText(this, "All the field need to be fill", Toast.LENGTH_SHORT).show();
            return;
        }
        // Generate a unique ID for the product
        String id = database.push().getKey();

        String idtranslation = databaseReference2.push().getKey();

        databaseReference2.child(idtranslation).child("productId").setValue(id);
        double priceValue = Double.parseDouble(price);
        Product product = new Product(id, name, description, priceValue);

        if (id!= null) {
            database.child(id).setValue(product);
            Toast.makeText(this, "Product added successfully", Toast.LENGTH_SHORT).show();
            // Clear the input fields
            editTextName.setText("");
            editTextDescription.setText("");
            editTextPrice.setText("");
        }

    }

}
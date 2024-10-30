package com.example.crud7380;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button addNewProduct, viewAllProducts , updateProduct, deleteProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNewProduct = findViewById(R.id.buttonAddNewProduct);
        viewAllProducts = findViewById(R.id.buttonViewProduct);
        updateProduct = findViewById(R.id.buttonUpdateProduct);
        deleteProduct = findViewById(R.id.buttonDeleteProduct);

        addNewProduct.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,
                AddProductActivity.class)));

        viewAllProducts.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,
                ViewProductActivity.class)));

        updateProduct.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,
                UpdateProductsActivity.class)));

        deleteProduct.setOnClickListener(v -> startActivity(new Intent(MainActivity.this
                ,DeleteProductActivity.class)));

    }
}
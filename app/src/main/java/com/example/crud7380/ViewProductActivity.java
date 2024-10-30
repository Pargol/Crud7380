package com.example.crud7380;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewProductActivity extends AppCompatActivity {

    ListView listViewProducts;

    DatabaseReference database;

    ArrayList<String> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_product);

        listViewProducts = findViewById(R.id.listviewProducts);

        database = FirebaseDatabase.getInstance().getReference("products");
        productList = new ArrayList<>();

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Product product = postSnapshot.getValue(Product.class);
                    if (product != null) {
                        productList.add(product.getName() + " - $" + product.getPrice());
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(ViewProductActivity.this,
                        android.R.layout.simple_list_item_1, productList);
                listViewProducts.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                // Handle database error here
            }
        });
    }
}
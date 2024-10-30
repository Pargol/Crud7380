package com.example.crud7380;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class DeleteProductAdapter extends ArrayAdapter<Product> {
    private final Context context;
    private final List<Product> productList;
    private final DatabaseReference database;

    public DeleteProductAdapter(Context context, List<Product> productList) {
        super(context, R.layout.item_product_delete, productList);
        this.context = context;
        this.productList = productList;
        this.database = FirebaseDatabase.getInstance().getReference("products");
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_product_delete, parent, false);
        }
        TextView textViewProductName = convertView.findViewById(R.id.textViewProductName);
        TextView textViewProductPrice = convertView.findViewById(R.id.textViewProductPrice);
        Button buttonDelete = convertView.findViewById(R.id.deletebtn);

        Product product = productList.get(position);

        textViewProductName.setText(product.getName());
        textViewProductPrice.setText("Price: " + product.getPrice());
        // Button click listener for delete button
        buttonDelete.setOnClickListener(v -> showDeleteConfirmationDialog(product));

        return convertView;
    }

    private void showDeleteConfirmationDialog(Product product) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete Product");
        builder.setMessage("Are you sure you want to delete " + product.getName() + "?");
        builder.setPositiveButton("Yes", (dialog, which) -> deleteProduct(product.getId()));
        builder.setNegativeButton("No", null);
        builder.show();
    }

    private void deleteProduct(String productId) {
        database.child(productId).removeValue().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(context, "Delete succesfully", Toast.LENGTH_SHORT).show();
                productList.removeIf(p -> p.getId().equals(productId));
                notifyDataSetChanged();
            } else {
                Toast.makeText(context, "delete failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

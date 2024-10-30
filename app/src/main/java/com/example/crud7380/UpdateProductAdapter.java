package com.example.crud7380;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class UpdateProductAdapter extends ArrayAdapter {

    private final Context context;
    private final List<Product> productList;

    public UpdateProductAdapter(Context context, List<Product> productList) {
        super(context, R.layout.item_product_update, productList);
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_product_update, parent, false);
        }
        Product product = productList.get(position);
        TextView nameTextView = convertView.findViewById(R.id.textViewProductName);
        TextView priceTextView = convertView.findViewById(R.id.textViewProductPrice);
        nameTextView.setText(product.getName());
        priceTextView.setText("Price: " + product.getPrice());

        Button updateBtn = convertView.findViewById(R.id.updatebtn);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditeProduct.class);
                intent.putExtra("productId", product.getId());
                intent.putExtra("productName", product.getName());
                intent.putExtra("productDescription", product.getDescription());
                intent.putExtra("productPrice", product.getPrice());
                context.startActivity(intent);
            }
        });


        return convertView;
    }
}

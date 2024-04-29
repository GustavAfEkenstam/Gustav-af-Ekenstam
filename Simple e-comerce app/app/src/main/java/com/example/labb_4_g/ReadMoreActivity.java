package com.example.labb_4_g;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReadMoreActivity extends AppCompatActivity {

    private TextView tvId, tvName, tvCategory, tvCompany, tvPrice, tvAvailable, tvDescription;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_more);

        tvId = findViewById(R.id.text_view_id_content);
        tvName = findViewById(R.id.text_view_name_content);
        tvCategory = findViewById(R.id.text_view_category_content);
        tvCompany = findViewById(R.id.text_view_company_content);
        tvPrice = findViewById(R.id.text_view_price_content);
        tvAvailable = findViewById(R.id.text_view_available_content);
        tvDescription = findViewById(R.id.text_view_description_content);

        product = (Product) getIntent().getSerializableExtra("product");

        tvId.setText(product.getId());
        tvName.setText(product.getName());
        tvCategory.setText(product.getCategory());
        tvCompany.setText(product.getCompany());
        tvPrice.setText(product.getPrice());
        tvAvailable.setText(product.getAvailable());
        tvDescription.setText(product.getDescription());
    }
}
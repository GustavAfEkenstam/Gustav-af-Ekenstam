package com.example.labb_4_g;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class SeeCartActivity extends AppCompatActivity {

    private ArrayAdapter adapter;
    private static final int CLEAR_CART = 400, CONTINUE_SHOPPING = 300;
    private ListView listView;
    private ShoppingCart cart;
    private Button clearCartButton, backToShoppingButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_cart);

        listView = findViewById(R.id.list_view);
        clearCartButton = findViewById(R.id.clear_cart_button);
        backToShoppingButton = findViewById(R.id.back_to_shopping_button);

        cart = (ShoppingCart) getIntent().getSerializableExtra("products");

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);

        for(int i = 0; i < cart.size(); i++){
            adapter.add(cart.getProduct(i).getName());
        }
        listView.setAdapter(adapter);

        clearCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart.clearCart();
                adapter.clear();
                Intent intent = new Intent(SeeCartActivity.this, MainActivity.class);
                setResult(CLEAR_CART, intent);
                finish();
            }
        });

        backToShoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeeCartActivity.this, MainActivity.class);
                setResult(CONTINUE_SHOPPING, intent);
                finish();
            }
        });
    }
}
package com.example.labb_4_g;

import static androidx.core.app.ActivityCompat.requestPermissions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements VolleyCallback, ReadMoreClick{

    private static final int
            LAUNCH_SEE_CART_ACTIVITY = 100,
            LAUNCH_READ_MORE_ACTIVITY = 200,
            CONTINUE_SHOPPING = 300,
            CLEAR_CART = 400;
    private String [] permissions= {
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    private String file = "ShoppingCart";
    private Controller controller;
    private APICall apiCall;
    private ShoppingCart cart;
    private ArrayList<Product> product;
    private ProductAdapter productAdapter;
    private RecyclerView recyclerView;
    private Button loadMoreButton, seeCartButton;
    private FileManager fileManager;
    private int page = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
        cart = new ShoppingCart();
        fileManager = new FileManager();
        product = new ArrayList<>();
        apiCall = new APICall();

        controller = new Controller( apiCall, MainActivity.this, product, fileManager, cart);
        productAdapter = new ProductAdapter(product, MainActivity.this, MainActivity.this);

        controller.setCart(controller.readFile(file));
        controller.getData(MainActivity.this, page);

        seeCartButton = findViewById(R.id.see_cart_button);
        recyclerView = findViewById(R.id.recycler_view);
        loadMoreButton = findViewById(R.id.load_more_button);

        loadMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page++;
                controller.getData(MainActivity.this, page);
            }
        });

        seeCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SeeCartActivity.class);
                intent.putExtra("products", controller.getCart());
                startActivityForResult(intent, LAUNCH_SEE_CART_ACTIVITY);
            }
        });
    }

    @Override
    protected void onActivityResult(int request, int result, @Nullable Intent intent) {
        super.onActivityResult(request, result, intent);

        if(request == LAUNCH_SEE_CART_ACTIVITY){
            if(result == CONTINUE_SHOPPING){}
            else if(result == CLEAR_CART){
                controller.clearCart();
                controller.writeToFile(file, controller.getCart());
            }
        }
    }

    private boolean checkPermission(){
        if(permissions != null){
            for(int i = 0; i < permissions.length; i++){
                int result = ContextCompat.checkSelfPermission(MainActivity.this, permissions[i]);
                if (result != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{permissions[i]}, 1);
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int request, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(request, permissions, grantResults);

        Log.e("hej", String.valueOf(grantResults));
        if(request == 100 &&
                (grantResults.length > 0) &&
                (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                controller.writeToFile(file, controller.getCart());
        }
        else{
            Log.e("permissions", "permission denied");
        }
    }

    @Override
    public void onSuccess(JSONObject object) {
        try {
            JSONArray jsonArray = object.getJSONArray("products");
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject products = jsonArray.getJSONObject(i);

                product.add(new Product(
                        products.getString("id"),
                        products.getString("name"),
                        products.getString("description"),
                        products.getString("category"),
                        products.getString("company"),
                        products.getString("price"),
                        products.getString("available")));
                Log.e("MethodOnSuccess", product.get(i).getAllInfo());

                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(productAdapter);
                recyclerView.scrollToPosition(product.size() - 11);
            }
        } catch (JSONException e) {
            Log.e("MethodOnSuccess", e.toString());
        }
    }

    @Override
    public void onFailure(Exception e) {
        Log.e("TaggOnFailure", e.toString());
    }

    @Override
    public void readMore(int position) {
        Intent intent = new Intent(MainActivity.this, ReadMoreActivity.class);
        intent.putExtra("product", product.get(position));
        startActivityForResult(intent, LAUNCH_READ_MORE_ACTIVITY);
    }

    @Override
    public void addToCart(int position) {
        controller.addToCart(product.get(position));
        controller.writeToFile(file, controller.getCart());
        for(int i = 0; i < controller.getCart().size(); i++){
            Log.e("Method addToCart", controller.getCart().getProduct(i).getName());
        }
    }
}
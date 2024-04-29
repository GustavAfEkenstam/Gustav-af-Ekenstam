package com.example.labb_4_g;

import java.io.Serializable;
import java.util.ArrayList;

public class ShoppingCart implements Serializable {
    private ArrayList<Product> cart;
    public ShoppingCart(){
        cart = new ArrayList<>();
    }

    public void addToCart(Product product){
        cart.add(product);
    }
    public Product getProduct(int position){
        return cart.get(position);
    }
    public int size(){
        return cart.size();
    }

    public void clearCart(){
        cart.clear();
    }
}

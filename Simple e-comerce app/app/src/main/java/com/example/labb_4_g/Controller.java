package com.example.labb_4_g;

import android.content.Context;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import java.util.ArrayList;

public class Controller {
    private APICall apiCall;
    private Context context;
    private ArrayList <Product> product;
    private String url;
    private FileManager fileManager;
    private ShoppingCart cart;
    public Controller(APICall apiCall,
                      Context context,
                      ArrayList<Product> product,
                      FileManager fileManager,
                      ShoppingCart cart){

        this.apiCall = apiCall;
        this.context = context;
        this.product = product;
        this.fileManager = fileManager;
        this.cart = cart;
    }

    public void getData(VolleyCallback callback, int pageNrr){
        url =   "https://informatik-webbkurser.hotell.kau.se/WebAPI/v1/products?" +
                "limit=10" +
                "&page=" +
                pageNrr +
                "&apikey=h2s269nsMn012NASi2537bsA9dBSa2";

        Cache cache = new DiskBasedCache(context.getCacheDir(), 1024 * 1024);
        Network network = new BasicNetwork(new HurlStack());
        RequestQueue requestQueue = new RequestQueue(cache, network);
        requestQueue.start();
        apiCall.get(requestQueue, callback, url);
    }

    public ShoppingCart readFile(String file){
        return fileManager.readFile(file);
    }
    public void writeToFile(String fileName, ShoppingCart cart){
        fileManager.writeToFile(fileName, cart);
    }

    public void addToCart(Product product){
        cart.addToCart(product);
    }

    public ShoppingCart getCart(){
        return  cart;
    }

    public ShoppingCart clearCart(){
        cart.clearCart();
        return cart;
    }
    public void setCart(ShoppingCart cart){
        this.cart = cart;
    }
}

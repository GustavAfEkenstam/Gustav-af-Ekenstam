package com.example.labb_4_g;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Product> productsList;
    private final ReadMoreClick onClickListener;

    public ProductAdapter(ArrayList<Product> productsList, Context context, ReadMoreClick onClickListener){
        this.productsList = productsList;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int typeOfView) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ViewHolder(view, onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int onBindPosition) {

        Product product = productsList.get(onBindPosition);
        holder.textViewName.setText(product.getName());
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewName;
        private Button readMoreButton, addToCartButton;
        public ViewHolder(@NonNull View itemView, ReadMoreClick onClickListener) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_name_2);
            readMoreButton = itemView.findViewById(R.id.read_more_button);
            addToCartButton = itemView.findViewById(R.id.add_to_cart_button);

            addToCartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onClickListener != null){
                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION){
                            onClickListener.addToCart(position);
                        }
                    }
                }
            });

            readMoreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onClickListener != null){
                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION){
                            onClickListener.readMore(position);
                        }
                    }
                }
            });
        }
    }
}


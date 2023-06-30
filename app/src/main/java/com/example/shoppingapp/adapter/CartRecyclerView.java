package com.example.shoppingapp.adapter;

import static com.example.shoppingapp.CartActivity.cartedItems;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoppingapp.Domain.ItemModelClass;
import com.example.shoppingapp.R;

import java.util.ArrayList;

public class CartRecyclerView extends RecyclerView.Adapter<CartRecyclerView.ViewHolder> {

    private final Context context;
//    private final ArrayList<ItemModelClass> items;

    //created an array for quantity to each item in the list
//    private int[] quantity;


    // Constructor
    public CartRecyclerView(Context context, ArrayList<ItemModelClass> items) {
        this.context = context;
//        this.items = items;
//        this.quantity = quantity;
    }

//    public int[] getItemsQuantity(){
//        return quantity;
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_each_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        ItemModelClass item = cartedItems.get(position);

        //image
        String picUrl = cartedItems.get(position).getImageUrl();
        int drawableResourceId =  holder.itemView.getContext().getResources().getIdentifier(picUrl,"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.cartImage);
        //item name
        holder.cartName.setText(cartedItems.get(position).getName());

        holder.cartCount.setText(String.valueOf(cartedItems.get(position).getQuantity()));

        //invisibling minus
//        if(holder.cartCount.getText().toString().equals("0")){
//            holder.cartMinusBtn.setVisibility(View.INVISIBLE);
//        }

        //item cost*quantity
        int cost = ((int)Integer.parseInt(holder.cartCount.getText().toString())) * cartedItems.get(position).getCost();
        holder.cartCost.setText(String.valueOf(cost));

        //minus
        holder.cartMinusBtn.setOnClickListener(v -> {
            cartedItems.get(position).setQuantity(cartedItems.get(position).getQuantity()-1);
            int quantity = cartedItems.get(position).getQuantity();
            holder.cartCount.setText(String.valueOf(quantity));
//            item.setQuantity(quantity[position]);
            if(holder.cartCount.getText().toString().equals("0")) {
                holder.cartMinusBtn.setVisibility(View.INVISIBLE);
            }
            holder.cartCost.setText(String.valueOf(cartedItems.get(position).getCost() * Integer.parseInt(holder.cartCount.getText().toString())));
        });

        //plus
        holder.cartPlusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartedItems.get(position).setQuantity(cartedItems.get(position).getQuantity()+1);
                int quantity = cartedItems.get(position).getQuantity();
                if(quantity>0){
                    holder.cartMinusBtn.setVisibility(View.VISIBLE);
                }
                if(quantity > cartedItems.get(position).getStock()){
                    cartedItems.get(position).setQuantity(quantity-1);
                    Toast.makeText(context,"No More Stock", Toast.LENGTH_SHORT).show();
                }
                else{

//                    Log.d("plus","++");
//                    item.setQuantity(quantity);
//                    Log.d("plus",""+item.getQuantity());
                    holder.cartCount.setText(String.valueOf(quantity));
                }
                holder.cartCost.setText(String.valueOf(cartedItems.get(position).getCost() * Integer.parseInt(holder.cartCount.getText().toString())));
                Toast.makeText(context, ""+cartedItems.get(position).getQuantity(),0).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        return cartedItems.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView cartImage;
        private final TextView cartName;
        private final TextView cartCount;
        private final ImageButton cartPlusBtn, cartMinusBtn;
        private  final TextView cartCost;
        //todo when error occurs remove final from variables above;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cartImage = itemView.findViewById(R.id.itemImage);
            cartName = itemView.findViewById(R.id.itemName);
            cartCount = itemView.findViewById(R.id.itemCount);
            cartPlusBtn = itemView.findViewById(R.id.plusBtn);
            cartMinusBtn = itemView.findViewById(R.id.minusBtn);
            cartCost = itemView.findViewById(R.id.itemCost);
        }
    }
}
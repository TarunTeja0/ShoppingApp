package com.example.shoppingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.shoppingapp.Domain.ItemModelClass;
import com.example.shoppingapp.adapter.CartRecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    int[] quantity;
    ArrayList<ItemModelClass> items;
    private TextView cartAmount, deliveryCharges, total;
    private CartRecyclerView cartRecyclerViewAdapter;
    private RecyclerView cartRecyclerView;
    public static ArrayList<ItemModelClass> cartedItems = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        cartAmount = findViewById(R.id.cartAmount1);
        deliveryCharges = findViewById(R.id.cartAmount2);
        total = findViewById(R.id.cartAmount3);
        cartRecyclerView =findViewById(R.id.cartRecyclerView);
        Intent intent = getIntent();
        quantity = intent.getIntArrayExtra("quantitiesArray");
        String jsonString = intent.getStringExtra("itemsArrayList");
        TypeToken<ArrayList<ItemModelClass>> typeToken = new TypeToken<ArrayList<ItemModelClass>>() {};

       items = new Gson().fromJson(jsonString,typeToken);
       cartedItems= filterItems(items);

       cartRecyclerViewAdapter =new CartRecyclerView(this, cartedItems);
       cartRecyclerView.setAdapter(cartRecyclerViewAdapter);
       cartRecyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        cartRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bill(cartedItems);
            }
        });
//        new RecyclerView.OnItemTouchListener() {
//            @Override
//            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//                return false;
//            }
//
//            @Override
//            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//                bill();
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//            }
//        });

    }

    private ArrayList<ItemModelClass> filterItems(ArrayList<ItemModelClass> items) {
        ArrayList<ItemModelClass> tempItems = new ArrayList<>();
        for(int i = 0; i< items.size();i++){
            if(items.get(i).getQuantity()>0){
                tempItems.add(items.get(i));
            }
        }
        cartedItems = tempItems;
        bill(cartedItems);
        return tempItems;

    }

    private void bill(ArrayList<ItemModelClass> items){
        int cartTotal=0;
        int deliveryChar= 15;

        if(items.size()==0){
            return;
        }
        for(int i = 0; i< items.size();i++){
            cartTotal += items.get(i).getQuantity() * items.get(i).getCost();
        }
        cartAmount.setText(String.valueOf(cartTotal));
        deliveryCharges.setText(String.valueOf(deliveryChar));
        total.setText(String.valueOf(cartTotal + deliveryChar));

    }
}
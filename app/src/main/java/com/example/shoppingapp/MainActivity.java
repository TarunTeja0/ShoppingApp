package com.example.shoppingapp;

import static com.example.shoppingapp.adapter.ItemsAdapter.quantity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;

import com.example.shoppingapp.Domain.CategoryDomain;
import com.example.shoppingapp.Domain.ItemModelClass;
import com.example.shoppingapp.adapter.CategoryAdapter;
import com.example.shoppingapp.adapter.ItemsAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences; 

    //todo shared preference lo nundi data oncreate lo teeskovali, data ni malli ondestroy lo store cheyyali sharedpref lo
            //intro page create cheyyaali andhulo login undali
            //todo menu create chesi andulo- "admin mode meedha click cheysthey data ni manipulate cheyyagalagaali
            //adim user details already create chesi key value pair la save cheyyaali sharedpref lo
            // login ki otp pettali
    private ItemsAdapter itemsRecyclerViewAdapter;


    private CategoryAdapter categoryRecyclerViewAdapter;
    private ArrayList<ItemModelClass> itemsArrayList;
    private  RecyclerView recyclerViewCategoryList, recyclerViewItemsList;

    private Handler handler;

    FloatingActionButton cartBtn;
    BottomSheetDialogFragment bottomSheetDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        getItemsArrayList();
        
        cartBtn = findViewById(R.id.cartBtn);

        handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                recyclerViewListCategory();
                recyclerViewItemsList();
                cartBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        assigningQuantitiesOfEachItem(quantity);
                        Intent cartIntent = new Intent(MainActivity.this, CartActivity.class);
//                        cartIntent.putExtra("quantitiesArray", itemsRecyclerViewAdapter.getItemsQuantity());
                        String jsonString = new Gson().toJson(itemsArrayList);
                        cartIntent.putExtra("itemsArrayList",jsonString);

                        startActivity(cartIntent);
                    }
                });


            }
        });


    }

    private void getItemsArrayList() {
        sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        String json = sharedPreferences.getString("itemsArray", null);
        ArrayList<ItemModelClass> items = new Gson().fromJson(json, new TypeToken<ArrayList<String>>() {}.getType());
        if(items != null){
            itemsArrayList = items;
        }
    }

    private void assigningQuantitiesOfEachItem(int[] quantity) {
        for(int i= 0 ; i <  quantity.length; i++){
            itemsArrayList.get(i).setQuantity(quantity[i]);
        }
    }

    private void recyclerViewListCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList=findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Vegetables", "vegetable_category"));
        category.add(new CategoryDomain("Fruits", "fruit_category"));
        category.add(new CategoryDomain("Cow Products", "cow_category"));

        categoryRecyclerViewAdapter=new CategoryAdapter(category);
        recyclerViewCategoryList.setAdapter(categoryRecyclerViewAdapter);

    }
    private void recyclerViewItemsList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewItemsList = findViewById(R.id.recyclerView2);
        recyclerViewItemsList.setLayoutManager(linearLayoutManager);

        recyclerViewItemsList.setOverScrollMode(View.SCROLL_CAPTURE_HINT_AUTO);
        //Todo cart button vlick chryyagaane oka intent create cheyyali new activity ki. anni items lo quantity edhaithey >0 unte cart of display cheyyali.

        itemsArrayList = new ArrayList<>();
        itemsArrayList.add(new ItemModelClass("Cabbage","cabbage","","vegetable",0,5,35,""));
        itemsArrayList.add(new ItemModelClass("Cabbage","cabbage","","vegetable",0,5,35,""));
        itemsArrayList.add(new ItemModelClass("Cabbage","cabbage","","vegetable",0,5,35,""));
        itemsArrayList.add(new ItemModelClass("Cabbage","cabbage","","vegetable",0,5,35,""));
        itemsArrayList.add(new ItemModelClass("Cabbage","cabbage","","vegetable",0,5,35,""));
        itemsArrayList.add(new ItemModelClass("Cabbage","cabbage","","vegetable",0,5,35,""));

        itemsRecyclerViewAdapter= new ItemsAdapter(this,itemsArrayList);
        recyclerViewItemsList.setAdapter(itemsRecyclerViewAdapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        
        setItemsArrayListIntoSharedPrefs();
    }

    private void setItemsArrayListIntoSharedPrefs() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json =   new Gson().toJson(itemsArrayList);
        editor.putString("itemsArray", json);
        editor.apply();

    }

}
package com.example.nathan.sale_stalk;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import java.util.HashMap;



/**
 * Created by Nathan on 27/02/2018.
 */

public class MainActivity extends Activity {
    private ListView lvProduct;
    private ProductListAdapter adapter;
    private List<Product> mProductList;

    // User Session Manager Class
    UserSessionManager session;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Session class instance
        session = new UserSessionManager(getApplicationContext());


        // Check user LoginActivity (this is the important point)
        // If User is not logged in , This will redirect user to LoginActivity
        // and finish current activity from activity stack.
        if(session.checkLogin())
            finish();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // get name
        String name = user.get(UserSessionManager.KEY_NAME);

        // get email
        String email = user.get(UserSessionManager.KEY_EMAIL);


        lvProduct = (ListView)findViewById(R.id.listview_product);

        mProductList = new ArrayList<>();
        //Add sample data for list
        //We can get data from DB, webservice here
        mProductList.add(new Product(1, "iPhone4", 200, "Apple iPhone4 16GB"));
        mProductList.add(new Product(3, "iPhone4S", 250, "Apple iPhone4S 16GB"));
        mProductList.add(new Product(4, "iPhone5", 300, "Apple iPhone5 16GB"));
        mProductList.add(new Product(5, "iPhone5S", 350, "Apple iPhone5S 16GB"));
        mProductList.add(new Product(6, "iPhone6", 400, "Apple iPhone6 16GB"));
        mProductList.add(new Product(7, "iPhone6S", 450, "Apple iPhone6S 16GB"));
        mProductList.add(new Product(8, "iPhone7", 500, "Apple iPhone7 16GB"));
        mProductList.add(new Product(9, "iPhone7S", 600, "Apple iPhon7S 16GB"));
        mProductList.add(new Product(10, "iPhone8", 700, "Apple iPhone8 16GB"));
        mProductList.add(new Product(11, "iPhone8S", 800, "Apple iPhone8S 16GB"));

        //Init adapter
        adapter = new ProductListAdapter(getApplicationContext(), mProductList);
        lvProduct.setAdapter(adapter);

        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Do something
                //Ex: display msg with product id get from view.getTag
                Toast.makeText(getApplicationContext(), "Clicked product id =" + view.getTag(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
package com.genuinecoder.springclient.order_page_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.genuinecoder.springclient.R;
import com.genuinecoder.springclient.main_menu_activity.MainMenuActivity;
import com.genuinecoder.springclient.pets_page_activity.PetsPageActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OrderPageActivity extends AppCompatActivity
{
    Button city;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);
        city = findViewById(R.id.button_city);
        //city.setText(((MyApplication) getApplication()).getUser().getAddressWalk());
        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.order_page);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.main_page:
                        startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.order_page:
                        return true;
                    case R.id.pet_page:
                        startActivity(new Intent(getApplicationContext(), PetsPageActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    /*case R.id.profile_page:
                        startActivity(new Intent(getApplicationContext(),About.class));
                        overridePendingTransition(0,0);
                        return true;*/
                }
                return false;
            }
        });
    }
}

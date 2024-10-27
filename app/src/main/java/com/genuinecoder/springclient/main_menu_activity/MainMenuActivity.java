package com.genuinecoder.springclient.main_menu_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.genuinecoder.springclient.MyApplication;
import com.genuinecoder.springclient.R;
import com.genuinecoder.springclient.employee_list_activity.EmployeeListActivity;
import com.genuinecoder.springclient.order_page_activity.OrderPageActivity;
import com.genuinecoder.springclient.pets_page_activity.PetsPageActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainMenuActivity extends AppCompatActivity
{
    Button city;
    RelativeLayout dogWalkingOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        city = findViewById(R.id.button_city);
        city.setText(((MyApplication) getApplication()).getUser().getAddressWalk());
        dogWalkingOrder = findViewById(R.id.idDogWalkingOrder);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.main_page);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.main_page:
                        return true;
                    case R.id.order_page:
                        startActivity(new Intent(getApplicationContext(), OrderPageActivity.class));
                        overridePendingTransition(0,0);
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

        dogWalkingOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toEmployeeList();
            }
        });
    }

    private void toEmployeeList()
    {
        Intent intent = new Intent(this, EmployeeListActivity.class);
        startActivity(intent);
    }
}

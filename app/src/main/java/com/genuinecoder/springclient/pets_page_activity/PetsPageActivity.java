package com.genuinecoder.springclient.pets_page_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.genuinecoder.springclient.MyApplication;
import com.genuinecoder.springclient.R;
import com.genuinecoder.springclient.main_menu_activity.MainMenuActivity;
import com.genuinecoder.springclient.order_page_activity.OrderPageActivity;
import com.genuinecoder.springclient.pets_page_activity.adapters.PetCardAdapter;
import com.genuinecoder.springclient.pets_page_activity.dto.PetDTO;
import com.genuinecoder.springclient.pets_page_activity.mapper.PetMapperImpl;
import com.genuinecoder.springclient.pets_page_activity.model.PetCard;
import com.genuinecoder.springclient.reotrfit.PetApi;
import com.genuinecoder.springclient.reotrfit.RetrofitService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetsPageActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private Button addPetButton;

    Button city;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets_page);

        city = findViewById(R.id.button_city);
        city.setText(((MyApplication) getApplication()).getUser().getAddressWalk());

        recyclerView = findViewById(R.id.petList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        addPetButton = findViewById(R.id.idButtonAddPet);

        loadPets();
        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.pet_page);

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
                        startActivity(new Intent(getApplicationContext(), OrderPageActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.pet_page:
                        return true;
                    /*case R.id.profile_page:
                        startActivity(new Intent(getApplicationContext(),About.class));
                        overridePendingTransition(0,0);
                        return true;*/
                }
                return false;
            }
        });

        addPetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAddPetPage();
            }
        });

        // on below line we are creating a method to create item touch helper
        // method for adding swipe to delete functionality.
        // in this we are specifying drag direction and position to right
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                // this method is called
                // when the item is moved.
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                // below line is to get the position
                // of the item at that position.
                int position = viewHolder.getAdapterPosition();

                // this method is called when item is swiped.
                // below line is to remove item from our array list.
                //recyclerDataArrayList.remove(viewHolder.getAdapterPosition());

                // below line is to notify our item is removed from adapter.
                //recyclerViewAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

                // below line is to display our snackbar with action.
                /*Snackbar.make(recyclerView, deletedCourse.getTitle(), Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // adding on click listener to our action of snack bar.
                        // below line is to add our item to array list with a position.
                        recyclerDataArrayList.add(position, deletedCourse);

                        // below line is to notify item is
                        // added to our adapter class.
                        recyclerViewAdapter.notifyItemInserted(position);
                    }
                }).show();*/
            }
            // at last we are adding this
            // to our recycler view.
        }).attachToRecyclerView(recyclerView);
    }

    private void loadPets() {
        RetrofitService retrofitService = new RetrofitService();
        PetApi petApi = retrofitService.getRetrofit().create(PetApi.class);

        petApi.getAllPets().
                enqueue(new Callback<List<PetDTO>>() {
                    @Override
                    public void onResponse(Call<List<PetDTO>> call, Response<List<PetDTO>> response) {
                        System.out.println("Ответ получен.");
                        PetMapperImpl petMapper = new PetMapperImpl();
                        List<PetDTO> petDTOList = response.body();
                        List<PetCard> petCardList = petMapper.dtoListToPetList(petDTOList);
                        populateListView(petCardList);
                    }

                    @Override
                    public void onFailure(Call<List<PetDTO>> call, Throwable t) {
                        Toast.makeText(PetsPageActivity.this, "Failed to load employees", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void populateListView(List<PetCard> petCardList) {
        //System.out.println(employeeList.get(0).getFirstName());
        PetCardAdapter petCardAdapter = new PetCardAdapter(petCardList);
        recyclerView.setAdapter(petCardAdapter);
    }

    private void toAddPetPage()
    {
        Intent intent = new Intent(this, AddPetActivity.class);
        startActivity(intent);
    }
}
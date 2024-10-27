package com.genuinecoder.springclient.pets_page_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.genuinecoder.springclient.R;
import com.genuinecoder.springclient.pets_page_activity.dto.PetDTO;
import com.genuinecoder.springclient.pets_page_activity.enums.Gender;
import com.genuinecoder.springclient.reotrfit.PetApi;
import com.genuinecoder.springclient.reotrfit.RetrofitService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPetActivity extends AppCompatActivity {

  EditText name;
  EditText about;
  EditText age;
  EditText breed;
  Spinner typeSpinner;
  Spinner genderSpinner;
  FloatingActionButton addButton;
  ImageButton backButton;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_pet);
    about = findViewById(R.id.idAboutInput);
    name = findViewById(R.id.idNameInput);
    age = findViewById(R.id.idAgeInput);
    breed = findViewById(R.id.idBreedInput);
    addButton = findViewById(R.id.fabAddPet);
    backButton = findViewById(R.id.backButton);

      genderSpinner = findViewById(R.id.spinnerInputGender);
      ArrayAdapter<CharSequence> adapterGender = ArrayAdapter.createFromResource(
              this,
              R.array.gender,
              android.R.layout.simple_spinner_item
      );
      adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      genderSpinner.setAdapter(adapterGender);

      typeSpinner = findViewById(R.id.spinnerInputType);
      ArrayAdapter<CharSequence> adapterType = ArrayAdapter.createFromResource(
              this,
              R.array.types,
              android.R.layout.simple_spinner_item
      );
      adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      typeSpinner.setAdapter(adapterType);

      backButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          toPetsPage();
        }
      });
    addButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          AddPet();
      }
    });

  }

  private void AddPet()
  {
    PetDTO petDTO = new PetDTO();
    petDTO.setAge(Integer.parseInt(age.getText().toString()));
    petDTO.setName(name.getText().toString());
    petDTO.setBreed(breed.getText().toString());
    petDTO.setDescription(about.getText().toString());
    petDTO.setOwnerId(14l);
    
    if(genderSpinner.getSelectedItem().equals("Мужской"))
      petDTO.setGender(Gender.Male);
    else
      petDTO.setGender(Gender.Female);
    if(typeSpinner.getSelectedItem().equals("Собака"))
      petDTO.setType("Собака");
    else
      petDTO.setType("Кот");

    RetrofitService retrofitService = new RetrofitService();
    PetApi petApi = retrofitService.getRetrofit().create(PetApi.class);

    petApi.addPet(petDTO)
            .enqueue(new Callback<PetDTO>() {
              @Override
              public void onResponse(Call<PetDTO> call, Response<PetDTO> response) {

                if(response.isSuccessful())
                {
                  Toast.makeText(AddPetActivity.this, "Питомец добавлен!", Toast.LENGTH_SHORT).show();
                  System.out.println("Питомец добавлен!");
                }
                else
                {
                  System.out.println("Ошибка!");
                }

              }

              @Override
              public void onFailure(Call<PetDTO> call, Throwable t) {
                Toast.makeText(AddPetActivity.this, "Неопознанная ошибка!", Toast.LENGTH_SHORT).show();
              }
            });
  }

  public void toPetsPage()
  {
    Intent intent = new Intent(this, PetsPageActivity.class);
    startActivity(intent);
  }

}
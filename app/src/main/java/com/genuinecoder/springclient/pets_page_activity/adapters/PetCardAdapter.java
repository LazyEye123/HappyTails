
package com.genuinecoder.springclient.pets_page_activity.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.genuinecoder.springclient.R;
import com.genuinecoder.springclient.pets_page_activity.model.PetCard;

import java.util.List;

public class PetCardAdapter extends RecyclerView.Adapter<PetCardAdapter.PetCardHolder> {

  public static class PetCardHolder extends RecyclerView.ViewHolder {

    TextView name, breed, age;
    static ImageView typeImage;

    public PetCardHolder(@NonNull View itemView) {
      super(itemView);
      name = itemView.findViewById(R.id.idPetName);
      breed = itemView.findViewById(R.id.idPetBreed);
      age = itemView.findViewById(R.id.idPetAge);
      typeImage
              = itemView.findViewById(
              R.id.idPetType);
    }

    }


  private List<PetCard> petCardList;

  public PetCardAdapter(List<PetCard> petCardList) {
    this.petCardList = petCardList;
  }

  @NonNull
  @Override
  public PetCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.petcard, parent, false);
    return new PetCardHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull PetCardHolder holder, int position) {
    PetCard petDto = petCardList.get(position);
    String name = petDto.getName();
    holder.name.setText(name);
    String breed = petDto.getBreed();
    holder.breed.setText(breed);
    String age = petDto.getAge() + " лет";
    holder.age.setText(age);
    
    int childItem = -1;

    if(petDto.getType().equals("Кот"))
      childItem = R.drawable.cat;
    if(petDto.getType().equals("Собака"))
      childItem = R.drawable.dog;
    
    PetCardHolder
            .typeImage
            .setImageResource(childItem);

  }

  @Override
  public int getItemCount() {
    return petCardList.size();
  }
}


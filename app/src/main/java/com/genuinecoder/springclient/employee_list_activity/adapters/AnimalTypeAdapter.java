package com.genuinecoder.springclient.employee_list_activity.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.genuinecoder.springclient.R;

import java.util.List;

public class AnimalTypeAdapter extends RecyclerView.Adapter<AnimalTypeHolder>
{

    private List<Integer> ChildItemList;


    // Constructor
    AnimalTypeAdapter(List<Integer> childItemList)
    {
        this.ChildItemList = childItemList;
    }

    @NonNull
    @Override
    public AnimalTypeHolder onCreateViewHolder(
            @NonNull ViewGroup viewGroup,
            int i)
    {

        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(
                        R.layout.animaltype_employee_elem,
                        viewGroup, false);

        return new AnimalTypeHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull AnimalTypeHolder animalTypeHolder,
            int position)
    {

        int childItem
                = ChildItemList.get(position);


        animalTypeHolder
                .typeImage
                .setImageResource(childItem);
    }


    @Override
    public int getItemCount()
    {
        return ChildItemList.size();
    }

}

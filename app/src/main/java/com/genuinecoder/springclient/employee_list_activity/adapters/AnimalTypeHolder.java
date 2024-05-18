package com.genuinecoder.springclient.employee_list_activity.adapters;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.genuinecoder.springclient.R;

public class AnimalTypeHolder
        extends RecyclerView.ViewHolder {

    ImageView typeImage;

    AnimalTypeHolder(View itemView)
    {
        super(itemView);
        typeImage
                = itemView.findViewById(
                R.id.type_image);
    }
}

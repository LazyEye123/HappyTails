package com.genuinecoder.springclient.employee_list_activity.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.genuinecoder.springclient.R;

public class OrderTypeAdapter extends RecyclerView.Adapter<OrderTypeHolder>
{

    private Boolean[] orderTypes;


    // Constructor
    OrderTypeAdapter(Boolean[] orderTypes)
    {
        this.orderTypes = orderTypes;
    }

    @NonNull
    @Override
    public OrderTypeHolder onCreateViewHolder(
            @NonNull ViewGroup viewGroup,
            int i)
    {

        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(
                        R.layout.ordertype_employee_elem,
                        viewGroup, false);

        return new OrderTypeHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull OrderTypeHolder orderTypeHolder,
            int position)
    {

        boolean childItem
                = orderTypes[position];


        switch(position)
        {
            case 0:
            {
                if(childItem) orderTypeHolder.orderType.setText("● Выгул");
                break;
            }
            case 1:
            {
                if(childItem) orderTypeHolder.orderType.setText("● Передержка");
                break;
            }
            case 2:
            {
                if(childItem) orderTypeHolder.orderType.setText("● Зооняня");
                break;
            }
        }
    }


    @Override
    public int getItemCount()
    {
        return orderTypes.length;
    }

}

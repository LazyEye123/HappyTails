package com.genuinecoder.springclient.employee_list_activity.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.genuinecoder.springclient.R;

public class OrderTypeHolder
        extends RecyclerView.ViewHolder {

    TextView orderType;

    OrderTypeHolder(View itemView)
    {
        super(itemView);
        orderType
                = itemView.findViewById(
                R.id.order_text);
    }
}

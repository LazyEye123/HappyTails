package com.genuinecoder.springclient.adapter;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.genuinecoder.springclient.R;

public class EmployeeHolder extends RecyclerView.ViewHolder {

  TextView name, address, rating, reviewCount, price;

  public EmployeeHolder(@NonNull View itemView) {
    super(itemView);
    name = itemView.findViewById(R.id.idEmployeeName);
    address = itemView.findViewById(R.id.idEmployeeAddress);
    rating = itemView.findViewById(R.id.idEmployeeRating);
    reviewCount = itemView.findViewById(R.id.idEmployeeReviewCount);
    price = itemView.findViewById(R.id.idEmployeePrice);
  }
}

package com.genuinecoder.springclient.employee_list_activity.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.genuinecoder.springclient.R;
import com.genuinecoder.springclient.employee_list_activity.model.EmployeeCard;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeHolder> {

  public static class EmployeeHolder extends RecyclerView.ViewHolder {

    TextView name, address, rating, reviewCount, price;
    static RecyclerView AnimalTypes;
    static RecyclerView OrderTypes;


    public EmployeeHolder(@NonNull View itemView) {
      super(itemView);
      name = itemView.findViewById(R.id.idEmployeeName);
      address = itemView.findViewById(R.id.idEmployeeAddress);
      rating = itemView.findViewById(R.id.idEmployeeRating);
      reviewCount = itemView.findViewById(R.id.idEmployeeReviewCount);
      price = itemView.findViewById(R.id.idEmployeePrice);
      AnimalTypes = itemView.findViewById(R.id.idAnimalTypes);
      OrderTypes = itemView.findViewById(R.id.idOrderTypes);
    }
  }

  private RecyclerView.RecycledViewPool
          viewPool
          = new RecyclerView
          .RecycledViewPool();
  private List<EmployeeCard> employeeList;

  public EmployeeAdapter(List<EmployeeCard> employeeList) {
    this.employeeList = employeeList;
  }

  @NonNull
  @Override
  public EmployeeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.viewcard, parent, false);
    return new EmployeeHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull EmployeeHolder holder, int position) {
    EmployeeCard employee = employeeList.get(position);
    String name = employee.getFirstName() + " " + employee.getLastName().charAt(0) + ".";
    holder.name.setText(name);
    holder.address.setText(employee.getAddress());
    String rating = "" + employee.getRating();
    rating = rating.substring(0, 3);
    holder.rating.setText(rating);
    String review = employee.getReviewCount() + " отзывов";
    holder.reviewCount.setText(review);
    String price = "от " + employee.getPrice().intValue() + " ₽";
    holder.price.setText(price);

    LinearLayoutManager layoutManagerAnimals
            = new LinearLayoutManager(
            EmployeeHolder
                    .AnimalTypes
                    .getContext(),
            LinearLayoutManager.HORIZONTAL,
            true);

    LinearLayoutManager layoutManagerOrders
            = new LinearLayoutManager(
            EmployeeHolder
                    .OrderTypes
                    .getContext(),
            LinearLayoutManager.HORIZONTAL,
            true);


    /*layoutManager
            .setInitialPrefetchItemCount(
                    employee
                            .AnimalTypes
                            .size());*/
    layoutManagerAnimals
            .setInitialPrefetchItemCount(2);
    layoutManagerOrders
            .setInitialPrefetchItemCount(3);

    List<Integer> typeImage = new ArrayList<>();

    if(employee.getIsCat())
      typeImage.add(R.drawable.cat);
    if(employee.getIsDog())
      typeImage.add(R.drawable.dog);

    OrderTypeAdapter orderTypeAdapter
            = new OrderTypeAdapter(employee.getOrderTypes());

    EmployeeHolder
            .OrderTypes
            .setLayoutManager(layoutManagerOrders);
    EmployeeHolder
            .OrderTypes
            .setAdapter(orderTypeAdapter);
    EmployeeHolder
            .OrderTypes
            .setRecycledViewPool(viewPool);

    AnimalTypeAdapter animalTypeAdapter
            = new AnimalTypeAdapter(typeImage);
    EmployeeHolder
            .AnimalTypes
            .setLayoutManager(layoutManagerAnimals);
    EmployeeHolder
            .AnimalTypes
            .setAdapter(animalTypeAdapter);
    EmployeeHolder
            .AnimalTypes
            .setRecycledViewPool(viewPool);
  }

  @Override
  public int getItemCount() {
    return employeeList.size();
  }
}

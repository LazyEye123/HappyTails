package com.genuinecoder.springclient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.genuinecoder.springclient.adapter.EmployeeAdapter;
import com.genuinecoder.springclient.adapter.EmployeeFilterAdapter;
import com.genuinecoder.springclient.adapter.EmployeeSortAdapter;
import com.genuinecoder.springclient.model.Cat;
import com.genuinecoder.springclient.model.Employee;
import com.genuinecoder.springclient.model.EmployeeFilter;
import com.genuinecoder.springclient.model.Priority;
import com.genuinecoder.springclient.reotrfit.EmployeeApi;
import com.genuinecoder.springclient.reotrfit.RetrofitService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeListActivity extends AppCompatActivity {

  private RecyclerView recyclerView;
  private  EmployeeFilter employeeFilter = new EmployeeFilter(
          new Boolean[] {true, false, false},
          Priority.Price,
          "Воронеж",
          false,
          true
  );

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    //getTestEmployee();
    //sendCat();
    //getEmployees();
    loadEmployees();
    setContentView(R.layout.activity_employee_list);

    recyclerView = findViewById(R.id.employeeList_recyclerView);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    Spinner spinnerCity = findViewById(R.id.spinnerCity);
    ArrayAdapter<CharSequence> adapterCity = ArrayAdapter.createFromResource(
            this,
            R.array.cities,
            android.R.layout.simple_spinner_item
    );
    adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinnerCity.setAdapter(adapterCity);

    spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
        String city = parentView.getItemAtPosition(position).toString();
        employeeFilter.setCity(city);
        loadEmployees();
      }
      @Override
      public void onNothingSelected(AdapterView<?> parentView) {}

    });

    Spinner spinnerSort = findViewById(R.id.spinnerSort);
    EmployeeSortAdapter adapterSort = new EmployeeSortAdapter(this);
    /*ArrayAdapter<CharSequence> adapterSort = ArrayAdapter.createFromResource(
            this,
            R.array.sort,
            android.R.layout.simple_spinner_item
    );*/
    adapterSort.setDropDownViewResource(R.layout.sort_spinner_row);
    spinnerSort.setAdapter(adapterSort);

    spinnerSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
        LinearLayout sort = (LinearLayout) parentView.getChildAt(0);
        TextView text = (TextView) sort.getChildAt(1);
        String sortMethod = text.getText().toString();
        switch(sortMethod)
        {
          case "Дешевле":
          {
            employeeFilter.setPriority(Priority.Price);
            break;
          }
          case "Много отзывов":
          {
            employeeFilter.setPriority(Priority.Review);
            break;
          }
          case "Выше рейтинг":
          {
            employeeFilter.setPriority(Priority.Rating);
            break;
          }
        }
        loadEmployees();
      }
      @Override
      public void onNothingSelected(AdapterView<?> parentView) {}

    });

    Spinner spinnerFilter = findViewById(R.id.spinnerFilter);
    EmployeeFilterAdapter adapterFilter = new EmployeeFilterAdapter(this);
    /*ArrayAdapter<CharSequence> adapterFilter = ArrayAdapter.createFromResource(
            this,
            R.array.filter,
            android.R.layout.simple_spinner_item
    );*/
    adapterFilter.setDropDownViewResource(R.layout.sort_spinner_row);
    spinnerFilter.setAdapter(adapterFilter);

    spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
        LinearLayout sort = (LinearLayout) parentView.getChildAt(0);
        TextView text = (TextView) sort.getChildAt(1);
        String sortMethod = text.getText().toString();
        switch(sortMethod)
        {
          case "Собака":
          {
            employeeFilter.setDog(true);
            employeeFilter.setCat(false);
            break;
          }
          case "Кот":
          {
            employeeFilter.setDog(false);
            employeeFilter.setCat(true);
            break;
          }
        }
        loadEmployees();
      }
      @Override
      public void onNothingSelected(AdapterView<?> parentView) {}

    });
  }

  private void sendCat()
  {
    RetrofitService retrofitService = new RetrofitService();
    EmployeeApi employeeApi = retrofitService.getRetrofit().create(EmployeeApi.class);
    Cat cat = new Cat();
    cat.setName("Барсик");
    cat.setAge(3);
    cat.setNeeds(new Boolean[] {true, true});
    employeeApi.print(cat)
            .enqueue(new Callback<Cat>() {
              @Override
              public void onResponse(Call<Cat> call, Response<Cat> response) {
                Cat cat = (Cat) response.body();
                cat.print();
              }

              @Override
              public void onFailure(Call<Cat> call, Throwable t) {
                Toast.makeText(EmployeeListActivity.this, "Failed to print cat", Toast.LENGTH_SHORT).show();
              }
            });
  }

  private void getTestEmployee() {
    RetrofitService retrofitService = new RetrofitService();
    EmployeeApi employeeApi = retrofitService.getRetrofit().create(EmployeeApi.class);

    employeeApi.getOneEmployee()
            .enqueue(new Callback<Employee>() {
              @Override
              public void onResponse(Call<Employee> call, Response<Employee> response) {
                System.out.println("Ответ получен.");
                Employee employee = response.body();
                System.out.println(employee);
                //populateListView(response.body());
              }

              @Override
              public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(EmployeeListActivity.this, "Failed to load employees", Toast.LENGTH_SHORT).show();
              }
            });
  }
  private void getEmployees() {
    RetrofitService retrofitService = new RetrofitService();
    EmployeeApi employeeApi = retrofitService.getRetrofit().create(EmployeeApi.class);

    employeeApi.getAllEmployees()
            .enqueue(new Callback<List<Employee>>() {
              @Override
              public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                System.out.println("Ответ получен.");
                List<Employee> list = response.body();
                for(Employee l : list)
                {
                  System.out.print(l);
                }

                //populateListView(response.body());
              }

              @Override
              public void onFailure(Call<List<Employee>> call, Throwable t) {
                Toast.makeText(EmployeeListActivity.this, "Failed to load employees", Toast.LENGTH_SHORT).show();
              }
            });
  }

  private void loadEmployees() {
    RetrofitService retrofitService = new RetrofitService();
    EmployeeApi employeeApi = retrofitService.getRetrofit().create(EmployeeApi.class);

    employeeApi.getEmployeesByFilter(employeeFilter)
        .enqueue(new Callback<List<Employee>>() {
          @Override
          public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
            System.out.println("Ответ получен.");
            populateListView(response.body());
          }

          @Override
          public void onFailure(Call<List<Employee>> call, Throwable t) {
            Toast.makeText(EmployeeListActivity.this, "Failed to load employees", Toast.LENGTH_SHORT).show();
          }
        });
  }

  private void populateListView(List<Employee> employeeList) {
    //System.out.println(employeeList.get(0).getFirstName());
    EmployeeAdapter employeeAdapter = new EmployeeAdapter(employeeList);
    recyclerView.setAdapter(employeeAdapter);
  }

/*  @Override
  protected void onResume() {
    super.onResume();
    loadEmployees();
  }*/
}
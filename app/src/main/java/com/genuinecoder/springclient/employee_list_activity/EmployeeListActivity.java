package com.genuinecoder.springclient.employee_list_activity;

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

import com.genuinecoder.springclient.R;
import com.genuinecoder.springclient.employee_list_activity.adapters.EmployeeAdapter;
import com.genuinecoder.springclient.employee_list_activity.adapters.EmployeeFilterAdapter;
import com.genuinecoder.springclient.employee_list_activity.adapters.EmployeeSortAdapter;
import com.genuinecoder.springclient.employee_list_activity.dto.EmployeeDTO;
import com.genuinecoder.springclient.employee_list_activity.mapper.EmployeeMapperImpl;
import com.genuinecoder.springclient.employee_list_activity.model.EmployeeCard;
import com.genuinecoder.springclient.employee_list_activity.model.EmployeeFilter;
import com.genuinecoder.springclient.employee_list_activity.enumerator.Priority;
import com.genuinecoder.springclient.reotrfit.EmployeeApi;
import com.genuinecoder.springclient.reotrfit.RetrofitService;

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

  private void loadEmployees() {
    RetrofitService retrofitService = new RetrofitService();
    EmployeeApi employeeApi = retrofitService.getRetrofit().create(EmployeeApi.class);

    employeeApi.getEmployeesByFilter(employeeFilter)
        .enqueue(new Callback<List<EmployeeDTO>>() {
          @Override
          public void onResponse(Call<List<EmployeeDTO>> call, Response<List<EmployeeDTO>> response) {
            System.out.println("Ответ получен.");
            EmployeeMapperImpl employeeMapper = new EmployeeMapperImpl();
            List<EmployeeDTO> employeeDTOList = response.body();
            List<EmployeeCard> employeeCardList = employeeMapper.dtoListToEmployeeList(employeeDTOList);
            populateListView(employeeCardList);
          }

          @Override
          public void onFailure(Call<List<EmployeeDTO>> call, Throwable t) {
            Toast.makeText(EmployeeListActivity.this, "Failed to load employees", Toast.LENGTH_SHORT).show();
          }
        });
  }

  private void populateListView(List<EmployeeCard> employeeList) {
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
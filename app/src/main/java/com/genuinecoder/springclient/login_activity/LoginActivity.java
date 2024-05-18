package com.genuinecoder.springclient.login_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.genuinecoder.springclient.R;
import com.genuinecoder.springclient.employee_list_activity.EmployeeListActivity;
import com.genuinecoder.springclient.employee_list_activity.adapters.EmployeeAdapter;
import com.genuinecoder.springclient.employee_list_activity.adapters.EmployeeFilterAdapter;
import com.genuinecoder.springclient.employee_list_activity.adapters.EmployeeSortAdapter;
import com.genuinecoder.springclient.employee_list_activity.dto.CustomerDTO;
import com.genuinecoder.springclient.employee_list_activity.dto.EmployeeDTO;
import com.genuinecoder.springclient.employee_list_activity.enumerator.Priority;
import com.genuinecoder.springclient.employee_list_activity.mapper.EmployeeMapperImpl;
import com.genuinecoder.springclient.employee_list_activity.model.EmployeeCard;
import com.genuinecoder.springclient.employee_list_activity.model.EmployeeFilter;
import com.genuinecoder.springclient.reotrfit.EmployeeApi;
import com.genuinecoder.springclient.reotrfit.RegistrationApi;
import com.genuinecoder.springclient.reotrfit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

  EditText login;
  EditText password;

  EditText name;
  EditText surname;
  Button loginButton;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.register);
    login = findViewById(R.id.editTextLogin);
    password = findViewById(R.id.editTextPassword);
    name = findViewById(R.id.editTextName);
    surname = findViewById(R.id.editTextSurname);
    loginButton = findViewById(R.id.buttonRegister);
    loginButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          CustomerDTO customer = new CustomerDTO();
          customer.setLogin(login.getText().toString());
          customer.setPassword(password.getText().toString());
          customer.setFirstName(name.getText().toString());
          customer.setLastName(surname.getText().toString());
          register(customer);

/*        if (login.getText().toString().equals("user") && password.getText().toString().equals("1234")) {
          Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
        } else {
          Toast.makeText(LoginActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
        }*/
      }
    });

  }

  private void toNextActivity()
  {
    Intent intent = new Intent(this, EmployeeListActivity.class);
    startActivity(intent);
  }
  private void register(CustomerDTO customer) {
    RetrofitService retrofitService = new RetrofitService();
    RegistrationApi registrationApi = retrofitService.getRetrofit().create(RegistrationApi.class);

    registrationApi.createCustomer(customer)
            .enqueue(new Callback<Void>() {
              @Override
              public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("Ответ получен.");
                toNextActivity();
              }

              @Override
              public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Введены некорректные данные.", Toast.LENGTH_SHORT).show();
              }
            });
  }
}
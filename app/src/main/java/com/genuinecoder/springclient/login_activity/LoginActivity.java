package com.genuinecoder.springclient.login_activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.genuinecoder.springclient.MyApplication;
import com.genuinecoder.springclient.R;
import com.genuinecoder.springclient.employee_list_activity.dto.CustomerDTO;
import com.genuinecoder.springclient.error.ErrorUtils;
import com.genuinecoder.springclient.error.ValidationErrorResponse;
import com.genuinecoder.springclient.error.Violation;
import com.genuinecoder.springclient.listener.TextChangedListener;
import com.genuinecoder.springclient.main_menu_activity.MainMenuActivity;
import com.genuinecoder.springclient.reotrfit.RegistrationApi;
import com.genuinecoder.springclient.reotrfit.RetrofitService;

import lombok.Setter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

  EditText login;
  EditText password;

  EditText name;
  EditText surname;
  Button loginButton;

  @Setter
  String cityMessage;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.register);
    login = findViewById(R.id.editTextLogin);
    password = findViewById(R.id.editTextPassword);
    name = findViewById(R.id.editTextName);
    surname = findViewById(R.id.editTextSurname);
    loginButton = findViewById(R.id.buttonRegister);

      Spinner spinnerCity = findViewById(R.id.spinnerInputCity);
      ArrayAdapter<CharSequence> adapterCity = ArrayAdapter.createFromResource(
              this,
              R.array.cities,
              android.R.layout.simple_spinner_item
      );
      adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      spinnerCity.setAdapter(adapterCity);


    login.addTextChangedListener(new TextChangedListener<EditText>(login)
    {
        @Override
        public void onTextChanged(EditText target, Editable s)
        {
            TextView errorView = findViewById(R.id.textView_login);
            errorView.setText("");
        }
    }
    );
      password.addTextChangedListener(new TextChangedListener<EditText>(password)
                                   {
                                       @Override
                                       public void onTextChanged(EditText target, Editable s)
                                       {
                                           TextView errorView = findViewById(R.id.textView_password);
                                           errorView.setText("");
                                       }
                                   }
      );

      name.addTextChangedListener(new TextChangedListener<EditText>(name)
                                   {
                                       @Override
                                       public void onTextChanged(EditText target, Editable s)
                                       {
                                           TextView errorView = findViewById(R.id.textView_firstName);
                                           errorView.setText("");
                                       }
                                   }
      );
      surname.addTextChangedListener(new TextChangedListener<EditText>(surname)
                                   {
                                       @Override
                                       public void onTextChanged(EditText target, Editable s)
                                       {
                                           TextView errorView = findViewById(R.id.textView_lastName);
                                           errorView.setText("");
                                       }
                                   }
      );

    loginButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          CustomerDTO customer = new CustomerDTO();
          customer.setLogin(login.getText().toString());
          customer.setPassword(password.getText().toString());
          customer.setFirstName(name.getText().toString());
          customer.setLastName(surname.getText().toString());
          customer.setAddressWalk(spinnerCity.getSelectedItem().toString());
          register(customer);
      }
    });
/*    GeoAPI geo = new GeoAPI();
    String ip = geo.receiveIp();
      System.out.println("В активити:" + ip);
    requestCityMessage("77.238.135.28");
      System.out.println("В активити сообщение:" + cityMessage);
      CityQuestion cityQuestion = new CityQuestion(cityMessage);
    FragmentManager manager = getSupportFragmentManager();
    cityQuestion.show(manager, "dialog");*/
  }

  private void toCompleteLogin(CustomerDTO user)
  {
      ((MyApplication) this.getApplication()).setUser(user);
      RetrofitService.setLogin(user.getLogin());
      RetrofitService.setPassword(user.getPassword());
    Intent intent = new Intent(this, MainMenuActivity.class);
    startActivity(intent);
  }
  private void register(CustomerDTO customer) {
    RetrofitService retrofitService = new RetrofitService();
    RegistrationApi registrationApi = retrofitService.getRetrofit().create(RegistrationApi.class);

    registrationApi.createCustomer(customer)
            .enqueue(new Callback<CustomerDTO>() {
              @Override
              public void onResponse(Call<CustomerDTO> call, Response<CustomerDTO> response) {

                  if(response.isSuccessful())
                  {
                      CustomerDTO user = response.body();
                      System.out.println("Успешно!!!");
                      toCompleteLogin(user);
                  }
                  else
                  {
                      System.out.println("Ошибка валидации!!!");
                      ValidationErrorResponse validationErrorResponse = ErrorUtils.parseValidationError(response);
                      displayValidationErrorList(validationErrorResponse);
                  }

              }

              @Override
              public void onFailure(Call<CustomerDTO> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Неопознанная ошибка!", Toast.LENGTH_SHORT).show();
              }
            });
  }

    private void requestCityMessage(String ip)
    {
        RetrofitService retrofitService = new RetrofitService();
        RegistrationApi registrationApi = retrofitService.getRetrofit().create(RegistrationApi.class);
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setAddressWalk(ip);
        registrationApi.getCityMessage(customerDTO)
                .enqueue(new Callback<CustomerDTO>() {
                    @Override
                    public void onResponse(@NonNull Call<CustomerDTO> call, @NonNull Response<CustomerDTO> response) {

                        if(response.isSuccessful())
                        {
                            CustomerDTO user = response.body();
                            System.out.println("Полученный 'адрес': " + user.getAddressWalk());
                            setCityMessage(user.getAddressWalk());
                        }
                        else
                        {
                            cityMessage = "Ошибка авторизации!";
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<CustomerDTO> call, @NonNull Throwable t) {}
                });
    }

  private void displayValidationErrorList(ValidationErrorResponse errorResponse)
  {
      for(Violation error : errorResponse.getViolations())
      {
            switch(error.getFieldName())
            {
                case "firstName":
                {
                    TextView view = findViewById(R.id.textView_firstName);
                    view.setText(error.getMessage());
                    break;
                }
                case "lastName":
                {
                    TextView view = findViewById(R.id.textView_lastName);
                    view.setText(error.getMessage());
                    break;
                }
                case "login":
                {
                    TextView view = findViewById(R.id.textView_login);
                    view.setText(error.getMessage());
                    break;
                }
                case "password":
                {
                    TextView view = findViewById(R.id.textView_password);
                    view.setText(error.getMessage());
                    break;
                }
            }
      }
  }

}
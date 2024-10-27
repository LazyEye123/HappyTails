package com.genuinecoder.springclient.greeting_screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.genuinecoder.springclient.MyApplication;
import com.genuinecoder.springclient.R;
import com.genuinecoder.springclient.employee_list_activity.dto.CustomerDTO;
import com.genuinecoder.springclient.login_activity.LoginActivity;
import com.genuinecoder.springclient.main_menu_activity.MainMenuActivity;
import com.genuinecoder.springclient.reotrfit.RegistrationApi;
import com.genuinecoder.springclient.reotrfit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstActivity extends AppCompatActivity
{
    EditText login;
    EditText password;

    TextView login_textView;
    Button loginButton;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login = findViewById(R.id.editTextLogin);
        password = findViewById(R.id.editTextPassword);
        registerButton = findViewById(R.id.buttonToRegister);
        loginButton = findViewById(R.id.buttonLogin);
        login_textView = findViewById(R.id.textView_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerDTO customer = new CustomerDTO();
                customer.setLogin(login.getText().toString());
                customer.setPassword(password.getText().toString());

                RetrofitService.setLogin(customer.getLogin());
                RetrofitService.setPassword(customer.getPassword());
                login(customer);

            }
        });
        registerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                toRegistration();
            }
        });


    }

    private void login(CustomerDTO customer) {
        RetrofitService retrofitService = new RetrofitService();
        RegistrationApi registrationApi = retrofitService.getRetrofit().create(RegistrationApi.class);

        registrationApi.getCityMessage(customer)
                .enqueue(new Callback<CustomerDTO>() {
                    @Override
                    public void onResponse(Call<CustomerDTO> call, Response<CustomerDTO> response) {

                        if(response.isSuccessful())
                        {
                            CustomerDTO user = response.body();
                            System.out.println("Успешный вход!");
                            toCompleteLogin(user);
                        }
                        else
                        {
                            System.out.println("Ошибка входа!");
                            login_textView.setText("Неверный логин или пароль.");
                        }

                    }

                    @Override
                    public void onFailure(Call<CustomerDTO> call, Throwable t) {
                        Toast.makeText(FirstActivity.this, "Неопознанная ошибка!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void toCompleteLogin(CustomerDTO user)
    {
        ((MyApplication) this.getApplication()).setUser(user);
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    private void toRegistration()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}

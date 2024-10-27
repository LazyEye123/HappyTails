package com.genuinecoder.springclient.login_activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class CityQuestion extends DialogFragment {

    String message;
    public CityQuestion(String message)
    {
        this.message = message;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String button1String = "Да";
        String button2String = "Нет";

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //builder.setTitle(title);  // заголовок
        builder.setMessage(message); // сообщение
        builder.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(getActivity(), "Вы сделали правильный выбор",
                        Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(getActivity(), "Возможно вы правы", Toast.LENGTH_LONG)
                        .show();
            }
        });
        builder.setCancelable(true);

        return builder.create();
    }
}

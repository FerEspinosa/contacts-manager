package com.curso.android.app.practica.contactsmanagerapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class MainActivityClickHandler {

    Context context;

    public MainActivityClickHandler(Context context) {
        this.context = context;
    }

    public void onFABClicked(View v) {
        Intent i = new Intent(v.getContext(), AddNewContactActivity.class);
        context.startActivity(i);
    }
}

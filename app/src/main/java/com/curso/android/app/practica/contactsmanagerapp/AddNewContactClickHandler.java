package com.curso.android.app.practica.contactsmanagerapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class AddNewContactClickHandler {
    Contacts contact;
    Context context;
    MyViewModel myViewModel;

    public AddNewContactClickHandler(Contacts contact, Context context, MyViewModel myViewModel) {
        this.contact = contact;
        this.context = context;
        this.myViewModel = myViewModel;
    }

    public void onSubmitBtnClicked(View view) {
        if (contact.getName() == null || contact.getEmail() == null) {
            Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            Intent i = new Intent(context, MainActivity.class);
//            i.putExtra("Name", contact.getName());
//            i.putExtra("Email", contact.getEmail());

            Contacts c = new Contacts(contact.getName(), contact.getEmail());

            myViewModel.addNewContact(c);

            context.startActivity(i);

        }
    }
}

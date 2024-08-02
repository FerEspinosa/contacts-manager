package com.curso.android.app.practica.contactsmanagerapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

    private Repository myRepository;

    // LiveData
    private LiveData<List<Contacts>> allContacts;

    public MyViewModel(@NonNull Application application, Repository myRepository) {
        super(application);
        this.myRepository = myRepository;
    }

    public LiveData<List<Contacts>> getAllContacts() {
        allContacts = myRepository.getAllContacts();
        return allContacts;
    }

    public void insert(Contacts contacts) {
        myRepository.insert(contacts);
    }

    public void delete(Contacts contacts) {
        myRepository.delete(contacts);
    }
}

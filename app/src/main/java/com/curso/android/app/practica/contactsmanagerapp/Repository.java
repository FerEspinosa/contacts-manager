package com.curso.android.app.practica.contactsmanagerapp;

import android.os.Handler;
import android.os.Looper;

import androidx.room.Delete;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    // The available data sources
    // - Room Database

    private final ContactDAO contactDAO;

    public Repository(ContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }

    // Methods in the ContactDAO
        public List<Contacts> getAllContacts() {
            return contactDAO.getAllContacts();
        }

        public void insert (Contacts contact) {
            contactDAO.insert(contact);
        }

        public void delete (Contacts contact) {
            contactDAO.delete(contact);
        }
}

package com.curso.android.app.practica.contactsmanagerapp;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

    public class Repository {
    // The available data sources
    // - Room Database

    private final ContactDAO contactDAO;
    ExecutorService executor;

    Handler handler;

    public Repository(Application application, ContactDAO contactDAO) {

        ContactDatabase contactDatabase = ContactDatabase.getInstance(application);
        this.contactDAO = contactDatabase.getContactDao();

        // used for background database operations
        executor= Executors.newSingleThreadExecutor();

        // used for updating the UI
        handler = new Handler(Looper.getMainLooper());

    }

    // Methods in the ContactDAO

        public void insert (Contacts contact) {

        /*  Next line would be executed in the UI thread
            contactDAO.insert(contact);
            That's why instead, we have to use the ExecutorService and Handler:
        */

        /*   I took next code to the constructor of the Repository class

            // used for background database operations
            ExecutorService executor= Executors.newSingleThreadExecutor();

            // used for updating the UI
            Handler handler = new Handler(Looper.getMainLooper());
        */
            // Runnable: Executing tasks on separate thread
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    contactDAO.insert(contact);
                }
            });
        }

        public void delete (Contacts contact) {

            executor.execute(new Runnable() {
                @Override
                public void run() {
                    contactDAO.delete(contact);
                }
            });
        }

        // Modified to return LiveData
        public LiveData<List<Contacts>> getAllContacts() {

            return contactDAO.getAllContacts();
            // in this case we will not use the ExecutorService and Handler
            // because we are using LiveData
            // and LiveData is already kind of asynchronous

            // it is necessary to modify the contactDAO class

        }
}

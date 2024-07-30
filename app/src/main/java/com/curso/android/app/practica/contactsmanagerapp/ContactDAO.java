package com.curso.android.app.practica.contactsmanagerapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDAO {

    @Insert
    void insert (Contacts Contact);

    @Delete
    void delete (Contacts Contact);

    // This was modified to return a LiveData object
    @Query ("SELECT * FROM contacts_table")
    LiveData<List<Contacts>> getAllContacts();
}

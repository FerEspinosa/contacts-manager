package com.curso.android.app.practica.contactsmanagerapp;

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

    @Query ("SELECT * FROM contacts_table")
    List<Contacts> getAllContacts();

}

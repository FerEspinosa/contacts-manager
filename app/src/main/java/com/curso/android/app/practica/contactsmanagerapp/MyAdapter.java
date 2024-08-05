package com.curso.android.app.practica.contactsmanagerapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.curso.android.app.practica.contactsmanagerapp.databinding.ContactListItemBinding;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ContactViewHolder> {

    private ArrayList <Contacts> contacts;

    public MyAdapter(ArrayList<Contacts> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // creating new View holders for items in recyclerView
        ContactListItemBinding contactListItemBinding =
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),
                    R.layout.contact_list_item,
                    parent,
                    false
                );

        return new ContactViewHolder(contactListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contacts contact = contacts.get(position);
        holder.contactListItemBinding.setContact(contact);
    }

    @Override
    public int getItemCount() {
        if (contacts == null) {
            return 0;
        } else {
            return contacts.size();
        }
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        private ContactListItemBinding contactListItemBinding;
        public ContactViewHolder(@NonNull ContactListItemBinding contactListItemBinding){
            super(contactListItemBinding.getRoot());
            this.contactListItemBinding = contactListItemBinding;
        }
    }

    public void setContacts(ArrayList<Contacts> contacts) {
        this.contacts = contacts;

        notifyDataSetChanged();
    }
}

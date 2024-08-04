package com.curso.android.app.practica.contactsmanagerapp;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.curso.android.app.practica.contactsmanagerapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Data source
    private ContactDatabase contactDatabase;
    private ArrayList<Contacts> contacts = new ArrayList<>();

    // Adapter
    private MyAdapter myAdapter;

    // Binding
    private ActivityMainBinding mainBinding;
    private MainActivityClickHandler handlers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Data binding

            // Initialize binding
            mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

            // Initialize and set click handler
            handlers = new MainActivityClickHandler(this);
            mainBinding.setClickHandler(handlers);


        // RecyclerView
        RecyclerView recyclerView = mainBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // Initialize (and set) adapter
        myAdapter = new MyAdapter(contacts);

        // Initialize database
        contactDatabase = ContactDatabase.getInstance(this);

        // View model
        MyViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        // Insert a new Contact (just for testing, later on we will create an activity to insert data)
        Contacts c1 = new Contacts("Pablo Ramirez", "p.ramirez@mail.com");
        viewModel.addNewContact(c1);

        // Loading Data from ROOM DB
        viewModel.getAllContacts().observe(this, new Observer<List<Contacts>>() {
                    @Override
                    public void onChanged(List<Contacts> contacts) {
                        for (Contacts c : contacts) {
                            Log.v("TAGY", c.getName());
                            contacts.add(c);
                        }
                        myAdapter.notifyDataSetChanged();
                    }
                });

        // linking adapter to recycler view
        recyclerView.setAdapter(myAdapter);

    }
}
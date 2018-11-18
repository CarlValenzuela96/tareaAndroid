package com.example.carl.tareaandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.carl.tareaandroid.Adapters.UserAdapter;
import com.example.carl.tareaandroid.Models.User;

import java.util.ArrayList;

public class UsersActivity extends AppCompatActivity {
    public RecyclerView users;
    public UserAdapter uAdapter;
    ArrayList<User> userList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users);
        Bundle extras = getIntent().getExtras();

        this.userList = (ArrayList<User>) extras.get("users");

        this.users = (RecyclerView)findViewById(R.id.users);
        this.users.setLayoutManager(new LinearLayoutManager(this));

        this.users.setHasFixedSize(true);
        this.uAdapter = new UserAdapter(this.userList);
        this.users.setAdapter(uAdapter);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}

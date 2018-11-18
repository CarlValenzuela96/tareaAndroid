package com.example.carl.tareaandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;


import com.example.carl.tareaandroid.Adapters.PostAdapter;
import com.example.carl.tareaandroid.Models.Post;
import com.example.carl.tareaandroid.Models.User;

import java.util.ArrayList;


public class PostsActivity extends AppCompatActivity {
    public RecyclerView posts;
    public PostAdapter pAdapter;
    ArrayList<Post> postList;
    ArrayList<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts);
        Bundle extras = getIntent().getExtras();

        postList = (ArrayList<Post>) extras.get("posts");
        userList = (ArrayList<User>) extras.get("users");

        this.posts = (RecyclerView)findViewById(R.id.posts);
        this.posts.setLayoutManager( new LinearLayoutManager(this));

        this.posts.setHasFixedSize(true);
        this.pAdapter = new PostAdapter(postList,userList);
        this.posts.setAdapter(pAdapter);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}

package com.example.carl.tareaandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.carl.tareaandroid.Adapters.GaleryAdapter;
import com.example.carl.tareaandroid.Models.Album;
import com.example.carl.tareaandroid.Models.Photo;

import java.util.ArrayList;

public class GaleryActivity extends AppCompatActivity {
    public RecyclerView photos;
    public GaleryAdapter gAdapter;
    ArrayList<Photo> photosList;
    ArrayList<Album> albumList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.galery);
        Bundle extras = getIntent().getExtras();
        this.photosList = (ArrayList<Photo>) extras.get("photos");
        this.albumList = (ArrayList<Album>) extras.get("albums");

        this.photos = (RecyclerView)findViewById(R.id.photos);
        this.photos.setLayoutManager( new LinearLayoutManager(this));

        this.photos.setHasFixedSize(true);
        this.gAdapter = new GaleryAdapter(this.photosList, this.albumList);
        this.photos.setAdapter(gAdapter);



        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }


}

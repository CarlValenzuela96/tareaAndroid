package com.example.carl.tareaandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.carl.tareaandroid.AsyncTasks.AlbumTask;
import com.example.carl.tareaandroid.AsyncTasks.PhotoTask;
import com.example.carl.tareaandroid.AsyncTasks.PostTask;
import com.example.carl.tareaandroid.AsyncTasks.UserTask;
import com.example.carl.tareaandroid.Models.Album;
import com.example.carl.tareaandroid.Models.Photo;
import com.example.carl.tareaandroid.Models.Post;
import com.example.carl.tareaandroid.Models.User;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    Button buttonPosts;
    Button buttonGalery;
    Button buttonSalir;
    Button buttonUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonPosts = findViewById(R.id.buttonPosts);
        buttonGalery = findViewById(R.id.buttonGalery);
        buttonSalir = findViewById(R.id.buttonSalir);
        buttonUsers = findViewById(R.id.buttonUsers);

        buttonPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    posts();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        buttonGalery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    galery();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        buttonUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    users();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        buttonSalir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        System.exit(0);
                    }
                });
    }

    /**
     * Metodo que obtiene los arraylist de usuarios y post para pararlos como parametros al metodo
     * intentPostActivity()
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void posts() throws ExecutionException, InterruptedException {
        PostTask pt = new PostTask(this,"https://jsonplaceholder.typicode.com/posts" );
        pt.execute();
        UserTask ut = new UserTask("https://jsonplaceholder.typicode.com/users");
        ut.execute();
        intentPostActivity(pt.get(),ut.get());
    }

    /**
     * Metodo que da inicio a la actividad PostActivity
     * @param p arrayList de fotos
     * @param u arrayList de usuarios
     */
    private void intentPostActivity(ArrayList<Post> p, ArrayList<User> u){
        Intent i = new Intent(this,PostsActivity.class);
        i.putExtra("posts", p);
        i.putExtra("users",u);
        startActivity(i);
        overridePendingTransition(R.anim.zoom_back_in,R.anim.zoom_back_out);
    }

    /**
     * Metodo que obtiene los arrayList de fotos y albunes para pasarlos como parametros al metodo
     * intentGaleryActivity()
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void galery() throws ExecutionException, InterruptedException {
        PhotoTask pt = new PhotoTask("https://jsonplaceholder.typicode.com/photos");
        pt.execute();
        AlbumTask at = new AlbumTask("https://jsonplaceholder.typicode.com/albums");
        at.execute();
        intentGaleryActivity(pt.get(),at.get());
    }

    /**
     * Metodo que da inicio a la actividad galeryActivity
     * @param photos arraylist de fotos
     * @param albums arraylist de albums
     */
    private void intentGaleryActivity(ArrayList<Photo> photos, ArrayList<Album> albums){
        Intent i = new Intent(this,GaleryActivity.class);
        i.putExtra("photos",photos);
        i.putExtra("albums",albums);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }

    /**
     * Metodo que obtiene arraylist de usuarios para pasarlo como parametro al metodo intentUserActivity()
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void users() throws ExecutionException, InterruptedException {
        UserTask ut = new UserTask("https://jsonplaceholder.typicode.com/users");
        ut.execute();
        intentUserActivity(ut.get());
    }

    /**
     * Metodo que da inicio a la actividad UsersActivity
     * @param users arrayList de usuarios
     */
    public void intentUserActivity(ArrayList<User> users){
        Intent i = new Intent(this,UsersActivity.class);
        i.putExtra("users",users);
        startActivity(i);
        overridePendingTransition(R.anim.right_in,R.anim.right_out);
    }
}

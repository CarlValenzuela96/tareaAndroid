package com.example.carl.tareaandroid.Adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carl.tareaandroid.Models.Album;
import com.example.carl.tareaandroid.Models.Photo;
import com.example.carl.tareaandroid.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class GaleryAdapter extends RecyclerView.Adapter<GaleryAdapter.RepositoryViewHolder>{
    int globalPosition;
    ArrayList<Photo> photos;
    ArrayList<Album> albums;

    public GaleryAdapter(ArrayList<Photo> photos, ArrayList<Album> albums) {
        this.photos = photos;
        this.albums = albums;
    }

    @Override
    public GaleryAdapter.RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item_list,null,false);

        return new RepositoryViewHolder(view);
    }

    /**
     * Metodo que busca album segun id especifica
     * @param idAlbum id del abum a buscar
     * @return album
     */
    public Album buscarAlbum(int idAlbum){
        Album album = null;
        for (int i=0;i<this.albums.size();i++){
            if (this.albums.get(i).getId()==idAlbum){
                album  = this.albums.get(i);
            }
        }
        return album;
    }

    @Override
    public void onBindViewHolder(GaleryAdapter.RepositoryViewHolder holder, int position) {
        try {
            holder.asignarDatos(this.photos.get(position), buscarAlbum(this.photos.get(position).getAlbunId()));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return this.photos.size();
    }

    public class RepositoryViewHolder extends RecyclerView.ViewHolder {

        ImageView idImage;
        TextView title;
        TextView album;

        public RepositoryViewHolder(View itemView) {
            super(itemView);
            idImage =itemView.findViewById(R.id.idImage);
            title=itemView.findViewById(R.id.title);
            album=itemView.findViewById(R.id.albumId);

        }

        /**
         * Metodo que cambia los valores de lo que se muestra en cada elemento del recyclerView galery
         * @param photo objeto photo
         * @param al objeto album
         * @throws ExecutionException
         * @throws InterruptedException
         */
        public void asignarDatos(Photo photo, Album al) throws ExecutionException, InterruptedException {
            ImageTask it = new ImageTask(photo.getUrl());
            it.execute();
            idImage.setImageBitmap(it.get());
            title.setText(photo.getTitle());
            album.setText("Album: "+al.getTitle());
        }
    }

    public class ImageTask extends AsyncTask<Void,Void,Bitmap> {
        private String link;

        public ImageTask(String link) {
            this.link = link;
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            Bitmap img = null;

            try {
                InputStream is = new URL(this.link).openStream();
                img = BitmapFactory.decodeStream(is);

            } catch (IOException e) {
                e.printStackTrace();
            }


            return img;
        }
    }
}


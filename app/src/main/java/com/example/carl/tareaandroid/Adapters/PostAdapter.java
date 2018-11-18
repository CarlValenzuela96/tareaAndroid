package com.example.carl.tareaandroid.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.carl.tareaandroid.Models.Post;
import com.example.carl.tareaandroid.Models.User;
import com.example.carl.tareaandroid.R;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.RepositoryViewHolder> {

    ArrayList<Post> posts;
    ArrayList<User> users;

    public PostAdapter(ArrayList<Post> posts, ArrayList<User> users) {
        this.users = users;
        this.posts = posts;
    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item_list,null,false);

        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {


        holder.asignarDatos(posts.get(position),buscarUsuario(posts.get(position).getUserId()));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    /**
     * Metodo que busca usuario segun su id
     * @param idUser
     * @return user
     */
    public User buscarUsuario(int idUser){
        User user = null;
        for (int i=0;i<this.users.size();i++){
            if (this.users.get(i).getId()==idUser){
                user  = this.users.get(i);
            }
        }
        return user;
    }
    public class RepositoryViewHolder extends RecyclerView.ViewHolder {

        TextView id;
        TextView idUser;
        TextView title;
        TextView body;

        public RepositoryViewHolder(View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.id);
            idUser=itemView.findViewById(R.id.idUser);
            body = itemView.findViewById(R.id.body);
            title = itemView.findViewById(R.id.title);
        }

        /**
         * Metodo que cambia los valores de lo que se muestra en cada elemento del recyclerView posts
         * @param post objeto post
         * @param u objeto usuario
         */
        public void asignarDatos(Post post, User u) {
            id.setText(String.valueOf(post.getId()));
            idUser.setText(u.getUsername());
            body.setText(post.getBody());
            title.setText(post.getTittle());
        }
    }
}

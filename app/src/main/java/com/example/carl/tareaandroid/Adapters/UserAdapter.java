package com.example.carl.tareaandroid.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.carl.tareaandroid.Models.User;
import com.example.carl.tareaandroid.R;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.RepositoryViewHolder> {

    ArrayList<User> user;

    public UserAdapter(ArrayList<User> user) {
        this.user = user;
    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item_list,null,false);

        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {
        holder.asignarDatos(this.user.get(position));
    }

    @Override
    public int getItemCount() {
        return this.user.size();
    }

    public class RepositoryViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView name;
        TextView username;
        TextView email;
        TextView phone;
        TextView website;

        public RepositoryViewHolder(View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.id);
            name=itemView.findViewById(R.id.name);
            username=itemView.findViewById(R.id.userName);
            email=itemView.findViewById(R.id.email);
            phone=itemView.findViewById(R.id.phone);


        }

        /**
         * Metodo que cambia los valores de lo que se muestra en cada elemento del recyclerView users
         * @param u ojeto usuario
         */
        public void asignarDatos(User u) {
            id.setText(String.valueOf(u.getId()));
            name.setText("Nombre: "+u.getName());
            username.setText("Nombre Usuario: "+u.getUsername());
            email.setText("Email: "+u.getEmail());
            phone.setText("Telefono: "+u.getPhone());
        }
    }
}

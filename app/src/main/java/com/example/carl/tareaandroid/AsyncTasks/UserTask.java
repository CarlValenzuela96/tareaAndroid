package com.example.carl.tareaandroid.AsyncTasks;

import android.os.AsyncTask;

import com.example.carl.tareaandroid.Models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class UserTask extends AsyncTask<Void,Void,ArrayList<User>> {
    private String linkrequestApi;
    public ArrayList<User> users = null;

    public UserTask(String linkrequestApi) {
        this.linkrequestApi = linkrequestApi;
    }


    /**
     * Se consume api de usuarios para obtener todos los usuarios del sistema
     * @param voids
     * @return ArrayList<User> retorna arraylist con todos los usuarios del sistema
     */
    @Override
    protected ArrayList<User> doInBackground(Void... voids) {
        StringBuilder json = new StringBuilder();

        JSONArray jsonArray;
        String wsURL = linkrequestApi;
        URL url;
        try {
            url = new URL(wsURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }

            jsonArray = new JSONArray(json.toString());
            this.users = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.optString("id");
                String name = jsonObject.optString("name");
                String username = jsonObject.optString("username");
                String email = jsonObject.optString("email");
                String phone = jsonObject.optString("phone");
                String website = jsonObject.optString("website");

                User u = new User(Integer.parseInt(id),name,username,email,phone,website);
                this.users.add(u);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this.users;
    }
}

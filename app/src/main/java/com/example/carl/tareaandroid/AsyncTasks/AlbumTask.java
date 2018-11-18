package com.example.carl.tareaandroid.AsyncTasks;

import android.os.AsyncTask;

import com.example.carl.tareaandroid.Models.Album;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class AlbumTask extends AsyncTask<Void,Void,ArrayList<Album>> {
    private String linkrequestApi;
    public ArrayList<Album> albums = null;

    public AlbumTask(String linkrequestApi) {
        this.linkrequestApi = linkrequestApi;
    }

    /**
     * Se consume api de albums para obtener todos los albums del sistema
     * @param voids
     * @return ArrayList<Album> retorna arraylist con todos los albums del sistema
     */
    @Override
    protected ArrayList<Album> doInBackground(Void... voids) {
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
            this.albums = new ArrayList<>();

            for (int i = 0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String userId = jsonObject.optString("userId");
                String id = jsonObject.optString("id");
                String title = jsonObject.optString("title");

                Album a = new Album(Integer.parseInt(userId),Integer.parseInt(id),title);
                this.albums.add(a);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return this.albums;
    }
}

package com.example.carl.tareaandroid.AsyncTasks;

import android.os.AsyncTask;

import com.example.carl.tareaandroid.Models.Photo;

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

public class PhotoTask extends AsyncTask<Void,Void,ArrayList<Photo>> {

    private String linkrequestApi;
    public ArrayList<Photo> photos = null;

    public PhotoTask(String linkrequestApi) {
        this.linkrequestApi = linkrequestApi;
    }

    /**
     * Se consume api de fotos para obtener todas los fotos del sistema
     * @param voids
     * @return ArrayList<Photo> retorna arraylist con 100 fotos del sistema
     */
    @Override
    protected ArrayList<Photo> doInBackground(Void... voids) {
        StringBuilder json = new StringBuilder();

        JSONArray jsonArray;
        String wsURL = linkrequestApi;
        URL url;

        try{
            url = new URL(wsURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }

            jsonArray = new JSONArray(json.toString());
            this.photos = new ArrayList<>();

            //se restringio a solo 100 fotos de 5000 para no sobrepasar capacidad de cache
            for (int i = 0; i < 100; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String albumId = jsonObject.optString("albumId");
                String id = jsonObject.optString("id");
                String title = jsonObject.optString("title");
                String urlP = jsonObject.optString("url");
                String thumbnailUrl = jsonObject.optString("thumbnailUrl");

                Photo p = new Photo(Integer.parseInt(albumId),Integer.parseInt(id),title,urlP,thumbnailUrl);
                this.photos.add(p);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return this.photos;
    }
}

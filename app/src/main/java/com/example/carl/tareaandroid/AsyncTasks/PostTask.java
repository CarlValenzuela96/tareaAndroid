package com.example.carl.tareaandroid.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;

import com.example.carl.tareaandroid.Models.Post;

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

public class PostTask extends AsyncTask<Void,Void,ArrayList<Post>> {

    private Context httpContext;
    public String linkrequestApi;
    public ArrayList<Post> posts;


    public PostTask(Context ctx, String linkApi) {
        this.httpContext=ctx;
        this.linkrequestApi=linkApi;

    }

    /**
     * Se consume api de posts para obtener todos los post del sistema
     * @param params
     * @return ArrayList<Post> retorna arraylist con todos los post del sistema
     */
    @Override
    protected ArrayList<Post> doInBackground(Void... params) {
        StringBuilder json = new StringBuilder();

        this.posts= null;
        JSONArray jsonArray;
        String wsURL = linkrequestApi;
        URL url;
        try{
            url = new URL(wsURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while((line = reader.readLine())!=null){
                json.append(line);
            }

            jsonArray = new JSONArray(json.toString());

            posts = new ArrayList<>();

            for(int i = 0;i<jsonArray.length();i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String userId=jsonObject.optString("userId");
                String id=jsonObject.optString("id");
                String title=jsonObject.optString("title");
                String body=jsonObject.optString("body");

                Post p = new Post(Integer.parseInt(userId),Integer.parseInt(id),title,body);
                posts.add(p);
            }

        }catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(ArrayList<Post> s) {
        super.onPostExecute(s);

    }



}

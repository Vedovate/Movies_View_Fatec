package com.example.vedovate.projetofilme;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Category> categories;
    private CategoryAdapter categoryAdapter;
    private ListView genreListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        categories = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(this, categories);
        genreListView = (ListView) findViewById(R.id.genreListView);
        genreListView.setAdapter(categoryAdapter);
        String text = "https://api.themoviedb.org/3/genre/movie/list?api_key=735b936618f64cb9fcbeffb11ebcc443&" + getString(R.string.language);
        new obterGeneros().execute(text);
    }



    class obterGeneros extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... enderecos) {
            try {
                URL url = new URL(enderecos[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String linha = null;
                final StringBuilder stringBuilder = new StringBuilder("");
                while ((linha = reader.readLine()) != null)
                    stringBuilder.append(linha);
                reader.close();
                return stringBuilder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String jsonS) {
            categories.clear();

            try {
                JSONObject json = new JSONObject(jsonS);
                JSONArray list = json.getJSONArray("genres");
                for (int i = 0; i < 18; i++) {
                    JSONObject category = list.getJSONObject(i);
                    String nameGenre = category.getString("name");

                    Category genre = new Category(nameGenre);
                    categories.add(genre);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Toast.makeText(getApplicationContext(), categories.toString(), Toast.LENGTH_SHORT).show();

        }

    }

}
package com.example.android.newsofindia;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.android.newsofindia.NewsModel;

public class News_page extends AppCompatActivity {
    private News_Adapter adapter;

    private static final String USGS_REQUEST_URL ="https://newsapi.org/v2/top-headlines?country=in&apiKey=5e3d75d93256402cb07f1ba676658c01";
//            "https://newsapi.org/v2/top-headlines?country=us&apiKey=5e3d75d93256402cb07f1ba676658c01";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_page);
        getSupportActionBar().hide();

        TextView emptyTextView = findViewById(R.id.empty_view);
        TextView nameText = findViewById(R.id.profilename_text_in);
        nameText.setText(getIntent().getStringExtra("toShow"));
        String ab = getIntent().getStringExtra("id_ref");
        nameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(News_page.this,Profile_page.class);
                i.putExtra("id",ab);
                startActivity(i);
            }
        });

        List<NewsModel> newsModelList = new ArrayList<>();

         adapter = new News_Adapter(News_page.this,newsModelList);
      ListView rcv = findViewById(R.id.recycler_Lay);
        rcv.setAdapter(adapter);

        rcv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NewsModel getDetails = adapter.getItem(i);
                Uri earthquakeUri = Uri.parse(getDetails.getContent_url());

                Intent website = new Intent(Intent.ACTION_VIEW,earthquakeUri);
                startActivity(website);
            }
        });

        rcv.setEmptyView(emptyTextView);

        ConnectivityManager connectivityManager =  (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkState =  connectivityManager.getActiveNetworkInfo();

//        check networkState should not be null, if then it will be a null pointer exception and app will crashes

        if (networkState!=null && networkState.isConnected()){
            Log.e("News_page1","url is"+USGS_REQUEST_URL);
            EarthquakeAsyncTask task = new EarthquakeAsyncTask();
            task.execute(USGS_REQUEST_URL);
        }
        else {
            ProgressBar progressBar = findViewById(R.id.progressBar);
            progressBar.setVisibility(View.GONE);
            emptyTextView.setText("No Internet Connection");
        }

    }


    private class EarthquakeAsyncTask extends AsyncTask<String, Void, List<NewsModel>> {

        @Override
        protected List<NewsModel> doInBackground(String... urls) {

            if (urls.length < 1 && urls[0] == null) {
                Log.v("News_page3", "urls is " + urls[0]);
                return null;
            }
            Log.v("News_page2", "urls is " + urls[0]);


            List<NewsModel> newsModelList = NewsQuery.fetchNewsData(urls[0]);
            return newsModelList;
        }

        @Override
        protected void onPostExecute(List<NewsModel> data) {

            ProgressBar progressBar = findViewById(R.id.progressBar);
            progressBar.setVisibility(View.GONE);
            TextView emptyTextView = findViewById(R.id.empty_view);
            String whenEmpty = " No News Available";
            emptyTextView.setText(whenEmpty);

            adapter.clear();


            if (data != null && !data.isEmpty()) {
                adapter.addAll(data);
            }else emptyTextView.setText("hello");
            }
        }


    }

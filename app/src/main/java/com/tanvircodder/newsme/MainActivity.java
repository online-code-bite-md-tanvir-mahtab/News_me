package com.tanvircodder.newsme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.tanvircodder.newsme.internet.JsonParsing;
import com.tanvircodder.newsme.internet.UriParseAndHttpConnection;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String[]> {
//    nwo i need to create the loader..//
    private static final int LOADER_VERSION_ID = 0;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    //    now i am going to create the recycerView ..//
    RecyclerView mRecyclerView;
    private AdapterList mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new AdapterList(this);
        mRecyclerView.setAdapter(mAdapter);
        getSupportLoaderManager().initLoader(LOADER_VERSION_ID,null,this);
    }

    @NonNull
    @Override
    public Loader<String[]> onCreateLoader(int id, @Nullable Bundle args) {
        return new AsyncTaskLoader<String[]>(this) {
            private String[] data;

            @Override
            protected void onStartLoading() {
                if (data != null) {
                    deleverResult(data);
                } else {
                    forceLoad();
                }
            }

            @Nullable
            @Override
            public String[] loadInBackground() {
               try {
                   URL url = UriParseAndHttpConnection.buildUri();
                   Log.e(LOG_TAG,"The uri : " + url);
                   String uriRequest = UriParseAndHttpConnection
                           .URlHttpRequest(url);
                   String[] jsonParse  = JsonParsing
                           .keyOfJsonParsing(MainActivity.this,uriRequest);
                   Log.e(LOG_TAG,"The json : " + uriRequest);
                   return jsonParse;
               } catch (Exception e) {
                   e.printStackTrace();
                   return null;
               }
            }

            private void deleverResult(String[] data) {
                this.data = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String[]> loader, String[] data) {
        mAdapter.swapData(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String[]> loader) {
        mAdapter.swapData(null);
    }
}
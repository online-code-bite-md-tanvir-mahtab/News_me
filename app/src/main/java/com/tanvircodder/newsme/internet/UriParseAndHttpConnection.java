package com.tanvircodder.newsme.internet;

import android.net.Uri;
import android.util.Log;

import com.tanvircodder.newsme.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
public class UriParseAndHttpConnection {
    private static final String LOG_TAG = UriParseAndHttpConnection.class.getSimpleName();
    // : 3/4/2021 this is the api from the news api :
    //  /*http://newsapi.org/v2/top-headlines?country=in&apiKey=b41e3c219279487aba73531b5104e26a*/
    private static final String BASE_NEWS_URI = "http://newsapi.org/v2/top-headlines";
    private static final String URI_API_KEY = "apiKey";
    private static final String URI_CONTRY_PARAMETER = "country";
    private static final String COUNTRY_VALUE = "in";
//    the key value of the uri...//
    private static final String API_KEY_VALUE = "b41e3c219279487aba73531b5104e26a";
    public static final URL buildUri (){
        final Uri buildurl = Uri.parse(BASE_NEWS_URI).buildUpon()
                .appendQueryParameter(URI_CONTRY_PARAMETER,COUNTRY_VALUE)
                .appendQueryParameter(URI_API_KEY,API_KEY_VALUE)
                .build();
//        now i am going to crate the instance of the URL class.../
        URL newUrl = null;
        try{
            newUrl = new URL(buildurl.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return newUrl;
    }
/*    now i need to get the access the http request that will grnat sme
    * the access to get the json so we are going to create an mathod that
    * will hold all the logic for it*/
    public static final String URlHttpRequest(URL urlConnection) throws IOException {
        HttpURLConnection openConnection =(HttpURLConnection)
                urlConnection.openConnection();
        openConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:33.0) Gecko/20100101 Firefox/33.0");
        openConnection.setDoInput(true);
        try {
            InputStream inputStream = openConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");
            boolean isNext = scanner.hasNext();
            if (isNext){
                return scanner.next();
            }else {
                return null;
            }
        }catch (IOException e){
            Log.e(LOG_TAG,"There is some problem" + urlConnection);
        }finally {
//            nwo we need to disconnect the httpconnectioan...//
            openConnection.disconnect();
        }
        return null;
    }
}

package com.tanvircodder.newsme.internet;

import android.util.Log;

import com.tanvircodder.newsme.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonParsing {
    private static final String LOG_TAG  = JsonParsing.class.getSimpleName();
//    to store the data of the json object we are going to declare the array string variable..//
    private static List<String> jsonData= null;
//    now i am going to declare all the key of the json..///
    private static final String NEWS_ARTICAL_JSON_OBJECT = "articles";
    private static final String NEWS_OBJECT_TITLE = "title";
    private static final String NEWS_OBJECT_DESCRIPTION = "description";
    private static final String NEWS_OBJECT_IMAGE = "urlToImage";
    private static final String NEWS_OBJECT_SOURCE = "source";
    private static final String NEWS_OBJECT_KEY_NAME = "name";
    private static final String NEWS_OBJECT_PUBLISH_AT = "publishedAt";
    /*now to get thef json we need to do some logic */
    public static List<String> keyOfJsonParsing(MainActivity context, String httprequest) throws JSONException {
//        nwo i am going to parse the jsonObeject..///
        JSONObject jsonObject = new JSONObject(httprequest);
//        now I need to deal with the jsonArraya..//
        JSONArray jsonArray = jsonObject.getJSONArray(NEWS_ARTICAL_JSON_OBJECT);
        jsonData = new ArrayList<>();
        for (int i =0;i<jsonArray.length();i++){
            JSONObject newJsonObject = jsonArray.getJSONObject(i);
            JSONObject sourceJson = newJsonObject.getJSONObject(NEWS_OBJECT_SOURCE);
            String source = sourceJson.getString(NEWS_OBJECT_KEY_NAME);
            String objectAuthor = newJsonObject.getString(NEWS_OBJECT_TITLE);
            String description = newJsonObject.getString(NEWS_OBJECT_DESCRIPTION);
//            now i am going to accesss the image of the json..///
            String jsonImage = newJsonObject.getString(NEWS_OBJECT_IMAGE);
            String jsonPublishAt = newJsonObject.getString(NEWS_OBJECT_PUBLISH_AT);
            Log.e(LOG_TAG,"The Source : " + source);
            Log.e(LOG_TAG,"The published time : " + jsonPublishAt);
//            jsonData[i] =jsonImage + objectAuthor + source + jsonPublishAt ;
            jsonData.add(jsonImage + objectAuthor + source + jsonPublishAt);
        }
        return jsonData;
    }
}

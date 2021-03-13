package com.tanvircodder.newsme.internet;

import com.tanvircodder.newsme.MainActivity;
import com.tanvircodder.newsme.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonParsing {
    private static final String LOG_TAG  = JsonParsing.class.getSimpleName();
//    to store the data of the json object we are going to declare the array string variable..//
    private static List<Util> jsonData= null;
//    now i am going to declare all the key of the json..///
    private static final String NEWS_ARTICAL_JSON_OBJECT = "articles";
    private static final String NEWS_OBJECT_TITLE = "title";
    private static final String NEWS_OBJECT_DESCRIPTION = "description";
    private static final String NEWS_OBJECT_IMAGE = "urlToImage";
    private static final String NEWS_OBJECT_SOURCE = "source";
    private static final String NEWS_OBJECT_KEY_NAME = "name";
    private static final String NEWS_OBJECT_PUBLISH_AT = "publishedAt";
    private static Util mUrilClass;
    /*now to get thef json we need to do some logic */
    public static List<Util> keyOfJsonParsing(MainActivity context, String httprequest) throws JSONException {
//        nwo i am going to parse the jsonObeject..///
        JSONObject jsonObject = new JSONObject(httprequest);
//        now I need to deal with the jsonArraya..//
        JSONArray jsonArray = jsonObject.getJSONArray(NEWS_ARTICAL_JSON_OBJECT);
        jsonData = new ArrayList<>();
        for (int i =0;i<jsonArray.length();i++){
//            nwo i am going to create a new jsonObject..///
            JSONObject neJsonObject = jsonArray.getJSONObject(i);
            JSONObject sourceJsonObject = neJsonObject.getJSONObject(NEWS_OBJECT_SOURCE);
//            nwo i am going to get the source key from the json  and store them inside the utl class..///
            String source = sourceJsonObject.getString(NEWS_OBJECT_KEY_NAME);
//            nwo i have getherd the title svalue form the json..//
            String title = neJsonObject.getString(NEWS_OBJECT_TITLE);
//            nwo i am going to get the description...//
            String content = neJsonObject.getString(NEWS_OBJECT_DESCRIPTION);
//            nwo i have parsed the iamge form the url...
            String image = neJsonObject.getString(NEWS_OBJECT_IMAGE);
//            this is for the published time..//
            String published  = neJsonObject.getString(NEWS_OBJECT_PUBLISH_AT);
            jsonData.add(new Util(title,source,image,published));
        }
        return jsonData;
    }
}

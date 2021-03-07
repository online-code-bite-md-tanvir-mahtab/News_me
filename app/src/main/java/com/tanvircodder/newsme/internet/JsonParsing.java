package com.tanvircodder.newsme.internet;

import com.tanvircodder.newsme.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParsing {
//    to store the data of the json object we are going to declare the array string variable..//
    private static String[] jsonData= null;
//    now i am going to declare all the key of the json..///
    private static final String NEWS_ARTICAL_JSON_OBJECT = "articles";
    private static final String NEWS_OBJECT_TITLE = "title";
    private static final String NEWS_OBJECT_DESCRIPTION = "description";
    /*now to get thef json we need to do some logic */
    public static String[] keyOfJsonParsing(MainActivity context,String httprequest) throws JSONException {
//        nwo i am going to parse the jsonObeject..///
        JSONObject jsonObject = new JSONObject(httprequest);
//        now I need to deal with the jsonArraya..//
        JSONArray jsonArray = jsonObject.getJSONArray(NEWS_ARTICAL_JSON_OBJECT);
        jsonData = new String[jsonArray.length()];
        for (int i =0;i<jsonArray.length();i++){
            JSONObject newJsonObject = jsonArray.getJSONObject(i);
            String objectAuthor = newJsonObject.getString(NEWS_OBJECT_TITLE);
            String description = newJsonObject.getString(NEWS_OBJECT_DESCRIPTION);
            jsonData[i] = objectAuthor + "" + description;
        }
        return jsonData;
    }
}

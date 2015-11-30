package com.subliminals_central.subliminalscentral.subliminalscentral.YouTube;


import android.os.AsyncTask;

import com.subliminals_central.subliminalscentral.subliminalscentral.Models.EventsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AsyncGetChannelVideos extends AsyncTask<String, Void, List<EventsModel>> {

    @Override
    protected List<EventsModel> doInBackground(String... params) {
        List<EventsModel> eventsList = new ArrayList<>();

        try {
            JSONObject json = new JSONObject(params[0]);
            JSONArray jsonArray = json.getJSONArray("items");

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                JSONObject snippet = jsonObject.getJSONObject("snippet");

                String title = snippet.getString("title");
                String thumbnailUrl = snippet.getJSONObject("thumbnails").getJSONObject("medium").getString("url");
                String videoId = jsonObject.getJSONObject("id").getString("videoId");

                    eventsList.add(new EventsModel(title, videoId, thumbnailUrl));
            }
        } catch(JSONException|RuntimeException ex){
            ex.printStackTrace();
            eventsList.add(new EventsModel("No internet connection!", "none", "no"));
        }

        return eventsList;
    }

}

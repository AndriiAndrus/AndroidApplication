package com.subliminals_central.subliminalscentral.subliminalscentral.YouTube;

import com.subliminals_central.subliminalscentral.subliminalscentral.Models.EventsModel;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class VideosGetter {

    private static final String API_KEY = "AIzaSyAFMoRTYkAYFz0oqlR4EZAOxEWdfV3PT3I";
    private  static final String CHANNEL_ID = "UCcLisqGjrOkpa5OB_PqTaSA";
    private static final String CLIENT_URL = "https://www.googleapis.com/youtube/v3/search?key="+API_KEY+"&channelId="+CHANNEL_ID+"&part=snippet,id&order=date&maxResults=50";

    public List<EventsModel> getSearchList(String query){
        AsyncGetChannelVideos videos = new AsyncGetChannelVideos();
        AsyncVideoGet getIt = new AsyncVideoGet();

        getIt.execute(CLIENT_URL+"&q="+query);

        List<EventsModel> nwList = null;
        try {

            String apiResponce = getIt.get();
            videos.execute(apiResponce);
            nwList = videos.get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return nwList;
    }

    public List<EventsModel> getList(){
        AsyncGetChannelVideos videos = new AsyncGetChannelVideos();
        AsyncVideoGet getIt = new AsyncVideoGet();

        getIt.execute(CLIENT_URL);

        List<EventsModel> nwList = null;
        try {

            String apiResponce = getIt.get();
            videos.execute(apiResponce);
            nwList = videos.get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return nwList;
    }

}

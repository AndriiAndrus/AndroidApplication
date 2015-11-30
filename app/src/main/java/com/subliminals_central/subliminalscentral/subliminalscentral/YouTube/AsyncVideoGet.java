package com.subliminals_central.subliminalscentral.subliminalscentral.YouTube;

import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class AsyncVideoGet extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        String toReturn = null;
        Request request = new Request.Builder()
                .url(params[0])
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = null;
        try {
            response = client.newCall(request).execute();
           toReturn = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}

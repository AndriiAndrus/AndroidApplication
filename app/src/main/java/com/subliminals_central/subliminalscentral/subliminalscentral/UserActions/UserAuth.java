package com.subliminals_central.subliminalscentral.subliminalscentral.UserActions;

import com.subliminals_central.subliminalscentral.subliminalscentral.YouTube.AsyncVideoGet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class UserAuth {

    private static AsyncVideoGet getResponce;

    public static String userUpdateScores(int Current){
        String isOk = "";

        String email = UserData.getUserEmail();
        String url = UserData.WEB_URL + UserData.API_UPD + "&login=" + email + "&points=" + Current;

        getResponce = new AsyncVideoGet();
        getResponce.execute(url);
        String responce = null;
        try {
            responce = getResponce.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        JSONObject json = null;
        try {
            json = new JSONObject(responce);
            isOk = "" + json.getInt("code");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return isOk;
    }

    public static String[] userAuth(String email, String passwd){
        String[] code = new String[]{"", ""};

        String url = UserData.WEB_URL + UserData.API_LOGIN + "&login=" + email + "&pass=" + passwd;

        getResponce = new AsyncVideoGet();
        getResponce.execute(url);

        String responce = null;

        try {
            responce = getResponce.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        JSONObject json = null;
        try {
            json = new JSONObject(responce);
            code[0] = "" + json.getInt("code");
            code[1] = json.getString("points");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return code;
    }

    public static String userRegistration(String email, String pass){
        String isOk = "";

String url = UserData.WEB_URL + UserData.API_REG + "&login=" + email + "&pass=" + pass;

        getResponce = new AsyncVideoGet();
        getResponce.execute(url);
        String responce = null;
        try {
            responce = getResponce.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        JSONObject json = null;
        try {
            json = new JSONObject(responce);
            isOk = "" + json.getInt("code");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return isOk;
    }

}

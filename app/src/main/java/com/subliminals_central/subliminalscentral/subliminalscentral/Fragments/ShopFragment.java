package com.subliminals_central.subliminalscentral.subliminalscentral.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.subliminals_central.subliminalscentral.subliminalscentral.R;

public class ShopFragment extends Fragment {

private static WebView myWebView;
    private static int LAYOUT = R.layout.fragment_shop;
    private View view;

    public void SetMeLater(){
            String uri = "https://sellfy.com/SubliminalsCentral";
            myWebView = (WebView) view.findViewById(R.id.webView);
            WebSettings webSettings = myWebView.getSettings();
            myWebView.setWebViewClient(new WebViewClient());
            webSettings.setJavaScriptEnabled(true);
            myWebView.loadUrl(uri);
    }

    @Override
    public void onStart() {
        super.onStart();
        SetMeLater();
    }

    public static ShopFragment getInstance(){
            Bundle args = new Bundle();
        ShopFragment fragment = new ShopFragment();
            fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
     //   SetMeLater();
        return view;
    }

}

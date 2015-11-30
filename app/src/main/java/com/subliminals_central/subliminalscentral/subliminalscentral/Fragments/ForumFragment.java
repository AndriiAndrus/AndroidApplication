package com.subliminals_central.subliminalscentral.subliminalscentral.Fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.subliminals_central.subliminalscentral.subliminalscentral.R;
import com.subliminals_central.subliminalscentral.subliminalscentral.StaticUrls;

public class ForumFragment extends Fragment {

    private static int LAYOUT = R.layout.fragment_forum;
    private View view;

    public void SetMeLater(){
            String uri = StaticUrls.FORUM_URL;
            WebView myWebView = (WebView) view.findViewById(R.id.webView);
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

    public static ForumFragment getInstance(){
        Bundle args = new Bundle();
        ForumFragment fragment = new ForumFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        return view;
    }
}
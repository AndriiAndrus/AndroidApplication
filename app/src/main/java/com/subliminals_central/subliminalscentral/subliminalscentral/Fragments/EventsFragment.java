package com.subliminals_central.subliminalscentral.subliminalscentral.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.subliminals_central.subliminalscentral.subliminalscentral.Adapters.EventsListAdapter;
import com.subliminals_central.subliminalscentral.subliminalscentral.Models.EventsModel;
import com.subliminals_central.subliminalscentral.subliminalscentral.R;
import com.subliminals_central.subliminalscentral.subliminalscentral.YouTube.VideosGetter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventsFragment extends Fragment {

    private static int LAYOUT = R.layout.events_fragment;
    private View view;
    private ListView listView;
    private EventsListAdapter adapter;
    VideosGetter getData;

    public void SetMeLater(){
        getData = new VideosGetter();
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.imageButton);
        listView = (ListView) view.findViewById(R.id.listView);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit = (EditText) view.findViewById(R.id.editText);
                startSearch(edit.getText().toString());
            }
        });
        adapter = new EventsListAdapter(getContext(), getData.getList());
        listView.setAdapter(adapter);
    }

    private void startSearch(String query) {
        EventsListAdapter search = new EventsListAdapter(getContext(), getData.getSearchList(query));
        listView.setAdapter(search);
    }

    @Override
    public void onStart() {
        super.onStart();
            SetMeLater();
    }

    public static EventsFragment getInstance(){
        Bundle args = new Bundle();
        EventsFragment fragment = new EventsFragment();
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

package com.subliminals_central.subliminalscentral.subliminalscentral.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.youtube.player.YouTubePlayer;
import com.subliminals_central.subliminalscentral.subliminalscentral.Models.EventsModel;
import com.subliminals_central.subliminalscentral.subliminalscentral.R;
import com.subliminals_central.subliminalscentral.subliminalscentral.YouTube.YouTubePlayerActivity;
import com.thefinestartist.ytpa.enums.Orientation;

import java.util.List;

public class EventsListAdapter extends BaseAdapter {

    private List<EventsModel> events;
    private LayoutInflater layoutInflater;
    private Context context;

    public EventsListAdapter(Context context, List<EventsModel> events) {
        this.events = events;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

if(view == null){
  view = layoutInflater.inflate(R.layout.event_list_item, parent, false);
}
        final EventsModel eventModel = (EventsModel) getItem(position);

        Uri uri = Uri.parse(eventModel.getEventUrl());
        SimpleDraweeView draweeView = (SimpleDraweeView) view.findViewById(R.id.my_image_view);
        draweeView.setImageURI(uri);
        draweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, YouTubePlayerActivity.class);
                intent.putExtra(YouTubePlayerActivity.EXTRA_VIDEO_ID, eventModel.getEventThumb());
                intent.putExtra(YouTubePlayerActivity.EXTRA_PLAYER_STYLE, YouTubePlayer.PlayerStyle.DEFAULT);
                intent.putExtra(YouTubePlayerActivity.EXTRA_ORIENTATION, Orientation.AUTO);
                intent.putExtra(YouTubePlayerActivity.EXTRA_SHOW_AUDIO_UI, true);
                intent.putExtra(YouTubePlayerActivity.EXTRA_HANDLE_ERROR, true);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        TextView title = (TextView) view.findViewById(R.id.textView2);
        title.setText(eventModel.getEventTitle());

        return view;
    }
}

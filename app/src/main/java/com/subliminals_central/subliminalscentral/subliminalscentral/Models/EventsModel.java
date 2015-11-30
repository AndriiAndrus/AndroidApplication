package com.subliminals_central.subliminalscentral.subliminalscentral.Models;

import java.util.Date;

public class EventsModel {

    private String eventTitle;
    private String eventThumb;
    private String eventUrl;

    public EventsModel(String eventTitle, String eventThumb, String eventUrl) {
        this.eventTitle = eventTitle;
        this.eventThumb = eventThumb;
        this.eventUrl = eventUrl;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventThumb(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventThumb() {
        return eventThumb;
    }

    public void setEventStatus(String eventStatus) {
        this.eventThumb = eventStatus;
    }

    public String getEventUrl() {
        return eventUrl;
    }

    public void setEventUrl(String eventUrl) {
        this.eventUrl = eventUrl;
    }

}

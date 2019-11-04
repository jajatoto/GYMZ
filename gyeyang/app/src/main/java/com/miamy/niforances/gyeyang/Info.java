package com.miamy.niforances.gyeyang;

import java.io.Serializable;

public class Info implements Serializable {

    private String title;
    private String subtitle;
    private String content;
    private String rental_location_lat;
    private String rental_location_lon;
    private String user;

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getContent() {
        return content;
    }

    public String getRental_location_lat() {
        return rental_location_lat;
    }

    public String getRental_location_lon() {
        return rental_location_lon;
    }
}

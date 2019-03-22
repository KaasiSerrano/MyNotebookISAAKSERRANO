package com.example.jsontime;

import android.widget.ListView;

import java.util.List;

public class ImageEvent {

        public final String imageURL;
        public final List<String> imageUrls;

    /**
     *
     * @param message Single random image Url
     * @param imageUrls List of all Image urls
     */


    public ImageEvent(String message, List<String> imageUrls) {
            this.imageURL = message;
            this.imageUrls = imageUrls;
        }
    }
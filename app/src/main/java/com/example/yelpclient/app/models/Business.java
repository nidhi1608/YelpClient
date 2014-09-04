package com.example.yelpclient.app.models;


import java.util.List;

public class Business {
    private String name;
    private String image_url;
    private String rating_img_url_large;
    private String review_count;
    private Location location;
    private List<String[]> categories;

    public String getName() {
        return name;
    }

    public void setName(String name) { name = name; }

    public String getImageUrl() {
        return image_url;
    }

    public void setImageUrl(String image_url) {
        this.image_url = image_url;
    }

    public String getRating_img_url_large() {
        return rating_img_url_large;
    }

    public void setRating_img_url_large(String rating_img_url) {
        this.rating_img_url_large = rating_img_url;
    }

    public String getReview_count() {
        return review_count;
    }

    public void setReview_count(String review_count) {
        this.review_count = review_count;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getCategories() {
        String strCategories = "";
        for(String[] category : categories) {
            strCategories += category[0] + ", ";
        }
        strCategories = strCategories.substring(0, strCategories.length()-2);
        if(strCategories.length() > 39) {
            strCategories = strCategories.substring(0, 38) + "...";
        }
        return strCategories;
    }

    public void setCategories(List<String[]> categories) {
        this.categories = categories;
    }
}

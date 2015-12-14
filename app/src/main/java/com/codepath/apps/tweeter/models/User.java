package com.codepath.apps.tweeter.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by asmurthy on 12/13/15.
 */
public class User {

    //list
    private String name;
    private long uid;
    private String screenName;

    public String getName() {
        return name;
    }

    public long getUid() {
        return uid;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    private String profileImageUrl;


    public static User fromJSON(JSONObject json){
        User u = new User();
        try {
            u.name = json.getString("name");
            u.uid = json.getLong("id");
            u.screenName = json.getString("screen_name");
            u.profileImageUrl = json.getString("profile_image_url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return u;

    }
}

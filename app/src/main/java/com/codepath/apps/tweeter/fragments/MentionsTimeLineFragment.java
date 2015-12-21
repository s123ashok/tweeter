package com.codepath.apps.tweeter.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.codepath.apps.tweeter.TweetsArrayAdapter;
import com.codepath.apps.tweeter.TwitterApp;
import com.codepath.apps.tweeter.TwitterClient;
import com.codepath.apps.tweeter.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

public class MentionsTimeLineFragment extends TweetsListFragment {

    private TwitterClient client;
    private long max_id;
    private long since_id;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApp.getRestClient();

        since_id = (long)1;
        max_id = (long)0;
        populateTimeline();

    }

    private void populateTimeline() {

        client.getMentionsTimeline(new JsonHttpResponseHandler() {
            // success

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.d("DEBUG", response.toString());
                // JSON comes in here
                // deserialize JSON

                // Create model and them to adapter

                // Load model into list view

                TweetsArrayAdapter aTweets = getTweetsArrayAdapter();
//                ArrayList<Tweet> tweets = getTweets();

                aTweets.addAll(Tweet.fromJSONArray(response));
                Log.d("DEBUG", aTweets.toString());
//                int lastTweet = tweets.size() - 1;
//                max_id = tweets.get(lastTweet).getUid();
//                //since_id = tweets.get(0).getUid();
//                since_id = 0;
            }


            //failure

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("DEBUG", errorResponse.toString());
            }
        });

    }

    public void customLoadMoreDataFromApi(int page) {
        populateTimeline();
    }
}

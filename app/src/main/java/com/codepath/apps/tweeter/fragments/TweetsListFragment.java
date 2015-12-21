package com.codepath.apps.tweeter.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.codepath.apps.tweeter.EndlessScrollListener;
import com.codepath.apps.tweeter.ProfileActivity;
import com.codepath.apps.tweeter.R;
import com.codepath.apps.tweeter.TweetsArrayAdapter;
import com.codepath.apps.tweeter.models.Tweet;
import com.codepath.apps.tweeter.models.User;

import java.util.ArrayList;

/**
 * Created by asmurthy on 12/19/15.
 */
public abstract class TweetsListFragment extends Fragment {


    private ArrayList<Tweet> tweets;
    private TweetsArrayAdapter aTweets;
    private ListView lvTweets;


    // Inflation logic

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tweets_list,container,false);

        lvTweets = (ListView)v.findViewById(R.id.lvTweets);
        lvTweets.setAdapter(aTweets);

        lvTweets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user = ((Tweet)parent.getItemAtPosition(position)).getUser();
                String userName = user.getScreenName();
                Intent i = new Intent(view.getContext(), ProfileActivity.class);
                i.putExtra("screen_name",userName);
                i.putExtra("other",true);
                startActivity(i);
            }
        });
        // Attach the listener to the AdapterView onCreate
        lvTweets.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to your AdapterView
                customLoadMoreDataFromApi(page);
                // or customLoadMoreDataFromApi(totalItemsCount);
                return true; // ONLY if more data is actually being loaded; false otherwise.
            }
        });
        return v;
    }

    // Creation life cycle

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create arraylist
        tweets = new ArrayList<>();
        aTweets = new TweetsArrayAdapter(getActivity(), tweets);
    }

    public TweetsArrayAdapter getTweetsArrayAdapter(){
        return aTweets;
    }

    public ArrayList<Tweet> getTweets(){
        return tweets;
    }

    public abstract void customLoadMoreDataFromApi(int page);


}

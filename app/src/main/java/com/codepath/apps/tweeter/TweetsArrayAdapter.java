package com.codepath.apps.tweeter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.tweeter.models.Tweet;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by asmurthy on 12/13/15.
 */

// Taking Tweets object and truning them into view - list
public class TweetsArrayAdapter extends ArrayAdapter<Tweet>{
    public TweetsArrayAdapter(Context context, List<Tweet> tweets) {
        super(context, 0, tweets);
    }
    // override and use custom template
    // ToDo ViewHolder pattern

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get tweet
        Tweet tweet = getItem(position);
        // find/inflate the template
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tweet, parent,false);
        }
        //subviews to fill with data in the template
        ImageView ivProfileImage = (ImageView)convertView.findViewById(R.id.ivProfileImage);
        TextView tvUserName = (TextView)convertView.findViewById(R.id.tvUserName);
        TextView tvBody = (TextView)convertView.findViewById(R.id.tvBody);
        TextView tvTime = (TextView)convertView.findViewById(R.id.tvTime);

        // populate data
        tvUserName.setText(tweet.getUser().getScreenName());
        tvBody.setText(tweet.getBody());
        tvTime.setText(tweet.getCreatedAt());
        ivProfileImage.setImageResource(android.R.color.transparent); // reset the image
        Picasso.with(getContext()).load(tweet.getUser().getProfileImageUrl()).into(ivProfileImage);

        return convertView;
    }
}

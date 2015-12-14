package com.codepath.apps.tweeter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

public class ComposeActivity extends AppCompatActivity {

    private EditText etTweet;
    private Button btTweet;
    private TwitterClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        etTweet = (EditText)findViewById(R.id.etTweet);
        btTweet = (Button)findViewById(R.id.btTweet);
        client = TwitterApp.getRestClient();
    }

    public void postTweet(View v){
        updateStatus();
        Intent i = new Intent(this, TimelineActivity.class);
        startActivity(i);
    }

    private void updateStatus() {
        client.postCompose(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                //,"Tweet posted successfully",Toast.LENGTH_SHORT).show();
                //finishActivity(1);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject jsonObject) {
                //finishActivity(0);
            }
        }, etTweet.getText().toString());

    }

}

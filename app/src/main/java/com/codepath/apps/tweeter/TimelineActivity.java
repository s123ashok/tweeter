package com.codepath.apps.tweeter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;
import com.codepath.apps.tweeter.fragments.HomeTimeLineFragment;
import com.codepath.apps.tweeter.fragments.MentionsTimeLineFragment;

public class TimelineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Get viewPager
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        //Set adapter
        viewPager.setAdapter(new TweetsPagerAdapter(getSupportFragmentManager()));
        //find pager sliding tabs adn attach
        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabStrip.setViewPager(viewPager);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.timemenu, menu);
        return true;
    }

    public void invokeCompose(MenuItem v){
        Intent i = new Intent(this, ComposeActivity.class);
        startActivity(i);
    }

    public  void invokeProfile(MenuItem v){
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }

    public class TweetsPagerAdapter extends FragmentPagerAdapter {

        private  String tabTitles[] = {"Home", "Mentions"};

        public TweetsPagerAdapter (FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0)
                return new HomeTimeLineFragment();
            else if(position == 1)
                return  new MentionsTimeLineFragment();
            else return  null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }
    }


}

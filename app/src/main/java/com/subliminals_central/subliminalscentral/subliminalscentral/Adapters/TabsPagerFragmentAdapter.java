package com.subliminals_central.subliminalscentral.subliminalscentral.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.subliminals_central.subliminalscentral.subliminalscentral.Fragments.EventsFragment;
import com.subliminals_central.subliminalscentral.subliminalscentral.Fragments.ForumFragment;
import com.subliminals_central.subliminalscentral.subliminalscentral.Fragments.ShopFragment;
import com.subliminals_central.subliminalscentral.subliminalscentral.Fragments.WebsiteFragment;

public class TabsPagerFragmentAdapter extends FragmentPagerAdapter {

    private String[] tabs;

    public TabsPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
        tabs = new String[]{
                "YouTube",
                "Forum",
                "Website",
                "Shop"
        };
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                return EventsFragment.getInstance();
            case 1:
                return ForumFragment.getInstance();
            case 2:
                return WebsiteFragment.getInstance();
            case 3:
                return ShopFragment.getInstance();
        }

        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}

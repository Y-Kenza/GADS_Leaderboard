package com.kenzadev.gads_leaderboard.ui;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabAdapter extends FragmentPagerAdapter {

    private static final String[] TAB_NAMES = new String[]{"Learning Leaders", "Skill IQ Leaders"};
    private final Context mContext;

    TabAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new LearningLeadersFragment();
            case 1:
                return new SkillIQLeadersFragment( );
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_NAMES[position];
    }
    @Override
    public int getCount() {
        return 2;
    }
}

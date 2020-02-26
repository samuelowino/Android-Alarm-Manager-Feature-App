package org.kazitek.androidalarm_labs.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import org.kazitek.androidalarm_labs.fragments.NonRepatingAlarmFragment;
import org.kazitek.androidalarm_labs.fragments.RepeatingAlarmFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new NonRepatingAlarmFragment();
            case 1:
                return new RepeatingAlarmFragment();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Non Repeating Alarm";
            case 1:
                return "Repeating Alarm";
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}

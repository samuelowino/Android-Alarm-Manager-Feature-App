package org.kazitek.androidalarm_labs.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import org.kazitek.androidalarm_labs.fragments.NonRepeatingAlarmFragment;
import org.kazitek.androidalarm_labs.fragments.RepeatingAlarmFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private NonRepeatingAlarmFragment nonRepeatingAlarmFragment;
    private RepeatingAlarmFragment repeatingAlarmFragment;

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior,
                            NonRepeatingAlarmFragment nonRepeatingAlarmFragment,
                            RepeatingAlarmFragment repeatingAlarmFragment) {
        super(fm, behavior);

        this.nonRepeatingAlarmFragment = nonRepeatingAlarmFragment;
        this.repeatingAlarmFragment = repeatingAlarmFragment;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return nonRepeatingAlarmFragment;
            case 1:
                return repeatingAlarmFragment;
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

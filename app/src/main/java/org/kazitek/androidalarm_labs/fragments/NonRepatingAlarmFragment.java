package org.kazitek.androidalarm_labs.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.kazitek.androidalarm_labs.R;

public class NonRepatingAlarmFragment extends Fragment {

    private Button fireAtButton;
    private Button setAlarmButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_non_repating_alarm,container,false);
        fireAtButton = view.findViewById(R.id.non_repating_alarm_time_button);
        setAlarmButton = view.findViewById(R.id.non_repating_set_alarm_button);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}

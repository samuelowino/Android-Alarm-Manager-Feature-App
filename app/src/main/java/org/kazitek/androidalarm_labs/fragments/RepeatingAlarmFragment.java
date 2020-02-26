package org.kazitek.androidalarm_labs.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.kazitek.androidalarm_labs.R;

public class RepeatingAlarmFragment extends Fragment {

    private Button startAtButton;
    private Spinner repeatingSpinner;
    private Button setAlarmButton;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repeating_alarm,container,false);
        startAtButton = view.findViewById(R.id.start_at_button);
        repeatingSpinner = view.findViewById(R.id.seconds_select_spinner);
        setAlarmButton = view.findViewById(R.id.repeating_set_button);
        return view;
    }
}

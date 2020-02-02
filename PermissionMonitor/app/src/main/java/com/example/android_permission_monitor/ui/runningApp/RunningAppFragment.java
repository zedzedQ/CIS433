package com.example.android_permission_monitor.ui.runningApp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.android_permission_monitor.R;

public class RunningAppFragment extends Fragment {

    private RunningAppViewModel runningAppViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        runningAppViewModel =
                ViewModelProviders.of(this).get(RunningAppViewModel.class);
        View root = inflater.inflate(R.layout.fragment_running_app, container, false);
        final TextView textView = root.findViewById(R.id.text_share);
        runningAppViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
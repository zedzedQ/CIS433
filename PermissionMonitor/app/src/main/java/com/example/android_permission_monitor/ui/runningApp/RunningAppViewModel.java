package com.example.android_permission_monitor.ui.runningApp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RunningAppViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RunningAppViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is RunningApp fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
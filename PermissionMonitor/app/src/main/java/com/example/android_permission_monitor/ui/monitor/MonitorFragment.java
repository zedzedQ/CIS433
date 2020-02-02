package com.example.android_permission_monitor.ui.monitor;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.android_permission_monitor.DisplayPermissionActivity;
import com.example.android_permission_monitor.MainActivity;
import com.example.android_permission_monitor.R;

import java.util.ArrayList;
import java.util.List;

public class MonitorFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_monitor, container, false);
        ArrayList<String> ArrayOfNames = onGetAllAppNames();
//        ArrayOfNames.add("WeChat");
//        ArrayOfNames.add("Keep");
//        ArrayOfNames.add("Sina");
//        ArrayOfNames.add("Facebook");
        final String TAG = "MyActivity";


        for (int i = 0; i < ArrayOfNames.size(); i++) {
            Log.i(TAG, "MyClass.getView() — get item number " + ArrayOfNames.get(i));
        }

        for (int i = 0; i < ArrayOfNames.size(); i++) {

            final Button myButton = new Button(root.getContext());
            myButton.setText(ArrayOfNames.get(i));
            myButton.setId(i + 1);
            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Intent intent = new Intent();
                    intent.setClass(getActivity(), DisplayPermissionActivity.class);
                    startActivity(intent);

                }
            });

            myButton.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            myButton.setTextSize(18);
            myButton.setPadding(20, 0, 20, 0);

            LinearLayout linearlayout = (LinearLayout) root.findViewById(R.id.btnholder);
            linearlayout.setOrientation(LinearLayout.VERTICAL);

            LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            buttonParams.setMargins(0, 0, 0, 10);

            linearlayout.addView(myButton, buttonParams);
        }

        return root;
    }
    public ArrayList onGetAllAppNames() {
        final PackageManager pm = getActivity().getPackageManager();

        List<PackageInfo> packages = pm.getInstalledPackages(PackageManager.GET_META_DATA);
        ArrayList<String> appNames = new ArrayList<String>();

        for (PackageInfo packageInfo : packages) {
            appNames.add(packageInfo.applicationInfo.loadLabel(getActivity().getPackageManager()).toString());
            Log.d(null, "App name:" + packageInfo.applicationInfo.loadLabel(getActivity().getPackageManager()).toString());

            // Log.d(null,"Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName));
        }

        return appNames;



    }
}
package com.example.android_permission_monitor.ui.monitor;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

import java.util.*;

public class MonitorFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_monitor, container, false);
        final ArrayList<String> ArrayOfNames = onGetAllAppPackageNames();
        final String TAG = "MyActivity";


        for (int i = 0; i < ArrayOfNames.size() / 2; i++) {
            Log.i(TAG, "MyClass.getView() — get item number " + ArrayOfNames.get(i));
        }

        for (int i = 0; i < ArrayOfNames.size() / 2; i++) {

            final Button myButton = new Button(root.getContext());
            final ImageView myIcon = new ImageView(root.getContext());


            myButton.setText(ArrayOfNames.get(2*i));
            myButton.setId(i + 1);

            final String curPackageName = ArrayOfNames.get(2*i+1);
            final String curAppName = ArrayOfNames.get(2*i);
            try {
                final Drawable icon = getActivity().getPackageManager().getApplicationIcon(curPackageName);
                myIcon.setImageDrawable(icon);
            } catch(PackageManager.NameNotFoundException e) {

                myIcon.setImageDrawable(null);
            }



            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Intent intent = new Intent();
                    intent.putExtra("PACKAGE_NAME", curPackageName);
                    intent.putExtra("APP_NAME", curAppName);
                    intent.setClass(getActivity(), DisplayPermissionActivity.class);
                    startActivity(intent);

                }
            });

            myButton.setBackgroundColor(Color.WHITE);
            myButton.setTextSize(18);
            myButton.setPadding(20, 0, 20, 0);

            LinearLayout linearlayout = (LinearLayout) root.findViewById(R.id.btnholder);
            linearlayout.setOrientation(LinearLayout.VERTICAL);

            LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            buttonParams.setMargins(0, 0, 0, 10);

            linearlayout.addView(myIcon);
            linearlayout.addView(myButton, buttonParams);

        }

        return root;
    }

    public ArrayList onGetAllAppPackageNames() {
        final PackageManager pm = getActivity().getPackageManager();

        List<PackageInfo> packages = pm.getInstalledPackages(PackageManager.GET_META_DATA);
        ArrayList<String> names = new ArrayList<String>();

        for (PackageInfo packageInfo : packages) {
            String curAppName = packageInfo.applicationInfo.loadLabel(getActivity().getPackageManager()).toString();
            String curPackageName = packageInfo.packageName;
            if (!isInWhilteList(curAppName)) {
                names.add(curAppName);
                names.add(curPackageName);
            }

            Log.d(null, "App name:" + packageInfo.applicationInfo.loadLabel(getActivity().getPackageManager()).toString());

            // Log.d(null,"Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName));
        }
        return names;
    }




    ArrayList<String> whiteList = new ArrayList<String>(
            Arrays.asList("com.android.cts.priv.ctsshim",
                    "Corner cutout",
                    "Android Services Library",
                    "Double cutout",
                    "Phone and Messaging Storage",
                    "Dynamic System Updates",
                    "Calendar Storage",
                    "Media Storage",
                    "Square",
                    "Google One Time Init",
                    "Android Shared Library",
                    "Gestural Navigation Bar",
                    "Cinnamon",
                    "Rounded",
                    "Files",
                    "External Storage",
                    "Device Policy",
                    "HTML Viewer",
                    "Companion Device Manager",
                    "MmsService",
                    "Android Permissions",
                    "Download Manager",
                    "Messages",
                    "Sounds",
                    "Rounded",
                    "Pixel Wallpapers 18",
                    "Circular",
                    "ConfigUpdater",
                    "com.google.android.overlay.googlewebview",
                    "Time Zone Updater",
                    "Downloads",
                    "license checker",
                    "PacProcessor",
                    "Sim App Dialog",
                    "Tall cutout",
                    "Certificate Installer",
                    "Black",
                    "com.android.carrierconfig",
                    "Green",
                    "Ocean",
                    "Space",
                    "3 Button Navigation Bar",
                    "Android System",
                    "Contacts",
                    "Camera",
                    "Rounded",
                    "Google Indic Keyboard",
                    "Android Q Easter Egg",
                    "MTP Host",
                    "Nfc Service",
                    "com.android.ons",
                    "SIM Toolkit",
                    "com.android.backupconfirm",
                    "Clock",
                    "2 Button Navigation Bar",
                    "Intent Filter Verification Service",
                    "OsuLogin",
                    "Settings Suggestions",
                    "Gestural Navigation Bar",
                    "Permission controller",
                    "Android Setup",
                    "Settings Storage",
                    "com.android.sharedstoragebackup",
                    "Android System Angle",
                    "Google Play Music",
                    "Print Spooler",
                    "Filled",
                    "Basic Daydreams",
                    "SecureElementApplication",
                    "Input Devices",
                    "Default Print Service",
                    "CaptivePortalLogin",
                    "Circular",
                    "Drive",
                    "Module Metadata",
                    "Emu01 display cutout",
                    "Markup",
                    "Emergency alerts",
                    "Android System WebView",
                    "Teardrop",
                    "NetworkStack",
                    "Call Management",
                    "Google Contacts Sync",
                    "Key Chain",
                    "com.google.android.overlay.googleconfig",
                    "com.android.service.ims.RcsServiceApp",
                    "Phone",
                    "Filled",
                    "Package installer",
                    "Google Play services",
                    "Google Services Framework",
                    "Carrier Services",
                    "Google Text-to-speech Engine",
                    "Live data wallpapers",
                    "com.google.android.overlay.permissioncontroller",
                    "com.google.android.overlay.emulatorconfig",
                    "Call Log Backup/Restore",
                    "Google Partner Setup",
                    "Safety & regulatory manual",
                    "Google Wallpaper Images",
                    "com.android.localtransport",
                    "OemDmTrigger",
                    "Carrier Setup",
                    "Hide",
                    "apnlib",
                    "com.google.android.overlay.pixelconfig2017",
                    "com.google.android.overlay.pixelconfig2018",
                    "ConnMO",
                    "VZW_Multicast_MW",
                    "com.quicinc.cne.CNEService.CNEServiceApp",
                    "DiagnosticsTool",
                    "SIM manager",
                    "com.android.simappdialog.auto_generated_rro_product__",
                    "com.android.safetyregulatoryinfo.auto_generated_rro_product__",
                    "uceShimService",
                    "com.verizon.obdm_permissions",
                    "com.android.systemui.auto_generated_rro_product__",
                    "Device configuration",
                    "com.qualcomm.qti.telephonyservice",
                    "Emergency Information",
                    "Google Play Services for AR",
                    "Android Accessibility Suite",
                    "Device setup",
                    "com.qti.qualcomm.datastatusnotification",
                    "X Google enrollment",
                    "Pixel Setup",
                    "com.google.android.grilservice",
                    "com.android.systemui.auto_generated_rro_vendor__",
                    "org.codeaurora.ims",
                    "Device Personalization Services",
                    "Carrier Settings",
                    "SprintDM",
                    "Duo",
                    "Pixel Ambient Services",
                    "com.qualcomm.qcrilmsgtunnel",
                    "AppDirectedSMS",
                    "com.android.nfc.auto_generated_rro_product__",
                    "SIM Manager",
                    "OK Google enrollment",
                    "com.android.sdm.plugins.diagmon",
                    "com.android.providers.settings.auto_generated_rro_product__",
                    "Playground: Deprecated Pack",
                    "Digital Wellbeing",
                    "Live Transcribe",
                    "Sound Amplifier",
                    "Pixel Tips",
                    "com.google.android.documentsui.theme.pixel",
                    "com.android.bluetooth.auto_generated_rro_vendor__",
                    "com.android.providers.settings.auto_generated_rro_vendor__",
                    "android.telephony.overlay.cmcc",
                    "Carrier App Logging",
                    "com.android.server.telecom.auto_generated_rro_product__",
                    "com.android.bips.auto_generated_rro_product__",
                    "Tags",
                    "Pixel Launcher",
                    "CarrierDefaultApp",
                    "RilConfig",
                    "ProxyHandler",
                    "com.google.SSRestartDetector",
                    "Retail Demo Mode",
                    "Arbutus Slab / Source Sans Pro",
                    "Rubik / Rubik",
                    "com.android.providers.contacts.auto_generated_rro_product__",
                    "Market Feedback Agent",
                    "Print Service Recommendation Service",
                    "Work Setup",
                    "com.android.sdm.plugins.dcmo",
                    "com.android.providers.partnerbookmarks",
                    "Presence",
                    "Live Wallpaper Picker",
                    "com.android.providers.telephony.auto_generated_rro_product__",
                    "com.android.phone.auto_generated_rro_vendor__",
                    "Squircle",
                    "Translate",
                    "com.android.server.NetworkPermissionConfig",
                    "Bookmark Provider",
                    "com.google.android.overlay.pixelconfigcommon",
                    "Settings",
                    "Data Transfer Tool",
                    "Android Auto",
                    "D-MAT",
                    "Connectivity Health Services",
                    "Device Health Services",
                    "Tethering Entitlement",
                    "Carrier Provisioning Service",
                    "Pixel Stand",
                    "Home",
                    "com.android.cts.ctsshim",
                    "com.google.android.apps.wearables.maestro.companion.MaestroApplication",
                    "Hidden Menu",
                    "com.android.nfc.auto_generated_rro_vendor__",
                    "Secure UI Service",
                    "VpnDialogs",
                    "Styles & wallpapers",
                    "Phone Services",
                    "Google VR Services",
                    "Shell",
                    "com.android.wallpaperbackup",
                    "Android_Permission_Monitor",
                    "Blocked Numbers Storage",
                    "User Dictionary",
                    "Playground",
                    "Emergency information",
                    "Google Location History",
                    "Fused Location",
                    "Orchid",
                    "System UI",
                    "com.google.android.hardwareinfo",
                    "Zilla Slab / Lato",
                    "MusicFX",
                    "Pixel Themes",
                    "Bluetooth MIDI Service",
                    "ConfDialer",
                    "com.google.android.timezone.data",
                    "LTE Broadcast Manager",
                    "Dock Updater",
                    "System Tracing",
                    "Google Support Services",
                    "vzw_msdc_api",
                    "com.android.phone.auto_generated_rro_product__",
                    "android.auto_generated_rro_product__",
                    "Pixel 3 Game Driver",
                    "Google Connectivity Services",
                    "Bluetooth",
                    "com.qualcomm.timeservice",
                    "com.qualcomm.atfwd",
                    "com.qualcomm.embms",
                    "Contacts Storage",
                    "Rounded Rectangle",
                    "com.android.connectivity.metrics.App",
                    "com.verizon.llkagent",
                    "Vzw OMA Trigger",
                    "Gboard",
                    "Smart Storage",
                    "android.auto_generated_rro_vendor__",
                    "com.google.android.overlay.pixelconfig2019midyear",
                    "Factory OTA Mode"
                    ));

    // return true if in whiteList, else false
    public boolean isInWhilteList(String input) {
        for (String str: whiteList) {
            if (str.equals(input)) {
                return true;
            }
        }
        return false;
    }
}
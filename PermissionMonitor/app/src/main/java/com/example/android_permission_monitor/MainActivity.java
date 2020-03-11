package com.example.android_permission_monitor;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.android_permission_monitor.ui.monitor.MonitorFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_monitor, R.id.nav_runningApp, R.id.nav_feedback)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public ArrayList onGetAllPackageNames(View view) {
        final PackageManager pm = getPackageManager();

        List<PackageInfo> packages = pm.getInstalledPackages(PackageManager.GET_META_DATA);
        ArrayList<String> packageNames = new ArrayList<String>();

        for (PackageInfo packageInfo : packages) {
            packageNames.add(packageInfo.packageName);
            Log.d(null, "Installed package :" + packageInfo.packageName);

            // Log.d(null,"Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName));
        }

        return packageNames;

    }

    public HashMap onGetAllPermissions(View view){
        final PackageManager pm = getPackageManager();
        List<PackageInfo> packages = pm.getInstalledPackages(PackageManager.GET_META_DATA);
        HashMap<String, Integer> permissionCount = new HashMap<>();

        for (PackageInfo packageInfo : packages) {
            String curAppName = packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
            String curPackageName = packageInfo.packageName;

            if (!isInWhilteList(curAppName)) {
                try {
                    PackageInfo pi = pm.getPackageInfo(curPackageName, PackageManager.GET_PERMISSIONS);
                    String [] curPermissions = pi.requestedPermissions;
                    if (curPermissions != null){
                        Log.d(null, "yeah");
                        for (String permission : curPermissions){
                            if (permissionCount.containsKey(permission)){
                                Integer curCount = permissionCount.get(permission);
                                permissionCount.put(permission, curCount+1);
                            } else {
                                permissionCount.put(permission, 1);
                            }
                        }
                    }

                }
                catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }

        for (String permission : permissionCount.keySet()){
            Log.d(null, "permission:" + permission + "  count:" + permissionCount.get(permission));
        }

        return permissionCount;
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

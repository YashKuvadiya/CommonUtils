package com.pocket.money.myapplication;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.alpha.common.utility.CommonImageInflate;
import com.alpha.common.utility.CommonToolbar;
import com.alpha.common.utility.CommonUtilities;

public class MainActivity extends AppCompatActivity {

    CommonImageInflate commonImageInflate, commonImage;
    CommonToolbar commonToolbar;
    private BroadcastReceiver onDownloadComplete;
    private long downloadID;
    private DownloadManager downloadManager;
    private IntentFilter intentFilterActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CommonUtilities.appThemeStatusBar(MainActivity.this, R.color.black, true);
        setContentView(R.layout.activity_main);

//        commonImageInflate = new CommonImageInflate(MainActivity.this);
//        commonToolbar = new CommonToolbar(MainActivity.this);

        commonToolbar = findViewById(R.id.commonToolbarCommon);
//        commonToolbar.

//        commonImage.InflateImage(MainActivity.this,"https://img.freepik.com/free-vector/website-setup-concept-landing-page-template_23-2148292726.jpg?t=st=1725086644~exp=1725090244~hmac=cae0e4c611cde9d8dbc6ae72ac5fa477579698564036c6df4cc162de376543b3&w=1380");

//        commonToolbar.TbSetPointsText("2000");

        CommonUtilities.CommonLogger.getInstance().e("CurrentDateStandard", "Date: " + CommonUtilities.CurrentDateStandard());
        CommonUtilities.CommonLogger.getInstance().e("CurrentDateShort", "Date: " + CommonUtilities.CurrentDateShort());
        CommonUtilities.CommonLogger.getInstance().e("CurrentDateFullDate", "Date: " + CommonUtilities.CurrentDateFullDate());
        CommonUtilities.CommonLogger.getInstance().e("CurrentDateUsa", "Date: " + CommonUtilities.CurrentDateUsa());
        CommonUtilities.CommonLogger.getInstance().e("CurrentDateRegular", "Date: " + CommonUtilities.CurrentDateRegular());
        CommonUtilities.CommonLogger.getInstance().e("CurrentAndroidID", "ID: " + CommonUtilities.GetAndroidId(MainActivity.this));
        CommonUtilities.CommonLogger.getInstance().e("CurrentInternet", "Internet: " + CommonUtilities.InternetAvailable(MainActivity.this));
        CommonUtilities.CommonLogger.getInstance().e("CurrentDeveloperMode", "DeveloperMode: " + CommonUtilities.isDeveloperModeEnabled(MainActivity.this));

        CommonUtilities.downloadAndInstallAPK(MainActivity.this, "https://egameeapp.com/Api/AppDownload/testing.apk", "testing", "1.0.1", R.drawable.ic_launcher_background, new CommonUtilities.DownloadCallback() {
            @Override
            public void onProgress(int progress) {
                CommonUtilities.CommonLogger.getInstance().e("DownloadApk", "Progress: " + progress);
            }

            @Override
            public void onDownloadComplete() {
                CommonUtilities.CommonLogger.getInstance().e("DownloadApk", "DownloadCompleted: ");
            }

            @Override
            public void onInstallStarted() {
                CommonUtilities.CommonLogger.getInstance().e("DownloadApk", "InstallStarted: ");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        registerBroadcast();
    }
//    private void registerBroadcast() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            registerReceiver(intentFilterActivities,RECEIVER_NOT_EXPORTED);
//        } else {
//            registerReceiver(appOpenAddLoadedBroadcast, intentFilterActivities);
//        }
//    }

//    public void registerBroadcast() {
//        Log.e("registerBroadcast","Entered");
//        Log.e("registerBroadcast","onDownloadComplete: " + onDownloadComplete);
//        if (onDownloadComplete == null) {
//            onDownloadComplete = new BroadcastReceiver() {
//                @Override
//                public void onReceive(Context context, Intent intent) {
//                    Log.e("registerBroadcast","onDownloadCompleteContext: " + context);
//                    long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
//                    Log.e("registerBroadcast","onDownloadCompleteId: " + id);
//                    Log.e("registerBroadcast","onDownloadCompleteDownloadID: " + downloadID);
//                    if (downloadID == id) {
//                        Uri uri = downloadManager.getUriForDownloadedFile(downloadID);
//                        Log.e("registerBroadcast","onDownloadCompleteUri: " + uri);
//                        if (uri != null) {
//                            Log.e("AppUpdate", "uri: " + uri);
//                            CommonUtilities.installAPK();
//                        }
//                    }
//                }
//            };
//        }
//    }
//    private void unRegisterReceivers() {
//        if (onDownloadComplete != null) {
//            unregisterReceiver(onDownloadComplete);
//            onDownloadComplete = null;
//        }
//    }
//    @Override
//    protected void onStop() {
//        super.onStop();
//        try {
//            if (isFinishing()) {
//                unRegisterReceivers();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onBackPressed() {
//        unRegisterReceivers();
//        super.onBackPressed();
//        System.exit(0);
//    }
}
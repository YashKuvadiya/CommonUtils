package com.pocket.money.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alpha.common.utility.CommonImageInflate;
import com.alpha.common.utility.CommonUtilities;

public class MainActivity extends AppCompatActivity {

    CommonImageInflate commonImageInflate, commonImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CommonUtilities.appThemeStatusBar(MainActivity.this, R.color.black, true);
        setContentView(R.layout.activity_main);

//        commonImageInflate = new CommonImageInflate(MainActivity.this);

        commonImage = findViewById(R.id.commonImage);

        commonImage.InflateImage(MainActivity.this,"https://img.freepik.com/free-vector/website-setup-concept-landing-page-template_23-2148292726.jpg?t=st=1725086644~exp=1725090244~hmac=cae0e4c611cde9d8dbc6ae72ac5fa477579698564036c6df4cc162de376543b3&w=1380");

        CommonUtilities.CommonLogger.getInstance().e("CurrentDateStandard","Date: " + CommonUtilities.CurrentDateStandard());
        CommonUtilities.CommonLogger.getInstance().e("CurrentDateShort","Date: " + CommonUtilities.CurrentDateShort());
        CommonUtilities.CommonLogger.getInstance().e("CurrentDateFullDate","Date: " + CommonUtilities.CurrentDateFullDate());
        CommonUtilities.CommonLogger.getInstance().e("CurrentDateUsa","Date: " + CommonUtilities.CurrentDateUsa());
        CommonUtilities.CommonLogger.getInstance().e("CurrentDateRegular","Date: " + CommonUtilities.CurrentDateRegular());
        CommonUtilities.CommonLogger.getInstance().e("CurrentAndroidID","ID: " + CommonUtilities.GetAndroidId(MainActivity.this));
        CommonUtilities.CommonLogger.getInstance().e("CurrentInternet","Internet: " + CommonUtilities.InternetAvailable(MainActivity.this));
        CommonUtilities.CommonLogger.getInstance().e("CurrentDeveloperMode","DeveloperMode: " + CommonUtilities.isDeveloperModeEnabled(MainActivity.this));
    }
}
package com.alpha.common.utility;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Path;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtilities {
    public static final Pattern VALID_MOBILE_REGEX = Pattern.compile("^[6-9]\\d{9}$", Pattern.CASE_INSENSITIVE);
    public static final String DATE_FORMAT_STANDARDIZED_UTC = "yyyy-MM-dd";
    public static final String DATE_FORMAT_REGULAR = "dd MMM yyyy";
    public static final String DATE_FORMAT_USA = "dd/MM/yyyy";
    public static final String DATE_FORMAT_FULL_DATE = "MMMM dd, yyyy";
    public static final String DATE_FORMAT_SHORT_DATE = "dd/MM/yy";
    public static final String GSF_ANDROID_ID_URI = "content://com.google.android.gsf.gservices";

    public static boolean isStringNullOrEmpty(String text) {
        return (text == null || text.trim().equals("null") || text.trim()
                .length() <= 0);
    }

    public static void setToast(Context currentActivity, String str) {
        Toast toast = Toast.makeText(currentActivity, str, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void setLottieAnimation(LottieAnimationView ivLottie, String image) {
        try {
            ivLottie.setFailureListener(new LottieListener<Throwable>() {
                @Override
                public void onResult(Throwable result) {

                }
            });
            ivLottie.setAnimationFromUrl(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String modifyDateLayout(String entryDate) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(entryDate);
            String time = new SimpleDateFormat("dd MMM yyyy, hh:mm a").format(date);
            time.replace("AM", "am");
            time.replace("PM", "pm");
            return time;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void CoinAnimation(Context context, RelativeLayout layoutMain, LinearLayout layoutPoints, int drawable) {
        int i;
        float f;
        float f2;
        float f3;
        Random random;
        int i2;
        RelativeLayout frameLayout2 = layoutMain;
        Random random2 = new Random();
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        int i4 = 0;
        while (true) {
            i = 12;
            if (i4 >= 12) {
                break;
            }
            arrayList.add(Integer.valueOf((i4 * 50) + 500));
            i4++;
        }
        int round = Math.round(Resources.getSystem().getDisplayMetrics().density * 20.0f);
        ViewParent parent = layoutPoints.getParent();
        float f4 = 0.0f;
        if (parent instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) parent;
            f4 = viewGroup.getX();
            f = viewGroup.getY();
        } else {
            f = 0.0f;
        }
        float f5 = 0.5f;
        Point point = new Point((int) (((layoutPoints.getWidth() - round) * 0.5f) + layoutPoints.getX() + f4), (int) (((layoutPoints.getHeight() - round) * 0.5f) + layoutPoints.getY() + f));
        int i5 = 0;
        while (i5 < i) {
            final View view2 = new View(context);
            frameLayout2.addView(view2, new ViewGroup.LayoutParams(round, round));
            view2.setBackgroundResource(drawable);
            if (i5 == 10) {
                view2.setTag(5);
            } else {
                view2.setTag(Integer.valueOf(i3));
            }
            float width = (layoutMain.getWidth() * f5) + layoutMain.getX();
            float height = (layoutMain.getHeight() * 0.4f) + layoutMain.getY();
            double radians = Math.toRadians((i5 * 15) + 180);
            Path path = new Path();
            float f6 = round * 0.5f;
            int i6 = round;
            double nextInt = ((random2.nextInt(10) * 0.01f) + 0.2f) * Math.min(layoutMain.getWidth(), layoutMain.getHeight());
            Random random3 = random2;
            float cos = (((float) (Math.cos(radians) * nextInt)) + width) - f6;
            float sin = (height - ((float) (Math.sin(radians) * nextInt))) - f6;
            path.moveTo(width, height);
            path.lineTo(cos, sin);
            if (cos <= width) {
                f2 = 0.5f;
                f3 = cos * 0.5f;
            } else {
                f2 = 0.5f;
                f3 = cos * 1.5f;
            }
            path.quadTo(f3, sin * 1.5f, point.x, point.y);
            i5++;
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view2, View.X, View.Y, path);
            ofFloat.setInterpolator(new AccelerateInterpolator());
            if (arrayList.size() > 0) {
                random = random3;
                i2 = random.nextInt(arrayList.size());
            } else {
                random = random3;
                i2 = 0;
            }
            ofFloat.setDuration(((Integer) arrayList.get(i2)).intValue());
            arrayList.remove(i2);
            ofFloat.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    layoutMain.removeView(view2);
                }
            });
            animatorSet.playTogether(ofFloat);
            random2 = random;
            frameLayout2 = layoutMain;
            round = i6;
            i = 12;
            f5 = f2;
            i3 = 0;
        }
        animatorSet.start();
    }

    public static boolean CheckMobileNumber(String trim) {
        Matcher matcher = VALID_MOBILE_REGEX.matcher(trim);
        return matcher.matches();
    }

    public static void appThemeStatusBar(Activity activity, int statusColor, boolean whiteText) {
        Window window = activity.getWindow();
//        Drawable background = activity.getDrawable(headerBg);
        if (whiteText) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        } else {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        window.setNavigationBarColor(activity.getColor(R.color.white));

        window.setStatusBarColor(activity.getColor(statusColor));

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    }
    public static void fullScreenThemeStatusBar(Activity activity, int statusColor) {
        Window window = activity.getWindow();
//        Drawable background = activity.getDrawable(headerBg);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        window.setNavigationBarColor(activity.getColor(statusColor));

        window.setStatusBarColor(activity.getColor(R.color.transparent));

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    }
    public static String CurrentDateStandard() {
        return new SimpleDateFormat(DATE_FORMAT_STANDARDIZED_UTC).format(new Date());
    }
    public static String CurrentDateShort() {
        return new SimpleDateFormat(DATE_FORMAT_SHORT_DATE).format(new Date());
    }
    public static String CurrentDateFullDate() {
        return new SimpleDateFormat(DATE_FORMAT_FULL_DATE).format(new Date());
    }
    public static String CurrentDateUsa() {
        return new SimpleDateFormat(DATE_FORMAT_USA).format(new Date());
    }
    public static String CurrentDateRegular() {
        return new SimpleDateFormat(DATE_FORMAT_REGULAR).format(new Date());
    }
    public static String GetAndroidId(Context context) {
        try {
            Uri URI = Uri.parse(GSF_ANDROID_ID_URI);
            String ID_KEY = "android_id";
            String params[] = {ID_KEY};
            Cursor c = context.getContentResolver().query(URI, null, null, params, null);
            if (c != null && (!c.moveToFirst() || c.getColumnCount() < 2)) {
                if (!c.isClosed())
                    c.close();
                return "";
            }

            try {
                if (c != null) {
                    String result = Long.toHexString(Long.parseLong(c.getString(1)));
                    if (!c.isClosed())
                        c.close();
                    return result;
                } else {
                    return "";
                }
            } catch (NumberFormatException e) {
                if (!c.isClosed())
                    c.close();
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public static void setEnableDisable(final Activity activity, View v) {
        v.setEnabled(false);
        Timer buttonTimer = new Timer();
        buttonTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        v.setEnabled(true);
                    }
                });
            }
        }, 2000);
    }
    public static boolean InternetAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public static boolean isDeveloperModeEnabled(Context context) {
        if (!BuildConfig.DEBUG) {
            int devMode = Settings.Global.getInt(
                    context.getContentResolver(),
                    Settings.Global.DEVELOPMENT_SETTINGS_ENABLED,
                    0
            );
            return devMode == 1;
        }
        return false;
    }
    public static void TextCountAnimation(TextView tvPoint, String welcomePoint) {
        ValueAnimator animator = ValueAnimator.ofInt(0, Integer.parseInt(welcomePoint));
        animator.setDuration(1500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                tvPoint.setText(animation.getAnimatedValue().toString());
            }
        });
        animator.start();
    }
    public static void CoinAnimationDeduct(Context context, RelativeLayout layoutMain, LinearLayout layoutPoints, int ic_coin) {
        int i;
        float f;
        float f2;
        float f3;
        Random random;
        int i2;
        RelativeLayout frameLayout2 = layoutMain;
        Random random2 = new Random();
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        int i4 = 0;
        int totalCoins = 12;
        while (i4 < totalCoins) {
            arrayList.add(Integer.valueOf((i4 * 50) + 500));
            i4++;
        }
        int round = Math.round(Resources.getSystem().getDisplayMetrics().density * 20.0f);
        ViewParent parent = layoutPoints.getParent();
        float f4 = 0.0f;
        if (parent instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) parent;
            f4 = viewGroup.getX();
            f = viewGroup.getY();
        } else {
            f = 0.0f;
        }
        float f5 = 0.5f;
        Point point = new Point(
                (int) (((layoutPoints.getWidth() - round) * 0.5f) + layoutPoints.getX() + f4),
                (int) (((layoutPoints.getHeight() - round) * 0.5f) + layoutPoints.getY() + f)
        );

        for (int i5 = 0; i5 < totalCoins; i5++) {
            final View view2 = new View(context);
            frameLayout2.addView(view2, new ViewGroup.LayoutParams(round, round));
            view2.setBackgroundResource(ic_coin);

            if (i5 == 10) {
                view2.setTag(5);
            } else {
                view2.setTag(0);
            }

            float width = (layoutMain.getWidth() * f5) + layoutMain.getX();
            float height = (layoutMain.getHeight() * 0.4f) + layoutMain.getY();
            double radians = Math.toRadians((i5 * 15) + 180);
            Path path = new Path();
            float f6 = round * 0.5f;
            double nextInt = ((random2.nextInt(10) * 0.01f) + 0.2f) * Math.min(layoutMain.getWidth(), layoutMain.getHeight());

            float cos = (((float) (Math.cos(radians) * nextInt)) + width) - f6;
            float sin = (height - ((float) (Math.sin(radians) * nextInt))) - f6;

            path.moveTo(point.x, point.y);
            path.lineTo(cos, sin);
            if (cos <= width) {
                f2 = 0.5f;
                f3 = cos * 0.5f;
            } else {
                f2 = 0.5f;
                f3 = cos * 1.5f;
            }
            path.quadTo(f3, sin * 1.5f, cos, sin);

            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view2, View.X, View.Y, path);
            ofFloat.setInterpolator(new AccelerateInterpolator());

            if (arrayList.size() > 0) {
                random = random2;
                i2 = random.nextInt(arrayList.size());
            } else {
                random = random2;
                i2 = 0;
            }
            ofFloat.setDuration(((Integer) arrayList.get(i2)).intValue());
            arrayList.remove(i2);

            ofFloat.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    layoutMain.removeView(view2);
                }
            });

            animatorSet.playTogether(ofFloat);
        }
        animatorSet.start();
    }
    public static String ConvertPointsToRupees(String Points, String PointsValue, String currency) {
        try {
            double calRupees = (Double.parseDouble(Points) * 1) / Double.parseDouble(PointsValue);
            String value = String.valueOf(calRupees);
            if (value.endsWith(".00")) {
                value = value.replace(".00", "");
            }
            if (value.endsWith(".0")) {
                value = value.replace(".0", "");
            }
            return currency + value;
        } catch (Exception e) {
        }
        return "";
    }
    public static class CommonLogger {
        private static CommonLogger instance = new CommonLogger();
        private boolean isLogEnabled = false;

        private CommonLogger() {
            isLogEnabled = BuildConfig.DEBUG;
        }

        public static CommonLogger getInstance() {
            return instance;
        }

        public void d(String a, String b) {
            if (isLogEnabled) Log.d(a, b);
        }

        public void e(String a, String b) {
            if (isLogEnabled) Log.e(a, b);
        }

        public void e_long(String TAG, String message) {
            int maxLogSize = 2000;
            for (int i = 0; i <= message.length() / maxLogSize; i++) {
                int start = i * maxLogSize;
                int end = (i + 1) * maxLogSize;
                end = end > message.length() ? message.length() : end;
                e(TAG, message.substring(start, end));
            }
        }

        public void e(String a, String b, Throwable t) {
            if (isLogEnabled) Log.e(a, b, t);
        }

        public void i(String a, String b) {
            if (isLogEnabled) Log.i(a, b);
        }

        public void w(String a, String b) {
            if (isLogEnabled) Log.w(a, b);
        }

        public void v(String a, String b) {
            if (isLogEnabled) Log.v(a, b);
        }
    }
//    public static void AppUpdateDialog(final Activity activity, String isForceUpdate, final String appUrl, String updateMessage) {
//        try {
//            if (activity != null) {
//                final Dialog dialog1 = new Dialog(activity, android.R.style.Theme_Light);
//                dialog1.getWindow().setBackgroundDrawableResource(R.color.blackTransparent);
//                dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog1.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//                dialog1.setContentView(R.layout.dialog_update_app);
//
//                Button btnUpdate = dialog1.findViewById(R.id.btnUpdate);
//                Button btnCancel = dialog1.findViewById(R.id.btnCancel);
//                TextView tvMessage = dialog1.findViewById(R.id.tvMessage);
//                tvMessage.setText(updateMessage);
//                if (isForceUpdate.equals("1")) {
//                    dialog1.setCancelable(false);
//                    btnUpdate.setVisibility(View.VISIBLE);
//                    btnCancel.setVisibility(View.GONE);
//                } else {
//                    dialog1.setCancelable(true);
//                    btnUpdate.setVisibility(View.VISIBLE);
//                    btnCancel.setVisibility(View.VISIBLE);
//                }
//                btnUpdate.setOnClickListener(v -> {
//                    if (!activity.isFinishing() && !isForceUpdate.equals("1")) {
//                        dialog1.dismiss();
//                    }
//                    try {
//                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(appUrl));
//                        activity.startActivity(browserIntent);
//                    } catch (ActivityNotFoundException e) {
//                        Toast.makeText(activity, "No application can handle this request." + " Please install a web browser", Toast.LENGTH_LONG).show();
//                        e.printStackTrace();
//                    }
//                });
//                btnCancel.setOnClickListener(view -> {
//                    if (!activity.isFinishing()) {
//                        dialog1.dismiss();
//                    }
//                });
//                if (!activity.isFinishing()) {
//                    dialog1.show();
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}

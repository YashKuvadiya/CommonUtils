package com.alpha.common.utility;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

public class CommonToolbar extends RelativeLayout {
    private TextView tvToolbarTitle, tvToolbarPoints;
    private ImageView ivToolbarBack, ivToolbarHistory, icCoin;
    private LinearLayout layoutToolbarPoints;
    private RelativeLayout layoutMain;

    public CommonToolbar(Context context) {
        super(context);
        init(context);
    }

    public CommonToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CommonToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.common_toolbar_layout, this, false);

        layoutMain = findViewById(R.id.layoutMain);
        tvToolbarTitle = findViewById(R.id.tvToolbarTitle);
        tvToolbarPoints = findViewById(R.id.tvToolbarPoints);
        ivToolbarBack = findViewById(R.id.ivToolbarBack);
        ivToolbarHistory = findViewById(R.id.ivToolbarHistory);
        layoutToolbarPoints = findViewById(R.id.layoutToolbarPoints);
        icCoin = findViewById(R.id.icCoin);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.common_toolbar_layout, this, true);

        layoutMain = findViewById(R.id.layoutMain);
        tvToolbarTitle = findViewById(R.id.tvToolbarTitle);
        tvToolbarPoints = findViewById(R.id.tvToolbarPoints);
        ivToolbarBack = findViewById(R.id.ivToolbarBack);
        ivToolbarHistory = findViewById(R.id.ivToolbarHistory);
        layoutToolbarPoints = findViewById(R.id.layoutToolbarPoints);
        icCoin = findViewById(R.id.icCoin);

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CommonToolbar);

            try {
                int drawableOrColor = typedArray.getResourceId(R.styleable.CommonToolbar_tb_headerBg, -1);
                if (drawableOrColor != -1) {
                    layoutMain.setBackground(getResources().getDrawable(drawableOrColor, context.getTheme()));
                } else {
                    int color = typedArray.getColor(R.styleable.CommonToolbar_tb_headerBg, -1);
                    if (color != -1) {
                        layoutMain.setBackgroundColor(color);
                    }
                }

//            back
                boolean backVisibility = typedArray.getBoolean(R.styleable.CommonToolbar_tb_backVisibility, true);
                if (backVisibility) {
                    ivToolbarBack.setVisibility(VISIBLE);
                } else {
                    ivToolbarBack.setVisibility(GONE);
                }
                Drawable backSrc = typedArray.getDrawable(R.styleable.CommonToolbar_tb_backIconSrc);
                if (backSrc != null) {
                    ivToolbarBack.setImageDrawable(backSrc);
                }
                int backSrcTint = typedArray.getColor(R.styleable.CommonToolbar_tb_backIconSrcTint, -1);
                if (backSrcTint != -1) {
                    ivToolbarBack.setColorFilter(backSrcTint);
                }

//            title
                boolean titleVisibility = typedArray.getBoolean(R.styleable.CommonToolbar_tb_titleVisibility, true);
                if (titleVisibility) {
                    tvToolbarTitle.setVisibility(VISIBLE);
                } else {
                    tvToolbarTitle.setVisibility(GONE);
                }
                String titleText = typedArray.getString(R.styleable.CommonToolbar_tb_titleText);
                if (titleText != null) {
                    tvToolbarTitle.setText(titleText);
                }
                int fontResId = typedArray.getResourceId(R.styleable.CommonToolbar_tb_titleTextFont, -1);
                if (fontResId != -1) {
                    Typeface typeface = ResourcesCompat.getFont(context, fontResId);
                    tvToolbarTitle.setTypeface(typeface);
                }
                int titleTextColor = typedArray.getColor(R.styleable.CommonToolbar_tb_titleTextColor, -1);
                if (titleTextColor != -1) {
                    tvToolbarTitle.setTextColor(titleTextColor);
                }

//            wallet
                boolean walletVisibility = typedArray.getBoolean(R.styleable.CommonToolbar_tb_walletVisibility, true);
                if (walletVisibility) {
                    layoutToolbarPoints.setVisibility(VISIBLE);
                } else {
                    layoutToolbarPoints.setVisibility(GONE);
                }
                Drawable walletBackground = typedArray.getDrawable(R.styleable.CommonToolbar_tb_walletBackground);
                if (walletBackground != null) {
                    layoutToolbarPoints.setBackground(walletBackground);
                }
                int walletBackgroundTint = typedArray.getColor(R.styleable.CommonToolbar_tb_walletBackgroundTint, -1);
                if (walletBackgroundTint != -1) {
                    layoutToolbarPoints.setBackgroundTintList(ColorStateList.valueOf(walletBackgroundTint));
                }

//            coin
                Drawable coinSrc = typedArray.getDrawable(R.styleable.CommonToolbar_tb_walletCoinSrc);
                if (coinSrc != null) {
                    icCoin.setImageDrawable(coinSrc);
                }

//            walletPoints
                String walletPointsText = typedArray.getString(R.styleable.CommonToolbar_tb_pointsText);
                if (walletPointsText != null) {
                    tvToolbarPoints.setText(walletPointsText);
                }
                int walletPointsFonts = typedArray.getResourceId(R.styleable.CommonToolbar_tb_pointsTextFont, -1);
                if (walletPointsFonts != -1) {
                    Typeface typefaceWalletPoints = ResourcesCompat.getFont(context, walletPointsFonts);
                    tvToolbarPoints.setTypeface(typefaceWalletPoints);
                }
                int walletPointsColor = typedArray.getColor(R.styleable.CommonToolbar_tb_pointsTextColor, -1);
                if (walletPointsColor != -1) {
                    tvToolbarPoints.setTextColor(walletPointsColor);
                }

//            history
                boolean historyVisibility = typedArray.getBoolean(R.styleable.CommonToolbar_tb_historyVisibility, true);
                if (historyVisibility) {
                    ivToolbarHistory.setVisibility(VISIBLE);
                } else {
                    ivToolbarHistory.setVisibility(GONE);
                }
                Drawable historySrc = typedArray.getDrawable(R.styleable.CommonToolbar_tb_historyIconSrc);
                if (historySrc != null) {
                    ivToolbarHistory.setImageDrawable(historySrc);
                }
                int historySrcTint = typedArray.getColor(R.styleable.CommonToolbar_tb_backIconSrcTint, -1);
                if (historySrcTint != -1) {
                    ivToolbarHistory.setColorFilter(historySrcTint);
                }
            } finally {
                typedArray.recycle();
            }
        }
    }

    //back
    public void TbSetBackVisibility(int visibility) {
        ivToolbarBack.setVisibility(visibility);
    }

    public void TbSetBackSrc(Drawable src) {
        ivToolbarBack.setImageDrawable(src);
    }

    public void TbSetBackSrcTint(int color) {
        ivToolbarBack.setColorFilter(color);
    }

    //    title
    public void TbSetTitleVisibility(int visibility) {
        tvToolbarTitle.setVisibility(visibility);
    }

    public void TbSetTitleText(String text) {
        tvToolbarTitle.setText(text);
    }

    public void TbSetTitleTextFont(Typeface font) {
        tvToolbarTitle.setTypeface(font);
    }

    public void TbSetTitleTextColor(int color) {
        tvToolbarTitle.setTextColor(color);
    }

    //wallet
    public void TbSetWalletBackground(Drawable background) {
        layoutToolbarPoints.setBackground(background);
    }

    public void TbSetWalletBackgroundTint(int color) {
        layoutToolbarPoints.setBackgroundTintList(ColorStateList.valueOf(color));
    }

    //coin
    public void TbSetCoinSrc(Drawable src) {
        icCoin.setImageDrawable(src);
    }


    //walletPoints
    public void TbSetPointsText(String string) {
        tvToolbarPoints.setText(string);
    }

    public void TbSetPointsFont(Typeface font) {
        tvToolbarPoints.setTypeface(font);
    }

    public void TbSetPointsTextColor(int color) {
        tvToolbarPoints.setTextColor(color);
    }

    //history
    public void TbSetHistorySrc(Drawable src) {
        ivToolbarHistory.setImageDrawable(src);
    }

    public void TbSetHistorySrcTint(int color) {
        ivToolbarHistory.setColorFilter(color);
    }

    //click
    public void TbWalletClick(OnClickListener onClickListener) {
        layoutToolbarPoints.setOnClickListener(onClickListener);
    }

    public void TbHistoryClick(OnClickListener onClickListener) {
        ivToolbarHistory.setOnClickListener(onClickListener);
    }

    public void TbBackClick(OnClickListener onClickListener) {
        ivToolbarBack.setOnClickListener(onClickListener);
    }

//    public void SetPoints(Activity context, String Points) {
//        MainResponseModel responseModel = new Gson().fromJson(SharedPreference.getInstance().getString(SharedPreference.HOME_DATA), MainResponseModel.class);
//
//        if (SharedPreference.getInstance().getBoolean(SharedPreference.IS_LOGIN)) {
//            if (responseModel.getTaskBalance() != null && responseModel.getTaskBalance().getPoints() != null) {
//                tvToolbarPoints.setText(Points + " + " + CommonUtility.ExtraPoints(context));
//            } else {
//                tvToolbarPoints.setText(Points);
//            }
//        } else {
//            tvToolbarPoints.setText(Points);
//        }
//    }
}

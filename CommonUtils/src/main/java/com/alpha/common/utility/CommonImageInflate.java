package com.alpha.common.utility;

import android.animation.Animator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class CommonImageInflate extends RelativeLayout {
    private RelativeLayout layoutCommonImage;
    private ImageView ivCommonImage, ivCommonGIF;
    private LottieAnimationView ltCommonLottie;
    private ProgressBar commonProgressBar;
    private int progressBarColor, imageScaleType, lottieScaleType;
    private boolean lottieLoop, lottieAutoPlay;
    private float progressBarSize;

    public CommonImageInflate(Context context) {
        super(context);
        init(context);
    }

    public CommonImageInflate(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CommonImageInflate(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.every_image_inflate_layout, this, false);

        layoutCommonImage = findViewById(R.id.layoutCommonImage);
        ivCommonImage = findViewById(R.id.ivCommonImage);
        ivCommonGIF = findViewById(R.id.ivCommonGIF);
        ltCommonLottie = findViewById(R.id.ltCommonLottie);
        commonProgressBar = findViewById(R.id.commonProgressBar);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.every_image_inflate_layout, this, true);

        layoutCommonImage = findViewById(R.id.layoutCommonImage);
        ivCommonImage = findViewById(R.id.ivCommonImage);
        ivCommonGIF = findViewById(R.id.ivCommonGIF);
        ltCommonLottie = findViewById(R.id.ltCommonLottie);
        commonProgressBar = findViewById(R.id.commonProgressBar);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.CommonImageInflate,
                0, 0);

        try {
            progressBarColor = typedArray.getColor(R.styleable.CommonImageInflate_cil_progress_bar_color, Color.BLACK);
            lottieLoop = typedArray.getBoolean(R.styleable.CommonImageInflate_cil_lottie_loop, true);
            lottieAutoPlay = typedArray.getBoolean(R.styleable.CommonImageInflate_cil_lottie_auto_play, true);
            imageScaleType = typedArray.getInt(R.styleable.CommonImageInflate_cil_image_scale_type, 1);
            lottieScaleType = typedArray.getInt(R.styleable.CommonImageInflate_cil_lottie_scale_type, 1);
            progressBarSize = typedArray.getDimension(R.styleable.CommonImageInflate_cil_progress_bar_size, 44);
        } finally {
            typedArray.recycle();
        }

        if (commonProgressBar != null) {
            ViewGroup.LayoutParams params = commonProgressBar.getLayoutParams();
            params.width = (int) progressBarSize;
            params.height = (int) progressBarSize;
            commonProgressBar.setLayoutParams(params);
        }

        if (commonProgressBar != null) {
            if (commonProgressBar.getProgressDrawable() != null) {
                commonProgressBar.getProgressDrawable().setColorFilter(progressBarColor, PorterDuff.Mode.SRC_IN);
            }

            if (commonProgressBar.getIndeterminateDrawable() != null) {
                commonProgressBar.getIndeterminateDrawable().setColorFilter(progressBarColor, PorterDuff.Mode.SRC_IN);
            }
        }
    }
    public void InflateImage(Context context, String image) {
        if (image != null && !CommonUtilities.isStringNullOrEmpty(image)) {
            layoutCommonImage.setVisibility(VISIBLE);
            if (image.contains(".json")) {
                ivCommonGIF.setVisibility(View.GONE);
                ivCommonImage.setVisibility(View.GONE);
                CommonUtilities.setLottieAnimation(ltCommonLottie, image);
                ltCommonLottie.setRepeatCount(LottieDrawable.INFINITE);
                ltCommonLottie.playAnimation();
                ltCommonLottie.addAnimatorListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        if (commonProgressBar != null) {
                            commonProgressBar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                });
            } else if (image.contains("gif")) {
                ivCommonGIF.setVisibility(View.VISIBLE);
                ltCommonLottie.setVisibility(View.GONE);
                ivCommonImage.setVisibility(View.GONE);
                Glide.with(context)
                        .load(image)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
                                if (commonProgressBar != null) {
                                    commonProgressBar.setVisibility(View.GONE);
                                }
                                return false;
                            }
                        })
                        .into(ivCommonGIF);
            } else {
                ivCommonGIF.setVisibility(View.GONE);
                ltCommonLottie.setVisibility(View.GONE);
                ivCommonImage.setVisibility(View.VISIBLE);
                Glide.with(context)
                        .load(image)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
                                if (commonProgressBar != null) {
                                    commonProgressBar.setVisibility(View.GONE);
                                }
                                return false;
                            }
                        })
                        .into(ivCommonImage);
            }
        } else {
            layoutCommonImage.setVisibility(GONE);
        }
    }
}

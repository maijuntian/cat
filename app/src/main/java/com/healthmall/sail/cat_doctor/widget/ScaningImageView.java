package com.healthmall.sail.cat_doctor.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;


/**
 * Created by mai on 2018/1/13.
 */
@SuppressLint("AppCompatCustomView")
public class ScaningImageView extends ImageView {

    boolean isStop = false;

    Runnable scanRunnable = new Runnable() {
        @Override
        public void run() {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getLayoutParams();
            params.topMargin += 10;

            if (params.topMargin > -450) {
                params.topMargin = -1300;
            }
            setLayoutParams(params);

            if (!isStop) {
                postDelayed(scanRunnable, 50);
            }
        }
    };

    public ScaningImageView(Context context) {
        super(context);
    }

    public ScaningImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScaningImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void startScaning() {
        isStop = false;
        postDelayed(scanRunnable, 50);
    }

    public void stopScaning() {
        isStop = true;
    }
}

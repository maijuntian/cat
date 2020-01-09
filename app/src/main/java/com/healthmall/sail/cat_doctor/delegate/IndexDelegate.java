package com.healthmall.sail.cat_doctor.delegate;

import android.text.TextUtils;
import android.widget.ImageView;

import com.healthmall.sail.cat_doctor.Constant;
import com.healthmall.sail.cat_doctor.R;
import com.healthmall.sail.cat_doctor.base.BaseDelegate;
import com.healthmall.sail.cat_doctor.utils.AesEncryptUtil;
import com.healthmall.sail.cat_doctor.utils.Configs;
import com.healthmall.sail.cat_doctor.utils.Keys;
import com.healthmall.sail.cat_doctor.utils.QrCodeUtils;
import com.mai.xmai_fast_lib.basehttp.MParams;
import com.mai.xmai_fast_lib.utils.SharedPreferencesHelper;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;


public class IndexDelegate extends BaseDelegate {

    @Bind(R.id.iv_qrcode)
    ImageView ivQrcode;
    @Bind(R.id.iv_manager)
    ImageView ivManager;
    @Bind(R.id.iv_logo)
    ImageView ivLogo;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_index;
    }

    @Override
    public void initWidget() {
        super.initWidget();

        if (Configs.isTaiYangShen) {
            ivLogo.setImageResource(R.mipmap.index_taiyangshen_logo);
        }
    }

    public void initQrCode() {
        String deviceId = SharedPreferencesHelper.getInstance(mContext.getApplicationContext()).getStringValue(Keys.KEY_DEVICE_ID);

        if (!TextUtils.isEmpty(deviceId)) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("type", "unlock");
                jsonObject.put("deviceNum", deviceId);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            ivQrcode.setImageBitmap(QrCodeUtils.createImage(AesEncryptUtil.encrypt(jsonObject.toString()), 200, 200));
        }
    }

    @Override
    public void onResume() {
        initQrCode();
    }
}



package com.healthmall.sail.cat_doctor.test;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;

import com.healthmall.sail.cat_doctor.R;
import com.healthmall.sail.cat_doctor.activity.ExamineActivity;
import com.healthmall.sail.cat_doctor.base.BaseSoftActivity;
import com.healthmall.sail.cat_doctor.bean.BodyReport;
import com.healthmall.sail.cat_doctor.bean.BodyRespone;
import com.healthmall.sail.cat_doctor.http.CatDoctorApi;
import com.healthmall.sail.cat_doctor.serialport.SerialPortCmd;
import com.mai.xmai_fast_lib.utils.MLog;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * Created by mai on 2018/10/25.
 */
public class TestUserInfoActivity extends BaseSoftActivity<TestUserInfoDelegate> {

    @Bind(R.id.et_height)
    EditText etHeight;
    @Bind(R.id.et_weight)
    EditText etWeight;
    @Bind(R.id.et_age)
    EditText etAge;
    @Bind(R.id.rb_man)
    RadioButton rbMan;
    @Bind(R.id.rb_women)
    RadioButton rbWomen;

    BodyReport currBodyReport;


    @OnClick(R.id.bt_examine)
    public void bt_examineClick() {
        if (etHeight.length() == 0) {
            showToast("不能为空");
            return;
        }
        if (etWeight.length() == 0) {
            showToast("不能为空");
            return;
        }

        try {
            showProgressDialog("正在测量，请稍等...");
            SerialPortCmd.bodyFat(rbMan.isChecked() ? 1 : 0, Integer.parseInt(etAge.getText().toString().trim()), Integer.parseInt(etWeight.getText().toString()) * 10 + "", (Integer.parseInt(etHeight.getText().toString()) * 10 + ""));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void serialPortCallBack(String msg) {
        super.serialPortCallBack(msg);
        if (msg.startsWith(SerialPortCmd.OK_BODYFAT)) { //体脂
            dismissProgressDialog();
            SerialPortCmd.stopFat();
            currBodyReport = new BodyReport("", "");
            SerialPortCmd.parseBodyFat(msg, currBodyReport);

            Bundle bundle = new Bundle();
            bundle.putSerializable("report", currBodyReport);
            currBodyReport.setAge(Integer.parseInt(etAge.getText().toString().trim()));
            currBodyReport.setSex(rbMan.isChecked() ? 1 : 0);
            currBodyReport.setBm_weight(etWeight.getText().toString());
            currBodyReport.setBm_height(etHeight.getText().toString());

            startActivity(TestResultActivity.class, bundle, false);
        }
    }
}

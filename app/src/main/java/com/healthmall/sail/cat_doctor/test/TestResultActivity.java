package com.healthmall.sail.cat_doctor.test;

import android.os.Bundle;

import com.healthmall.sail.cat_doctor.base.BaseSoftActivity;
import com.healthmall.sail.cat_doctor.bean.BodyReport;

/**
 * Created by mai on 2018/10/25.
 */
public class TestResultActivity extends BaseSoftActivity<TestResultDelegate>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewDelegate.showResult((BodyReport) getIntent().getSerializableExtra("report"));
    }
}

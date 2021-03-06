package com.healthmall.sail.cat_doctor.fragment;

import com.healthmall.sail.cat_doctor.R;
import com.healthmall.sail.cat_doctor.base.BaseFragment;
import com.healthmall.sail.cat_doctor.delegate.ManagerHardwareCheckDelegate;
import com.healthmall.sail.cat_doctor.serialport.SerialPortCmd;

import butterknife.OnClick;

/**
 * Created by mai on 2017/11/21.
 */
public class ManagerHardwareCheckFragment extends BaseFragment<ManagerHardwareCheckDelegate> {


    @OnClick(R.id.iv_tip)
    public void iv_tipClick() {
        if (viewDelegate.rbCheck.isChecked()) {
            viewDelegate.showChecking();
            SerialPortCmd.hadTest();
        } else {
            viewDelegate.showCalibartioning();
            SerialPortCmd.calibration();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SerialPortCmd.stopHadTest();
        SerialPortCmd.stopCalibration();
    }

    @Override
    public void serialPortCallBack(String msg) {
        super.serialPortCallBack(msg);

        if (msg.startsWith(SerialPortCmd.OK_BCALIBRATION)) {
            viewDelegate.showBloodOResult(true,true);
            viewDelegate.showHeightResult(true,true);
            viewDelegate.showWeightResult(true,true);
            viewDelegate.showBPHRResult(true,true);
            SerialPortCmd.stopCalibration();

        } else if (msg.startsWith(SerialPortCmd.OK_HADTEST)) {
            viewDelegate.showBloodOResult(true, true);
            viewDelegate.showWeightResult(true, true);
            viewDelegate.showHeightResult(true, true);
            viewDelegate.showBPHRResult(true, true);
            SerialPortCmd.stopHadTest();
        } else if(msg.startsWith(SerialPortCmd.ERR_CAL_HEIGHT)){
            viewDelegate.showHeightResult(false, false);
        } else if(msg.startsWith(SerialPortCmd.ERR_WEIGHT)){
            viewDelegate.showWeightResult(false, false);
        }
    }
}

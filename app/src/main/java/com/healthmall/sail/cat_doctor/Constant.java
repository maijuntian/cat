package com.healthmall.sail.cat_doctor;

import android.os.Environment;

/**
 * Created by mai on 2017/11/21.
 */
public class Constant {
    public static final String DEVICE_ID_PRE = "BC";
    public static final String DEVICE_QR_CODE_URL_PRE = "panda.xiliangmen.com/api/wechatapplet/unlock/unlock?deviceNum=";

    public static final String RXBUS_LOGIN = "login";

    public static final String SERIAL_PATH = "/dev/ttyS3";

    public static final String SERIAL_PATH_SMALL = "/dev/ttyS1";

    public static final int BAUDRATE = 115200;

    public static final String BASE_PATH = Environment
            .getExternalStorageDirectory().getPath() + "/cat_doctor/";

    public static final String SDCARD_CACHE_IMG_PATH = BASE_PATH
            + "upload/";
    public static final String SDCARD_DOWNLOAD_PATH = BASE_PATH + "download/";
}

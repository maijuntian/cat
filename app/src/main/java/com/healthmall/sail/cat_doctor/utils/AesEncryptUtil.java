package com.healthmall.sail.cat_doctor.utils;

import android.util.Base64;

import com.mai.xmai_fast_lib.utils.MLog;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;

/**
 * @Description: AES二维码加密解密工具类
 * @Author: fengyu
 * @CreateDate: 2019/9/10 11:21
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/9/10 11:21
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class AesEncryptUtil {
    //默认二维码为解密规则
    private static String KEY = "xlmBQnaFD05BQnaF";
    private static String IV = "FY9BQnaFk0SBv6aF";

    /**
     * 加密方法
     *
     * @param data 要加密的数据
     * @param key  加密key
     * @param iv   加密iv
     * @return 加密的结果
     */
    public static String encrypt(String data, String key, String iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] dataBytes = data.getBytes();
        int plaintextLength = dataBytes.length;
        byte[] plaintext = new byte[plaintextLength];
        System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
        SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
        byte[] encrypted = cipher.doFinal(plaintext);

        return Base64.encodeToString(encrypted, Base64.DEFAULT)
                .replace("\n", "").trim();
    }

    /**
     * 解密方法
     *
     * @param data 要解密的数据
     * @param key  解密key
     * @param iv   解密iv
     * @return 解密的结果
     */
    public static String desEncrypt(String data, String key, String iv) throws Exception {
        String originalString = "";

        byte[] encrypted1 = Base64.decode(data, Base64.DEFAULT);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

        cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

        byte[] original = cipher.doFinal(encrypted1);
        originalString = new String(original, StandardCharsets.UTF_8);

        return originalString;
    }


    /**
     * 使用默认的key和iv加密
     *
     * @param data
     * @return
     */
    public static String encrypt(String data) {
        String result = "";
        try {
            result = encrypt(data, KEY, IV);
        } catch (Exception e) {
            e.printStackTrace();
        }
        MLog.log("encrypt--->" + result);
        return result;
    }

    /**
     * 使用默认的key和iv解密
     *
     * @param data
     * @return
     */
    public static String desEncrypt(String data) throws Exception {
        return desEncrypt(data, KEY, IV);
    }


    public static void main(String[] args) throws Exception {
        String msg = "{\"type\":\"unlock\",\"deviceNum\":\"BC000001\"}";
        //String msg = "{\"username\":\"admin\",\"password\":\"admin123\"}";
        //String ss = encrypt(msg, "xlmParameter6HaF", "FY9BQGaFk0QBv6aF");
        String ss = encrypt(msg);
        System.out.println("加密后的数据：" + ss);
        System.out.println("解密后的数据：" + desEncrypt(ss, "xlmParameter6HaF", "FY9BQGaFk0QBv6aF"));
    }

}

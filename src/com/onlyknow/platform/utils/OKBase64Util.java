package com.onlyknow.platform.utils;

import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class OKBase64Util {
    public static boolean saveBase64ToImage(String img_base64, String dir_path, String file_name) {
        if (img_base64 == null) {
            return false;
        }

        File file = new File(dir_path);

        if (!file.exists()) {
            file.mkdirs();
        }

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(img_base64);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(dir_path + file_name);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

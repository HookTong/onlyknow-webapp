package com.onlyknow.platform.utils;

import com.onlyknow.platform.OKProjectConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OKLogUtil {

    public static void print(String msg) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.print(df.format(new Date()) + " -- " + msg + "\n\r");
    }

    public static void printLog(String msg) {
        try {

            FileWriter fw = null;
            File f = new File(OKProjectConfig.LOG_FILE_PATH);

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            if (!f.exists()) {
                f.createNewFile();
            }
            fw = new FileWriter(f, true);
            BufferedWriter out = new BufferedWriter(fw);
            // 往已有的文件上添加字符串
            out.append("Only Know Service :" + "\r\n");
            out.write("[ " + df.format(new Date()) + " ] :" + msg + "\r\n");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            print(e.getMessage());
        }
    }

    public static void printLog(String tag, String msg) {
        try {

            FileWriter fw = null;
            File f = new File(OKProjectConfig.LOG_FILE_PATH);

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            if (!f.exists()) {
                f.createNewFile();
            }
            fw = new FileWriter(f, true);
            BufferedWriter out = new BufferedWriter(fw);
            // 往已有的文件上添加字符串
            out.append("Only Know Service By " + tag + " :" + "\r\n");
            out.write("[ " + df.format(new Date()) + " ] :" + msg + "\r\n");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            print(e.getMessage());
        }
    }
}

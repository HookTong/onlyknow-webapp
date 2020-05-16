package com.onlyknow.platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OKConstants {
    public final static String getNowDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd/HH/mm");
        return dateFormat.format(new Date());
    }

    public final static String getEditDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
        return dateFormat.format(new Date());
    }

    public final static String getUrlTag() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(new Date());
    }

    public final static long getNowTime() {
        return new Date().getTime();
    }

    public final static String bodyToString(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            line = br.readLine();
        }

        return sb.toString();
    }

    public final static Map<String, String> getParamMap(String paramStr) {
        Map<String, String> map = new HashMap<>();

        String str[] = paramStr.split("&");
        for (int i = 0; i < str.length; i++) {
            String s[] = str[i].split("=");
            String key = "";
            String value = "";
            for (int p = 0; p < s.length; p++) {
                if (p == 0) {
                    key = s[p];
                }
                if (p == 1) {
                    value = s[p];
                    break;
                }
            }
            map.put(key, value);
        }

        return map;
    }
}

package com.onlyknow.platform.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.*;

public class OKAutoEvaluationUtil {

    public static Map<String, Object> domainToMap(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            try {
                String firstLetter = fieldName.substring(0, 1).toUpperCase();
                String getter = "get" + firstLetter + fieldName.substring(1);
                Method met = obj.getClass().getMethod(getter);
                map.put(fieldName, met.invoke(obj));
            } catch (Exception ex) {
                map.put(fieldName, null);
            }
        }
        return map;
    }

    /**
     * 根据reqParams传递过来的属性名称和ojb的属性名称对比，如果相同，reqParams属性的值赋与obj属性的值
     *
     * @throws Exception
     */
    public static void evaluationObject(Map<String, String> reqParams, Object obj) throws Exception {
        try {
            Set<Map.Entry<String, String>> entrySet = reqParams.entrySet();

            //String[] strNames = reqParams.getParameterNames();

            Field[] fields = obj.getClass().getDeclaredFields();

            String mapKey = null;
            String mapValue = null;
            String classField = null;

            for (Map.Entry<String, String> entry : entrySet) {

                mapKey = entry.getKey();

                for (int j = 0; j < fields.length; j++) {

                    classField = fields[j].getName();

                    String fieldClassName = fields[j].getType().getName();

                    //判断是否相等
                    if (mapKey.equals(classField)) {

                        mapValue = reqParams.get(mapKey);
                        mapValue = URLDecoder.decode(mapValue, "utf-8");
                        mapValue = URLDecoder.decode(mapValue, "utf-8");

                        String fieldName = fields[j].getName();
                        String firstLetter = fieldName.substring(0, 1).toUpperCase();
                        String setter = "set" + firstLetter + fieldName.substring(1);

                        /**
                         * 对常用基本数据类型进行类型名构造 fieldClassName={boolean,int,float,double,long}
                         * 1.java.lang.Boolean
                         * 2.java.lang.Integer
                         * 3.java.lang.Float
                         * 4.java.lang.Double
                         * 5.java.lang.Long
                         */
                        if (fieldClassName.equals(boolean.class.getName())) {
                            fieldClassName = "java.lang.Boolean";
                        }
                        if (fieldClassName.equals(int.class.getName())) {
                            fieldClassName = "java.lang.Integer";
                        }
                        if (fieldClassName.equals(long.class.getName())) {
                            fieldClassName = "java.lang.Long";
                        }
                        if (fieldClassName.equals(double.class.getName())) {
                            fieldClassName = "java.lang.Double";
                        }
                        if (fieldClassName.equals(float.class.getName())) {
                            fieldClassName = "java.lang.Float";
                        }

                        Method met = obj.getClass().getMethod(setter, Class.forName(fieldClassName));

                        if ("null".equals(mapValue)) {
                            met.invoke(obj, (Object) null);
                            continue;
                        }

                        if (fieldClassName.equals(String.class.getName())) {
                            met.invoke(obj, mapValue.trim());
                        }
                        if (!OKStringUtil.isEmpty(mapValue)) {

                            if (fieldClassName.indexOf("$") > 0) {
                                Class myClass = Class.forName(fieldClassName);
                                //Object myObject = myClass.newInstance();		// 构造对象
                                met.invoke(obj, Enum.valueOf(myClass, mapValue));
                            }


                            if (fieldClassName.equals(int.class.getName()) || fieldClassName.equals(Integer.class.getName())) {
                                met.invoke(obj, Integer.parseInt(mapValue));
                            }
                            if (fieldClassName.equals(long.class.getName()) || fieldClassName.equals(Long.class.getName())) {
                                met.invoke(obj, Long.parseLong(mapValue));
                            }
                            if (fieldClassName.equals(double.class.getName()) || fieldClassName.equals(Double.class.getName())) {
                                met.invoke(obj, Double.parseDouble(mapValue));
                            }
                            if (fieldClassName.equals(float.class.getName()) || fieldClassName.equals(Float.class.getName())) {
                                met.invoke(obj, Float.parseFloat(mapValue));
                            }
                            if (fieldClassName.equals(boolean.class.getName()) || fieldClassName.equals(Boolean.class.getName())) {
                                if ("0".equals(mapValue)) mapValue = "false";
                                if ("1".equals(mapValue)) mapValue = "true";
                                if ("off".equals(mapValue)) mapValue = "false";
                                if ("on".equals(mapValue)) mapValue = "true";
                                met.invoke(obj, Boolean.parseBoolean(mapValue));
                            }
                            if (fieldClassName.equals(Date.class.getName())) {
                                met.invoke(obj, OKDateUtil.parseDate(mapValue.trim(), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd", "HH:mm"));
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void sortList(List<Map<String, Object>> list, final String sortName, final String sortOrder) {
        Collections.sort(list, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> obj1, Map<String, Object> obj2) {
                Object o1 = obj1.get(sortName);
                Object o2 = obj2.get(sortName);
                return compareObject(o1, o2, sortOrder);
            }
        });
    }

    public static Map<String, Object> genPaginationSupport(List<Map<String, Object>> list, final String sortName, final String sortOrder, int pageSize, int pageNumber) {

        sortList(list, sortName, sortOrder);

        List<Map<String, Object>> pgList = new ArrayList<Map<String, Object>>();

        int fromIndex = (pageNumber - 1) * pageSize;
        int toIndex = fromIndex + pageSize;
        if (toIndex > list.size()) {
            toIndex = list.size();
        }
        if (fromIndex < list.size()) {
            pgList = list.subList(fromIndex, toIndex);
        }

        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("total", list.size());
        ret.put("rows", pgList);
        return ret;
    }

    public static Map<String, Object> genPaginationSupport(List<Map<String, Object>> list, int pageSize, int pageNumber) {

        List<Map<String, Object>> pgList = new ArrayList<Map<String, Object>>();

        int fromIndex = (pageNumber - 1) * pageSize;
        int toIndex = fromIndex + pageSize;
        if (toIndex > list.size()) {
            toIndex = list.size();
        }
        if (fromIndex < list.size()) {
            pgList = list.subList(fromIndex, toIndex);
        }

        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("total", list.size());
        ret.put("rows", pgList);
        return ret;
    }

    private static int compareObject(Object obj1, Object obj2, String sortOrder) {
        int sort = 1;
        if ("desc".equals(sortOrder)) sort = -1;

        if (obj1 == null) return -1 * sort;
        if (obj2 == null) return 1 * sort;
        String className = obj1.getClass().getName();

        if (className.equals(Integer.class.getName()) || className.equals(int.class.getName())) {
            return ((Integer) obj1).compareTo((Integer) obj2) * sort;
        }
        if (className.equals(Float.class.getName()) || className.equals(float.class.getName())) {
            return ((Float) obj1).compareTo((Float) obj2) * sort;
        }
        if (className.equals(Double.class.getName()) || className.equals(double.class.getName())) {
            return ((Double) obj1).compareTo((Double) obj2) * sort;
        }
        if (className.equals(Date.class.getName())) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(obj1).compareTo(sdf.format(obj2)) * sort;
        }

        return Collator.getInstance(Locale.getDefault()).compare(obj1.toString(), obj2.toString()) * sort;
    }

    /**
     * 根据src对象的的属性名称和ojb的属性名称对比，如果相同，src属性的值赋与obj属性的值
     *
     * @throws Exception
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void evaluationObject(Object src, Object obj) throws Exception {
        try {
            Field[] strNames = src.getClass().getDeclaredFields();
            Field[] fields = obj.getClass().getDeclaredFields();

            String strReqParams = null;
            String strObj = null;
            for (int i = 0; i < strNames.length; i++) {
                strReqParams = strNames[i].getName();
                strNames[i].setAccessible(true);//修改访问权限
                for (int j = 0; j < fields.length; j++) {
                    strObj = fields[j].getName();
                    String fieldClassName = fields[j].getType().getName();
                    //判断是否相等
                    if (strReqParams.equals(strObj)) {

                        String objValues = (String) strNames[i].get(src);

                        String fieldName = fields[j].getName();
                        String firstLetter = fieldName.substring(0, 1).toUpperCase();
                        String setter = "set" + firstLetter + fieldName.substring(1);

                        /**
                         * 对常用基本数据类型进行类型名构造 fieldClassName={boolean,int,float,double,long}
                         * 1.java.lang.Boolean
                         * 2.java.lang.Integer
                         * 3.java.lang.Float
                         * 4.java.lang.Double
                         * 5.java.lang.Long
                         */
                        if (fieldClassName.equals(boolean.class.getName())) {
                            fieldClassName = "java.lang.Boolean";
                        }
                        if (fieldClassName.equals(int.class.getName())) {
                            fieldClassName = "java.lang.Integer";
                        }
                        if (fieldClassName.equals(long.class.getName())) {
                            fieldClassName = "java.lang.Long";
                        }
                        if (fieldClassName.equals(double.class.getName())) {
                            fieldClassName = "java.lang.Double";
                        }
                        if (fieldClassName.equals(float.class.getName())) {
                            fieldClassName = "java.lang.Float";
                        }

                        Method met = obj.getClass().getMethod(setter, Class.forName(fieldClassName));

                        if ("null".equals(objValues)) {
                            met.invoke(obj, (Object) null);
                            continue;
                        }

                        if (fieldClassName.equals(String.class.getName())) {
                            met.invoke(obj, objValues.trim());
                        }
                        if (!OKStringUtil.isEmpty(objValues)) {

                            if (fieldClassName.indexOf("$") > 0) {
                                Class myClass = Class.forName(fieldClassName);
                                //Object myObject = myClass.newInstance();		// 构造对象
                                met.invoke(obj, Enum.valueOf(myClass, objValues));
                            }


                            if (fieldClassName.equals(int.class.getName()) || fieldClassName.equals(Integer.class.getName())) {
                                met.invoke(obj, Integer.parseInt(objValues));
                            }
                            if (fieldClassName.equals(long.class.getName()) || fieldClassName.equals(Long.class.getName())) {
                                met.invoke(obj, Long.parseLong(objValues));
                            }
                            if (fieldClassName.equals(double.class.getName()) || fieldClassName.equals(Double.class.getName())) {
                                met.invoke(obj, Double.parseDouble(objValues));
                            }
                            if (fieldClassName.equals(float.class.getName()) || fieldClassName.equals(Float.class.getName())) {
                                met.invoke(obj, Float.parseFloat(objValues));
                            }
                            if (fieldClassName.equals(boolean.class.getName()) || fieldClassName.equals(Boolean.class.getName())) {
                                if ("0".equals(objValues)) objValues = "false";
                                if ("1".equals(objValues)) objValues = "true";
                                met.invoke(obj, Boolean.parseBoolean(objValues));
                            }
                            if (fieldClassName.equals(Date.class.getName())) {
                                met.invoke(obj, OKDateUtil.parseDate(objValues, "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd", "HH:mm"));
                            }
                        }

                    }
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static Map<String, String> getParameterMap(Map properties) {
        // 返回值Map
        Map<String, String> returnMap = new HashMap();

        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }

    //由出生日期获得年龄
    public static int getAge(Date birthDay) {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) return 0;

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            } else {
                age--;
            }
        }
        return age;
    }

    public static double getDistance(double lat1, double longt1, double lat2, double longt2) {
        double PI = 3.14159265358979323; // 圆周率
        double R = 6371229; // 地球的半径
        double x, y, distance;
        x = (longt2 - longt1) * PI * R * Math.cos(((lat1 + lat2) / 2) * PI / 180) / 180;
        y = (lat2 - lat1) * PI * R / 180;
        distance = Math.hypot(x, y);
        return distance;
    }
}

package com.springapp.mvc.util;

/**
 * Created by Administrator on 2016/12/29.
 */
public class NameUtil {

    private static int count = 0;

    public static String getName() {
        count++;
        return "Classifier-" + count;
    }

}

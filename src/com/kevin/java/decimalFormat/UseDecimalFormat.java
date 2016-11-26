package com.kevin.java.decimalFormat;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2016/6/29.
 */
public class UseDecimalFormat {

    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println(df.format(345.342d));
    }
}

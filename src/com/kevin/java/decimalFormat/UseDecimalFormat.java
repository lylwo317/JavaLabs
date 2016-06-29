package com.kevin.java.decimalFormat;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2016/6/29.
 */
public class UseDecimalFormat {

    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("###.#");

        System.out.println(df.format(1));
    }
}

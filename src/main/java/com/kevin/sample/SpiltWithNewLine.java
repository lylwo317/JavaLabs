package com.kevin.sample;

/**
 * Created by: kevin
 * Date: 2022-10-28
 */
public class SpiltWithNewLine {
    public static void main(String[] args) {
        String str = "ys7\rdgasdg\n\rv12234\nj382r\r\n1254";

        String[] split = str.split("(\r\n|\n\r|\r|\n)");

        Class<Integer> integerClass = Integer.class;
        Integer.TYPE.isPrimitive();

        int.class.isPrimitive();
        System.out.println(split);
    }
}

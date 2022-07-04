package com.kevin.java.lab;

public class VersionCode {
    public static void main(String[] args) {

        //自定义版本号
        int[] versions = {4,1,3};

        //转换为单个数值
        int verNum = versions[0] << 24 | versions[1] << 16 | versions[2] << 8;
        System.out.printf("verNum:%d\n",verNum);

        //逆向还原版本号
        int mainV = verNum >> 24;
        int subV = ((255 << 16) & verNum) >> 16;
        int minV = ((255 << 8) & verNum) >> 8;
        System.out.printf("versions:%d.%d.%d\n",mainV,subV,minV);
    }
}

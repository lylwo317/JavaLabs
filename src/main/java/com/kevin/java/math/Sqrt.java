package com.kevin.java.math;

/**
 * Created by: kevin
 * Date: 2023-03-07
 */
public class Sqrt {
    public static void main(String[] args) {
        Sqrt sqrt = new Sqrt();
        System.out.println(sqrt.sqrt(10));
    }

    public double sqrt(int x){
        double ans=1, pre=0;
        while(Math.abs(ans-pre)>1e-7){
            pre=ans;
            ans=(ans+x/ans)/2;
        }
        return ans;
    }
}

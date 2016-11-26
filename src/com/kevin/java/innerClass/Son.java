package com.kevin.java.innerClass;

/**
 * Created by kevin on 9/2/16.
 */
public class Son extends  Father {

    public class SonMother extends Mother{

    }

    public static void main(String[] args) {
        SonMother sonMother = new Son().new SonMother();
        sonMother.motherSay();

    }
}

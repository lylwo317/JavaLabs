package com.kevin.java.annotation.use;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lily on 16/7/17.
 */
public class SuppressWarningsUse {


    public static void main(String[] args) {
        SuppressWarningsUse warningsUse = new SuppressWarningsUse();
        warningsUse.addItems("hello");
        @SuppressWarnings("unchcked")
        List words = new ArrayList();
        words.add("hello"); // this causes unchecked warning


    }

    public void addItems(String item){
        List items = new ArrayList();
        items.add(item);
    }
}

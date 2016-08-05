package com.kevin.java.annotation.use;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lily on 16/7/17.
 */
public class SuppressWarningsUse {


    @SuppressWarnings("unchcked")
    public static void main(String[] args) {
        SuppressWarningsUse warningsUse = new SuppressWarningsUse();
        warningsUse.addItems("hello");
        List words = new ArrayList();
        words.add("hello"); // this causes unchecked warning
        String word = (String) words.get(0);
    }

    @SuppressWarnings("unchcked")
    public void addItems(String item){
        List items = new ArrayList();
        items.add(item);
    }
}

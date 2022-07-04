package com.kevin.java.performancetTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kevin on 16-7-10.
 */
public class ForeachAndForInt{

    public static void main(String[] args) {
        foreach();
        forByInt();
    }

    public static List<String> generateObject() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add("Hello");
        }
        return list;
    }

    //@Benchmark
    public static void foreach() {
        List list = generateObject();
        Iterator var1 = list.iterator();
        while (var1.hasNext()) {
            String s = (String) var1.next();
            s.hashCode();
        }
    }

    //@Benchmark
    public static void forByInt() {
        List<String> list = generateObject();
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            s.hashCode();
        }

    }
}

package com.kevin.java.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by kevin on 11/15/16.
 */
public class CollectionsSortUse {

    private static List<Person> arrayList = new ArrayList<>();

    private static int INDEX = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new ThreadA().start();
        }
    }

    public static class Person{
        public int index;
        public String name;
        public String sex;
    }



    static class ThreadA extends Thread{
        @Override
        public void run() {


            synchronized (ThreadA.class) {
                for (int i = 0; i < 10; i++) {
                    Person person = new Person();
                    person.index = INDEX++;
                    arrayList.add(person);
                }
                Collections.sort(arrayList, new Comparator<Person>() {
                    @Override
                    public int compare(Person o1, Person o2) {
                        return o1.index-o2.index;
                    }
                });
            }
        }
    }
}

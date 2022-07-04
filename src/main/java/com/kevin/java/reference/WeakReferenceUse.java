package com.kevin.java.reference;

import java.lang.ref.WeakReference;

/**
 * Created by kevin on 4/20/16.
 */
public class WeakReferenceUse {
    public static void main(String[] args) {
        Object object = new Object();
        WeakReference<Object> objectWeakReference = new WeakReference<Object>(object);
        System.out.println(objectWeakReference.get() == null);

        object = null;

        System.gc();

        System.out.println(objectWeakReference.get() == null);
    }
}

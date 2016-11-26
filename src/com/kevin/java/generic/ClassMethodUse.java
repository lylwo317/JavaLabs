package com.kevin.java.generic;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

/**
 * Created by kevin on 9/6/16.
 */
public class ClassMethodUse {
    public static void main(String[] args) {

        Class<?> faceExtClass = FaceExt.class;
        Class<? extends IFace> ifaceClass = faceExtClass.asSubclass(IFace.class);


        if (IFace.class.isAssignableFrom(FaceExt.class)) {
            System.out.println();
        }
    }
}

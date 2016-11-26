package com.kevin.java.collections;

import java.util.*;

/**
 * Created by kevin on 11/8/16.
 */
public class ArrayListUse {


    private static class Resfrag {



        private static class Track {
            public String name;
        }

        public Track track;
    }

    public static void main(String[] args) {

        List<Resfrag> arrayList = new ArrayList();
        arrayList.add(null);
        arrayList.add(new Resfrag());
        Collections.sort(arrayList, new Comparator<Resfrag>() {
            @Override
            public int compare(Resfrag o1, Resfrag o2) {
                o1.track.name=null;
                o2.track.name=null;
                return 0;
            }
        });

        //System.out.println(Arrays.toString(arrayList.toArray()));

    }



}

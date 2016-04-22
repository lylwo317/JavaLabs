package com.kevin.java.newFeature8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by kevin on 4/21/16.
 */
public class StreamUse {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Stream<Integer> stream = numbers.stream();
        stream.filter((x) ->
            x % 2 == 0
        ).map((x) ->
            x * x
        ).forEach(System.out::println);
    }
}

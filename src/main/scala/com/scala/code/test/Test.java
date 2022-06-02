package com.scala.code.test;

import java.util.function.Function;

public class Test {
    public static void main(String[] args) {
        Function<Integer, Integer> plusFn = (Integer value) -> value + 1;
        Function<Integer, Integer> product = (Integer value) -> value * 2;

        Function result = plusFn.compose(product);
        System.out.println(result.apply(5));
    }
}

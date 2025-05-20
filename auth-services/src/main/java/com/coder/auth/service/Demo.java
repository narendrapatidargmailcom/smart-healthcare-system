package com.coder.auth.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Demo {

    public static void main(String[] args) {
        String str="aabbcdd";

        Map<Character,Long> m1=str.chars().mapToObj(s-> (char)s).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new,Collectors.counting()));


        Character cd=m1.entrySet().stream().filter(s-> s.getValue()==1).map(a-> a.getKey()).findFirst().get();
        System.out.println(cd);
    }
}

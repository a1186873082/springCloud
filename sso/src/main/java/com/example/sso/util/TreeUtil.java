package com.example.sso.util;

import java.util.ArrayList;
import java.util.TreeSet;

public class TreeUtil {
    public static void main(String[] args) {
        System.out.println("treeSet1");
        TreeSet<Integer> treeSet1 = new TreeSet<>();
        treeSet1.add(641);
        treeSet1.add(15);
        treeSet1.add(8988);
        treeSet1.add(9);
        treeSet1.add(1544);
        for(Integer integer : treeSet1){
            System.out.println(integer);
        }
        System.out.println("treeSet2");
        TreeSet<String> treeSet2 = new TreeSet<>();
        treeSet2.add("641");
        treeSet2.add("15");
        treeSet2.add("8988");
        treeSet2.add("9");
        treeSet2.add("01544");
        for(String str : treeSet2){
            System.out.println(str);
        }
        System.out.println("list1");
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(641);
        list1.add(15);
        list1.add(8988);
        list1.add(9);
        list1.add(1544);
        for(Integer integer : list1){
            System.out.println(integer);
        }


        System.out.println("list2");
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("641");
        list2.add("15");
        list2.add("8988");
        list2.add("9");
        list2.add("01544");
        for(String str : list2){
            System.out.println(str);
        }
    }
}

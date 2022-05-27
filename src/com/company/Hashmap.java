package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Hashmap {
    public static char highestFrequency(String str) {
        str = str.toLowerCase();
        HashMap<Character, Integer> h = new HashMap<Character, Integer>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (h.containsKey(ch)) {
                int cv = h.get(ch) + 1;
                h.put(ch, cv);

            } else
                h.put(ch, 1);
        }
        Set<Character> k = h.keySet();
        int max = 0;
        char ch = str.charAt(0);
        for (Character key : k) {
            if (h.get(key) > max) {
                ch = key;
                max = h.get(key);
            }
        }
        return ch;
    }

    public static ArrayList<Integer> commonElement(int[] a, int[] b) {
        ArrayList<Integer> list = new ArrayList<>();
        HashMap<Integer, Integer> hm = new HashMap<>();

        for (int value : a) {
            hm.put(value, 1);
        }
        for (int value : b) {
            if (hm.containsKey(value)) {
                list.add(value);
                hm.remove(value);
            }
        }
        return list;
    }

    public static ArrayList<Integer> longestConsSeq(int[] arr) {
        HashMap<Integer, Boolean> hm = new HashMap<>();
        ArrayList<Integer> ml = new ArrayList<>();
        for (int i : arr) {
            hm.put(i, true);
        }
        for (int i : arr) {
            if (hm.containsKey(i - 1))
                hm.put(i, false);
        }
        for (int i : arr) {
            if (hm.get(i)) {
                ArrayList<Integer> tl = new ArrayList<>();
                int j = i;
                while (hm.containsKey(j)) {
                    tl.add(j);
                    j++;
                }
                if (tl.size() > ml.size()) {
                    ml.clear();
                    ml.addAll(tl);
                }
            }
        }
        return ml;
    }

    public static ArrayList<Integer> commonElement2(int[] a, int[] b) {
        ArrayList<Integer> list = new ArrayList<>();
        HashMap<Integer, Integer> hm = new HashMap<>();

        for (int value : a) {
            if (hm.containsKey(value)) {
                int val = hm.get(value);
                hm.put(value, val + 1);
            } else
                hm.put(value, 1);
        }
        for (int value : b) {
            if (hm.containsKey(value)) {

                int x = hm.get(value);
                if (x <= 0)
                    hm.remove(value);
                else {
                    list.add(value);
                    hm.put(value, x - 1);
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("India", 1);
        hm.put("Australia", 2);
        hm.put("UK", 4);
        hm.put("US", 3);
//        System.out.println(hm);
        int[] ar = new int[6];

        Set<String> keys = hm.keySet();
//        System.out.println(keys);
        for (String key : keys) {
            hm.get(key);
        }
    }
}

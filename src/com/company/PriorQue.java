package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class PriorQue {

    public static class MedianPriorityQueue {
        PriorityQueue<Integer> left;
        PriorityQueue<Integer> right;

        public MedianPriorityQueue() {
            left = new PriorityQueue<>(Collections.reverseOrder());
            right = new PriorityQueue<>();
        }

        public void add(int val) {
            // write your code here
            left.add(val);
            if (right.size() > 0 && left.peek() > right.peek()) {
                int x = left.remove();
                left.add(right.remove());
                right.add(x);
            }
            if (left.size() - right.size() > 1) {
                right.add(left.remove());
            }


        }

        public int remove() {
            // write your code here
            if (left.size() == 0) {
                System.out.println("Underflow");
                return -1;
            }
            int x = left.remove();
            if (left.size() < right.size())
                left.add(right.remove());
            return x;
        }

        public int peek() {
            // write your code here
            if (left.size() == 0) {
                System.out.println("Underflow");
                return -1;
            }
            return left.peek();
        }

        public int size() {
            // write your code here
            return left.size() + right.size();
        }
    }

    public static class Pair implements Comparable<Pair> {
        int li;
        int di;
        int val;

        public Pair(int li, int di, int val) {
            this.li = li;
            this.di = di;
            this.val = val;
        }

        @Override
        public int compareTo(Pair o) {
            return this.val - o.val;
        }
    }

    public static class PriorityQ {
        ArrayList<Integer> data;

        public PriorityQ() {
            data = new ArrayList<>();
        }
        public PriorityQ(int[] arr) {
            data = new ArrayList<>();
            for (int val:arr){
                data.add(val);
            }
            for (int i=data.size()/2 -1;i>=0;i--){
                downHeapify(i);
            }
        }
        public void add(int val) {
            // write your code here
            data.add(val);
            upHeapify(data.size()-1);
        }

        public int remove() {
            // write your code here
            if (data.size() == 0) {
                System.out.println("Underflow");
                return -1;
            }
            int rt=data.get(0);
            swap(0,data.size()-1);
            data.remove(data.size()-1);
            downHeapify(0);
            return rt;
        }

        public int peek() {
            if (data.size() == 0) {
                System.out.println("Underflow");
                return -1;
            }
            return data.get(0);
        }

        public int size() {
            // write your code here
            return data.size();
        }


        private void upHeapify(int i) {
            int pi = (i - 1) / 2;
            if (data.get(i) < data.get(pi)) {
                swap(i,pi);
                upHeapify(pi);
            }
        }
        private void downHeapify(int i) {
            int min=i;
            int cl = 2*i+1;
            int cr = 2*i+2;
            if(cl<data.size()&&data.get(cl)<data.get(min)){
                min=cl;
            }
            if(cr<data.size()&&data.get(cr)<data.get(min)){
                min=cr;
            }
            if(min!=i){
                swap(min,i);
                downHeapify(min);
            }

        }
        private void swap(int i, int j){
            int temp = data.get(i);
            data.set(i, data.get(j));
            data.set(j, temp);
        }
    }




    public static ArrayList<Integer> mergeKSortedLists(ArrayList<ArrayList<Integer>> lists) {
        ArrayList<Integer> rv = new ArrayList<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (int i = 0; i < lists.size(); i++) {
            Pair pr = new Pair(i, 0, lists.get(i).get(0));
            pq.add(pr);
        }

        while (pq.size() > 0) {
            Pair p = pq.remove();
            rv.add(p.val);
            if (lists.get(p.li).size() > p.di + 1) {
                Pair np = new Pair(p.li, p.di + 1, lists.get(p.li).get(p.di + 1));
                pq.add(np);
            }
        }

        // write your code here

        return rv;
    }

    public static void kLargest(int[] arr, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            if (i < k)
                pq.add(arr[i]);
            else {
                if (arr[i] > pq.peek()) {
                    pq.remove();
                    pq.add(arr[i]);
                }
            }
        }
        while (pq.size() > 0) {
            System.out.println(pq.peek());
            pq.remove();
        }
    }

    public static void sortNearlySorted(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int j = 0; j < k + 1; j++) {
            pq.add(arr[j]);
        }
        for (int j = k + 1; j < arr.length; j++) {
            System.out.println(pq.remove());
            pq.add(arr[j]);
        }
        while (pq.size() > 0) {
            System.out.println(pq.remove());
        }
    }


    public static void main(String[] args) {

    }
}

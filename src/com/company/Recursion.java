package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Recursion {

    public static int powerLogarithmic(int x, int n) {
        int ans;
        if (n == 0)
            return 1;
        if (n % 2 == 0)
            ans = powerLogarithmic(x, n / 2) * powerLogarithmic(x, n / 2);
        else
            ans = powerLogarithmic(x, n / 2) * powerLogarithmic(x, n / 2) * x;
        return ans;
    }

    public static void twoRecursionCalls(int n) {
        if (n == 0)
            return;
        System.out.println("Pre " + n);
        twoRecursionCalls(n - 1);
        System.out.println("In " + n);
        twoRecursionCalls(n - 1);
        System.out.println("Post " + n);
    }

    public static void towerOfHanoi(int n, String A, String B, String C) {
        if (n == 0)
            return;
        towerOfHanoi(n - 1, A, C, B);
        System.out.println(n + "[" + A + " -> " + B + "]");
        towerOfHanoi(n - 1, C, B, A);
    }

    static int maxA = Integer.MIN_VALUE;

    public static void maxOfArray(int[] arr, int i) {
        if (i == arr.length)
            return;
        if (arr[i] > maxA)
            maxA = arr[i];
        maxOfArray(arr, i + 1);
    }

    //same as above
    public static int maxOfArrayReturn(int[] arr, int i) {
        if (arr.length == i)
            return arr[arr.length - 1];
        int mvr = Math.max(maxOfArrayReturn(arr, i + 1), arr[i]);
        return mvr;
    }

    public static int returnIdxOfFirstOcr(int[] arr, int i, int data) {
        if (arr.length == i)
            return -1;
        if (arr[i] == data)
            return i;
        return returnIdxOfFirstOcr(arr, i + 1, data);
    }

    //size=arr.length-1 in start
    public static int returnIdxOfLastOcr(int[] arr, int size, int data) {
        if (size == -1)
            return -1;
        if (arr[size] == data)
            return size;
        return returnIdxOfLastOcr(arr, size - 1, data);
    }

    public static ArrayList<Integer> returnAllIndices(int[] arr, int i, int data) {
        if (i == arr.length)
            return new ArrayList<>();
        ArrayList<Integer> arl = new ArrayList<>();
        if (arr[i] == data) {
            arl.add(i);
        }
        arl.addAll(returnAllIndices(arr, i + 1, data));
        return arl;
    }

    public static ArrayList<String> printSubsequence(String str) {

        if (str.length() == 0) {
            ArrayList<String> a = new ArrayList<>();
            a.add("");
            return a;
        }

        char ch = str.charAt(0);
        String st = str.substring(1);
        ArrayList<String> arrayList = printSubsequence(st);
        ArrayList<String> result = new ArrayList<>(arrayList);
        for (String i : arrayList) {
            result.add(ch + i);

        }
        return result;
    }

    static String[] codes = {".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz"};

    public static ArrayList<String> getKeyPadCharacter(String str) {
        if (str.length() == 0) {
            ArrayList<String> s = new ArrayList<>();
            s.add("");
            return s;
        }
        char ch = str.charAt(0);
        int num = Integer.parseInt(String.valueOf(ch));


        String restString = str.substring(1);
        ArrayList<String> recursionResult = getKeyPadCharacter(restString);
        ArrayList<String> result = new ArrayList<>();
        for (int j = 0; j < codes[num].length(); j++) {
            char fromNum = codes[num].charAt(j);
            for (String i : recursionResult) {
                result.add(fromNum + i);
            }
        }
        return result;
    }

    public static ArrayList<String> getStairsPath(int n) {

        if (n == 0) {
            ArrayList<String> x = new ArrayList<>();
            x.add("");
            return x;
        } else if (n < 0)
            return new ArrayList<>();
        ArrayList<String> n1 = getStairsPath(n - 1);
        ArrayList<String> n2 = getStairsPath(n - 2);
        ArrayList<String> n3 = getStairsPath(n - 3);
        ArrayList<String> result = new ArrayList<>();
        for (String i : n1) {
            result.add("1" + i);
        }
        for (String i : n2) {
            result.add("2" + i);
        }
        for (String i : n3) {
            result.add("3" + i);
        }
        return result;

    }

    public static ArrayList<String> getMazePath(int sx, int sy, int dx, int dy) {

        if (dy == sy && dx == sx) {
            ArrayList<String> s = new ArrayList<>();
            s.add("");
            return s;
        }

        ArrayList<String> hPath = new ArrayList<>();
        ArrayList<String> vPath = new ArrayList<>();
        if (dx > sx) {
            hPath = getMazePath(sx + 1, sy, dx, dy);
        }
        if (dy > sy) {
            vPath = getMazePath(sx, sy + 1, dx, dy);
        }
        ArrayList<String> result = new ArrayList<>();
        for (String i : hPath) {
            result.add("h" + i);
        }
        for (String i : vPath) {
            result.add("v" + i);
        }

        return result;
    }


    public static ArrayList<String> getMazePathJump(int sx, int sy, int dx, int dy) {
        if (dy == sy && dx == sx) {
            ArrayList<String> s = new ArrayList<>();
            s.add("");
            return s;
        }
        ArrayList<String> hPath = new ArrayList<>();
        ArrayList<String> vPath = new ArrayList<>();
        for (int i = 1; i <= dx - sx; i++) {
            ArrayList<String> n = getMazePathJump(sx + i, sy, dx, dy);
            for (String s : n) {
                hPath.add("h" + i + s);
            }
        }
        for (int i = 1; i <= dy - sy; i++) {
            ArrayList<String> n = getMazePathJump(sx, sy + i, dx, dy);
            for (String s : n) {
                hPath.add("v" + i + s);
            }
        }

        for (int i = 1; i <= dy - sy && i <= dx - sx; i++) {
            ArrayList<String> n = getMazePathJump(sx + i, sy + i, dx, dy);
            for (String s : n) {
                hPath.add("d" + i + s);
            }
        }

        return hPath;
    }

    public static ArrayList<String> printPermutation(String str) {
        if (str.length() == 0) {
            ArrayList<String> s = new ArrayList<>();
            s.add("");
            return s;
        }
        ArrayList<String> m = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            String lPart = str.substring(0, i);
            String rPart = str.substring(i + 1);
            ArrayList<String> al = printPermutation(lPart + rPart);
            for (String s : al) {
                m.add(str.charAt(i) + s);
            }
        }
        return m;
    }

    public static void printEncodings(String str, String asf) {
        if (str.length() == 0) {
            System.out.println(asf);
            return;
        } else if (str.length() == 1) {
            char ch = str.charAt(0);
            if (ch == '0')
                return;
            else {
                int cha = ch - '0';
                char code = (char) ('a' + cha - 1);
                System.out.println(asf + code);
                return;
            }
        }
        char ch = str.charAt(0);
        String roq = str.substring(1);
        if (ch == '0')
            return;
        else {
            int cha = ch - '0';
            char code = (char) ('a' + cha - 1);
            printEncodings(roq, asf + code);
        }

        String ch12 = str.substring(0, 2);
        String ro = str.substring(2);
        int ch12v = Integer.parseInt(ch12);
        if (ch12v <= 26) {
            char code = (char) ('a' + ch12v - 1);

            printEncodings(ro, asf + code);
        }

    }

    public static void floodFill(int[][] maze, int row, int col, String asf) {

        if (row < 0 || col < 0 || row == maze.length || col == maze[0].length || maze[row][col] == 1
                || maze[row][col] == 2) {
            return;
        }
        if (row == maze.length - 1 && col == maze[0].length - 1) {
            System.out.println(asf);
            return;
        }
        maze[row][col] = 2;
        floodFill(maze, row - 1, col, asf + "t");
        floodFill(maze, row, col - 1, asf + "l");
        floodFill(maze, row + 1, col, asf + "d");
        floodFill(maze, row, col + 1, asf + "r");
        maze[row][col] = 0;
    }

    public static void targetSumSubset(int[] arr, int idx, int target, int stn, String result) {

        if (idx == arr.length) {
            if (stn == target) {
                System.out.println(result + ".");
            }
            return;
        }
        //            stn+=stn+arr[idx];

        targetSumSubset(arr, idx + 1, target, stn + arr[idx], result + arr[idx] + ",");
        targetSumSubset(arr, idx + 1, target, stn, result);
    }

    public static void printNQueens(int[][] arr, int n, int row, String csf) {

        if (row == n) {
            System.out.println(csf + ".");
            return;
        }

        for (int i = 0; i < n; i++) {
            if (isQueenSafe(arr, row, i) && row != i) {
                arr[row][i] = 1;
                printNQueens(arr, n, row + 1, csf + row + "-" + i + ", ");
                arr[row][i] = 0;
            }
        }
    }

    private static boolean isQueenSafe(int[][] arr, int r, int c) {

        for (int i = r - 1; i >= 0; i--) {
            if (arr[i][c] == 1) {
                return false;
            }
        }
        for (int i = r - 1, j = c - 1; i >= 0 && j >= 0; i--, j--) {
            if (arr[i][j] == 1) {
                return false;
            }
        }
        for (int i = r - 1, j = c + 1; i >= 0 && j < arr.length; i--, j++) {
            if (arr[i][j] == 1) {
                return false;
            }
        }
        return true;
    }


    public static void knightsTour(int[][] arr, int r, int c, int n) {
        if(!isInBoard(r,c,arr.length)||arr[r][c]>0){
            return;
        }
       else if (n == arr.length * arr.length) {
            arr[r][c] = n;
            displayBoard(arr);
            arr[r][c] = 0;
            return;
        }
        arr[r][c] = n;
//        System.out.println("pre "+n);
            knightsTour(arr, r - 2, c + 1, n + 1);

            knightsTour(arr, r - 1, c + 2, n + 1);

            knightsTour(arr, r + 1, c + 2, n + 1);

            knightsTour(arr, r + 2, c + 1, n + 1);

            knightsTour(arr, r + 2, c - 1, n + 1);

            knightsTour(arr, r + 1, c - 2, n + 1);

            knightsTour(arr, r - 1, c - 2, n + 1);

            knightsTour(arr, r - 2, c - 1, n + 1);

        arr[r][c] = 0;
//        System.out.println("post "+n);


    }

    private static boolean isInBoard(int r, int c, int length) {
        if (r < length && c < length && r >= 0 && c >= 0 ) {
            return true;
        }
        return false;
    }

    public static void displayBoard(int[][] chess) {
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[0].length; j++) {
                System.out.print(chess[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    public static void main(String[] args) {
//        System.out.println(powerLogarithmic(5,0));
//        towerOfHanoi(3,"a","b","c");

        int[] ar = {10, -4, 8, 0, 56, 89, 0, 7, 8, 9, 0, 9, 888, 9};
        int[] arr = {10, 20, 30, 40, 50};
//        maxOfArray(ar,0);
//        System.out.println(maxA);
//        System.out.println(returnIdxOfFirstOcr(ar,0,888));
//        ArrayList<Integer> arl = returnAllIndices(ar, 0, 9);
//        ArrayList<String> s = printPermutation("abc");
////        System.out.println(s);

//        printEncodings("1203","");
//        Scanner sc = new Scanner(System.in);
//        int n= sc.nextInt();
//        int m= sc.nextInt();
//        int[][] arr = new int[n][m];
//
//        for (int i=0;i<arr.length;i++){
//            for (int j=0;j<arr[0].length;j++) {
//                arr[i][j]=sc.nextInt();
//            }
//            }

//        targetSumSubset(arr,0,60,0,"");
        int[][] chess = new int[5][5];
        knightsTour(chess,2,0,1);
    }

}

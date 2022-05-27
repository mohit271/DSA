package com.company;

public class BinarySearchTree {

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static Node construct(Integer[] arr, int lo, int hi) {

        if (lo > hi) {
            return null;
        }
        int mid = (lo + hi) / 2;
        int data = arr[mid];
        Node left = construct(arr, lo, mid - 1);
        Node right = construct(arr, mid + 1, hi);
        Node cur = new Node(data, left, right);
        return cur;

    }

    public static void display(Node node) {

        if (node == null)
            return;

        if (node.left == null)
            System.out.print(". ");
        else
            System.out.print(node.left.data + " ");

        System.out.print(node.data + " ");

        if (node.right == null)
            System.out.println(". ");
        else
            System.out.println(node.right.data);
        display(node.left);
        display(node.right);
    }

    public static int size(Node node) {
        if (node == null)
            return 0;
        int lc = size(node.left);
        int rc = size(node.right);

        return lc + rc + 1;
    }

    public static int sum(Node node) {
        if (node == null)
            return 0;

        int ls = sum(node.left);
        int rs = sum(node.right);

        return ls + rs + node.data;
    }

    public static int min(Node node) {
        if (node.left == null)
            return node.data;
        return min(node.left);
    }

    public static int max(Node node) {
        if (node.right == null)
            return node.data;
        return max(node.right);
    }

    public static boolean find(Node node, int data) {
        if (node == null)
            return false;
        if (data == node.data)
            return true;
        else if (data < node.data)
            return find(node.left, data);
        else
            return find(node.right, data);
    }

    public static void add1(Node node, int data) {
        if (data < node.data) {
            if (node.left == null) {
                node.left = new Node(data, null, null);
                return;
            }
            add1(node.left, data);
        } else if (data > node.data) {
            if (node.right == null) {
                node.right = new Node(data, null, null);
                return;
            }
            add1(node.right, data);
        }
    }

    public static Node add2(Node node, int data) {
        if (node == null)
            return new Node(data, null, null);
        if (data < node.data)
            node.left = add2(node.left, data);
        else if (data > node.data)
            node.right = add2(node.right, data);
        return node;
    }

    public static Node remove(Node node, int data) {
        if (node == null)
            return null;

        if (data < node.data) {
            node.left = remove(node.left, data);
        } else if (data > node.data)
            node.right = remove(node.right, data);
        else {
            if (node.left != null && node.right != null) {
                int lm = max(node.left);
                node.data = lm;
                node.left = remove(node.left, lm);
                return node;
            } else if (node.left != null) {
                return node.left;
            } else if (node.right != null) {
                return node.right;
            } else {
                return null;
            }
        }
        return node;
    }


    static int wholeSum;

    public static void replaceSumOfLarger(Node node) {
        if (node == null)
            return;
        //right can also be called first, resulting in decreasing order of numbers
        replaceSumOfLarger(node.left);
        wholeSum = wholeSum - node.data;
        node.data = wholeSum;

        replaceSumOfLarger(node.right);
    }

    public static int lca(Node node, int a, int b) {

        if (a > node.data && b > node.data) {
            return lca(node.right, a, b);
        } else if (a < node.data && b < node.data) {
            return lca(node.left, a, b);
        } else {
            return node.data;
        }

    }

    public static void printInRange(Node node, int lo, int hi) {
        if (node == null)
            return;
        printInRange(node.left, lo, hi);
        if (node.data >= lo && node.data <= hi)
            System.out.println(node.data);
        printInRange(node.right, lo, hi);
    }

    public static void printInRangeEf(Node node, int lo, int hi) {
        if (node == null)
            return;
        if (lo < node.data && hi < node.data)
            printInRangeEf(node.left, lo, hi);
        else if (lo > node.data && hi > node.data)
            printInRangeEf(node.right, lo, hi);
        else {
            printInRangeEf(node.left, lo, hi);
            System.out.println(node.data);
            printInRangeEf(node.right, lo, hi);
        }
    }

    public static void tsm1(Node root, Node node, int target) {
        if (node == null)
            return;
        tsm1(root, node.left, target);
        int fin = target - node.data;
        if (fin > node.data) {
            if (find(node, fin))
                System.out.println(node.data + " " + fin);
        }
        tsm1(root, node.right, target);
    }

    public static class ITPair {
        Node node;
        int state = 0;

        ITPair(Node node, int state) {
            this.node = node;
            this.state = state;
        }

    }

    public static void bestApproach(Node node, int target) {

    }

    public static void main(String[] args) {
        Integer[] arr = {12, 25, 37, 50, 62, 75, 87};
        Node root = construct(arr, 0, arr.length - 1);
        display(root);
        wholeSum = sum(root);
        System.out.println(wholeSum);
        replaceSumOfLarger(root);
        display(root);
    }
}

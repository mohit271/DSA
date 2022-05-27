package com.company;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {


    public static class Node {
        int data;
        Node left;
        Node right;

        Node() {
        }

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static class Pair {
        Node node;
        int state;

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
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

    public static void preOrder(Node node) {
        if (node == null)
            return;
        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public static void inOrder(Node node) {
        if (node == null)
            return;
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    public static void postOrder(Node node) {
        if (node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + " ");
    }

    public static int size(Node node) {
        if (node == null)
            return 0;

        return size(node.left) + size(node.right) + 1;
    }

    public static int max(Node node) {
        if (node == null)
            return Integer.MIN_VALUE;
        return Math.max(node.data, Math.max(max(node.left), max(node.right)));
    }

    public static int sum(Node node) {
        if (node == null)
            return 0;
        return node.data + sum(node.left) + sum(node.right);
    }

    public static int height(Node node) {
        if (node == null)
            return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public static void levelOrderTraversal(Node node) {
        //remove print add
        Queue<Node> q = new ArrayDeque<>();
        q.add(node);
        while (q.size() > 0) {
            int qs = q.size();
            for (int i = 0; i < qs; i++) {
                Node cur = q.remove();
                System.out.print(cur.data + " ");
                if (cur.left != null)
                    q.add(cur.left);
                if (cur.right != null)
                    q.add(cur.right);
            }
            System.out.println();
        }
    }

    public static void iterativeOrder(Node node) {
        Stack<Pair> st = new Stack<>();
        st.add(new Pair(node, 1));
        while (!st.isEmpty()) {
            Pair p = st.peek();

            if (p.state == 1) {
                System.out.println("pre " + p.node.data);
                p.state = 2;
                if (p.node.left != null) {
                    st.push(new Pair(p.node.left, 1));
                }
            } else if (p.state == 2) {
                System.out.println("in " + p.node.data);
                p.state = 3;
                if (p.node.right != null) {
                    st.push(new Pair(p.node.right, 1));
                }
            } else {
                System.out.println("post " + p.node.data);
                st.pop();
            }

        }
    }

    static ArrayList<Node> path;

    public static boolean findAndNodeToRoot(Node node, int data) {
        if (node == null)
            return false;
        if (node.data == data) {
            path.add(node);
            return true;
        }
        if (findAndNodeToRoot(node.left, data)) {
            path.add(node);
            return true;
        }
        if (findAndNodeToRoot(node.right, data)) {
            path.add(node);
            return true;
        }

        return false;
    }

    public static void printNodesKLevel(Node node, int data, int k) {
        path = new ArrayList<>();
        findAndNodeToRoot(node, data);
        for (int i = 0; i < path.size(); i++) {
            printKDown2(path.get(i), k - i, i == 0 ? null : path.get(i - 1));
        }
    }

    public static void pathToLeafRoot(Node node, String str, int sum, int lo, int hi) {
        if (node == null)
            return;
        if (node.left == null && node.right == null) {
            sum = sum + node.data;
            if (sum < hi && sum > lo) {
                str = str + node.data + " ";
                System.out.println(str);
            } else
                return;
        }

        pathToLeafRoot(node.left, str + " " + node.data, sum + node.data, lo, hi);
        pathToLeafRoot(node.right, str + " " + node.data, sum + node.data, lo, hi);

    }

    public static void transferToLeftCloneTree(Node node) {
        if (node == null)
            return;
        Node newNode = new Node();
        newNode.data = node.data;
        Node temp = node.left;
        node.left = newNode;
        newNode.left = temp;
        transferToLeftCloneTree(node.left.left);
        transferToLeftCloneTree(node.right);

    }

    public static void backToNormalTree(Node node) {
        if (node == null)
            return;
        if (node.left != null)
            node.left = node.left.left;
        backToNormalTree(node.left);
        backToNormalTree(node.right);
    }

    public static Node backToNormalTreeNode(Node node) {
        if (node == null)
            return null;
        Node lnn = backToNormalTreeNode(node.left.left);
        Node rnn = backToNormalTreeNode(node.right);
        node.left = lnn;
        node.right = rnn;
        return node;
    }

    public static void printSingleChild(Node node) {
        if (node == null)
            return;
        if (node.left == null && node.right != null)
            System.out.println(node.right.data);
        else if (node.right == null && node.left != null)
            System.out.println(node.left.data);

        printSingleChild(node.left);
        printSingleChild(node.right);

    }

    public static Node removeLeaves(Node node) {
        if (node == null)
            return null;
        if (node.left == null && node.right == null)
            return null;

        node.left = removeLeaves(node.left);
        node.right = removeLeaves(node.right);
        return node;
    }

    static int dia1;

    public static int diameterUsingHeight(Node node) {

        if (node == null)
            return 0;
        int ld = diameterUsingHeight(node.left);
        int rd = diameterUsingHeight(node.right);

        int h = height(node.left) + height(node.right) + 2;


        return Math.max(h, Math.max(ld, rd));
    }

    static class DiaPair {
        int ht;
        int dia;
    }

    public static DiaPair diameterUsingPair(Node node) {
        if (node == null) {
            DiaPair dp = new DiaPair();
            dp.ht = -1;
            dp.dia = 0;
            return dp;
        }
        DiaPair ldp = diameterUsingPair(node.left);
        DiaPair rdp = diameterUsingPair(node.right);

        DiaPair ndp = new DiaPair();
        ndp.ht = Math.max(ldp.ht, rdp.ht) + 1;

        int cd = ldp.ht + rdp.ht + 2;
        ndp.dia = Math.max(cd, Math.max(ldp.dia, rdp.dia));
        return ndp;
    }

    static int tilt = 0;

    public static int tiltOfTree(Node node) {
        if (node == null)
            return 0;
        int lt = tiltOfTree(node.left);
        int rt = tiltOfTree(node.right);
        tilt = tilt + Math.abs(lt - rt);
        return lt + rt + node.data;
    }

    static boolean isBalanced = true;

    static class BalPair {
        int ht;
        boolean isBal;
    }

    public static int isBalancedTree(Node node) {
        if (node == null)
            return -1;
        int lh = isBalancedTree(node.left);
        int rh = isBalancedTree(node.right);

        if (Math.abs(lh - rh) > 1)
            isBalanced = false;

        return Math.max(lh, rh) + 1;
    }

    public static BalPair isBalancedTree2(Node node) {
        if (node == null) {
            BalPair np = new BalPair();
            np.ht = -1;
            np.isBal = true;
            return np;
        }

        BalPair lh = isBalancedTree2(node.left);
        BalPair rh = isBalancedTree2(node.right);

        BalPair rp = new BalPair();
        rp.ht = Math.max(lh.ht, rh.ht) + 1;
        if (Math.abs(lh.ht - rh.ht) > 1 || !lh.isBal || !rh.isBal)
            rp.isBal = false;
        else
            rp.isBal = true;

        return rp;
    }

    static class MinMax {
        int min;
        int max;
        boolean isBSt;
        int size;
        int data;
    }

    public static MinMax isBinarySearchTree(Node node) {
        if (node == null) {
            MinMax nm = new MinMax();
            nm.max = Integer.MIN_VALUE;
            nm.min = Integer.MAX_VALUE;
            nm.isBSt = true;
            nm.size = 0;
            return nm;
        }

        MinMax lt = isBinarySearchTree(node.left);
        MinMax rt = isBinarySearchTree(node.right);
        MinMax rm = new MinMax();
        if (node.data < lt.max || node.data > rt.min) {
            rm.isBSt = false;
        } else {
            rm.isBSt = true;
        }
        rm.min = Math.min(node.data, Math.min(lt.min, rt.min));
        rm.max = Math.max(node.data, Math.max(lt.max, rt.max));
        if (rm.isBSt) {
            rm.size = lt.size + rt.size + 1;
            rm.data = node.data;
        } else if (lt.size > rt.size) {
            rm.size = lt.size;
            rm.data = lt.data;
        } else {
            rm.size = rt.size;
            rm.data = rt.data;
        }
        return rm;
    }

    public static void printKDown2(Node node, int k, Node block) {
        if (node == null && k < 0) {
            return;
        }
        if (k == 0) {
            System.out.println(node.data);
        }
        printKDown2(node.left, k - 1, block);
        printKDown2(node.right, k - 1, block);
    }

    public static void printKDown(Node node, int depth, int ques) {
        if (node == null || ques < 0)
            return;
//        if(ques==0){
//            System.out.println(node.data);
//            return;
//        }

        if (ques == depth) {
            System.out.println(node.data);

        }

        printKDown(node.left, depth + 1, ques);
        printKDown(node.right, depth + 1, ques);

    }

    public static void nodeToRootPath(Node node, int data) {
        if (node == null)
            return;
    }

    public static void main(String[] args) {
        Integer[] arr = {50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null, null};

        Stack<Pair> st = new Stack<>();
        Node root = new Node(arr[0], null, null);
        Pair rp = new Pair(root, 1);
        st.add(rp);
        int i = 1;
        while (!st.isEmpty()) {

            Pair temp = st.peek();
            if (temp.state == 1) {
                if (arr[i] != null) {
                    Node ln = new Node(arr[i], null, null);
                    temp.node.left = ln;
                    Pair np = new Pair(ln, 1);
                    st.push(np);
                } else
                    temp.node.left = null;
                temp.state++;
                i++;
            } else if (temp.state == 2) {
                if (arr[i] != null) {
                    Node rn = new Node(arr[i], null, null);
                    ;
                    temp.node.right = rn;
                    Pair np = new Pair(rn, 1);
                    st.push(np);
                } else
                    temp.node.right = null;
                temp.state++;
                i++;
            } else {
                st.pop();
            }
        }

//        display(root);
//        System.out.println(size(root));
//        System.out.println(max(root));
//        System.out.println(sum(root));
//        System.out.println(height(root));
//        preOrder(root);
//        System.out.println();
//        inOrder(root);
//        System.out.println();
//        postOrder(root);
//        levelOrderTraversal(root);
//        iterativeOrder(root);
//        path= new ArrayList<>();
//        System.out.println(findAndNodeToRoot(root,70));
//        System.out.println(path);
//        printKDown(root,1,3);
        printNodesKLevel(root, 25, 2);
    }
}

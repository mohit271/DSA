package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GenericTree {


    // Represent a Single Node
    private static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();

        Node() {
        }
        //null node constructor
        Node(int data) {
            this.data = data;
        }
    }

    public static class RepresentGenericTree implements Iterable<Integer>{
        Node root;
        RepresentGenericTree(Node root){
            this.root=root;
        }

        @Override
        public Iterator<Integer> iterator() {
            return null;
        }
    }

    public static class GTPreorderIterator implements Iterator<Integer>{
        Integer nval;

        public GTPreorderIterator(Node root) {

        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Integer next() {
            return null;
        }
    }
    /**
     * Constructing generic tree through an array
     */
    public static Node construct(int[] arr) {
        Node root = null;
        // -1 in the array denotes the closing of a open node. Root is closed at last
        //Using stack
        Stack<Node> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1) {
                stack.pop();
            } else {
                Node newNode = new Node();
                newNode.data = arr[i];
                if (!stack.isEmpty()) {
                    stack.peek().children.add(newNode);
                }
                else {
                    root = newNode;
                }
                stack.push(newNode);
            }
        }
        return root;
    }


    public static void display(Node node) {
        String str = node.data + "--> ";
        for (Node child : node.children) {
            str += child.data + ", ";
        }
        str += ". ";
        //display child of root node of method
        System.out.println(str);
        for (Node child : node.children) {
            //recursion for displaying child of all nodes
            display(child);
        }
    }

    //number of nodes
    public static int sizeOfTree(Node node) {
        int size = 0;
        for (Node cn : node.children) {
            size += sizeOfTree(cn);
        }
        //size of child + 1(itself)
        size = size + 1;
        return size;
    }

    public static int maxOfTree(Node node) {
        int max = node.data;
        for (Node cn : node.children) {
            //max of -> root data, children's max value
            max = Math.max(max, maxOfTree(cn));
        }
        return max;
    }

    public static int height(Node node) {
        // as height is the path length of deepest node not the number of nodes in that path
        int ht = -1;
        for (Node cn : node.children) {
            int ch = height(cn);
            ht = Math.max(ht, ch);
        }
        // add itself to the height received by childrens
        return ht + 1;
    }

    //using queue
    public static void levelOrderTraversal(Node node) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(node);
        while (q.size() > 0) {
            node = q.peek();
            System.out.println(q.peek().data);
            q.remove();
            for (Node cn : node.children) {
                q.add(cn);
            }
        }
    }

    //using two queue
    public static void levelOrderTraversalLineWise(Node node) {
        //remove print add child
        Queue<Node> mq = new ArrayDeque<>();
        mq.add(node);
        Queue<Node> cq = new ArrayDeque<>();
        while (mq.size() > 0) {
            node = mq.remove();
            System.out.print(node.data + " ");
            //add children in child queue
            for (Node cn : node.children) {
                cq.add(cn);
            }
            //if main queue is empty break the line and empty child queue into main queue
            if (mq.size() == 0) {
                mq = cq;
                cq = new ArrayDeque<>();
                System.out.println();
            }
        }
    }

    //zigzag line wise travel using two stack
    public static void levelOrderLineWiseZZ(Node node) {
        Stack<Node> mq = new Stack<>();
        mq.add(node);
        Stack<Node> cq = new Stack<>();
        //maintain level variable, add children's from start and end accordingly
        int level = 0;
        while (mq.size() > 0) {
            node = mq.pop();
            System.out.print(node.data + " ");

            if (level % 2 == 0) {
                for (int i = 0; i < node.children.size(); i++) {
                    cq.add(node.children.get(i));
                }
            } else {
                for (int j = node.children.size() - 1; j >= 0; j--) {
                    cq.add(node.children.get(j));
                }
            }
            if (mq.size() == 0) {
                mq = cq;
                cq = new Stack<>();
                System.out.println();
                level++;
            }
        }
    }

    // null or a marker variable to tell the level is changed
    public static void llTraversalDelimiter(Node node) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(node);
        q.add(new Node(-1));

        while (q.size() > 0) {
            node = q.remove();
            if (node.data != -1) {
                System.out.print(node.data + " ");
                for (Node cn : node.children) {
                    q.add(cn);
                }
            } else {
                if (q.size() > 0) {
                    q.add(new Node(-1));
                    System.out.println();
                }
            }
        }
    }

    //using the count of children
    public static void levelOrderCount(Node node) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(node);
        while (q.size() > 0) {
            int count = q.size();
            for (int i = 0; i < count; i++) {
                node = q.remove();
                System.out.print(node.data + " ");
                for (Node cn : node.children) {
                    q.add(cn);
                }
            }
            System.out.println();
        }
    }


    // pair class
    static class Pair {
        Node node;
        int state;
        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static void levelOrderPair(Node node) {
//        Using Pair, store node and it's level
        Queue<Pair> q = new ArrayDeque<>();
        int level = 0;
        q.add(new Pair(node, level));
        while (q.size() > 0) {
            Pair pair = q.remove();
            if(pair.state>level){
                level=pair.state;
                System.out.println();
            }
            System.out.print(pair.node.data+" ");

            for (Node cn : pair.node.children) {
                Pair pair1 = new Pair(cn, pair.state+1);
                q.add(pair1);
            }
        }
    }

    public static void traversal(Node node) {
        // euler's left, otw deep in recursion, node's pre data
        System.out.println("Node pre " + node.data);
        for (Node cn : node.children) {
            System.out.println("Edge pre " + node.data + "--" + cn.data);
            traversal(cn);
            System.out.println("Edge post " + node.data + "--" + cn.data);
        }
        System.out.println("Node post " + node.data);
        // euler's right, ote out of recursion, node's post area
    }

    public static void mirror(Node node) {

    }

    public static void removeLeaves(Node node) {
        //loop from back as from front it will skip some items after removing
        for (int i = node.children.size() - 1; i >= 0; i--) {
            Node child = node.children.get(i);
            if (child.children.size() == 0) {
                node.children.remove(child);
            }
        }
        for (Node cn : node.children) {
            removeLeaves(cn);
        }
    }

    public static Node getTail(Node node) {
        while (node.children.size() == 1) {
            node = node.children.get(0);
        }
        return node;
    }

    public static void linearizeTree(Node node) {

        for (Node cn : node.children) {
            linearizeTree(cn);
        }

        while (node.children.size() > 1) {
            Node lc = node.children.remove(node.children.size() - 1);
            Node sc = node.children.get(node.children.size() - 1);
            Node tail = getTail(sc);
            tail.children.add(lc);
        }

    }

    public static Node linearizeTree2(Node node) {

        if (node.children.size() == 0) {
            return node;
        }
        Node lkt = linearizeTree2(node.children.get(node.children.size() - 1));
        while (node.children.size() > 1) {
            Node lc = node.children.remove(node.children.size() - 1);
            Node sc = node.children.get(node.children.size() - 1);
            Node tailOfSc = linearizeTree2(sc);
            tailOfSc.children.add(lc);
        }

        return lkt;
    }

    public static boolean existInTree(Node node, int x) {
        boolean exists;
        if (node.data == x) {
            return true;
        }
        for (Node cn : node.children) {
            exists = existInTree(cn, x);
            if (exists)
                return true;
        }
        return false;
    }

    public static ArrayList<Integer> nodeToRootPath(Node node, int x) {

        if (node.data == x) {
            ArrayList<Integer> arrayList = new ArrayList();
            arrayList.add(node.data);
            return arrayList;
        }

        for (Node cn : node.children) {
            ArrayList<Integer> pt = nodeToRootPath(cn, x);
            if (pt.size() > 0) {
                pt.add(node.data);
                return pt;
            }
        }
        return new ArrayList<>();
    }

    public static int lowestCommonAncestor(Node node, int x, int y) {
        ArrayList<Integer> xa = nodeToRootPath(node, x);
        ArrayList<Integer> ya = nodeToRootPath(node, y);

        int i = xa.size() - 1;
        int j = ya.size() - 1;
        while (i >= 0 && j >= 0 && xa.get(i).equals(ya.get(i))) {
            i--;
            j--;
        }
        return xa.get(j + 1);
    }

    public static int distanceBetweenTwoNodes(Node node, int x, int y) {
        ArrayList<Integer> xa = nodeToRootPath(node, x);
        ArrayList<Integer> ya = nodeToRootPath(node, y);

        int i = xa.size() - 1;
        int j = ya.size() - 1;
        while (i >= 0 && j >= 0 && xa.get(i).equals(ya.get(i))) {
            i--;
            j--;
        }
        i++;
        j++;
        return i + j;
    }

    public static boolean isTreeAreSame(Node n1, Node n2) {
        if (n1.children.size() != n2.children.size()) {
            return false;
        }

        for (int i = 0; i < n1.children.size(); i++) {
            Node c1 = n1.children.get(i);
            Node c2 = n2.children.get(i);
            if (!isTreeAreSame(c1, c2)) {
                return false;
            }
        }
        return true;

    }

    public static boolean isTreeMirror(Node n1, Node n2) {
        if (n1.children.size() != n2.children.size()) {
            return false;
        }

        for (int i = 0; i < n1.children.size(); i++) {
            int sz = n2.children.size() - 1;
            Node c1 = n1.children.get(i);
            Node c2 = n2.children.get(sz - i);
            if (!isTreeAreSame(c1, c2)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isTreeSymmetric(Node n1) {
        return isTreeMirror(n1, n1);
    }

    static int msSize = 0;
    static int msMin = Integer.MAX_VALUE;
    static int msMax = Integer.MIN_VALUE;
    static int msHeight = 0;

    //travel and change
    public static void multiSolver(Node node, int depth) {
        msSize++;
        msMin = Math.min(msMin, node.data);
        msMax = Math.min(msMax, node.data);
        msHeight = Math.min(msHeight, depth);
        for (Node cn : node.children) {
            multiSolver(cn, 0);
        }
    }

    static Node predecessor;
    static Node successor;
    static int PState = 0;

    public static void predecessorAndSuccessor(Node node, int data) {

        if (PState == 0) {
            if (node.data == data) {
                PState = 1;
            } else {
                predecessor = node;
            }
        } else if (PState == 1) {
            successor = node;
            PState = 2;
        }

        for (Node cn : node.children) {
            predecessorAndSuccessor(cn, data);
        }
    }

    static int cCeil = Integer.MAX_VALUE;
    static int fFloor = Integer.MIN_VALUE;

    public static void ceilAndFloor(Node node, int data) {
        if (node.data > data && node.data < cCeil) {
            cCeil = node.data;
        }
        if (node.data < data && node.data > fFloor) {
            fFloor = node.data;
        }

        for (Node cn : node.children) {
            ceilAndFloor(cn, data);
        }
    }

    public static void kthLargest(Node node, int k) {
        fFloor = Integer.MIN_VALUE;
        int f = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            ceilAndFloor(node, f);
            f = fFloor;
            fFloor = Integer.MIN_VALUE;
        }
        System.out.println(f);
    }

    static int subMax = Integer.MIN_VALUE;
    static Node subNode;

    public static int maxSubTreeSum(Node node) {

        int sum = node.data;
        for (Node cn : node.children) {
            int x = maxSubTreeSum(cn);
            sum = sum + x;
        }
        if (sum >= subMax) {
            subMax = sum;
            subNode = node;
        }
        return sum;

    }

    static int diameter = 0;

    public static int calculateDiaReturnHeight(Node node) {
        int dc = -1;
        int sdc = -1;
        for (Node cn : node.children) {
            int c = calculateDiaReturnHeight(cn);
            if (c > dc) {
                sdc = dc;
                dc = c;
            } else if (c > sdc) {
                sdc = c;
            }
        }
        if (diameter < dc + sdc + 2) {
            diameter = dc + sdc + 2;
        }
        dc += 1;
        return dc;
    }

    public static void iterativePreAndPostOrder(Node node) {
        Stack<Pair> st = new Stack<>();
        st.push(new Pair(node, -1));
        String pre = "";
        String post = "";
        while (st.size() > 0) {
            Pair top = st.peek();
            if (top.state == -1) {
                pre += top.node.data + " ";
                top.state += 1;
            } else if (top.state == top.node.children.size()) {
                post += top.node.data + " ";
                st.pop();
            } else {
                st.push(new Pair(top.node.children.get(top.state), -1));
                top.state += 1;
            }
        }
        System.out.println(pre);
        System.out.println(post);
    }




    public static void main(String[] args) throws IOException {
        int[] defaultArr = {10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1, 120, -1, -1, 90, -1, -1, 40, 100, -1, -1, -1};
        /** through input
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         int n = Integer.parseInt(br.readLine());
         int[] arr = new int[n];
         String[] values = br.readLine().split(" ");
         for (int i = 0; i < n; i++) {
         arr[i] = Integer.parseInt(values[i]);
         }
         */

//        int data = Integer.parseInt(br.readLine());

        Node root = construct(defaultArr);


//        display(root);

//        System.out.println(sizeOfTree(root));

//        System.out.println(height(root));

//        levelOrderPair(root);


//        boolean flag = existInTree(root, data);
//        System.out.println(flag);

//        display(root);
//        System.out.println(sizeTree(root));
//        System.out.println(maxOfTree(root));
//        System.out.println(height(root));
//        traversal(root);
//        levelOrderTraversal(root);
//        levelOrderTraversalLineWise(root);
//        levelOrderLineWiseZZ(root);
//        llTraversalDelimiter(root);
//        levelOrderCount(root);

//        predecessorAndSuccessor(root,10);
//        if(predecessor==null)
//            System.out.println(successor.data);
//        else if(successor==null)
//                System.out.println(predecessor.data);
//        else
//            System.out.println(predecessor.data+" "+successor.data);

//        ceilAndFloor(root,5);
//        System.out.println(cCeil+" "+fFloor);

//        kthLargest(root, 4);

//            maxSubTreeSum(root);
//        System.out.println(subNode.data+" "+subMax);
//        iterativePreAndPostOrder(root);

    }
}

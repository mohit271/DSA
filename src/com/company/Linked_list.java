package com.company;

import java.util.LinkedList;

public class Linked_list {


    public static class Node {
        int data;
        Node next;
    }

    public static class LinkList {
        Node head;
        Node tail;
        int size;

        void addLast(int val) {
            Node temp = new Node();
            temp.data = val;
            temp.next = null;

            if (size == 0) {
                head = tail = temp;
            } else {
                tail.next = temp;
                tail = temp;
            }

            size++;
        }

        public int size() {
            // write code here
            return size;
        }

        public void display() {
            // write code here
            Node node = new Node();
            node = head;
            while (node != null) {
                System.out.print(node.data + " ");
                node = node.next;
            }
            System.out.println();

        }

        public void removeFirst() {
            if (size == 0) {
                System.out.println("List is empty");
            } else if (size == 1) {
                head = tail = null;
                size = 0;
            } else {
                head = head.next;
                size--;
            }
        }

        public int getFirst() {
            if (size == 0) {
                System.out.println("List is empty");
                return -1;
            } else {
                return head.data;
            }
        }

        public int getLast() {
            if (size == 0) {
                System.out.println("List is empty");
                return -1;
            } else {
                return tail.data;
            }
        }

        public Node getAt(int idx) {
            if (size == 0) {
                System.out.println("List is empty");
                return null;
            } else if (idx < 0 || idx >= size) {
                System.out.println("Invalid arguments");
                return null;
            } else {
                Node temp = head;
                for (int i = 0; i < idx; i++) {
                    temp = temp.next;
                }
                return temp;
            }
        }

        public void addFirst(int val) {
            Node temp = new Node();
            temp.data = val;
            temp.next = head;
            head = temp;

            if (size == 0) {
                tail = temp;
            }

            size++;
        }

        public void addAt(int idx, int val) {
            if (idx < 0 || idx > size) {
                System.out.println("Invalid arguments");
            } else if (idx == 0) {
                addFirst(val);
            } else if (idx == size) {
                addLast(val);
            } else {
                Node node = new Node();
                node.data = val;

                Node temp = head;
                for (int i = 0; i < idx - 1; i++) {
                    temp = temp.next;
                }
                node.next = temp.next;

                temp.next = node;
                size++;
            }
        }

        public void removeLast() {
            if (size == 0) {
                System.out.println("List is empty");
            } else if (size == 1) {
                head = tail = null;
                size = 0;
            } else {
                Node temp = head;
                for (int i = 0; i < size - 2; i++) {
                    temp = temp.next;
                }

                tail = temp;
                tail.next = null;
                size--;
            }
        }

        public void removeAt(int idx) {
            if (idx < 0 || idx >= size) {
                System.out.println("Invalid arguments");
            } else if (idx == 0) {
                removeFirst();
            } else if (idx == size - 1) {
                removeLast();
            } else {
                Node temp = head;
                for (int i = 0; i < idx - 1; i++) {
                    temp = temp.next;
                }

                temp.next = temp.next.next;
                size--;
            }
        }

        public void reverseDI() {
            // write your code here
            int i=0,j=size;
            while(j>i){
                Node xi=getAt(i);
                Node xj=getAt(j-1);
                int temp=xi.data;
                xi.data=xj.data;
                xj.data=temp;
                j--;
                i++;
            }
        }

        public void reversePI(){
            Node cur=head;
            Node prev=null;
            while(cur!=null){
                Node temp=cur.next;

                cur.next=prev;

                prev=cur;
                cur=temp;
            }
            Node temp=head;
            head=tail;
            tail=temp;
        }

        public void reversePR(){
            reversePRHelp(head);
            head.next=null;
            Node temp =head;
            head =tail;
            tail=temp;
        }

        public void reverseDR(){
            left=head;
            reverseDRHelp(head,0 );
        }

        Node left;
        private void reverseDRHelp(Node node,int mid) {

            if(node==null)
                return;

            reverseDRHelp(node.next,mid+1);
            if(mid>=size/2) {
                int tempData = left.data;
                left.data = node.data;
                node.data = tempData;

                left = left.next;
            }

        }

        public int kthFromLast(int k) {
            // write your code here
            Node s= head;
            Node f= head;
            for(int i=0;i<k;i++){
                f=f.next;
            }
            while(f.next!=null){
                f=f.next;
                s=s.next;
            }
            return s.data;

        }
        public static LinkList mergeTwoSortedLists(LinkList l1, LinkList l2) {
            // write your code hered
            LinkList ll= new LinkList();
            Node l1n=l1.head;
            Node l2n=l2.head;
            while(l1n!=null&&l2n!=null){
                if(l1n.data>l2n.data){
                    ll.addLast(l2n.data);
                    l2n=l2n.next;
                }else{
                    ll.addLast(l1n.data);
                    l1n=l1n.next;
                }
            }
            Node n= new Node();
            if(l1n==null){
                n=l2n;
            }
            else
                n=l1n;
            while(n!=null){
                ll.addLast(n.data);
                n=n.next;
            }
            return ll;

        }


        public static LinkList mergeSort(Node head, Node tail){

            if(head==tail){
                LinkList ll= new LinkList();
                ll.addLast(head.data);
                return ll;
            }

            Node mid =midNode(head,tail);
            LinkList first= mergeSort(head,mid);
            LinkList second = mergeSort(mid.next,tail);

            LinkList lastList=mergeTwoSortedLists(first,second);
            return lastList;
        }
        public void removeDuplicatesSortedList(){
            // write your code here
            Node prev = head;
            Node cur = head.next;
            while(cur!=null){
                if(prev.data==cur.data&&cur==tail){
                    prev.next=cur.next;
                    tail=prev;
                }
                else if(prev.data==cur.data){
                    prev.next=cur.next;
                    cur=prev.next;
                }
                else{
                    prev=prev.next;
                    cur=cur.next;
                }
            }
        }

        public void kReverseLinkedList(int k){
            int nk= size/k;
            Node cur=head;
            Node prev =null;
            //using two linklist curr , prev
        }

        public boolean palindromeLlHelp(Node node) {
            if (node == null)
                return true;

            boolean right = palindromeLlHelp(node.next);
            if (node.data == palLeft.data && right){
                palLeft = palLeft.next;
            return true;
        }
            else{
                palLeft=palLeft.next;
                return false;
            }
        }
        Node palLeft;
        public boolean  palindromeLl(Node head){
            palLeft=head;
           return palindromeLlHelp(head);

        }

        Node foldLeft;
        private void  foldLinkedList(){
            foldLeft=head;
            foldLinkedListHelp(head,0);
        }

        public void foldLinkedListHelp(Node node,int floor){

            if(node==null )
                return;
            foldLinkedListHelp(node.next,floor+1);
            if(floor>size/2) {
                Node temp = foldLeft.next;
                foldLeft.next = node;
                node.next = temp;

                foldLeft = temp;
            }
            else if(floor==size/2){
                tail=node;
                tail.next=null;
            }

        }

        public void displayRecursive(Node node){
            if(node==null){
                return;
            }
            displayRecursive(node.next);
            System.out.print(node.data+" ");
        }

        public void reversePRHelp(Node node){
            if(node ==null)
                return;
            reversePRHelp(node.next);
            if(node!=tail){
                node.next.next=node;
            }

        }



    }
    public static class LLToStackAdapter {
        LinkedList<Integer> list;

        public LLToStackAdapter() {
            list = new LinkedList<>();
        }


        int size() {
            // write your code here
            return list.size();

        }

        void push(int val) {
            // write your code here
            list.addFirst(val);
        }

        int pop() {
            // write your code here
            if(size()==0){
                System.out.println("Stack Underflow");
                return -1;
            }
           return list.removeFirst();
        }

        int top() {
            // write your code here
            return list.getFirst();
        }
    }

    public static class LLToQueueAdapter {
        LinkedList<Integer> list;

        public LLToQueueAdapter() {
            list = new LinkedList<>();
        }

        int size() {
            // write your code here
            return list.size();
        }

        void add(int val) {
            // write your code here
            list.addLast(val);
        }

        int remove() {
            // write your code here
            if(size()==0){
                System.out.println("queue empty");
                return -1;
            }
            return list.removeFirst();
        }

        int peek() {
            // write your code here
            if(size()==0){
                System.out.println("queue empty");
                return -1;
            }
            return list.getFirst();
        }
    }

    static int sum;
    static int carry=0;
    public static LinkList addNumbersInLinkedList(LinkList a, LinkList b){
        LinkList res = new LinkList();
       int carry= addNumbersInLinkedListHelp(a.head,a.size,b.head,b.size,res);
       if(carry>0)
           res.addFirst(carry);
        return res;
    }
    public static int addNumbersInLinkedListHelp(Node one, int pv1, Node two, int pv2, LinkList res){

        if(one ==null&&two==null)
            return 0;
        if(pv1==pv2) {
           int x= addNumbersInLinkedListHelp(one.next, pv1 - 1, two.next, pv2 - 1,res);
            res.addFirst((one.data+two.data+x)%10);
            return (one.data+two.data)/10;
        }
        else if(pv1>pv2) {
           int x= addNumbersInLinkedListHelp(one.next, pv1 - 1, two, pv2, res);
            res.addFirst((one.data+x)%10);
            return  (one.data+x)/10;
        }
        else {
            int x=addNumbersInLinkedListHelp(one, pv1, two.next, pv2 - 1, res);
            res.addFirst((two.data+x)%10);
            return  (two.data)/10;
        }
    }

    public static Node midNode(Node head,Node tail){
        Node f= head;
        Node s= head;
        while(f!=tail&&f.next!=tail){
            f=f.next.next;
            s=s.next;
        }
        return s;
    }




    public static void main(String[] args) {

        LinkList ll = new LinkList();
        ll.addLast(1);
        ll.addLast(2);
        ll.addLast(3);
        ll.addLast(4);
        ll.addLast(6);
        ll.addLast(7);
        LinkList ll1 = new LinkList();
        ll1.addLast(5);
        ll1.addLast(4);
        ll1.addLast(6);
        ll1.addLast(7);

//      LinkList n=  addNumbersInLinkedList(ll1,ll);
//        n.display();

//        ll.foldLinkedList();


//        ll.reversePI();
//        ll.displayRecursive(ll.head);
//        ll.reverseDR();
//        ll.display();
    }
}

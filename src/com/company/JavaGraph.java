package com.company;

import java.util.*;

public class JavaGraph {

    public static class Edge {
        int src;
        int nbr;
        int wt;

        Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited) {
        if (src == dest)
            return true;

        visited[src] = true;
        for (int i = 0; i < graph[src].size(); i++) {
            Edge edge = graph[src].get(i);
            if (!visited[edge.nbr]) {
                boolean hasPathFrom = hasPath(graph, edge.nbr, dest, visited);
                if (hasPathFrom)
                    return true;
            }
        }
        return false;
    }

    public static void printAllPaths(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited, String psf) {
        if (src == dest) {
            System.out.println(psf);
            return;
        }

        visited[src] = true;
        for (int i = 0; i < graph[src].size(); i++) {
            Edge edge = graph[src].get(i);
            if (!visited[edge.nbr]) {
                printAllPaths(graph, edge.nbr, dest, visited, psf + edge.nbr);
            }
        }
        visited[src] = false;

    }

    static String sPath;
    static Integer sPathWt = Integer.MAX_VALUE;
    static String lPath;
    static Integer lPathWt = Integer.MIN_VALUE;
    static String cPath;
    static Integer cPathWt = Integer.MAX_VALUE;
    static String fPath;
    static Integer fPathWt = Integer.MIN_VALUE;

    static class Pair implements Comparable<Pair> {
        int wsf;
        String psf;

        public Pair(int wsf, String psf) {
            this.wsf = wsf;
            this.psf = psf;
        }

        @Override
        public int compareTo(Pair o) {
            return this.wsf - o.wsf;
        }
    }

    static PriorityQueue<Pair> pq = new PriorityQueue<>();

    public static void multiSolver(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited, int criteria, int k, String psf, int wsf) {
        if (src == dest) {
            if (wsf < sPathWt) {
                sPathWt = wsf;
                sPath = psf;
            }
            if (wsf > lPathWt) {
                lPathWt = wsf;
                lPath = psf;
            }
            if (wsf > criteria && wsf < cPathWt) {
                cPathWt = wsf;
                cPath = psf;
            }
            if (wsf < criteria && wsf > fPathWt) {
                fPathWt = wsf;
                fPath = psf;
            }
            pq.add(new Pair(wsf, psf));
            if (pq.size() > k) {
                pq.remove();
            }
            return;
        }

        visited[src] = true;
        for (int i = 0; i < graph[src].size(); i++) {
            Edge edge = graph[src].get(i);
            if (!visited[edge.nbr]) {
                multiSolver(graph, edge.nbr, dest, visited, criteria, k, psf + edge.nbr, wsf + edge.wt);
            }
        }
        visited[src] = false;

    }


    //running for each vertex and adding the result to
    public static void drawTree(ArrayList<Edge>[] graph, int src, ArrayList<Integer> ar, boolean[] visited) {
        visited[src] = true;
        ar.add(src);
        for (Edge e : graph[src]) {
            if (!visited[e.nbr]) {
                drawTree(graph, e.nbr, ar, visited);
            }
        }
    }

    public static void connectedGraph(int[][] ar, int i, int j, boolean[][] visited) {
        if (i < 0 || j < 0 || i >= ar.length || j >= ar[0].length || visited[i][j] || ar[i][j] == 1) {
            return;
        }
        visited[i][j] = true;
        connectedGraph(ar, i - 1, j, visited);
        connectedGraph(ar, i, j - 1, visited);
        connectedGraph(ar, i + 1, j, visited);
        connectedGraph(ar, i, j + 1, visited);
    }

    public static int numberOfIslands(int[][] ar) {
        int count = 0;
        boolean[][] visited = new boolean[ar.length][ar[0].length];
        for (int i = 0; i < ar.length; i++) {
            for (int j = 0; j < ar[0].length; j++) {
                if (ar[i][j] == 0 && !visited[i][j]) {
                    count++;
                    connectedGraph(ar, i, j, visited);
                }
            }
        }

        return count;
    }

    public static void hamiltonianPathCycle(ArrayList<Edge>[] graph, HashSet<Integer> visited, int src, String psf, int first) {

        if (visited.size() == graph.length-1) {
            for (Edge e : graph[src]) {
                if (e.nbr == first) {
                    System.out.println(psf  + "*");
                    return;
                }
            }
            System.out.println(psf + ".");
        }


        visited.add(src);
        for (Edge e : graph[src]) {
            if (!visited.contains(e.nbr))
                hamiltonianPathCycle(graph, visited, e.nbr, psf+e.nbr, first);
        }
        visited.remove(src);

    }
    public static void perfectFriends(ArrayList<Edge>[] graph, boolean[] visited, ArrayList<Integer> comp, int src) {

        visited[src] = true;
        comp.add(src);
        for (Edge e : graph[src]) {
            if (!visited[e.nbr]) {
                perfectFriends(graph, visited, comp, e.nbr);
            }
        }


    }

   static class BfsPair{
        int v;
        String psf;
        BfsPair(int v, String psf){
            this.v=v;
            this.psf=psf;
        }
    }

    static class Pair2{
        int v;
        int lev;
        Pair2(int v, int psf){
            this.v=v;
            this.lev=psf;
        }
    }
    /**
    r m* p a*
     **/
    public static void bfs(ArrayList<Edge>[] graph,int src,boolean[] visited){
        ArrayDeque<BfsPair> q= new ArrayDeque<>();

        q.add(new BfsPair(src,src+""));
        while (q.size()>0){
            BfsPair p= q.remove();
            if(!visited[p.v]) {
                System.out.println(p.v + "@" + p.psf);
                visited[p.v] = true;
                for (Edge e : graph[p.v]) {
                    if (!visited[e.nbr]) {
                        q.add(new BfsPair(e.nbr, p.psf + e.nbr));
                    }
                }
            }
        }
    }
    public static boolean isCyclic(ArrayList<Edge>[] graph){

        boolean[] visited= new boolean[graph.length];
        for (int i=0;i<graph.length;i++) {
            if (!visited[i]) {
                ArrayDeque<BfsPair> q = new ArrayDeque<>();
                q.add(new BfsPair(i, i + ""));
                while (q.size() > 0) {
                    BfsPair p = q.remove();
                    if (!visited[p.v]) {
                        visited[p.v] = true;
                        for (Edge e : graph[p.v]) {
                            if (!visited[e.nbr]) {
                                q.add(new BfsPair(e.nbr, p.psf + e.nbr));
                            }
                        }
                    }
                    else
                        return true;
                }
            }
        }
        return false;
    }

    public static boolean isBipartite(ArrayList<Edge>[] graph){

        boolean isCyclic=false;
        boolean[] visited= new boolean[graph.length];
        for (int i=0;i<graph.length;i++) {
            if (!visited[i]) {
                int count=1;
                ArrayDeque<BfsPair> q = new ArrayDeque<>();
                q.add(new BfsPair(i, i + ""));
                while (q.size() > 0) {
                    BfsPair p = q.remove();
                    if (!visited[p.v]) {
                        visited[p.v] = true;
                        for (Edge e : graph[p.v]) {
                            if (!visited[e.nbr]) {
                                q.add(new BfsPair(e.nbr, p.psf + e.nbr));
                                count++;
                            }
                        }
                    }
                    else {
                        isCyclic = true;
                        if(count%2==0)
                            return false;

                    }
                }
            }
        }
        return true;
    }
    public static int spreadOfInfection(ArrayList<Edge>[] graph, int src, int t){
        boolean[] visited= new boolean[graph.length];
        ArrayDeque<Pair2> q= new ArrayDeque<>();
        q.add(new Pair2(src,1));
        int count=0;
        while (q.size()>0) {
            Pair2 x = q.remove();
            if (!visited[x.v]){
                visited[x.v] = true;
                if(x.lev>t)
                    return count;
                count++;
            for (Edge e : graph[x.v]) {
                if (!visited[e.nbr]) {
                    q.add(new Pair2(e.nbr,x.lev+1));
                }
            }
        }

        }
        return count;
    }
    static class PairD implements Comparable<PairD> {
        int v;
        int wsf;
        String psf;

        public PairD(int v,int wsf, String psf) {
            this.v=v;
            this.wsf = wsf;
            this.psf = psf;
        }

        @Override
        public int compareTo(PairD o) {
            return this.wsf - o.wsf;
        }
    }
    public static void dijkstra(ArrayList<Edge>[] graph,boolean[] visited, int src, int dest){

        PriorityQueue<PairD> pq= new PriorityQueue<>();
        pq.add(new PairD(src,0,src+""));
        while (pq.size()>0){
            PairD x= pq.remove();
            if(!visited[x.v]) {
                visited[x.v]=true;
                System.out.println(x.v+" via "+x.psf+"@"+x.wsf);
                for (Edge e : graph[x.v]) {
                    if (!visited[e.nbr]) {
                        pq.add(new PairD(e.nbr, x.wsf + e.wt, x.psf + e.nbr));
                    }
                }
            }
        }
    }
    static class PairP implements Comparable<PairP> {
        int v;
        int rfv;
        int wt;

        public PairP(int v,int rfv, int wt) {
            this.v=v;
            this.rfv = rfv;
            this.wt = wt;
        }

        @Override
        public int compareTo(PairP o) {
            return this.wt - o.wt;
        }
    }
    public static void prim(ArrayList<Edge>[] graph, boolean[] visited, int src){

        PriorityQueue<PairP> pq= new PriorityQueue<>();
        pq.add(new PairP(src,-1,0));

        while (pq.size()>0){
            PairP cur= pq.remove();
            if(!visited[cur.v]) {
                visited[cur.v]=true;
                if(cur.rfv!=-1)
                System.out.println("["+cur.v+"-"+cur.rfv+"@"+cur.wt+"]");
                for (Edge e : graph[cur.v]) {
                    pq.add(new PairP(e.nbr,cur.v,e.wt));
                }
            }
        }
    }
    public static void topologicalSortDirected(ArrayList<Edge>[] graph, boolean[] visited,int src,Stack<Integer> st){

        if(!visited[src]) {
            visited[src]=true;
            for (Edge e : graph[src]) {
                if(!visited[e.nbr]) {
                    topologicalSortDirected(graph, visited, e.nbr, st);
                }
            }
            st.push(src);

        }
    }
    public static void directedGraphTopSort(ArrayList<Edge>[] graph){
        Stack<Integer> st= new Stack<>();
        boolean[] visited= new boolean[graph.length];
        for (int i=0;i<graph.length;i++){
            if(!visited[i]){
                topologicalSortDirected(graph,visited,i,st);
//                st.push(i);
            }
        }
        while (st.size()>0){
            System.out.println(st.peek());
            st.pop();
        }
    }
    public static void dfsIterative(ArrayList<Edge> [] graph, boolean[] visited,int src){
        Stack<BfsPair> st = new Stack<>();
        st.add(new BfsPair(src,src+""));
        while (st.size()>0){
            BfsPair cur= st.pop();
            if(!visited[cur.v]){
                visited[cur.v]=true;
                System.out.println(cur.v+"@"+cur.psf);
                for (Edge e:graph[cur.v]) {
                    if(!visited[e.nbr])
                    st.push(new BfsPair(e.nbr,cur.psf+e.nbr));
                }

            }
        }
    }
    public static void main(String[] args) {
        int vertices = 7;
        ArrayList<Edge>[] graph = new ArrayList[7];
        for (int i = 0; i < vertices; i++) {
            graph[i] = new ArrayList<>();
        }

        boolean[] visited = new boolean[7];
        System.out.println(visited[0]);
        //perfect friends
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        ArrayList<Edge>[] graphs = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graphs[a].add(new Edge(a, b, 0));
            graphs[b].add(new Edge(b, a, 0));
        }

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        boolean[] visited2 = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited2[i]) {
                ArrayList<Integer> comp = new ArrayList<>();
                perfectFriends(graph, visited2, comp, i);
                list.add(comp);
            }
        }
        int prFr = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                prFr = prFr + list.get(i).size() * list.get(j).size();
            }
        }
//        System.out.println(prFr);
        //perfectfriends
        HashSet<Integer> set= new HashSet<>();
        int x= graph[0].get(0).nbr;

        hamiltonianPathCycle(graphs,set,0,"",0);

    }


}

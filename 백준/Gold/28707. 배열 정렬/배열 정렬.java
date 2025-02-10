import java.io.*;
import java.util.*;

public class Main
{
    static Control[] controls;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] start = new int[n];
        int[] target = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            int num = Integer.parseInt(st.nextToken());
            start[i] = num;
            target[i] = num;
        }

        Arrays.sort(target);

        int m = Integer.parseInt(br.readLine());
        controls = new Control[m];
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken())-1;
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken());

            controls[i] = new Control(l,r,c);
        }

        System.out.print(dijkstra(start, target));
    }

    static int dijkstra(int[] start, int[] target)
    {
        HashMap<String, Integer> visit = new HashMap<>();
        visit.put(new String(Arrays.toString(start)), 0);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty())
        {
            Edge cur = pq.poll();
            int[] curNode = cur.node;
            int curCnt = cur.cnt;

            if(Arrays.equals(curNode, target)) return curCnt;

            for(Control control : controls)
            {
                int l = control.l;
                int r = control.r;
                int c = control.c;

                int[] nextNode = swap(curNode, l , r);
                int nextCnt = curCnt + c;

                if(visit.getOrDefault(Arrays.toString(nextNode), Integer.MAX_VALUE) > nextCnt)
                {
                    pq.add(new Edge(nextNode, nextCnt));
                    visit.put(Arrays.toString(nextNode), nextCnt);
                }
            }

        }
        return -1;
    }

    static int[] swap(int[] arr, int l, int r)
    {
        int[] newArr = arr.clone();  // 원본 배열을 복사하여 새로운 배열 생성
        int tmp = newArr[l];
        newArr[l] = newArr[r];
        newArr[r] = tmp;

        return newArr;
    }


    static class Edge implements Comparable<Edge>
    {
        int[] node;
        int cnt;

        public Edge(int[] node, int cnt)
        {
            this.node = node;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cnt, o.cnt);
        }
    }

    static class Control
    {
        int l;
        int r;
        int c;

        public Control(int l, int r, int c) {
            this.l = l;
            this.r = r;
            this.c = c;
        }
    }
}

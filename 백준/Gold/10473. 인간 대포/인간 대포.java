// 총 노드의 개수 -> n+2개 : 시작 노드 , 끝 노드, n개의 대포
import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main
{
    static final int INF = Integer.MAX_VALUE;
    static ArrayList<Edge>[] graph = new ArrayList[102];
    static float[] time = new float[102];
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Vertex[] vertices = new Vertex[102];

        for(int i=0; i<2; i++)
        {
            st = new StringTokenizer(br.readLine());
            float x = Float.parseFloat(st.nextToken());
            float y = Float.parseFloat(st.nextToken());
            vertices[i] = new Vertex(x,y,i);
        }

        int n = Integer.parseInt(br.readLine());

        vertices[n+1] = new Vertex(vertices[1].x , vertices[1].y, n+1);

        for(int i=1; i<=n; i++)
        {
            st = new StringTokenizer(br.readLine());
            float x = Float.parseFloat(st.nextToken());
            float y = Float.parseFloat(st.nextToken());

            vertices[i] = new Vertex(x,y,i);
        }

        for(int i=0; i<=n+1; i++)
        {
            time[i] = INF;
            graph[i] = new ArrayList<>();
            for(int j=0; j<=n+1; j++)
            {
                if(i==j) continue;
                float dist = getDist(vertices[i], vertices[j]);
                graph[i].add(new Edge(j,dist,0));
            }
        }

        dijkstra(n);
        System.out.print(time[n+1]);

    }
    static void dijkstra(int n)
    {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        time[0] = 0;

        pq.add(new Edge(0,0,0));

        while (!pq.isEmpty())
        {
            Edge cur = pq.poll();

            int curNode = cur.target;
            float curSec = cur.sec;

            if(curSec > time[curNode]) continue;

            for(Edge next:graph[curNode])
            {
                int nextNode = next.target;
                float nextDist = next.dist;

                //대포를 이용(출발점과 도착점에서는 대포를 이용하지 못함
                //대포와 다음 도착점의 거리가 50보다 작으면 대포를 타고 도착점까지 되돌아간다.
                if(curNode > 0 && curNode <= n)
                {
                    if(nextDist >= 50)
                    {
                        float nextSec = curSec + 2 + (nextDist - 50) / 5.0f;

                        if(time[nextNode] > nextSec)
                        {
                            time[nextNode] = nextSec;
                            pq.add(new Edge(nextNode, 0, nextSec));
                        }
                    }
                    else
                    {
                        float nextSec = curSec + 2 + (50 - nextDist) / 5.0f;

                        if(time[nextNode] > nextSec)
                        {
                            time[nextNode] = nextSec;
                            pq.add(new Edge(nextNode, 0, nextSec));
                        }
                    }
                }

                //걸어서 이동
                float nextSec = curSec + (nextDist / 5.0f);
                if(time[nextNode] > nextSec)
                {
                    time[nextNode] = nextSec;
                    pq.add(new Edge(nextNode, 0, nextSec));
                }
            }
        }
    }
    static float getDist(Vertex a, Vertex b)
    {
        return (float)Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y,2));
    }
    static class Edge implements Comparable<Edge>
    {
        int target;
        float dist;
        float sec;

        public Edge(int target, float dist, float sec) {
            this.target = target;
            this.dist = dist;
            this.sec = sec;
        }

        @Override
        public int compareTo(Edge o) {
            return Float.compare(this.sec, o.sec);
        }
    }
    static class Vertex
    {
        float x;
        float y;
        int idx;

        public Vertex(float x, float y, int idx)
        {
            this.x = x;
            this.y = y;
            this.idx = idx;
        }
    }
}

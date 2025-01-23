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
        PriorityQueue<Edge> pq = new PriorityQueue<>((a,b) -> (int)(a.sec - b.sec));
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
    static class Edge
    {
        int target;
        float dist;
        float sec;

        public Edge(int target, float dist, float sec) {
            this.target = target;
            this.dist = dist;
            this.sec = sec;
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

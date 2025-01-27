// 총 노드의 개수 -> n+2개 : 시작 노드 , 끝 노드, n개의 대포
import java.io.*;
import java.util.*;

public class Main
{
    static float[] time = new float[102];
    static ArrayList<Edge>[] graph = new ArrayList[102];
    static final float INF = Float.MAX_VALUE;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        float startX = Float.parseFloat(st.nextToken());
        float startY = Float.parseFloat(st.nextToken());

        st = new StringTokenizer(br.readLine());
        float endX = Float.parseFloat(st.nextToken());
        float endY = Float.parseFloat(st.nextToken());

        int n = Integer.parseInt(br.readLine());

        Vertex[] vertices = new Vertex[102];
        vertices[0] = new Vertex(startX, startY, 0);
        vertices[n+1] = new Vertex(endX, endY, n+1);

        for(int i=1; i<=n; i++)
        {
            st = new StringTokenizer(br.readLine());
            float x = Float.parseFloat(st.nextToken());
            float y = Float.parseFloat(st.nextToken());

            vertices[i] = new Vertex(x,y,i);
        }

        Arrays.fill(time, INF);
        for(int i=0; i<=n+1; i++)
        {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<=n+1; i++)
        {
            for(int j=0; j<=n+1; j++)
            {
                if(i == j) continue;
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
        pq.add(new Edge(0,0,0));
        time[0] = 0;

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

                //현재 노드가 시작노드이거나 마지막 노드라면 대포를 이용할 수 없음
                if(curNode >= 1 && curNode <=n)
                {
                    float nextSec = curSec + 2 + Math.abs(nextDist - 50) / 5.0f;

                    if(time[nextNode] > nextSec)
                    {
                        time[nextNode] = nextSec;
                        pq.add(new Edge(nextNode, 0, nextSec));
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
    //점과 점사이의 직선 거리 공식
    static float getDist(Vertex a, Vertex b)
    {
        return (float)Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
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

        public Vertex(float x, float y, int idx) {
            this.x = x;
            this.y = y;
            this.idx = idx;
        }
    }
}

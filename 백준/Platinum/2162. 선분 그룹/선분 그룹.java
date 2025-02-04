import java.io.*;
import java.util.StringTokenizer;

public class Main
{
    static int[] parent;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        parent = new int[n];
        Line[] lines = new Line[n];

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            lines[i] = new Line(x1, y1, x2, y2);
            parent[i] = i;
        }

        for(int i=0; i<n; i++)
        {
            for(int j=i+1; j<n; j++)
            {
                if(isIntersect(lines[i], lines[j]))
                {
                    union(i,j);
                }
            }
        }

        int[] groupSize = new int[n];
        int groupCnt = 0;

        for(int i=0; i<n; i++)
        {
            int root = find(i);
            if(root == i) groupCnt++;
            groupSize[root]++;
        }

        int maxGroupSize = 0;
        for(int i=0; i<n; i++)
        {
            if(groupSize[i] > maxGroupSize)
            {
                maxGroupSize = groupSize[i];
            }
        }

        System.out.println(groupCnt);
        System.out.println(maxGroupSize);
    }

    static int find(int x)
    {
        if(x == parent[x])
            return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x,int y)
    {
        int rootX = find(x);
        int rootY = find(y);

        if(rootX != rootY)
            parent[rootY] = rootX;
    }
    static int ccw(int x1, int y1, int x2, int y2, int x3, int y3)
    {
        long result = (long)(x2-x1) * (y3-y1) - (long)(y2-y1) * (x3-x1);
        if(result > 0) return 1;
        if(result < 0) return -1;
        return 0;
    }
    static boolean isIntersect(Line l1, Line l2)
    {
        int ccw1 = ccw(l1.x1, l1.y1, l1.x2, l1.y2, l2.x1, l2.y1);
        int ccw2 = ccw(l1.x1, l1.y1, l1.x2, l1.y2, l2.x2, l2.y2);
        int ccw3 = ccw(l2.x1, l2.y1, l2.x2, l2.y2, l1.x1, l1.y1);
        int ccw4 = ccw(l2.x1, l2.y1, l2.x2, l2.y2, l1.x2, l1.y2);

        if(ccw1 == 0 && ccw2 == 0 && ccw3 == 0 && ccw4 == 0)
        {
            return Math.min(l1.x1, l1.x2) <= Math.max(l2.x1, l2.x2) &&
                    Math.min(l2.x1, l2.x2) <= Math.max(l1.x1, l1.x2) &&
                    Math.min(l1.y1, l1.y2) <= Math.max(l2.y1, l2.y2) &&
                    Math.min(l2.y1, l2.y2) <= Math.max(l1.y1, l1.y2);
        }

        return ccw1 * ccw2 <= 0 && ccw3 * ccw4 <= 0;
    }
    static class Line
    {
        int x1;
        int y1;
        int x2;
        int y2;

        public Line(int x1, int y1, int x2, int y2)
        {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
}

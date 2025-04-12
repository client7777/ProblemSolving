package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_12908
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int startX = Integer.parseInt(st.nextToken());
        int startY = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int endX = Integer.parseInt(st.nextToken());
        int endY = Integer.parseInt(st.nextToken());

        Node[] nodes = new Node[8];
        nodes[0] = new Node(startX, startY);
        nodes[7] = new Node(endX, endY);

        long[][] dist = new long[8][8];
        for(int i=0; i<8; i++)
        {
            Arrays.fill(dist[i], Long.MAX_VALUE / 2);
            dist[i][i] = 0;
        }

        dist[0][7] = dist[7][0] = Math.abs(endX - startX) + Math.abs(endY - startY);

        for(int i=1; i<=6; i+=2)
        {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());

            nodes[i] = new Node(x1,y1);

            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            nodes[i+1] = new Node(x2,y2);

            dist[i][i+1] = 10;
            dist[i+1][i] = 10;
        }

        for(int i=0; i<8; i++)
        {
            for(int j=0; j<8; j++)
            {
                if(i == j) continue;
                dist[i][j] = Math.min(dist[i][j], Math.abs(nodes[j].x - nodes[i].x) + Math.abs(nodes[j].y - nodes[i].y));
            }
        }

        for(int k=0; k<8; k++)
        {
            for(int i=0; i<8; i++)
            {
                for(int j=0; j<8; j++)
                {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        System.out.print(dist[0][7]);
    }

    static class Node
    {
        int x;
        int y;

        public Node(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }
}

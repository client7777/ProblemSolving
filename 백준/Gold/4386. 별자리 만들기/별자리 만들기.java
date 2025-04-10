import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main
{
    static int n;
    static double[][] planet;
    static int[] parent;
    static ArrayList<double[]> edge = new ArrayList<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        parent = new int[n+1];
        for(int i=1; i<=n; i++)
        {
            parent[i] = i;
        }
        planet = new double[n+1][3];
        for(int i=1; i<=n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            planet[i][0] = i;
            planet[i][1] = Double.parseDouble(st.nextToken());
            planet[i][2] = Double.parseDouble(st.nextToken());
        }
            for(int i=1; i<=n; i++)
            {
                for(int j=i+1; j<=n; j++)
                {
                    double dist = Math.sqrt(Math.pow(planet[i][1] - planet[j][1],2) + Math.pow(planet[i][2] - planet[j][2],2));
                    edge.add(new double[]{i,j,dist});
                }
            }
        Collections.sort(edge, Comparator.comparingDouble(o->o[2]));
        kruskal();
    }
    static void kruskal()
    {
        double tot = 0.0;
        int used = 0;
        for(double[] node:edge)
        {
            if(used>n-1) break;
            int from = (int)node[0];
            int to = (int)node[1];
            double dist = node[2];

            if(find(from) != find(to))
            {
                tot += dist;
                union(from, to);
                used++;
            }
        }
        System.out.printf("%.2f",tot);
    }
    static int find(int x)
    {
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
    static void union(int x,int y)
    {
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY)
        {
            parent[rootY] = rootX;
        }
    }
}

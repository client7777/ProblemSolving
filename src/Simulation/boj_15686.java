package Simulation;
//도시의 치킨거리가 최소가 되려면 치킨집이 최대한 많으면 됨.
//따라서 M개의 치킨집을 폐업시키지 않을 경우만 따지면 그 경우가 도시의 치킨거리가 최소가 되는 경우
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;
public class boj_15686
{
    static int n,m;
    static int minDistance = Integer.MAX_VALUE; // 최소 치킨거리
    static int[][] arr;
    static boolean[] visit;
    static ArrayList<int[]> chick = new ArrayList<>(); // 치킨집의 좌표를 저장
    static ArrayList<int[]> house = new ArrayList<>(); // 집의 좌표를 저장
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][n];

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
            {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j]==1) // 집의 좌표를 저장
                {
                    house.add(new int[]{i,j});
                }
                if(arr[i][j]==2) // 치킨집의 좌표를 저장
                {
                    chick.add(new int[]{i,j});
                }
            }
        }
        visit = new boolean[chick.size()];
        dfs(0,0);
        System.out.print(minDistance);
    }
    static void dfs(int start, int depth)
    {
        if(depth == m)
        {
            // 치킨 거리를 계산하는 함수
            calculateChickDistance();
            return;
        }
        for(int i = start; i<chick.size(); i++)
        {
            if(!visit[i])
            {
                visit[i] = true;
                dfs(i+1, depth+1);
                visit[i] = false;
            }
        }
    }
    static void calculateChickDistance()
    {
        int total = 0;
        for(int[] h:house)
        {
            int hx = h[0];
            int hy = h[1];
            int minDist = Integer.MAX_VALUE;

            for(int i=0; i<chick.size(); i++)
            {
                if(visit[i])
                {
                    int cx = chick.get(i)[0];
                    int cy = chick.get(i)[1];
                    int dist = Math.abs(hx - cx) + Math.abs(hy - cy);
                    minDist = Math.min(minDist, dist);
                }
            }
            total += minDist;
        }
        minDistance = Math.min(minDistance, total);
    }
}

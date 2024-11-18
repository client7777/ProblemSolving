package BFS_DFS;

import java.io.*;
import java.util.*;

public class boj_1039
{
    static int n,k,m;
    static int result = -1;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        // 주어진 정수의 길이
        //m = Integer.toString(n).length();
        m = String.valueOf(n).length();
        bfs();
        System.out.print(result);
    }
    static void bfs()
    {
        boolean[][] visit = new boolean[1000001][k+1];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{n,0});
        visit[n][0] = true;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curNum = cur[0];
            int curCnt = cur[1];
            if(curCnt == k)
            {
                //k번 탐색을 끝냈다면 최대값을 갱신하고 다음 노드로 이동
                result = Math.max(result, curNum);
                continue;
            }
            for(int i=0; i<m-1; i++)
            {
                for(int j=i+1; j<m; j++)
                {
                    int next = swap(curNum, i , j);
                    if(next != -1 && !visit[next][curCnt + 1])
                    {
                        q.add(new int[]{next, curCnt + 1});
                        visit[next][curCnt + 1] = true;
                    }
                }
            }
        }
    }
    static int swap(int n, int i, int j)
    {
        char[] numArr = String.valueOf(n).toCharArray();

        char tmp = numArr[i];
        numArr[i] = numArr[j];
        numArr[j] = tmp;

        if(numArr[0] == '0') return -1;
        String swapResult = new String(numArr);
        return Integer.parseInt(swapResult);
    }
}

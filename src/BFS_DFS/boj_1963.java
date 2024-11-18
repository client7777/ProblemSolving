package BFS_DFS;

import java.io.*;
import java.util.*;

public class boj_1963
{
    static int test_case;
    static boolean[] isPrime = new boolean[10000];
    static StringBuilder sb = new StringBuilder();
    static boolean[] visit;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        test_case = Integer.parseInt(br.readLine());

        Era();

        for(int t=0; t<test_case; t++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(a == b)
            {
                sb.append("0\n");
                continue;
            }
            bfs(a,b);
        }
        System.out.print(sb);
    }
    static void bfs(int a, int b)
    {
        visit = new boolean[10000];
        visit[a] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{a, 0});
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curNum = cur[0];
            int curCnt = cur[1];
            if(curNum == b)
            {
                sb.append(curCnt + "\n");
                return;
            }
            for(int i=0; i<4; i++)
            {
                for(int d=0; d<10; d++)
                {
                    int nextNum =change(curNum,i,d);
                    if(nextNum != -1 && !visit[nextNum] && isPrime[nextNum])
                    {
                        q.add(new int[]{nextNum, curCnt + 1});
                        visit[nextNum] = true;
                    }
                }
            }
        }
        sb.append("Impossible\n");
    }
    static int change(int num, int pos, int newDigit)
    {
        int[] digit = {1000,100,10,1};
        int curNum = (num/digit[pos]) % 10;

        if(curNum == newDigit) return -1;

        int nextNum = num - curNum * digit[pos] + newDigit * digit[pos];

        if(nextNum >= 1000) return nextNum;

        return -1;
    }
    static void Era()
    {
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for(int i=2; i<10000; i++)
        {
            if(isPrime[i])
            {
                for(int j=i*i; j<10000; j+=i)
                {
                    isPrime[j] = false;
                }
            }
        }
    }
}

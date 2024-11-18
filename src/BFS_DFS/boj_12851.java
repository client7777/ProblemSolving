package BFS_DFS;
//숨바꼭질2
import java.io.*;
import java.util.*;
public class boj_12851
{
    static int[] dx = {-1,1,2}; // 이동할 수 있는 방법 3가지
    static int cnt = 0, res = Integer.MAX_VALUE; // 경로 수와 최단 시간
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        bfs(n,k);
        System.out.println(res);
        System.out.println(cnt);
    }
    static void bfs(int start, int end)
    {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        int[] move = new int[100000+1]; // 각 위치에 도달하는 시간을 저장할 배열

        while (!q.isEmpty())
        {
           int pos = q.poll();
           if(pos == end) // 목표 위치에 도달할 경우
           {
               if(move[pos] < res)
               {
                   res = move[pos]; // 최단 시간 갱신
                   cnt = 1; // 경로수 초기화
               }
               //다른 경로로 동일한 시간으로 목표 위치에 도달할 경우
               else if(move[pos] == res)
               {
                   cnt++;
               }
               continue; // 다음 위치 탐색
           }
           for(int i=0; i<3; i++)
           {
               int next;
               if(i == 2)
               {
                   next = pos * dx[i];
               }
               else
               {
                   next = pos + dx[i];
               }
               // 이동할 위치가 범위 내에 있다면
               if(next >= 0 && next < move.length)
               {
                   // 아직 방문하지 않았거나 현재 위치까지의 시간이 동일한 경우
                   if(move[next] == 0 || move[next] == move[pos] + 1)
                   {
                       move[next] = move[pos] + 1; // 다음 위치의 시간을 현재 위치의 시간 + 1로 설정
                       q.add(next);
                   }
               }
           }
        }
    }
}

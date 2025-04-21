import java.io.*;
import java.util.*;

public class Main
{
    static int n,t;
    static boolean[][] hole;
    static int[][] dist;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        t = Integer.parseInt(br.readLine());

        hole = new boolean[101][21];
        dist = new int[101][21];

        for(int i=0; i<n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for(int j=0; j<cnt; j++)
            {
                hole[i+1][Integer.parseInt(st.nextToken())] = true;
            }
        }

        for(int i=0; i<n+1; i++)
        {
            for(int j=1; j<21; j++)
            {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        dijkstra();
    }
    static void dijkstra()
    {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[2]));
        pq.add(new int[]{0,1,0});
        dist[0][1] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNum = cur[0]; // 현재 나무 번호
            int curHeight = cur[1]; // 현재 높이
            int curCost = cur[2]; // 현재까지 사용한 T기능 횟수
            if(curNum == n) break;
            if (curCost > dist[curNum][curHeight]) continue; // 더 이상 최적 경로가 아닌 경우 무시

            // O 버튼: 현재 높이로 이동
            if (curNum + 1 <= n && hole[curNum + 1][curHeight] && dist[curNum + 1][curHeight] > curCost) {
                pq.add(new int[]{curNum + 1, curHeight, curCost});
                dist[curNum + 1][curHeight] = curCost;
            }

            // A 버튼: 위로 1m 이동
            if (curHeight + 1 <= 20 && hole[curNum + 1][curHeight + 1] && dist[curNum + 1][curHeight + 1] > curCost) {
                pq.add(new int[]{curNum + 1, curHeight + 1, curCost});
                dist[curNum + 1][curHeight + 1] = curCost;
            }

            // B 버튼: 현재 높이 * 2만큼 이동 (최대 20m로 제한)
            int newHeight = Math.min(20, curHeight * 2);
            if (hole[curNum + 1][newHeight] && dist[curNum + 1][newHeight] > curCost) {
                pq.add(new int[]{curNum + 1, newHeight, curCost});
                dist[curNum + 1][newHeight] = curCost;
            }

            // C 버튼: 아래로 1m 이동
            if (curHeight - 1 >= 1 && hole[curNum + 1][curHeight - 1] && dist[curNum + 1][curHeight - 1] > curCost) {
                pq.add(new int[]{curNum + 1, curHeight - 1, curCost});
                dist[curNum + 1][curHeight - 1] = curCost;
            }

            // T 버튼: 모든 높이에 대해 이동 가능
            for (int k = 1; k <= 20; k++) {
                if (hole[curNum + 1][k] && dist[curNum + 1][k] > curCost + 1) {
                    pq.add(new int[]{curNum + 1, k, curCost + 1});
                    dist[curNum + 1][k] = curCost + 1;
                }
            }
        }
        // 마지막 나무에 도달할 수 있는 최소 T기능 사용 횟수 계산
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= 20; i++) {
            ans = Math.min(ans, dist[n][i]);
        }

        // 출력: T기능 사용 횟수가 제한을 초과하면 -1 출력, 그렇지 않으면 최소 횟수 출력
        System.out.println(ans <= t ? ans : -1);
    }
}

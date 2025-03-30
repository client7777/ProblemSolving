import java.util.*;
import java.io.*;

public class Main {
	
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static int MAX = 1;
	
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Queue<int[]> q = new LinkedList<>();
		
		int m = Integer.parseInt(st.nextToken()); //상자의 행(세로)
		int n = Integer.parseInt(st.nextToken()); // 상자의 열(가로)
		
		map = new int[n][m];
		visit = new boolean[n][m];
		
		for(int i=0; i<n; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) // 1인경우, 토마토가 익은 경우는 탐색 시작지점이므로
				{
					q.add(new int[] {i,j}); // 큐에 좌표 저장
					visit[i][j] =true; // 방문처리
				}
				
			}
		}
		while(!q.isEmpty())
		{
			int cur[] = q.poll();
			int curX = cur[0];
			int curY = cur[1];
			
			for(int dir = 0; dir<4; dir++)
			{
				int nextX = curX + dx[dir];
				int nextY = curY + dy[dir];
				
				if(nextX < 0 || nextY < 0 || nextX >=n || nextY >= m) continue;
				if(visit[nextX][nextY] || map[nextX][nextY] != 0) continue;
				q.add(new int[] {nextX, nextY});
				visit[nextX][nextY] = true;
				map[nextX][nextY] = map[curX][curY] + 1;
			
			}
			
		}
		for(int i=0; i<n; i++)
		{
			for(int j=0; j<m; j++)
			{
				if(map[i][j] == 0)
				{
					System.out.print(-1);
					return;
				}
				if(map[i][j] > MAX) MAX = map[i][j];
			}
			
		}
		System.out.print(MAX - 1);
		
	}
}

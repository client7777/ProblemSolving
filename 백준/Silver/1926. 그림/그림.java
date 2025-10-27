import java.util.*;
import java.io.*;

public class Main
{
    static int map[][]; // 도화지
    static boolean visit[][]; // 방문 여부 표시
    //상하좌우 이동을 위한 델타 배열
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(tk.nextToken()); // 도화지의 행
        int m = Integer.parseInt(tk.nextToken()); // 도화지의 열
        Queue<int[]> q = new LinkedList<>(); // 좌표를 담기 위한 큐
        
        map = new int[n][m];
        visit = new boolean[n][m];
        
        for(int i=0; i<n; i++)
        {
            tk = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++)
            {
                map[i][j] = Integer.parseInt(tk.nextToken());
                
            }
            
        }
        
        int max = 0;
        int num = 0;
        
        // BFS  탐색 시작
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                //현재 위치가 이미 방문한 곳이거나 그림이 아니라면 넘김
                if(visit[i][j] == true || map[i][j] == 0) continue;
                //위의 경우가 아니라면 그림을 발견한 경우이므로 그림의 개수 하나 카운트, 현재 위치 방문처리
                visit[i][j] = true;
                num++;
                q.add(new int[]{i,j}); // 큐에 현재 위치 추가
                int area = 0;
                
                while(!q.isEmpty())
                {
                    area++;
                    int[] temp = q.poll(); 
                    int x = temp[0];
                    int y = temp[1];
                    
                    for(int dir=0; dir<4; dir++)
                    {
                        int nx = x + dx[dir];
                        int ny = y + dy[dir];
                        
                        if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue; 
                        if(visit[nx][ny] == true || map[nx][ny] == 0) continue;
                        
                        visit[nx][ny] = true;
                        q.offer(new int[]{nx,ny});
                        
                    }
                }
                max = Math.max(max, area);
            }
         
        }
 
        System.out.println(num);
        System.out.println(max);
        
    }
}
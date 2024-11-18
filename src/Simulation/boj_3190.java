package Simulation;

import java.io.*;
import java.util.*;

public class boj_3190
{
    static int n,k;
    static int time = 0;
    static int startX = 1, startY = 1, startDir = 1;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        map = new int[n+1][n+1];
        StringTokenizer st;
        for(int i=0; i<k; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            map[x][y] = 1; //  사과는 1로 표시
        }

        int l = Integer.parseInt(br.readLine());

        HashMap<Integer,Character> spin = new HashMap<>();
        for(int i=0; i<l; i++)
        {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            spin.put(time, dir);
        }

        Queue<int[]> snake = new LinkedList<>();
        snake.add(new int[]{startX, startY});
        map[startX][startY] = 2;

        while (true)
        {
            int nextX = startX + dx[startDir];
            int nextY = startY + dy[startDir];

            time++;
            
            // 범위 밖으로 나가거나 몸에 부딪히면 게임 중지
            if(OOB(nextX, nextY) || map[nextX][nextY] == 2) break;

            // 머리를 다음 좌표로 이동시킴
            snake.add(new int[]{nextX, nextY});
            map[nextX][nextY] = 2;

            // 다음 칸에 사과가 없다면 꼬리 자르기, 몸 길이는 변하지 않음
            if(map[nextX][nextY] == 0)
            {
                int[] tail = snake.poll();
                map[tail[0]][tail[1]] = 0;
            }

            if(spin.containsKey(time))
            {
                char turnDir = spin.get(time);
                if(turnDir == 'L')
                {
                    startDir = (startDir + 3) % 4; // 왼쪽 회전
                }
                else if(turnDir == 'D')
                {
                    startDir = (startDir + 1) % 4; // 오른쪽 회전
                }
            }

            startX = nextX;
            startY = nextY;
        }

        System.out.print(time);
    }
    static boolean OOB(int x,int y)
    {
        return x < 1 || y < 1 || x > n || y > n;
    }
}

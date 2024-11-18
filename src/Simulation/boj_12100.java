package Simulation;
// 게임판을 상하좌우로 기울이기
// 5번 기울이는 각각의 방향을 정하기
import java.io.*;
import java.util.*;
public class boj_12100
{
    static int n; // 게임판의 크기
    static int[][] arr1 = new int[21][21]; // 원본 배열
    static int[][] arr2 = new int[21][21]; // 시뮬레이션 배열

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++)
            {
                arr1[i][j] = Integer.parseInt(st.nextToken()); // 원본 배열
            }
        }
        int mx = 0;
        for(int tmp=0; tmp<1024; tmp++)
        {
            for(int i=0; i<n; i++) System.arraycopy(arr1[i], 0, arr2[i], 0, n);

            int brute = tmp;
            for(int i=0; i<5; i++)
            {
                int dir = brute%4;
                brute /= 4;
                // 방향을 받아서 배열을 기울이는 함수
                tilt(dir);
            }
            for(int i=0; i<n; i++)
            {
                for(int j=0; j<n; j++)
                {
                    mx = Math.max(mx, arr2[i][j]);
                }
            }
        }
        System.out.print(mx);
    }
    static void tilt(int dir)
    {
        for(int i=0; i<dir; i++)
        {
            rotate();
        }
        for(int i=0; i<n; i++)
        {
            int[] tilted = new int[n];
            int idx = 0;
            for(int j=0; j<n; j++)
            {
                if(arr2[i][j] == 0) continue;
                else if(tilted[idx] == 0 ) tilted[idx] = arr2[i][j];
                else if(tilted[idx] == arr2[i][j]) tilted[idx++] *= 2;
                else tilted[++idx] = arr2[i][j];
            }
            for(int j=0; j<n; j++)
            {
                arr2[i][j] = tilted[j];
            }
        }
        for(int i=dir; i<4; i++)
        {
            rotate();
        }
    }
    static void rotate()
    {
        int[][] tmp = new int[n][n];
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                tmp[i][j] = arr2[n-j-1][i];
            }
        }
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                arr2[i][j] = tmp[i][j];
            }
        }
    }
}
package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_9657
{
    static int[] d;
    static int[] num = {1,3,4};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        d = new int[1001];
        d[1] = 1;
        d[2] = 0;
        d[3] = 1;
        d[4] = 1;

        for(int i=5; i<=n; i++)
        {
            for(int j : num)
            {
                //상대방이 돌 i-j개에서 반드시 패배하는 경우가 존재한다면, 현재 차례에서 그만큼 가져가면 승리 가능
                //한 번이라도 상대가 패배하는 경우가 있다 -> 현재 턴에서 승리 가능
                if(d[i - j] == 0)
                {
                    d[i] = 1;
                    break;
                }
                //상대방이 i-j개에서 반드시 패배하는 경우가 존재하지 않는다면 현재 턴에서는 패배
                //모든 경우에서 상대가 승리한다 -> 현재 턴에서 패배
                else
                    d[i] = 0;
            }
        }
        System.out.print(d[n] == 1 ? "SK" : "CY");
    }
}

/*
public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        System.out.print(n % 7 == 0 || n % 7 == 2 ? "CY" : "SK");
    }
*/
package zzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class boj_24509
{
    static int n;
    static int[][] student;
    static HashSet<Integer> set = new HashSet<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        student = new int[n+1][4];

        for(int i=0; i<n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int studentNum = Integer.parseInt(st.nextToken());
            for(int j=0; j<4; j++)
            {
                student[studentNum][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<4; i++)
        {
            findStudent(i);
        }
    }

    static void findStudent(int idx)
    {
        int max = -1;
        int ans = 0;

        for(int i=1; i<=n; i++)
        {
            if(set.contains(i) || student[i][idx] <= max) continue;

            ans = i;
            max = student[i][idx];
        }

        set.add(ans);
        System.out.print(ans + " ");
    }
}

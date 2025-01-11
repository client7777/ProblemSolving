package zzz;

import java.io.*;
import java.util.*;

public class boj_31533
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double a = Double.parseDouble(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        double m = Double.parseDouble(st.nextToken());
        double n = Double.parseDouble(st.nextToken());

        if (m > n)
        {
            double temp = m;
            m = n;
            n = temp;
        }

        // 동시에 처리하는 경우와 하나의 컴퓨터로 처리하는 경우 계산
        double answer = Math.max(m, n / a); // 동시에 처리
        answer = Math.min((m / a) * 2, answer); // 하나의 컴퓨터 처리

        System.out.printf("%.7f", answer);
    }

}

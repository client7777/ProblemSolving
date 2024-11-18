package zzz;

import java.io.*;
import java.util.StringTokenizer;

public class boj_1297
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        double d = Double.parseDouble(st.nextToken());
        double h = Double.parseDouble(st.nextToken());
        double w = Double.parseDouble(st.nextToken());

        double pow = Math.pow(d,2) / (Math.pow(h,2) + Math.pow(w,2));
        double sqrt = Math.sqrt(pow);
        System.out.print((int)(sqrt * h) + " " + (int)(sqrt * w));
    }
}

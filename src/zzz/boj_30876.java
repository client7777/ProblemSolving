package zzz;
// 주어진 좌표를 y좌표를 기준으로 오름차순으로 정렬
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class boj_30876
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayList<int[]> arr = new ArrayList<>();

        StringTokenizer st;

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr.add(new int[]{x,y});
        }
        arr.sort(Comparator.comparingInt(o -> o[1]));
        System.out.print(arr.get(0)[0] + " " + arr.get(0)[1]);
    }
}

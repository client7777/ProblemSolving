package Hash;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class boj_1620
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String[] arr = new String[n+1];

        for(int i=1; i<=n; i++)
        {
            arr[i] = br.readLine();
        }

        HashMap<String, Integer> map = new HashMap<>();
        for(int i=1; i<=n; i++)
        {
            map.put(arr[i], i);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m; i++)
        {
            String q = br.readLine();

            if(map.containsKey(q)) // 문제가 문자열로 주어진 경우 -> 숫자로 답
                sb.append(map.get(q)).append('\n');
            else // 문제가 정수로 주어진 경우 -> 이름으로 답
                sb.append(arr[Integer.parseInt(q)]).append('\n');
        }
        System.out.print(sb);
    }
}

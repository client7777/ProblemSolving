import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Map<Long, Integer> map = new HashMap<>(); // 숫자, 등장횟수

        for(int i=0; i<n; i++)
        {
            long num = Long.parseLong(br.readLine());
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        long maxNum = 0;
        int maxCnt = 0;

        for(Map.Entry<Long, Integer> entry : map.entrySet())
        {
            long key = entry.getKey();
            int cnt = entry.getValue();

            if(cnt > maxCnt || (cnt == maxCnt && key < maxNum))
            {
                maxCnt = cnt;
                maxNum = key;
            }
        }

        System.out.print(maxNum);
    }
}

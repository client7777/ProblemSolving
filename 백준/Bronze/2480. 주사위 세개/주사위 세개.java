import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[7];
        int max = 0;
        int idx = 0;
        boolean flag = false;
        for(int i=0; i<3; i++)
        {
            int num = Integer.parseInt(st.nextToken());
            arr[num]++;
            if(arr[num] >= 2)
            {
                flag = true;
                idx = num;
            }

            max = Math.max(max, num);
        }

        if(flag)
        {
            if(arr[idx] == 3)
            {
                System.out.print(10000 + idx * 1000);
            }
            else if(arr[idx] == 2)
            {
                System.out.print(1000 + idx * 100);
            }
        }
        else
            System.out.print(max * 100);

    }
}
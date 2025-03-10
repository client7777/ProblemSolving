import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int cnt = 0;

        HashMap<Integer, Integer> map = new HashMap<>();

        while (right < n)
        {
            map.put(arr[right], map.getOrDefault(arr[right], 0) + 1);

            while (map.size() > 2)
            {
                map.put(arr[left], map.get(arr[left]) - 1);
                if(map.get(arr[left]) == 0)
                {
                    map.remove(arr[left]);
                }

                left++;
            }

            cnt = Math.max(cnt, right - left + 1);
            right++;
        }

        System.out.print(cnt);
    }
}

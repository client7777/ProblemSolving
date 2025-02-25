import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t=0; t<test_case; t++)
        {
            HashMap<String, Integer> map = new HashMap<>();
            int n = Integer.parseInt(br.readLine()); // 가지고 있는 의상의 수
            for(int i=0; i<n; i++)
            {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String name = st.nextToken();
                String kind = st.nextToken();

                if(map.containsKey(kind))
                    map.put(kind, map.get(kind) + 1);
                else
                    map.put(kind, 1);
            }
            int res = 1;

            for(int val:map.values())
            {
                res *= (val + 1);
            }
            sb.append(res-1).append('\n');
        }
        System.out.print(sb);
    }
}

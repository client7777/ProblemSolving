package Hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class boj_19583
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int s = Integer.parseInt(st.nextToken().replace(":",""));
        int e = Integer.parseInt(st.nextToken().replace(":",""));
        int q = Integer.parseInt(st.nextToken().replace(":",""));

        HashSet<String> set = new HashSet<>();
        HashSet<String> cnt = new HashSet<>();

        String input;
        while (((input = br.readLine()) != null))
        {
            st = new StringTokenizer(input);

            int time = Integer.parseInt(st.nextToken().replace(":", ""));
            String name = st.nextToken();

            if(time <= s) set.add(name);

            if(time >= e && time <= q && set.contains(name))
            {
                cnt.add(name);
            }
        }

        System.out.print(cnt.size());
    }
}
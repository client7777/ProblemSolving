import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int size = 0;

        HashSet<String> set = new HashSet<>();
        for(int i=0; i<n; i++)
        {
            set.add(br.readLine());
        }

       ArrayList<String> ans = new ArrayList<>();

        for(int i=0; i<m; i++)
        {
            String name = br.readLine();
            if(set.contains(name))
            {
                size++;
                ans.add(name);
            }
        }

        ans.sort(Comparator.naturalOrder());
        
        StringBuilder sb = new StringBuilder();
        sb.append((size)).append('\n');

        for(String name:ans)
        {
            sb.append(name).append('\n');
        }

        System.out.print(sb);
    }
}

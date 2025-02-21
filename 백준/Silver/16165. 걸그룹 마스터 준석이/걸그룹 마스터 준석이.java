import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashMap<String, ArrayList<String>> group = new HashMap<>();
        HashMap<String, String> member = new HashMap<>();

        for(int i=0; i<n; i++)
        {
            String groupName = br.readLine();
            int groupSize = Integer.parseInt(br.readLine());

            ArrayList<String> arr = new ArrayList<>();
            for(int j=0; j<groupSize; j++)
            {
                String groupMember = br.readLine();

                arr.add(groupMember);

                member.put(groupMember, groupName);
            }

            Collections.sort(arr);
            group.put(groupName, arr);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m; i++)
        {
            String q = br.readLine();
            int type = Integer.parseInt(br.readLine());

            if(type == 1)
            {
                sb.append(member.get(q)).append('\n');
            }
            else if(type == 0)
            {
                for(String name:group.get(q))
                {
                    sb.append(name).append('\n');
                }
            }
        }

        System.out.print(sb);
    }
}

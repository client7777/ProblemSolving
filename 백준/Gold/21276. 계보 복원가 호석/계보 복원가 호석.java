import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main
{
    static int[] inDegree;
    static HashMap<String, Integer> map = new HashMap<>();
    static HashMap<Integer, String> reverse_map = new HashMap<>();
    static HashMap<String, ArrayList<String>> childMap = new HashMap<>();
    static HashMap<String, ArrayList<String>> parentMap = new HashMap<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        inDegree = new int[n];

        ArrayList<String> nameList = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens())
        {
            nameList.add(st.nextToken());
        }

        nameList.sort(Comparator.naturalOrder());

        for(int i=0; i<n; i++)
        {
            String name = nameList.get(i);
            map.put(name, i);
            reverse_map.put(i, name);
        }

        int m = Integer.parseInt(br.readLine());
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            String son = st.nextToken();
            String parent = st.nextToken();

            inDegree[map.get(son)]++;

            //parent의 자식들을 map에 저장
            childMap.putIfAbsent(parent, new ArrayList<>());
            childMap.get(parent).add(son);

            //child의 부모들을 map에 저장
            parentMap.putIfAbsent(son, new ArrayList<>());
            parentMap.get(son).add(parent);

        }

        for(String key : childMap.keySet())
        {
            Collections.sort(childMap.get(key));
        }

        StringBuilder sb = new StringBuilder();
        ArrayList<String> root = new ArrayList<>();
        Queue<String> q = new LinkedList<>();

        for(int i=0; i<n; i++)
        {
            if(inDegree[i] == 0)
            {
                String name = reverse_map.get(i);
                root.add(name);
                q.add(name);
            }
        }

        sb.append(root.size()).append('\n');
        for(String str:root)
        {
            sb.append(str).append(" ");
        }
        sb.append('\n');

        HashMap<String, ArrayList<String>> direct = new HashMap<>();
        while (!q.isEmpty())
        {
            String cur = q.poll();

            if(!childMap.containsKey(cur)) continue;

            for(String child : childMap.get(cur))
            {
                inDegree[map.get(child)]--;

                if(inDegree[map.get(child)] == 0)
                {
                    q.add(child);
                    direct.putIfAbsent(cur, new ArrayList<>());
                    direct.get(cur).add(child);
                }
            }
        }

        for(String str : nameList)
        {
            sb.append(str).append(" ");

            if(direct.containsKey(str))
            {
                ArrayList<String> ans = direct.get(str);
                Collections.sort(ans);

                sb.append(ans.size()).append(" ");

                for(String child : ans)
                {
                    sb.append(child).append(" ");
                }
            }
            else sb.append(0);

            sb.append('\n');
        }

        System.out.print(sb);
    }
}

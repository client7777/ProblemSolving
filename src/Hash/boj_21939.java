package Hash;

import java.io.*;
import java.util.*;

public class boj_21939
{
    static TreeSet<int[]> map = new TreeSet<>((o1,o2) ->
    {
        if(o1[1] == o2[1])
            return o1[0] - o2[0];
        return o1[1] - o2[1];
    });
    static HashMap<Integer, Integer> problemDiff = new HashMap<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            add(p,l);
        }

        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            String commend = st.nextToken();

            switch (commend)
            {
                case "add":
                    int p = Integer.parseInt(st.nextToken());
                    int l = Integer.parseInt(st.nextToken());
                    add(p, l);
                    break;

                case "recommend":
                    int num = Integer.parseInt(st.nextToken());
                    sb.append(num == 1 ? map.last()[0] : map.first()[0]).append('\n');
                    break;

                case "solved":
                    int removeNum = Integer.parseInt(st.nextToken());
                    remove(removeNum);
                    break;

            }
        }
        System.out.print(sb);
    }
    static void add(int p,int l)
    {
        if(problemDiff.containsKey(p))
        {
            remove(p);
        }
        map.add(new int[]{p,l});
        problemDiff.put(p,l);
    }
    static void remove(int p)
    {
        int remove_l = problemDiff.get(p);
        problemDiff.remove(p);
        map.remove(new int[]{p,remove_l});
    }
}

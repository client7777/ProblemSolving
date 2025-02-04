package Sort;

import java.io.*;
import java.util.*;

public class boj_11651
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        ArrayList<Pos> arr = new ArrayList<>();
        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr.add(new Pos(x,y));
        }

        arr.sort((o1, o2) -> {
            if(o1.y == o2.y) return o1.x - o2.x;
            return o1.y - o2.y;
        });

        StringBuilder sb = new StringBuilder();
        for(Pos val:arr)
        {
            sb.append(val.x).append(" ").append(val.y).append('\n');
        }

        System.out.print(sb);

    }

    static class Pos
    {
        int x;
        int y;

        public Pos(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayList<Info> arr = new ArrayList<>();

        for(int i=0; i<n; i++)
        {
            String str = br.readLine();
            int length = str.length();

            arr.add(new Info(str, length));
        }

        arr.sort((o1, o2) ->
        {
            if(o1.length == o2.length)
            {
                return o1.str.compareTo(o2.str);
            }
            return o1.length - o2.length;
        });

        StringBuilder sb = new StringBuilder();

        sb.append(arr.get(0).str).append('\n');

        for(int i=1; i<n; i++)
        {
            if(arr.get(i).str.equals(arr.get(i-1).str)) continue;
            sb.append(arr.get(i).str).append('\n');
        }

        System.out.print(sb);
    }

    static class Info
    {
        String str;
        int length;

        public Info(String str, int length)
        {
            this.str = str;
            this.length = length;
        }
    }
}

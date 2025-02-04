package Sort;
//수 정렬하기 4
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class boj_11931
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer> arr = new ArrayList<>();
        for(int i=0; i<n; i++)
        {
            arr.add(Integer.parseInt(br.readLine()));
        }

        arr.sort(Comparator.comparingInt(o->-o));

        StringBuilder sb = new StringBuilder();
        for(int val:arr)
        {
            sb.append(val).append('\n');
        }

        System.out.print(sb);
    }
}

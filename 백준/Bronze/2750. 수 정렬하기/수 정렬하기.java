import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer> arr = new ArrayList<>();
        for(int i=0; i<n; i++)
        {
            int num = Integer.parseInt(br.readLine());
            arr.add(num);
        }

        arr.sort(Comparator.comparingInt(o->o));

        for(int val:arr)
        {
            System.out.println(val);
        }
    }
}
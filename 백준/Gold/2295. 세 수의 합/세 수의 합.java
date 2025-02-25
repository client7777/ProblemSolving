import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
//a[i] + a[j] + a[k] = a[l]을 만족하는 a[l] 중에서 최댓값
//O(n^4) 풀이 = i,j,k,l에 대한 4중 for문
public class Main
{
    static int[] arr;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        for(int i=0; i<n; i++)
        {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        ArrayList<Integer> two = new ArrayList<>();
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                two.add(arr[i] + arr[j]);
            }
        }
        Collections.sort(two);
        for(int i= n-1;  i>0; i--)
        {
            for(int j=0; j<i; j++)
            {
                if(Collections.binarySearch(two, arr[i] - arr[j]) >= 0)
                {
                    System.out.print(arr[i]);
                    return;
                }
            }
        }
    }
}

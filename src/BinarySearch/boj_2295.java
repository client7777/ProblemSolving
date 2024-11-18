package BinarySearch;
//O(N^2logN)
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
//a[i] + a[j] + a[k] = a[l]을 만족하는 a[l] 중에서 최댓값
//O(n^4) 풀이 = i,j,k,l에 대한 4중 for문
//O(n^3) 풀이 = i,j,k에 대한 3중 for문, arr[i] + arr[j] + arr[k]가 배열에 있는지 이분탐색
public class boj_2295
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
        Arrays.sort(arr); //이진 탐색을 하기 위해서 오름차순 정렬
        ArrayList<Integer> two = new ArrayList<>(); //  미리 배열에서 원소들을 2개씩 합으로 묶음
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                two.add(arr[i] + arr[j]); //arr[i] + arr[j]
            }
        }
        Collections.sort(two);
        for(int i= n-1;  i>0; i--)
        {
            for(int j=0; j<i; j++)
            {
                //반환값이 0 이상이면 값이 리스트에 존재한다는 의미, 음수이면 리스트에 없다는 의미
                if(Collections.binarySearch(two, arr[i] - arr[j]) >= 0)
                {
                    System.out.print(arr[i]);
                    return;
                }
            }
        }
    }
}
//x+y+z=k에서 식을 변형해서 x+y=k-z
//k-z값이 two 리스트 안에 존재하는지 찾는 방식

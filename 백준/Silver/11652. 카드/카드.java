import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args)throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		long[] arr = new long[N];
		
		for(int i=0; i<N; i++)
		{
			arr[i] = Long.parseLong(br.readLine());
		}
		Arrays.sort(arr); // 배열을 입력받아서 오름차순으로 정렬
		
		int cnt = 1;
		long mxval = arr[0];
		int mxcnt = 0;
		for(int i=1; i<N; i++)
		{
			if(arr[i] == arr[i-1]) cnt++;
			else 
			{
				if(cnt > mxcnt)
				{
					mxcnt =cnt;
					mxval = arr[i-1];
				}
				cnt = 1;
			}
			if(i == N-1)
			{
				if(cnt> mxcnt)
				{
					mxcnt = cnt;
					mxval = arr[i-1];
				}
			}
			
		}
		
		System.out.print(mxval);
	}

}

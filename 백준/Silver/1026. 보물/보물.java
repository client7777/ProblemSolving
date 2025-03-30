

// A배열은 오름차순 정렬, B배열은 내림차순정렬을해서 각 요소끼리 곱하면 그 값이 최소값이다.
// 혹은 A,B배열 모두 오름차순 정렬한 뒤 A[i] * B[n-1-i]를 곱해줌

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] A = new int[n];
		int[] B = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		
		for(int i=0; i<n; i++)
		{
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st1.nextToken());
		}
		
		Arrays.sort(A);
		Arrays.sort(B);
		
		int ans = 0;
		for(int i=0; i<n; i++)
		{
			ans+=A[i]*B[n-1-i];
			
		}
		System.out.print(ans);
	}
}

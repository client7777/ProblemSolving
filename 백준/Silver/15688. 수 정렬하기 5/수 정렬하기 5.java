

import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args)throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] a = new int[n];
		
		for(int i=0; i<n; i++)
		{
			a[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(a);
		
		StringBuilder sb = new StringBuilder();
		
		for(int val: a)
		{
			sb.append(val).append('\n');
		}
		
		System.out.print(sb);
		
		
		
	}

}

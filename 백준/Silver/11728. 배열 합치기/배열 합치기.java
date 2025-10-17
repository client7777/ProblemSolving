

import java.util.*;
import java.io.*;

public class Main {
	
	

	public static void main(String[] args)throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] a = new int[n];
		int[] b = new int[m];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++)
		{
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<m; i++)
		{
			b[i] = Integer.parseInt(st.nextToken());
		}
		
		int pa = 0, pb = 0;
		
		while(pa < n && pb < m)
		{
			if(a[pa] >= b[pb])
			{
				sb.append(b[pb]).append(' ');
				pb++;
			}
			else 
			{
				sb.append(a[pa]).append(' ');
				pa++;
			}
			if(pa == n)
			{
				for(int i=pb; i<m; i++)
				{
					sb.append(b[i]).append(' ');
				}
			}
			if(pb == m)
			{
				for(int i=pa; i<n; i++)
				{
					sb.append(a[i]).append(' ');
					
				}
			}
		}
		System.out.print(sb);
	
	}

}

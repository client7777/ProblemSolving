package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_10610 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String n = br.readLine();

		char[] ch = n.toCharArray();
		Arrays.sort(ch);

		StringBuilder sb = new StringBuilder();

		int sum = 0;
		for(int i = ch.length - 1; i >= 0; i--){
			int num = ch[i] - '0';
			sum += num;
			sb.append(num);
		}

		if(sum % 3 != 0 || ch[0] != '0'){
			System.out.print(-1);
			return;
		}

		System.out.print(sb);
	}
}

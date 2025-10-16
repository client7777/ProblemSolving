import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String str = br.readLine();

		char[] chars = str.toCharArray();
		int bigDataCnt = 0;
		int securityCnt = 0;

		for(int i = 0; i < chars.length; i++){

			if(chars[i] == 'b'){
				i += 6;
				bigDataCnt++;
			}
			else if(chars[i] == 's'){
				i += 7;
				securityCnt++;
			}
		}

		if(bigDataCnt > securityCnt){
			System.out.print("bigdata?");
		}
		else if(bigDataCnt < securityCnt){
			System.out.print("security!");
		}
		else{
			System.out.print("bigdata? security!");
		}
	}
}
import java.io.*;

public class Main 
{
	public static void main(String[] args) throws IOException
    {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		//입력된 값과 아스키코드 값을 맞추기 위해 44031을 더한다.
		N += 44031;
		//한글로 출력하기 위해 char형으로 형 변환
		char C = (char) N;
		
		System.out.println(C);
	}

}
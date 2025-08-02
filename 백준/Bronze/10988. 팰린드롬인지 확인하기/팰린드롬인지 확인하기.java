import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String[] arr = str.split("");

        boolean flag = false;

        String str1 = "";
        String str2 = "";

        for(int i = 0; i < arr.length; i++){
            str1 += arr[i];
        }

        for(int i = arr.length - 1; i >= 0; i--){
            str2 += arr[i];
        }

        if(str1.equals(str2)){
            flag = true;
        }

        System.out.print(flag ? 1 : 0);
    }
}

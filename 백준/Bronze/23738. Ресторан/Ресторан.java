import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        String[] strings = str.split("");

        for(int i = 0; i < strings.length; i++){
            if(strings[i].equals("B")){
                strings[i] = "v";
            }
            else if(strings[i].equals("E")){
                strings[i] = "ye";
            }
            else if(strings[i].equals("H")){
                strings[i] = "n";
            }
            else if(strings[i].equals("P")){
                strings[i] = "r";
            }
            else if(strings[i].equals("C")){
                strings[i] = "s";
            }
            else if(strings[i].equals("Y")){
                strings[i] = "u";
            }
            else if(strings[i].equals("X")){
                strings[i] = "h";
            }
        }

        StringBuilder answer = new StringBuilder();
        for(int i = 0; i < strings.length; i++){
            answer.append(strings[i]);
        }

        System.out.print(answer.toString().toLowerCase());
    }
}

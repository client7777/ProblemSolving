import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main
{
    static HashMap<String, Double> blood = new HashMap<>();
    static HashMap<String, Parent> family = new HashMap<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String king = br.readLine();

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            String child = st.nextToken();
            String father = st.nextToken();
            String mom = st.nextToken();

            blood.putIfAbsent(child, -1d);
            blood.putIfAbsent(father, -1d);
            blood.putIfAbsent(mom, -1d);

            family.put(child, new Parent(father, mom));

        }

        blood.put(king, 1d);

        for(String s : blood.keySet())
        {
            dfs(s);
        }

        ArrayList<String> claimer = new ArrayList<>();
        for(int i=0; i<m; i++)
        {
            claimer.add(br.readLine());
        }

        String answer = "";
        double max = -1d;

        for(String str : claimer)
        {
            double val = blood.getOrDefault(str, -1d);

            if(val > max)
            {
                answer = str;
                max = val;
            }
        }

        System.out.print(answer);
    }

    static double dfs(String name)
    {
        // 해당 이름이 혈통에 존재하지 않는 경우 왕과 아무 관계가 없다고 가정하고 혈통의 값을 0으로 지정
        if(!blood.containsKey(name)) return 0.0;

        // 이미 계산된 값이 있는 경우
        if(blood.get(name) != -1) return blood.get(name);

        // 부모의 정보가 존재하지 않는 경우, 누군가의 부모로만 등장한 경우에는 혈통을 계산할 수 없다.
        if(!family.containsKey(name)) return 0.0;

        Parent p = family.get(name);
        String father = p.father;
        String mom = p.mother;

        double myBlood = (dfs(father) + dfs(mom)) / 2.0;

        blood.put(name, myBlood);
        return myBlood;
    }

    static class Parent
    {
        String father;
        String mother;

        public Parent(String father, String mother)
        {
            this.father = father;
            this.mother = mother;
        }
    }
}

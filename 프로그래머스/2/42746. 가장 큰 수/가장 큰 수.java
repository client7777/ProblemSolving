import java.util.*;

class Solution 
{
    public String solution(int[] numbers)
    {
        String answer = "";

        ArrayList<String> list = new ArrayList<>();
        for(int n:numbers)
        {
            list.add(String.valueOf(n));
        }

        list.sort(new Comparator<String>()
        {
            @Override
            public int compare(String o1, String o2) {

                String str1 = o1 + o2;
                String str2 = o2 + o1;

                return str2.compareTo(str1);
            }
        });

        for(String str:list)
        {
            answer += str;
        }

        return answer.startsWith("0") ? "0" : answer;
    }
}
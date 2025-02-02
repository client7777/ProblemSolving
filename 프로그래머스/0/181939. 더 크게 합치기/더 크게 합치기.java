class Solution {
    public int solution(int a, int b) {
        
        //정수형 -> 문자열
        String ab = String.valueOf(a) + String.valueOf(b); 
        String ba = String.valueOf(b) + String.valueOf(a);
        
        //문자열 -> 정수형
        if (Integer.parseInt(ab) >= Integer.parseInt(ba)) 
        { 
            return Integer.parseInt(ab);
        } 
        else 
        {
            return Integer.parseInt(ba);
        }

    }
}
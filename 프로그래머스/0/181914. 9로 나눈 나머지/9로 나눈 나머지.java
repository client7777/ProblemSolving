class Solution {
    public int solution(String number) {
        int answer = 0;
        int tot = 0;
        char[] ch = number.toCharArray();
        for(char c : ch) {
            tot += c - '0';
        }
        
        answer = tot % 9;
        return answer;
    }
}
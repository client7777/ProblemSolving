class Solution {
    public String solution(String n_str) {
        String answer = "";
        int index = 0;
        
        String[] arr = n_str.split("");
        for(int i = 0; i < arr.length; i++) {
            if(!arr[i].equals("0")) {
                index = i;
                break;
            }
            
        }
        
        for(int i = index; i < arr.length; i++) {
            answer += arr[i];
        }
        
        return answer;
    }
}
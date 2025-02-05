class Solution {
    public int solution(int n, int k) 
    {
        //양꼬치 -> 12000 * n 음료수 2000 * k
        
        return 12000 * n + 2000 * k - (n/10) * 2000;
        
    }
}
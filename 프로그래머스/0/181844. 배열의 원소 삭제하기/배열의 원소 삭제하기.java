import java.util.ArrayList;

class Solution {
    	public int[] solution(int[] arr, int[] delete_list) {
		int[] answer = {};

		ArrayList<Integer> del = new ArrayList<>();
		for(int val :delete_list){
			del.add(val);
		}

		ArrayList<Integer> ans = new ArrayList<>();
		for(int val :arr){
			if(del.contains(val)){
				continue;
			}

			ans.add(val);
		}

		answer = new int[ans.size()];
		for(int i = 0; i < ans.size(); i++){
			answer[i] = ans.get(i);
		}
		
		return answer;
	}
}
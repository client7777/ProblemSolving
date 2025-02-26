import java.util.*;

class Solution 
{
    public int[] solution(String[] genres, int[] plays)
    {
        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0; i<genres.length; i++)
        {
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
        }

        ArrayList<String> sortedCount = new ArrayList<>(map.keySet());
        sortedCount.sort((o1, o2) -> Integer.compare(map.get(o2), map.get(o1)));

        ArrayList<Integer> answerList = new ArrayList<>();

        for(String genre : sortedCount)
        {
            HashMap<Integer, Integer> playMap = new HashMap<>();

            for(int i=0; i<genres.length; i++)
            {
                if(genre.equals(genres[i]))
                {
                    playMap.put(i, plays[i]);
                }
            }

            ArrayList<Integer> playList = new ArrayList<>(playMap.keySet());
            playList.sort((o1, o2) -> {
                int cmp = Integer.compare(playMap.get(o2), playMap.get(o1));
                return (cmp == 0) ? Integer.compare(o1, o2) : cmp;
            });

            for(int i=0; i<Math.min(2, playList.size()); i++)
            {
                answerList.add(playList.get(i));  
            }
        }

        return answerList.stream().mapToInt(i -> i).toArray();
    }
}
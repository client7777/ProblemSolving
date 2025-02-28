import java.util.*;

class Solution 
{
    static class Song implements Comparable<Song>
    {
        int index;
        int playCount;

        public Song(int index, int playCount)
        {
            this.index = index;
            this.playCount = playCount;
        }

        @Override
        public int compareTo(Song o)
        {
            return this.playCount == o.playCount ? Integer.compare(this.index, o.index) : Integer.compare(o.playCount, this.playCount);
        }
    }
    public int[] solution(String[] genres, int[] plays)
    {
        Map<String, Integer> map = new HashMap<>(); // 장르별 총 재생 횟수 저장
        Map<String, ArrayList<Song>> genreSong = new HashMap<>(); // 장르별 곡 인덱스 저장
        for(int i=0; i<genres.length; i++)
        {
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);

            genreSong.putIfAbsent(genres[i], new ArrayList<>());
            genreSong.get(genres[i]).add(new Song(i, plays[i]));
        }

        List<String> list = new ArrayList<>(map.keySet());
        list.sort(((o1, o2) ->
                Integer.compare(map.get(o2), map.get(o1))
                ));

        List<Integer> answerList = new ArrayList<>();

        for(String genre : list)
        {
            List<Song> songs = genreSong.get(genre);
            Collections.sort(songs);
            
            for(int i=0; i<Math.min(2, songs.size()); i++)
            {
                answerList.add(songs.get(i).index);
            }
        }

        return answerList.stream().mapToInt(i -> i).toArray();
    }
}
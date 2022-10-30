package onboarding;

import java.util.*;

public class Problem7 {
    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        List<String> result = new ArrayList<>();//추천 결과
        TreeSet<String> friendSet = new TreeSet<>();//user의 친구 리스트
        HashMap<String, Integer> recommendScore = new HashMap<>();//key : 친구의 친구, value : 점수


        return result;
    }

    /**
     * user의 친구 목록을 구하는 기능
     */
    private static void makeUserFriendList(String user, List<List<String>> friends, TreeSet<String> friendSet) {
        for(List<String> relation : friends){
            if(relation.contains(user)){
                friendSet.add(relation.get(0));
                friendSet.add(relation.get(1));
            }
        }
        friendSet.remove(user);
    }

    /**
     * user와 함께 아는 친구 점수 계산 기능
     */
    private static void addFriendOfFriend(String user, List<List<String>> friends, TreeSet<String> friendSet, HashMap<String, Integer> recommendScore) {
        for(String friend : friendSet){
            for(List<String> relation : friends){
                String friendOfFriend = "";
                if(relation.get(0).equals(friend)){
                    friendOfFriend = relation.get(1);
                }else if(relation.get(1).equals(friend)){
                    friendOfFriend = relation.get(0);
                }

                if(friendOfFriend.equals(user) || friendOfFriend.isEmpty()){
                    continue;
                }

                if(!recommendScore.containsKey(friendOfFriend)){
                    recommendScore.put(friendOfFriend, 10);
                }else{
                    recommendScore.replace(friendOfFriend, recommendScore.get(friendOfFriend) + 10);
                }
            }
        }
    }

    /**
     * user의 타임 라인에 방문한 사람 점수 계산 기능
     */
    private static void addVisitor(List<String> visitors, TreeSet<String> friendSet, HashMap<String, Integer> recommendScore) {
        for(String visitor : visitors){
            if(!friendSet.contains(visitor)){
                if(!recommendScore.containsKey(visitor)){
                    recommendScore.put(visitor, 1);
                }else{
                    recommendScore.replace(visitor, recommendScore.get(visitor) + 1);
                }
            }
        }
    }
}

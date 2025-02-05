

import java.util.HashMap;


class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        
        HashMap<String,Integer> cntMap = new HashMap<>();
        
        for(int i=0;i<clothes.length;i++){
            String category = clothes[i][1];
            cntMap.put(category,cntMap.getOrDefault(category,0)+1);
        }
        

        
        for(int cnt : cntMap.values()){
            answer *= (cnt+1);
        }
        return answer-1;
    }
}
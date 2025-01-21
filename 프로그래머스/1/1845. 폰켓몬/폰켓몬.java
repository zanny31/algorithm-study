import java.util.HashSet;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        int max = nums.length/2;
        
        HashSet<Integer> setNums = new HashSet();
        for(int num : nums){
            setNums.add(num);
        }
        
        answer = setNums.size();
        return (max <= answer) ? max : answer;
        
        // return 가능한 최대 개수 : nums/2
        // 중복 제거 후 결과값 vs 최대 개수 비교 후 return
        // keypoint : 중복 제거 / 중복 제거를 만나면 set을 기억할 것 
        
        /*
            Set은 인터페이스, HashSet은 Set의 구현체 중 하나 
            HashSet은 해싱 알고리즘을 이용하여 저장하는 Set 
        */
        
    
    }
}
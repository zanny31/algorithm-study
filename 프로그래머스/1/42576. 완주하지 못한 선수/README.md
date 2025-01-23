# [level 1] 완주하지 못한 선수 - 42576 

[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/42576) 

### 성능 요약

메모리: 97.9 MB, 시간: 305.18 ms

### 구분

코딩테스트 연습 > 해시

### 채점결과

정확성: 58.3<br/>효율성: 41.7<br/>합계: 100.0 / 100.0

### 제출 일자

2025년 01월 23일 14:59:03

### 문제 설명

<p>수많은 마라톤 선수들이 마라톤에 참여하였습니다. 단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주하였습니다.</p>

<p>마라톤에 참여한 선수들의 이름이 담긴 배열 participant와 완주한 선수들의 이름이 담긴 배열 completion이 주어질 때, 완주하지 못한 선수의 이름을 return 하도록 solution 함수를 작성해주세요.</p>

<h5>제한사항</h5>

<ul>
<li>마라톤 경기에 참여한 선수의 수는 1명 이상 100,000명 이하입니다.</li>
<li>completion의 길이는 participant의 길이보다 1 작습니다.</li>
<li>참가자의 이름은 1개 이상 20개 이하의 알파벳 소문자로 이루어져 있습니다.</li>
<li>참가자 중에는 동명이인이 있을 수 있습니다.</li>
</ul>

<h5>입출력 예</h5>
<table class="table">
        <thead><tr>
<th>participant</th>
<th>completion</th>
<th>return</th>
</tr>
</thead>
        <tbody><tr>
<td>["leo", "kiki", "eden"]</td>
<td>["eden", "kiki"]</td>
<td>"leo"</td>
</tr>
<tr>
<td>["marina", "josipa", "nikola", "vinko", "filipa"]</td>
<td>["josipa", "filipa", "marina", "nikola"]</td>
<td>"vinko"</td>
</tr>
<tr>
<td>["mislav", "stanko", "mislav", "ana"]</td>
<td>["stanko", "ana", "mislav"]</td>
<td>"mislav"</td>
</tr>
</tbody>
      </table>
<h5>입출력 예 설명</h5>

<p>예제 #1<br>
"leo"는 참여자 명단에는 있지만, 완주자 명단에는 없기 때문에 완주하지 못했습니다.</p>

<p>예제 #2<br>
"vinko"는 참여자 명단에는 있지만, 완주자 명단에는 없기 때문에 완주하지 못했습니다.</p>

<p>예제 #3<br>
"mislav"는 참여자 명단에는 두 명이 있지만, 완주자 명단에는 한 명밖에 없기 때문에 한명은 완주하지 못했습니다.</p>

<hr>

<p>※ 공지 - 2023년 01월 25일 테스트케이스가 추가되었습니다.</p>


> 출처: 프로그래머스 코딩 테스트 연습, https://school.programmers.co.kr/learn/challenges

### 학습 포인트
1. 처음엔 A 배열인 participant의 값으로 B 배열인 completion과 비교하면서 동일 여부를 체크했음. 근데 동명이인 문제 때문에 해결이 안됨
```
class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        for(String runner : participant ){
            boolean state = false; 
            if()
            for(String finishRunner : completion){
                if(runner.equals(finishRunner)){
                    state = true;
                }
            }
            
            if(state == false){
                answer = runner;
                break;
            }
        }
        
        return answer;
    }
}
``` 

2. 배열이 아닌 ArrayList로 변경 후 제거하는 방향으로 코드 수정함. 
정확성 테스트는 모두 통과했지만, 효율성 테스트에서 시간이 초과되어 실패. (성능 이슈) 
```
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        ArrayList<String> participantList = new ArrayList<>(Arrays.asList(participant));
        
        for(String runner : completion){
            if(participantList.contains(runner)){
                participantList.remove(runner);
            }
        }
        
        answer = participantList.get(0);
        
        return answer;
    }
}
```



3. 다른 분이 작성한 풀이를 참고하여, 배열들을 정렬한 후 서로 비교하는 방식으로 코드를 구현함.
   마지막에 participant[participant.length - 1]을 사용하는 이유는, participant 배열의 마지막에 완주하지 않은 사람이 있다면 for문으로 비교할 수 없기 때문
```

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        Arrays.sort(participant);
        Arrays.sort(completion);
        
        for(int i = 0; i < completion.length; i++) {
            if(!participant[i].equals(completion[i])) {
                answer = participant[i];
                return answer;
            }
        }
 
        answer = participant[participant.length - 1];
        return answer;

    }
}
``` 
   

4. 좀 더 찾아보니 HashMap을 이용한 문제 풀이 방법도 있었다.
HashMap에서 동일한 key 개수를 구할 수 있다. `x.getOrDefault`
왜 +1을 하는지 궁금했는데, 숫자를 깔끔하게 맞추기 위함이었다.
+1을 안하면 `cm.get(runner) == 0`가 아닌 `cm.get(runner) == -1`로 구현해야 한다.. 

```
class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> cm = new HashMap<>();
        
        for(String finishRunner : completion){
            cm.put(finishRunner, cm.getOrDefault(finishRunner,0)+1);
        }
        
        for(String runner : participant){
            if(!cm.containsKey(runner) || cm.get(runner) == 0){
                return runner;
            } else {
                cm.put(runner, cm.get(runner) - 1);
            }
        }
        
        return answer;
    }
}
```

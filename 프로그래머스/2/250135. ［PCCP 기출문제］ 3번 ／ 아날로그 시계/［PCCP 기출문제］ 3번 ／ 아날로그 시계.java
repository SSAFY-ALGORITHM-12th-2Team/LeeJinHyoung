class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int sTime=h1*3600+m1*60+s1;
        int eTime=h2*3600+m2*60+s2;
        
        int answer = eTime * 59/3600 + eTime * 719/43200 - sTime * 59/3600 - sTime * 719/43200;
        if (sTime >= 43200) {
            answer += 1;
        }
        if (eTime >= 43200) {
            answer -= 1;
        }
        if (sTime * 59 % 3600 == 0 || sTime * 719 % 43200 == 0) {
            answer += 1;
        }
        return answer;
    }
}
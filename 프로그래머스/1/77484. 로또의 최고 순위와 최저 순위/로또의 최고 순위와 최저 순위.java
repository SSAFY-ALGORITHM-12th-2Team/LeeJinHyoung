class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        
        int zero=0;
        int score=0;
        int ranking=6;
        for(int i=0;i<6;i++)
        {
            if(lottos[i]==0)
            {
                zero++;
            }
            else{
                for(int j=0;j<6;j++)
                {
                    if(lottos[i]==win_nums[j])
                    {
                        score++;
                    }
                }
            }
        }
        switch(score)
        {
            case 0:
                if(zero==0)
                {
                    answer[0]=6;
                    answer[1]=6;
                    break;
                }
                else{
                answer[0]=7-zero;
                answer[1]=6;
                break;
                }
            case 1:
                answer[0]=6-zero;
                answer[1]=6;
                break;
            default:
                answer[0]=7-score-zero;
                answer[1]=7-score;
                break;
        }
        return answer;
    }
}
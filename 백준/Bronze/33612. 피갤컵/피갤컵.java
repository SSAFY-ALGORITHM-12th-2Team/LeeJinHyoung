import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine().trim());
        // 기준 연도 및 월
        long baseYear = 2024;
        long baseMonth = 8;  // 8월

        // 회차 N은 (N-1) * 7개월 뒤
        long monthsToAdd = (N - 1) * 7;

        long totalMonths = baseMonth + monthsToAdd;  
        // 연도 증가 분
        long yearIncrement = (totalMonths - 1) / 12;  
        long resultYear = baseYear + yearIncrement;

        // 월 계산 (1~12)
        long resultMonth = totalMonths % 12;
        if (resultMonth == 0) {
            resultMonth = 12;
        }

        System.out.println(resultYear + " " + resultMonth);
    }
}

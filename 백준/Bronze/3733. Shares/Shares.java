import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            st = new StringTokenizer(line);
            int N = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());
            sb.append(S / (N + 1)).append("\n");
        }

        System.out.print(sb.toString());
        br.close();
    }
}

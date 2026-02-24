class Solution {
    public String makeLargestSpecial(String s) {
        return F(s);
    }

    private String F(String s) {
        List<String> mountains = new ArrayList<>();
        int balance = 0;
        int start = 0;

        // mountain 분해
        for (int i = 0; i < s.length(); i++) {
            balance += (s.charAt(i) == '1') ? 1 : -1;

            if (balance == 0) {
                String inner = s.substring(start + 1, i);
                String transformed = "1" + F(inner) + "0";
                mountains.add(transformed);
                start = i + 1;
            }
        }

        // 내림차순 정렬
        mountains.sort(Collections.reverseOrder());

        // 이어붙이기
        StringBuilder res = new StringBuilder();
        for (String m : mountains) {
            res.append(m);
        }
        return res.toString();
    }
}
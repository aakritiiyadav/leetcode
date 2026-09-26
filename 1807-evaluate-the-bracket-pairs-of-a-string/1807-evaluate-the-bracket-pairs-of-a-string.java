class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {

        // 1. Store knowledge in HashMap
        Map<String, String> map = new HashMap<>();

        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder ans = new StringBuilder();

        // 2. Traverse string
        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {

                // Find closing bracket
                int j = i + 1;

                while (s.charAt(j) != ')') {
                    j++;
                }

                // Extract key
                String key = s.substring(i + 1, j);

                // Replace with value or ?
                ans.append(map.getOrDefault(key, "?"));

                // Jump to ')'
                i = j;

            } else {
                // Normal character
                ans.append(s.charAt(i));
            }
        }

        return ans.toString();
    }
}
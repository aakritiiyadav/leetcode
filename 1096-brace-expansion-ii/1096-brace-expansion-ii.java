class Solution {

    String s;
    int index;

    public List<String> braceExpansionII(String expression) {

        s = expression;
        index = 0;

        Set<String> set = solve();

        List<String> ans = new ArrayList<>(set);
        Collections.sort(ans);

        return ans;
    }

    // Parses an expression until '}' or end
    public Set<String> solve() {

        Set<String> result = new HashSet<>();
        result.add("");

        while (index < s.length() && s.charAt(index) != '}') {

            char ch = s.charAt(index);

            if (ch == ',') {
                // OR operation
                index++;

                Set<String> next = solve();

                result.addAll(next);

            } 
            else {

                Set<String> part;

                if (ch == '{') {

                    index++; // skip {

                    part = solve();

                    index++; // skip }

                } 
                else {

                    part = new HashSet<>();

                    part.add(String.valueOf(ch));

                    index++;
                }

                // Concatenation
                result = multiply(result, part);
            }
        }

        return result;
    }

    public Set<String> multiply(Set<String> a, Set<String> b) {

        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}
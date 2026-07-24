class Solution {

    Set<String> ans = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {

        int leftRemove = 0, rightRemove = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                leftRemove++;
            } else if (c == ')') {

                if (leftRemove == 0)
                    rightRemove++;
                else
                    leftRemove--;
            }
        }

        dfs(s, 0, leftRemove, rightRemove, 0, new StringBuilder());

        return new ArrayList<>(ans);
    }

    private void dfs(String s,
                     int index,
                     int leftRemove,
                     int rightRemove,
                     int balance,
                     StringBuilder path) {

        if (index == s.length()) {

            if (leftRemove == 0 &&
                rightRemove == 0 &&
                balance == 0)

                ans.add(path.toString());

            return;
        }

        char ch = s.charAt(index);

        int len = path.length();

        if (ch == '(') {

            if (leftRemove > 0)
                dfs(s, index + 1,
                        leftRemove - 1,
                        rightRemove,
                        balance,
                        path);

            path.append(ch);

            dfs(s, index + 1,
                    leftRemove,
                    rightRemove,
                    balance + 1,
                    path);

            path.setLength(len);

        }

        else if (ch == ')') {

            if (rightRemove > 0)
                dfs(s, index + 1,
                        leftRemove,
                        rightRemove - 1,
                        balance,
                        path);

            if (balance > 0) {

                path.append(ch);

                dfs(s, index + 1,
                        leftRemove,
                        rightRemove,
                        balance - 1,
                        path);

                path.setLength(len);
            }

        }

        else {

            path.append(ch);

            dfs(s,
                index + 1,
                leftRemove,
                rightRemove,
                balance,
                path);

            path.setLength(len);
        }
    }
}
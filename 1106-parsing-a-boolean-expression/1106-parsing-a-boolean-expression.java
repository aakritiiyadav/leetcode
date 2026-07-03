class Solution {
    public boolean parseBoolExpr(String expression) {
        Stack<Character> stack = new Stack<>();

        for (char ch : expression.toCharArray()) {

            // skip commas
            if (ch == ',') continue;

            if (ch != ')') {
                stack.push(ch);
            } else {

                int t = 0, f = 0;

                // collect values inside bracket
                while (stack.peek() != '(') {
                    char curr = stack.pop();
                    if (curr == 't') t++;
                    else if (curr == 'f') f++;
                }

                // remove '('
                stack.pop();

                // operator
                char op = stack.pop();

                char result;

                if (op == '!') {
                    result = (f == 1) ? 't' : 'f';
                } 
                else if (op == '&') {
                    result = (f > 0) ? 'f' : 't';
                } 
                else { // '|'
                    result = (t > 0) ? 't' : 'f';
                }

                stack.push(result);
            }
        }

        return stack.peek() == 't';
    }
}
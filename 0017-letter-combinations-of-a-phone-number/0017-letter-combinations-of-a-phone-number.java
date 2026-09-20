class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if(digits.length()==0){
            return ans;
        }
        String[] map={"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        backtrack(map,0,new StringBuilder(),digits,ans);
        return ans;

        
    }
    private void backtrack(String[] map,int index, StringBuilder current, String digits, List<String> ans){
        if(index==digits.length()){
            ans.add(current.toString());
            return;
        }
        int digit = digits.charAt(index)-'0';
        String letters= map[digit];
        for(char ch:letters.toCharArray()){
            current.append(ch);
            backtrack(map,index+1,current,digits,ans);
            current.deleteCharAt(current.length()-1);
        }
        

    }

}
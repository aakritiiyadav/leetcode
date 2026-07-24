class Solution {
    List<List<String>> ans= new ArrayList<>();
    HashSet<Integer> cols= new HashSet<>();
     HashSet<Integer> diag1= new HashSet<>();
      HashSet<Integer> diag2= new HashSet<>();

    public List<List<String>> solveNQueens(int n) {
        char[][]board= new char[n][n];
        for(char[]row:board){
            Arrays.fill(row,'.');
        }
        backtrack(0,board);
        return ans;
        
    }
    private void backtrack(int row, char[][]board){
        if(row==board.length){
            List<String> temp= new ArrayList<>();
            for(char[]r:board){
                temp.add(new String(r));
            }
            ans.add(temp);
            return;
        }
        for( int col=0;col<board.length;col++){
            if(cols.contains(col)||diag1.contains(row-col)||diag2.contains(row+col))continue;
            board[row][col]='Q';
            cols.add(col);
            diag1.add(row-col);
            diag2.add(row+col);
            backtrack(row+1,board);
            board[row][col]='.';
            cols.remove(col);
            diag1.remove(row - col);
            diag2.remove(row + col);


        }
    }
}
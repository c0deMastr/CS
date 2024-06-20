public class BoardRec(){
    int[][] grid;
    int[] leftClues;
    int[] rightClues;
    int[] topClues;
    int[] bottomClues;
/*
*   The naive approach is to generate all possible configurations of numbers from 1 to 9 to fill the empty cells. 
    try every configuration one by one until the correct configuration is found,
    i.e. for every unassigned position fill the position with a number from 1 to 9. 
    After filling all the unassigned positions check if the matrix is safe or not. 
    If safe print else recurs for other cases. 
 */
    public Board(String str){
        topClues = new int[4];
        bottomClues = new int[4];
        leftClues = new int[4];
        rightClues = new int[4];
        grid = new int[4][4];
        String curItem = getNextItem(str);
        str = str.substring(str.indexOf(",") + 2);
        //sets top clues
        for(int i = 0; i < 4; i++)
            topClues[i] = Character.getNumericValue(curItem.charAt(i));
        //sets rows clues
        int currentRow = 0;
        for(int i = 0; i < 4; i++){
            curItem = getNextItem(str);
            if(curItem.length() == 6){
                leftClues[currentRow] = Integer.parseInt(curItem.substring(0, 1));
                for(int col = 0; col < 4; col++)
                    grid[currentRow][col] = Integer.parseInt(curItem.substring(col + 1, col + 2));
                rightClues[currentRow] = Integer.parseInt(curItem.substring(5));
            }else{
                leftClues[currentRow] = Integer.parseInt(curItem.substring(0, 1));
                rightClues[currentRow] = Integer.parseInt(curItem.substring(1));
            }
            str = str.substring(str.indexOf(",") + 2);
            currentRow ++;
        }
        //sets bottom clues
        curItem = getNextItem(str);
        for(int i = 0; i < 4; i++)
            bottomClues[i] = Character.getNumericValue(curItem.charAt(i));
         setBasedClues();
         solve(0,0);
    }
    private void setBasedClues(){
        //fill in based on 4's as clues;
        for(int i = 0; i < 4; i++){
            if(leftClues[i] == 4)
                for(int col = 0; col < 4; col ++)
                    grid[i][col] = col + 1;
            if(rightClues[i] == 4){
                int z = 1;
                for(int col = 4; col > 0; col --){
                    grid[i][col - 1] = z;
                    z++;
                }
            }
            if(topClues[i] == 4)
                for(int row = 0; row < 4; row++)
                    grid[row][i] = row + 1;
            if(bottomClues[i] == 4){
                int val = 1;
                for(int row = 4; row > 0; row--){
                    grid[row][i] = val;
                    val ++;
                }
            }
        }
                //fill in 4 if clue is 1  - rows
        for(int row = 0; row < 4; row++){
            if(leftClues[row] == 1)
                grid[row][0] = 4;
            if(rightClues[row] == 1)
                grid[row][3] = 4;
        }
                //fill in 4 if clue is 1  - columns
        for(int col = 0; col < 4; col ++){
            if(topClues[col] == 1)
                grid[0][col] = 4;
            if(bottomClues[col] == 1)
                grid[3][col] = 4;
        }
    }
    //parameters 
    private void solve(int curCell){
        int row = curCell / 4;
        int col = curCell % 4;
        for(int placer = 0; placer < 4; placer++){
            grid[row][col] = placer;
            if(isComplete() && isCorrect())
            if(curCell % 3 ==4 )
                else if ( threeOrFour().equals("false"))
                    return solve(curCell / 3);
            
                return grid;
            else return solve(curCell ++);
        if(Exception !- true){
            Scanner scan = new Scanner(System.in)
        }
        }
        //if row = 8 and col = 9 that means grid is solved or unsolvable.
        //if col = 9 set col to 1 and row ++
        //
    }
    private boolean isComplete(){
            for(int[] row : grid){
                for(int item : row){
                    if(item == 0)
                        return false;
                }
        }
        return true;
    }
    private boolean isCorrect(){
        return true;
    }



}
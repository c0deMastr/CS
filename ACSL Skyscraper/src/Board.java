import java.util.Scanner;


public class Board{
    int[][] grid;
    int[] leftClues;
    int[] rightClues;
    int[] topClues;
    int[] bottomClues;
    //sets the grid to the given row and the clue arrays to there respective clues
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
        solve();
    }
    //displays the current state of the grid and the clue arrays
    public void display(){
        System.out.println("Left clues are:");
        for(int i : leftClues)
            System.out.print(i + ", ");
        System.out.println("");
        System.out.println("Right clues are:");
       for(int i : rightClues)
            System.out.print(i + ", ");
        System.out.println("");
        System.out.println("top clues are:");
        for(int i : topClues)
            System.out.print(i + ", ");
        System.out.println("");
        System.out.println("bottom clues are:");
        for(int i : bottomClues)
            System.out.print(i + ", ");
        System.out.println("");
        System.out.println("the board is:");
        for(int[] row : grid){
            for(int col : row){
                System.out.print(col + ", ");
            }
            System.out.println("");
        }


        

    }
    //A helper method to return the current comma seperated item in iput string
    private String getNextItem(String fullInput){
        if(fullInput.indexOf(",") > -1)
            return fullInput.substring(0,fullInput.indexOf(","));
        return fullInput;
    }
    //main recursion method
    public void solve(){
        while (!isSolved()) {
            update ();
        }
    }
    //returns false if any of the grid values are still 0.
    public boolean isSolved(){
            for(int[] row : grid){
                for(int item : row){
                    if(item == 0)
                        return false;
                }
        }
        return true;
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
    public void update(){
        //fill in missing square in a row (top to bottom)
        for(int row = 0; row < 4; row++){
            if(countZeros(grid[row]) == 1){
                grid[row] = fillMissingSquare(grid[row]);
            }      
        }
        //fill in missing square in a columm (left to right by column)
        for(int col = 0; col < 4; col++){
            int [] curCol = new int[4];
            for(int i = 0; i < 4; i++)
                curCol[i] = grid[i][col]; 
            if(countZeros(curCol) == 1)
                curCol = fillMissingSquare(curCol);
            for(int i = 0; i < 4; i++)
                grid[i][col] = curCol[i]; 
        } 
    }
    public int countZeros(int[] curGroup){
        int zCount = 0;
        for(int item : curGroup)
            if(item == 0)
                zCount++;
        return zCount;
    }

    /*
     * PRECONDITION:  curGroup has a single 0 in it
     */
    public int[] fillMissingSquare(int[] curGroup){

        boolean[] values = {false, false, false, false};
        for(int item : curGroup)
            if(item != 0)
                values[item - 1] = true;

        int missingNum = 0;
        for(int i = 0; i < 4; i++){
            if(values[i] == false)
                missingNum = i + 1;
        }

        for(int i = 0; i < 4 ; i++)
            if(curGroup[i] == 0)
                curGroup[i] = missingNum;

        return curGroup;
    }
    // returns the item at the input row / col
    public String getItem(String userInput){
        int rowIndex = Integer.valueOf(userInput.substring(0,1));
        int colIndex = Integer.valueOf(userInput.substring(1));
        int value = grid[rowIndex - 1][colIndex -1];
        return Integer.toString(value);
    }
}
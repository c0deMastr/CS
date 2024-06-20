public class Minesweeper{
    private Tile[][] board;
    private Timer timer;
    private int mines;

    /* CONSTRUCTOR
    * Creates a new Minesweeper game
    * @Param rowLen - the row size of the array
    * @Param colLen - the col size of the array
    * @Param mines - the number of mines in the game
    */
    public Minesweeper (int rowLen, int colLen, int m) {
        board = new Tile[rowLen][colLen];
        timer = new Timer ();
        for (int r = 0; r < board.length; r++)
            for (int c = 0; c < board[1].length; c++)
                board[r][c] = new Tile();
        mines = m;
    }
    
    /* reveals the tile @param row/ col; if 0 reveals surronding */
    public void open (int row, int col) {
        if (!isValidTile(row, col) || !board[row][col].isHidden() || isGameLost())
            return;

        if (! hasGameStarted())
            setBoard(row, col);
        
        Tile curCell = board[row][col];
        curCell.reveal();
        //if its a 0 and not bomb, recursively open surronding tiles. 
        if (curCell.getTileVal() == 0 && ! curCell.getIsMine()) {
            for (int r = Math.max (0, row - 1); r <= Math.min (row + 1, board.length - 1 ); r++)
                for (int c = Math.max (0, col - 1); c <= Math.min (col + 1, board[1].length - 1); c++)
                    open (r,c);      
        }  
    
    }
    
    /* returns true if the game has been started  */
    public boolean hasGameStarted() {
        for (Tile[] row : board)
            for (Tile tile : row)
                if (tile.isRevealed())
                    return true;
        return false;
    }
    
    /* if neighboring flags = tile value @param row/ col;  sweep reveals all other adjacent tiles */
    public void sweep (int row, int col) {
        Tile curTile = board[row][col];
        if (isGameLost()
            || curTile.getTileVal() != countSurroundingFlags(row, col)
            || !board[row][col].isRevealed())
                return;
        
        //go through 3 by 3 and reveal all cells
        for (int r = Math.max (0, row - 1); r <= Math.min (row + 1, board.length - 1 ); r++)
            for (int c = Math.max (0, col - 1); c <= Math.min (col + 1, board[1].length - 1); c++)
                if (board[r][c].isHidden() && ! board[r][c].isFlagged())
                    open(r,c);
    }
    
    /* helper method for sweep
     * @param row/ col - the row and col of the tile to check
     * @return the number of surrounding flags
     */
    private int countSurroundingFlags (int row, int col) {
        int count = 0;
        for (int r = row-1; r <= row+1; r++)
            for (int c = col-1; c <= col+1; c++)
               if (isValidTile (r, c) && board[r][c].isFlagged())
                  count++;
        return count;
    }

    /* sets the board with all random mines and tile values
     * @param row/ col - the row and col of the tile that was clicked
     * first tile is never a mine
     */
    private void setBoard (int row, int col) {
        System.out.println("setting board, " + row + "row and "+ col + "col is clear");
        setMines(row, col);
        for (int r =  0; r < board.length; r++)
            for (int c = 0; c < board[r].length; c++)
                setValue(r, c);
    }

    /* helper method for setBoard
     * randomly assigns mines on the board until all mines have been placed
     * @param row/ col - the row and col of the tile that was clicked(safe)
     */
    private void setMines (int row, int col) {
        int minesLeft = mines;
        while( minesLeft > 0) {
            int randomRow = (int)(Math.random() * board.length);
            int randomCol = (int)(Math.random() * board[0].length);
            Tile randomTile = board[randomRow][randomCol];
            if (! randomTile.getIsMine() && !(randomRow == row && randomCol == col)) {
                randomTile.setToMine();
                minesLeft--;
            } 
        }
    }

    /* helper method for setBoard
     * sets the value of the tile on board @row/col to the number of mines surrounding it
     * @param row/ col - the row and col of the tile to set the value of.
     */
    private void setValue (int row, int col) {
        int surrondingMines  = countSurroundingMines(row, col);
        board[row][col].setTileVal(surrondingMines);
    }

    /* helper method for setValue
     * handles edge cases where the tile is on the edge
     * @param row/ col - the row and col of the tile to check
     * @return the number of mines surrounding the tile
     */
    private int countSurroundingMines (int row, int col) {
        int count = 0;
        for (int r = row-1; r <= row+1; r++)
            for (int c = col-1; c <= col+1; c++)
               if (isValidTile (r, c) && board[r][c].getIsMine())
                  count++;
        return count;
    }

    /* returns true if the @param-row and @param-col exist within the board*/
    private boolean isValidTile (int row, int col) {
        return row >= 0 && row < board.length &&
               col >= 0 && col < board[row].length;
    }
   /* either flags or unflags a tile @row/col on the board depending on the current state of the tile.
    * if the tile is already revealed does nothing.
    */
    public void flag (int row, int col) {    
        Tile curTile =  board[row][col];
        if (!isGameLost()){
            if (! curTile.isFlagged() && curTile.isHidden())
                board[row][col].flag();
            
            else if (curTile.isFlagged())
                    board[row][col].unflag();
        }
    }

    /* finds a random mine on the board and reveals it.
     * @return -  if all mines have been revealed does nothing. 
     */
    public void revealNextMine () {
        int len = board[0].length;
        int start = (int) (Math.random() * board.length * len);
        int loc = start;
        int curRow = loc/len;
        int curCol = loc%len;

        while (! (board[curRow][curCol].getIsMine() && !board[curRow][curCol].isRevealed())){ 
            loc = (loc+1) % (board.length * len);
            curRow = loc/len;
            curCol = loc%len;
            if(loc == start)
                return;
        }

        board[curRow][curCol].reveal();
        
}   

    /* checks if the current game is lost, returns true if a mine has been opened */
    public boolean isGameLost () {
        for (Tile [] row : board)
            for(Tile tile : row)
                if(tile.getIsMine() && tile.isRevealed()){
                    timer.pause();
                    return true;
                }
        return false;
    }
    
    /* checks if the current game is won, returns true if all non-mine tiles have been revealed */
    public boolean isGameWon() {
        for (Tile [] row : board)
            for(Tile tile : row)
                if (!tile.getIsMine() && !tile.isRevealed())
                    return false;
        timer.pause();
        return true;
    }

    /*returns a string of the current state of the board, using \n to seperate rows
     * @return str - the string version of the board, from the players POV.
     */
    public String toString () {
      String str = "";
        for (int r = 0; r < board.length; r ++){
            for (int c = 0; c < board[0].length; c++){
                str += board[r][c].toString ();
            }
            str += '\n';
        }
        return str;

    }
    
    /* returns the number of bombs still hidden, or # of flags still needing to be placed */
    public int getFlagsLeft() {
        int flagCount = mines;
        for(Tile[] row : board)
            for(Tile tile : row)
                if(tile.isFlagged())
                    flagCount--;
        return flagCount;
    }
    
    //returns the amount of time the game has been running for. 
    public int getCurTime(){
        return timer.getCurTime();
    }
}

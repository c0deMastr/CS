/* a class for a tile of the board */
public class Tile{

    private final static int HIDDEN = 0;
    private final static int REVEALED = 1;
    private final static int FLAGGED = 2;

    private int state;
    private boolean isMine;
    private int tileVal; 

    /* creates a new tile, always hidden and not a mine */
    public Tile(){
        isMine = false;
        tileVal = 0;
        state = HIDDEN;
    }
    /* sets the value of any tile */
    public void setTileVal (int set) {
        tileVal = set;
    }
    /* returns the value of any tile */
    public int getTileVal() {
        return tileVal;
    }
    /* sets the tile to be a mine */
    public void setToMine() { 
        isMine = true;
    }
    /* returns true if the tile is a mine */
    public boolean getIsMine() {
        return isMine;
    }
    /* reveals the tile */
    public void reveal() {
        state = REVEALED;
    }
    /*flags the tile */
    public void flag() {
        state = FLAGGED;
    }
    /*unflags the tile */
    public void unflag() {
        state = HIDDEN;
    }
    /* returns true if the tile is hidden */
    public boolean isHidden() {
        return state == HIDDEN;
    }
    /* returns true if the tile is revealed */
    public boolean isRevealed() {
        return state == REVEALED;
    }
    /* returns true if the tile is flagged */
    public boolean isFlagged() {
        return state == FLAGGED;
    }
    /* returns a string representation of the tile */
    public String toString () {
        if (isHidden())
          return "h";

        if (isFlagged())
            return "f";
        
        if (getIsMine())
            return "m";

        return ""+ getTileVal();

    }

}

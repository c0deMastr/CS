// a class for the snake on the board
import java.util.ArrayList;
import java.util.Arrays;

public class Snake {
    //length is the length of the snake
    private int length;
    //direction is the direction the snake is moving, 0 is up, 1 is right, 2 is down, 3 is left
    private int direction;
    //occupiedTiles is an arraylist of the tiles the snake is occupying, row and col are inside every int array
    private ArrayList<int[]> occupiedTiles;
    final int UP = 0;
    final int RIGHT = 1;
    final int DOWN = 2;
    final int LEFT = 3;

    /* CONSTRUCTOR for the singular snake on the board 
     * @PARAM length - is the starting length of the snake
     * @PARAM occupiedTiles - is an array of the tiles(row,col) the snake is occupying at the start 
     */
    public Snake (int length, int[][] startingTiles) {
        this.length = length;
        this.direction = 1; //right
        occupiedTiles  = new ArrayList<int[]>();
        
        // adds all the starting tiles to the occupiedTiles arraylist
        for (int[] tile: startingTiles) {
            occupiedTiles.add(tile);
        }
    }
    //moves the snake one tile in the direction it is facing
    public void move() {
        int[] lastTile = getHeadTile();
        int[] newtile;

        if(direction == UP) 
            newtile = new int[] {lastTile[0] - 1, lastTile[1]};   
        else if(direction == RIGHT) 
            newtile = new int[] {lastTile[0], lastTile[1] + 1};
        else if(direction == DOWN) 
            newtile = new int[] {lastTile[0] + 1, lastTile[1]};
        else 
            newtile = new int[] {lastTile[0], lastTile[1] - 1};
        
            occupiedTiles.add (newtile);
            //if snake has grown dont remove the last tile
            if(occupiedTiles.size() > length)
                occupiedTiles.remove (0);
    }

    //checks if a tile(r,c) is on the board
    private boolean isValidTile (int[] tile) {
        if(tile[0] < 1 || tile[0] > 21  || tile[1] < 1 || tile[1] > 24)
            return false;
        return true;
    }

    //returns the tiles(row, col) the snake is occupying
    public ArrayList<int[]> getoccupiedTiles() {
        ArrayList <int[]> temp = new ArrayList<int[]>();

        for(int [] tile : occupiedTiles) {
            temp.add(tile);
        }
        return temp;
    }

    /*changes the direction of the snake(0 is up, 1 is right, 2 is down, 3 is left) */
    public void changeDirection(int direction) {
        if(Math.abs(this.direction - direction) <= 1 ||
           Math.abs(this.direction - direction) == 3)
            this.direction = direction;
    }

    //returns the direction of the snake(0 is up, 1 is right, 2 is down, 3 is left)
    public int getDirection() {
        int output = direction;
        return output;
    }

    //returns the length of the snake
    public int getLength() {
            int output = length;
            return output;
        }
    
    private int[] getHeadTile() {
        return occupiedTiles.get(occupiedTiles.size() - 1);
    }

    public boolean atApple(int[] appleLoc) {
        if (Arrays.equals(getHeadTile(), appleLoc)) {
            return true;
        }
        return false;
    }

    public void grow() {
        length++;
    }
    
    public boolean touchingSelf() {
        for(int curTile = 0; curTile < occupiedTiles.size() - 1; curTile++) {
            if(Arrays.equals(getHeadTile(), occupiedTiles.get(curTile)))
                return true;
        }
        return false;
    
    }

    public boolean touchingWall() {
        if(! isValidTile(getHeadTile()) )
            return true;
        return false;
    }
}
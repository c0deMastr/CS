public class Board {
    int rows;
    int cols;
    Snake snake;
    Clock clock;
    int[] appleRowCol;
    int score = 0;
    int highScore = 0;

    public Board (int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        // creates a new snake length "length" occupying the following 3 tiles (row, col)
        this.snake = new Snake(3, new int[][] {{11, 4}, {11, 5}, {11 , 6}});
        this.clock = new Clock();
        this.appleRowCol = new int[] {11 , 19};
    }

    //returns the number of rows on the board
    public int getRows() {
        int output = rows;
        return output;
    }
    //returns the number of columns on the board
    public int getCols() {
        int output = cols;
        return output;
    }
    /* returns the state of the board as a string, # being tiles the snake is occupying, A being the apple, and C being empty tiles */
    public String toString() {
        String output = "";

        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= cols; c++) {
                boolean curTileIsSnake = false;
                boolean curTileIsHead = false;
                //if the current tile(r,c) is occupied by the snake add a # to the output
                for (int [] tile : snake.getoccupiedTiles())
                    if (tile[0] == r && tile[1] == c){
                        curTileIsSnake = true;
                        //this line checks if the current tile is the last tile of the snake
                        if(tile == snake.getoccupiedTiles().get(snake.getoccupiedTiles().size() - 1))
                            curTileIsHead = true;
                    }
                
                if (curTileIsHead)
                    output += "H";
                else if (curTileIsSnake)
                    output += "#";
                //if the current tile(r,c) is occupied by the apple add an A to the output
                else if (appleRowCol[0] == r && appleRowCol[1] == c) 
                    output += "A";
                else 
                    output += "C";
            }
            output += "\n";
        }
        return output;
    }

    public int getSnakeDirection() {
        int direction = -1;
        direction  = snake.getDirection();
        return direction;
    }

    public void setDirection(int direction) {
        snake.changeDirection(direction);
    }

    public void startClock() {
        clock.start();
    }

    public void moveSnake() {
        if(clock.running()) {
            snake.move();

            if(snake.atApple (appleRowCol)){
                snake.grow();
                score++;
                //all next lines reasign apple to tile that snake is not occupying
                int ranRow = (int) (Math.random() * 21.0);
                int ranCol = (int) (Math.random() * 24.0);

                while (tileInSnake(ranRow, ranCol) ) {
                    ranRow = (int) (Math.random() * 21.0);
                    ranCol = (int) (Math.random() * 24.0);
                }
                appleRowCol[0] = ranRow + 1;
                appleRowCol[1] = ranCol + 1;
            }
        }
    }
    //helper function that compares if a tile (inputed as row,col) is in the snake
    public boolean tileInSnake(int row, int col) {
        for (int[] tile : snake.getoccupiedTiles())
            if (tile[0] == row && tile[1] == col)
                return true;
        return false;
    }

    public boolean isGameOver(){
        if( snake.touchingSelf() || snake.touchingWall())
            //this way we will only end the clock once
            if(clock.running())
                clock.end();
            //this way gameOver doesnt return true at start
            if(clock.getCurTime() != 0 )
            return ! clock.running();
        else return false;
    }

    public int getScore () {
        int output = 0;
        output = score;
        return output;
    }


}


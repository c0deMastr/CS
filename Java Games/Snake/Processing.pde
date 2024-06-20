Board board;
PImage[] fruits;
PImage[] headDirections;
PImage lostScreen;
int frame = 0;
boolean frameBuffer = false;

int highScore = 0;
//runs once at the start of the program
void setup() {
    size (720, 630);
    strokeWeight(0);
    board = new Board(21, 24);

    fruits = new PImage[]{
        loadImage("Apple.png"), 
        loadImage("Banana.png"), 
        loadImage("Grapes.png"),
        loadImage("Watermelon.png")
    };
    headDirections = new PImage[]{
        loadImage("Up.png"),
        loadImage("Right.png"),
        loadImage("Down.png"),
        loadImage("Left.png")
    };

    lostScreen = loadImage("losingScreen.png");

    drawBackground();
}

//runs 60 times per second
void draw() {
    frame++;
    if(board.isGameOver()){
        gameOverAnimation();
        return;
    }
    if(frame % 8 == 0) {
        frameBuffer = false;
        drawBackground();
        board.moveSnake();
    }
     //parses the boards toString 
    String boardState = board.toString();
    int curRow = 1;
    int curCol = 1;
    for (int i = 0; i < boardState.length(); i++) {
        char curChar = boardState.charAt(i);
        int x = (curCol - 1) * 30;
        int y = (curRow - 1) * 30;

        if (curChar == '#')
            displaySnake(x, y);
        if(curChar == 'H')
            displayHead (x, y, board.getSnakeDirection());
        if (curChar == 'A')
            displayApple(x, y);

        if (curChar == '\n'){ //update col and row to new line
            curRow++;
            curCol = 1;
        } else curCol++;
    }
}
/* draws all the alternating green boxes */
void drawBackground() {
    for (int r = 0; r < board.getRows(); r++) 
        for (int c = 0; c < board.getCols(); c++) {

            if(r % 2 == c % 2)
                fill(#aad751);
            else fill(#a2d149);
            int x = c;
            int y = r;
            rect(x * 30, y * 30, 30, 30);
        }
}

/* draws an apple on the screen at the specified (x,y) position */
void displayApple (int x, int y) {
    image(fruits[0], x, y );
}
/*draws the head of the snake facing the snake direction (0 is up, 1 is right, 2 is down, 3 is left) */
void displayHead (int x, int y, int snakeDirection) {
    displaySnake(x , y );
    image(headDirections[snakeDirection], x, y);
}
/*draws a body square for the snake at the (x,y) position */
void displaySnake (int x, int y) {
    fill(#507cf4);
    rect(x ,y ,30,30);
}

void keyPressed() {
    if (frameBuffer) 
        return;

    board.startClock();

    if(board.isGameOver()) //dont move the snake
        return;

    if (keyCode == UP || key == 'W') 
        board.setDirection(0);
    
    if (keyCode == RIGHT || key == 'D') 
        board.setDirection(1);
    
    if (keyCode == DOWN || key == 'S') 
        board.setDirection(2);

    if (keyCode == LEFT || key == 'A')
        board.setDirection(3);
    
    frameBuffer = true;
    
}

boolean once = true;
/* dims the screen once and draws the end game image */
void gameOverAnimation() {
    fill(0, 75);
    if(once){
        rect(0,0,720,630);
        once = false;
    }
    image(lostScreen, width/2 - 277/2,height / 2 -200);
    textSize(30);
    fill(0);
    if(board.getScore() > highScore)
        highScore = board.getScore();
    text(board.getScore() + "", 295, 280); // prints score
    text(highScore + "", 410, 280); // prints High score

}

/* handles every scenerio the mouse could do something */
void mouseClicked () {
    //if 
    if (board.isGameOver()) 
        if(withinRestartButton (mouseX, mouseY )) {
            board = new Board(21, 24);
            once = true;
        }
           
}

/* returns true if the mouses x,y is within the restart button*/
boolean withinRestartButton(int mouseX, int mouseY) {
                             // Button appears on:
    if (mouseX > 8*30               // column 8
        && mouseX < 16*30           // to column 16
        && mouseY > 16*30           //row 16
        && mouseY < 17*30 )         // to row 17
        return true;
    else return false;
}

Minesweeper game;
//Confetti confetti;

PImage clockIcon;
PImage backgroundGrid;
PImage flag;
PImage wonImage;
PImage [] mines;
boolean minesChosen;
double lastReveal;
int fastestTime;

/*draws the inital screen, and instantiates the global variables including images and the minesweeper game.*/
void setup(){

  clockIcon = loadImage("Clock.png");
  backgroundGrid = loadImage("BGIMG.png");
  flag = loadImage("Flag.png");
  wonImage = loadImage("Win.png");
  
  mines = new PImage[]{
    loadImage("Pink.png"), 
    loadImage("Blue.png"), 
    loadImage("Orange.png"),
    loadImage("Purple.png"),
    loadImage("Teal.png"),
    loadImage("Green.png"),
    loadImage("Red.png"),
    loadImage("Yellow.png"),
    loadImage("Pink.png"), 
    loadImage("Blue.png")
  };
  lastReveal = 0.0;
  fastestTime = 0;
  minesChosen = false;

  size(600,550);
  fill(#447631);
  rect(0,500,600,50);
  textSize(30);
  image(backgroundGrid, 0, 0);

}

/*
* runs 60 times a second 
* parces the string version of the current state of the board and displays each tile based on its character.
*checks if the game has been won or lost, if so draws the end screen with option to restart. 
*/
void draw(){
  if(!minesChosen)
    return;
  image(backgroundGrid, 0, 0);
  displayBanner();
  String boardState = game.toString();
  int curCol = 0;
  int curRow = 0;

  //parses the minesweepers toString
  for (int i = 0; i < boardState.length(); i++) {
    char curChar = boardState.charAt(i);
    int x = curCol;
    int y = curRow;
    if (curChar != '\n'){
    
      if (curChar == 'm')
        displayMine(x, y);
      if (curChar == 'f')
        displayFlag(x, y);
      if ("012345678".indexOf(curChar) != -1) 
        displayValue(Character.getNumericValue(curChar), x, y);
    curCol++;
    } 
    else {
      //we have a \n so go to next line 
      curRow++;
      curCol = 0;
    }
  }
  if (game.isGameLost()) {
      displayGameLost();
    if ( (System.currentTimeMillis() - lastReveal) > 500.0) {
      game.revealNextMine ();
      lastReveal = System.currentTimeMillis();
    }
  }

  if(game.isGameWon())
    displayGameWon();
}

/*if the game is lost or won, restarts the game if user clicks on the button.
* if they click the board either flag or open the square that was clicked (1 vs 2 tap respectively)
*/
void mouseClicked() {
  int clickedCellRow = (mouseY / 25);
  int clickedCellCol = (mouseX / 25);
  if(game.isGameLost() || game.isGameWon())
    if ((mouseX > 150 && mouseX < 450) && (mouseY > 363 && mouseY < 425)){
      game = new Minesweeper(20, 24, 40);
      return;
    }
  if (clickedCellCol <= 24 && clickedCellRow <= 20){
    if (mouseButton == RIGHT)
      game.flag (clickedCellRow, clickedCellCol);
    else
      game.open (clickedCellRow, clickedCellCol);
  }
}
/* a method to call sweep on the cell that was hovered over if ENTER was pressed */
void keyPressed() {
  if (key == ENTER){
    int row = (mouseY / 25);
    int col = (mouseX / 25);
    game.sweep (row, col);
  }
}
/*
* displays the value of a cell @ x,y with whatever value is passed in. 
* also displays the sqaure behind the value
*/
void displayValue (int val, int x, int y) {
  displaySquare(x, y);
  
  fill(#000000);
  if (val != 0)
    text(""+val,(x * 25)  + 6, (y * 25) + 22);
}
/* displays a flag @x,y */
void displayFlag (int x, int y ){
  image(flag, x * 25, y * 25);
}
/* displays the bacground sqaure @x,y
* tan or dark tan is based of an alternating pattern
*/
void displaySquare (int x, int y) {
  fill(#d8bca4); 
  if (x % 2 == y % 2)
    fill(#e8c4a4);
  
  strokeWeight(0);
  rect(x * 25 , y * 25, 25, 25);
}
/* displays a mine @x,y 
* gives each mine a random color based of its x and y values.
*/
void displayMine (int x, int y) {
  int key = x * 503 + y * 509;
  key %= 10;
  
  image(mines[key], x * 25, y * 25);
  //explosion = new confetti();
  //explosion.explode(key);
}
/* displays the banner at the bottm of the screen, including icons and time/ flags left.*/
void displayBanner () {
  fill (#447631);
  rect (0,500,600,50);

  image (clockIcon, 350, 510);
  fill (#ffffff);
  textSize(28);
  text (""+game.getCurTime(), 390, 535);

  image(flag, 200, 512.5);
  text (""+game.getFlagsLeft(), 230, 535);
  
}
/* displays the end screen if the game is lost */
void displayGameLost () {
  tint(255, 230);
  image(wonImage,150,125);
  tint(255,255);
}
/* displays the end screen if the game is won */
void displayGameWon () {
  image(wonImage,150,125);

  int time = game.getCurTime();
  text(""+time, 270, 250);

  if(time > fastestTime)
    text(""+time, 300, 250);
  else 
    text(""+fastestTime, 300, 250);
}

class confetti {
  int numShreds;
 
  int xPos;
  int yPos;
  int xVel;
  int yVel;
  int gVel;
  
}

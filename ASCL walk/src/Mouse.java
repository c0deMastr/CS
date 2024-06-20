import java.util.ArrayList;
//A class for each of the test cases involving a starting and ending position in the predetermined array. 
public class Mouse {

  int curRow;
  int curColumn;
  String curDirection;
  int moveQuant;
  int[][] angles;

  /** CONSTRUCTOR
  *
  *
  *
  */
  public Mouse(String input, Board board){
    curRow = Integer.valueOf(input.substring(0,input.indexOf(",")));
    input = input.substring(input.indexOf(",") + 2);
    curColumn = Integer.valueOf(input.substring(0,input.indexOf(",")));
    input = input.substring(input.indexOf(",") + 2);
    curDirection = input.substring(0,input.indexOf(","));
    input = input.substring(input.indexOf(",") + 2);
    moveQuant = Integer.valueOf(input);
  
    angles = new int[8][8];
    for(int row = 0; row < angles.length; row++) {
        for(int col = 0; col < angles[row].length; col++) {
            if(board.getGrid()[row][col] == 1)
              angles[row][col] = 90;
            else angles[row][col] = 0;
        }
    }
  }
  /*
  * Gets the final position of the mouse based on its variables
  * excutes moveQuant amount of moves.
  */
  public void getFinalPos(Board board){
    for(int i = 0; i < moveQuant; i++){
      move(board);
    }
    System.out.println(curRow + ", " + curColumn);
  }

  /*
  * first updates current Row and current Column based on direction. IE right would move column + 1.
  * next checks for out of bounds
  *finally recalculates direction based on sqaure it moved too.
  */
  public void move(Board board){
    curDirection = changeDirection(board, curDirection);
    //sysytem prints are for test cases
    if(curDirection.equals("A")){
      curRow++;
    }
    if(curDirection.equals("B")){
      curRow--;
    }
    if(curDirection.equals("L")){
      curColumn++;
    }
    if(curDirection.equals("R")){
      curColumn--;
    }
    //update direction
    if(true){
      if(curColumn == 9)
        curColumn = 1;
      if(curColumn == 0)
        curColumn = 8;
      if(curRow == 9)
        curRow = 1;
      if(curRow == 0)
        curRow = 8;
    }
    
  }
  /*
  * sets the prev cardinal barring based on curDirection
  * if sqaure is 1 adds squares angle to cardinal Bearing.
  * @return directions[cardinalBarring] - converts bearing to letter for move function. 
  */
  public String changeDirection(Board board, String prevDirection){
    int curBarring = 0;
    curBarring = "BRAL".indexOf(prevDirection);
    curBarring *= 90;
    if(board.getGrid()[curRow-1][curColumn-1] == 0){
      return prevDirection;
    }else{
      curBarring += angles[curRow - 1][curColumn - 1];
      angles[curRow-1][curColumn-1] -= 90;
    }
    curBarring = curBarring % 360;
    curBarring = curBarring / 90;
    String[] directions = {"B", "R", "A" , "L"};
    return directions[curBarring];
  }

  /*
  * a method to make sure setting the instance variables works.
  */
  public void displayVars(){
    
    System.out.println(Integer.toString(curRow));
    System.out.println(Integer.toString(curColumn));
    System.out.println(curDirection);
    System.out.println(Integer.toString(moveQuant));
    
    for(int[] row : angles){
      for(int i : row){
        System.out.print(i + " ");
      }
      System.out.println("");
    }
  }
}
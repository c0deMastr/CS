import java.util.Scanner;
//Sample input:
  //C, C7, 85, D6, 46, D7, E6, 87
  //2, 3, L, 2 --> 2, 5
  //2, 7, B, 8 --> 2, 5
  //4, 5, R, 3 --> 6, 4
  //6, 7, A, 5 --> 3, 7
  //8, 8, L, 7 --> 6, 1
//Test input:
  //B2, F, F3, A1, 4E, 3, 78, BC
  //1, 4, L, 5 --> 1, 5
  //6, 5, B, 9 --> 5, 3
  //4, 3, A, 7 --> 3, 7
  //8, 8, R, 12 --> 3, 5
  //1, 1, A, 20 --> 8, 6

public class App {
  public static void main(String[] args) throws Exception {
    Scanner scan = new Scanner(System.in);
    Converter converter = new Converter();
    Board board = new Board();
    String userInput = scan.nextLine();
    board.fill(userInput, converter);
    //board.display();
    for(int i 0; i < 5; i++){
      userInput = scan.nextLine();
      Mouse currentMouse = new Mouse(userInput, board);
      currentMouse.getFinalPos(board);
    }
  }
}


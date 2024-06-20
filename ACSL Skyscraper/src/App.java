import java.util.Scanner;
/*
 * 1. 3221, 41, 22, 14, 231422, 2313
 * 2. 14 -> 4   
 * 3. 34 -> 1
 * 4. 23 -> 1
 * 5. 32 -> 3
 * 6. 21 -> 2
 */
/*
 * 2124, 23, 13, 221432, 41, 3321
 * 1. 11 -> 3
 * 2. 23 -> 1
 * 3. 42 -> 2
 * 4. 24 -> 2
 * 5. 14 -> 1
*/
public class App {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        Board board = new Board(scan.nextLine());
        for(int input = 0; input < 5; input ++){
            System.out.println(board.getItem(scan.nextLine()));
        }
        scan.close();
        
    }
public App ()
}

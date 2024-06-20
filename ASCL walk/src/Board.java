public class Board {
    int[][] grid;
  
    //Constructor
    public Board(){
     grid = new int[8][8];
    }
  
    public void fill(String fullHexadecInput, Converter converter){
      for(int i = 0; i < 8; i++){
        //parses input to get current hexdecimal string
        String curHexaDec = "";
        if(fullHexadecInput.indexOf(",") > -1){
          curHexaDec = fullHexadecInput.substring(0, fullHexadecInput.indexOf(","));
          fullHexadecInput = fullHexadecInput.substring(fullHexadecInput.indexOf(",") + 2);
        }else curHexaDec = fullHexadecInput;
        //fills the board
        int[] curRow = converter.getBinary(curHexaDec);
        int z = 0;
        for(int item : curRow){
          grid[i][z] = item;
          z++;
        }
      }
    }
    public int[][] getGrid(){
      return grid;
    }
    
    public void display(){
      for (int[] row : grid) {
          for (int element : row) {
              System.out.print(element + " ");
          }
          System.out.println();
      }
    }
    
  }
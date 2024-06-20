// a class to convert from hexadecimal to binary
public class Converter{

    //constructor
    public Converter(){
      
    }
    /* A helper method to convert from hexadecimal to integer
    *@param hexadecimal -
    *@return intValue - 
    */
    public int getInt(String hexadecimal){
      int output = 0;
      int curMultiple = 0;
      for(int i = hexadecimal.length() - 1; i >= 0; i --){
        char curChar = hexadecimal.charAt(i);
        int decimalValue = "0123456789ABCDEF".indexOf(curChar);
        output += decimalValue * Math.pow(16, curMultiple);
        curMultiple ++;
      }
      return output;
    }
  
  /* A Method to convert from hexadecimal to correspond binary in an int array
  *@param hexadecimal - 
  *@return int[] output - 
  */
    public int[] getBinary(String hexadecimal){
      int[] output = new int[8];
      int intValue = getInt(hexadecimal);
      int curSubtractor = 128;
  
      for(int i = 0 ; i < 8 ; i++){
        if(intValue - curSubtractor >= 0 ){
          output[i] = 1;
          intValue -= curSubtractor;
      }
      else output[i] = 0;
      curSubtractor /= 2;
    }
    return output;  
    }
  
    //Prints the current grid, (debugging method)
   
  
    
  }
  
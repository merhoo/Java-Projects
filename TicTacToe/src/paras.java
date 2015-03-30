import java.util.*;

public class paras {
    public static boolean Move(char[][]board,int row, int column,char pieces[],int turn,int movesmade) {
        if (board[row][column] == '_'){
          board[row][column] = pieces[turn];
          movesmade++;
          return true;
        }
        else{
            return false;
        }
      }
    public static boolean inbounds(int row) {
        if(row>0 && row<4){
            return true;
        }
        return false;
      }
    public static int changeturn(int turn) {
          turn = (turn + 1)%2;
          return turn;
        }
    public static String toString(char[][]board, int type) {
        StringBuilder build = new StringBuilder();
        if(type == 1){
            build.append("\n");
        }
        build.append("-------------\n");
        for (int i = 0; i < board.length; i++) {
            build.append("|");
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '_') {
                    build.append("   ");
                } else if (board[i][j] == 'X'){
                    build.append(" X ");
                } else if (board[i][j] == 'O') {
                    build.append(" O ");
                }
                if (j == board[i].length - 1) {
                    build.append("|\n");
                    build.append("-------------");
                } else {
                    build.append("|");
                }
            }build.append("\n");
        }
        return build.toString();
      }
    private static boolean SameArray(char[] word) {
        char check = word[0];
        for (int i=1; i<word.length; i++)
          if (check != word[i])
            return false;
        return true;
      }
    public static char ifWon(char[][]board, int movesmade) {
        //System.out.println("THE LOOP GOT HERE");
        for (int i=0; i<3; i++){
          if (SameArray(board[i]) && board[i][0] != '_'){
            //System.out.println("THE LOOP GOT HERE12");
             // System.out.print(board[i][0]);  
            return board[i][0];
          }
        }
        for (int i=0; i<3; i++){
          if ((board[0][i] == board[1][i]) && (board[1][i] == board[2][i]) && board[0][i] != '_'){
              //System.out.println("THE LOOP GOT HERE1");
             //System.out.print(board[0][i]);
             return board[0][i];
          }
        }
        if ((board[0][0] == board[1][1]) && (board[1][1] == board[2][2])){
            //System.out.println("THE LOOP GOT HERE2");
            //System.out.print(board[0][0]);
             return board[0][0];
        }
        if ((board[2][0] == board[1][1]) && (board[1][1] == board[0][2])){
            //System.out.println("THE LOOP GOT HERE3");
            //System.out.print(board[2][0]);
            return board[2][0];
        }
        if (movesmade == 9){
          return 'T';
        }
        return '_';

      }
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        char[][] board;
        int rowX = 0,colX =0,rowO =0,colO = 0;
        int turn,numRows = 3,movesmade;
        char[] pieces = {'X','O'};
        board = new char[numRows][numRows];
        for (int i=0;i < 3; i++){
            for (int j=0; j < 3; j++){
              board[i][j] = '_';
            }
        }
        turn = 0;
        movesmade = 0; 
        char won = ifWon(board,movesmade);
        System.out.print(toString(board,0));
        while(won == '_'){
            
            System.out.print("Enter a row (1.."+numRows+") for player X: ");
            String r = input.nextLine();
            try{
                rowX = Integer.parseInt(r);
            }catch(NumberFormatException nfe){
                System.out.print("Error: Invalid row 'x' received.");
            }
            rowX = rowX-1;
            System.out.print("Enter a column (1.."+numRows+") for player X: ");
            String col = input.nextLine();
            try{
                colX = Integer.parseInt(col);
            }catch(NumberFormatException nfe){
                System.out.print("Error: Invalid column 'x' received.");
            }
            colX = colX -1;
            board[rowX][colX] = pieces[turn];
            movesmade++;
            turn = changeturn(turn);
            won = ifWon(board,movesmade);
            if(won == 'X'){
                System.out.print(toString(board,1));
                System.out.print("Player X wins.");
                System.exit(0);
            }else if(won == 'T'){
                System.out.print(toString(board,1));
                System.out.print("Tie.");
                System.exit(0);
            }
            System.out.print(toString(board,1));
            System.out.print("Enter a row (1.."+numRows+") for player O: ");
            String row = input.nextLine();
            try{
                rowO = Integer.parseInt(row);
            }catch(NumberFormatException nfe){
                System.out.print("Error: Invalid row 'x' received.");
            }
            rowO = rowO-1;
            System.out.print("Enter a column (1.."+numRows+") for player O: ");
            String co = input.nextLine();
            try{
                colO = Integer.parseInt(co);
            }catch(NumberFormatException nfe){
                System.out.print("Error: Invalid column 'x' received.");
            }
            colO = colO-1;
            board[rowO][colO] = pieces[turn];
            movesmade++;
            turn = changeturn(turn);        
            won = ifWon(board,movesmade);
            if(won == 'O'){
                System.out.print(toString(board,1));
                System.out.print("Player O wins.");
                System.exit(0);
            }
            System.out.print(toString(board,1)); 
        }
        input.close();
    }
}
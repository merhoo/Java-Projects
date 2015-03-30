import java.util.*;

public class TicTacToe {
    int rowX,colX,rowO,colO;
    char pieces[] = {'X','O'};
    int turn;
    int movesmade;
    char board[][];
    public TicTacToe(char board[][],int rowX, int colX, int rowO,int colO){
        this.board = board;
        this.rowX = rowX;
        this.colX = colX;
        this.rowO = rowO;
        this.colO = colO;
    }
    public boolean move(int row,int column) {
        if (board[row][column] == '_') {
          board[row][column] = pieces[turn];
          movesmade++;
          return true;
        } else {
            return false;
        }
    }
    public static boolean inBounds(int row) {
        if (row > 0 && row < 4) {
            return true;
        }
        return false;
      }
    public int changeTurn(int turn) {
          turn = (turn + 1)%2;
          return turn;
        }
    public String toString(char[][] board,int type) {
        StringBuilder build = new StringBuilder();
        if (type == 1){
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
            if (check != word[i]) {
                return false;
            }
        return true;
        
      }
    
    public char ifWon(char[][]board,int movesmade) {
        for (int i=0; i<3; i++) {
          if (SameArray(board[i]) && board[i][0] != '_') {
            return board[i][0];
          }
        }
        for (int i=0; i<3; i++){
          if ((board[0][i] == board[1][i]) && (board[1][i] == board[2][i]) && board[0][i] != '_'){
             return board[0][i];
          }
        }
        if ((board[0][0] == board[1][1]) && (board[1][1] == board[2][2])){
             return board[0][0];
        }
        if ((board[2][0] == board[1][1]) && (board[1][1] == board[0][2])){
            return board[2][0];
        }
        if (movesmade == 9){
          return 'T';
        }
        return '_';

      }
    public boolean ifFree(int row, int col, char[][]board){
        if (board[row][col] == '_') {
            return true;
        } else {
            return false;
        }
    }
    public static void main(String[] args){
        char[][] board;
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
              board[i][j] = '_';
            }
        }
        int rowX = 0,colX =0,rowO =0,colO = 0;
        int turn,numRows = 3,movesmade;
        TicTacToe tic = new TicTacToe(board,rowX,colX,rowO,colO);
        Scanner input = new Scanner(System.in);
        turn = 0;
        movesmade = 0; 
        boolean reprompt = false;
        boolean posfix = false;
        char won = tic.ifWon(board,movesmade);
        System.out.print(tic.toString(board,0));
        while(won == '_'){  
            boolean pos = tic.ifFree(rowX,colX,board);
            start:while (true ){
                while (true) {
                    System.out.print("Enter a row (1.."+numRows+") for player X: ");
                    String r = input.nextLine();
                    try {
                        rowX = Integer.parseInt(r);
                    } catch (NumberFormatException nfe) {
                        System.out.println("Error: Invalid row '"+ r +"' received.");
                        posfix = true;
                        continue start;
                    }
                    if (inBounds(rowX) == true) {
                        break;
                    } else {
                        System.out.println("Error: Invalid row "+ r +" received.");
                    }
                }
                rowX = rowX-1;
                while (true) {
                    System.out.print("Enter a column (1.."+numRows+") for player X: ");
                    String col = input.nextLine();
                    try {
                        colX = Integer.parseInt(col);
                    } catch (NumberFormatException nfe) {
                        System.out.println("Error: Invalid column '" + col +"' received.");
                        posfix = true;
                    }
                    if (inBounds(colX) == true) {
                        break;
                    } else {
                        System.out.println("Error: Invalid column "+ col +" received.");
                        reprompt = true;
                        break;
                    }
                }
                if (reprompt == true) {
                    reprompt = false;
                    continue start;
                }
                colX = colX -1;
                pos = tic.ifFree(rowX,colX,board);
                if (pos == true) {
                    board[rowX][colX] = tic.pieces[turn];
                    break;
                } else {
                    if (posfix == true) {
                    } else {
                        System.out.println("Error: Position already taken.");
                    }
                }
            }
            movesmade++;
            turn = tic.changeTurn(turn);
            won = tic.ifWon(board,movesmade);
            if (won == 'X') {
                System.out.print(tic.toString(board,1));
                System.out.print("Player X wins.");
                System.exit(0);
            } else if (won == 'T') {
                System.out.print(tic.toString(board,1));
                System.out.print("Tie.");
                System.exit(0);
            }
            System.out.print(tic.toString(board,1));
            while (true) {
                while (true) {
                    System.out.print("Enter a row (1.."+numRows+") for player O: ");
                    String row = input.nextLine();
                    try {
                        rowO = Integer.parseInt(row);
                    } catch (NumberFormatException nfe) {
                        System.out.println("Error: Invalid row '"+ row +"' received.");
                    }
                    if (inBounds(rowO) == true) {
                        break;
                    } else {
                        System.out.println("Error: Invalid row "+ row +" received.");
                    }
                }
                rowO = rowO-1;
                while (true) {
                    System.out.print("Enter a column (1.."+numRows+") for player O: ");
                    String co = input.nextLine();
                    try {
                        colO = Integer.parseInt(co);
                    } catch (NumberFormatException nfe) {
                        System.out.println("Error: Invalid column '"+ co +"' received.");
                    }
                    if (inBounds(colO) == true) {
                        break;
                    } else {
                        System.out.println("Error: Invalid column "+ co +" received.");
                    }
                }
                colO = colO-1;
                pos = tic.ifFree(rowO,colO,board);
                if (pos == true) {
                    board[rowO][colO] = tic.pieces[turn];
                    break;
                } else {
                    System.out.print("Error: Position already taken.");
                    continue;
                }
            }
            movesmade++;
            turn = tic.changeTurn(turn);        
            won = tic.ifWon(board,movesmade);
            if (won == 'O'){
                System.out.print(tic.toString(board,1));
                System.out.print("Player O wins.");
                System.exit(0);
            } else if(won == 'T') {
                System.out.print(tic.toString(board,1));
                System.out.print("Tie.");
                System.exit(0);
            }
            System.out.print(tic.toString(board,1)); 
        }
        input.close();
    }
}
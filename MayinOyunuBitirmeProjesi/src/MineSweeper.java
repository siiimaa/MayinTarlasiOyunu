import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    int rowNumber, colNumber, size, mineCount, counter;
    String [][] map;
    String [][] gameBoard;
    boolean isWin = false;
    boolean game = true;
    Random rand = new Random();
    Scanner inp = new Scanner(System.in);

    MineSweeper(){
        System.out.print("Enter row count : ");
        int rowNumber = inp.nextInt();
        System.out.print("Enter column count : ");
        int colNumber = inp.nextInt();
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        this.map = new String[rowNumber][colNumber];
        this.gameBoard = new String[rowNumber][colNumber];
        this.size = rowNumber * colNumber;
        this.mineCount = this.size / 4;
        fill(this.map);
        fill(this.gameBoard);
    }

    public void gameStart(){
        printGameBoard();
        System.out.println("-----------------------\nGAME START\n-----------------------");
        enterCoordinators();
        printMap(map);
    }

    public void enterCoordinators(){
        while(this.game){
            boolean check = true;
            int row = 0, col = 0, around = 0;
            counter++;
            while(check) {
                System.out.print("Enter row number : ");
                row = inp.nextInt()-1;
                System.out.print("Enter col number : ");
                col = inp.nextInt()-1;

                if ((row < 0) || (row > rowNumber - 1) || (col < 0) || (col > colNumber - 1)){
                    System.out.println("Wrong . Try again.");
                    check = true;
                }else {
                    check = false;
                }
            }
            if(this.map[row][col].equals("*")) {
                System.out.println("YOU LOSE");
                printMap(map);
                this.game = false;
                break;
            }
            for(int i = -1; i < 2; i++){
                for(int j = -1 ; j < 2; j++){
                    if(((row + i) >= 0 && (row + i) <= (this.rowNumber - 1)) && ((col + j) >= 0 && (col + j) <= (this.colNumber - 1))){
                        if(this.map[row + i][col + j].equals("*")){
                            around++;
                            this.gameBoard[row][col] = String.valueOf(around);
                        }
                        if(around == 0){
                            this.gameBoard[row][col] = "0";
                        }
                    }
                }
            }
            if(this.counter == this.size - this.mineCount){
                this.isWin = true;
            }
            if(this.isWin == true){
                this.game = false;
                System.out.println("YOU WIN!");
            }else this.game = true;
            printGameBoard();
            System.out.println();
        }
    }
    public void placingMines(){
        int mineCount = 0;
        int rowRandom, colRandom;

        while(mineCount != this.mineCount){
            rowRandom = rand.nextInt(this.rowNumber);
            colRandom = rand.nextInt(this.colNumber);
            if (this.map[rowRandom][colRandom] != "*"){
                this.map[rowRandom][colRandom] = "*";
                mineCount++;
            }
        }
    }

    public void printMap(String[][] array){
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[0].length; j++){
                System.out.print(" " + this.map[i][j]);
            }
            System.out.println();
        }
        System.out.println("----------------");
    }

    public String[][] fill(String[][] board) {
        placingMines();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = "-";
            }
        }
        return board;
    }

    public void printGameBoard(){
        for(int i = 0; i < this.rowNumber; i++){
            for(int j = 0; j < this.colNumber; j++){
                System.out.print(" " + this.gameBoard[i][j]);
            }
            System.out.println();
        }
    }
}
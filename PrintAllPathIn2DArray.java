/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printallpathin2darray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PrintAllPathIn2DArray {

    static int rowCount;
    static int colCount;
    static double[][] arrA; //
    
    static double  coinNum = 0;
    static double  coinVal = 0;
    private static double[] coins = {0.0, 0.5, 1.0, 2.0, 5.0};
    private static int tablerow = 3;
    private static int tablecolumn =tablerow;
    static double[][] maxCoin = new double[tablerow][tablecolumn]; // 
    static double[][] ValueCoin = new double[tablerow][tablecolumn]; // 
    private static double[][] CoinBoard = new double[tablerow][tablecolumn]; // 
    public  PrintAllPathIn2DArray(double arrA[][]) {
        this.arrA = arrA;
        rowCount = arrA.length;
        colCount = arrA[0].length;
    }

    public static void PrintBoard(double array[][]){
        for(int r = 0; r < tablerow; r++){
            for(int c = 0; c < tablecolumn; c++){
                System.out.print("(" + array[r][c] + ") ");
            }
            System.out.println();
        }
        System.out.println();
    }
public static void CreateRandomBoard(){

        maxCoin = new double[tablerow][tablecolumn];
        Random rand = new Random();
        int tempNumber = 0;
        for(int r = 0; r < tablerow; r++){
            for(int c = 0; c < tablecolumn; c++){
                CoinBoard[r][c] = 0;
                if ((r == 0 && c == 0)){//||(r==tablerow-1 && c ==tablecolumn-1)){
                    CoinBoard[r][c] = coins[0];
                    continue;
                }
                tempNumber = rand.nextInt(6);
                if(tempNumber ==0){
                    CoinBoard[r][c] = coins[1];
                }
                else if(tempNumber ==1){
                    CoinBoard[r][c] = coins[2];
                }
                else if(tempNumber ==2){
                    CoinBoard[r][c] = coins[3];
                }
                else if(tempNumber ==3){
                    CoinBoard[r][c] = coins[4];
                }
                else{
                    CoinBoard[r][c] = coins[0];
                }
                  
            }
        }
    }
public static void traceBack(){

        ArrayList<String> trace = new ArrayList<>();
        int row = rowCount-1;
        int col = colCount -1;
        trace.add ("Row: " + (row) +" Col: " + (col) + "\n");
        while (row > 0 & col >0){
            // if up is greater than left pick that one and row-1
            //else col - 1
            if(maxCoin[row-1][col]> maxCoin[row][col-1]){
                
                row = row-1;
            }
            else{
                col = col-1;
            }
            trace.add ("Row: " + (row) +" Col: " + (col) + "\n");
        }
        while (row >= 0 && col >=0){
            if (row == 0 && col != 0){
                col = col-1;
            }
            else{
                if (row==0){
                    break;
                }
                else{
                    row = row -1;
                }
            }
            trace.add ("Row: " + (row) +" Col: " + (col) + "\n");
        }

        Collections.reverse(trace);
        System.out.println(trace.toString());
        
    }
    public static double printAll(int currentRow, int currentColumn, double coin) {
        if (currentRow == rowCount - 1) {
            for (int i = currentColumn; i < colCount; i++) {
                // go down only
                if (arrA[currentRow][i] != 0) {
                    coin++;
                    if (coinNum < coin) {
                        coinNum = coin;
                    }
                    // if the current Coin is greater than the current value of Maxarray[currentRow][i][then save it
                    if(coin > maxCoin[currentRow][i]|| maxCoin[currentRow][i]==0){
                        maxCoin[currentRow][i] = coin;
                    }
                }
                else{
                                        if (coinNum < coin) {
                        coinNum = coin;
                    }
                    // if the current Coin is greater than the current value of Maxarray[currentRow][i][then save it
                    if(coin > maxCoin[currentRow][i]|| maxCoin[currentRow][i]==0){
                        maxCoin[currentRow][i] = coin;
                    }
                }
            }

            return maxCoin[rowCount-1][colCount-1];
        }
        if (currentColumn == colCount - 1) {
            for (int i = currentRow; i <= rowCount - 1; i++) {
                // go right only
                if (arrA[i][currentColumn] != 0) {
                    coin++;
                    if (coinNum < coin) {
                        coinNum = coin;
                    }
                    if(coin > maxCoin[i][currentColumn]|| maxCoin[i][currentColumn]==0){
                        maxCoin[i][currentColumn] = coin;
                    }
                }
                else{
                    if (coinNum < coin) {
                        coinNum = coin;
                    }
                    if(coin > maxCoin[i][currentColumn]|| maxCoin[i][currentColumn]==0){
                        maxCoin[i][currentColumn] = coin;
                    }
                }
            }
            
            
            return maxCoin[rowCount-1][colCount-1];
        }
        // do middle code that adds the coin
        if (arrA[currentRow][currentColumn] != 0) {
            coin++;
            if (coinNum < coin) {
                coinNum = coin;
            }
            if(coin > maxCoin[currentRow][currentColumn]|| maxCoin[currentRow][currentColumn]==0){
                maxCoin[currentRow][currentColumn] = coin;
            }
        }
        printAll(currentRow + 1, currentColumn, coin);
        printAll(currentRow, currentColumn + 1, coin);
        //	printAll(currentRow + 1, currentColumn + 1, path);
       return maxCoin[rowCount-1][colCount-1];
    }
     public static double printMaxValue(int currentRow, int currentColumn, double coin) {
        if (currentRow == rowCount - 1) {
            for (int i = currentColumn; i < colCount; i++) {
                // go down only
                    coin = coin + CoinBoard[currentRow][i];

                    // if the current Coin is greater than the current value of Maxarray[currentRow][i][then save it
                    if(coin > ValueCoin[currentRow][i]|| ValueCoin[currentRow][i]==0){
                        ValueCoin[currentRow][i] = coin;
                    }                
            }

            return ValueCoin[rowCount-1][colCount-1];
        }
        if (currentColumn == colCount - 1) {
            for (int i = currentRow; i <= rowCount - 1; i++) {
                // go right only
                    coin = coin + CoinBoard[i][currentColumn];

                    // if the current Coin is greater than the current value of Maxarray[currentRow][i][then save it
                    if(coin > ValueCoin[i][currentColumn]|| ValueCoin[i][currentColumn]==0){
                        ValueCoin[i][currentColumn] = coin;
                    }     
            }
            
            
            return ValueCoin[rowCount-1][colCount-1];
        }
        // do middle code that adds the coin
         coin = coin + CoinBoard[currentRow][currentColumn];

                    // if the current Coin is greater than the current value of Maxarray[currentRow][i][then save it
                    if(coin > ValueCoin[currentRow][currentColumn]|| ValueCoin[currentRow][currentColumn]==0){
                        ValueCoin[currentRow][currentColumn] = coin;
                    }     
        printMaxValue(currentRow + 1, currentColumn, coin);
        printMaxValue(currentRow, currentColumn + 1, coin);
        //	printAll(currentRow + 1, currentColumn + 1, path);
        return ValueCoin[rowCount-1][colCount-1];
    }

    public static void main(String args[]) {
        CreateRandomBoard();
        System.out.println("Original");
        PrintBoard(CoinBoard);
        PrintAllPathIn2DArray p = new PrintAllPathIn2DArray(CoinBoard);
        System.out.println("maxCoin");
        long startTime = System.nanoTime();
        double maxCoinReturn = printAll(0, 0, 0);
        long endTime = System.nanoTime();

        // time stop
        PrintBoard(maxCoin);
        System.out.println(maxCoinReturn);
        double timeTaken = ((endTime - startTime)/1000000000);
        if (timeTaken == 0){
            System.out.println("Took "+ (endTime - startTime) + " NS");
        }
        else{
            System.out.println("Took "+ timeTaken + " seconds"); 
        }
        System.out.println("Path");
        traceBack();
        
        System.out.println("CoinValue");
        // time start 
        long startTime1 = System.nanoTime();
        double maxValueReturn = printMaxValue(0,0,0);
        long endTime1 = System.nanoTime();

        // time stop
        PrintBoard(ValueCoin);
        System.out.println(maxValueReturn);
        double timeTaken1 = ((endTime1 - startTime1)/1000000000);
        if (timeTaken == 0){
            System.out.println("Took "+ (endTime1 - startTime1) + " NS");
        }
        else{
            System.out.println("Took "+ timeTaken1 + " seconds"); 
        }
        System.out.println("Path");
        traceBack();
    }

}

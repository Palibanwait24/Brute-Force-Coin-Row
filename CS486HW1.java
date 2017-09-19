package cs486hw1;

/**
 *
 * @author AmritPal
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CS486HW1 {

    /**
     * @param args the command line arguments
     */
    private static double[] coins = {0.0, 0.5, 1.0, 2.0, 5.0};
    private static int tablerow = 3;
    private static int tablecolumn = tablerow;
    private static double[][] MaxCoinBoard = new double[tablerow][tablecolumn];
    private static double[][] BruteMaxCoinBoard = new double[tablerow][tablecolumn];
    private static double[][] ValueCoinBoard = new double[tablerow][tablecolumn];
    ;
    private static double[][] CoinBoard;

    public static void main(String[] args) {
        CreateRandomBoard(); // create random board
        System.out.println("Array of Coins");
        System.out.println("------------------");
        PrintBoard(CoinBoard);

        System.out.println();
        System.out.println("Max Coin");
        System.out.println("------------------");
        long startTime = System.nanoTime();
        double numberOfCoin = ComputeMaxNumofCoin();
        long endTime = System.nanoTime();
        PrintBoard(MaxCoinBoard);
        System.out.println();
                double timeTaken = ((endTime - startTime)/1000000000);
        if (timeTaken == 0){
            System.out.println("Took "+ (endTime - startTime) + " NS");
        }
        else{
            System.out.println("Took "+ timeTaken + " seconds"); 
        }
        System.out.println("Number of coin: " + numberOfCoin);
        traceBack(MaxCoinBoard);
        System.out.println();
        System.out.println("Max Value");
        System.out.println("------------------");
        long startTime1 = System.nanoTime();
        double value = ComputeMaxValueCoin();
        long endTime1 = System.nanoTime();

        PrintBoard(ValueCoinBoard);
        System.out.println();
                double timeTaken1 = ((endTime1 - startTime1)/1000000000);
        if (timeTaken == 0){
            System.out.println("Took "+ (endTime1 - startTime1) + " NS");
        }
        else{
            System.out.println("Took "+ timeTaken1 + " seconds"); 
        }
        System.out.println("value of coin: " + value);
        traceBack(ValueCoinBoard);

    }

    public static void traceBack(double array[][]) {

        ArrayList<String> trace = new ArrayList<>(); 
        int row = tablerow - 1;
        int col = tablecolumn - 1;
        trace.add("Row: " + (row) + " Col: " + (col) + "\n");
        while (row > 0 & col > 0) {
            // if up is greater than left pick that one and row-1
            //else col - 1
            if (array[row - 1][col] > array[row][col - 1]) {

                row = row - 1;
            } else {
                col = col - 1;
            }
            trace.add("Row: " + (row) + " Col: " + (col) + "\n");
        }
        while (row >= 0 && col >= 0) {
            if (row == 0 && col != 0) {
                col = col - 1;
            } else {
                if (row == 0) {
                    break;
                } else {
                    row = row - 1;
                }
            }
            trace.add("Row: " + (row) + " Col: " + (col) + "\n");
        }

        Collections.reverse(trace);
        System.out.println(trace.toString());
    }

    public static double ComputeMaxValueCoin() { // 
        for (int col = 1; col < tablecolumn; col++) {
            ValueCoinBoard[0][col] = ValueCoinBoard[0][col - 1] + CoinBoard[0][col];
        }

        for (int row = 1; row < tablerow; row++) {
            ValueCoinBoard[row][0] = ValueCoinBoard[row - 1][0] + CoinBoard[row][0];
            for (int col = 1; col < tablecolumn; col++) {
                if (ValueCoinBoard[row - 1][col] > ValueCoinBoard[row][col - 1]) {
                    ValueCoinBoard[row][col] = ValueCoinBoard[row - 1][col] + CoinBoard[row][col];
                } else {
                    ValueCoinBoard[row][col] = ValueCoinBoard[row][col - 1] + CoinBoard[row][col];
                }

            }
        }
        return ValueCoinBoard[tablerow - 1][tablecolumn - 1];
    }

    public static double ComputeMaxNumofCoin() {

        for (int col = 1; col < tablecolumn; col++) {
            int hasCoin = 0;
            if (CoinBoard[0][col] != 0) {
                hasCoin = 1;
            }
            MaxCoinBoard[0][col] = MaxCoinBoard[0][col - 1] + hasCoin;
        }
        for (int row = 1; row < tablerow; row++) {
            int hasCoin = 0;
            if (CoinBoard[row][0] != 0) {
                hasCoin = 1;
            }
            MaxCoinBoard[row][0] = MaxCoinBoard[row - 1][0] + hasCoin;
            for (int col = 1; col < tablecolumn; col++) {
                hasCoin = 0;
                if (CoinBoard[row][col] != 0) {
                    hasCoin = 1;
                }
                if (MaxCoinBoard[row - 1][col] > MaxCoinBoard[row][col - 1]) {
                    MaxCoinBoard[row][col] = MaxCoinBoard[row - 1][col] + hasCoin;
                } else {
                    MaxCoinBoard[row][col] = MaxCoinBoard[row][col - 1] + hasCoin;
                }
            }
        }
        return MaxCoinBoard[tablerow - 1][tablecolumn - 1];
    }

    public static void PrintBoard(double array[][]) {
        for (int r = 0; r < tablerow; r++) {
            for (int c = 0; c < tablecolumn; c++) {
                System.out.print("(" + array[r][c] + ") ");
            }
            System.out.println();
        }
    }

    public static void CreateRandomBoard() {

        CoinBoard = new double[tablerow][tablecolumn];
        Random rand = new Random();
        int tempNumber = 0;
        for (int r = 0; r < tablerow; r++) {
            for (int c = 0; c < tablecolumn; c++) {
                MaxCoinBoard[r][c] = 0;
                BruteMaxCoinBoard[r][c] = 0;
                if ((r == 0 && c == 0)) {//||(r==tablerow-1 && c ==tablecolumn-1)){
                    CoinBoard[r][c] = coins[0];
                    continue;
                }
                tempNumber = rand.nextInt(6);
                if (tempNumber == 0) {
                    CoinBoard[r][c] = coins[1];
                } else if (tempNumber == 1) {
                    CoinBoard[r][c] = coins[2];
                } else if (tempNumber == 2) {
                    CoinBoard[r][c] = coins[3];
                } else if (tempNumber == 3) {
                    CoinBoard[r][c] = coins[4];
                } else {
                    CoinBoard[r][c] = coins[0];
                }

            }
        }
    }

}

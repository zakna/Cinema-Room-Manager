package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int row = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seat = scanner.nextInt();

        printCinema2(row, seat);
        System.out.println("Enter a row number:");
        int choosenRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int choosenSeat = scanner.nextInt();


        System.out.println();

        // printIncome(row, seat);
        // printCinema();
    }

    private static void printCinema2(int row, int seat) {
        // Create the Cinema
        String[][] cinema2dimArray = new String[row + 1][seat + 1];
        cinema2dimArray[0][0] = " ";
        for (int i = 1; i < row + 1; i++) {
            Arrays.fill(cinema2dimArray[i], "S");
            cinema2dimArray[i][0] = String.valueOf(i);
        }
        for (int i = 1; i < seat + 1; i++) {
            cinema2dimArray[0][i] = String.valueOf(i);
        }
        for (String[] rows : cinema2dimArray) {
            System.out.println();
            for (String s : rows) {
                System.out.print(s);
                System.out.print(" ");
            }
        }
    }

    private static void printCinema(int row, int seats) {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i < 9; i++) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println();
        for (int i = 1; i < 8; i++) {
            System.out.print(i);
            System.out.print(" ");
            for (int j = 0; j < 8; j++) {
                System.out.print("S");
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    private static void printIncome(int row, int seat) {
        System.out.println("Total income:");
        int result = seat * row;
        int seatPrice = 0;
        if (result < 60) {
            seatPrice = 10;
            result = result * seatPrice;
        } else {
            int fullPrice = seat * ((row / 2) * 10);
            int reducedPrice = seat * ((row / 2) * 8);
            if (row % 2 != 0) {
                reducedPrice = seat * (((row / 2) + 1) * 8);
            }
            result = fullPrice + reducedPrice;
        }
        System.out.print("$");
        System.out.print(result);
    }
}
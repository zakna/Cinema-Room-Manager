package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the cinema size from the user
        System.out.println("Enter the number of rows:");
        int numberOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numberOfSeats = scanner.nextInt();

        // Build and Print the initial cinema
        String[][] cinema = setupCinema(numberOfRows, numberOfSeats);
        printCinema(cinema);

        // Get the chosen Seat and row
        System.out.println();
        System.out.println("Enter a row number:");
        int chosenRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int chosenSeat = scanner.nextInt();

        //Print ticket Price
        int price = priceCalculator(numberOfRows, numberOfSeats, chosenRow);
        System.out.println(price);

        // Print the updated cinema
        String[][] updatedCinema = updateSeat(chosenSeat, chosenRow, cinema);
        printCinema(updatedCinema);
        System.out.println();
    }

    private static int priceCalculator(int numberOfRows, int numberOfSeats, int chosenRow) {
        int cinemaSize = numberOfSeats * numberOfRows;
        int seatPrice;
        if (cinemaSize < 60) {
            seatPrice = 10;
        } else if (chosenRow > (numberOfRows / 2)) {
            seatPrice = 8;
        } else {
            seatPrice = 10;
        }
        return seatPrice;
    }


    private static void printCinema(String[][] cinema) {
        System.out.print("Cinema:");
        for (String[] rows : cinema) {
            System.out.println();
            for (String s : rows) {
                System.out.print(s);
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    private static String[][] updateSeat(int choosenSeat, int choosenRow, String[][] cinema) {
        cinema[choosenSeat][choosenRow] = "B";
        return cinema;
    }

    private static String[][] setupCinema(int row, int seat) {
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
        return cinema2dimArray;
    }
}
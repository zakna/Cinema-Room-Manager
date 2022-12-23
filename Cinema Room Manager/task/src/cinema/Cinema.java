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

        // Build cinema
        String[][] cinema = setupCinema(numberOfRows, numberOfSeats);
        boolean exitMenu = false;
        while (!exitMenu) {
            printMenu();
            int choice = scanner.nextInt();
            if (choice == 1) {
                // Print the cinema
                printCinema(cinema);
                System.out.println();

            } else if (choice == 2) {
                // Get the chosen seat and row
                System.out.println("Enter a row number:");
                int chosenRow = scanner.nextInt();
                System.out.println("Enter a seat number in that row:");
                int chosenSeat = scanner.nextInt();

                // Print ticket price
                int price = priceCalculator(numberOfRows, numberOfSeats, chosenRow);
                System.out.print("Ticket price: $");
                System.out.println(price);
                System.out.println();
                updateSeat(chosenSeat, chosenRow, cinema);
            } else if (choice == 0) {
                exitMenu = true;
            }
        }
    }

    private static void printMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("0. Exit");
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

    private static void updateSeat(int choosenSeat, int choosenRow, String[][] cinema) {
        cinema[choosenRow][choosenSeat] = "B";
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
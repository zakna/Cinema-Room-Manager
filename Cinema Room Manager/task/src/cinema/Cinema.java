package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    private static int purchasedTicket;
    private static int currentIncome;
    private static int potentialTotalIncome;
    private static int cinemaSize;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the cinema size
        System.out.println("Enter the number of rows:");
        int numberOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numberOfSeats = scanner.nextInt();

        // Initialise number of seats
        cinemaSize = numberOfRows * numberOfSeats;
        potentialTotalIncome = getPotentialTotalIncome(numberOfRows, numberOfSeats);

        // Build cinema
        String[][] cinema = setupCinema(numberOfRows, numberOfSeats);

        boolean exitMenu = false;
        // Enter main program menu
        while (!exitMenu) {
            printMenu();
            int choice = scanner.nextInt();

            // Todo: refactor to a switch statement
            if (choice == 1) {

                // Print the cinema
                printCinema(cinema);
                System.out.println();
            } else if (choice == 2) {
                boolean isBuyingTicket = true;
                while (isBuyingTicket) {
                    // Get the chosen seat and row
                    System.out.println("Enter a row number:");
                    int chosenRow = scanner.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    int chosenSeat = scanner.nextInt();

                    if (chosenRow < 0
                            || chosenSeat < 0
                            || chosenRow > cinema.length - 1
                            || chosenSeat > cinema[chosenRow].length - 1
                    ) {
                        System.out.println("Wrong input!");
                    }
                    // Checking if the seat is available
                    else if (isSeatTaken(chosenSeat, chosenRow, cinema)) {
                        System.out.println("That ticket has already been purchased!");
                    } else {
                        // Print ticket price
                        int price = priceCalculator(numberOfRows, numberOfSeats, chosenRow);
                        System.out.print("Ticket price: $");
                        System.out.println(price);
                        System.out.println();
                        updateCurrentIncome(price);
                        updateSeat(chosenSeat, chosenRow, cinema);
                        purchasedTicket += 1;
                        isBuyingTicket = false;
                    }
                }
            } else if (choice == 3) {

                // Statistics
                printStatistics();
            } else if (choice == 0) {
                exitMenu = true;
            }
        }

    }

    private static int getPotentialTotalIncome(int rows, int seats) {
        int seatPrice;
        int potentialIncome;
        if (cinemaSize < 60) {
            seatPrice = 10;
            potentialIncome = seatPrice * cinemaSize;
        } else {
            int fullPrice = seats * ((rows / 2) * 10);
            int reducedPrice = seats * ((rows / 2) * 8);
            // if rows are uneven skew round towards the reducedPrise
            if (rows % 2 != 0) {
                reducedPrice = seats * (((rows / 2) + 1) * 8);
            }
            potentialIncome = fullPrice + reducedPrice;
        }
        return potentialIncome;
    }

    private static void updateCurrentIncome(int price) {
        currentIncome += price;
    }

    private static void printStatistics() {
        System.out.print("Number of purchased tickets: ");
        System.out.println(purchasedTicket);
        double percentage = (purchasedTicket / (double) cinemaSize) * 100;
        // Print operations
        System.out.printf("Percentage: %.2f", percentage);
        System.out.println("%");
        System.out.print("Current income: $");
        System.out.println(currentIncome);
        System.out.print("Total income: $");
        System.out.println(potentialTotalIncome);
    }

    private static void printMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
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

    private static void updateSeat(int chosenSeat, int chosenRow, String[][] cinema) {
        cinema[chosenRow][chosenSeat] = "B";
    }

    private static boolean isSeatTaken(int chosenSeat, int chosenRow, String[][] cinema) {
        return cinema[chosenRow][chosenSeat].equals("B");
    }

    private static String[][] setupCinema(int row, int seat) {
        // Create the Cinema
        String[][] cinema = new String[row + 1][seat + 1];
        cinema[0][0] = " ";
        for (int i = 1; i < row + 1; i++) {
            Arrays.fill(cinema[i], "S");
            cinema[i][0] = String.valueOf(i);
        }
        for (int i = 1; i < seat + 1; i++) {
            cinema[0][i] = String.valueOf(i);
        }
        return cinema;
    }
}
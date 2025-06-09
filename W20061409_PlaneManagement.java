/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.planemanagement2;

import java.util.Scanner;

public class W20061409_PlaneManagement {
    private static final int ROWS = 4; // Assuming 4 rows for simplicity
    private static final int SEATS_PER_ROW = 14; // Assuming a maximum of 14 seats per row
    private static int[][] seats = new int[ROWS][SEATS_PER_ROW];
    
    private static int ticketCount = 0;
    
    private static Tickets[] ticketsList = new Tickets[52]; //as the instructor told me to create an array

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Plane Management application");

        // Initialize all seats to available
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < SEATS_PER_ROW; j++) {
                seats[i][j] = 0;
            }
        }

        boolean running = true;
        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Buy a seat");
            System.out.println("2. Cancel a seat");
            System.out.println("3. Find first available seat");
            System.out.println("4. Show seating plan");
            System.out.println("5. Print tickets information and total sales");
            System.out.println("6. Search for a ticket");
            System.out.println("0. Exit");
            System.out.print("Please select an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    buySeat(scanner);
                    break;
                case 2:
                    cancelSeat(scanner);
                    break;
                case 3:
                    findFirstAvailableSeat();
                    break;
                case 4:
                    showSeatingPlan();
                    break;
                case 5:
                    print_tickets_info(scanner);
                    break;
                case 6:
                    searchTicket(scanner);
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting the Plane Management2 application...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (running);

        scanner.close();
    }


    private static void buySeat(Scanner scanner) {
        System.out.println("Enter the row letter (A-D):");
        String rowInput = scanner.next().toUpperCase();
        int rowIndex = rowInput.charAt(0) - 'A';

        if (rowIndex < 0 || rowIndex >= ROWS) {
            System.out.println("Invalid row. Please try again.");
            return;
        }

        System.out.println("Enter the seat number (1-14):");
        int seatNumber = scanner.nextInt() - 1;

        if (seatNumber < 0 || seatNumber >= SEATS_PER_ROW) {
            System.out.println("Invalid seat number. Please try again.");
            return;
        }

        if (seats[rowIndex][seatNumber] == 1) {
            System.out.println("This seat is already sold. Please select another seat.");
            return;
        }

        seats[rowIndex][seatNumber] = 1;
        System.out.println("Seat " + rowInput + (seatNumber + 1) + " has been successfully bought.");
    }

    private static void cancelSeat(Scanner scanner) {
        System.out.println("Enter the row letter (A-D) to cancel:");
        String rowInput = scanner.next().toUpperCase();
        int rowIndex = rowInput.charAt(0) - 'A';

        if (rowIndex < 0 || rowIndex >= ROWS) {
            System.out.println("Invalid row. Please try again.");
            return;
        }

        System.out.println("Enter the seat number (1-14) to cancel:");
        int seatNumber = scanner.nextInt() - 1;

        if (seatNumber < 0 || seatNumber >= SEATS_PER_ROW) {
            System.out.println("Invalid seat number. Please try again.");
            return;
        }

        if (seats[rowIndex][seatNumber] == 0) {
            System.out.println("This seat is already available and cannot be cancelled.");
            return;
        }

        seats[rowIndex][seatNumber] = 0;
        System.out.println("Seat " + rowInput + (seatNumber + 1) + " reservation has been successfully cancelled.");
    }

    private static void findFirstAvailableSeat() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < SEATS_PER_ROW; j++) {
                if (seats[i][j] == 0) {
                    char rowLetter = (char) ('A' + i);
                    int seatNumber = j + 1;
                    System.out.println("The first available seat is " + rowLetter + seatNumber);
                    return;
                }
            }
        }
        System.out.println("There are no available seats.");
    }

    private static void showSeatingPlan() {
        System.out.println("Seating Plan:");
        for (int i = 0; i < ROWS; i++) {
            char rowLabel = (char) ('A' + i);
            System.out.print(rowLabel + ": ");
            for (int j = 0; j < SEATS_PER_ROW; j++) {
                if (seats[i][j] == 0) {
                    System.out.print("O ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }

    private static void print_tickets_info(Scanner printTicketInfo){
        double totalAmount = 0.0;
        System.out.println("TICKETS INFORMATION");
        for(int i =0; i<ticketCount; i++){
            totalAmount += ticketsList[i].getPrice();
            
        }
        System.out.println("Total Amount: £"+ totalAmount);
    }
    
    private static void searchTicket(Scanner scanner) {
        System.out.println("Enter the row letter (A-D):");
        String rowInput = scanner.next().toUpperCase();
        int rowIndex = rowInput.charAt(0) - 'A';

        if (rowIndex < 0 || rowIndex >= ROWS) {
            System.out.println("Invalid row. Please try again.");
            return;
        }

        System.out.println("Enter the seat number (1-14):");
        int seatNumber = scanner.nextInt() - 1;

        if (seatNumber < 0 || seatNumber >= SEATS_PER_ROW) {
            System.out.println("Invalid seat number. Please try again.");
            return;
        }

        if (seats[rowIndex][seatNumber - 1]==1){
            for(Tickets ticket : ticketsList){
                if(ticket != null && ticket.getRow() == rowIndex && ticket.getSeat() == seatNumber){
                    System.out.println("--------Tickets Information--------");
                    ticket.getTicketInfo();
                    return;
                }
            }
            System.out.println("This seat has already been sold.");
        }
        else{
            System.out.println("This seat is still available.");
        }
    }
}
                

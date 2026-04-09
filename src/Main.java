import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import parts.*;

class Main {

    static Scanner scanner = new Scanner(System.in);
    static BankLinkedList accounts = new BankLinkedList();
    static TransactionHistory history = new TransactionHistory();
    static BillQueue billQueue = new BillQueue();
    static AccountRequestQueue requestQueue = new AccountRequestQueue();


    public static int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid — enter a number: ");
            scanner.next();
        }
        int val = scanner.nextInt();
        scanner.nextLine();
        return val;
    }


    public static void bankMenu() {
        int choice;
        do {
            System.out.println(" BANK MENU ");
            System.out.println("1. Submit account opening request");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. Display all accounts");
            System.out.println("5. Search account by username");
            System.out.println("6. View transaction history");
            System.out.println("7. Show last transaction");
            System.out.println("8. Undo last transaction");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose: ");
            choice = readInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter username: ");
                    String uname = scanner.nextLine();
                    System.out.print("Enter initial balance: ");
                    double bal = scanner.nextDouble();
                    scanner.nextLine();

                    requestQueue.submitRequest(uname, bal);
                }

                case 2 -> {
                    System.out.print("Enter username: ");
                    String uname = scanner.nextLine();
                    System.out.print("Enter deposit amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    accounts.deposit(uname, amount);
                    history.addTransaction("Deposit " + amount + " to " + uname);
                }

                case 3 -> {
                    System.out.print("Enter username: ");
                    String uname = scanner.nextLine();
                    System.out.print("Enter withdraw amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    accounts.withdraw(uname, amount);
                    history.addTransaction("Withdraw " + amount + " from " + uname);
                }
                case 4 -> accounts.displayAccounts();

                case 5 -> {
                    System.out.print("Enter username to search: ");
                    String uname = scanner.nextLine();
                    BankAccount found = accounts.searchByUsername(uname);
                    if (found != null) System.out.println("Found: " + found);
                    else System.out.println("Account not found.");
                }
                case 6 -> history.displayHistory();

                case 7 -> history.showLastTransaction();

                case 8 -> history.undoLastTransaction();

                case 0 -> System.out.println("Returning to main menu...");

                default -> System.out.println("Invalid option.");
            }
        } while (choice != 0);
    }


    public static void atmMenu() {

        int choice;
        do {
            System.out.println(" ATM MENU ");
            System.out.println("1. Balance enquiry");
            System.out.println("2. Withdraw");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose: ");
            choice = readInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String uname = scanner.nextLine();
                    BankAccount found = accounts.searchByUsername(uname);
                    if(found != null) {
                        System.out.println("Balance for " + uname
                                + ": " + found.getBalance());
                    }
                    else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 2:
                    System.out.print("Enter username: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter amount to withdraw: ");
                    double amount = scanner.nextDouble(); scanner.nextLine();
                    accounts.withdraw(name, amount);
                    history.addTransaction("ATM Withdraw " + amount + " from " + name);
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        } while (choice != 0);
    }


    public static void adminMenu() {
        int choice;
        do {
            System.out.println(" ADMIN MENU ");
            System.out.println("1. View pending account requests");
            System.out.println("2. Process next account request");
            System.out.println("3. Add bill payment to queue");
            System.out.println("4. Process next bill payment");
            System.out.println("5. View bill queue");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose: ");

            choice = readInt();

            switch (choice) {
                case 1:
                    requestQueue.displayPendingRequests();
                    break;
                case 2:
                    requestQueue.processNextRequest(accounts);
                    break;
                case 3:
                    System.out.print("Enter bill name: ");
                    String bill = scanner.nextLine();
                    billQueue.addBill(bill);
                    break;
                case 4:
                    billQueue.processNextBill();
                    break;
                case 5:
                    billQueue.displayQueue();
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        } while (choice != 0);
    }


    public static void main(String[] args) {

        accounts.addAccount(new BankAccount(101, "Ali",  150000));
        accounts.addAccount(new BankAccount(102, "Sara", 220000));

        BankArray.run();

        int choice;
        do {
            System.out.println("    MAIN BANKING MENU     ");
            System.out.println(" 1 – Enter Bank");
            System.out.println(" 2 – Enter ATM");
            System.out.println(" 3 – Admin Area");
            System.out.println(" 4 – Exit");
            System.out.print("Choose: ");
            choice = readInt();

            switch (choice) {
                case 1 -> bankMenu();
                case 2 -> atmMenu();
                case 3 -> adminMenu();
                case 4 -> System.out.println("Thank you. Goodbye!");
                default -> System.out.println("Invalid option. Try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}

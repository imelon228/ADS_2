package parts;

public class BankArray {


    public static void run() {
        System.out.println(" TASK 6: Array of BankAccounts ");

        BankAccount[] accounts = new BankAccount[3];   // fixed-size array

        accounts[0] = new BankAccount(201, "Amir",   300000);
        accounts[1] = new BankAccount(202, "Leila",  175000);
        accounts[2] = new BankAccount(203, "Daniyar",450000);

        System.out.println("Stored Accounts in Array:");
        for (int i = 0; i < accounts.length; i++) {
            System.out.println((i + 1) + ". " + accounts[i]);
        }
    }
}

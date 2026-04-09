package parts;

import java.util.LinkedList;
import java.util.Queue;

public class AccountRequestQueue {

    private Queue<String> requestQueue = new LinkedList<>();
    private int nextAccountNumber = 1000;


    public void submitRequest(String username, double balance) {
        requestQueue.offer(username + "," + balance);
        System.out.println("Request submitted for: " + username);
    }


    public void processNextRequest(BankLinkedList accounts) {
        if (requestQueue.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }
        String data = requestQueue.poll();
        String[] parts = data.split(",");
        String uname = parts[0];
        double balance = Double.parseDouble(parts[1]);

        BankAccount newAccount = new BankAccount(nextAccountNumber++, uname, balance);
        accounts.addAccount(newAccount);
        System.out.println("Processed and activated account for: " + uname);
    }


    public void displayPendingRequests() {
        if (requestQueue.isEmpty()) {
            System.out.println("No pending account requests.");
            return;
        }
        System.out.println("Pending Account Requests:");
        int index = 1;
        for (String req : requestQueue) {
            String[] parts = req.split(",");
            System.out.println(index + ". Username: " + parts[0] + " Initial Balance: "  + parts[1]);
            index++;
        }
    }
}
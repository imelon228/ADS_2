package parts;

import java.util.Stack;

public class TransactionHistory {

    private Stack<String> history = new Stack<>();


    public void addTransaction(String transaction) {
        history.push(transaction);
        System.out.println("Transaction recorded: " + transaction);
    }


    public void showLastTransaction() {
        if (history.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        System.out.println("Last transaction: " + history.peek());
    }


    public void undoLastTransaction() {
        if (history.isEmpty()) {
            System.out.println("Nothing to undo.");
            return;
        }
        String removed = history.pop();
        System.out.println("Undo → " + removed + " removed");
    }


    public void displayHistory() {
        if (history.isEmpty()) {
            System.out.println("Transaction history is empty.");
            return;
        }
        System.out.println("Transaction History (latest first):");

        for (int i = history.size() - 1; i >= 0; i--) {
            System.out.println("  - " + history.get(i));
        }
    }
}
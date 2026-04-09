package parts;


public class BankLinkedList {

    private Node head;   // always points to the FIRST node
    private int  size;

    public BankLinkedList() {
        head = null;
        size = 0;
    }

    public void addAccount(BankAccount account) {
        Node newNode = new Node(account);   // put account inside a Node

        // Case 1: list is empty — new node becomes the head
        if (head == null) {
            head = newNode;
        }
        // Case 2: list has nodes — walk to the last node, attach there
        else {
            Node current = head;
            while (current.next != null) {   // stop when next is null = last node
                current = current.next;
            }
            current.next = newNode;          // link last node → new node
        }

        size++;
        System.out.println("Account added successfully");
    }

    public void displayAccounts() {
        if (head == null) {
            System.out.println("No accounts found.");
            return;
        }

        System.out.println("Accounts List:");
        Node current = head;
        int  index   = 1;

        while (current != null) {            // walk until the end (null)
            System.out.println(index + ". " + current.account);
            current = current.next;          // move to next node
            index++;
        }
    }

    public BankAccount searchByUsername(String username) {
        Node current = head;
        while (current != null) {
            if (current.account.getUsername().equalsIgnoreCase(username)) {
                return current.account;
            }
            current = current.next;
        }
        return null;
    }

    public void deposit(String username, double amount) {
        BankAccount account = searchByUsername(username);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
        account.setBalance(account.getBalance() + amount);
        System.out.println("Deposit: " + amount);
        System.out.println("New balance: " + account.getBalance());
    }


    public void withdraw(String username, double amount) {
        BankAccount account = searchByUsername(username);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
        if (amount > account.getBalance()) {
            System.out.println("Insufficient balance.");
            return;
        }
        account.setBalance(account.getBalance() - amount);
        System.out.println("Withdraw: "    + amount);
        System.out.println("New balance: " + account.getBalance());
    }

    public int getSize() { return size; }
}

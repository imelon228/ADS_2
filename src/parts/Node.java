package parts;

public class Node {
    BankAccount account;
    Node next;

    public Node(BankAccount account) {
        this.account = account;
        this.next = null;
    }

}
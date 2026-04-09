package parts;

import java.util.LinkedList;
import java.util.Queue;

public class BillQueue {

    private Queue<String> queue = new LinkedList<>();


    public void addBill(String bill) {
        queue.offer(bill);
        System.out.println("Added: " + bill);
    }


    public void processNextBill() {
        if (queue.isEmpty()) {
            System.out.println("No bills in queue.");
            return;
        }
        System.out.println("Processing: " + queue.poll());
    }


    public void displayQueue() {
        if (queue.isEmpty()) {
            System.out.println("Bill queue is empty.");
            return;
        }
        System.out.println("\nPending Bills:");
        int index = 1;
        for (String bill : queue) {
            System.out.println(index + ". " + bill);
            index++;
        }
    }
}
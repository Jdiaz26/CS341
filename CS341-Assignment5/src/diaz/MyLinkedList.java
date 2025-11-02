package diaz;

public class MyLinkedList {

    Node head;
    int count = 0;

    class Node {
        double value;
        Node next;
    }

    void add(double val) {
        Node newNode = new Node();
        newNode.value = val;
        newNode.next = null;

        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }

        count++;
    }

    int size() {
        return count;
    }

    double getMean() {
        if (head == null) return 0;
        Node temp = head;
        double sum = 0;
        while (temp != null) {
            sum = sum + temp.value;
            temp = temp.next;
        }
        return sum / count;
    }

    double getStd(double mean) {
        if (head == null) return 0;
        Node temp = head;
        double sumSq = 0;
        while (temp != null) {
            double diff = temp.value - mean;
            sumSq = sumSq + diff * diff;
            temp = temp.next;
        }
        return Math.sqrt(sumSq / count);
    }
}



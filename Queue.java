package ASM2;

import java.util.NoSuchElementException;

class Queue<T> implements AbstractOder<T> {
    private String customerName;
    private String customerAddress;
    private Node<T> head;
    private Node<T> tail;
    private int size;


    private class Node<T> {
        private T element;
        private Node<T> next;
        private int quantity;

        public Node(T element, int quantity) {
            this.element = element;
            this.next = null;
            this.quantity = quantity;
        }
    }

    @Override
    public void offer(T element, int quantity) {
        Node<T> current = head;
        while (current != null) {
            if (current.element.equals(element)) {
                current.quantity += quantity; // Cập nhật số lượng nếu sách đã có trong đơn hàng
                return;
            }
            current = current.next;
        }
        Node<T> newNode = new Node<>(element, quantity);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public boolean remove(T element) {
        if (isEmpty()) {
            return false;
        }

        if (head.element.equals(element)) {
            poll(); // Remove the first element
            return true;
        }

        Node<T> current = head;
        while (current.next != null) {
            if (current.next.element.equals(element)) {
                current.next = current.next.next;
                if (current.next == null) {
                    tail = current; // Update tail if needed
                }
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public T poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        T oldNodeValue = head.element;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return oldNodeValue;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return head.element;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void add(T currentOrder) {

    }

    @Override
    public String toString() {
        StringBuilder results = new StringBuilder();
        results.append("Order{");

        Node<T> tempNode = head;
        while (tempNode != null) {
            results.append(tempNode.element).append(" (Quantity buy: ").append(tempNode.quantity).append(")");
            if (tempNode.next != null) {
                results.append(", ");
            }
            tempNode = tempNode.next;
        }

        results.append('}');
        return results.toString();
    }
}

package com.amo.algorithms;

public class Node <Item>{
    private Node next;
    private Node previous;
    private Item data;

    public Node(Item data, Node next, Node previous) {
        this.next = next;
        this.previous = previous;
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Item getData() {
        return data;
    }

    public void setData(Item data) {
        this.data = data;
    }
}

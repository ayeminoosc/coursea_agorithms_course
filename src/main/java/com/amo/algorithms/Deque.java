package com.amo.algorithms;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of the assignment mentioned in `https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php`
 *
 * A double-ended queue or deque (pronounced “deck”) is a generalization of a stack and a queue that supports adding and
 * removing items from either the front or the back of the data structure.
 *
 * Performance requirements.  Your deque implementation must support each deque operation (including construction) in
 * constant worst-case time. A deque containing n items must use at most 48n + 192 bytes of memory. Additionally, your
 * iterator implementation must support each operation (including construction) in constant worst-case time.
 *
 * Corner cases.  Throw the specified exception for the following corner cases: *
 * Throw an IllegalArgumentException if the client calls either addFirst() or addLast() with a null argument.
 * Throw a java.util.NoSuchElementException if the client calls either removeFirst() or removeLast when the deque is empty.
 * Throw a java.util.NoSuchElementException if the client calls the next() method in the iterator when there are no more items to return.
 * Throw an UnsupportedOperationException if the client calls the remove() method in the iterator.
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {
    private Node<Item> left;
    private Node<Item> right;
    private int length;
    // construct an empty deque
    public Deque(){

    }

    // is the deque empty?
    public boolean isEmpty(){
        return left == null && right == null;
    }

    // return the number of items on the deque
    public int size(){
        return length;
    }

    // add the item to the front
    public void addFirst(Item item){
        if(item == null) throw new IllegalArgumentException("item can't be null");
        if(isEmpty()){
            Node<Item> node = new Node<Item>(item, null, null);
            left = node;
            right = node;
        }else{
            Node node = new Node(item, left, null);
            left.setPrevious(node);
            left = node;
        }
        length++;
    }

    // add the item to the back
    public void addLast(Item item){
        if(item == null) throw new IllegalArgumentException("item can't be null");
        if(isEmpty()){
            Node<Item> node = new Node<Item>(item, null, null);
            left = node;
            right = node;
        }else{
            Node node = new Node(item, null, right);
            right.setNext(node);
            right = node;
        }
        length++;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if(isEmpty()) throw new NoSuchElementException("deque is empty");
        Item temp = left.getData();
        if(left == right) {
            right = null;
        }
        left = left.getNext();

        if(left !=null) left.setPrevious(null);
        length--;
        return temp;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if(isEmpty()) throw new NoSuchElementException("deque is empty");
        Item temp = right.getData();
        if(left == right){
            left = null;
        }
        right = right.getPrevious();
        if(right != null) right.setNext(null);
        length--;
        return temp;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new DequeIterator<Item>(left);
    }

    // unit testing (required)
    public static void main(String[] args){
        Deque<String> deque = new Deque<String>();
        System.out.println(deque.size());
        deque.addFirst("1");
        deque.addFirst("2");
        deque.addFirst("3");
        deque.addFirst("4");
        deque.addLast("5");
        println(deque);

        deque.addLast("10");
        println(deque);

        deque.removeFirst();
        deque.removeFirst();
        println(deque);

        deque.removeLast();
        deque.removeLast();
        println(deque);
        deque.removeLast();
        deque.removeFirst();
        println(deque);

    }

    private static <Item> void println(Deque<Item> deque){
        System.out.println("size: " + deque.size() + " isEmpty: "+ deque.isEmpty());
        for (Item item : deque) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

}

class DequeIterator<Item> implements Iterator<Item>{
    private Node <Item> start;

    public DequeIterator(Node<Item> left){
        start = left;
    }

    @Override
    public boolean hasNext() {
        return start != null;
    }

    @Override
    public Item next() {
        Item temp = start.getData();
        start = start.getNext();
        return temp;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
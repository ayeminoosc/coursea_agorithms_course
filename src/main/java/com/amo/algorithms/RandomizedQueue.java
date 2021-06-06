package com.amo.algorithms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Implementation of the assignment mentioned in `https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php`
 *
 * A randomized queue is similar to a stack or queue, except that the item removed is chosen uniformly at random among
 * items in the data structure
 *
 * Performance requirements.  Your randomized queue implementation must support each randomized queue operation (besides
 * creating an iterator) in constant amortized time. That is, any intermixed sequence of m randomized queue operations
 * (starting from an empty queue) must take at most cm steps in the worst case, for some constant c. A randomized queue
 * containing n items must use at most 48n + 192 bytes of memory. Additionally, your iterator implementation must support
 * operations next() and hasNext() in constant worst-case time; and construction in linear time; you may (and will need to)
 * use a linear amount of extra memory per iterator.
 *
 * Iterator.  Each iterator must return the items in uniformly random order. The order of two or more iterators to the
 * same randomized queue must be mutually independent; each iterator must maintain its own random order.
 *
 * Corner cases.  Throw the specified exception for the following corner cases:
 * Throw an IllegalArgumentException if the client calls enqueue() with a null argument.
 * Throw a java.util.NoSuchElementException if the client calls either sample() or dequeue() when the randomized queue is empty.
 * Throw a java.util.NoSuchElementException if the client calls the next() method in the iterator when there are no more items to return.
 * Throw an UnsupportedOperationException if the client calls the remove() method in the iterator.
 *
 * @param <Item>
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private List<Item> items = new ArrayList<>();
    // construct an empty randomized queue
    public RandomizedQueue(){

    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return items.isEmpty();
    }

    // return the number of items on the randomized queue
    public int size(){
        return items.size();
    }

    // add the item
    public void enqueue(Item item){
        items.add(item);
    }

    // remove and return a random item
    public Item dequeue(){
        int i = ThreadLocalRandom.current().nextInt(0, items.size());
        return items.remove(i);
    }

    // return a random item (but do not remove it)
    public Item sample(){
        int i = ThreadLocalRandom.current().nextInt(0, items.size());
        return items.get(i);
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new RandomizedIterator<>(items);
    }

    // unit testing (required)
    public static void main(String[] args){
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        queue.enqueue("5");
        queue.dequeue();
        Iterator<String> iterO = queue.iterator();
        Iterator<String> iter1 = queue.iterator();
        while (iterO.hasNext()) {
            System.out.println(iterO.next());
        }
        System.out.println();
        while (iter1.hasNext()) {
            System.out.println(iter1.next());
        }
        System.out.println();
    }

}

class RandomizedIterator<Item> implements Iterator<Item> {
    private List<Item> items;
    private int index;
    public RandomizedIterator(List<Item> list){
        index = 0;
        items = new ArrayList<>(list.size());
        List<Item> tempList = new ArrayList<>(list);
        for(int i = 0; i < list.size();i++){
            int randomIndex = ThreadLocalRandom.current().nextInt(0, tempList.size());
            items.add(tempList.remove(randomIndex));
        }
    }

    @Override
    public boolean hasNext() {
        return index < items.size();
    }

    @Override
    public Item next() {
        return items.get(index++);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
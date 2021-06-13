package com.amo.algorithms.sort;

public interface Sorter{
    public void sort(int[]array);

    /**
     * swap collection[i] with collection[j]
     * @param collection
     * @param i
     * @param j
     */
    default void swap(int[] collection, int i, int j){
        int temp = collection[i];
        collection[i] = collection[j];
        collection[j] = temp;
    }
}

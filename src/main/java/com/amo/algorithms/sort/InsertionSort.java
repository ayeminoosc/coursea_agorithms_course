package com.amo.algorithms.sort;

public class InsertionSort implements Sorter{
    @Override
    public void sort(int[] array) {
        for(int i=1; i< array.length; i++){
            compareAndMoveToLeftIfSmaller(array, i);
        }
    }

    /**
     * take data from array[i]
     * compare it with array[i-1]. if array[i] < array[i-1], swap them and continue the process til the 0 index or array[i] >= array[i-1]
     *
     * @param array
     * @param i
     */
    private void compareAndMoveToLeftIfSmaller(int[]array, int i){
        for(;i>0; i--){
            if(array[i] >= array[i-1]) return;
            swap(array, i, i-1);
        }
    }
}

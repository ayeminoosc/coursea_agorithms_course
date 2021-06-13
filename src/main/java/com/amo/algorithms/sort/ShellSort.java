package com.amo.algorithms.sort;

public abstract class ShellSort implements Sorter{
    /**
     * sort array by look back to h gap only
     * basically 1 gap sort is same as insertion sort
     * @param array
     * @param hGap
     */
    public void hSort(int[]array, int hGap){
        for(int i=hGap; i< array.length; i++){
            compareAndMoveToLeftByhGapIfSmaller(array, i, hGap);
        }
    }

    /**
     * take data from array[i]
     * compare it with array[i-hGap]. if array[i] < array[i-hGap], swap them and continue the process til the 0 index or array[i] >= array[i-hGap]
     *
     * @param array
     * @param i
     */
    private void compareAndMoveToLeftByhGapIfSmaller(int[]array, int i, int hGap){
        while(i - hGap >=0){
            if(array[i] >= array[i-hGap]) return;
            swap(array, i, i-hGap);
            i -= hGap;
        }
    }
}

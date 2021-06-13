package com.amo.algorithms.sort;

import java.util.Arrays;
public class SelectionSort implements Sorter{

    @Override
    public void sort(int[]collection) {
        for(int i = 0; i<collection.length; i++){
            //find smallest
            int smallestNumberIndex = i;
            for(int j = i+1; j< collection.length;j++){
                if(collection[smallestNumberIndex] > collection[j]) smallestNumberIndex = j;
            }
            if(i != smallestNumberIndex) swap(collection, i, smallestNumberIndex);
        }
    }



    public static void main(String[]args){
        int[]test= new int[]{5,4,3};
        Arrays.sort(test);
        System. out. println(Arrays. toString(test));
    }
}

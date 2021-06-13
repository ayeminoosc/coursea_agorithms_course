package com.amo.algorithms.sort;

public class ShellSortUsing3xPlusOne extends ShellSort{
    @Override
    public void sort(int[] array) {
        int h = 1;
        while (h <= array.length / 3) h = 3 * h + 1;
        while (h >= 1) {
            hSort(array, h);
            h = h / 3;
        }
    }
}

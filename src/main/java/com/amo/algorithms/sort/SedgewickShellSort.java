package com.amo.algorithms.sort;

public class SedgewickShellSort extends ShellSort{
    @Override
    public void sort(int[] array) {
        int incs[] = { 1391376, 463792, 198768, 86961, 33936,
                13776, 4592, 1968, 861, 336,
                112, 48, 21, 7, 3, 1 };
        for(int i = 0; i < incs.length; i++){
            int h = incs[i];
            if(h > array.length) continue;
            hSort(array, h);
        }
    }
}

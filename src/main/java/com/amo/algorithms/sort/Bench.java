package com.amo.algorithms.sort;//
// HERE BE DRAGONS!
//
// You don't have to read any of this file.
// It's just the benchmarking program.
//

import java.util.*;

public class Bench {
    /**
     * Main function
     **/

    public static void main(final String[] args) {
        executionTimeReport();
    }

    /**
     * Test data generator
     **/

    // Generates a random array of size 'size'.
    // Part of the array is sorted, while the rest is chosen uniformly
    // at random; the 'randomness' parameter sets what percent of the
    // array is chosen at random.
    public static int[] generateSample(int size, int randomness) {
        int[] sample = new int[size];

        Random random = new Random();
        int previousElement = 0;
        for (int i = 0; i < size; i++) {
            if (random.nextInt(100) >= randomness) {
                int randomOffset = random.nextInt(3);
                int currentElement = previousElement + randomOffset;
                sample[i] = currentElement;
                previousElement = currentElement;
            } else {
                sample[i] = random.nextInt(size);
            }
        }

        return sample;
    }

    /**
     * Auxiliary code, that measures performance of sorting algorithms
     **/

    private static int[] SAMPLE_SIZES = new int[]{10, 30, 100, 300, 1000, 3000, 10000, 30000, 100000};

    private static void executionTimeReport() {
        for (int size : SAMPLE_SIZES) {
            executionTimeReport(size);
        }
    }

    public static interface Function<A, B> {
        public B apply(A arg);
    }

    public static Function<int[], int[]> insertionSort = new Function<int[], int[]>() {
        @Override
        public int[] apply(int[] array) {
            new InsertionSort().sort(array);
            return array;
        }
    };

    public static Function<int[], int[]> selectionSort = new Function<int[], int[]>() {
        @Override
        public int[] apply(int[] array) {
            new SelectionSort().sort(array);
            return array;
        }
    };

    public static Function<int[], int[]> shellSortUsing3xPlus1 = new Function<int[], int[]>() {
        @Override
        public int[] apply(int[] array) {
            new ShellSortUsing3xPlusOne().sort(array);
            return array;
        }
    };

    public static Function<int[], int[]> sedgewickShellSort = new Function<int[], int[]>() {
        @Override
        public int[] apply(int[] array) {
            new SedgewickShellSort().sort(array);
            return array;
        }
    };

    public static Function<int[], int[]> quickSort = new Function<int[], int[]>() {
        @Override
        public int[] apply(int[] array) {
            new SelectionSort().sort(array);
            return array;
        }
    };

    public static Function<int[], int[]> mergeSort = new Function<int[], int[]>() {
        @Override
        public int[] apply(int[] array) {
             new SelectionSort().sort(array);
             return array;
        }
    };

    // Execute an algorithm on an input and return its runtime.
    private static String execute(Function<int[], int[]> algorithm, int[] input) {
        // To get accurate results even for small inputs, we repeat
        // the algorithm several times in a row and count the total time.
        // We pick the number of repetitions automatically so that
        // the total time is at least 10ms.
        //
        // To pick the number of repetitions, we start by assuming
        // that one repetition will be enough. We then execute the
        // algorithm and measure how long it takes. If it took less
        // than 10ms, we scale up the number of repetitions by
        // an appropriate factor. E.g., if the algorithm only took
        // 1ms, we will multiply the number of repetitions by 10.
        // We then repeat this whole process with the new number of
        // repetitions.
        //
        // Once the repetitions take more than 10ms, we try it three
        // times and take the smallest measured runtime. This avoids
        // freakish results due to e.g. the garbage collector kicking
        // in at the wrong time.

        // Minimum acceptable value for total time.
        final long target = 10000000;
        // How many times to re-measure the algorithm once it hits the
        // target time.
        final int MAX_LIVES = 3;
        // The final result of the algorithm.
        int[] result = {};
        // How many repetitions we guess will be enough.
        int repetitions = 1;
        // The lowest runtime we saw with the current number of repetitions.
        long runtime = Long.MAX_VALUE;
        // How many times we've measured after hitting the target time.
        int lives = MAX_LIVES;
        try {
            while (true) {
                // Build the input arrays in advance to avoid memory
                // allocation during testing.
                int[][] inputs = new int[repetitions][];
                for (int i = 0; i < repetitions; i++)
                    inputs[i] = Arrays.copyOf(input, input.length);
                // Try to reduce unpredictability
                System.gc();
                Thread.yield();

                // Run the algorithm
                long startTime = System.nanoTime();
                for (int i = 0; i < repetitions; i++)
                    result = algorithm.apply(inputs[i]);
                long endTime = System.nanoTime();
                runtime = Math.min(runtime, endTime - startTime);

                // If the algorithm is really slow, we don't
                // need to measure too carefully
                if (repetitions == 1 && runtime >= 30 * target)
                    break;
                if (runtime >= target) {
                    // Ran for long enough - reduce number of lives by one.
                    if (lives == 0) break;
                    else lives--;
                } else {
                    // Didn't run for long enough.
                    // Increase number of repetitions to try to hit
                    // target - but at least double it.
                    if (runtime == 0)
                        repetitions *= 2;
                    else
                        repetitions *= 2 * target / runtime;
                    runtime = Long.MAX_VALUE;
                    lives = MAX_LIVES;
                }
            }
        } catch (UnsupportedOperationException uop) {
            return "-";
        } catch (Exception e) {
            return "EXCEPTION";
        } catch (StackOverflowError e) {
            return "STACK OVERFLOW";
        }
        int[] reference = Arrays.copyOf(input, input.length);
        Arrays.sort(reference);
        if (Arrays.equals(result, reference)) {
            return String.format("%6f", (double) runtime / ((long) repetitions * 1000000));
        } else {
            return "INCORRECT";
        }
    }

    private static int[] reverse(int[] array){
        for(int i=0; i<= array.length/2; i++){
            int temp = array[i];
            array[i] = array[array.length-1-i];
            array[array.length-1-i] = temp;
        }
        return array;
    }

    private static void executionTimeReport(int size) {
        int[] sortedSample = generateSample(size, 0);
        int[] partiallySortedSample = generateSample(size, 5);
        int[] randomSample = generateSample(size, 100);
        int[] reverseSortedSample = reverse(sortedSample.clone());

        System.out.println(String.format(
                "Arrays of length %d\n" +
                        "======================================================================\n" +
                        "Algorithm           | %14s | %14s | %14s | %14s\n" +
                        "Selection sort      | %14s | %14s | %14s | %14s\n" +
                        "Insertion sort      | %14s | %14s | %14s | %14s\n" +
                        "Shell sort (3x+1)   | %14s | %14s | %14s | %14s\n" +
                        "Sedgewick Shell sort| %14s | %14s | %14s | %14s\n" +
                        "Quicksort           | %14s | %14s | %14s | %14s\n" +
                        "Merge sort          | %14s | %14s | %14s | %14s\n",
                size,
                "Random", "95% sorted", "Sorted", "Reverse Sorted",
                execute(selectionSort, randomSample),
                execute(selectionSort, partiallySortedSample),
                execute(selectionSort, sortedSample),
                execute(selectionSort, reverseSortedSample),

                execute(insertionSort, randomSample),
                execute(insertionSort, partiallySortedSample),
                execute(insertionSort, sortedSample),
                execute(insertionSort, reverseSortedSample),

                execute(shellSortUsing3xPlus1, randomSample),
                execute(shellSortUsing3xPlus1, partiallySortedSample),
                execute(shellSortUsing3xPlus1, sortedSample),
                execute(shellSortUsing3xPlus1, reverseSortedSample),

                execute(sedgewickShellSort, randomSample),
                execute(sedgewickShellSort, partiallySortedSample),
                execute(sedgewickShellSort, sortedSample),
                execute(sedgewickShellSort, reverseSortedSample),

                execute(quickSort, randomSample),
                execute(quickSort, partiallySortedSample),
                execute(quickSort, sortedSample),
                execute(quickSort, reverseSortedSample),

                execute(mergeSort, randomSample),
                execute(mergeSort, partiallySortedSample),
                execute(mergeSort, sortedSample),
                execute(mergeSort, reverseSortedSample)
        ));
    }
}
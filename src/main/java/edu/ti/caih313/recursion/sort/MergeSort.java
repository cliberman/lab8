package edu.ti.caih313.recursion.sort;

/**
 * Class for sorting an array of integers from smallest to largest
 * using the merge sort algorithm.
 */
public class MergeSort {
    private static int numberOfCopies;

    public MergeSort() {
        this.numberOfCopies = 0;
    }

    private static int getNumberOfCopies() {
        return numberOfCopies;
    }
    /**
     * Precondition: Every indexed variable of the array a has a value.
     * Postcondition: a[0] <= a[1] <= ... <= a[a.length - 1].
     */
    public static void sort(int[] a, String prefix) {
        System.out.println(prefix + "Entering sort(a.length=" + a.length + ")");
        //if a.length == 1, a is sorted.
        if (a.length >= 2) {
            int halfLength = a.length / 2;
            int[] firstHalf = new int[halfLength];
            int[] lastHalf = new int[a.length - halfLength];

            System.out.println(prefix +"Calling divide(a.length" + a.length + "; firstHalf.length = " + firstHalf.length + "; lastHalf.length = " + lastHalf.length + ")");
            divide(a, firstHalf, lastHalf, "-" + prefix);
            System.out.println(prefix + "Calling sort(firstHalf.length= " + firstHalf.length + ", a.length= " + a.length + ")");
            sort(firstHalf, "-"+prefix);
            System.out.println(prefix + "Calling sort(lastHalf.length= " + lastHalf.length + ", a.length= " + a.length + ")");
            sort(lastHalf, "-"+prefix);
            System.out.println(prefix + "Calling merge(a.length = " + a.length + "; firstHalf.length = " + firstHalf.length + "; lastHalf.length = " + lastHalf.length + ")");
            merge(a, firstHalf, lastHalf, "-" + prefix);
        }
        System.out.println(prefix + "Exiting sort(a.length=" + a.length + ")");
    }

    //Precondition: a.length = firstHalf.length + lastHalf.length.
    //Postcondition: All the elements of a are divided
    //between the arrays firstHalf and lastHalf.
    private static void divide(int[] a, int[] firstHalf,
                               int[] lastHalf, String prefix) {
        int copiesFirstHalf = 0;
        int copiesLastHalf = 0;
        System.out.println(prefix + "Entering divide(a.length = " + a.length + ", firstHalf.length = " + firstHalf.length + ", lastHalf.length = " + lastHalf.length + ")");
        for (int i = 0; i < firstHalf.length; i++) {
            firstHalf[i] = a[i];
            copiesFirstHalf++;
        }
        for (int i = 0; i < lastHalf.length; i++) {
            lastHalf[i] = a[firstHalf.length + i];
            copiesLastHalf++;
        }
        numberOfCopies= copiesFirstHalf + copiesLastHalf;
        System.out.println("Copied to first half: " + copiesFirstHalf + ", copied to last half: " + copiesLastHalf + ", number of copies= " + numberOfCopies);
        System.out.println(prefix + "Exiting divide(a.length=" + a.length + ", firstHalf.length = " + firstHalf.length + ", lastHalf.length = " + lastHalf.length + ")");
    }

    //Precondition: Arrays firstHalf and lastHalf are sorted from
    //smallest to largest; a.length = firstHalf.length +
    //lastHalf.length.
    //Postcondition: Array a contains all the values from firstHalf
    //and lastHalf and is sorted from smallest to largest.
    private static void merge(int[] a, int[] firstHalf,
                              int[] lastHalf, String prefix) {
        System.out.println(prefix + "Entering merge(a.length = " + a.length + "; firstHalf.length = " + firstHalf.length + "; lastHalf.length = " + lastHalf.length + ")");
        int firstHalfIndex = 0, lastHalfIndex = 0, aIndex = 0, numberMerged = 0;
        while ((firstHalfIndex < firstHalf.length) &&
                (lastHalfIndex < lastHalf.length)) {
            if (firstHalf[firstHalfIndex] < lastHalf[lastHalfIndex]) {
                a[aIndex] = firstHalf[firstHalfIndex];
                firstHalfIndex++;
            } else {
                a[aIndex] = lastHalf[lastHalfIndex];
                lastHalfIndex++;
            }
            aIndex++;
            numberMerged++;
        }
        int numberAppended = 0;
        //At least one of firstHalf and lastHalf has been
        //completely copied to a.
        //Copy rest of firstHalf, if any.
        while (firstHalfIndex < firstHalf.length) {
            a[aIndex] = firstHalf[firstHalfIndex];
            aIndex++;
            firstHalfIndex++;
            numberAppended++;
        }
        //Copy rest of lastHalf, if any.
        while (lastHalfIndex < lastHalf.length) {
            a[aIndex] = lastHalf[lastHalfIndex];
            aIndex++;
            lastHalfIndex++;
            numberAppended++;
        }
        System.out.println("Number of items merged: " + numberMerged + ", number of items appended: " + numberAppended);
        System.out.println(prefix + "Exiting merge(a.length=" + a.length + ", firstHalf.length = " + firstHalf.length + ", lastHalf.length = " + lastHalf.length + ")");
    }
}
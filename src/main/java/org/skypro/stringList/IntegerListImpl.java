package org.skypro.stringList;

import org.skypro.exceptions.IllegalIndexException;
import org.skypro.exceptions.IllegalInputException;
import org.skypro.exceptions.NullInputException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList{
    private Integer[] integerArray;
    private int initLength;
    private int size;

    public IntegerListImpl(int initLength) {
        this.initLength = initLength;
        this.integerArray = new Integer[initLength];
        this.size = 0;

    }

    public int getInitLength() {
        return initLength;
    }

    public void doubleArray() {
        initLength *= 1.5;
        integerArray = Arrays.copyOf(integerArray, initLength);
    }

    private void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Integer temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    public static void merge(Integer[] arr, Integer[] left, Integer[] right) {

        int mainP = 0;
        int leftP = 0;
        int rightP = 0;
        while (leftP < left.length && rightP < right.length) {
            if (left[leftP] <= right[rightP]) {
                arr[mainP++] = left[leftP++];
            } else {
                arr[mainP++] = right[rightP++];
            }
        }
        while (leftP < left.length) {
            arr[mainP++] = left[leftP++];
        }
        while (rightP < right.length) {
            arr[mainP++] = right[rightP++];
        }
    }
    public static void mergeSort(Integer[] arr) {
        if (arr.length < 2) {
            return;
        }
        int mid = arr.length / 2;
        Integer[] left = new Integer[mid];
        Integer[] right = new Integer[arr.length - mid];

        for (int i = 0; i < left.length; i++) {
            left[i] = arr[i];
        }

        for (int i = 0; i < right.length; i++) {
            right[i] = arr[mid + i];
        }

        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right);
    }



    private boolean containsSorted(Integer[] arr, Integer element) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (element.equals(arr[mid])) {
                return true;
            }

            if (element < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public Integer add(Integer item) {
        if (item == null) {
            throw new NullInputException("Item shouldn't be null");
        }
        if (size == initLength) {
            doubleArray();
        }
        integerArray[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        if (item == null) {
            throw new NullInputException("Item shouldn't be null");
        }
        if (index > size) {
            throw new IllegalIndexException("Index out of bounds");
        }
        if (size == initLength) {
            doubleArray();
        }
        for (int i = size - 1; i >= index; i--) {
            integerArray[i + 1] = integerArray[i];
        }
        integerArray[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        if (item == null) {
            throw new NullInputException("Item shouldn't be null");
        }
        if (index > size) {
            throw new IllegalIndexException("Index out of bounds");
        }
        integerArray[index] = item;
        return item;
    }

    @Override
    public Integer remove(Double item1) {
        Integer item = item1.intValue();
        if (item == null) {
            throw new NullInputException("Item shouldn't be null");
        }
        int itemIndex = initLength;
        for (int i = 0; i < size; i++) {
            if (integerArray[i].equals(item)) {
                itemIndex = i;
            }
            if (itemIndex != initLength) {
                if (i == initLength - 1) {
                    integerArray[i] = null;
                } else {
                    integerArray[i] = integerArray[i + 1];
                }
            }
        }
        if (itemIndex == initLength) {
            throw new IllegalInputException("There isn't such item");
        }
        size--;
        return item;
    }

    @Override
    public Integer remove(int index) {
        if (index > size - 1) {
            throw new IllegalIndexException("Index out of bounds");
        }
        Integer itemToRemove = integerArray[index];
        for (int i = index; i < size; i++) {
            if (i == initLength - 1) {
                integerArray[i] = null;
            } else {
                integerArray[i] = integerArray[i + 1];
            }
        }
        size--;
        return itemToRemove;
    }

    @Override
    public boolean contains(Integer item) {
        Integer[] tmp = Arrays.copyOf(integerArray, size);
        mergeSort(tmp);
        return containsSorted(tmp, item);
    }

    @Override
    public int indexOf(Integer item) {
        if (item == null) {
            throw new NullInputException("Item shouldn't be null");
        }
        for (int i = 0; i < size; i++) {
            if (integerArray[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        if (item == null) {
            throw new NullInputException("Item shouldn't be null");
        }
        for (int i = size - 1; i >= 0; i--) {
            if (integerArray[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (index > size - 1) {
            throw new IllegalIndexException("Index out of bounds");
        }
        return integerArray[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null) {
            throw new IllegalInputException("Input shouldn't be null");
        }
        if (this.size != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!this.toArray()[i].equals(otherList.toArray()[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            integerArray[i] = null;
        }
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(integerArray, size);
    }
}

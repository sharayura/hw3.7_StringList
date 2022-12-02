package org.skypro.stringList;

import org.skypro.exceptions.IllegalIndexException;
import org.skypro.exceptions.IllegalInputException;
import org.skypro.exceptions.NullInputException;

import java.util.Arrays;

public class StringListImpl implements StringList {

    private String[] stringArray;
    private int initLength;
    private int size;

    public StringListImpl(int initLength) {
        this.initLength = initLength;
        this.stringArray = new String[initLength];
        this.size = 0;

    }

    public void doubleArray() {
        initLength *= 2;
        String[] tmpArray = Arrays.copyOf(stringArray, initLength);
        stringArray = tmpArray;
    }

    @Override
    public String add(String item) {
        if (item == null) {
            throw new NullInputException("String shouldn't be null");
        }
        if (size == initLength) {
            doubleArray();
        }
        stringArray[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        if (item == null) {
            throw new NullInputException("String shouldn't be null");
        }
        if (index > size) {
            throw new IllegalIndexException("Index out of bounds");
        }
        if (size == initLength) {
            doubleArray();
        }
        for (int i = size - 1; i >= index; i--) {
            stringArray[i + 1] = stringArray[i];
        }
        stringArray[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        if (item == null) {
            throw new NullInputException("String shouldn't be null");
        }
        if (index > size) {
            throw new IllegalIndexException("Index out of bounds");
        }
        stringArray[index] = item;
        size++;
        return item;
    }

    @Override
    public String remove(String item) {
        if (item == null) {
            throw new NullInputException("String shouldn't be null");
        }
        int itemIndex = initLength;
        for (int i = 0; i < size; i++) {
            if (stringArray[i].equals(item)) {
                itemIndex = i;
            }
            if (itemIndex != initLength) {
                if (itemIndex == initLength - 1) {
                    stringArray[i] = null;
                } else {
                    stringArray[i] = stringArray[i + 1];
                }
            }
        }
        if (itemIndex == initLength) {
            throw new IllegalInputException("There isn't such string");
        }
        size--;
        return item;
    }

    @Override
    public String remove(int index) {
        if (index > size - 1) {
            throw new IllegalIndexException("Index out of bounds");
        }
        String itemToRemove = stringArray[index];
        for (int i = index; i < size; i++) {
            if (i == initLength - 1) {
                stringArray[i] = null;
            } else {
                stringArray[i] = stringArray[i + 1];
            }
        }
        return itemToRemove;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        if (item == null) {
            throw new NullInputException("String shouldn't be null");
        }
        int itemIndex = -1;
        for (int i = 0; i < size; i++) {
            if (stringArray[i].equals(item)) {
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }

    @Override
    public int lastIndexOf(String item) {
        if (item == null) {
            throw new NullInputException("String shouldn't be null");
        }
        int itemIndex = -1;
        for (int i = size - 1; i >= 0; i--) {
            if (stringArray[i].equals(item)) {
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }

    @Override
    public String get(int index) {
        if (index > size - 1) {
            throw new IllegalIndexException("Index out of bounds");
        }
        return stringArray[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            throw new IllegalInputException("Input shouldn't be null");
        }
        if (this.size != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!this.stringArray[i].equals(otherList.toArray()[i])) {
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
            stringArray[i] = null;
        }
    }

    @Override
    public String[] toArray() {
        return stringArray;
    }
}

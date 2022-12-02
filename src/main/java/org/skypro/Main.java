package org.skypro;

import org.skypro.stringList.StringList;
import org.skypro.stringList.StringListImpl;

public class Main {
    public static void main(String[] args) {

        StringList stringList = new StringListImpl(2);
        stringList.add("one");
        stringList.add("two");
        stringList.add("three");

        stringList.remove("two");

        System.out.println(stringList.toArray()[2]);

    }
}